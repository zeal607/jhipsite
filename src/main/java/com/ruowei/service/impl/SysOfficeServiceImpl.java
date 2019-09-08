package com.ruowei.service.impl;

import com.ruowei.service.SysOfficeService;
import com.ruowei.domain.SysOffice;
import com.ruowei.repository.SysOfficeRepository;
import com.ruowei.service.dto.SysOfficeDTO;
import com.ruowei.service.mapper.SysOfficeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysOffice}.
 */
@Service
@Transactional
public class SysOfficeServiceImpl implements SysOfficeService {

    private final Logger log = LoggerFactory.getLogger(SysOfficeServiceImpl.class);

    private final SysOfficeRepository sysOfficeRepository;

    private final SysOfficeMapper sysOfficeMapper;

    public SysOfficeServiceImpl(SysOfficeRepository sysOfficeRepository, SysOfficeMapper sysOfficeMapper) {
        this.sysOfficeRepository = sysOfficeRepository;
        this.sysOfficeMapper = sysOfficeMapper;
    }

    /**
     * Save a sysOffice.
     *
     * @param sysOfficeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysOfficeDTO save(SysOfficeDTO sysOfficeDTO) {
        log.debug("Request to save SysOffice : {}", sysOfficeDTO);
        SysOffice sysOffice = sysOfficeMapper.toEntity(sysOfficeDTO);
        sysOffice = sysOfficeRepository.save(sysOffice);
        return sysOfficeMapper.toDto(sysOffice);
    }

    /**
     * Get all the sysOffices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysOfficeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysOffices");
        return sysOfficeRepository.findAll(pageable)
            .map(sysOfficeMapper::toDto);
    }


    /**
     * Get one sysOffice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysOfficeDTO> findOne(Long id) {
        log.debug("Request to get SysOffice : {}", id);
        return sysOfficeRepository.findById(id)
            .map(sysOfficeMapper::toDto);
    }

    /**
     * Delete the sysOffice by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysOffice : {}", id);
        sysOfficeRepository.deleteById(id);
    }
}
