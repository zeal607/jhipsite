package com.ruowei.service;

import com.ruowei.service.dto.SysCompanyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysCompany}.
 */
public interface SysCompanyService {

    /**
     * Save a sysCompany.
     *
     * @param sysCompanyDTO the entity to save.
     * @return the persisted entity.
     */
    SysCompanyDTO save(SysCompanyDTO sysCompanyDTO);

    /**
     * Get all the sysCompanies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysCompanyDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysCompany.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysCompanyDTO> findOne(Long id);

    /**
     * Delete the "id" sysCompany.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
