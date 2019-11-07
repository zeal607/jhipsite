package com.ruowei.modules.sys.domain.table;

import com.ruowei.common.entity.BaseEntity;
import com.ruowei.modules.sys.domain.enumeration.MenuStatusType;
import com.ruowei.modules.sys.domain.enumeration.MenuType;
import com.ruowei.modules.sys.domain.enumeration.SysType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 菜单表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单编码
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "menu_code", length = 100, nullable = false, unique = true)
    private String menuCode;

    /**
     * 父级编号
     */
    @Size(max = 100)
    @Column(name = "parent_code", length = 100)
    private String parentCode;

    /**
     * 所有父级编号
     */
    @Size(max = 1000)
    @Column(name = "parent_codes", length = 1000)
    private String parentCodes;

    /**
     * 本级排序号（升序）
     */
    @Column(name = "tree_sort")
    private Integer treeSort;

    /**
     * 所有级别排序号
     */
    @Column(name = "tree_sorts")
    private Integer treeSorts;

    /**
     * 是否最末级
     */
    @NotNull
    @Column(name = "tree_leaf", nullable = false)
    private Boolean treeLeaf;

    /**
     * 层次级别
     */
    @NotNull
    @Column(name = "tree_level", nullable = false)
    private Integer treeLevel;

    /**
     * 全节点名
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "tree_names", length = 1000, nullable = false)
    private String treeNames;

    /**
     * 菜单名称
     */
    @Size(max = 100)
    @Column(name = "menu_name", length = 100)
    private String menuName;

    /**
     * 菜单类型
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "menu_type", nullable = false)
    private MenuType menuType;

    /**
     * 链接
     */
    @Size(max = 1000)
    @Column(name = "menu_href", length = 1000)
    private String menuHref;

    /**
     * 图标
     */
    @Size(max = 100)
    @Column(name = "menu_icon", length = 100)
    private String menuIcon;

    /**
     * 菜单标题
     */
    @Size(max = 100)
    @Column(name = "menu_title", length = 100)
    private String menuTitle;

    /**
     * 权限标识
     */
    @Size(max = 1000)
    @Column(name = "permission", length = 1000)
    private String permission;

    /**
     * 菜单排序（升序）
     */
    @Column(name = "menu_sort")
    private Integer menuSort;

    /**
     * 是否显示
     */
    @NotNull
    @Column(name = "is_show", nullable = false)
    private Boolean isShow;

    /**
     * 归属系统（default:主导航菜单、mobileApp:APP菜单）
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sys_code", nullable = false)
    private SysType sysCode;

    /**
     * 状态
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MenuStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getMenuCode() {
        return menuCode;
    }

    public SysMenu menuCode(String menuCode) {
        this.menuCode = menuCode;
        return this;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public SysMenu parentCode(String parentCode) {
        this.parentCode = parentCode;
        return this;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCodes() {
        return parentCodes;
    }

    public SysMenu parentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
        return this;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
    }

    public Integer getTreeSort() {
        return treeSort;
    }

    public SysMenu treeSort(Integer treeSort) {
        this.treeSort = treeSort;
        return this;
    }

    public void setTreeSort(Integer treeSort) {
        this.treeSort = treeSort;
    }

    public Integer getTreeSorts() {
        return treeSorts;
    }

    public SysMenu treeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
        return this;
    }

    public void setTreeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
    }

    public Boolean isTreeLeaf() {
        return treeLeaf;
    }

    public SysMenu treeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
        return this;
    }

    public void setTreeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public SysMenu treeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
        return this;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeNames() {
        return treeNames;
    }

    public SysMenu treeNames(String treeNames) {
        this.treeNames = treeNames;
        return this;
    }

    public void setTreeNames(String treeNames) {
        this.treeNames = treeNames;
    }

    public String getMenuName() {
        return menuName;
    }

    public SysMenu menuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public SysMenu menuType(MenuType menuType) {
        this.menuType = menuType;
        return this;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public SysMenu menuHref(String menuHref) {
        this.menuHref = menuHref;
        return this;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public SysMenu menuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public SysMenu menuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
        return this;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getPermission() {
        return permission;
    }

    public SysMenu permission(String permission) {
        this.permission = permission;
        return this;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public SysMenu menuSort(Integer menuSort) {
        this.menuSort = menuSort;
        return this;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Boolean isIsShow() {
        return isShow;
    }

    public SysMenu isShow(Boolean isShow) {
        this.isShow = isShow;
        return this;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public SysType getSysCode() {
        return sysCode;
    }

    public SysMenu sysCode(SysType sysCode) {
        this.sysCode = sysCode;
        return this;
    }

    public void setSysCode(SysType sysCode) {
        this.sysCode = sysCode;
    }

    public MenuStatusType getStatus() {
        return status;
    }

    public SysMenu status(MenuStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(MenuStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public SysMenu remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysMenu)) {
            return false;
        }
        return id != null && id.equals(((SysMenu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
            "id=" + getId() +
            ", menuCode='" + getMenuCode() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            ", parentCodes='" + getParentCodes() + "'" +
            ", treeSort=" + getTreeSort() +
            ", treeSorts=" + getTreeSorts() +
            ", treeLeaf='" + isTreeLeaf() + "'" +
            ", treeLevel=" + getTreeLevel() +
            ", treeNames='" + getTreeNames() + "'" +
            ", menuName='" + getMenuName() + "'" +
            ", menuType='" + getMenuType() + "'" +
            ", menuHref='" + getMenuHref() + "'" +
            ", menuIcon='" + getMenuIcon() + "'" +
            ", menuTitle='" + getMenuTitle() + "'" +
            ", permission='" + getPermission() + "'" +
            ", menuSort=" + getMenuSort() +
            ", isShow='" + isIsShow() + "'" +
            ", sysCode='" + getSysCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
