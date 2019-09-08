package com.ruowei.service.mapper;

import com.ruowei.domain.*;
import com.ruowei.service.dto.SysEmployeePostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysEmployeePost} and its DTO {@link SysEmployeePostDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysEmployeePostMapper extends EntityMapper<SysEmployeePostDTO, SysEmployeePost> {



    default SysEmployeePost fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysEmployeePost sysEmployeePost = new SysEmployeePost();
        sysEmployeePost.setId(id);
        return sysEmployeePost;
    }
}
