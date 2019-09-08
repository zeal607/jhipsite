package com.ruowei.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * 员工与岗位关联表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee_post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeePost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 员工ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_employee_id", length = 100, nullable = false)
    private String sysEmployeeId;

    /**
     * 岗位ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_post_id", length = 100, nullable = false)
    private String sysPostId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysEmployeeId() {
        return sysEmployeeId;
    }

    public SysEmployeePost sysEmployeeId(String sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
        return this;
    }

    public void setSysEmployeeId(String sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
    }

    public String getSysPostId() {
        return sysPostId;
    }

    public SysEmployeePost sysPostId(String sysPostId) {
        this.sysPostId = sysPostId;
        return this;
    }

    public void setSysPostId(String sysPostId) {
        this.sysPostId = sysPostId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysEmployeePost)) {
            return false;
        }
        return id != null && id.equals(((SysEmployeePost) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysEmployeePost{" +
            "id=" + getId() +
            ", sysEmployeeId='" + getSysEmployeeId() + "'" +
            ", sysPostId='" + getSysPostId() + "'" +
            "}";
    }
}
