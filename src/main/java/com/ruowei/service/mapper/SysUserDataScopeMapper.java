package com.ruowei.service.mapper;

import com.ruowei.domain.*;
import com.ruowei.service.dto.SysUserDataScopeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysUserDataScope} and its DTO {@link SysUserDataScopeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysUserDataScopeMapper extends EntityMapper<SysUserDataScopeDTO, SysUserDataScope> {



    default SysUserDataScope fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysUserDataScope sysUserDataScope = new SysUserDataScope();
        sysUserDataScope.setId(id);
        return sysUserDataScope;
    }
}
