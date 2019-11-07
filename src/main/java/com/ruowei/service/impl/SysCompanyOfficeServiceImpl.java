package com.ruowei.service.impl;

import com.ruowei.modules.sys.domain.table.SysCompanyOffice;
import com.ruowei.modules.sys.repository.SysCompanyOfficeRepository;
import com.ruowei.modules.sys.service.company.SysCompanyOfficeService;
import com.ruowei.service.dto.SysCompanyOfficeDTO;
import com.ruowei.service.mapper.SysCompanyOfficeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysCompanyOffice}.
 */
@Service
@Transactional
public class SysCompanyOfficeServiceImpl implements SysCompanyOfficeService {

    private final Logger log = LoggerFactory.getLogger(SysCompanyOfficeServiceImpl.class);

    private final SysCompanyOfficeRepository sysCompanyOfficeRepository;

    private final SysCompanyOfficeMapper sysCompanyOfficeMapper;

    public SysCompanyOfficeServiceImpl(SysCompanyOfficeRepository sysCompanyOfficeRepository, SysCompanyOfficeMapper sysCompanyOfficeMapper) {
        this.sysCompanyOfficeRepository = sysCompanyOfficeRepository;
        this.sysCompanyOfficeMapper = sysCompanyOfficeMapper;
    }

    /**
     * Save a sysCompanyOffice.
     *
     * @param sysCompanyOfficeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysCompanyOfficeDTO save(SysCompanyOfficeDTO sysCompanyOfficeDTO) {
        log.debug("Request to save SysCompanyOffice : {}", sysCompanyOfficeDTO);
        SysCompanyOffice sysCompanyOffice = sysCompanyOfficeMapper.toEntity(sysCompanyOfficeDTO);
        sysCompanyOffice = sysCompanyOfficeRepository.save(sysCompanyOffice);
        return sysCompanyOfficeMapper.toDto(sysCompanyOffice);
    }

    /**
     * Get all the sysCompanyOffices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysCompanyOfficeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysCompanyOffices");
        return sysCompanyOfficeRepository.findAll(pageable)
            .map(sysCompanyOfficeMapper::toDto);
    }


    /**
     * Get one sysCompanyOffice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysCompanyOfficeDTO> findOne(Long id) {
        log.debug("Request to get SysCompanyOffice : {}", id);
        return sysCompanyOfficeRepository.findById(id)
            .map(sysCompanyOfficeMapper::toDto);
    }

    /**
     * Delete the sysCompanyOffice by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysCompanyOffice : {}", id);
        sysCompanyOfficeRepository.deleteById(id);
    }
}
