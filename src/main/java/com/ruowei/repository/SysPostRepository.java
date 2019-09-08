package com.ruowei.repository;

import com.ruowei.domain.SysPost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysPost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysPostRepository extends JpaRepository<SysPost, Long>, JpaSpecificationExecutor<SysPost> {

}
