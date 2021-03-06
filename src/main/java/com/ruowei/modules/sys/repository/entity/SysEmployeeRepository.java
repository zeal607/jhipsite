package com.ruowei.modules.sys.repository.entity;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.entity.QSysEmployee;
import com.ruowei.modules.sys.domain.entity.SysEmployee;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployee entity.
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeRepository
    extends BaseRepository<String, SysEmployee, QSysEmployee> {
}
