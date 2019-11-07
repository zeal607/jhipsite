package com.ruowei.modules.sys.service.user.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataAlreadyExistException;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.common.service.crud.CrudBaseService;
import com.ruowei.config.Constants;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.table.Authority;
import com.ruowei.modules.sys.domain.table.QSysUser;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.mapper.SysUserEmployeeMapper;
import com.ruowei.modules.sys.mapper.SysUserMapper;
import com.ruowei.modules.sys.pojo.*;
import com.ruowei.modules.sys.pojo.user.SysUserRegisterDTO;
import com.ruowei.modules.sys.repository.AuthorityRepository;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.service.employee.SysEmployeeOfficeService;
import com.ruowei.modules.sys.service.employee.SysEmployeePostService;
import com.ruowei.modules.sys.service.employee.SysEmployeeService;
import com.ruowei.modules.sys.service.office.SysOfficeQueryService;
import com.ruowei.modules.sys.service.user.SysUserRoleService;
import com.ruowei.modules.sys.service.user.SysUserService;
import com.ruowei.modules.sys.service.company.SysCompanyQueryService;
import com.ruowei.modules.sys.service.MailService;
import com.ruowei.modules.sys.service.user.util.SysUserUtil;
import com.ruowei.security.AuthoritiesConstants;
import com.ruowei.security.SecurityUtils;
import com.ruowei.service.util.RandomUtil;
import com.ruowei.web.rest.errors.EmailAlreadyUsedException;
import com.ruowei.web.rest.errors.EmailNotFoundException;
import com.ruowei.web.rest.errors.InvalidPasswordException;
import com.ruowei.web.rest.errors.LoginAlreadyUsedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 刘东奇
 */
