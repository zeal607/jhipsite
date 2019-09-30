package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.SysEmployee;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.domain.SysUserRole;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import com.ruowei.modules.sys.pojo.SysUserRoleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysUserRole} and its DTO {@link SysUserRoleDTO}.
 * @author 刘东奇
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysUserRoleMapper extends EntityMapper<SysUserRoleDTO, SysUserRole> {



    default SysUserRole fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(id);
        return sysUserRole;
    }

    /**
     * 组装SysUserRole
     * @param sysRoleDTO
     * @param sysUser
     * @return
     */
    @Mappings({
        @Mapping(target = "id" ,ignore=true),
        @Mapping(source = "sysUser.userCode", target = "sysUserId"),
        @Mapping(source = "sysRoleDTO.roleCode", target = "sysRoleId")
    })
    SysUserRole assembleEntity(SysRoleDTO sysRoleDTO, SysUser sysUser);
}
