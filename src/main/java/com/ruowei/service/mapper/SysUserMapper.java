package com.ruowei.service.mapper;

import com.ruowei.domain.*;
import com.ruowei.service.dto.SysUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysUser} and its DTO {@link SysUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysUserMapper extends EntityMapper<SysUserDTO, SysUser> {



    default SysUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        return sysUser;
    }
}
