package com.ruowei.modules.sys.web.rest;

import com.ruowei.common.error.exception.BadRequestAlertException;
import com.ruowei.common.error.exception.EmailAlreadyUsedException;
import com.ruowei.common.error.exception.InvalidPasswordException;
import com.ruowei.common.error.exception.LoginAlreadyUsedException;
import com.ruowei.config.Constants;
import com.ruowei.modules.sys.domain.entity.SysDeveloperUser;
import com.ruowei.modules.sys.domain.enumeration.Renyuanleixing;
import com.ruowei.modules.sys.domain.table.SysUserTable;
import com.ruowei.modules.sys.mapper.SysUserMapper;
import com.ruowei.modules.sys.pojo.PasswordChangeDTO;
import com.ruowei.modules.sys.pojo.UserDTO;
import com.ruowei.modules.sys.repository.table.SysUserTableRepository;
import com.ruowei.modules.sys.service.MailService;
import com.ruowei.modules.sys.service.SysDeveloperService;
import com.ruowei.modules.sys.web.MeijuBody;
import com.ruowei.modules.sys.web.api.SysDeveloperApi;
import com.ruowei.modules.sys.web.vm.KeyAndPasswordVM;
import com.ruowei.modules.sys.web.vm.ManagedUserVM;
import com.ruowei.security.AuthoritiesConstants;
import com.ruowei.security.SecurityUtils;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for 开发者相关
 * @author 刘东奇
 */
@RestController
@RequestMapping("/api")
@Api(value = "开发者相关",tags = "开发者相关")
public class SysDeveloperResource implements SysDeveloperApi {

    private final Logger log = LoggerFactory.getLogger(SysDeveloperResource.class);

    private final SysDeveloperService sysDeveloperService;
    private final MailService mailService;

    private final SysUserTableRepository sysUserTableRepository;

    private final SysUserMapper sysUserMapper;

    @Value("${spring.application.name}")
    private String applicationName;

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    public SysDeveloperResource(SysDeveloperService sysDeveloperService, MailService mailService, SysUserTableRepository sysUserTableRepository, SysUserMapper sysUserMapper) {
        this.sysDeveloperService = sysDeveloperService;
        this.mailService = mailService;
        this.sysUserTableRepository = sysUserTableRepository;
        this.sysUserMapper = sysUserMapper;
    }

    /**
     * 获取所有权限
     *
     * @author 刘东奇
     * @date 2019/10/21
     */
    @Override
    @GetMapping("/users/authorities")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<String> getAuthorities() {
        return sysDeveloperService.getAuthorities();
    }

    /**
     * 根据loginCode获取用户信息
     *
     * @param login
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @GetMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
        log.debug("REST request to get User : {}", login);
        return ResponseUtil.wrapOrNotFound(
            sysDeveloperService.getUserWithAuthoritiesByLoginCode(login));
    }

    /**
     * 根据loginCode删除用户信息
     *
     * @param login
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @DeleteMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.debug("REST request to delete User: {}", login);
        sysDeveloperService.deleteUserByLoginCode(login);
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName,  "userManagement.deleted", login)).build();
    }

    /**
     * 注册用户
     *
     * @param managedUserVM
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM ) {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        SysDeveloperUser user = sysDeveloperService.registerUser(managedUserVM,managedUserVM.getPassword());
        mailService.sendActivationEmail(user);
    }

    /**
     * 激活用户
     *
     * @param key
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @GetMapping("/activate")
    public void activateAccount(@RequestParam(value = "key") String key) {
        Optional<SysUserTable> user = sysDeveloperService.activateRegistration(key);
        if (!user.isPresent()) {
            throw new AccountResourceException("No user was found for this activation key");
        }
    }

    /**
     * 检查登录者是否有权限并且返回其登录ID
     *
     * @param request
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * 获取当前用户信息及权限
     *
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @GetMapping("/account")
    public UserDTO getAccount() {
        return sysDeveloperService.getCurUserWithAuthorities()
            .orElseThrow(() -> new AccountResourceException("User could not be found"));
    }

    /**
     * 更新当前账号信息
     *
     * @param userDTO
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @PostMapping("/account")
    public void saveAccount(@Valid @RequestBody UserDTO userDTO) {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        Optional<SysUserTable> existingUser = this.sysUserTableRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getLoginCode().equalsIgnoreCase(userLogin))) {
            throw new EmailAlreadyUsedException();
        }
        Optional<SysUserTable> user = this.sysUserTableRepository.findOneByLoginCode(userLogin);
        if (!user.isPresent()) {
            throw new AccountResourceException("User could not be found");
        }
        this.sysDeveloperService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
            userDTO.getLangKey(), userDTO.getImageUrl());
    }

    /**
     * 修改当前账号密码
     *
     * @param passwordChangeDto
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @PostMapping(path = "/account/change-password")
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        sysDeveloperService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    /**
     * 重置账号密码
     *
     * @param mail
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @PostMapping(path = "/account/reset-password/init")
    public void requestPasswordReset(@RequestBody String mail) {
        sysDeveloperService.requestPasswordReset(mail);
    }

    /**
     * 完成密码重置
     *
     * @param keyAndPassword
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @PostMapping(path = "/account/reset-password/finish")
    public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        Optional<SysUserTable> user =
            sysDeveloperService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

        if (!user.isPresent()) {
            throw new AccountResourceException("No user was found for this reset key");
        }
    }

    /**
     * 更新用户信息
     *
     * @param userDTO
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @PutMapping("/users")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    @Transactional
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        log.debug("REST request to update User : {}", userDTO);
        Optional<SysUserTable> existingUser = this.sysUserTableRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new EmailAlreadyUsedException();
        }

        existingUser = this.sysUserTableRepository.findOneByLoginCode(userDTO.getLogin().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new LoginAlreadyUsedException();
        }

        Optional<UserDTO> updatedUser = this.sysDeveloperService.updateUser(userDTO);
        return ResponseUtil.wrapOrNotFound(updatedUser,
            HeaderUtil.createAlert(applicationName, "userManagement.updated", userDTO.getLogin()));
    }

    /**
     * 分页获取用户数据
     *
     * @param pageable
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder, Pageable pageable) {
        final Page<UserDTO> page = this.sysDeveloperService.getAllManagedUsers(pageable);
        HttpHeaders headers = io.github.jhipster.web.util.PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @Override
    @PostMapping("/users")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<SysDeveloperUser> createUser(@Valid UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);

        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
            // Lowercase the user login before comparing with database
        } else if (sysDeveloperService.findOneByLoginCode(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (sysDeveloperService.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            SysDeveloperUser newUser = sysDeveloperService.createUser(userDTO);
            mailService.sendCreationEmail(newUser);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin()))
                .headers(HeaderUtil.createAlert(applicationName,  "userManagement.created", newUser.getLogin()))
                .body(newUser);
        }
    }


    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }

    @GetMapping("/test/meiju")
    public ResponseEntity<Renyuanleixing> getMeiju(Renyuanleixing renyuanleixing) {
        System.out.println(renyuanleixing);
        return ResponseEntity.ok(renyuanleixing);
    }

    @GetMapping("/test/meijus")
    public ResponseEntity<Renyuanleixing[]> getMeijus() {
        System.out.println(Renyuanleixing.values());
        return ResponseEntity.ok(Renyuanleixing.values());
    }

    @PostMapping("/test/meiju")
    public ResponseEntity<MeijuBody> posttMeiju(@RequestBody MeijuBody meijuBody) {
        System.out.println(meijuBody);
        return ResponseEntity.ok(meijuBody);
    }
}
