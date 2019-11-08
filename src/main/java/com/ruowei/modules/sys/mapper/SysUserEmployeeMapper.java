package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.SysUserEmployeeDetailVM;
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
    @Mappings({
        @Mapping(source = "sysRoleDTOList", target = "sysRoleList")
    })
    SysUser projectIntoUser(SysUserEmployeeDTO sysUserEmployeeDTO);

    /**
     * 投影到SysEmployee
     * @param sysUserEmployeeDTO
     * @return
     */
    @Mappings({
        @Mapping(source = "sysPostDTOList", target = "sysPostList"),
        @Mapping(source = "sysEmployeeOfficeDTOList", target = "sysEmployeeOfficeList")
    })
    SysEmployee projectIntoEmployee(SysUserEmployeeDTO sysUserEmployeeDTO);

    /**
     * 组装SysUserEmployeeDTO
     * @param sysUser
     * @param sysEmployee
     * @return
     */
    @Mappings({
        @Mapping(source = "sysUser.id", target = "id"),
        @Mapping(source = "sysUser.remarks", target = "remarks"),
        @Mapping(source = "sysEmployee.sysPostList", target = "sysPostDTOList"),
        @Mapping(source = "sysEmployee.sysEmployeeOfficeList", target = "sysEmployeeOfficeDTOList"),
        @Mapping(source = "sysUser.sysRoleList", target = "sysRoleDTOList")
    })
    SysUserEmployeeDTO assembleSysUserEmployeeDTO(SysUser sysUser, SysEmployee sysEmployee);

    /**
     * 投影到SysUserEmployeeDTO
     * @param sysUserEmployeeDetailVM
     * @return
     */
    @Mappings({
        @Mapping(source = "sysPostList", target = "sysPostDTOList"),
        @Mapping(source = "sysEmployeeOfficeList", target = "sysEmployeeOfficeDTOList"),
        @Mapping(source = "sysRoleList", target = "sysRoleDTOList")
    })
    SysUserEmployeeDTO projectIntoSysUserEmployeeDTO(SysUserEmployeeDetailVM sysUserEmployeeDetailVM);


}
