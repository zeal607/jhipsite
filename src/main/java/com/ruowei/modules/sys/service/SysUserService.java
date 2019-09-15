package com.ruowei.modules.sys.service;

import com.querydsl.core.QueryResults;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.api.SysUserApi;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.pojo.SysEmployeeCriteria;
import com.ruowei.modules.sys.pojo.SysUserCriteria;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeVM;
import com.ruowei.modules.sys.repository.SysUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysUserService
    extends CrudBaseService<SysUser, Long, SysUserCriteria, SysUserEmployeeVM, SysUserDTO, SysUserRepository>
    implements SysUserApi {

    private final Logger log = LoggerFactory.getLogger(SysUserService.class);

    private final SysUserQueryService sysUserQueryService;

    public SysUserService(SysUserQueryService sysUserQueryService) {
        this.sysUserQueryService = sysUserQueryService;
    }

    /**
     * 通过条件分页查询机构员工
     *
     * @param userCriteria
     * @param employeeCriteria
     * @param page
     * @author 刘东奇
     * @date 2019/9/8
     */
    @Override
    public QueryResults<SysUserEmployeeVM> findSysUserEmployeeVMPageByCriteria(SysUserCriteria userCriteria, SysEmployeeCriteria employeeCriteria, Pageable page) {
        QueryResults<SysUserEmployeeVM> results = sysUserQueryService.findSysUserEmployeeVMPageByCriteria(userCriteria,employeeCriteria,page);
        return results;
    }



}
