package com.ruowei.service;

import com.ruowei.service.dto.SysEmployeeOfficeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysEmployeeOffice}.
 */
public interface SysEmployeeOfficeService {

    /**
     * Save a sysEmployeeOffice.
     *
     * @param sysEmployeeOfficeDTO the entity to save.
     * @return the persisted entity.
     */
    SysEmployeeOfficeDTO save(SysEmployeeOfficeDTO sysEmployeeOfficeDTO);

    /**
     * Get all the sysEmployeeOffices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysEmployeeOfficeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysEmployeeOffice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysEmployeeOfficeDTO> findOne(Long id);

    /**
     * Delete the "id" sysEmployeeOffice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
