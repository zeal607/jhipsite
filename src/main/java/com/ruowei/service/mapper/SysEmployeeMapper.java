package com.ruowei.service.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.pojo.SysEmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysEmployee} and its DTO {@link SysEmployeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysEmployeeMapper extends EntityMapper<SysEmployeeDTO, SysEmployee> {



    default SysEmployee fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysEmployee sysEmployee = new SysEmployee();
        sysEmployee.setId(id);
        return sysEmployee;
    }
}
