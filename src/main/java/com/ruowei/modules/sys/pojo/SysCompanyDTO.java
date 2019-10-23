package com.ruowei.modules.sys.pojo;
import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.SysCompany;
import com.ruowei.modules.sys.domain.enumeration.CompanyStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SysCompany} entity.
 * @author 刘东奇
 */
@ApiModel(description = "公司表 @author 刘东奇")
public class SysCompanyDTO extends BaseDTO implements Serializable {

    /**
     * 公司编码
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "公司编码", required = true)
    private String companyCode;

    /**
     * 父级编号
     */
    @Size(max = 100)
    @ApiModelProperty(value = "父级编号")
    private String parentCode;

    /**
     * 所有父级编号
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "所有父级编号")
    private String parentCodes;

    /**
     * 本级排序号（升序）
     */
    @ApiModelProperty(value = "本级排序号（升序）")
    private Integer treeSort;

    /**
     * 所有级别排序号
     */
    @ApiModelProperty(value = "所有级别排序号")
    private Integer treeSorts;

    /**
     * 是否最末级
     */
    @NotNull
    @ApiModelProperty(value = "是否最末级", required = true)
    private Boolean treeLeaf;

    /**
     * 层次级别
     */
    @NotNull
    @ApiModelProperty(value = "层次级别", required = true)
    private Integer treeLevel;

    /**
     * 全节点名
     */
    @NotNull
    @Size(max = 1000)
    @ApiModelProperty(value = "全节点名", required = true)
    private String treeNames;

    /**
     * 公司代码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "公司代码")
    private String viewCode;

    /**
     * 公司名称
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "公司名称", required = true)
    private String companyName;

    /**
     * 公司全称
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "公司全称", required = true)
    private String fullName;

    /**
     * 区域编码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "区域编码")
    private String areaCode;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    private CompanyStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCodes() {
        return parentCodes;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
    }

    public Integer getTreeSort() {
        return treeSort;
    }

    public void setTreeSort(Integer treeSort) {
        this.treeSort = treeSort;
    }

    public Integer getTreeSorts() {
        return treeSorts;
    }

    public void setTreeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
    }

    public Boolean isTreeLeaf() {
        return treeLeaf;
    }

    public void setTreeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeNames() {
        return treeNames;
    }

    public void setTreeNames(String treeNames) {
        this.treeNames = treeNames;
    }

    public String getViewCode() {
        return viewCode;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public CompanyStatusType getStatus() {
        return status;
    }

    public void setStatus(CompanyStatusType status) {
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

        SysCompanyDTO sysCompanyDTO = (SysCompanyDTO) o;
        if (sysCompanyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysCompanyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysCompanyDTO{" +
            "id=" + getId() +
            ", companyCode='" + getCompanyCode() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            ", parentCodes='" + getParentCodes() + "'" +
            ", treeSort=" + getTreeSort() +
            ", treeSorts=" + getTreeSorts() +
            ", treeLeaf='" + isTreeLeaf() + "'" +
            ", treeLevel=" + getTreeLevel() +
            ", treeNames='" + getTreeNames() + "'" +
            ", viewCode='" + getViewCode() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", areaCode='" + getAreaCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
