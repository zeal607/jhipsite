package com.ruowei.modules.sys.domain.table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruowei.common.entity.AbstractAuditingEntity;
import com.ruowei.modules.sys.domain.enumeration.GenderType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * 用户表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysUser extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编码 ，该字段不作为表的关联外键，仅供展示
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "user_code", length = 100, nullable = false, unique = true)
    private String userCode;

    /**
     * 登录账号
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "login_code", length = 100, nullable = false, unique = true)
    private String loginCode;

    /**
     * 用户昵称
     */
    @Size(max = 100)
    @Column(name = "user_name", length = 100)
    private String userName;

    /**
     * 登录密码
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    /**
     * 电子邮箱
     */
    @Size(max = 300)
    @Column(name = "email", length = 300, unique = true)
    private String email;

    /**
     * 手机号码
     */
    @Size(max = 100)
    @Column(name = "mobile", length = 100, unique = true)
    private String mobile;

    /**
     * 办公电话
     */
    @Size(max = 100)
    @Column(name = "phone", length = 100)
    private String phone;

    /**
     * 用户性别
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private GenderType sex;

    /**
     * 头像相对路径
     */
    @Size(max = 1000)
    @Column(name = "avatar", length = 1000)
    private String avatar;

    /**
     * 个性签名
     */
    @Size(max = 200)
    @Column(name = "sign", length = 200)
    private String sign;

    /**
     * 绑定的微信号
     */
    @Size(max = 1000)
    @Column(name = "wx_openid", length = 1000, unique = true)
    private String wxOpenid;

    /**
     * 绑定的手机MID
     */
    @Size(max = 100)
    @Column(name = "mobile_imei", length = 100, unique = true)
    private String mobileImei;

    /**
     * 用户类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    /**
     * 用户类型引用编号（雇员表或会员表的ID）
     */
    @Size(max = 100)
    @Column(name = "ref_code", length = 100)
    private String refCode;

    /**
     * 用户类型引用姓名
     */
    @Size(max = 100)
    @Column(name = "ref_name", length = 100)
    private String refName;

    /**
     * 最后登录IP
     */
    @Size(max = 100)
    @Column(name = "last_login_ip", length = 100)
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_date")
    private Instant lastLoginDate;

    /**
     * 冻结时间
     */
    @Column(name = "freeze_date")
    private Instant freezeDate;

    /**
     * 冻结原因
     */
    @Size(max = 200)
    @Column(name = "freeze_cause", length = 200)
    private String freezeCause;

    /**
     * 用户权重，用于排序（降序）
     */
    @Column(name = "user_sort")
    private Integer userSort;

    /**
     * 用户状态
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    /**
     * 是否激活
     */
    @NotNull
    @Column(nullable = false)
    private boolean activated;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
        name = "jhi_user_authority",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    /**
     * 分配角色
     */
    @ApiModelProperty(value = "分配角色")
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="sys_user_role",joinColumns={@JoinColumn(name="sys_user_id",referencedColumnName="id")}
        ,inverseJoinColumns={@JoinColumn(name="sys_role_id",referencedColumnName="id")})
    private List<SysRole> sysRoleList;


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getUserCode() {
        return userCode;
    }

    public SysUser userCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public SysUser loginCode(String loginCode) {
        this.loginCode = loginCode;
        return this;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getUserName() {
        return userName;
    }

    public SysUser userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public SysUser password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public SysUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public SysUser mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public SysUser phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GenderType getSex() {
        return sex;
    }

    public SysUser sex(GenderType sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(GenderType sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public SysUser avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public SysUser sign(String sign) {
        this.sign = sign;
        return this;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public SysUser wxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
        return this;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getMobileImei() {
        return mobileImei;
    }

    public SysUser mobileImei(String mobileImei) {
        this.mobileImei = mobileImei;
        return this;
    }

    public void setMobileImei(String mobileImei) {
        this.mobileImei = mobileImei;
    }

    public UserType getUserType() {
        return userType;
    }

    public SysUser userType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getRefCode() {
        return refCode;
    }

    public SysUser refCode(String refCode) {
        this.refCode = refCode;
        return this;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getRefName() {
        return refName;
    }

    public SysUser refName(String refName) {
        this.refName = refName;
        return this;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public SysUser lastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
        return this;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Instant getLastLoginDate() {
        return lastLoginDate;
    }

    public SysUser lastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public void setLastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Instant getFreezeDate() {
        return freezeDate;
    }

    public SysUser freezeDate(Instant freezeDate) {
        this.freezeDate = freezeDate;
        return this;
    }

    public void setFreezeDate(Instant freezeDate) {
        this.freezeDate = freezeDate;
    }

    public String getFreezeCause() {
        return freezeCause;
    }

    public SysUser freezeCause(String freezeCause) {
        this.freezeCause = freezeCause;
        return this;
    }

    public void setFreezeCause(String freezeCause) {
        this.freezeCause = freezeCause;
    }

    public Integer getUserSort() {
        return userSort;
    }

    public SysUser userSort(Integer userSort) {
        this.userSort = userSort;
        return this;
    }

    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }

    public UserStatusType getStatus() {
        return status;
    }

    public SysUser status(UserStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(UserStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public SysUser remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return activated == sysUser.activated &&
            Objects.equals(userCode, sysUser.userCode) &&
            Objects.equals(loginCode, sysUser.loginCode) &&
            Objects.equals(userName, sysUser.userName) &&
            Objects.equals(password, sysUser.password) &&
            Objects.equals(email, sysUser.email) &&
            Objects.equals(mobile, sysUser.mobile) &&
            Objects.equals(phone, sysUser.phone) &&
            sex == sysUser.sex &&
            Objects.equals(avatar, sysUser.avatar) &&
            Objects.equals(sign, sysUser.sign) &&
            Objects.equals(wxOpenid, sysUser.wxOpenid) &&
            Objects.equals(mobileImei, sysUser.mobileImei) &&
            userType == sysUser.userType &&
            Objects.equals(refCode, sysUser.refCode) &&
            Objects.equals(refName, sysUser.refName) &&
            Objects.equals(lastLoginIp, sysUser.lastLoginIp) &&
            Objects.equals(lastLoginDate, sysUser.lastLoginDate) &&
            Objects.equals(freezeDate, sysUser.freezeDate) &&
            Objects.equals(freezeCause, sysUser.freezeCause) &&
            Objects.equals(userSort, sysUser.userSort) &&
            status == sysUser.status &&
            Objects.equals(remarks, sysUser.remarks) &&
            Objects.equals(activationKey, sysUser.activationKey) &&
            Objects.equals(resetKey, sysUser.resetKey) &&
            Objects.equals(resetDate, sysUser.resetDate) &&
            Objects.equals(authorities, sysUser.authorities) &&
            Objects.equals(sysRoleList, sysUser.sysRoleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCode, loginCode, userName, password, email, mobile, phone, sex, avatar, sign, wxOpenid, mobileImei, userType, refCode, refName, lastLoginIp, lastLoginDate, freezeDate, freezeCause, userSort, status, remarks, activated, activationKey, resetKey, resetDate, authorities, sysRoleList);
    }

    @Override
    public String toString() {
        return "SysUser{" +
            "userCode='" + userCode + '\'' +
            ", loginCode='" + loginCode + '\'' +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", phone='" + phone + '\'' +
            ", sex=" + sex +
            ", avatar='" + avatar + '\'' +
            ", sign='" + sign + '\'' +
            ", wxOpenid='" + wxOpenid + '\'' +
            ", mobileImei='" + mobileImei + '\'' +
            ", userType=" + userType +
            ", refCode='" + refCode + '\'' +
            ", refName='" + refName + '\'' +
            ", lastLoginIp='" + lastLoginIp + '\'' +
            ", lastLoginDate=" + lastLoginDate +
            ", freezeDate=" + freezeDate +
            ", freezeCause='" + freezeCause + '\'' +
            ", userSort=" + userSort +
            ", status=" + status +
            ", remarks='" + remarks + '\'' +
            ", activated=" + activated +
            ", activationKey='" + activationKey + '\'' +
            ", resetKey='" + resetKey + '\'' +
            ", resetDate=" + resetDate +
            ", authorities=" + authorities +
            ", sysRoleList=" + sysRoleList +
            '}';
    }
}
