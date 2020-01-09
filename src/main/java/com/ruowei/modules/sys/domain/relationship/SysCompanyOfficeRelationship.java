package com.ruowei.modules.sys.domain.relationship;

import com.ruowei.common.entity.AbstractPrimaryKeyAutoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * 公司机构关系表
 * @author JeeSite
 */
@ApiModel(description = "公司机构关系表 @author JeeSite")
@Entity
@Table(name = "sys_company_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysCompanyOfficeRelationship  extends AbstractPrimaryKeyAutoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 公司ID
     */
    @ApiModelProperty(value = "公司ID")
    @Column(name = "sys_company_id",length = 32)
    private String sysCompanyId;

    /**
     * 机构ID
     */
    @ApiModelProperty(value = "机构ID")
    @Column(name = "sys_office_id",length = 32)
    private String sysOfficeId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


    public String getSysCompanyId() {
        return sysCompanyId;
    }

    public SysCompanyOfficeRelationship sysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
        return this;
    }

    public void setSysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public SysCompanyOfficeRelationship sysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
        return this;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysCompanyOfficeRelationship that = (SysCompanyOfficeRelationship) o;
        return Objects.equals(sysCompanyId, that.sysCompanyId) &&
            Objects.equals(sysOfficeId, that.sysOfficeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysCompanyId, sysOfficeId);
    }

    @Override
    public String toString() {
        return "SysCompanyOfficeRelationship{" +
            "sysCompanyId='" + sysCompanyId + '\'' +
            ", sysOfficeId='" + sysOfficeId + '\'' +
            '}';
    }
}
