package com.ruowei.service;

import com.ruowei.service.dto.SysUserRoleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysUserRole}.
 */
public interface SysUserRoleService {

    /**
     * Save a sysUserRole.
     *
     * @param sysUserRoleDTO the entity to save.
     * @return the persisted entity.
     */
    SysUserRoleDTO save(SysUserRoleDTO sysUserRoleDTO);

    /**
     * Get all the sysUserRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysUserRoleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysUserRole.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysUserRoleDTO> findOne(Long id);

    /**
     * Delete the "id" sysUserRole.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
