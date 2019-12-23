package com.ruowei.modules.sys.domain.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 员工的附属机构及岗位信息
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeOfficePostInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sys_employee_office表主键
     */
    @Id
    private Long id;

    /**
     * 员工
     */
    @ManyToOne
    @JoinColumn(name="sys_employee_id",referencedColumnName ="id")
    private SysEmployeeDetail employeeDetail;

    /**
     * 机构
     */
    @ManyToOne
    @JoinColumn(name="sys_office_id",referencedColumnName ="id")
    private SysEmployeeOfficeInfo officeInfo;

    /**
     * 岗位
     */
    @ManyToOne
    @JoinColumn(name="sys_post_id",referencedColumnName ="id")
    private SysEmployeePostInfo postInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SysEmployeeDetail getEmployeeDetail() {
        return employeeDetail;
    }

    public void setEmployeeDetail(SysEmployeeDetail employeeDetail) {
        this.employeeDetail = employeeDetail;
    }

    public SysEmployeeOfficeInfo getOfficeInfo() {
        return officeInfo;
    }

    public void setOfficeInfo(SysEmployeeOfficeInfo officeInfo) {
        this.officeInfo = officeInfo;
    }

    public SysEmployeePostInfo getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(SysEmployeePostInfo postInfo) {
        this.postInfo = postInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeOfficePostInfo that = (SysEmployeeOfficePostInfo) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(employeeDetail, that.employeeDetail) &&
            Objects.equals(officeInfo, that.officeInfo) &&
            Objects.equals(postInfo, that.postInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeDetail, officeInfo, postInfo);
    }

    @Override
    public String toString() {
        return "SysEmployeeOfficePostInfo{" +
            "id=" + id +
            ", employeeDetail=" + employeeDetail +
            ", officeInfo=" + officeInfo +
            ", postInfo=" + postInfo +
            '}';
    }
}