@Service
@Transactional
public class SysUserServiceImpl
    extends CrudBaseService<
    SysUser,
    QSysUser,
    SysUserRepository>
    implements SysUserService {

    private final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    /**
     * service依赖
     */
    private final SysUserQueryService sysUserQueryService;
    private final SysOfficeQueryService sysOfficeQueryService;
    private final SysCompanyQueryService sysCompanyQueryService;
    private final SysEmployeeService sysEmployeeService;
    private final SysEmployeePostService sysEmployeePostService;
    private final SysEmployeeOfficeService sysEmployeeOfficeService;
    private final SysUserRoleService sysUserRoleService;
    private final MailService mailService;
    /**
     * repository依赖
     */
    private final AuthorityRepository authorityRepository;
    /**
     * mapper依赖
     */
    private final SysUserEmployeeMapper sysUserEmployeeMapper;
    private final SysUserMapper sysUserMapper;
    /**
     * 密码加密器
     */
    private final PasswordEncoder passwordEncoder;
    /**
     * 缓存
     */
    private final CacheManager cacheManager;

    private final static String DEFAULT_PASSWORD = "123456";

    public SysUserServiceImpl(SysUserQueryService sysUserQueryService,
                              SysOfficeQueryService sysOfficeQueryService,
                              SysCompanyQueryService sysCompanyQueryService,
                              SysEmployeeService sysEmployeeService,
                              SysEmployeePostService sysEmployeePostService,
                              SysEmployeeOfficeService sysEmployeeOfficeService,
                              SysUserRoleService sysUserRoleService,
                              MailService mailService,
                              AuthorityRepository authorityRepository,
                              SysUserEmployeeMapper sysUserEmployeeMapper,
                              SysUserMapper sysUserMapper,
                              PasswordEncoder passwordEncoder,
                              CacheManager cacheManager) {
        this.sysUserQueryService = sysUserQueryService;
        this.sysOfficeQueryService = sysOfficeQueryService;
        this.sysCompanyQueryService = sysCompanyQueryService;
        this.sysEmployeeService = sysEmployeeService;
        this.sysEmployeePostService = sysEmployeePostService;
        this.sysEmployeeOfficeService = sysEmployeeOfficeService;
        this.sysUserRoleService = sysUserRoleService;
        this.mailService = mailService;

        this.authorityRepository = authorityRepository;

        this.sysUserEmployeeMapper = sysUserEmployeeMapper;
        this.sysUserMapper = sysUserMapper;

        this.passwordEncoder = passwordEncoder;

        this.cacheManager = cacheManager;
    }

    /**
     * 通过条件分页查询机构员工
     *
     * @param userCriteria
     * @param employeeCriteria
     * @param page
     * @author 刘东奇
     * @date 2019/9/8
     */
    @Override
    public QueryResults<SysUserEmployeeVM> findSysUserEmployeeVMPageByCriteria(SysUserCriteria userCriteria, SysEmployeeCriteria employeeCriteria, Pageable page) {
        QueryResults<SysUserEmployeeVM> results = sysUserQueryService.findPageVMByCriteriaArray(page,userCriteria,employeeCriteria);
        return results;
    }

    /**
     * 通过条件分页查询员工信息
     *
     * @param sysUserPredicate
     * @param sysEmployeePredicate
     * @param pageable
     * @return
     * @author 刘东奇
     * @date 2019/11/4
     */
    @Override
    public QueryResults<SysUserEmployeeVM> findSysUserEmployeeVMPageByPredicate(Predicate sysUserPredicate, Predicate sysEmployeePredicate, Pageable pageable) {
        BooleanBuilder booleanBuilder =new BooleanBuilder(sysUserPredicate).and(sysEmployeePredicate);
       return null;
    }

    /**
     * 通过主键获取员工信息
     *
     * @param id
     * @author 刘东奇
     * @date 2019/9/16
     */
    @Override
    public Optional<SysUserEmployeeDTO> getSysUserEmployeeById(Long id) {
        return sysUserQueryService.getDTOById(id);
    }

    /**
     * 创建员工
     *
     * @param sysUserEmployeeDTO
     * @author 刘东奇
     * @date 2019/9/22
     */
    @Override
    public SysUserEmployeeDTO createSysUserEmployee(SysUserEmployeeDTO sysUserEmployeeDTO) {
        String officeCode = null;

        if(sysUserEmployeeDTO.getSysOfficeId() != null){
            // 判断机构是否有效存在
            Optional<SysOfficeDTO> sysOfficeDTOOptional = sysOfficeQueryService.getDTOById(Long.valueOf(sysUserEmployeeDTO.getSysOfficeId()));
            if(sysOfficeDTOOptional.isPresent()){
                officeCode =sysOfficeDTOOptional.get().getOfficeCode();
            }else{
                // 机构不存在
                throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage("机构",sysUserEmployeeDTO.getSysOfficeId()));
            }
        }

        if(sysUserEmployeeDTO.getSysCompanyId() != null){
            // 判断公司是否有效存在
            if(!sysCompanyQueryService.existsById(Long.valueOf(sysUserEmployeeDTO.getSysCompanyId()))){
                // 公司不存在
                throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage("公司",sysUserEmployeeDTO.getSysCompanyId()));
            }
        }

        // 创建用户
        // 判断登录id、电话是否重复
        QSysUser qSysUser = QSysUser.sysUser;
        BooleanBuilder booleanBuilder = new BooleanBuilder(qSysUser.loginCode.eq(sysUserEmployeeDTO.getLoginCode()));
        HashMap map= new HashMap<String,String>();
        map.put("登录ID", sysUserEmployeeDTO.getLoginCode());
        if(StringUtils.isNotEmpty(sysUserEmployeeDTO.getMobile())){
            booleanBuilder.or(qSysUser.mobile.eq(sysUserEmployeeDTO.getMobile()));
            map.put("电话", sysUserEmployeeDTO.getMobile());
        }
        if(this.sysUserQueryService.exists(booleanBuilder)){
            throw new DataAlreadyExistException(ErrorMessageUtils.getAlreadyExistMessageByOrMap("用户",map));
        }
        SysUser sysUser = sysUserEmployeeMapper.projectIntoUser(sysUserEmployeeDTO);
        sysUser.setStatus(UserStatusType.NORMAL);
        sysUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        sysUser.setUserCode(SysUserUtil.generateUserCode());
        sysUser = this.insert(sysUser);

        //创建员工
        SysEmployee sysEmployee = sysUserEmployeeMapper.projectIntoEmployee(sysUserEmployeeDTO);
        sysEmployee.setStatus(EmployeeStatusType.NORMAL);
        // 生成员工编号
        sysEmployee.setEmpCode(sysEmployeeService.generateEmpCode(sysUserEmployeeDTO.getSysOfficeId(),officeCode));
        sysEmployeeService.insert(sysEmployee);

        // 保存岗位关系
        // 判断岗位是否存在
        sysEmployeePostService.saveEmployeePostRelationship(sysEmployee,sysUserEmployeeDTO.getSysPostDTOList());

        // 保存附属机构及岗位
        // 判断附属机构及岗位是否存在
        sysEmployeeOfficeService.saveEmployeeOfficePostRelationship(sysEmployee,sysUserEmployeeDTO.getSysEmployeeOfficeDTOList());

        // 保存角色关系
        sysUserRoleService.saveUserRoleRelationship(sysUser,sysUserEmployeeDTO.getSysRoleDTOList());

        Optional<SysUserEmployeeDTO> sysUserEmployeeDTOOptional = getSysUserEmployeeById(sysUser.getId());
        return sysUserEmployeeDTOOptional.get();
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
        return jpaRepository.findOneByActivationKey(key)
            .map(user -> {
                user.setActivated(true);
                user.setActivationKey(null);
                this.clearUserCaches(user.getLoginCode(),user.getEmail());
                log.debug("Activated user: {}", user);
                return user;
            });
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
        return jpaRepository.findOneByResetKey(key)
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
     * 注册用户
     *
     * @param sysUserRegisterDTO
     * @return
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public SysUserDTO registerUser(SysUserRegisterDTO sysUserRegisterDTO) {
        //TODO 注册用户
        jpaRepository.findOneByLoginCode(sysUserRegisterDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
            throw new LoginAlreadyUsedException();
        });
        jpaRepository.findOneByEmailIgnoreCase(sysUserRegisterDTO.getEmail()).ifPresent(existingUser -> {
            throw new EmailAlreadyUsedException();
        });
        SysUser user = sysUserMapper.assembleEntity(sysUserRegisterDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLoginCode(user.getLoginCode().toLowerCase());
        user.setActivationKey(SysUserUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        user.setAuthorities(authorities);
        jpaRepository.save(user);
        this.clearUserCaches(user.getLoginCode(),user.getEmail());
        log.debug("Created Information for User: {}", user);
        return sysUserMapper.toDto(user);
    }

    /**
     * 创建用户
     *
     * @param userDTO
     * @return
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public SysUserDTO createUser(SysUserDTO userDTO) {
        //TODO 创建用户
        return null;
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
    public SysUserDTO updateUser(SysUserDTO userDTO) {
        //TODO 更新用户
        return null;
    }

    /**
     * 根据登录Id删除用户
     *
     * @param loginCode
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public void deleteUser(String loginCode) {
        jpaRepository.findOneByLoginCode(loginCode).ifPresent(user -> {
            jpaRepository.delete(user);
            this.clearUserCaches(user.getLoginCode(),user.getEmail());
            log.debug("Deleted User: {}", user);
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
            .flatMap(jpaRepository::findOneByLoginCode)
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
     * 获取用户列表
     *
     * @param pageable
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public Page<SysUserDTO> getAllUsers(Pageable pageable) {
        return jpaRepository.findAllByLoginCodeNot(pageable, Constants.ANONYMOUS_USER).map(user->
            sysUserMapper.toDto(user)
        );
    }

    /**
     * 通过LoginCode查询用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param loginCode
     */
    @Override
    public Optional<SysUserDTO> findOneByLoginCode(String loginCode) {
        return this.jpaRepository.findOneByLoginCode(loginCode).map(
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
        return this.jpaRepository.findOneByEmailIgnoreCase(email).map(
            user->{
                SysUserDTO sysUserDTO = sysUserMapper.toDto(user);
                return sysUserDTO;
            }
        );
    }


    /**
     * Gets a list of all the authorities.
     * @return a list of all the authorities.
     */
    @Override
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

    /**
     * 根据登录Id获取用户信息并携带权限
     *
     * @param loginCode
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public Optional<SysUserDTO> getUserWithAuthoritiesByLogin(String loginCode) {
        return jpaRepository.findOneWithAuthoritiesByLoginCode(loginCode).map(user->sysUserMapper.toDto(user));
    }

    /**
     * 获取当前登录用户信息并携带权限
     *
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    public Optional<SysUserDTO> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(this::getUserWithAuthoritiesByLogin);
    }
}
