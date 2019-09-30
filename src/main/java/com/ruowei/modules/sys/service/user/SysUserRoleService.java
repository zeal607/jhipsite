package com.ruowei.modules.sys.service.user;

import com.ruowei.common.service.api.CrudServiceApi;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.domain.SysUserRole;
import com.ruowei.modules.sys.pojo.SysRoleDTO;

import java.util.List;

/**
 * Service Interface for managing {@link SysUserRole}.
 * @author 刘东奇
 */
public interface SysUserRoleService extends CrudServiceApi<SysUserRole,Long>{

    /**
     * 保存角色关系
     * @author 刘东奇
     * @date 2019/9/30
     * @param sysUser
     * @param sysRoleDTOList
     */
    void saveUserRoleRelationship(SysUser sysUser,List<SysRoleDTO> sysRoleDTOList);
}
