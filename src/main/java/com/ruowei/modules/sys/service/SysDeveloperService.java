package com.ruowei.modules.sys.service;

import com.ruowei.common.error.exception.EmailAlreadyUsedException;
import com.ruowei.common.error.exception.EmailNotFoundException;
import com.ruowei.common.error.exception.InvalidPasswordException;
import com.ruowei.common.error.exception.LoginAlreadyUsedException;
import com.ruowei.common.service.BaseService;
import com.ruowei.config.Constants;
import com.ruowei.modules.sys.domain.entity.Authority;
import com.ruowei.modules.sys.domain.entity.SysUser;
import com.ruowei.modules.sys.mapper.SysUserMapper;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import com.ruowei.modules.sys.pojo.UserDTO;
import com.ruowei.modules.sys.pojo.user.SysUserRegisterDTO;
import com.ruowei.modules.sys.repository.AuthorityRepository;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.service.api.SysDeveloperApi;
import com.ruowei.modules.sys.service.util.RandomUtil;
import com.ruowei.modules.sys.service.util.SysUserUtil;
import com.ruowei.security.AuthoritiesConstants;
import com.ruowei.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 刘东奇
 * @date 2019/11/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDeveloperService extends BaseService implements SysDeveloperApi {
    private final Logger log = LoggerFactory.getLogger(SysDeveloperService.class);

    private final MailService mailService;

    private final AuthorityRepository authorityRepository;
    private final SysUserRepository sysUserRepository;

    private final SysUserMapper sysUserMapper;

    private final PasswordEncoder passwordEncoder;

    private final CacheManager cacheManager;

    public SysDeveloperService(MailService mailService, AuthorityRepository authorityRepository, SysUserRepository sysUserRepository, SysUserMapper sysUserMapper, PasswordEncoder passwordEncoder, CacheManager cacheManager) {
        this.mailService = mailService;
        this.authorityRepository = authorityRepository;
        this.sysUserRepository = sysUserRepository;
        this.sysUserMapper = sysUserMapper;
        this.passwordEncoder = passwordEncoder;
        this.cacheManager = cacheManager;
    }

    /**
     * 获取所有权限
     *
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

    /**
     * 根据登录ID获取用户信息并携带权限
     *
     * @param loginCode
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    public Optional<UserDTO> getUserWithAuthoritiesByLoginCode(String loginCode) {
        return sysUserRepository.findOneWithAuthoritiesByLoginCode(loginCode)
            .map(UserDTO::new);
    }

    /**
     * 根据登录ID删除用户
     *
     * @param loginCode
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    public void deleteUserByLoginCode(String loginCode) {
        sysUserRepository.findOneByLoginCode(loginCode).ifPresent(user -> {
            sysUserRepository.delete(user);
            this.clearUserCaches(user.getLoginCode(),user.getEmail());
            log.debug("Deleted User: {}", user);
        });
    }

    /**
     * 注册用户
     *
     * @param sysUserRegisterDTO 从注册页面传来的数据
     * @return
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public SysUserDTO registerUser(SysUserRegisterDTO sysUserRegisterDTO) {
        //TODO 注册用户 ？？？
        sysUserRepository.findOneByLoginCode(sysUserRegisterDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
            throw new LoginAlreadyUsedException();
        });
        sysUserRepository.findOneByEmailIgnoreCase(sysUserRegisterDTO.getEmail()).ifPresent(existingUser -> {
            throw new EmailAlreadyUsedException();
        });
        SysUser user = sysUserMapper.assembleEntity(sysUserRegisterDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLoginCode(user.getLoginCode().toLowerCase());
        user.setActivationKey(SysUserUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        user.setAuthorities(authorities);
        sysUserRepository.save(user);
        this.clearUserCaches(user.getLoginCode(),user.getEmail());
        log.debug("Created Information for User: {}", user);
        return sysUserMapper.toDto(user);
    }

    /**
     * 根据激活码激活用户
     *
     * @param key
     * @return
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public Optional<SysUser> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        // 根据激活码找到SysUser并更新激活状态
        return sysUserRepository.findOneByActivationKey(key)
            .map(user -> {
                user.setActivated(true);
                user.setActivationKey(null);
                this.clearUserCaches(user.getLoginCode(),user.getEmail());
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    /**
     * 获取当前登录用户信息并携带权限
     *
     * @author 刘东奇
     * @date 2019/10/21
     * @return
     */
    @Override
    public Optional<UserDTO> getCurUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(this::getUserWithAuthoritiesByLoginCode);
    }

    /**
     * 通过LoginCode查询用户
     *
     * @param loginCode
     * @return
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public Optional<SysUserDTO> findOneByLoginCode(String loginCode) {
        return this.sysUserRepository.findOneByLoginCode(loginCode).map(
            user->{
                SysUserDTO sysUserDTO = sysUserMapper.toDto(user);
                return sysUserDTO;
            }
        );
    }

    /**
     * 通过email查询用户
     *
     * @param email
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public Optional<SysUserDTO> findOneByEmailIgnoreCase(String email) {
        return this.sysUserRepository.findOneByEmailIgnoreCase(email).map(
            user->{
                SysUserDTO sysUserDTO = sysUserMapper.toDto(user);
                return sysUserDTO;
            }
        );
    }

    /**
     * 更新用户
     *
     * @param userDTO
     * @return
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(this.sysUserRepository
            .findById(userDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                this.clearUserCaches(user);
                user.setLoginCode(userDTO.getLogin().toLowerCase());
                user.setUserName(userDTO.getFirstName());
                user.setRefName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail().toLowerCase());
//                user.setImageUrl(userDTO.getImageUrl());
                user.setActivated(userDTO.isActivated());
//                user.setLangKey(userDTO.getLangKey());
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                userDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(managedAuthorities::add);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }

    /**
     * 更新用户
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param langKey
     * @param imageUrl
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(this.sysUserRepository::findOneByLoginCode)
            .ifPresent(user -> {
                user.setUserName(firstName);
                user.setRefName(lastName);
                user.setEmail(email.toLowerCase());
//                user.setLangKey(langKey);
//                user.setImageUrl(imageUrl);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
            });
    }

    /**
     * 修改密码
     *
     * @param currentClearTextPassword
     * @param newPassword
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(this.sysUserRepository::findOneByLoginCode)
            .ifPresent(user -> {
                String currentEncryptedPassword = user.getPassword();
                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    throw new InvalidPasswordException();
                }
                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);
                this.clearUserCaches(user.getLoginCode(),user.getEmail());
                log.debug("Changed password for User: {}", user);
            });
    }

    /**
     * 请求密码重置
     *
     * @param mail
     * @return
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public void requestPasswordReset(String mail) {
        // 按邮箱查询用户
        // 生成重置码
        // 发送对方邮箱
        mailService.sendPasswordResetMail(findOneByEmailIgnoreCase(mail)
            .filter(SysUserDTO::isActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                this.clearUserCaches(user.getLoginCode(),user.getEmail());
                return user;
            }).orElseThrow(EmailNotFoundException::new));
        return;
    }

    /**
     * 根据新密码和重置码完成密码重置
     *
     * @param newPassword
     * @param key
     * @return
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public Optional<SysUser> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return this.sysUserRepository.findOneByResetKey(key)
            .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
            .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                this.clearUserCaches(user.getLoginCode(),user.getEmail());
                return user;
            });
    }

    /**
     * 分页获取用户
     *
     * @param pageable
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {

        List<Sort.Order> sortOrderList = new ArrayList<>();
        for (Sort.Order order : pageable.getSort()) {
            Sort.Order newOrder;
            if(order.getProperty().equals("login")){
                newOrder = new Sort.Order(order.getDirection(),"loginCode");
            }else if(order.getProperty().equals("langKey")){
               continue;
            }else{
                newOrder=order;
            }
            sortOrderList.add(newOrder);
        }
        Pageable newPageable = new PageRequest(pageable.getPageNumber(),
            pageable.getPageSize(),
            new Sort(sortOrderList));
        return this.sysUserRepository.findAllByLoginCodeNot(newPageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    /**
     * 创建用户
     * @author 刘东奇
     * @date 2019/11/14
     * @param userDTO
     * @return
     */
    @Override
    public SysUser createUser(UserDTO userDTO) {
        SysUser user = new SysUser();
        user.setLoginCode(userDTO.getLogin().toLowerCase());
        user.setUserName(userDTO.getFirstName());
        user.setRefName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail().toLowerCase());
//        user.setImageUrl(userDTO.getImageUrl());
//        if (userDTO.getLangKey() == null) {
//            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
//        } else {
//            user.setLangKey(userDTO.getLangKey());
//        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = userDTO.getAuthorities().stream()
                .map(authorityRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        this.sysUserRepository.save(user);
        this.clearUserCaches(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * 更新用户表缓存
     * @author 刘东奇
     * @date 2019/10/21
     * @param loginCode
     * @param email
     */
    private void clearUserCaches(String loginCode,String email) {
        Objects.requireNonNull(cacheManager.getCache(SysUserRepository.USERS_BY_LOGIN_CODE_CACHE)).evict(loginCode);
        Objects.requireNonNull(cacheManager.getCache(SysUserRepository.USERS_BY_EMAIL_CACHE)).evict(email);
    }

    private void clearUserCaches(SysUser user) {
        Objects.requireNonNull(cacheManager.getCache(SysUserRepository.USERS_BY_LOGIN_CODE_CACHE)).evict(user.getLoginCode());
        Objects.requireNonNull(cacheManager.getCache(SysUserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
    }

}
