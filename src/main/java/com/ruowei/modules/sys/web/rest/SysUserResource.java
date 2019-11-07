package com.ruowei.modules.sys.web.rest;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.ruowei.common.response.PaginationUtil;
import com.ruowei.config.Constants;
import com.ruowei.modules.sys.domain.SysUserEmployeeLVM;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.pojo.*;

import com.ruowei.modules.sys.pojo.user.SysUserRegisterDTO;
import com.ruowei.modules.sys.repository.SysUserEmployeeLVMRepository;
import com.ruowei.modules.sys.service.MailService;
import com.ruowei.modules.sys.service.user.SysUserService;
import com.ruowei.security.AuthoritiesConstants;
import com.ruowei.security.SecurityUtils;
import com.ruowei.service.dto.PasswordChangeDTO;
import com.ruowei.web.rest.errors.*;
import com.ruowei.web.rest.vm.KeyAndPasswordVM;
import com.ruowei.web.rest.vm.ManagedUserVM;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link SysUser}.
 * @author 刘东奇
 */
@RestController
@RequestMapping("/api")
public class SysUserResource {

    private final Logger log = LoggerFactory.getLogger(SysUserResource.class);

    private static final String ENTITY_NAME = "sysUser";

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysUserService sysUserService;
    private final MailService mailService;

    private final SysUserEmployeeLVMRepository sysUserEmployeeLVMRepository;

    public SysUserResource( SysUserService sysUserService,
                            MailService mailService,
                            SysUserEmployeeLVMRepository sysUserEmployeeLVMRepository) {
        this.sysUserService = sysUserService;
        this.mailService = mailService;
        this.sysUserEmployeeLVMRepository = sysUserEmployeeLVMRepository;
    }

