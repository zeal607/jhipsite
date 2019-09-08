package com.ruowei.service;

import com.ruowei.service.dto.SysPostDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysPost}.
 */
public interface SysPostService {

    /**
     * Save a sysPost.
     *
     * @param sysPostDTO the entity to save.
     * @return the persisted entity.
     */
    SysPostDTO save(SysPostDTO sysPostDTO);

    /**
     * Get all the sysPosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysPostDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysPost.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysPostDTO> findOne(Long id);

    /**
     * Delete the "id" sysPost.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
