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
 * 员工的所属机构信息
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeOfficeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sys_office表主键
     */
    @Id
    private Long id;

    /**
     * 机构编码
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "office_code", length = 100, nullable = false, unique = true)
    private String officeCode;

    /**
     * 机构代码
     */
    @Size(max = 100)
    @Column(name = "view_code", length = 100)
    private String viewCode;

    /**
     * 机构名称
     */
    @NotNull
    @Size(max = 200)
    @Column(name = "office_name", length = 200, nullable = false)
    private String officeName;

    /**
     * 机构全称
     */
    @NotNull
    @Size(max = 200)
    @Column(name = "full_name", length = 200, nullable = false, unique = true)
    private String fullName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getOfficeCode() {
        return officeCode;
    }

    public SysEmployeeOfficeInfo officeCode(String officeCode) {
        this.officeCode = officeCode;
        return this;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getViewCode() {
        return viewCode;
    }

    public SysEmployeeOfficeInfo viewCode(String viewCode) {
        this.viewCode = viewCode;
        return this;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public SysEmployeeOfficeInfo officeName(String officeName) {
        this.officeName = officeName;
        return this;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getFullName() {
        return fullName;
    }

    public SysEmployeeOfficeInfo fullName(String fullName) {
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
        SysEmployeeOfficeInfo that = (SysEmployeeOfficeInfo) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(officeCode, that.officeCode) &&
            Objects.equals(viewCode, that.viewCode) &&
            Objects.equals(officeName, that.officeName) &&
            Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, officeCode, viewCode, officeName, fullName);
    }
}
