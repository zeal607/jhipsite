package com.ruowei.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ruowei.domain.SysUserRole} entity.
 */
@ApiModel(description = "用户与角色关联表 @author 刘东奇")
public class SysUserRoleDTO implements Serializable {

    private Long id;

    /**
     * 用户外键
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "用户外键", required = true)
    private String sysUserId;

    /**
     * 角色外键
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "角色外键", required = true)
    private String sysRoleId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysUserRoleDTO sysUserRoleDTO = (SysUserRoleDTO) o;
        if (sysUserRoleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysUserRoleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysUserRoleDTO{" +
            "id=" + getId() +
            ", sysUserId='" + getSysUserId() + "'" +
            ", sysRoleId='" + getSysRoleId() + "'" +
            "}";
    }
}
