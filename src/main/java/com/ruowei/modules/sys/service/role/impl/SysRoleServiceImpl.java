package com.ruowei.modules.sys.service.role.impl;

import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.SysRole;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import com.ruowei.modules.sys.repository.SysRoleRepository;
import com.ruowei.modules.sys.service.role.SysRoleService;
import com.ruowei.modules.sys.service.user.impl.SysUserRoleQueryService;
import com.ruowei.modules.sys.service.user.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 刘东奇
 */
@Service
@Transactional
public class SysRoleServiceImpl
    extends CrudBaseService<SysRole, Long, SysRoleRepository>
    implements SysRoleService {

    private final Logger log = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    /**
     * service依赖
     */
    private final SysRoleQueryService sysRoleQueryService;

    public SysRoleServiceImpl(SysRoleQueryService sysRoleQueryService){
        this.sysRoleQueryService = sysRoleQueryService;
    }
}
