package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.SysEmployee;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeVM;
import com.ruowei.modules.sys.pojo.SysEmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper for the entity {@link SysUser} and its DTO {@link SysUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysUserEmployeeMapper{

    @Mappings({
        @Mapping(source = "sysUserDTO.id", target = "id"),
        @Mapping(source = "sysUserDTO.buildTime", target = "buildTime"),
        @Mapping(source = "sysUserDTO.status", target = "userStatus"),
        @Mapping(source = "sysEmployeeDTO.status", target = "empStatus")
    })
    SysUserEmployeeVM converter1(SysUserDTO sysUserDTO, SysEmployeeDTO sysEmployeeDTO);

    SysUser converter2(SysUserEmployeeDTO sysUserEmployeeDTO);

    SysEmployee converter3(SysUserEmployeeDTO sysUserEmployeeDTO);
}
