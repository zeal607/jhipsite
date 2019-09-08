package com.ruowei.repository;

import com.ruowei.domain.SysCompanyOffice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysCompanyOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCompanyOfficeRepository extends JpaRepository<SysCompanyOffice, Long>, JpaSpecificationExecutor<SysCompanyOffice> {

}
