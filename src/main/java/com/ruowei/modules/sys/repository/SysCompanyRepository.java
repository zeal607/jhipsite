package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysCompany;
import com.ruowei.modules.sys.domain.table.SysCompany;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysCompany entity.
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysCompanyRepository
    extends BaseRepository<Long, SysCompany, QSysCompany> {

}
