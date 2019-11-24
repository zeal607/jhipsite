package com.ruowei.modules.sys.pojo;
import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.entity.SysEmployee;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;

/**
 * A DTO for the {@link SysEmployee} entity.
 */
@ApiModel(description = "员工表 @author 刘东奇")
public class SysEmployeeDTO extends BaseDTO implements Serializable {

    /**
     * 员工编码
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "员工编码", required = true)
    private String empCode;

    /**
     * 员工姓名
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "员工姓名", required = true)
    private String empName;

    /**
     * 英文名
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "英文名")
    private String empNameEn;

    /**
     * 机构ID
     */
    @Size(max = 100)
    @ApiModelProperty(value = "机构ID")
    private String sysOfficeId;

    /**
     * 机构名称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "机构名称")
    private String officeName;

    /**
     * 公司ID
     */
    @Size(max = 200)
    @ApiModelProperty(value = "公司ID")
    private String sysCompanyId;

    /**
     * 公司名称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    private EmployeeStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNameEn() {
        return empNameEn;
    }

    public void setEmpNameEn(String empNameEn) {
        this.empNameEn = empNameEn;
    }

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public EmployeeStatusType getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
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

        SysEmployeeDTO sysEmployeeDTO = (SysEmployeeDTO) o;
        if (sysEmployeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysEmployeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysEmployeeDTO{" +
            "id=" + getId() +
            ", empCode='" + getEmpCode() + "'" +
            ", empName='" + getEmpName() + "'" +
            ", empNameEn='" + getEmpNameEn() + "'" +
            ", sysOfficeId='" + getSysOfficeId() + "'" +
            ", officeName='" + getOfficeName() + "'" +
            ", sysCompanyId='" + getSysCompanyId() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
