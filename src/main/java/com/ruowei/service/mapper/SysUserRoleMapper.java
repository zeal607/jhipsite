package com.ruowei.service.mapper;

import com.ruowei.domain.*;
import com.ruowei.service.dto.SysUserRoleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysUserRole} and its DTO {@link SysUserRoleDTO}.
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
}
