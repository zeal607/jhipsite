package com.ruowei.modules.sys.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;


/**
 * 用户员工实体-列表模型
 * sys_employee作为主表、sys_user作为从表，通过sys_user.ref_code进行关联
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeList extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户
     */
    @OneToOne(mappedBy = "employeeDetail")
    @JsonIgnoreProperties("employeeDetail")
    private SysEmployeeUserInfo userInfo;

    /**
     * 更新时间
     */
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    /**
     * 员工姓名
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "emp_name", length = 100, nullable = false)
    private String empName;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public SysEmployeeUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(SysEmployeeUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeList that = (SysEmployeeList) o;
        return Objects.equals(userInfo, that.userInfo) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(officeInfo, that.officeInfo) &&
            Objects.equals(companyInfo, that.companyInfo) &&
            Objects.equals(postInfoList, that.postInfoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInfo, lastModifiedDate, empName, officeInfo, companyInfo, postInfoList);
    }

    @Override
    public String toString() {
        return "SysEmployeeList{" +
            "userInfo=" + userInfo +
            ", lastModifiedDate=" + lastModifiedDate +
            ", empName='" + empName + '\'' +
            ", officeInfo=" + officeInfo +
            ", companyInfo=" + companyInfo +
            ", postInfoList=" + postInfoList +
            ", id=" + id +
            '}';
    }
}
