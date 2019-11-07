package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysEmployeeOffice;
import com.ruowei.modules.sys.pojo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author 刘东奇
 * @date 2019/9/24
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysEmployeeOfficePostMapper {

    /**
     * 组装SysEmployeeOffice
     * @param sysOfficeDTO
     * @param sysPostDTO
     * @param sysEmployee
     * @return
     */
    @Mappings({
        @Mapping(target = "id" ,ignore=true),
        @Mapping(source = "sysEmployee.empCode", target = "sysEmployeeId"),
        @Mapping(source = "sysOfficeDTO.officeCode", target = "sysOfficeId"),
        @Mapping(source = "sysPostDTO.postCode", target = "sysPostId")
    })
    SysEmployeeOffice assembleEntity(SysOfficeDTO sysOfficeDTO, SysPostDTO sysPostDTO, SysEmployee sysEmployee);
}
