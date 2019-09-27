package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.domain.*;
import com.ruowei.modules.sys.pojo.SysRoleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysRole} and its DTO {@link SysRoleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysRoleMapper extends EntityMapper<SysRoleDTO, SysRole> {



    default SysRole fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        return sysRole;
    }
}
