package com.ruowei.modules.sys.service.role.impl;

import com.ruowei.common.service.crud.CrudBaseService;
import com.ruowei.modules.sys.domain.table.QSysRole;
import com.ruowei.modules.sys.domain.table.SysRole;
import com.ruowei.modules.sys.repository.SysRoleRepository;
import com.ruowei.modules.sys.service.role.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 刘东奇
 */
@Service
@Transactional
public class SysRoleServiceImpl
    extends CrudBaseService<
    SysRole,
    QSysRole,
    SysRoleRepository>
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
