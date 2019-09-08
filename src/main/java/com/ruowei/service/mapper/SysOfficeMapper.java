package com.ruowei.service.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.domain.*;
import com.ruowei.service.dto.SysOfficeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysOffice} and its DTO {@link SysOfficeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysOfficeMapper extends EntityMapper<SysOfficeDTO, SysOffice> {



    default SysOffice fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysOffice sysOffice = new SysOffice();
        sysOffice.setId(id);
        return sysOffice;
    }
}
