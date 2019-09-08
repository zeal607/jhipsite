package com.ruowei.service;

import com.ruowei.service.dto.SysUserDataScopeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysUserDataScope}.
 */
public interface SysUserDataScopeService {

    /**
     * Save a sysUserDataScope.
     *
     * @param sysUserDataScopeDTO the entity to save.
     * @return the persisted entity.
     */
    SysUserDataScopeDTO save(SysUserDataScopeDTO sysUserDataScopeDTO);

    /**
     * Get all the sysUserDataScopes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysUserDataScopeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysUserDataScope.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysUserDataScopeDTO> findOne(Long id);

    /**
     * Delete the "id" sysUserDataScope.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
