package com.ruowei.service;

import com.ruowei.service.dto.SysOfficeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysOffice}.
 */
public interface SysOfficeService {

    /**
     * Save a sysOffice.
     *
     * @param sysOfficeDTO the entity to save.
     * @return the persisted entity.
     */
    SysOfficeDTO save(SysOfficeDTO sysOfficeDTO);

    /**
     * Get all the sysOffices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysOfficeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysOffice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysOfficeDTO> findOne(Long id);

    /**
     * Delete the "id" sysOffice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
