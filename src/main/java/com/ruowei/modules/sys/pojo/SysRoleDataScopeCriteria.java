package com.ruowei.modules.sys.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.ruowei.modules.sys.domain.enumeration.ControlType;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.ruowei.domain.SysRoleDataScope} entity. This class is used
 * in {@link com.ruowei.web.rest.SysRoleDataScopeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-role-data-scopes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysRoleDataScopeCriteria implements Serializable, Criteria {
    /**
     * Class for filtering ControlType
     */
    public static class ControlTypeFilter extends Filter<ControlType> {

        public ControlTypeFilter() {
        }

        public ControlTypeFilter(ControlTypeFilter filter) {
            super(filter);
        }

        @Override
        public ControlTypeFilter copy() {
            return new ControlTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter sysRoleId;

    private ControlTypeFilter ctrlType;

    private StringFilter ctrlData;

    private StringFilter ctrlPermi;

    public SysRoleDataScopeCriteria(){
    }

    public SysRoleDataScopeCriteria(SysRoleDataScopeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sysRoleId = other.sysRoleId == null ? null : other.sysRoleId.copy();
        this.ctrlType = other.ctrlType == null ? null : other.ctrlType.copy();
        this.ctrlData = other.ctrlData == null ? null : other.ctrlData.copy();
        this.ctrlPermi = other.ctrlPermi == null ? null : other.ctrlPermi.copy();
    }

    @Override
    public SysRoleDataScopeCriteria copy() {
        return new SysRoleDataScopeCriteria(this);
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

    public ControlTypeFilter getCtrlType() {
        return ctrlType;
    }

    public void setCtrlType(ControlTypeFilter ctrlType) {
        this.ctrlType = ctrlType;
    }

    public StringFilter getCtrlData() {
        return ctrlData;
    }

    public void setCtrlData(StringFilter ctrlData) {
        this.ctrlData = ctrlData;
    }

    public StringFilter getCtrlPermi() {
        return ctrlPermi;
    }

    public void setCtrlPermi(StringFilter ctrlPermi) {
        this.ctrlPermi = ctrlPermi;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SysRoleDataScopeCriteria that = (SysRoleDataScopeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sysRoleId, that.sysRoleId) &&
            Objects.equals(ctrlType, that.ctrlType) &&
            Objects.equals(ctrlData, that.ctrlData) &&
            Objects.equals(ctrlPermi, that.ctrlPermi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sysRoleId,
        ctrlType,
        ctrlData,
        ctrlPermi
        );
    }

    @Override
    public String toString() {
        return "SysRoleDataScopeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sysRoleId != null ? "sysRoleId=" + sysRoleId + ", " : "") +
                (ctrlType != null ? "ctrlType=" + ctrlType + ", " : "") +
                (ctrlData != null ? "ctrlData=" + ctrlData + ", " : "") +
                (ctrlPermi != null ? "ctrlPermi=" + ctrlPermi + ", " : "") +
            "}";
    }

}
