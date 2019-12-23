package com.ruowei.modules.sys.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * 员工信息
 * sys_employee作为主表、sys_user作为从表，通过sys_user.ref_code进行关联
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeDetail extends PrimaryKeyAutoIncrementEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 员工编码
     */
    @Size(max = 100)
    @Column(name = "emp_code", length = 100, nullable = false, unique = true)
    private String empCode;

    /**
     * 员工姓名
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "emp_name", length = 100, nullable = false)
    private String empName;

    /**
     * 英文名
     */
    @Size(max = 1000)
    @Column(name = "emp_name_en", length = 1000)
    private String empNameEn;

    /**
     * 备注信息
     * sys_user、sys_employee都有备注信息，这里默认取sys_employee的
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    @Column(name = "remarks", length = 500)
    private String remarks;

    /**
     * 用户
     */
    @OneToOne(mappedBy = "employeeDetail")
    @JsonIgnoreProperties("employeeDetail")
    private SysEmployeeUserInfo userInfo;

    /**
     * 机构
     */
    @ManyToOne
    @JoinColumns(
        {
            @JoinColumn(name="sys_office_id",referencedColumnName ="id") ,
            @JoinColumn(name="office_name",referencedColumnName ="office_name")
        }
    )
    private SysEmployeeOfficeInfo officeInfo;

    /**
     * 公司
     */
    @ManyToOne
    @JoinColumns(
        {
            @JoinColumn(name="sys_company_id",referencedColumnName ="id") ,
            @JoinColumn(name="company_name",referencedColumnName ="company_name")
        }
    )
    private SysEmployeeCompanyInfo companyInfo;

    /**
     * 所在岗位
     */
    @ManyToMany(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="sys_employee_post",joinColumns={@JoinColumn(name="sys_employee_id", referencedColumnName="id")}
        ,inverseJoinColumns={@JoinColumn(name="sys_post_id", referencedColumnName="id")})
    private List<SysEmployeePostInfo> postInfoList;

    /**
     *  附属机构及岗位
     */
    @OneToMany(fetch=FetchType.EAGER,mappedBy="employeeDetail")
    @JsonIgnoreProperties("employeeDetail")
    private List<SysEmployeeOfficePostInfo> officePostInfoList;


    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getEmpNameEn() {
        return empNameEn;
    }

    public void setEmpNameEn(String empNameEn) {
        this.empNameEn = empNameEn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public SysEmployeeUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(SysEmployeeUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public SysEmployeeOfficeInfo getOfficeInfo() {
        return officeInfo;
    }

    public void setOfficeInfo(SysEmployeeOfficeInfo officeInfo) {
        this.officeInfo = officeInfo;
    }

    public SysEmployeeCompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(SysEmployeeCompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public List<SysEmployeePostInfo> getPostInfoList() {
        return postInfoList;
    }

    public void setPostInfoList(List<SysEmployeePostInfo> postInfoList) {
        this.postInfoList = postInfoList;
    }

    public List<SysEmployeeOfficePostInfo> getOfficePostInfoList() {
        return officePostInfoList;
    }

    public void setOfficePostInfoList(List<SysEmployeeOfficePostInfo> officePostInfoList) {
        this.officePostInfoList = officePostInfoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeDetail that = (SysEmployeeDetail) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(empNameEn, that.empNameEn) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(userInfo, that.userInfo) &&
            Objects.equals(officeInfo, that.officeInfo) &&
            Objects.equals(companyInfo, that.companyInfo) &&
            Objects.equals(postInfoList, that.postInfoList) &&
            Objects.equals(officePostInfoList, that.officePostInfoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empCode, empName, empNameEn, remarks, userInfo, officeInfo, companyInfo, postInfoList, officePostInfoList);
    }

    @Override
    public String toString() {
        return "SysEmployeeDetail{" +
            "id=" + id +
            ", empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", empNameEn='" + empNameEn + '\'' +
            ", remarks='" + remarks + '\'' +
            ", userInfo=" + userInfo +
            ", officeInfo=" + officeInfo +
            ", companyInfo=" + companyInfo +
            ", postInfoList=" + postInfoList +
            ", officePostInfoList=" + officePostInfoList +
            '}';
    }
}
