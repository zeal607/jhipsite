package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysUser;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the SysUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserRepository extends BaseRepository<SysUser,Long> {
    
    /**
     * 通过usercode倒叙找第一个Sysuser
     * @author 刘东奇
     * @date 2019/9/25
     */
    Optional<SysUser> findFirstByUserCodeNotNullOrderByUserCodeDesc();
}
