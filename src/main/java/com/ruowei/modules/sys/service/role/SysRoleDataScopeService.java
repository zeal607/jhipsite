package com.ruowei.modules.sys.service.role;

import com.ruowei.service.dto.SysRoleDataScopeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysRoleDataScope}.
 */
public interface SysRoleDataScopeService {

    /**
     * Save a sysRoleDataScope.
     *
     * @param sysRoleDataScopeDTO the entity to save.
     * @return the persisted entity.
     */
    SysRoleDataScopeDTO save(SysRoleDataScopeDTO sysRoleDataScopeDTO);

    /**
     * Get all the sysRoleDataScopes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysRoleDataScopeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysRoleDataScope.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysRoleDataScopeDTO> findOne(Long id);

    /**
     * Delete the "id" sysRoleDataScope.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
