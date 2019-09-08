package com.ruowei.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * 角色与菜单关联表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_role_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色外键
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_role_id", length = 100, nullable = false)
    private String sysRoleId;

    /**
     * 菜单外键
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_menu_id", length = 100, nullable = false)
    private String sysMenuId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public SysRoleMenu sysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
        return this;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getSysMenuId() {
        return sysMenuId;
    }

    public SysRoleMenu sysMenuId(String sysMenuId) {
        this.sysMenuId = sysMenuId;
        return this;
    }

    public void setSysMenuId(String sysMenuId) {
        this.sysMenuId = sysMenuId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRoleMenu)) {
            return false;
        }
        return id != null && id.equals(((SysRoleMenu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
            "id=" + getId() +
            ", sysRoleId='" + getSysRoleId() + "'" +
            ", sysMenuId='" + getSysMenuId() + "'" +
            "}";
    }
}
