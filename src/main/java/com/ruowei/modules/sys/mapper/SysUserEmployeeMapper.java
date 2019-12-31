package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.entity.SysEmployee;
import com.ruowei.modules.sys.domain.entity.SysUser;
import com.ruowei.modules.sys.domain.table.*;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import com.ruowei.modules.sys.web.vm.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper for the entity {@link SysUserTable} and its DTO {@link SysUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysUserEmployeeMapper{

    /**
     * 投影到SysEmployee
     * @param sysEmployee
     * @return
     */
    SysEmployeeTable SysUserEmployeeDetailVMToSysEmployee(SysEmployee sysEmployee);

    @Mappings({
        @Mapping(source = "sysEmployeeDetailVM.userInfo", target = "user"),
        @Mapping(source = "sysEmployeeDetailVM.officeInfo", target = "office"),
        @Mapping(source = "sysEmployeeDetailVM.companyInfo", target = "company"),
        @Mapping(source = "sysEmployeeDetailVM.postInfoList", target = "postList"),
        @Mapping(source = "sysEmployeeDetailVM.officePostInfoList", target = "officePostList")
    })
    SysEmployee sysUserEmployeeDetailVMToSysEmployee(SysEmployeeDetailVM sysEmployeeDetailVM);

    @Mappings({
        @Mapping(source = "sysEmployee.user", target = "userInfo"),
        @Mapping(source = "sysEmployee.office", target = "officeInfo"),
        @Mapping(source = "sysEmployee.company", target = "companyInfo"),
        @Mapping(source = "sysEmployee.postList", target = "postInfoList"),
        @Mapping(source = "sysEmployee.officePostList", target = "officePostInfoList")
    })
    SysEmployeeDetailVM sysEmployeeToSysEmployeeDetailVM(SysEmployee sysEmployee);

    SysEmployeeUserVM sysUserToSysEmployeeUserVM(SysUser sysUser);

    SysEmployeeOfficeVM sysOfficeToSysEmployeeOfficeVM(SysOffice sysOffice);

    SysEmployeeCompanyVM sysCompanyToSysEmployeeCompanyVM(SysCompany sysCompany);

    List<SysEmployeePostVM> sysPostToSysEmployeePostVM(List<SysPost> sysPostList);

    @Mappings({
        @Mapping(source = "sysEmployee.user", target = "userInfo"),
        @Mapping(source = "sysEmployee.user.lastModifiedDate", target = "lastModifiedDate"),
        @Mapping(source = "sysEmployee.office", target = "officeInfo"),
        @Mapping(source = "sysEmployee.company", target = "companyInfo"),
        @Mapping(source = "sysEmployee.postList", target = "postInfoList")
    })
    SysEmployeeListVM sysEmployeeToSysEmployeeListVM(SysEmployee sysEmployee);

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
     * @param sysUserTable
     * @param sysEmployee
     * @return
     * TODO
     */
//    @Mappings({
//        @Mapping(source = "sysUser.id", target = "id"),
//        @Mapping(source = "sysUser.remarks", target = "remarks")
//    })
//    SysUserEmployeeDTO assembleSysUserEmployeeDTO(SysUserTable sysUserTable, com.ruowei.modules.sys.domain.table.SysEmployee sysEmployee);

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
