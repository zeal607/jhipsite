package com.ruowei.modules.sys.pojo.user;

import com.ruowei.config.Constants;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.service.util.SysUserUtil;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

/**
 * 用户注册的DTO
 * @author 刘东奇
 * @date 2019/10/22
 */
public class SysUserRegisterDTO {

    /**
     * 用户编号，系统自动生成
     */
    private String userCode= SysUserUtil.generateUserCode();
    /**
     * 对应SysUser LoginCode字段
     */
    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    /**
     * 登录密码
     */
    @NotNull
    @Size(max = 100)
    private String password;

    /**
     * 电子邮箱
     */
    @Size(max = 300)
    @Email
    private String email;

    /**
     * 是否激活
     */
    private Boolean activated = false;

    /**
     * 用户状态
     */
    @Enumerated(EnumType.STRING)
    private UserStatusType status = UserStatusType.FREEZE;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public UserStatusType getStatus() {
        return status;
    }

    public void setStatus(UserStatusType status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
