package com.ruowei.modules.sys.service.employee;

import com.ruowei.common.service.crud.CrudApi;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysEmployeeOffice;
import com.ruowei.modules.sys.pojo.SysEmployeeOfficeDTO;

import java.util.List;

/**
 * @author 刘东奇
 * @date 2019/9/30
 */
public interface SysEmployeeOfficeService{

    /**
     * 保存员工附属机构及岗位关系
     * @author 刘东奇
     * @date 2019/9/30
     * @param sysEmployee
     * @param sysEmployeeOfficeDTOList
     */
    void saveEmployeeOfficePostRelationship(SysEmployee sysEmployee, List<SysEmployeeOfficeDTO> sysEmployeeOfficeDTOList);
}
