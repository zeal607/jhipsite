package com.ruowei.modules.sys.domain.table;
import com.ruowei.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 员工表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployee extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编码
     */
    @NotNull
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
     * 机构ID
     */
    @Size(max = 100)
    @Column(name = "sys_office_id", length = 100)
    private String sysOfficeId;

    /**
     * 机构名称
     */
    @Size(max = 100)
    @Column(name = "office_name", length = 100)
    private String officeName;

    /**
     * 公司ID
     */
    @Size(max = 200)
    @Column(name = "sys_company_id", length = 200)
    private String sysCompanyId;

    /**
     * 公司名称
     */
    @Size(max = 100)
    @Column(name = "company_name", length = 100)
    private String companyName;

    /**
     * 状态
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

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
     * 附属机构及岗位
     */
    @ApiModelProperty(value = "附属机构及岗位")
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name="sys_employee_id", referencedColumnName="id")
    private List<SysEmployeeOffice> sysEmployeeOfficeList;


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getEmpCode() {
        return empCode;
    }

    public SysEmployee empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public SysEmployee empName(String empName) {
        this.empName = empName;
        return this;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNameEn() {
        return empNameEn;
    }

    public SysEmployee empNameEn(String empNameEn) {
        this.empNameEn = empNameEn;
        return this;
    }

    public void setEmpNameEn(String empNameEn) {
        this.empNameEn = empNameEn;
    }

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public SysEmployee sysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
        return this;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public SysEmployee officeName(String officeName) {
        this.officeName = officeName;
        return this;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getSysCompanyId() {
        return sysCompanyId;
    }

    public SysEmployee sysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
        return this;
    }

    public void setSysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public SysEmployee companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public EmployeeStatusType getStatus() {
        return status;
    }

    public SysEmployee status(EmployeeStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(EmployeeStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public SysEmployee remarks(String remarks) {
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

    public List<SysPost> getSysPostList() {
        return sysPostList;
    }

    public void setSysPostList(List<SysPost> sysPostList) {
        this.sysPostList = sysPostList;
    }

    public List<SysEmployeeOffice> getSysEmployeeOfficeList() {
        return sysEmployeeOfficeList;
    }

    public void setSysEmployeeOfficeList(List<SysEmployeeOffice> sysEmployeeOfficeList) {
        this.sysEmployeeOfficeList = sysEmployeeOfficeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysEmployee that = (SysEmployee) o;
        return Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(empNameEn, that.empNameEn) &&
            Objects.equals(sysOfficeId, that.sysOfficeId) &&
            Objects.equals(officeName, that.officeName) &&
            Objects.equals(sysCompanyId, that.sysCompanyId) &&
            Objects.equals(companyName, that.companyName) &&
            status == that.status &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(sysPostList, that.sysPostList) &&
            Objects.equals(sysEmployeeOfficeList, that.sysEmployeeOfficeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empCode, empName, empNameEn, sysOfficeId, officeName, sysCompanyId, companyName, status, remarks, sysPostList, sysEmployeeOfficeList);
    }

    @Override
    public String toString() {
        return "SysEmployee{" +
            "empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", empNameEn='" + empNameEn + '\'' +
            ", sysOfficeId='" + sysOfficeId + '\'' +
            ", officeName='" + officeName + '\'' +
            ", sysCompanyId='" + sysCompanyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", status=" + status +
            ", remarks='" + remarks + '\'' +
            ", sysPostList=" + sysPostList +
            ", sysEmployeeOfficeList=" + sysEmployeeOfficeList +
            '}';
    }
}
