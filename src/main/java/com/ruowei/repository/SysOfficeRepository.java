package com.ruowei.repository;

import com.ruowei.domain.SysOffice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysOfficeRepository extends JpaRepository<SysOffice, Long>, JpaSpecificationExecutor<SysOffice> {

}
