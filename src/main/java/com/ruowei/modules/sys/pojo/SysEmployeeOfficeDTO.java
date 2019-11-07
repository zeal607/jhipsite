package com.ruowei.modules.sys.pojo;
import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.table.SysEmployeeOffice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SysEmployeeOffice} entity.
 * @author 刘东奇
 */
@ApiModel(description = "员工附属机构关系表 @author 刘东奇")
public class SysEmployeeOfficeDTO extends BaseDTO implements Serializable {

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

    private SysOfficeDTO sysOfficeDTO;

    /**
     * 岗位ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "岗位ID", required = true)
    private String sysPostId;

    private SysPostDTO sysPostDTO;

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

    public SysOfficeDTO getSysOfficeDTO() {
        return sysOfficeDTO;
    }

    public void setSysOfficeDTO(SysOfficeDTO sysOfficeDTO) {
        this.sysOfficeDTO = sysOfficeDTO;
    }

    public SysPostDTO getSysPostDTO() {
        return sysPostDTO;
    }

    public void setSysPostDTO(SysPostDTO sysPostDTO) {
        this.sysPostDTO = sysPostDTO;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysEmployeeOfficeDTO{" +
            "sysEmployeeId='" + sysEmployeeId + '\'' +
            ", sysOfficeId='" + sysOfficeId + '\'' +
            ", sysOfficeDTO=" + sysOfficeDTO +
            ", sysPostId='" + sysPostId + '\'' +
            ", sysPostDTO=" + sysPostDTO +
            ", id=" + id +
            '}';
    }
}