    /**
     * 查询员工分页
     * @author 刘东奇
     * @date 2019/9/16
     * @param sysUserCriteria
     * @param employeeCriteria
     * @param pageable
     */
    @ApiOperation(value = "查询员工分页")
    @GetMapping("/sys-user-employees")
    public ResponseEntity<List<SysUserEmployeeVM>> getAllSysUserEmployees(SysUserCriteria sysUserCriteria, SysEmployeeCriteria employeeCriteria, Pageable pageable) {
        log.debug("REST request to get SysUsers by criteria: {}", sysUserCriteria);
        QueryResults<SysUserEmployeeVM> queryResults = sysUserService.findSysUserEmployeeVMPageByCriteria(sysUserCriteria,employeeCriteria,pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), queryResults);
        return ResponseEntity.ok().headers(headers).body(queryResults.getResults());
    }

    /**
     * 查询员工分页2
     * @author 刘东奇
     * @date 2019/11/4
     * @param sysUserPredicate
     * @param sysEmployeePredicate
     * @param pageable
     */
    @ApiOperation(value = "查询员工分页2")
    @GetMapping("/sys-user-employees2")
    public ResponseEntity<List<SysUserEmployeeVM>> getAllSysUserEmployees2(
        @QuerydslPredicate(root = SysUser.class) Predicate sysUserPredicate,
        @QuerydslPredicate(root = SysEmployee.class) Predicate sysEmployeePredicate,
        Pageable pageable) {
        log.debug("REST request 查询员工分页2 by 参数: {},{}", sysUserPredicate,sysEmployeePredicate);


        QueryResults<SysUserEmployeeVM> queryResults = sysUserService.findSysUserEmployeeVMPageByPredicate(sysUserPredicate,sysEmployeePredicate,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), queryResults);
        return ResponseEntity.ok().headers(headers).body(queryResults.getResults());
    }

    /**
     * 查询员工分页3
     * @author 刘东奇
     * @date 2019/11/5
     * @param pageable
     */
    @ApiOperation(value = "查询员工分页3")
    @GetMapping("/sys-user-employees3")
    public ResponseEntity<List<SysUserEmployeeLVM>> getAllSysUserEmployees3(
        @QuerydslPredicate(root = SysUserEmployeeLVM.class) Predicate sysUserEmployeeLVMPredicate,
        Pageable pageable) {
        log.debug("REST request 查询员工分页3 by 参数: {}", sysUserEmployeeLVMPredicate);

        Page<SysUserEmployeeLVM> page = sysUserEmployeeLVMRepository.findAll(sysUserEmployeeLVMPredicate,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * 获取单个员工
     * @author 刘东奇
     * @date 2019/9/17
     * @param id
     */
    @ApiOperation(value = "获取单个员工")
    @GetMapping("/sys-user-employee/{id}")
    public ResponseEntity<SysUserEmployeeDTO> getSysUserEmployee(@PathVariable Long id) {
        log.debug("REST request to get SysUser : {}", id);
        Optional<SysUserEmployeeDTO> sysUserEmployeeDTO = sysUserService.getSysUserEmployeeById(id);
        return ResponseUtil.wrapOrNotFound(sysUserEmployeeDTO);
    }

    /**
     * 新增员工
     * @author 刘东奇
     * @date 2019/9/22
     */
    @ApiOperation(value = "新增员工")
    @PostMapping("/sys-user-employees")
    public ResponseEntity<SysUserEmployeeDTO> createSysUserEmployee(@Valid @RequestBody SysUserEmployeeDTO sysUserEmployeeDTO) throws URISyntaxException {
        log.debug("REST request to save SysUserEmployee : {}", sysUserEmployeeDTO);
        if (sysUserEmployeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysEmployee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysUserEmployeeDTO result = sysUserService.createSysUserEmployee(sysUserEmployeeDTO);
        return ResponseEntity.created(new URI("/api/sys-user-employees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /users}  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param sysUserDTO the user to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the login or email is already in use.
     */
    @PostMapping("/users")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<SysUserDTO> createUser(@Valid @RequestBody SysUserDTO sysUserDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", sysUserDTO);

        if (sysUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
            // Lowercase the user login before comparing with database
        } else if (sysUserService.findOneByLoginCode(sysUserDTO.getLoginCode().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (sysUserService.findOneByEmailIgnoreCase(sysUserDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            SysUserDTO newUser = sysUserService.createUser(sysUserDTO);
            mailService.sendCreationEmail(newUser);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getLoginCode()))
                .headers(HeaderUtil.createAlert(applicationName,  "userManagement.created", newUser.getLoginCode()))
                .body(newUser);
        }
    }

    /**
     * {@code PUT /users} : Updates an existing User.
     *
     * @param sysUserDTO the user to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already in use.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already in use.
     */
    @PutMapping("/users")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<SysUserDTO> updateUser(@Valid @RequestBody SysUserDTO sysUserDTO) {
        log.debug("REST request to update User : {}", sysUserDTO);
        Optional<SysUserDTO> existingUser = sysUserService.findOneByEmailIgnoreCase(sysUserDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(sysUserDTO.getId()))) {
            throw new EmailAlreadyUsedException();
        }
        existingUser = sysUserService.findOneByLoginCode(sysUserDTO.getLoginCode().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(sysUserDTO.getId()))) {
            throw new LoginAlreadyUsedException();
        }
        Optional<SysUserDTO> updatedUser =  Optional.ofNullable(sysUserService.updateUser(sysUserDTO));

        return ResponseUtil.wrapOrNotFound(updatedUser,
            HeaderUtil.createAlert(applicationName, "userManagement.updated", sysUserDTO.getLoginCode()));
    }

    /**
     * {@code GET /users} : get all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/users")
    public ResponseEntity<List<SysUserDTO>> getAllUsers(Pageable pageable) {
        final Page<SysUserDTO> page = sysUserService.getAllUsers(pageable);
        HttpHeaders headers = io.github.jhipster.web.util.PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * Gets a list of all roles.
     * @return a string list of all roles.
     */
    @GetMapping("/users/authorities")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<String> getAuthorities() {
        return sysUserService.getAuthorities();
    }

    /**
     * {@code GET /users/:login} : get the "login" user.
     *
     * @param loginCode the login of the user to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "login" user, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/users/{loginCode:" + Constants.LOGIN_REGEX + "}")
    public ResponseEntity<SysUserDTO> getUser(@PathVariable String loginCode) {
        log.debug("REST request to get User : {}", loginCode);
        return ResponseUtil.wrapOrNotFound(
            sysUserService.getUserWithAuthoritiesByLogin(loginCode));
    }

    /**
     * {@code DELETE /users/:login} : delete the "login" User.
     *
     * @param login the login of the user to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.debug("REST request to delete User: {}", login);
        sysUserService.deleteUser(login);
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName,  "userManagement.deleted", login)).build();
    }

    /**
     * {@code POST  /register} : register the user.
     *
     * @param sysUserRegisterDTO the managed user View Model.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody SysUserRegisterDTO sysUserRegisterDTO) {
        if (!checkPasswordLength(sysUserRegisterDTO.getPassword())) {
            throw new InvalidPasswordException();
        }
        SysUserDTO user = sysUserService.registerUser(sysUserRegisterDTO);
        mailService.sendActivationEmail(user);
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }

    /**
     * {@code GET  /activate} : activate the registered user.
     *
     * @param key the activation key.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be activated.
     */
    @GetMapping("/activate")
    public void activateAccount(@RequestParam(value = "key") String key) {
        Optional<SysUser> user = sysUserService.activateRegistration(key);
        if (!user.isPresent()) {
            throw new AccountResourceException("No user was found for this activation key");
        }
    }

    /**
     * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request.
     * @return the login if the user is authenticated.
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * {@code GET  /account} : 获取当前用户信息及权限
     * @author 刘东奇
     * @date 2019/10/23
     * @return 当前登录用户信息及权限
     * @throws RuntimeException
     */
    @ApiOperation(value = "获取当前用户信息及权限")
    @GetMapping("/account")
    public SysUserDTO getAccount() {
        return sysUserService.getUserWithAuthorities()
            .orElseThrow(() -> new AccountResourceException("User could not be found"));
    }

    /**
     * {@code POST  /account} : update the current user information.
     *
     * @param sysUserDTO the current user information.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user login wasn't found.
     */
    @PostMapping("/account")
    public void saveAccount(@Valid @RequestBody SysUserDTO sysUserDTO) {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        Optional<SysUserDTO> existingUser = sysUserService.findOneByEmailIgnoreCase(sysUserDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getLoginCode().equalsIgnoreCase(userLogin))) {
            throw new EmailAlreadyUsedException();
        }
        Optional<SysUserDTO> user = sysUserService.findOneByLoginCode(userLogin);
        if (!user.isPresent()) {
            throw new AccountResourceException("User could not be found");
        }
        sysUserService.updateUser(sysUserDTO);
    }

    /**
     * {@code POST  /account/change-password} : changes the current user's password.
     *
     * @param passwordChangeDto current and new password.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the new password is incorrect.
     */
    @PostMapping(path = "/account/change-password")
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        sysUserService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    /**
     * {@code POST   /account/reset-password/init} : Send an email to reset the password of the user.
     *
     * @param mail the mail of the user.
     * @throws EmailNotFoundException {@code 400 (Bad Request)} if the email address is not registered.
     */
    @PostMapping(path = "/account/reset-password/init")
    public void requestPasswordReset(@RequestBody String mail) {
        sysUserService.requestPasswordReset(mail);
    }

    /**
     * {@code POST   /account/reset-password/finish} : Finish to reset the password of the user.
     *
     * @param keyAndPassword the generated key and the new password.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the password could not be reset.
     */
    @PostMapping(path = "/account/reset-password/finish")
    public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        Optional<SysUser> user =
            sysUserService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

        if (!user.isPresent()) {
            throw new AccountResourceException("No user was found for this reset key");
        }
    }
}
