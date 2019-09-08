package com.ruowei.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ruowei.domain.SysCompanyOffice} entity.
 */
@ApiModel(description = "公司部门关联表 @author 刘东奇")
public class SysCompanyOfficeDTO implements Serializable {

    private Long id;

    /**
     * 公司ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "公司ID", required = true)
    private String sysCompanyId;

    /**
     * 机构ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "机构ID", required = true)
    private String sysOfficeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysCompanyOfficeDTO sysCompanyOfficeDTO = (SysCompanyOfficeDTO) o;
        if (sysCompanyOfficeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysCompanyOfficeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysCompanyOfficeDTO{" +
            "id=" + getId() +
            ", sysCompanyId='" + getSysCompanyId() + "'" +
            ", sysOfficeId='" + getSysOfficeId() + "'" +
            "}";
    }
}
