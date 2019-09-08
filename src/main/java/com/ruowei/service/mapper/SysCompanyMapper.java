package com.ruowei.service.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.domain.*;
import com.ruowei.service.dto.SysCompanyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysCompany} and its DTO {@link SysCompanyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysCompanyMapper extends EntityMapper<SysCompanyDTO, SysCompany> {



    default SysCompany fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysCompany sysCompany = new SysCompany();
        sysCompany.setId(id);
        return sysCompany;
    }
}
