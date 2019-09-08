package com.ruowei.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.ruowei.domain.enumeration.GenderType;

import com.ruowei.domain.enumeration.UserType;

import com.ruowei.domain.enumeration.UserStatusType;

/**
 * 用户表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUser)) {
            return false;
        }
        return id != null && id.equals(((SysUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysUser{" +
            "id=" + getId() +
            ", userCode='" + getUserCode() + "'" +
            ", loginCode='" + getLoginCode() + "'" +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", phone='" + getPhone() + "'" +
            ", sex='" + getSex() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", sign='" + getSign() + "'" +
            ", wxOpenid='" + getWxOpenid() + "'" +
            ", mobileImei='" + getMobileImei() + "'" +
            ", userType='" + getUserType() + "'" +
            ", refCode='" + getRefCode() + "'" +
            ", refName='" + getRefName() + "'" +
            ", lastLoginIp='" + getLastLoginIp() + "'" +
            ", lastLoginDate='" + getLastLoginDate() + "'" +
            ", freezeDate='" + getFreezeDate() + "'" +
            ", freezeCause='" + getFreezeCause() + "'" +
            ", userSort=" + getUserSort() +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
