package com.ruowei.modules.sys.web.api;

import com.querydsl.core.types.Predicate;
import com.ruowei.modules.sys.domain.SysUserEmployeeDetailVM;
import com.ruowei.modules.sys.domain.SysUserEmployeeListVM;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author 刘东奇
 * @date 2019/11/9
 */
public interface SysUserEmployeeApi {
    /**
     * 通过员工主键获取员工详情
     * @author 刘东奇
     * @date 2019/11/13
     * @param id
     * @return
     */
    ResponseEntity<SysUserEmployeeDetailVM> getSysUserEmployee(@PathVariable Long id);

    /**
     * 分页查询员工数据
     * @author 刘东奇
     * @date 2019/11/13
     * @param sysUserEmployeeLVMPredicate
     * @param pageable
     * @return
     */
    ResponseEntity<List<SysUserEmployeeListVM>> getAllSysUserEmployees(
        @QuerydslPredicate(root = SysUserEmployeeListVM.class) Predicate sysUserEmployeeLVMPredicate,
        Pageable pageable);

    /**
     * 创建员工
     * @author 刘东奇
     * @date 2019/11/13
     * @param sysUserEmployeeDetailVM
     * @return
     */
    ResponseEntity<SysUserEmployeeDetailVM> createSysUserEmployee(@Valid @RequestBody SysUserEmployeeDetailVM sysUserEmployeeDetailVM) throws URISyntaxException;


}
