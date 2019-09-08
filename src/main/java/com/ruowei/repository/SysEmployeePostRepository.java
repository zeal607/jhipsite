package com.ruowei.repository;

import com.ruowei.domain.SysEmployeePost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployeePost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeePostRepository extends JpaRepository<SysEmployeePost, Long>, JpaSpecificationExecutor<SysEmployeePost> {

}
