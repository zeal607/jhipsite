package com.ruowei.modules.sys.domain.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;


/**
 * 员工的所属公司信息
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeCompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sys_company表主键
     */
    @Id
    private Long id;

    /**
     * 公司编码
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "company_code", length = 100, nullable = false, unique = true)
    private String companyCode;

    /**
     * 公司代码
     */
    @Size(max = 100)
    @Column(name = "view_code", length = 100)
    private String viewCode;

    /**
     * 公司名称
     */
    @NotNull
    @Size(max = 200)
    @Column(name = "company_name", length = 200, nullable = false)
    private String companyName;

    /**
     * 公司全称
     */
    @NotNull
    @Size(max = 200)
    @Column(name = "full_name", length = 200, nullable = false, unique = true)
    private String fullName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getCompanyCode() {
        return companyCode;
    }

    public SysEmployeeCompanyInfo companyCode(String companyCode) {
        this.companyCode = companyCode;
        return this;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getViewCode() {
        return viewCode;
    }

    public SysEmployeeCompanyInfo viewCode(String viewCode) {
        this.viewCode = viewCode;
        return this;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public SysEmployeeCompanyInfo companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFullName() {
        return fullName;
    }

    public SysEmployeeCompanyInfo fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeCompanyInfo that = (SysEmployeeCompanyInfo) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(companyCode, that.companyCode) &&
            Objects.equals(viewCode, that.viewCode) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyCode, viewCode, companyName, fullName);
    }
}
