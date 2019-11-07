package com.ruowei.modules.sys.web.rest;

import com.ruowei.modules.sys.domain.table.SysCompany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link SysCompany}.
 */
@RestController
@RequestMapping("/api")
public class SysCompanyResource {

    private final Logger log = LoggerFactory.getLogger(SysCompanyResource.class);

}
