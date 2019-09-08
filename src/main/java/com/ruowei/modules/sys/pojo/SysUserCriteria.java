package com.ruowei.modules.sys.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.ruowei.modules.sys.web.SysUserResource;
import io.github.jhipster.service.Criteria;
import com.ruowei.domain.enumeration.GenderType;
import com.ruowei.domain.enumeration.UserType;
import com.ruowei.domain.enumeration.UserStatusType;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.ruowei.domain.SysUser} entity. This class is used
 * in {@link SysUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sys-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SysUserCriteria implements Serializable, Criteria {
    /**
     * Class for filtering GenderType
     */
    public static class GenderTypeFilter extends Filter<GenderType> {

        public GenderTypeFilter() {
        }

        public GenderTypeFilter(GenderTypeFilter filter) {
            super(filter);
        }

        @Override
        public GenderTypeFilter copy() {
            return new GenderTypeFilter(this);
        }

    }
    /**
     * Class for filtering UserType
     */
    public static class UserTypeFilter extends Filter<UserType> {

        public UserTypeFilter() {
        }

        public UserTypeFilter(UserTypeFilter filter) {
            super(filter);
        }

        @Override
        public UserTypeFilter copy() {
            return new UserTypeFilter(this);
        }

    }
    /**
     * Class for filtering UserStatusType
     */
    public static class UserStatusTypeFilter extends Filter<UserStatusType> {

        public UserStatusTypeFilter() {
        }

        public UserStatusTypeFilter(UserStatusTypeFilter filter) {
            super(filter);
        }

        @Override
        public UserStatusTypeFilter copy() {
            return new UserStatusTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter userCode;

    private StringFilter loginCode;

    private StringFilter userName;

    private StringFilter password;

    private StringFilter email;

    private StringFilter mobile;

    private StringFilter phone;

    private GenderTypeFilter sex;

    private StringFilter avatar;

    private StringFilter sign;

    private StringFilter wxOpenid;

    private StringFilter mobileImei;

    private UserTypeFilter userType;

    private StringFilter refCode;

    private StringFilter refName;

    private StringFilter lastLoginIp;

    private InstantFilter lastLoginDate;

    private InstantFilter freezeDate;

    private StringFilter freezeCause;

    private IntegerFilter userSort;

    private UserStatusTypeFilter status;

    private StringFilter remarks;

    public SysUserCriteria(){
    }

    public SysUserCriteria(SysUserCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.userCode = other.userCode == null ? null : other.userCode.copy();
        this.loginCode = other.loginCode == null ? null : other.loginCode.copy();
        this.userName = other.userName == null ? null : other.userName.copy();
        this.password = other.password == null ? null : other.password.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.mobile = other.mobile == null ? null : other.mobile.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.sex = other.sex == null ? null : other.sex.copy();
        this.avatar = other.avatar == null ? null : other.avatar.copy();
        this.sign = other.sign == null ? null : other.sign.copy();
        this.wxOpenid = other.wxOpenid == null ? null : other.wxOpenid.copy();
        this.mobileImei = other.mobileImei == null ? null : other.mobileImei.copy();
        this.userType = other.userType == null ? null : other.userType.copy();
        this.refCode = other.refCode == null ? null : other.refCode.copy();
        this.refName = other.refName == null ? null : other.refName.copy();
        this.lastLoginIp = other.lastLoginIp == null ? null : other.lastLoginIp.copy();
        this.lastLoginDate = other.lastLoginDate == null ? null : other.lastLoginDate.copy();
        this.freezeDate = other.freezeDate == null ? null : other.freezeDate.copy();
        this.freezeCause = other.freezeCause == null ? null : other.freezeCause.copy();
        this.userSort = other.userSort == null ? null : other.userSort.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
    }

    @Override
    public SysUserCriteria copy() {
        return new SysUserCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUserCode() {
        return userCode;
    }

    public void setUserCode(StringFilter userCode) {
        this.userCode = userCode;
    }

    public StringFilter getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(StringFilter loginCode) {
        this.loginCode = loginCode;
    }

    public StringFilter getUserName() {
        return userName;
    }

    public void setUserName(StringFilter userName) {
        this.userName = userName;
    }

    public StringFilter getPassword() {
        return password;
    }

    public void setPassword(StringFilter password) {
        this.password = password;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getMobile() {
        return mobile;
    }

    public void setMobile(StringFilter mobile) {
        this.mobile = mobile;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    public GenderTypeFilter getSex() {
        return sex;
    }

    public void setSex(GenderTypeFilter sex) {
        this.sex = sex;
    }

    public StringFilter getAvatar() {
        return avatar;
    }

    public void setAvatar(StringFilter avatar) {
        this.avatar = avatar;
    }

    public StringFilter getSign() {
        return sign;
    }

    public void setSign(StringFilter sign) {
        this.sign = sign;
    }

    public StringFilter getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(StringFilter wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public StringFilter getMobileImei() {
        return mobileImei;
    }

    public void setMobileImei(StringFilter mobileImei) {
        this.mobileImei = mobileImei;
    }

    public UserTypeFilter getUserType() {
        return userType;
    }

    public void setUserType(UserTypeFilter userType) {
        this.userType = userType;
    }

    public StringFilter getRefCode() {
        return refCode;
    }

    public void setRefCode(StringFilter refCode) {
        this.refCode = refCode;
    }

    public StringFilter getRefName() {
        return refName;
    }

    public void setRefName(StringFilter refName) {
        this.refName = refName;
    }

    public StringFilter getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(StringFilter lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public InstantFilter getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(InstantFilter lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public InstantFilter getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(InstantFilter freezeDate) {
        this.freezeDate = freezeDate;
    }

    public StringFilter getFreezeCause() {
        return freezeCause;
    }

    public void setFreezeCause(StringFilter freezeCause) {
        this.freezeCause = freezeCause;
    }

    public IntegerFilter getUserSort() {
        return userSort;
    }

    public void setUserSort(IntegerFilter userSort) {
        this.userSort = userSort;
    }

    public UserStatusTypeFilter getStatus() {
        return status;
    }

    public void setStatus(UserStatusTypeFilter status) {
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
        final SysUserCriteria that = (SysUserCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(userCode, that.userCode) &&
            Objects.equals(loginCode, that.loginCode) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(password, that.password) &&
            Objects.equals(email, that.email) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(sex, that.sex) &&
            Objects.equals(avatar, that.avatar) &&
            Objects.equals(sign, that.sign) &&
            Objects.equals(wxOpenid, that.wxOpenid) &&
            Objects.equals(mobileImei, that.mobileImei) &&
            Objects.equals(userType, that.userType) &&
            Objects.equals(refCode, that.refCode) &&
            Objects.equals(refName, that.refName) &&
            Objects.equals(lastLoginIp, that.lastLoginIp) &&
            Objects.equals(lastLoginDate, that.lastLoginDate) &&
            Objects.equals(freezeDate, that.freezeDate) &&
            Objects.equals(freezeCause, that.freezeCause) &&
            Objects.equals(userSort, that.userSort) &&
            Objects.equals(status, that.status) &&
            Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        userCode,
        loginCode,
        userName,
        password,
        email,
        mobile,
        phone,
        sex,
        avatar,
        sign,
        wxOpenid,
        mobileImei,
        userType,
        refCode,
        refName,
        lastLoginIp,
        lastLoginDate,
        freezeDate,
        freezeCause,
        userSort,
        status,
        remarks
        );
    }

    @Override
    public String toString() {
        return "SysUserCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (userCode != null ? "userCode=" + userCode + ", " : "") +
                (loginCode != null ? "loginCode=" + loginCode + ", " : "") +
                (userName != null ? "userName=" + userName + ", " : "") +
                (password != null ? "password=" + password + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (mobile != null ? "mobile=" + mobile + ", " : "") +
                (phone != null ? "phone=" + phone + ", " : "") +
                (sex != null ? "sex=" + sex + ", " : "") +
                (avatar != null ? "avatar=" + avatar + ", " : "") +
                (sign != null ? "sign=" + sign + ", " : "") +
                (wxOpenid != null ? "wxOpenid=" + wxOpenid + ", " : "") +
                (mobileImei != null ? "mobileImei=" + mobileImei + ", " : "") +
                (userType != null ? "userType=" + userType + ", " : "") +
                (refCode != null ? "refCode=" + refCode + ", " : "") +
                (refName != null ? "refName=" + refName + ", " : "") +
                (lastLoginIp != null ? "lastLoginIp=" + lastLoginIp + ", " : "") +
                (lastLoginDate != null ? "lastLoginDate=" + lastLoginDate + ", " : "") +
                (freezeDate != null ? "freezeDate=" + freezeDate + ", " : "") +
                (freezeCause != null ? "freezeCause=" + freezeCause + ", " : "") +
                (userSort != null ? "userSort=" + userSort + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
            "}";
    }

}
