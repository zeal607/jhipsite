package com.ruowei.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.ruowei.domain.enumeration.MenuType;
import com.ruowei.domain.enumeration.SysType;
import com.ruowei.domain.enumeration.MenuStatusType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.ruowei.domain.SysMenu} entity. This class is used
 * in {@link com.ruowei.web.rest.SysMenuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-menus?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysMenuCriteria implements Serializable, Criteria {
    /**
     * Class for filtering MenuType
     */
    public static class MenuTypeFilter extends Filter<MenuType> {

        public MenuTypeFilter() {
        }

        public MenuTypeFilter(MenuTypeFilter filter) {
            super(filter);
        }

        @Override
        public MenuTypeFilter copy() {
            return new MenuTypeFilter(this);
        }

    }
    /**
     * Class for filtering SysType
     */
    public static class SysTypeFilter extends Filter<SysType> {

        public SysTypeFilter() {
        }

        public SysTypeFilter(SysTypeFilter filter) {
            super(filter);
        }

        @Override
        public SysTypeFilter copy() {
            return new SysTypeFilter(this);
        }

    }
    /**
     * Class for filtering MenuStatusType
     */
    public static class MenuStatusTypeFilter extends Filter<MenuStatusType> {

        public MenuStatusTypeFilter() {
        }

        public MenuStatusTypeFilter(MenuStatusTypeFilter filter) {
            super(filter);
        }

        @Override
        public MenuStatusTypeFilter copy() {
            return new MenuStatusTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter menuCode;

    private StringFilter parentCode;

    private StringFilter parentCodes;

    private IntegerFilter treeSort;

    private IntegerFilter treeSorts;

    private BooleanFilter treeLeaf;

    private IntegerFilter treeLevel;

    private StringFilter treeNames;

    private StringFilter menuName;

    private MenuTypeFilter menuType;

    private StringFilter menuHref;

    private StringFilter menuIcon;

    private StringFilter menuTitle;

    private StringFilter permission;

    private IntegerFilter menuSort;

    private BooleanFilter isShow;

    private SysTypeFilter sysCode;

    private MenuStatusTypeFilter status;

    private StringFilter remarks;

    public SysMenuCriteria(){
    }

    public SysMenuCriteria(SysMenuCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.menuCode = other.menuCode == null ? null : other.menuCode.copy();
        this.parentCode = other.parentCode == null ? null : other.parentCode.copy();
        this.parentCodes = other.parentCodes == null ? null : other.parentCodes.copy();
        this.treeSort = other.treeSort == null ? null : other.treeSort.copy();
        this.treeSorts = other.treeSorts == null ? null : other.treeSorts.copy();
        this.treeLeaf = other.treeLeaf == null ? null : other.treeLeaf.copy();
        this.treeLevel = other.treeLevel == null ? null : other.treeLevel.copy();
        this.treeNames = other.treeNames == null ? null : other.treeNames.copy();
        this.menuName = other.menuName == null ? null : other.menuName.copy();
        this.menuType = other.menuType == null ? null : other.menuType.copy();
        this.menuHref = other.menuHref == null ? null : other.menuHref.copy();
        this.menuIcon = other.menuIcon == null ? null : other.menuIcon.copy();
        this.menuTitle = other.menuTitle == null ? null : other.menuTitle.copy();
        this.permission = other.permission == null ? null : other.permission.copy();
        this.menuSort = other.menuSort == null ? null : other.menuSort.copy();
        this.isShow = other.isShow == null ? null : other.isShow.copy();
        this.sysCode = other.sysCode == null ? null : other.sysCode.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
    }

    @Override
    public SysMenuCriteria copy() {
        return new SysMenuCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(StringFilter menuCode) {
        this.menuCode = menuCode;
    }

    public StringFilter getParentCode() {
        return parentCode;
    }

    public void setParentCode(StringFilter parentCode) {
        this.parentCode = parentCode;
    }

    public StringFilter getParentCodes() {
        return parentCodes;
    }

    public void setParentCodes(StringFilter parentCodes) {
        this.parentCodes = parentCodes;
    }

    public IntegerFilter getTreeSort() {
        return treeSort;
    }

    public void setTreeSort(IntegerFilter treeSort) {
        this.treeSort = treeSort;
    }

    public IntegerFilter getTreeSorts() {
        return treeSorts;
    }

    public void setTreeSorts(IntegerFilter treeSorts) {
        this.treeSorts = treeSorts;
    }

    public BooleanFilter getTreeLeaf() {
        return treeLeaf;
    }

    public void setTreeLeaf(BooleanFilter treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public IntegerFilter getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(IntegerFilter treeLevel) {
        this.treeLevel = treeLevel;
    }

    public StringFilter getTreeNames() {
        return treeNames;
    }

    public void setTreeNames(StringFilter treeNames) {
        this.treeNames = treeNames;
    }

    public StringFilter getMenuName() {
        return menuName;
    }

    public void setMenuName(StringFilter menuName) {
        this.menuName = menuName;
    }

    public MenuTypeFilter getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuTypeFilter menuType) {
        this.menuType = menuType;
    }

    public StringFilter getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(StringFilter menuHref) {
        this.menuHref = menuHref;
    }

    public StringFilter getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(StringFilter menuIcon) {
        this.menuIcon = menuIcon;
    }

    public StringFilter getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(StringFilter menuTitle) {
        this.menuTitle = menuTitle;
    }

    public StringFilter getPermission() {
        return permission;
    }

    public void setPermission(StringFilter permission) {
        this.permission = permission;
    }

    public IntegerFilter getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(IntegerFilter menuSort) {
        this.menuSort = menuSort;
    }

    public BooleanFilter getIsShow() {
        return isShow;
    }

    public void setIsShow(BooleanFilter isShow) {
        this.isShow = isShow;
    }

    public SysTypeFilter getSysCode() {
        return sysCode;
    }

    public void setSysCode(SysTypeFilter sysCode) {
        this.sysCode = sysCode;
    }

    public MenuStatusTypeFilter getStatus() {
        return status;
    }

    public void setStatus(MenuStatusTypeFilter status) {
        this.status = status;
    }

    public StringFilter getRemarks() {
        return remarks;
    }

    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SysMenuCriteria that = (SysMenuCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(menuCode, that.menuCode) &&
            Objects.equals(parentCode, that.parentCode) &&
            Objects.equals(parentCodes, that.parentCodes) &&
            Objects.equals(treeSort, that.treeSort) &&
            Objects.equals(treeSorts, that.treeSorts) &&
            Objects.equals(treeLeaf, that.treeLeaf) &&
            Objects.equals(treeLevel, that.treeLevel) &&
            Objects.equals(treeNames, that.treeNames) &&
            Objects.equals(menuName, that.menuName) &&
            Objects.equals(menuType, that.menuType) &&
            Objects.equals(menuHref, that.menuHref) &&
            Objects.equals(menuIcon, that.menuIcon) &&
            Objects.equals(menuTitle, that.menuTitle) &&
            Objects.equals(permission, that.permission) &&
            Objects.equals(menuSort, that.menuSort) &&
            Objects.equals(isShow, that.isShow) &&
            Objects.equals(sysCode, that.sysCode) &&
            Objects.equals(status, that.status) &&
            Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        menuCode,
        parentCode,
        parentCodes,
        treeSort,
        treeSorts,
        treeLeaf,
        treeLevel,
        treeNames,
        menuName,
        menuType,
        menuHref,
        menuIcon,
        menuTitle,
        permission,
        menuSort,
        isShow,
        sysCode,
        status,
        remarks
        );
    }

    @Override
    public String toString() {
        return "SysMenuCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (menuCode != null ? "menuCode=" + menuCode + ", " : "") +
                (parentCode != null ? "parentCode=" + parentCode + ", " : "") +
                (parentCodes != null ? "parentCodes=" + parentCodes + ", " : "") +
                (treeSort != null ? "treeSort=" + treeSort + ", " : "") +
                (treeSorts != null ? "treeSorts=" + treeSorts + ", " : "") +
                (treeLeaf != null ? "treeLeaf=" + treeLeaf + ", " : "") +
                (treeLevel != null ? "treeLevel=" + treeLevel + ", " : "") +
                (treeNames != null ? "treeNames=" + treeNames + ", " : "") +
                (menuName != null ? "menuName=" + menuName + ", " : "") +
                (menuType != null ? "menuType=" + menuType + ", " : "") +
                (menuHref != null ? "menuHref=" + menuHref + ", " : "") +
                (menuIcon != null ? "menuIcon=" + menuIcon + ", " : "") +
                (menuTitle != null ? "menuTitle=" + menuTitle + ", " : "") +
                (permission != null ? "permission=" + permission + ", " : "") +
                (menuSort != null ? "menuSort=" + menuSort + ", " : "") +
                (isShow != null ? "isShow=" + isShow + ", " : "") +
                (sysCode != null ? "sysCode=" + sysCode + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
            "}";
    }

}
