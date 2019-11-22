package com.ruowei.modules.sys.web.rest;

import com.querydsl.core.types.Predicate;
import com.ruowei.modules.sys.domain.SysUserEmployeeDetail;
import com.ruowei.modules.sys.domain.SysUserEmployeeList;
import com.ruowei.modules.sys.domain.enumeration.TestEnum;
import com.ruowei.modules.sys.repository.SysUserEmployeeDetailRepository;
import com.ruowei.modules.sys.repository.SysUserEmployeeListRepository;
import com.ruowei.modules.sys.service.SysUserEmployeeService;
import com.ruowei.modules.sys.web.api.SysUserEmployeeApi;
import com.ruowei.modules.sys.web.vm.AssignRoleVM;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for 员工相关
 * @author 刘东奇
 */
@RestController
@RequestMapping("/api")
@Api(value = "员工相关" ,tags = "员工相关")
public class SysUserEmployeeResource implements SysUserEmployeeApi {

    private final Logger log = LoggerFactory.getLogger(SysUserEmployeeResource.class);

    private final SysUserEmployeeService sysUserEmployeeService;

    private final SysUserEmployeeDetailRepository sysUserEmployeeDetailRepository;
    private final SysUserEmployeeListRepository sysUserEmployeeListRepository;

    public SysUserEmployeeResource(SysUserEmployeeService sysUserEmployeeService, SysUserEmployeeDetailRepository sysUserEmployeeDetailRepository, SysUserEmployeeListRepository sysUserEmployeeListRepository) {
        this.sysUserEmployeeService = sysUserEmployeeService;
        this.sysUserEmployeeDetailRepository = sysUserEmployeeDetailRepository;
        this.sysUserEmployeeListRepository = sysUserEmployeeListRepository;
    }

    /**
     * 通过员工主键获取员工详情
     *
     * @param id
     * @return
     * @author 刘东奇
     * @date 2019/11/13
     */
    @Override
    @ApiOperation(value = "获取单个员工")
    @GetMapping("/sys-user-employee/{id}")
    public ResponseEntity<SysUserEmployeeDetail> getSysUserEmployee(@PathVariable Long id) {
        log.debug("REST request to 获取单个员工 : {}", id);
        Optional<SysUserEmployeeDetail> one = sysUserEmployeeDetailRepository.findById(id);

        one.ifPresent(userEmployee->{            ;
            userEmployee.setSysRoleList(sysUserEmployeeService.getSysRoleListBySysEmployeeId(id));
        });

        return ResponseUtil.wrapOrNotFound(one);
    }

