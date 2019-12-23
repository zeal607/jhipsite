package com.ruowei.modules.sys.web.rest;

import com.querydsl.core.types.Predicate;
import com.ruowei.modules.sys.domain.entity.SysEmployeeDetail;
import com.ruowei.modules.sys.domain.entity.SysEmployeeList;
import com.ruowei.modules.sys.repository.SysEmployeeDetailRepository;
import com.ruowei.modules.sys.repository.SysEmployeeListRepository;
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
@RequestMapping("/api/sys")
@Api(value = "员工相关" ,tags = "员工相关")
public class SysUserEmployeeResource implements SysUserEmployeeApi {

    private final Logger log = LoggerFactory.getLogger(SysUserEmployeeResource.class);

    private final SysUserEmployeeService sysUserEmployeeService;

    private final SysEmployeeDetailRepository sysEmployeeDetailRepository;
    private final SysEmployeeListRepository sysEmployeeListRepository;

    public SysUserEmployeeResource(SysUserEmployeeService sysUserEmployeeService, SysEmployeeDetailRepository sysEmployeeDetailRepository, SysEmployeeListRepository sysEmployeeListRepository) {
        this.sysUserEmployeeService = sysUserEmployeeService;
        this.sysEmployeeDetailRepository = sysEmployeeDetailRepository;
        this.sysEmployeeListRepository = sysEmployeeListRepository;
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
    @ApiOperation(value = "查询员工分页", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供分页查询员工数据功能")
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
    @GetMapping("/user-employees")
    public ResponseEntity<List<SysEmployeeList>> getAllSysUserEmployees(
        @QuerydslPredicate(root = SysEmployeeList.class) Predicate sysUserEmployeePredicate, Pageable pageable) {
        log.debug("REST request to 查询员工分页 by : {}", sysUserEmployeePredicate);

        Page<SysEmployeeList> page = sysEmployeeListRepository.findAll(sysUserEmployeePredicate,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(),page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
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
    @ApiOperation(value = "获取单个员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页-用户详情页、用户修改页】提供获取单个员工数据功能")
    @GetMapping("/user-employee/{id}")
    public ResponseEntity<SysEmployeeDetail> getSysUserEmployee(@PathVariable Long id) {
        log.debug("REST request to 获取单个员工 : {}", id);
        Optional<SysEmployeeDetail> one = sysEmployeeDetailRepository.findById(id);

//        one.ifPresent(userEmployee->{            ;
//            userEmployee.setSysRoleList(sysUserEmployeeService.getSysRoleListBySysEmployeeId(id));
//        });

        return ResponseUtil.wrapOrNotFound(one);
    }

    /**
     * 创建员工
     *
     * @param sysEmployeeDetail
     * @return
     * @author 刘东奇
     * @date 2019/11/13
     */
    @Override
    @ApiOperation(value = "创建员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页-用户新增页】提供保存单个员工数据功能")
    @PostMapping("/user-employees")
    public ResponseEntity<SysEmployeeDetail> createSysUserEmployee(@Valid @RequestBody SysEmployeeDetail sysEmployeeDetail) throws URISyntaxException {
        log.debug("REST request to 创建员工 : {}", sysEmployeeDetail);
        sysUserEmployeeService.createSysUserEmployee(sysEmployeeDetail);
        return ResponseEntity.created(new URI("/api/sys-user-employees/" + sysEmployeeDetail.getId()))
           .body(sysEmployeeDetail);
    }

    /**
     * 修改员工
     *
     * @param sysEmployeeDetail
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @ApiOperation(value = "修改员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页-用户修改页】提供修改单个员工数据功能")
    @PutMapping("/user-employees")
    public ResponseEntity<SysEmployeeDetail> modifySysUserEmployee(@Valid @RequestBody SysEmployeeDetail sysEmployeeDetail) {
        log.debug("REST request to 修改员工 : {}", sysEmployeeDetail);
        sysEmployeeDetail = sysUserEmployeeService.modifySysUserEmployee(sysEmployeeDetail);
        return ResponseEntity.ok()
            .body(sysEmployeeDetail);
    }


    /**
     * 停用员工
     * @author 刘东奇
     * @date 2019/11/16
     */
    @Override
    @ApiOperation(value = "停用员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供停用单个员工账号功能")
    @PutMapping("/user-employees/disable")
    public ResponseEntity disableSysEmployee(@Valid @RequestBody Long sysEmployeeId) {
        log.debug("REST request to 停用员工 : {}", sysEmployeeId);
        sysUserEmployeeService.disableSysEmployee(sysEmployeeId);
        return ResponseEntity.ok(null);
    }

    /**
     * 启用员工
     * @author 刘东奇
     * @date 2019/11/9
     */
    @Override
    @ApiOperation(value = "启用员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供启用单个员工账号功能")
    @PutMapping("/user-employees/enable")
    public ResponseEntity enableSysEmployee(@Valid @RequestBody Long sysEmployeeId) {
        log.debug("REST request to 启用员工 : {}", sysEmployeeId);
        sysUserEmployeeService.enableSysEmployee(sysEmployeeId);
        return ResponseEntity.ok(null);
    }

    /**
     * 删除员工
     * @author 刘东奇
     * @date 2019/11/2
     */
    @Override
    @ApiOperation(value = "删除员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供删除单个员工功能")
    @DeleteMapping("/user-employees/delete")
    public ResponseEntity deleteSysEmployee(@Valid @RequestBody Long sysEmployeeId) {
        log.debug("REST request to 删除员工 : {}", sysEmployeeId);
        sysUserEmployeeService.deleteSysEmployee(sysEmployeeId);
        return ResponseEntity.ok(null);
    }

    /**
     * 重置员工密码
     * @author 刘东奇
     * @date 2019/11/9
     */
    @Override
    @ApiOperation(value = "重置员工密码", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供重置单个员工密码功能")
    @PutMapping("/user-employees/reset-password")
    public ResponseEntity resetSysEmployeePassword(@Valid @RequestBody Long sysEmployeeId) {
        log.debug("REST request to 重置员工密码 : {}", sysEmployeeId);
        sysUserEmployeeService.resetSysEmployeePassword(sysEmployeeId);
        return ResponseEntity.ok(null);
    }

    /**
     * 员工分配角色
     * @author 刘东奇
     * @date 2019/9/22
     */
    @Override
    @ApiOperation(value = "员工分配角色", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供分配单个员工角色功能")
    @PostMapping("/user-employees/assign-role")
    public ResponseEntity assignRoleToSysEmployee(@Valid @RequestBody AssignRoleVM assignRoleVM) {
        log.debug("REST request to 员工分配角色 : {}", assignRoleVM);
        sysUserEmployeeService.assignRoleToSysEmployee(assignRoleVM.getSysEmployeeId(),assignRoleVM.getRoleIdList());
        return ResponseEntity.ok(null);
    }
}
