package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeVM;
import com.ruowei.service.dto.SysEmployeeDTO;
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
        @Mapping(source = "sysUserDTO.status", target = "userStatus"),
        @Mapping(source = "sysEmployeeDTO.status", target = "empStatus")
    })
    SysUserEmployeeVM converter(SysUserDTO sysUserDTO, SysEmployeeDTO sysEmployeeDTO);
}
