package com.ruowei.modules.sys.service.role;

import com.ruowei.service.dto.SysRoleMenuDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysRoleMenu}.
 */
public interface SysRoleMenuService {

    /**
     * Save a sysRoleMenu.
     *
     * @param sysRoleMenuDTO the entity to save.
     * @return the persisted entity.
     */
    SysRoleMenuDTO save(SysRoleMenuDTO sysRoleMenuDTO);

    /**
     * Get all the sysRoleMenus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysRoleMenuDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysRoleMenu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysRoleMenuDTO> findOne(Long id);

    /**
     * Delete the "id" sysRoleMenu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
