package com.ruowei.modules.sys.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.ruowei.modules.sys.domain.table.SysUserRole;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link SysUserRole} entity. This class is used
 * in {@link com.ruowei.web.rest.SysUserRoleResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-user-roles?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysUserRoleCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter sysUserId;

    private StringFilter sysRoleId;

    public SysUserRoleCriteria(){
    }

    public SysUserRoleCriteria(SysUserRoleCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sysUserId = other.sysUserId == null ? null : other.sysUserId.copy();
        this.sysRoleId = other.sysRoleId == null ? null : other.sysRoleId.copy();
    }

    @Override
    public SysUserRoleCriteria copy() {
        return new SysUserRoleCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(StringFilter sysUserId) {
        this.sysUserId = sysUserId;
    }

    public StringFilter getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(StringFilter sysRoleId) {
        this.sysRoleId = sysRoleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SysUserRoleCriteria that = (SysUserRoleCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sysUserId, that.sysUserId) &&
            Objects.equals(sysRoleId, that.sysRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sysUserId,
        sysRoleId
        );
    }

    @Override
    public String toString() {
        return "SysUserRoleCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sysUserId != null ? "sysUserId=" + sysUserId + ", " : "") +
                (sysRoleId != null ? "sysRoleId=" + sysRoleId + ", " : "") +
            "}";
    }

}
