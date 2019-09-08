package com.ruowei.common.service;

import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.service.SysUserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 刘东奇
 * @date 2019/9/8
 */
class CrudServiceTest {

    @Test
    void delete() {
        SysUserService sysUserService=new SysUserService();
        sysUserService.delete(1l);
    }
}
