package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.common.pojo.TreeDTO;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;

import com.ruowei.modules.sys.pojo.SysOfficeTreeDTO;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link SysOffice} and its DTO {@link SysOfficeDTO}.
 * @author 刘东奇
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

    /**
     * 组装TreeDTO
     * @param sysOffice
     * @return TreeDTO
     */
    @Mappings({
        @Mapping(source = "sysOffice.officeName", target = "name"),
        @Mapping(source = "sysOffice.officeCode", target = "code"),
        @Mapping(source = "sysOffice.parentCode", target = "parentCode")
    })
    TreeDTO toTreeDTO(SysOffice sysOffice);

    /**
     * 组装TreeDTO
     * @param sysOfficeList
     * @return List<TreeDTO>
     */
    List<TreeDTO> toTreeDTO( List<SysOffice> sysOfficeList);

    /**
     * 组装TreeDTO
     * @param sysOffice
     * @return TreeDTO
     */
    @Mappings({
        @Mapping(source = "sysOffice.officeName", target = "name"),
        @Mapping(source = "sysOffice.officeCode", target = "code"),
        @Mapping(source = "sysOffice.parentCode", target = "parentCode")
    })
    SysOfficeTreeDTO toSysOfficeTree(SysOffice sysOffice);
}
