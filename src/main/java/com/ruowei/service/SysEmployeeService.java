package com.ruowei.service;

import com.ruowei.service.dto.SysEmployeeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysEmployee}.
 */
public interface SysEmployeeService {

    /**
     * Save a sysEmployee.
     *
     * @param sysEmployeeDTO the entity to save.
     * @return the persisted entity.
     */
    SysEmployeeDTO save(SysEmployeeDTO sysEmployeeDTO);

    /**
     * Get all the sysEmployees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysEmployeeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysEmployee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysEmployeeDTO> findOne(Long id);

    /**
     * Delete the "id" sysEmployee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
