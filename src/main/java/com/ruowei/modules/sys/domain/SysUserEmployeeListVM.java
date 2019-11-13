package com.ruowei.modules.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruowei.common.json.LongJsonDeserializer;
import com.ruowei.common.json.LongJsonSerializer;
import com.ruowei.common.json.SysPostListJsonSerializer;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.domain.table.SysPost;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;


/**
 * 用户员工实体-列表模型
 * sys_user、sys_employee两个表主键相同，组成主从表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee")
@SecondaryTable(name = "sys_user", pkJoinColumns = {
    @PrimaryKeyJoinColumn(name = "ref_code",referencedColumnName = "id")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysUserEmployeeListVM implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * sys_user
     */

    /**
     * ID
     */
    @Id
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    /**
     * 登录账号
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "login_code", length = 100, nullable = false, unique = true, table="sys_user")
    private String loginCode;

    /**
     * 用户昵称
     */
    @Size(max = 100)
    @Column(name = "user_name", length = 100, table="sys_user")
    private String userName;

    /**
     * 电子邮箱
     */
    @Size(max = 300)
    @Column(name = "email", length = 300, unique = true, table="sys_user")
    private String email;

    /**
     * 手机号码
     */
    @Size(max = 100)
    @Column(name = "mobile", length = 100, unique = true, table="sys_user")
    private String mobile;

    /**
     * 办公电话
     */
    @Size(max = 100)
    @Column(name = "phone", length = 100, table="sys_user")
    private String phone;

    /**
     * 用户类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", table="sys_user")
    private UserType userType = UserType.EMPLOYEE;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "last_modified_date", table="sys_user")
    @JsonIgnore
    private Instant lastModifiedDate;

    /**
     * 用户状态
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, table="sys_user")
    private UserStatusType status;

    /**
     * sys_employee
     */

    /**
     * 员工姓名
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "emp_name", length = 100, nullable = false)
    private String empName;

    /**
     * 机构ID
     */
    @Size(max = 100)
    @Column(name = "sys_office_id", length = 100)
    private String sysOfficeId;

    /**
     * 公司ID
     */
    @Size(max = 200)
    @Column(name = "sys_company_id", length = 200)
    private String sysCompanyId;

    //实体间的关系
    /**
     * 岗位
     */
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="sys_employee_post",joinColumns={@JoinColumn(name="sys_employee_id",referencedColumnName="id")}
        ,inverseJoinColumns={@JoinColumn(name="sys_post_id",referencedColumnName="id")})
    @JsonSerialize(using = SysPostListJsonSerializer.class)
    private List<SysPost> sysPostList;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


    public String getLoginCode() {
        return loginCode;
    }

    public SysUserEmployeeListVM loginCode(String loginCode) {
        this.loginCode = loginCode;
        return this;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getUserName() {
        return userName;
    }

    public SysUserEmployeeListVM userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }

    public SysUserEmployeeListVM email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public SysUserEmployeeListVM mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public SysUserEmployeeListVM phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public SysUserEmployeeListVM userType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserEmployeeListVM)) {
            return false;
        }
        return id != null && id.equals(((SysUserEmployeeListVM) o).id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public UserStatusType getStatus() {
        return status;
    }

    public void setStatus(UserStatusType status) {
        this.status = status;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public List<SysPost> getSysPostList() {
        return sysPostList;
    }

    public void setSysPostList(List<SysPost> sysPostList) {
        this.sysPostList = sysPostList;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "SysUserEmploeeLVM{" +
            "id=" + id +
            ", loginCode='" + loginCode + '\'' +
            ", userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", phone='" + phone + '\'' +
            ", userType=" + userType +
            ", lastModifiedDate=" + lastModifiedDate +
            ", status=" + status +
            ", empName='" + empName + '\'' +
            '}';
    }
}
