package com.ruowei.modules.sys.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.enumeration.OfficeType;
import com.ruowei.modules.sys.web.rest.SysOfficeResource;
import io.github.jhipster.service.Criteria;
import com.ruowei.modules.sys.domain.enumeration.OfficeStatusType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link SysOffice} entity. This class is used
 * in {@link SysOfficeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-offices?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysOfficeCriteria implements Serializable, Criteria {
    /**
     * Class for filtering OfficeType
     */
    public static class OfficeTypeFilter extends Filter<OfficeType> {

        public OfficeTypeFilter() {
        }

        public OfficeTypeFilter(OfficeTypeFilter filter) {
            super(filter);
        }

        @Override
        public OfficeTypeFilter copy() {
            return new OfficeTypeFilter(this);
        }

    }
    /**
     * Class for filtering OfficeStatusType
     */
    public static class OfficeStatusTypeFilter extends Filter<OfficeStatusType> {

        public OfficeStatusTypeFilter() {
        }

        public OfficeStatusTypeFilter(OfficeStatusTypeFilter filter) {
            super(filter);
        }

        @Override
        public OfficeStatusTypeFilter copy() {
            return new OfficeStatusTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter officeCode;

    private StringFilter parentCode;

    private StringFilter parentCodes;

    private IntegerFilter treeSort;

    private IntegerFilter treeSorts;

    private BooleanFilter treeLeaf;

    private IntegerFilter treeLevel;

    private StringFilter treeNames;

    private StringFilter viewCode;

    private StringFilter officeName;

    private StringFilter fullName;

    private OfficeTypeFilter officeType;

    private StringFilter leader;

    private StringFilter phone;

    private StringFilter address;

    private StringFilter zipCode;

    private StringFilter email;

    private StringFilter remarks;

    private OfficeStatusTypeFilter status;

    public SysOfficeCriteria(){
    }

    public SysOfficeCriteria(SysOfficeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.officeCode = other.officeCode == null ? null : other.officeCode.copy();
        this.parentCode = other.parentCode == null ? null : other.parentCode.copy();
        this.parentCodes = other.parentCodes == null ? null : other.parentCodes.copy();
        this.treeSort = other.treeSort == null ? null : other.treeSort.copy();
        this.treeSorts = other.treeSorts == null ? null : other.treeSorts.copy();
        this.treeLeaf = other.treeLeaf == null ? null : other.treeLeaf.copy();
        this.treeLevel = other.treeLevel == null ? null : other.treeLevel.copy();
        this.treeNames = other.treeNames == null ? null : other.treeNames.copy();
        this.viewCode = other.viewCode == null ? null : other.viewCode.copy();
        this.officeName = other.officeName == null ? null : other.officeName.copy();
        this.fullName = other.fullName == null ? null : other.fullName.copy();
        this.officeType = other.officeType == null ? null : other.officeType.copy();
        this.leader = other.leader == null ? null : other.leader.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.zipCode = other.zipCode == null ? null : other.zipCode.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.status = other.status == null ? null : other.status.copy();
    }

    @Override
    public SysOfficeCriteria copy() {
        return new SysOfficeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(StringFilter officeCode) {
        this.officeCode = officeCode;
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

    public StringFilter getOfficeName() {
        return officeName;
    }

    public void setOfficeName(StringFilter officeName) {
        this.officeName = officeName;
    }

    public StringFilter getFullName() {
        return fullName;
    }

    public void setFullName(StringFilter fullName) {
        this.fullName = fullName;
    }

    public OfficeTypeFilter getOfficeType() {
        return officeType;
    }

    public void setOfficeType(OfficeTypeFilter officeType) {
        this.officeType = officeType;
    }

    public StringFilter getLeader() {
        return leader;
    }

    public void setLeader(StringFilter leader) {
        this.leader = leader;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getZipCode() {
        return zipCode;
    }

    public void setZipCode(StringFilter zipCode) {
        this.zipCode = zipCode;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getRemarks() {
        return remarks;
    }

    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }

    public OfficeStatusTypeFilter getStatus() {
        return status;
    }

    public void setStatus(OfficeStatusTypeFilter status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SysOfficeCriteria that = (SysOfficeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(officeCode, that.officeCode) &&
            Objects.equals(parentCode, that.parentCode) &&
            Objects.equals(parentCodes, that.parentCodes) &&
            Objects.equals(treeSort, that.treeSort) &&
            Objects.equals(treeSorts, that.treeSorts) &&
            Objects.equals(treeLeaf, that.treeLeaf) &&
            Objects.equals(treeLevel, that.treeLevel) &&
            Objects.equals(treeNames, that.treeNames) &&
            Objects.equals(viewCode, that.viewCode) &&
            Objects.equals(officeName, that.officeName) &&
            Objects.equals(fullName, that.fullName) &&
            Objects.equals(officeType, that.officeType) &&
            Objects.equals(leader, that.leader) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(address, that.address) &&
            Objects.equals(zipCode, that.zipCode) &&
            Objects.equals(email, that.email) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        officeCode,
        parentCode,
        parentCodes,
        treeSort,
        treeSorts,
        treeLeaf,
        treeLevel,
        treeNames,
        viewCode,
        officeName,
        fullName,
        officeType,
        leader,
        phone,
        address,
        zipCode,
        email,
        remarks,
        status
        );
    }

    @Override
    public String toString() {
        return "SysOfficeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (officeCode != null ? "officeCode=" + officeCode + ", " : "") +
                (parentCode != null ? "parentCode=" + parentCode + ", " : "") +
                (parentCodes != null ? "parentCodes=" + parentCodes + ", " : "") +
                (treeSort != null ? "treeSort=" + treeSort + ", " : "") +
                (treeSorts != null ? "treeSorts=" + treeSorts + ", " : "") +
                (treeLeaf != null ? "treeLeaf=" + treeLeaf + ", " : "") +
                (treeLevel != null ? "treeLevel=" + treeLevel + ", " : "") +
                (treeNames != null ? "treeNames=" + treeNames + ", " : "") +
                (viewCode != null ? "viewCode=" + viewCode + ", " : "") +
                (officeName != null ? "officeName=" + officeName + ", " : "") +
                (fullName != null ? "fullName=" + fullName + ", " : "") +
                (officeType != null ? "officeType=" + officeType + ", " : "") +
                (leader != null ? "leader=" + leader + ", " : "") +
                (phone != null ? "phone=" + phone + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (zipCode != null ? "zipCode=" + zipCode + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
            "}";
    }

}
