package com.ruowei.service.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.domain.*;
import com.ruowei.service.dto.SysEmployeeOfficeDTO;

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
}
