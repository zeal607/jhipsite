package com.ruowei.modules.sys.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.ruowei.modules.sys.domain.enumeration.ControlType;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.ruowei.domain.SysUserDataScope} entity. This class is used
 * in {@link com.ruowei.web.rest.SysUserDataScopeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-user-data-scopes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysUserDataScopeCriteria implements Serializable, Criteria {
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

    private StringFilter sysUserId;

    private ControlTypeFilter ctrlType;

    private StringFilter ctrlData;

    private StringFilter ctrlPermi;

    public SysUserDataScopeCriteria(){
    }

    public SysUserDataScopeCriteria(SysUserDataScopeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sysUserId = other.sysUserId == null ? null : other.sysUserId.copy();
        this.ctrlType = other.ctrlType == null ? null : other.ctrlType.copy();
        this.ctrlData = other.ctrlData == null ? null : other.ctrlData.copy();
        this.ctrlPermi = other.ctrlPermi == null ? null : other.ctrlPermi.copy();
    }

    @Override
    public SysUserDataScopeCriteria copy() {
        return new SysUserDataScopeCriteria(this);
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
        final SysUserDataScopeCriteria that = (SysUserDataScopeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sysUserId, that.sysUserId) &&
            Objects.equals(ctrlType, that.ctrlType) &&
            Objects.equals(ctrlData, that.ctrlData) &&
            Objects.equals(ctrlPermi, that.ctrlPermi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sysUserId,
        ctrlType,
        ctrlData,
        ctrlPermi
        );
    }

    @Override
    public String toString() {
        return "SysUserDataScopeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sysUserId != null ? "sysUserId=" + sysUserId + ", " : "") +
                (ctrlType != null ? "ctrlType=" + ctrlType + ", " : "") +
                (ctrlData != null ? "ctrlData=" + ctrlData + ", " : "") +
                (ctrlPermi != null ? "ctrlPermi=" + ctrlPermi + ", " : "") +
            "}";
    }

}
