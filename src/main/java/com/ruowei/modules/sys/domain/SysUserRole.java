package com.ruowei.modules.sys.domain;
import com.ruowei.common.entity.BaseEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * 用户与角色关联表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_user_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysUserRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户外键
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_user_id", length = 100, nullable = false)
    private String sysUserId;

    /**
     * 角色外键
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_role_id", length = 100, nullable = false)
    private String sysRoleId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getSysUserId() {
        return sysUserId;
    }

    public SysUserRole sysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
        return this;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public SysUserRole sysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
        return this;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserRole)) {
            return false;
        }
        return id != null && id.equals(((SysUserRole) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
            "id=" + getId() +
            ", sysUserId='" + getSysUserId() + "'" +
            ", sysRoleId='" + getSysRoleId() + "'" +
            "}";
    }
}
