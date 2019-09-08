package com.ruowei.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ruowei.domain.SysEmployeeOffice} entity.
 */
@ApiModel(description = "员工附属机构关系表 @author 刘东奇")
public class SysEmployeeOfficeDTO implements Serializable {

    private Long id;

    /**
     * 员工ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "员工ID", required = true)
    private String sysEmployeeId;

    /**
     * 机构ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "机构ID", required = true)
    private String sysOfficeId;

    /**
     * 岗位ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "岗位ID", required = true)
    private String sysPostId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysEmployeeId() {
        return sysEmployeeId;
    }

    public void setSysEmployeeId(String sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
    }

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getSysPostId() {
        return sysPostId;
    }

    public void setSysPostId(String sysPostId) {
        this.sysPostId = sysPostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysEmployeeOfficeDTO sysEmployeeOfficeDTO = (SysEmployeeOfficeDTO) o;
        if (sysEmployeeOfficeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysEmployeeOfficeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysEmployeeOfficeDTO{" +
            "id=" + getId() +
            ", sysEmployeeId='" + getSysEmployeeId() + "'" +
            ", sysOfficeId='" + getSysOfficeId() + "'" +
            ", sysPostId='" + getSysPostId() + "'" +
            "}";
    }
}
