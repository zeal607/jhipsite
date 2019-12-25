package com.ruowei.modules.sys.domain.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 员工的岗位信息
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeePostInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sys_post表主键
     */
    @Id
    private Long id;

    /**
     * 岗位编码
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "post_code", length = 100, nullable = false, unique = true)
    private String postCode;

    /**
     * 岗位名称
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "post_name", length = 100, nullable = false)
    private String postName;

    @ManyToMany(mappedBy="officePostInfoList")
    private List<SysEmployee> employeeList;


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getPostCode() {
        return postCode;
    }

    public SysEmployeePostInfo postCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public SysEmployeePostInfo postName(String postName) {
        this.postName = postName;
        return this;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SysEmployee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<SysEmployee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeePostInfo that = (SysEmployeePostInfo) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(postCode, that.postCode) &&
            Objects.equals(postName, that.postName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postCode, postName);
    }
}
