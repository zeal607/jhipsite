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
 * Criteria class for the {@link com.ruowei.domain.SysEmployeeOffice} entity. This class is used
 * in {@link com.ruowei.web.rest.SysEmployeeOfficeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-employee-offices?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysEmployeeOfficeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter sysEmployeeId;

    private StringFilter sysOfficeId;

    private StringFilter sysPostId;

    public SysEmployeeOfficeCriteria(){
    }

    public SysEmployeeOfficeCriteria(SysEmployeeOfficeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sysEmployeeId = other.sysEmployeeId == null ? null : other.sysEmployeeId.copy();
        this.sysOfficeId = other.sysOfficeId == null ? null : other.sysOfficeId.copy();
        this.sysPostId = other.sysPostId == null ? null : other.sysPostId.copy();
    }

    @Override
    public SysEmployeeOfficeCriteria copy() {
        return new SysEmployeeOfficeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSysEmployeeId() {
        return sysEmployeeId;
    }

    public void setSysEmployeeId(StringFilter sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
    }

    public StringFilter getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(StringFilter sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public StringFilter getSysPostId() {
        return sysPostId;
    }

    public void setSysPostId(StringFilter sysPostId) {
        this.sysPostId = sysPostId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SysEmployeeOfficeCriteria that = (SysEmployeeOfficeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sysEmployeeId, that.sysEmployeeId) &&
            Objects.equals(sysOfficeId, that.sysOfficeId) &&
            Objects.equals(sysPostId, that.sysPostId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sysEmployeeId,
        sysOfficeId,
        sysPostId
        );
    }

    @Override
    public String toString() {
        return "SysEmployeeOfficeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sysEmployeeId != null ? "sysEmployeeId=" + sysEmployeeId + ", " : "") +
                (sysOfficeId != null ? "sysOfficeId=" + sysOfficeId + ", " : "") +
                (sysPostId != null ? "sysPostId=" + sysPostId + ", " : "") +
            "}";
    }

}
