package com.ruowei.repository;

import com.ruowei.domain.SysCompany;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCompanyRepository extends JpaRepository<SysCompany, Long>, JpaSpecificationExecutor<SysCompany> {

}
