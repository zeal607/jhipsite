package com.ruowei.modules.sys.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.ruowei.modules.sys.domain.table.SysEmployee;
import io.github.jhipster.service.Criteria;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link SysEmployee} entity. This class is used
 * in {@link com.ruowei.web.rest.SysEmployeeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-employees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysEmployeeCriteria implements Serializable, Criteria {
    /**
     * Class for filtering EmployeeStatusType
     */
    public static class EmployeeStatusTypeFilter extends Filter<EmployeeStatusType> {

        public EmployeeStatusTypeFilter() {
        }

        public EmployeeStatusTypeFilter(EmployeeStatusTypeFilter filter) {
            super(filter);
        }

        @Override
        public EmployeeStatusTypeFilter copy() {
            return new EmployeeStatusTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter empCode;

    private StringFilter empName;

    private StringFilter empNameEn;

    private StringFilter sysOfficeId;

    private StringFilter officeName;

    private StringFilter sysCompanyId;

    private StringFilter companyName;

    private EmployeeStatusTypeFilter status;

    private StringFilter remarks;

    public SysEmployeeCriteria(){
    }

    public SysEmployeeCriteria(SysEmployeeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.empCode = other.empCode == null ? null : other.empCode.copy();
        this.empName = other.empName == null ? null : other.empName.copy();
        this.empNameEn = other.empNameEn == null ? null : other.empNameEn.copy();
        this.sysOfficeId = other.sysOfficeId == null ? null : other.sysOfficeId.copy();
        this.officeName = other.officeName == null ? null : other.officeName.copy();
        this.sysCompanyId = other.sysCompanyId == null ? null : other.sysCompanyId.copy();
        this.companyName = other.companyName == null ? null : other.companyName.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
    }

    @Override
    public SysEmployeeCriteria copy() {
        return new SysEmployeeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEmpCode() {
        return empCode;
    }

    public void setEmpCode(StringFilter empCode) {
        this.empCode = empCode;
    }

    public StringFilter getEmpName() {
        return empName;
    }

    public void setEmpName(StringFilter empName) {
        this.empName = empName;
    }

    public StringFilter getEmpNameEn() {
        return empNameEn;
    }

    public void setEmpNameEn(StringFilter empNameEn) {
        this.empNameEn = empNameEn;
    }

    public StringFilter getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(StringFilter sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public StringFilter getOfficeName() {
        return officeName;
    }

    public void setOfficeName(StringFilter officeName) {
        this.officeName = officeName;
    }

    public StringFilter getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(StringFilter sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public StringFilter getCompanyName() {
        return companyName;
    }

    public void setCompanyName(StringFilter companyName) {
        this.companyName = companyName;
    }

    public EmployeeStatusTypeFilter getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatusTypeFilter status) {
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
        final SysEmployeeCriteria that = (SysEmployeeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(empNameEn, that.empNameEn) &&
            Objects.equals(sysOfficeId, that.sysOfficeId) &&
            Objects.equals(officeName, that.officeName) &&
            Objects.equals(sysCompanyId, that.sysCompanyId) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(status, that.status) &&
            Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        empCode,
        empName,
        empNameEn,
        sysOfficeId,
        officeName,
        sysCompanyId,
        companyName,
        status,
        remarks
        );
    }

    @Override
    public String toString() {
        return "SysEmployeeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (empCode != null ? "empCode=" + empCode + ", " : "") +
                (empName != null ? "empName=" + empName + ", " : "") +
                (empNameEn != null ? "empNameEn=" + empNameEn + ", " : "") +
                (sysOfficeId != null ? "sysOfficeId=" + sysOfficeId + ", " : "") +
                (officeName != null ? "officeName=" + officeName + ", " : "") +
                (sysCompanyId != null ? "sysCompanyId=" + sysCompanyId + ", " : "") +
                (companyName != null ? "companyName=" + companyName + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
            "}";
    }

}
