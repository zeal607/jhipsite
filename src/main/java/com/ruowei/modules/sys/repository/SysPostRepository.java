package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysPost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysPost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysPostRepository
    extends BaseRepository<SysPost, Long> {

}
