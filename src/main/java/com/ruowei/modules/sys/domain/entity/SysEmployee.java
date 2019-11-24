package com.ruowei.modules.sys.domain.entity;
import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 员工表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployee extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编码
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "emp_code", length = 100, nullable = false, unique = true)
    private String empCode;

    /**
     * 员工姓名
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "emp_name", length = 100, nullable = false)
    private String empName;

    /**
     * 英文名
     */
    @Size(max = 1000)
    @Column(name = "emp_name_en", length = 1000)
    private String empNameEn;

    /**
     * 机构ID
     */
    @Column(name = "sys_office_id", length = 20)
    private Long sysOfficeId;

    /**
     * 机构名称
     */
    @Size(max = 100)
    @Column(name = "office_name", length = 100)
    private String officeName;

    /**
     * 公司ID
     */
    @Size(max = 200)
    @Column(name = "sys_company_id", length = 20)
    private Long sysCompanyId;

    /**
     * 公司名称
     */
    @Size(max = 100)
    @Column(name = "company_name", length = 100)
    private String companyName;

    /**
     * 状态
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    /**
     * 所在岗位
     */
    @ApiModelProperty(value = "所在岗位")
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="sys_employee_post",joinColumns={@JoinColumn(name="sys_employee_id", referencedColumnName="id")}
        ,inverseJoinColumns={@JoinColumn(name="sys_post_id", referencedColumnName="id")})
    private List<SysPost> sysPostList;

    /**
     * 附属机构及岗位
     */
    /**
     * 员工和附属机构、岗位的映射关系的配置方法：
     * 方案1（当前方案）：使用Map，SysOffice作为KEY,SysPost作为Value
     * 优点：没有引入中间表实体（sys_employee_office），比较优雅易理解
     * 缺点：由于Map的原因，不支持一个员工在一个机构中有多个岗位
     * @author 刘东奇
     * @date 2019/11/12
     */
    /**
     * 员工和附属机构、岗位的映射关系的配置方法：
     * 方案2：引入中间表实体SysEmployeeOffice，
     * SysEmployee-OneToMany-SysEmployeeOffice，
     * SysEmployeeOffice-ManyToOne-SysEmployee、
     * SysEmployeeOffice-ManyToOne-SysOffice、
     * SysEmployeeOffice-ManyToOne-SysPost、
     * 优点：支持一个员工在一个机构中有多个岗位
     * 缺点：引入了中间表实体（sys_employee_office）不够优雅，查询时存在无限嵌套bug
     * @author 刘东奇
     * @date 2019/11/12
     */
    @ApiModelProperty(value = "附属机构及岗位")
    @ManyToMany
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_employee_office",
        joinColumns = @JoinColumn(name = "sys_employee_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name="sys_post_id", referencedColumnName="id"))
    @MapKeyJoinColumn(name = "sys_office_id")
    @ElementCollection
    private Map<SysOffice,SysPost> sysOfficeSysPostMap;


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getEmpCode() {
        return empCode;
    }

    public SysEmployee empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public SysEmployee empName(String empName) {
        this.empName = empName;
        return this;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNameEn() {
        return empNameEn;
    }

    public SysEmployee empNameEn(String empNameEn) {
        this.empNameEn = empNameEn;
        return this;
    }

    public void setEmpNameEn(String empNameEn) {
        this.empNameEn = empNameEn;
    }

    public Long getSysOfficeId() {
        return sysOfficeId;
    }

    public SysEmployee sysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
        return this;
    }

    public void setSysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public SysEmployee officeName(String officeName) {
        this.officeName = officeName;
        return this;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Long getSysCompanyId() {
        return sysCompanyId;
    }

    public SysEmployee sysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
        return this;
    }

    public void setSysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public SysEmployee companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public EmployeeStatusType getStatus() {
        return status;
    }

    public SysEmployee status(EmployeeStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(EmployeeStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public SysEmployee remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<SysPost> getSysPostList() {
        return sysPostList;
    }

    public void setSysPostList(List<SysPost> sysPostList) {
        this.sysPostList = sysPostList;
    }

    public Map<SysOffice, SysPost> getSysOfficeSysPostMap() {
        return sysOfficeSysPostMap;
    }

    public void setSysOfficeSysPostMap(Map<SysOffice, SysPost> sysOfficeSysPostMap) {
        this.sysOfficeSysPostMap = sysOfficeSysPostMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysEmployee that = (SysEmployee) o;
        return Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(empNameEn, that.empNameEn) &&
            Objects.equals(sysOfficeId, that.sysOfficeId) &&
            Objects.equals(officeName, that.officeName) &&
            Objects.equals(sysCompanyId, that.sysCompanyId) &&
            Objects.equals(companyName, that.companyName) &&
            status == that.status &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(sysPostList, that.sysPostList) &&
            Objects.equals(sysOfficeSysPostMap, that.sysOfficeSysPostMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empCode, empName, empNameEn, sysOfficeId, officeName, sysCompanyId, companyName, status, remarks, sysPostList, sysOfficeSysPostMap);
    }
}
