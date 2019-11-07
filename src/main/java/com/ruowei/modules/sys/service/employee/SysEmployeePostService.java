package com.ruowei.modules.sys.service.employee;

import com.ruowei.common.service.crud.CrudApi;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysEmployeePost;
import com.ruowei.modules.sys.pojo.SysPostDTO;

import java.util.List;

/**
 * @author 刘东奇
 * @date 2019/9/30
 */
public interface SysEmployeePostService extends CrudApi<SysEmployeePost,Long> {

    /**
     * 保存员工岗位关系
     * @author 刘东奇
     * @date 2019/9/30
     * @param sysEmployee
    * @param sysPostDTOList
     */
    void saveEmployeePostRelationship(SysEmployee sysEmployee,List<SysPostDTO> sysPostDTOList);
}
