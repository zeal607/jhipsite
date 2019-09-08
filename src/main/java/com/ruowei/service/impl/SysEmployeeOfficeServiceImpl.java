package com.ruowei.service.impl;

import com.ruowei.service.SysEmployeeOfficeService;
import com.ruowei.domain.SysEmployeeOffice;
import com.ruowei.repository.SysEmployeeOfficeRepository;
import com.ruowei.service.dto.SysEmployeeOfficeDTO;
import com.ruowei.service.mapper.SysEmployeeOfficeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysEmployeeOffice}.
 */
@Service
@Transactional
public class SysEmployeeOfficeServiceImpl implements SysEmployeeOfficeService {

    private final Logger log = LoggerFactory.getLogger(SysEmployeeOfficeServiceImpl.class);

    private final SysEmployeeOfficeRepository sysEmployeeOfficeRepository;

    private final SysEmployeeOfficeMapper sysEmployeeOfficeMapper;

    public SysEmployeeOfficeServiceImpl(SysEmployeeOfficeRepository sysEmployeeOfficeRepository, SysEmployeeOfficeMapper sysEmployeeOfficeMapper) {
        this.sysEmployeeOfficeRepository = sysEmployeeOfficeRepository;
        this.sysEmployeeOfficeMapper = sysEmployeeOfficeMapper;
    }

    /**
     * Save a sysEmployeeOffice.
     *
     * @param sysEmployeeOfficeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysEmployeeOfficeDTO save(SysEmployeeOfficeDTO sysEmployeeOfficeDTO) {
        log.debug("Request to save SysEmployeeOffice : {}", sysEmployeeOfficeDTO);
        SysEmployeeOffice sysEmployeeOffice = sysEmployeeOfficeMapper.toEntity(sysEmployeeOfficeDTO);
        sysEmployeeOffice = sysEmployeeOfficeRepository.save(sysEmployeeOffice);
        return sysEmployeeOfficeMapper.toDto(sysEmployeeOffice);
    }

    /**
     * Get all the sysEmployeeOffices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysEmployeeOfficeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysEmployeeOffices");
        return sysEmployeeOfficeRepository.findAll(pageable)
            .map(sysEmployeeOfficeMapper::toDto);
    }


    /**
     * Get one sysEmployeeOffice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysEmployeeOfficeDTO> findOne(Long id) {
        log.debug("Request to get SysEmployeeOffice : {}", id);
        return sysEmployeeOfficeRepository.findById(id)
            .map(sysEmployeeOfficeMapper::toDto);
    }

    /**
     * Delete the sysEmployeeOffice by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysEmployeeOffice : {}", id);
        sysEmployeeOfficeRepository.deleteById(id);
    }
}
