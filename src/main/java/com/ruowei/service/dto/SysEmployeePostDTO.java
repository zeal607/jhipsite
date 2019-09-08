package com.ruowei.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ruowei.domain.SysEmployeePost} entity.
 */
@ApiModel(description = "员工与岗位关联表 @author 刘东奇")
public class SysEmployeePostDTO implements Serializable {

    private Long id;

    /**
     * 员工ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "员工ID", required = true)
    private String sysEmployeeId;

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

        SysEmployeePostDTO sysEmployeePostDTO = (SysEmployeePostDTO) o;
        if (sysEmployeePostDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysEmployeePostDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysEmployeePostDTO{" +
            "id=" + getId() +
            ", sysEmployeeId='" + getSysEmployeeId() + "'" +
            ", sysPostId='" + getSysPostId() + "'" +
            "}";
    }
}
