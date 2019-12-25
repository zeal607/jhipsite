package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.entity.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeDTO;
import com.ruowei.modules.sys.web.vm.SysEmployeeDetailVM;
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
     * @param sysEmployee
     * @return
     */
    com.ruowei.modules.sys.domain.table.SysEmployee SysUserEmployeeDetailVMToSysEmployee(SysEmployee sysEmployee);

    @Mappings({
        @Mapping(source = "sysEmployeeDetailVM.userInfo", target = "user"),
        @Mapping(source = "sysEmployeeDetailVM.officeInfo", target = "office"),
        @Mapping(source = "sysEmployeeDetailVM.companyInfo", target = "company"),
        @Mapping(source = "sysEmployeeDetailVM.postInfoList", target = "postList")
    })
    SysEmployee sysUserEmployeeDetailVMToSysEmployeeDetail(SysEmployeeDetailVM sysEmployeeDetailVM);

    /**
     * 投影到SysUser
     * id忽略，因为ID是SysEmployee的ID
     * @param sysEmployee
     * @return
     */
//    @Mappings({
//        @Mapping(target = "id" ,ignore=true)
//    })
//    SysUser SysUserEmployeeDetailVMToSysUser(SysEmployee sysEmployee);

    /**
     * 组装SysUserEmployeeDTO
     * @param sysUser
     * @param sysEmployee
     * @return
     * TODO
     */
    @Mappings({
        @Mapping(source = "sysUser.id", target = "id"),
        @Mapping(source = "sysUser.remarks", target = "remarks")
    })
    SysUserEmployeeDTO assembleSysUserEmployeeDTO(SysUser sysUser, com.ruowei.modules.sys.domain.table.SysEmployee sysEmployee);

    SysOfficeDTO sysOfficeToSysOfficeDTO(SysOffice sysOffice);

    SysOffice sysOfficeDTOToSysOffice(SysOfficeDTO sysOfficeDTO);

//    @Mappings({
//        @Mapping(source = "sysEmployee.id", target = "id"),
//        @Mapping(source = "sysEmployee.remarks", target = "remarks"),
//        @Mapping(source = "sysEmployee.createdBy", target = "createdBy"),
//        @Mapping(source = "sysEmployee.createdDate", target = "createdDate"),
//        @Mapping(source = "sysEmployee.lastModifiedBy", target = "lastModifiedBy"),
//        @Mapping(source = "sysEmployee.lastModifiedDate", target = "lastModifiedDate")
//    })
//    SysEmployee assembleSysUserEmployeeDetail(SysUser sysUser, com.ruowei.modules.sys.domain.table.SysEmployee sysEmployee);
}
