package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysUser;
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

    /**
     * 组装SysUserEmployeeVM
     * @param sysUserDTO
     * @param sysEmployeeDTO
     * @return
     */
    @Mappings({
        @Mapping(source = "sysUserDTO.id", target = "id"),
        @Mapping(source = "sysUserDTO.buildTime", target = "buildTime"),
        @Mapping(source = "sysUserDTO.status", target = "userStatus"),
        @Mapping(source = "sysEmployeeDTO.status", target = "empStatus")
    })
    SysUserEmployeeVM assembleVM(SysUserDTO sysUserDTO, SysEmployeeDTO sysEmployeeDTO);


    /**
     * 投影到SysUser
     * @param sysUserEmployeeDTO
     * @return
     */
    SysUser projectIntoUser(SysUserEmployeeDTO sysUserEmployeeDTO);

    /**
     * 投影到SysEmployee
     * @param sysUserEmployeeDTO
     * @return
     */
    SysEmployee projectIntoEmployee(SysUserEmployeeDTO sysUserEmployeeDTO);
}
