package com.ruowei.modules.sys.domain.table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruowei.common.entity.BaseEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * 员工附属机构关系表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeOffice extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
//    @Size(max = 100)
//    @Column(name = "sys_employee_id", length = 100, nullable = false)
//    private String sysEmployeeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private SysEmployee sysEmployee;

    /**
     * 机构ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_office_id", length = 100, nullable = false)
    private String sysOfficeId;

    /**
     * 岗位ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_post_id", length = 100, nullable = false)
    private String sysPostId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public SysEmployeeOffice sysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
        return this;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getSysPostId() {
        return sysPostId;
    }

    public SysEmployeeOffice sysPostId(String sysPostId) {
        this.sysPostId = sysPostId;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public void setSysPostId(String sysPostId) {
        this.sysPostId = sysPostId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


//    public String getSysEmployeeId() {
//        return sysEmployeeId;
//    }
//
//    public void setSysEmployeeId(String sysEmployeeId) {
//        this.sysEmployeeId = sysEmployeeId;
//    }


    public SysEmployee getSysEmployee() {
        return sysEmployee;
    }

    public void setSysEmployee(SysEmployee sysEmployee) {
        this.sysEmployee = sysEmployee;
    }
}
