package com.ruowei.service.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.ruowei.domain.enumeration.ControlType;

/**
 * A DTO for the {@link com.ruowei.domain.SysUserDataScope} entity.
 */
@ApiModel(description = "用户数据权限表 @author 刘东奇")
public class SysUserDataScopeDTO implements Serializable {

    private Long id;

    /**
     * 控制用户ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "控制用户ID", required = true)
    private String sysUserId;

    /**
     * 控制类型
     */
    @NotNull
    @ApiModelProperty(value = "控制类型", required = true)
    private ControlType ctrlType;

    /**
     * 控制数据，如控制类型为公司，那么这里就是公司编号
     */
    @Size(max = 100)
    @ApiModelProperty(value = "控制数据，如控制类型为公司，那么这里就是公司编号")
    private String ctrlData;

    /**
     * 控制权限 ???????????????????
     */
    @Size(max = 100)
    @ApiModelProperty(value = "控制权限 ???????????????????")
    private String ctrlPermi;


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

    public ControlType getCtrlType() {
        return ctrlType;
    }

    public void setCtrlType(ControlType ctrlType) {
        this.ctrlType = ctrlType;
    }

    public String getCtrlData() {
        return ctrlData;
    }

    public void setCtrlData(String ctrlData) {
        this.ctrlData = ctrlData;
    }

    public String getCtrlPermi() {
        return ctrlPermi;
    }

    public void setCtrlPermi(String ctrlPermi) {
        this.ctrlPermi = ctrlPermi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysUserDataScopeDTO sysUserDataScopeDTO = (SysUserDataScopeDTO) o;
        if (sysUserDataScopeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysUserDataScopeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysUserDataScopeDTO{" +
            "id=" + getId() +
            ", sysUserId='" + getSysUserId() + "'" +
            ", ctrlType='" + getCtrlType() + "'" +
            ", ctrlData='" + getCtrlData() + "'" +
            ", ctrlPermi='" + getCtrlPermi() + "'" +
            "}";
    }
}
