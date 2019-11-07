package com.ruowei.modules.sys.web.rest;

import com.ruowei.common.pojo.BaseTree;
import com.ruowei.modules.sys.domain.table.SysOffice;

import com.ruowei.modules.sys.pojo.*;
import com.ruowei.modules.sys.service.office.SysOfficeService;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link SysOffice}.
 */
@RestController
@RequestMapping("/api")
public class SysOfficeResource {

    private final Logger log = LoggerFactory.getLogger(SysOfficeResource.class);

    private final SysOfficeService sysOfficeService;

    public SysOfficeResource(SysOfficeService sysOfficeService) {
        this.sysOfficeService = sysOfficeService;
    }


    /**
     * 一次性获取整个机构树
     * @author 刘东奇
     * @date 2019/10/6
     * @return
     */
    @ApiOperation(value = "一次性获取整个机构树")
    @GetMapping("/sys-office/tree")
    public ResponseEntity<BaseTree> getEntireOfficeTreeAtOnce() {
        BaseTree result = sysOfficeService.getEntireOfficeTreeAtOnce();
        return ResponseUtil.wrapOrNotFound( Optional.ofNullable(result));
    }

    /**
     * 一次性获取某机构及其下属构成的树
     * @author 刘东奇
     * @date 2019/10/18
     * @return
     */
    @ApiOperation(value = "一次性获取某机构及其下属构成的树")
    @GetMapping("/sys-office/tree/parentCode={parentCode}")
    public ResponseEntity<BaseTree> getOfficeTreeAtOnce(@PathVariable String parentCode) {
        BaseTree result = sysOfficeService.getOfficeTreeAtOnce(parentCode);
        return ResponseUtil.wrapOrNotFound( Optional.ofNullable(result));
    }

    /**
     * 查询机构树
     * @author 刘东奇
     * @date 2019/10/18
     * @param sysOfficeCriteria
     */
    @ApiOperation(value = "查询机构树")
    @GetMapping("/sys-offices")
    public ResponseEntity<List<SysOfficeDTO>> findSysOfficeTreeVMByCriteria(SysOfficeCriteria sysOfficeCriteria) {
        log.debug("REST request to get SysUsers by criteria: {}", sysOfficeCriteria);

        List<SysOfficeDTO> sysOfficeDTOList = sysOfficeService.findSysOfficeTreeVMByCriteria(sysOfficeCriteria);
        return ResponseEntity.ok().headers(null).body(sysOfficeDTOList);
    }
}
