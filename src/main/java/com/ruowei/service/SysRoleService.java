package com.ruowei.service;

import com.ruowei.service.dto.SysRoleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysRole}.
 */
public interface SysRoleService {

    /**
     * Save a sysRole.
     *
     * @param sysRoleDTO the entity to save.
     * @return the persisted entity.
     */
    SysRoleDTO save(SysRoleDTO sysRoleDTO);

    /**
     * Get all the sysRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysRoleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysRole.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysRoleDTO> findOne(Long id);

    /**
     * Delete the "id" sysRole.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
