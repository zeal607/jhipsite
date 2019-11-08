package com.ruowei.modules.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruowei.common.json.LongJsonDeserializer;
import com.ruowei.common.json.LongJsonSerializer;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.domain.table.SysEmployeeOffice;
import com.ruowei.modules.sys.domain.table.SysPost;
import com.ruowei.modules.sys.domain.table.SysRole;
import io.swagger.annotations.ApiModelProperty;
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
import java.util.Objects;


/**
 * 用户员工实体-详情模型
 * sys_user、sys_employee两个表主键相同，组成主从表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_user")
@SecondaryTable(name = "sys_employee", pkJoinColumns = {
    @PrimaryKeyJoinColumn(name = "id",referencedColumnName = "id")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysUserEmployeeDetailVM implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @Id
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    /**
     * 归属
     * 机构ID
     */
    @Size(max = 100)
    @ApiModelProperty(value = "机构ID")
    @Column(name = "sys_office_id", length = 100, table="sys_employee")
    private String sysOfficeId;

    /**
     * 归属
     * 机构名称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "机构名称")
    @Column(name = "office_name", length = 100, table="sys_employee")
    private String officeName;

    /**
     * 归属
     * 公司ID
     */
    @Size(max = 200)
    @ApiModelProperty(value = "公司ID")
    @Column(name = "sys_company_id", length = 200, table="sys_employee")
    private String sysCompanyId;

    /**
     * 归属
     * 公司名称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "公司名称")
    @Column(name = "company_name", length = 100, table="sys_employee")
    private String companyName;

    /**
     * 登录账号
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "登录账号")
    @Column(name = "login_code", length = 100, nullable = false, unique = true)
    private String loginCode;

    /**
     * 用户昵称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "用户昵称")
    @Column(name = "user_name", length = 100)
    private String userName;

    /**
     * 电子邮箱
     */
    @Size(max = 300)
    @ApiModelProperty(value = "电子邮箱")
    @Column(name = "email", length = 300, unique = true)
    private String email;

    /**
     * 手机号码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "手机号码")
    @Column(name = "mobile", length = 100, unique = true)
    private String mobile;

    /**
     * 办公电话
     */
    @Size(max = 100)
    @ApiModelProperty(value = "办公电话")
    @Column(name = "phone", length = 100)
    private String phone;

    /**
     * 用户权重，用于排序（降序）
     */
    @ApiModelProperty(value = "用户权重")
    @Column(name = "user_sort")
    private Integer userSort;

    /**
     * 员工编码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "员工编码")
    @Column(name = "emp_code", length = 100, nullable = false, unique = true, table="sys_employee")
    private String empCode;

    /**
     * 员工姓名
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "员工姓名")
    @Column(name = "emp_name", length = 100, nullable = false, table="sys_employee")
    private String empName;

    /**
     * 所在岗位
     */
    @ApiModelProperty(value = "所在岗位")
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="sys_employee_post",joinColumns={@JoinColumn(name="sys_employee_id", referencedColumnName="id")}
        ,inverseJoinColumns={@JoinColumn(name="sys_post_id", referencedColumnName="id")})
    private List<SysPost> sysPostList;

    /**
     * 英文名
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "英文名")
    @Column(name = "emp_name_en", length = 1000, table="sys_employee")
    private String empNameEn;

    /**
     * 附属机构及岗位
     */
    @ApiModelProperty(value = "附属机构及岗位")
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name="sys_employee_id", referencedColumnName="id")
    private List<SysEmployeeOffice> sysEmployeeOfficeList;

    /**
     * 备注信息
     * sys_user、sys_employee都有备注信息，这里默认取sys_user的
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    @Column(name = "remarks", length = 500)
    private String remarks;

    /**
     * 分配角色
     */
    @ApiModelProperty(value = "分配角色")
    @ManyToMany
    @JoinTable(name="sys_user_role",joinColumns={@JoinColumn(name="sys_user_id",referencedColumnName="id")}
        ,inverseJoinColumns={@JoinColumn(name="sys_role_id",referencedColumnName="id")})
    private List<SysRole> sysRoleList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserSort() {
        return userSort;
    }

    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public List<SysPost> getSysPostList() {
        return sysPostList;
    }

    public void setSysPostList(List<SysPost> sysPostList) {
        this.sysPostList = sysPostList;
    }

    public String getEmpNameEn() {
        return empNameEn;
    }

    public void setEmpNameEn(String empNameEn) {
        this.empNameEn = empNameEn;
    }

    public List<SysEmployeeOffice> getSysEmployeeOfficeList() {
        return sysEmployeeOfficeList;
    }

    public void setSysEmployeeOfficeList(List<SysEmployeeOffice> sysEmployeeOfficeList) {
        this.sysEmployeeOfficeList = sysEmployeeOfficeList;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        SysUserEmployeeDetailVM that = (SysUserEmployeeDetailVM) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(sysOfficeId, that.sysOfficeId) &&
            Objects.equals(officeName, that.officeName) &&
            Objects.equals(sysCompanyId, that.sysCompanyId) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(loginCode, that.loginCode) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(email, that.email) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(userSort, that.userSort) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(sysPostList, that.sysPostList) &&
            Objects.equals(empNameEn, that.empNameEn) &&
            Objects.equals(sysEmployeeOfficeList, that.sysEmployeeOfficeList) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(sysRoleList, that.sysRoleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sysOfficeId, officeName, sysCompanyId, companyName, loginCode, userName, email, mobile, phone, userSort, empCode, empName, sysPostList, empNameEn, sysEmployeeOfficeList, remarks, sysRoleList);
    }

    @Override
    public String toString() {
        return "SysUserEmployeeDetailVM{" +
            "id=" + id +
            ", sysOfficeId='" + sysOfficeId + '\'' +
            ", officeName='" + officeName + '\'' +
            ", sysCompanyId='" + sysCompanyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", loginCode='" + loginCode + '\'' +
            ", userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", phone='" + phone + '\'' +
            ", userSort=" + userSort +
            ", empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", sysPostList=" + sysPostList +
            ", empNameEn='" + empNameEn + '\'' +
            ", sysEmployeeOfficeList=" + sysEmployeeOfficeList +
            ", remarks='" + remarks + '\'' +
            ", sysRoleList=" + sysRoleList +
            '}';
    }
}
