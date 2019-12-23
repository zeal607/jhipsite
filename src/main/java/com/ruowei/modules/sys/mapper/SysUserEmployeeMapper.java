package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.entity.SysEmployeeDetail;
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
     * 投影到SysEmployee
     * @param sysEmployeeDetail
     * @return
     */
    SysEmployee SysUserEmployeeDetailVMToSysEmployee(SysEmployeeDetail sysEmployeeDetail);

    /**
     * 投影到SysUser
     * id忽略，因为ID是SysEmployee的ID
     * @param sysEmployeeDetail
     * @return
     */
    @Mappings({
        @Mapping(target = "id" ,ignore=true)
    })
    SysUser SysUserEmployeeDetailVMToSysUser(SysEmployeeDetail sysEmployeeDetail);

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
    SysUserEmployeeDTO assembleSysUserEmployeeDTO(SysUser sysUser, SysEmployee sysEmployee);

    SysOfficeDTO sysOfficeToSysOfficeDTO(SysOffice sysOffice);

    SysOffice sysOfficeDTOToSysOffice(SysOfficeDTO sysOfficeDTO);

    @Mappings({
        @Mapping(source = "sysEmployee.id", target = "id"),
        @Mapping(source = "sysEmployee.remarks", target = "remarks")
    })
    SysEmployeeDetail assembleSysUserEmployeeDetail(SysUser sysUser, SysEmployee sysEmployee);
}
