package com.ruowei.modules.sys.domain;

import com.ruowei.common.entity.BaseEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 公司部门关联表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_company_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysCompanyOffice extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_company_id", length = 100, nullable = false)
    private String sysCompanyId;

    /**
     * 机构ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_office_id", length = 100, nullable = false)
    private String sysOfficeId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getSysCompanyId() {
        return sysCompanyId;
    }

    public SysCompanyOffice sysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
        return this;
    }

    public void setSysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public SysCompanyOffice sysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
        return this;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysCompanyOffice)) {
            return false;
        }
        return id != null && id.equals(((SysCompanyOffice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysCompanyOffice{" +
            "id=" + getId() +
            ", sysCompanyId='" + getSysCompanyId() + "'" +
            ", sysOfficeId='" + getSysOfficeId() + "'" +
            "}";
    }
}
