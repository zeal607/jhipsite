package com.ruowei.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.ruowei.domain.SysRoleMenu} entity. This class is used
 * in {@link com.ruowei.web.rest.SysRoleMenuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-role-menus?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysRoleMenuCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter sysRoleId;

    private StringFilter sysMenuId;

    public SysRoleMenuCriteria(){
    }

    public SysRoleMenuCriteria(SysRoleMenuCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sysRoleId = other.sysRoleId == null ? null : other.sysRoleId.copy();
        this.sysMenuId = other.sysMenuId == null ? null : other.sysMenuId.copy();
    }

    @Override
    public SysRoleMenuCriteria copy() {
        return new SysRoleMenuCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(StringFilter sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public StringFilter getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(StringFilter sysMenuId) {
        this.sysMenuId = sysMenuId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SysRoleMenuCriteria that = (SysRoleMenuCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sysRoleId, that.sysRoleId) &&
            Objects.equals(sysMenuId, that.sysMenuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sysRoleId,
        sysMenuId
        );
    }

    @Override
    public String toString() {
        return "SysRoleMenuCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sysRoleId != null ? "sysRoleId=" + sysRoleId + ", " : "") +
                (sysMenuId != null ? "sysMenuId=" + sysMenuId + ", " : "") +
            "}";
    }

}
