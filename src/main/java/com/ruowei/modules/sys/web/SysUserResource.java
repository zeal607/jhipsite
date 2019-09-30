package com.ruowei.modules.sys.web;

import com.querydsl.core.QueryResults;
import com.ruowei.common.response.PaginationUtil;
import com.ruowei.modules.sys.pojo.SysEmployeeCriteria;
import com.ruowei.modules.sys.pojo.SysUserEmployeeDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeVM;
import com.ruowei.modules.sys.pojo.SysUserCriteria;

import com.ruowei.modules.sys.service.user.SysUserService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ruowei.modules.sys.domain.SysUser}.
 */
@RestController
@RequestMapping("/api")
public class SysUserResource {

    private final Logger log = LoggerFactory.getLogger(SysUserResource.class);

    private static final String ENTITY_NAME = "sysUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysUserService sysUserService;

    public SysUserResource( SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 查询员工分页
     * @author 刘东奇
     * @date 2019/9/16
     * @param sysUserCriteria
     * @param employeeCriteria
     * @param pageable
     */
    @ApiOperation(value = "查询员工分页")
    @GetMapping("/sys-user-employees")
    public ResponseEntity<List<SysUserEmployeeVM>> getAllSysUserEmployees(SysUserCriteria sysUserCriteria, SysEmployeeCriteria employeeCriteria, Pageable pageable) {
        log.debug("REST request to get SysUsers by criteria: {}", sysUserCriteria);
        QueryResults<SysUserEmployeeVM> queryResults = sysUserService.findSysUserEmployeeVMPageByCriteria(sysUserCriteria,employeeCriteria,pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), queryResults);
        return ResponseEntity.ok().headers(headers).body(queryResults.getResults());
    }

    /**
     * 获取单个员工
     * @author 刘东奇
     * @date 2019/9/17
     * @param id
     */
    @ApiOperation(value = "获取单个员工")
    @GetMapping("/sys-user-employee/{id}")
    public ResponseEntity<SysUserEmployeeDTO> getSysUserEmployee(@PathVariable Long id) {
        log.debug("REST request to get SysUser : {}", id);
        Optional<SysUserEmployeeDTO> sysUserEmployeeDTO = sysUserService.getSysUserEmployeeById(id);
        return ResponseUtil.wrapOrNotFound(sysUserEmployeeDTO);
    }

    /**
     * 新增员工
     * @author 刘东奇
     * @date 2019/9/22
     */
    @ApiOperation(value = "新增员工")
    @PostMapping("/sys-user-employees")
    public ResponseEntity<SysUserEmployeeDTO> createSysUserEmployee(@Valid @RequestBody SysUserEmployeeDTO sysUserEmployeeDTO) throws URISyntaxException {
        log.debug("REST request to save SysUserEmployee : {}", sysUserEmployeeDTO);
        if (sysUserEmployeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysEmployee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysUserEmployeeDTO result = sysUserService.createSysUserEmployee(sysUserEmployeeDTO);
        return ResponseEntity.created(new URI("/api/sys-user-employees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

}
