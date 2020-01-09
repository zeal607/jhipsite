package com.ruowei.modules.sys.web.rest;

import com.ruowei.modules.sys.domain.table.SysOffice;

import com.ruowei.modules.sys.web.api.SysOfficeApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link SysOffice}.
 * @author 刘东奇
 */
@RestController
@RequestMapping("/api")
public class SysOfficeResource implements SysOfficeApi {

    private final Logger log = LoggerFactory.getLogger(SysOfficeResource.class);

    /**
     * 根据根节点获取全部节点
     * @author 刘东奇
     * @date 2019/11/16
     * @param rootId
     * @return
     */
    @Override
    @GetMapping("/sys-office/all")
    public ResponseEntity getSysOfficeAllNodesByRoot(@PathVariable String rootId) {
        return null;
    }

    /**
     * 根据根节点获取直接子节点
     * @author 刘东奇
     * @date 2019/11/16
     * @param rootId
     * @return
     */
    @Override
    @GetMapping("/sys-office/direct")
    public ResponseEntity getSysOfficeDirectNodesByRoot(@PathVariable String rootId) {
        return null;
    }
}
