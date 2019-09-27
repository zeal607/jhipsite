package com.ruowei.modules.sys.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.ruowei.modules.sys.domain.SysEmployeePost;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link SysEmployeePost} entity. This class is used
 * in {@link com.ruowei.web.rest.SysEmployeePostResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-employee-posts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysEmployeePostCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter sysEmployeeId;

    private StringFilter sysPostId;

    public SysEmployeePostCriteria(){
    }

    public SysEmployeePostCriteria(SysEmployeePostCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sysEmployeeId = other.sysEmployeeId == null ? null : other.sysEmployeeId.copy();
        this.sysPostId = other.sysPostId == null ? null : other.sysPostId.copy();
    }

    @Override
    public SysEmployeePostCriteria copy() {
        return new SysEmployeePostCriteria(this);
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
        final SysEmployeePostCriteria that = (SysEmployeePostCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sysEmployeeId, that.sysEmployeeId) &&
            Objects.equals(sysPostId, that.sysPostId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sysEmployeeId,
        sysPostId
        );
    }

    @Override
    public String toString() {
        return "SysEmployeePostCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sysEmployeeId != null ? "sysEmployeeId=" + sysEmployeeId + ", " : "") +
                (sysPostId != null ? "sysPostId=" + sysPostId + ", " : "") +
            "}";
    }

}
