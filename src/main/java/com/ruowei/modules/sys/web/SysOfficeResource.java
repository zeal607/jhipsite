package com.ruowei.modules.sys.web;

import com.ruowei.common.pojo.TreeDTO;
import com.ruowei.modules.sys.domain.SysOffice;

import com.ruowei.modules.sys.service.office.SysOfficeService;
import com.ruowei.modules.sys.service.user.SysUserService;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * 获取机构树
     * @author 刘东奇
     * @date 2019/10/6
     * @return
     */
    @ApiOperation(value = "获取机构树")
    @GetMapping("/sys-office/tree")
    public ResponseEntity<TreeDTO> getOfficeTree() {
        TreeDTO result = sysOfficeService.getOfficeTree();
       return   ResponseUtil.wrapOrNotFound( Optional.ofNullable(result));
    }
}
