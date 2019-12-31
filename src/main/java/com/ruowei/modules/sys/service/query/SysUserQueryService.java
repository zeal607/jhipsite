package com.ruowei.modules.sys.service.query;

import com.ruowei.common.service.query.simple.QueryService;
import com.ruowei.modules.sys.domain.table.QSysUserTable;
import com.ruowei.modules.sys.domain.table.SysUserTable;
import com.ruowei.modules.sys.repository.SysUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
@Service
@Transactional(readOnly = true)
public class SysUserQueryService extends QueryService<SysUserTable, QSysUserTable, SysUserRepository> {
}
