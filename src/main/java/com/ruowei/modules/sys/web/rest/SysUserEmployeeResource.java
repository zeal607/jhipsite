package com.ruowei.modules.sys.web.rest;

import com.querydsl.core.types.Predicate;
import com.ruowei.common.response.PaginationUtil;
import com.ruowei.modules.sys.domain.SysUserEmployeeDetailVM;
import com.ruowei.modules.sys.domain.SysUserEmployeeListVM;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.repository.SysUserEmployeeDetailVMRepository;
import com.ruowei.modules.sys.repository.SysUserEmployeeListVMRepository;
import com.ruowei.modules.sys.service.alpha.SysUserEmployeeService;
import com.ruowei.modules.sys.web.api.SysUserEmployeeApi;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

/**
 * REST controller for managing {@link SysUser}.
 * @author 刘东奇
 */
@RestController
@RequestMapping("/api")
public class SysUserEmployeeResource implements SysUserEmployeeApi {

    private final Logger log = LoggerFactory.getLogger(SysUserEmployeeResource.class);

    private final SysUserEmployeeService sysUserEmployeeService;

    private final SysUserEmployeeDetailVMRepository sysUserEmployeeDetailVMRepository;
    private final SysUserEmployeeListVMRepository sysUserEmployeeListVMRepository;

    public SysUserEmployeeResource(SysUserEmployeeService sysUserEmployeeService, SysUserEmployeeDetailVMRepository sysUserEmployeeDetailVMRepository, SysUserEmployeeListVMRepository sysUserEmployeeListVMRepository) {
        this.sysUserEmployeeService = sysUserEmployeeService;
        this.sysUserEmployeeDetailVMRepository = sysUserEmployeeDetailVMRepository;
        this.sysUserEmployeeListVMRepository = sysUserEmployeeListVMRepository;
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
    public ResponseEntity<SysUserEmployeeDetailVM> getSysUserEmployee(@PathVariable Long id) {
        log.debug("REST request to 获取单个员工 : {}", id);
        Optional<SysUserEmployeeDetailVM> one = sysUserEmployeeDetailVMRepository.findById(id);

        one.ifPresent(sysUserEmployeeDetailVM->{            ;
            sysUserEmployeeDetailVM.setSysRoleList(sysUserEmployeeService.getSysRoleListBySysEmployeeId(id));
        });

        return ResponseUtil.wrapOrNotFound(one);
    }

    /**
     * 分页查询员工数据
     *
     * @param sysUserEmployeeLVMPredicate
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
    public ResponseEntity<List<SysUserEmployeeListVM>> getAllSysUserEmployees(Predicate sysUserEmployeeLVMPredicate, Pageable pageable) {
        log.debug("REST request to 查询员工分页 by : {}", sysUserEmployeeLVMPredicate);

        Page<SysUserEmployeeListVM> page = sysUserEmployeeListVMRepository.findAll(sysUserEmployeeLVMPredicate,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,"");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * 创建员工
     *
     * @param sysUserEmployeeDetailVM
     * @return
     * @author 刘东奇
     * @date 2019/11/13
     */
    @Override
    @ApiOperation(value = "创建员工")
    @PostMapping("/sys-user-employees")
    public ResponseEntity<SysUserEmployeeDetailVM> createSysUserEmployee(@Valid @RequestBody SysUserEmployeeDetailVM sysUserEmployeeDetailVM) throws URISyntaxException {
        log.debug("REST request to 创建员工 : {}", sysUserEmployeeDetailVM);
        sysUserEmployeeService.createSysUserEmployee(sysUserEmployeeDetailVM);
        return ResponseEntity.created(new URI("/api/sys-user-employees/" + sysUserEmployeeDetailVM.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("系统内置功能", true, "创建员工", sysUserEmployeeDetailVM.getId().toString()))
            .body(sysUserEmployeeDetailVM);
    }


}
