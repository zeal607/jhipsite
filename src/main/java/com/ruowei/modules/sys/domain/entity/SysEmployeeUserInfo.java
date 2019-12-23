package com.ruowei.modules.sys.domain.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;


/**
 * 员工的用户信息
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sys_user表主键
     */
    @Id
    private Long id;

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
     * 电子邮箱
     */
    @Email
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
     * 用户权重，用于排序（降序）
     */
    @Column(name = "user_sort")
    private Integer userSort;

    /**
     * 用户的员工信息
     */
    @OneToOne
    @JoinColumn(name="ref_code")
    private SysEmployeeDetail employeeDetail;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getLoginCode() {
        return loginCode;
    }

    public SysEmployeeUserInfo loginCode(String loginCode) {
        this.loginCode = loginCode;
        return this;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getUserName() {
        return userName;
    }

    public SysEmployeeUserInfo userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public SysEmployeeUserInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public SysEmployeeUserInfo mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public SysEmployeeUserInfo phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserSort() {
        return userSort;
    }

    public SysEmployeeUserInfo userSort(Integer userSort) {
        this.userSort = userSort;
        return this;
    }

    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SysEmployeeDetail getEmployeeDetail() {
        return employeeDetail;
    }

    public void setEmployeeDetail(SysEmployeeDetail employeeDetail) {
        this.employeeDetail = employeeDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeUserInfo that = (SysEmployeeUserInfo) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(loginCode, that.loginCode) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(email, that.email) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(userSort, that.userSort) &&
            Objects.equals(employeeDetail, that.employeeDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginCode, userName, email, mobile, phone, userSort, employeeDetail);
    }

    @Override
    public String toString() {
        return "SysEmployeeUserInfo{" +
            "id=" + id +
            ", loginCode='" + loginCode + '\'' +
            ", userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", phone='" + phone + '\'' +
            ", userSort=" + userSort +
            ", employeeDetail=" + employeeDetail +
            '}';
    }
}
