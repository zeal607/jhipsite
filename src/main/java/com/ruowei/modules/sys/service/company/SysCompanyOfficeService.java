package com.ruowei.modules.sys.service.company;

import com.ruowei.service.dto.SysCompanyOfficeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruowei.domain.SysCompanyOffice}.
 */
public interface SysCompanyOfficeService {

    /**
     * Save a sysCompanyOffice.
     *
     * @param sysCompanyOfficeDTO the entity to save.
     * @return the persisted entity.
     */
    SysCompanyOfficeDTO save(SysCompanyOfficeDTO sysCompanyOfficeDTO);

    /**
     * Get all the sysCompanyOffices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysCompanyOfficeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysCompanyOffice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysCompanyOfficeDTO> findOne(Long id);

    /**
     * Delete the "id" sysCompanyOffice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
