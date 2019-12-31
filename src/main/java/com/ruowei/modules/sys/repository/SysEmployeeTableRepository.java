package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysEmployeeTable;
import com.ruowei.modules.sys.domain.table.SysEmployeeTable;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;
import java.util.Optional;


/**
 * Spring Data  repository for the SysEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeTableRepository
    extends BaseRepository<Long, SysEmployeeTable, QSysEmployeeTable> {
    /**
     * @author 刘东奇
     * @date 2019/9/25
     * @param sysOfficeId
     */
    Optional<SysEmployeeTable> findFirstByEmpCodeIsNotNullAndSysOfficeIdOrderByEmpCodeDesc(@Size(max = 100) String sysOfficeId);

    /**
     * @author 刘东奇
     * @date 2019/9/25
     */
    Optional<SysEmployeeTable> findFirstByEmpCodeNotNullAndSysOfficeIdIsNullOrderByEmpCodeDesc();
}
