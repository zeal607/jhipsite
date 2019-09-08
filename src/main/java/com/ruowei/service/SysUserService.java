package com.ruowei.service;

import com.ruowei.service.dto.SysUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysUser}.
 */
public interface SysUserService {

    /**
     * Save a sysUser.
     *
     * @param sysUserDTO the entity to save.
     * @return the persisted entity.
     */
    SysUserDTO save(SysUserDTO sysUserDTO);

    /**
     * Get all the sysUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysUserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysUserDTO> findOne(Long id);

    /**
     * Delete the "id" sysUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
