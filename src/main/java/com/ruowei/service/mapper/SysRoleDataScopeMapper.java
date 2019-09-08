package com.ruowei.service.mapper;

import com.ruowei.domain.*;
import com.ruowei.service.dto.SysRoleDataScopeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysRoleDataScope} and its DTO {@link SysRoleDataScopeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysRoleDataScopeMapper extends EntityMapper<SysRoleDataScopeDTO, SysRoleDataScope> {



    default SysRoleDataScope fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysRoleDataScope sysRoleDataScope = new SysRoleDataScope();
        sysRoleDataScope.setId(id);
        return sysRoleDataScope;
    }
}
