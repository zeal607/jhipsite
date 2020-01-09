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
 * 用户与角色关系表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_user_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysUserRoleRelationship extends AbstractPrimaryKeyAutoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @NotNull
    @Column(name = "sys_user_id",length = 32,nullable = false)
    private String sysUserId;

    /**
     * 角色ID
     */
    @NotNull
    @Column(name = "sys_role_id",length = 32,nullable = false)
    private String sysRoleId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getSysUserId() {
        return sysUserId;
    }

    public SysUserRoleRelationship sysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
        return this;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public SysUserRoleRelationship sysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
        return this;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserRoleRelationship that = (SysUserRoleRelationship) o;
        return Objects.equals(sysUserId, that.sysUserId) &&
            Objects.equals(sysRoleId, that.sysRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysUserId, sysRoleId);
    }

    @Override
    public String toString() {
        return "SysUserRoleRelationship{" +
            "sysUserId='" + sysUserId + '\'' +
            ", sysRoleId='" + sysRoleId + '\'' +
            '}';
    }
}
