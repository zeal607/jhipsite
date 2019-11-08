package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysUserEmployeeDetailVM;
import com.ruowei.modules.sys.domain.SysUserEmployeeDetailVM;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserEmployeeDetailVMRepository
    extends BaseRepository<Long, SysUserEmployeeDetailVM, QSysUserEmployeeDetailVM> {
}
