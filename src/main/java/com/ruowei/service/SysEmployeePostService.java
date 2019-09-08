package com.ruowei.service;

import com.ruowei.service.dto.SysEmployeePostDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysEmployeePost}.
 */
public interface SysEmployeePostService {

    /**
     * Save a sysEmployeePost.
     *
     * @param sysEmployeePostDTO the entity to save.
     * @return the persisted entity.
     */
    SysEmployeePostDTO save(SysEmployeePostDTO sysEmployeePostDTO);

    /**
     * Get all the sysEmployeePosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysEmployeePostDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysEmployeePost.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysEmployeePostDTO> findOne(Long id);

    /**
     * Delete the "id" sysEmployeePost.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
