package com.ruowei.modules.sys.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.ruowei.modules.sys.domain.table.SysCompany;
import com.ruowei.modules.sys.domain.enumeration.CompanyStatusType;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link SysCompany} entity. This class is used
 * in {@link SysCompanyResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-companies?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysCompanyCriteria implements Serializable, Criteria {
    /**
     * Class for filtering CompanyStatusType
     */
    public static class CompanyStatusTypeFilter extends Filter<CompanyStatusType> {

        public CompanyStatusTypeFilter() {
        }

        public CompanyStatusTypeFilter(CompanyStatusTypeFilter filter) {
            super(filter);
        }

        @Override
        public CompanyStatusTypeFilter copy() {
            return new CompanyStatusTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter companyCode;

    private StringFilter parentCode;

    private StringFilter parentCodes;

    private IntegerFilter treeSort;

    private IntegerFilter treeSorts;

    private BooleanFilter treeLeaf;

    private IntegerFilter treeLevel;

    private StringFilter treeNames;

    private StringFilter viewCode;

    private StringFilter companyName;

    private StringFilter fullName;

    private StringFilter areaCode;

    private CompanyStatusTypeFilter status;

    private StringFilter remarks;

    public SysCompanyCriteria(){
    }

    public SysCompanyCriteria(SysCompanyCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.companyCode = other.companyCode == null ? null : other.companyCode.copy();
        this.parentCode = other.parentCode == null ? null : other.parentCode.copy();
        this.parentCodes = other.parentCodes == null ? null : other.parentCodes.copy();
        this.treeSort = other.treeSort == null ? null : other.treeSort.copy();
        this.treeSorts = other.treeSorts == null ? null : other.treeSorts.copy();
        this.treeLeaf = other.treeLeaf == null ? null : other.treeLeaf.copy();
        this.treeLevel = other.treeLevel == null ? null : other.treeLevel.copy();
        this.treeNames = other.treeNames == null ? null : other.treeNames.copy();
        this.viewCode = other.viewCode == null ? null : other.viewCode.copy();
        this.companyName = other.companyName == null ? null : other.companyName.copy();
        this.fullName = other.fullName == null ? null : other.fullName.copy();
        this.areaCode = other.areaCode == null ? null : other.areaCode.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
    }

    @Override
    public SysCompanyCriteria copy() {
        return new SysCompanyCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(StringFilter companyCode) {
        this.companyCode = companyCode;
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

    public StringFilter getViewCode() {
        return viewCode;
    }

    public void setViewCode(StringFilter viewCode) {
        this.viewCode = viewCode;
    }

    public StringFilter getCompanyName() {
        return companyName;
    }

    public void setCompanyName(StringFilter companyName) {
        this.companyName = companyName;
    }

    public StringFilter getFullName() {
        return fullName;
    }

    public void setFullName(StringFilter fullName) {
        this.fullName = fullName;
    }

    public StringFilter getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(StringFilter areaCode) {
        this.areaCode = areaCode;
    }

    public CompanyStatusTypeFilter getStatus() {
        return status;
    }

    public void setStatus(CompanyStatusTypeFilter status) {
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
        final SysCompanyCriteria that = (SysCompanyCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(companyCode, that.companyCode) &&
            Objects.equals(parentCode, that.parentCode) &&
            Objects.equals(parentCodes, that.parentCodes) &&
            Objects.equals(treeSort, that.treeSort) &&
            Objects.equals(treeSorts, that.treeSorts) &&
            Objects.equals(treeLeaf, that.treeLeaf) &&
            Objects.equals(treeLevel, that.treeLevel) &&
            Objects.equals(treeNames, that.treeNames) &&
            Objects.equals(viewCode, that.viewCode) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(fullName, that.fullName) &&
            Objects.equals(areaCode, that.areaCode) &&
            Objects.equals(status, that.status) &&
            Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        companyCode,
        parentCode,
        parentCodes,
        treeSort,
        treeSorts,
        treeLeaf,
        treeLevel,
        treeNames,
        viewCode,
        companyName,
        fullName,
        areaCode,
        status,
        remarks
        );
    }

    @Override
    public String toString() {
        return "SysCompanyCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (companyCode != null ? "companyCode=" + companyCode + ", " : "") +
                (parentCode != null ? "parentCode=" + parentCode + ", " : "") +
                (parentCodes != null ? "parentCodes=" + parentCodes + ", " : "") +
                (treeSort != null ? "treeSort=" + treeSort + ", " : "") +
                (treeSorts != null ? "treeSorts=" + treeSorts + ", " : "") +
                (treeLeaf != null ? "treeLeaf=" + treeLeaf + ", " : "") +
                (treeLevel != null ? "treeLevel=" + treeLevel + ", " : "") +
                (treeNames != null ? "treeNames=" + treeNames + ", " : "") +
                (viewCode != null ? "viewCode=" + viewCode + ", " : "") +
                (companyName != null ? "companyName=" + companyName + ", " : "") +
                (fullName != null ? "fullName=" + fullName + ", " : "") +
                (areaCode != null ? "areaCode=" + areaCode + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
            "}";
    }

}
