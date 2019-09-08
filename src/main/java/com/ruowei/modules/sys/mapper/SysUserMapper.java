package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.pojo.SysUserDTO;
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
