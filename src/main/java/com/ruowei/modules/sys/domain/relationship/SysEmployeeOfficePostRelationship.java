package com.ruowei.modules.sys.domain.relationship;

import com.ruowei.common.entity.AbstractPrimaryKeyAutoEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * 员工附属机构关系表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeOfficePostRelationship extends AbstractPrimaryKeyAutoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    @NotNull
    @Column(name = "sys_employee_id", length = 32, nullable = false)
    private String sysEmployeeId;

    /**
     * 机构ID
     */
    @NotNull
    @Column(name = "sys_office_id", length = 32, nullable = false)
    private String sysOfficeId;

    /**
     * 岗位ID
     */
    @NotNull
    @Column(name = "sys_post_id", length = 32, nullable = false)
    private String sysPostId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getSysEmployeeId() {
        return sysEmployeeId;
    }

    public SysEmployeeOfficePostRelationship sysEmployeeId(String sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
        return this;
    }

    public void setSysEmployeeId(String sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
    }

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public SysEmployeeOfficePostRelationship sysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
        return this;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getSysPostId() {
        return sysPostId;
    }

    public SysEmployeeOfficePostRelationship sysPostId(String sysPostId) {
        this.sysPostId = sysPostId;
        return this;
    }

    public void setSysPostId(String sysPostId) {
        this.sysPostId = sysPostId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeOfficePostRelationship that = (SysEmployeeOfficePostRelationship) o;
        return Objects.equals(sysEmployeeId, that.sysEmployeeId) &&
            Objects.equals(sysOfficeId, that.sysOfficeId) &&
            Objects.equals(sysPostId, that.sysPostId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysEmployeeId, sysOfficeId, sysPostId);
    }

    @Override
    public String toString() {
        return "SysEmployeeOfficePostRelationship{" +
            "sysEmployeeId='" + sysEmployeeId + '\'' +
            ", sysOfficeId='" + sysOfficeId + '\'' +
            ", sysPostId='" + sysPostId + '\'' +
            '}';
    }
}
