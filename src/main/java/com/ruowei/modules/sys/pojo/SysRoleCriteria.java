package com.ruowei.modules.sys.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.ruowei.modules.sys.domain.SysRole;
import com.ruowei.modules.sys.domain.enumeration.DataScopeType;
import com.ruowei.modules.sys.domain.enumeration.RoleStatusType;
import com.ruowei.modules.sys.domain.enumeration.RoleType;
import com.ruowei.modules.sys.web.rest.SysRoleResource;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link SysRole} entity. This class is used
 * in {@link SysRoleResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-roles?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysRoleCriteria implements Serializable, Criteria {
    /**
     * Class for filtering RoleType
     */
    public static class RoleTypeFilter extends Filter<RoleType> {

        public RoleTypeFilter() {
        }

        public RoleTypeFilter(RoleTypeFilter filter) {
            super(filter);
        }

        @Override
        public RoleTypeFilter copy() {
            return new RoleTypeFilter(this);
        }

    }
    /**
     * Class for filtering DataScopeType
     */
    public static class DataScopeTypeFilter extends Filter<DataScopeType> {

        public DataScopeTypeFilter() {
        }

        public DataScopeTypeFilter(DataScopeTypeFilter filter) {
            super(filter);
        }

        @Override
        public DataScopeTypeFilter copy() {
            return new DataScopeTypeFilter(this);
        }

    }
    /**
     * Class for filtering RoleStatusType
     */
    public static class RoleStatusTypeFilter extends Filter<RoleStatusType> {

        public RoleStatusTypeFilter() {
        }

        public RoleStatusTypeFilter(RoleStatusTypeFilter filter) {
            super(filter);
        }

        @Override
        public RoleStatusTypeFilter copy() {
            return new RoleStatusTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter roleCode;

    private StringFilter roleName;

    private RoleTypeFilter roleType;

    private IntegerFilter roleSort;

    private BooleanFilter isSys;

    private DataScopeTypeFilter dataScope;

    private StringFilter bizScope;

    private RoleStatusTypeFilter status;

    private StringFilter remarks;

    public SysRoleCriteria(){
    }

    public SysRoleCriteria(SysRoleCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.roleCode = other.roleCode == null ? null : other.roleCode.copy();
        this.roleName = other.roleName == null ? null : other.roleName.copy();
        this.roleType = other.roleType == null ? null : other.roleType.copy();
        this.roleSort = other.roleSort == null ? null : other.roleSort.copy();
        this.isSys = other.isSys == null ? null : other.isSys.copy();
        this.dataScope = other.dataScope == null ? null : other.dataScope.copy();
        this.bizScope = other.bizScope == null ? null : other.bizScope.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
    }

    @Override
    public SysRoleCriteria copy() {
        return new SysRoleCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(StringFilter roleCode) {
        this.roleCode = roleCode;
    }

    public StringFilter getRoleName() {
        return roleName;
    }

    public void setRoleName(StringFilter roleName) {
        this.roleName = roleName;
    }

    public RoleTypeFilter getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleTypeFilter roleType) {
        this.roleType = roleType;
    }

    public IntegerFilter getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(IntegerFilter roleSort) {
        this.roleSort = roleSort;
    }

    public BooleanFilter getIsSys() {
        return isSys;
    }

    public void setIsSys(BooleanFilter isSys) {
        this.isSys = isSys;
    }

    public DataScopeTypeFilter getDataScope() {
        return dataScope;
    }

    public void setDataScope(DataScopeTypeFilter dataScope) {
        this.dataScope = dataScope;
    }

    public StringFilter getBizScope() {
        return bizScope;
    }

    public void setBizScope(StringFilter bizScope) {
        this.bizScope = bizScope;
    }

    public RoleStatusTypeFilter getStatus() {
        return status;
    }

    public void setStatus(RoleStatusTypeFilter status) {
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
        final SysRoleCriteria that = (SysRoleCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(roleCode, that.roleCode) &&
            Objects.equals(roleName, that.roleName) &&
            Objects.equals(roleType, that.roleType) &&
            Objects.equals(roleSort, that.roleSort) &&
            Objects.equals(isSys, that.isSys) &&
            Objects.equals(dataScope, that.dataScope) &&
            Objects.equals(bizScope, that.bizScope) &&
            Objects.equals(status, that.status) &&
            Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        roleCode,
        roleName,
        roleType,
        roleSort,
        isSys,
        dataScope,
        bizScope,
        status,
        remarks
        );
    }

    @Override
    public String toString() {
        return "SysRoleCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (roleCode != null ? "roleCode=" + roleCode + ", " : "") +
                (roleName != null ? "roleName=" + roleName + ", " : "") +
                (roleType != null ? "roleType=" + roleType + ", " : "") +
                (roleSort != null ? "roleSort=" + roleSort + ", " : "") +
                (isSys != null ? "isSys=" + isSys + ", " : "") +
                (dataScope != null ? "dataScope=" + dataScope + ", " : "") +
                (bizScope != null ? "bizScope=" + bizScope + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
            "}";
    }

}
