package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysEmployeePost;
import com.ruowei.modules.sys.pojo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author 刘东奇
 * @date 2019/9/22
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysEmployeePostMapper extends EntityMapper<SysEmployeePostDTO, SysEmployeePost> {

    /**
     * 组装SysEmployeePost
     * @param sysPostDTO
     * @param sysEmployee
     * @return
     */
    @Mappings({
        @Mapping(target = "id" ,ignore=true),
        @Mapping(source = "sysEmployee.empCode", target = "sysEmployeeId"),
        @Mapping(source = "sysPostDTO.postCode", target = "sysPostId")
    })
    SysEmployeePost assembleEntity(SysPostDTO sysPostDTO, SysEmployee sysEmployee);

    default SysEmployeePost fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysEmployeePost sysEmployeePost = new SysEmployeePost();
        sysEmployeePost.setId(id);
        return sysEmployeePost;
    }
}
