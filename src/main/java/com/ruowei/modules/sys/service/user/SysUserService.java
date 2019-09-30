package com.ruowei.modules.sys.service.user;

import com.ruowei.common.service.api.CrudServiceApi;
import com.ruowei.modules.sys.api.SysUserApi;
import com.ruowei.modules.sys.domain.SysUser;

/**
 * @author 刘东奇
 * @date 2019/9/27
 */
public interface SysUserService extends SysUserApi, CrudServiceApi<SysUser,Long> {
}
