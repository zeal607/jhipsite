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
 * Criteria class for the {@link com.ruowei.domain.SysCompanyOffice} entity. This class is used
 * in {@link com.ruowei.web.rest.SysCompanyOfficeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-company-offices?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysCompanyOfficeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter sysCompanyId;

    private StringFilter sysOfficeId;

    public SysCompanyOfficeCriteria(){
    }

    public SysCompanyOfficeCriteria(SysCompanyOfficeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sysCompanyId = other.sysCompanyId == null ? null : other.sysCompanyId.copy();
        this.sysOfficeId = other.sysOfficeId == null ? null : other.sysOfficeId.copy();
    }

    @Override
    public SysCompanyOfficeCriteria copy() {
        return new SysCompanyOfficeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(StringFilter sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public StringFilter getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(StringFilter sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SysCompanyOfficeCriteria that = (SysCompanyOfficeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sysCompanyId, that.sysCompanyId) &&
            Objects.equals(sysOfficeId, that.sysOfficeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sysCompanyId,
        sysOfficeId
        );
    }

    @Override
    public String toString() {
        return "SysCompanyOfficeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sysCompanyId != null ? "sysCompanyId=" + sysCompanyId + ", " : "") +
                (sysOfficeId != null ? "sysOfficeId=" + sysOfficeId + ", " : "") +
            "}";
    }

}
