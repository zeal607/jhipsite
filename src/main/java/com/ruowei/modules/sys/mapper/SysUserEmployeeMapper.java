package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.SysUserEmployeeDetail;
import com.ruowei.modules.sys.domain.entity.SysEmployee;
import com.ruowei.modules.sys.domain.entity.SysOffice;
import com.ruowei.modules.sys.domain.entity.SysUser;
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
     * 投影到SysEmployee
     * @param sysUserEmployeeDetail
     * @return
     */
    SysEmployee SysUserEmployeeDetailVMToSysEmployee(SysUserEmployeeDetail sysUserEmployeeDetail);

    /**
     * 投影到SysUser
     * id忽略，因为ID是SysEmployee的ID
     * @param sysUserEmployeeDetail
     * @return
     */
    @Mappings({
        @Mapping(target = "id" ,ignore=true)
    })
    SysUser SysUserEmployeeDetailVMToSysUser(SysUserEmployeeDetail sysUserEmployeeDetail);

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

    @Mappings({
        @Mapping(source = "sysEmployee.id", target = "id"),
        @Mapping(source = "sysEmployee.remarks", target = "remarks")
    })
    SysUserEmployeeDetail assembleSysUserEmployeeDetail(SysUser sysUser,SysEmployee sysEmployee);
}
