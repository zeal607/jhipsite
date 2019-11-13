package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.SysUserEmployeeDetailVM;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.pojo.*;
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
     * @param sysUserEmployeeDetailVM
     * @return
     */
    SysEmployee SysUserEmployeeDetailVMToSysEmployee(SysUserEmployeeDetailVM sysUserEmployeeDetailVM);

    /**
     * 投影到SysUser
     * id忽略，因为ID是SysEmployee的ID
     * @param sysUserEmployeeDetailVM
     * @return
     */
    @Mappings({
        @Mapping(target = "id" ,ignore=true)
    })
    SysUser SysUserEmployeeDetailVMToSysUser(SysUserEmployeeDetailVM sysUserEmployeeDetailVM);

    /**
     * 组装SysUserEmployeeDTO
     * @param sysUser
     * @param sysEmployee
     * @return
     * TODO
     */
    @Mappings({
        @Mapping(source = "sysUser.id", target = "id"),
        @Mapping(source = "sysUser.remarks", target = "remarks"),
//        @Mapping(source = "sysEmployee.sysPostList", target = "sysPostDTOList"),
//        @Mapping(source = "sysEmployee.sysOfficeList", target = "sysOfficeDTOList"),
//        @Mapping(source = "sysUser.sysRoleList", target = "sysRoleDTOList")
    })
    SysUserEmployeeDTO assembleSysUserEmployeeDTO(SysUser sysUser, SysEmployee sysEmployee);

    @Mappings({
        @Mapping(source = "sysPostList", target = "sysPostDTOList")
    })
    SysOfficeDTO sysOfficeToSysOfficeDTO(SysOffice sysOffice);

    @Mappings({
        @Mapping(source = "sysPostDTOList", target = "sysPostList")
    })
    SysOffice sysOfficeDTOToSysOffice(SysOfficeDTO sysOfficeDTO);
}