    /**
     * 分页查询员工数据
     *
     * @param sysUserEmployeePredicate
     * @param pageable
     * @return
     * @author 刘东奇
     * @date 2019/11/13
     */
    @Override
    @ApiOperation(value = "查询员工分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value = "主键", paramType = "query", dataType="Long"),
        @ApiImplicitParam(name="loginCode", value = "登录账号", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="userName", value = "用户昵称", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="email", value = "电子邮箱", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="mobile", value = "手机号码", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="phone", value = "办公电话", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="userType", value = "用户类型", paramType = "query", dataType="UserType"),
        @ApiImplicitParam(name="lastModifiedDate", value = "更新时间", paramType = "query", dataType="Instant"),
        @ApiImplicitParam(name="status", value = "用户状态", paramType = "query", dataType="UserStatusType"),
        @ApiImplicitParam(name="empName", value = "员工姓名", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="sysOfficeId", value = "机构ID", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="sysCompanyId", value = "公司ID", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="sysPostList", value = "岗位ID", paramType = "query", dataType="String")
    })
    @GetMapping("/sys-user-employees")
    public ResponseEntity<List<SysUserEmployeeList>> getAllSysUserEmployees(
        @QuerydslPredicate(root = SysUserEmployeeList.class) Predicate sysUserEmployeePredicate, Pageable pageable) {
        log.debug("REST request to 查询员工分页 by : {}", sysUserEmployeePredicate);

        Page<SysUserEmployeeList> page = sysUserEmployeeListRepository.findAll(sysUserEmployeePredicate,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(),page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * 创建员工
     *
     * @param sysUserEmployeeDetail
     * @return
     * @author 刘东奇
     * @date 2019/11/13
     */
    @Override
    @ApiOperation(value = "创建员工")
    @PostMapping("/sys-user-employees")
    public ResponseEntity<SysUserEmployeeDetail> createSysUserEmployee(@Valid @RequestBody SysUserEmployeeDetail sysUserEmployeeDetail) throws URISyntaxException {
        log.debug("REST request to 创建员工 : {}", sysUserEmployeeDetail);
        sysUserEmployeeService.createSysUserEmployee(sysUserEmployeeDetail);
        return ResponseEntity.created(new URI("/api/sys-user-employees/" + sysUserEmployeeDetail.getId()))
           .body(sysUserEmployeeDetail);
    }

    /**
     * 修改员工
     *
     * @param sysUserEmployeeDetail
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @ApiOperation(value = "修改员工")
    @PutMapping("/sys-user-employees")
    public ResponseEntity<SysUserEmployeeDetail> modifySysUserEmployee(@Valid SysUserEmployeeDetail sysUserEmployeeDetail) {
        log.debug("REST request to 修改员工 : {}", sysUserEmployeeDetail);
        sysUserEmployeeService.modifySysUserEmployee(sysUserEmployeeDetail);
        return ResponseEntity.ok()
            .body(sysUserEmployeeDetail);
    }


    /**
     * 停用员工
     * @author 刘东奇
     * @date 2019/11/16
     */
    @Override
    @ApiOperation(value = "停用员工")
    @PutMapping("/sys-user-employees/disable")
    public ResponseEntity disableSysEmployee(@Valid @RequestBody Long sysEmployeeId) {
        log.debug("REST request to 停用用户 : {}", sysEmployeeId);
        sysUserEmployeeService.disableSysEmployee(sysEmployeeId);
        return ResponseEntity.ok(null);
    }

    /**
     * 启用员工
     * @author 刘东奇
     * @date 2019/11/9
     */
    @Override
    @ApiOperation(value = "启用员工")
    @PutMapping("/sys-user-employees/enable")
    public ResponseEntity enableSysEmployee(@Valid @RequestBody Long sysEmployeeId) {
        sysUserEmployeeService.enableSysEmployee(sysEmployeeId);
        return ResponseEntity.ok(null);
    }

    /**
     * 删除员工
     * @author 刘东奇
     * @date 2019/11/2
     */
    @Override
    @ApiOperation(value = "删除员工")
    @PutMapping("/sys-user-employees/delete")
    public ResponseEntity deleteSysEmployee(@Valid @RequestBody Long sysEmployeeId) {
        sysUserEmployeeService.deleteSysEmployee(sysEmployeeId);
        return ResponseEntity.ok(null);
    }

    /**
     * 重置员工密码
     * @author 刘东奇
     * @date 2019/11/9
     */
    @Override
    @ApiOperation(value = "重置员工密码")
    @PutMapping("/sys-user-employees/reset-password")
    public ResponseEntity resetSysEmployeePassword(@Valid @RequestBody Long sysEmployeeId) {
        sysUserEmployeeService.resetSysEmployeePassword(sysEmployeeId);
        return ResponseEntity.ok(null);
    }

    /**
     * 员工分配角色
     * @author 刘东奇
     * @date 2019/9/22
     */
    @Override
    @ApiOperation(value = "员工分配角色")
    @PostMapping("/sys-user-employees/assign-role")
    public ResponseEntity assignRoleToSysEmployee(@Valid @RequestBody AssignRoleVM assignRoleVM) {
        sysUserEmployeeService.assignRoleToSysEmployee(assignRoleVM.getSysEmployeeId(),assignRoleVM.getRoleIdList());
        return ResponseEntity.ok(null);
    }

    /**
     * 通过员工主键获取员工详情
     *
     * @param id
     * @return
     * @author 刘东奇
     * @date 2019/11/13
     */
    @ApiOperation(value = "测试")
    @GetMapping("/test")
    public ResponseEntity getSysUserEmployee( Test2 test2) {
        log.debug("REST request to \"测试\" : {}", test2.getTestEnum());
        return ResponseEntity.ok(test2);
    }


}
