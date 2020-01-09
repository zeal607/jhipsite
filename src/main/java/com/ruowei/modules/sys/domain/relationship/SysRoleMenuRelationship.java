package com.ruowei.modules.sys.domain.relationship;

import com.ruowei.common.entity.AbstractPrimaryKeyAutoEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * 角色与菜单关系表
 * @author JeeSite
 */
@Entity
@Table(name = "sys_role_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysRoleMenuRelationship extends AbstractPrimaryKeyAutoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色外键
     */
    @ApiModelProperty(value = "角色外键")
    @Column(name = "sys_role_id",length = 32)
    private String sysRoleId;

    /**
     * 菜单外键
     */
    @ApiModelProperty(value = "菜单外键")
    @Column(name = "sys_menu_id",length = 32)
    private String sysMenuId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getSysRoleId() {
        return sysRoleId;
    }

    public SysRoleMenuRelationship sysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
        return this;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getSysMenuId() {
        return sysMenuId;
    }

    public SysRoleMenuRelationship sysMenuId(String sysMenuId) {
        this.sysMenuId = sysMenuId;
        return this;
    }

    public void setSysMenuId(String sysMenuId) {
        this.sysMenuId = sysMenuId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRoleMenuRelationship that = (SysRoleMenuRelationship) o;
        return Objects.equals(sysRoleId, that.sysRoleId) &&
            Objects.equals(sysMenuId, that.sysMenuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysRoleId, sysMenuId);
    }

    @Override
    public String toString() {
        return "SysRoleMenuRelationship{" +
            "sysRoleId='" + sysRoleId + '\'' +
            ", sysMenuId='" + sysMenuId + '\'' +
            '}';
    }
}
