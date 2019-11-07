package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysEmployeeOffice;
import com.ruowei.modules.sys.pojo.SysEmployeeOfficeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysEmployeeOffice} and its DTO {@link SysEmployeeOfficeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysEmployeeOfficeMapper extends EntityMapper<SysEmployeeOfficeDTO, SysEmployeeOffice> {



    default SysEmployeeOffice fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysEmployeeOffice sysEmployeeOffice = new SysEmployeeOffice();
        sysEmployeeOffice.setId(id);
        return sysEmployeeOffice;
    }

    /**
     * 组装SysEmployeeOffice
     * @param sysEmployeeOfficeDTO
     * @param sysEmployee
     * @return
     */
    @Mappings({
        @Mapping(target = "id" ,ignore=true),
        @Mapping(source = "sysEmployee.empCode", target = "sysEmployeeId"),
        @Mapping(source = "sysEmployeeOfficeDTO.sysOfficeId", target = "sysOfficeId")
    })
    SysEmployeeOffice assembleEntity(SysEmployeeOfficeDTO sysEmployeeOfficeDTO, SysEmployee sysEmployee);
}
