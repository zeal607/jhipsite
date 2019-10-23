package com.ruowei.service.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.SysRoleMenu;
import com.ruowei.modules.sys.pojo.SysRoleMenuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysRoleMenu} and its DTO {@link SysRoleMenuDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysRoleMenuMapper extends EntityMapper<SysRoleMenuDTO, SysRoleMenu> {



    default SysRoleMenu fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setId(id);
        return sysRoleMenu;
    }
}
