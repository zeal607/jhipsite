package com.ruowei.modules.sys.web.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户的角色信息
 * @author 刘东奇
 */
@ApiModel(description = "用户的角色信息 @author 刘东奇")
public class SysUserRoleVM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserRoleVM that = (SysUserRoleVM) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(roleCode, that.roleCode) &&
            Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleCode, roleName);
    }

    @Override
    public String toString() {
        return "SysUserRoleVM{" +
            "id=" + id +
            ", roleCode='" + roleCode + '\'' +
            ", roleName='" + roleName + '\'' +
            '}';
    }
}
