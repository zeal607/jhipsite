package com.ruowei.service;

import com.ruowei.modules.sys.pojo.SysMenuDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysMenu}.
 */
public interface SysMenuService {

    /**
     * Save a sysMenu.
     *
     * @param sysMenuDTO the entity to save.
     * @return the persisted entity.
     */
    SysMenuDTO save(SysMenuDTO sysMenuDTO);

    /**
     * Get all the sysMenus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysMenuDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysMenu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysMenuDTO> findOne(Long id);

    /**
     * Delete the "id" sysMenu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
