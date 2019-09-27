package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysEmployeePost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployeePost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeePostRepository
    extends BaseRepository<SysEmployeePost, Long> {

}
