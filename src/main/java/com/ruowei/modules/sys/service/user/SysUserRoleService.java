package com.ruowei.modules.sys.service.user;

import com.ruowei.modules.sys.domain.SysUserRole;
import com.ruowei.modules.sys.pojo.SysRoleDTO;

import java.util.List;

/**
 * Service Interface for managing {@link SysUserRole}.
 * @author 刘东奇
 */
public interface SysUserRoleService {
    /**
     * 通过用户id获取角色列表
     * @author 刘东奇
     * @date 2019/9/27
     * @param
     */
    List<SysRoleDTO> getSysRoleDTOListBySysUserId(Long sysUserId);
}
