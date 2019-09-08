package com.ruowei.repository;

import com.ruowei.domain.SysEmployeeOffice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployeeOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeOfficeRepository extends JpaRepository<SysEmployeeOffice, Long>, JpaSpecificationExecutor<SysEmployeeOffice> {

}
