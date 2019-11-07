package com.ruowei.service.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.table.SysMenu;
import com.ruowei.modules.sys.pojo.SysMenuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysMenu} and its DTO {@link SysMenuDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysMenuMapper extends EntityMapper<SysMenuDTO, SysMenu> {



    default SysMenu fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(id);
        return sysMenu;
    }
}
