package com.ruowei.service.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.domain.*;
import com.ruowei.service.dto.SysCompanyOfficeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysCompanyOffice} and its DTO {@link SysCompanyOfficeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysCompanyOfficeMapper extends EntityMapper<SysCompanyOfficeDTO, SysCompanyOffice> {



    default SysCompanyOffice fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysCompanyOffice sysCompanyOffice = new SysCompanyOffice();
        sysCompanyOffice.setId(id);
        return sysCompanyOffice;
    }
}
