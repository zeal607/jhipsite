package com.ruowei.service.impl;

import com.ruowei.service.SysEmployeeService;
import com.ruowei.domain.SysEmployee;
import com.ruowei.repository.SysEmployeeRepository;
import com.ruowei.service.dto.SysEmployeeDTO;
import com.ruowei.service.mapper.SysEmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysEmployee}.
 */
@Service
@Transactional
public class SysEmployeeServiceImpl implements SysEmployeeService {

    private final Logger log = LoggerFactory.getLogger(SysEmployeeServiceImpl.class);

    private final SysEmployeeRepository sysEmployeeRepository;

    private final SysEmployeeMapper sysEmployeeMapper;

    public SysEmployeeServiceImpl(SysEmployeeRepository sysEmployeeRepository, SysEmployeeMapper sysEmployeeMapper) {
        this.sysEmployeeRepository = sysEmployeeRepository;
        this.sysEmployeeMapper = sysEmployeeMapper;
    }

    /**
     * Save a sysEmployee.
     *
     * @param sysEmployeeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysEmployeeDTO save(SysEmployeeDTO sysEmployeeDTO) {
        log.debug("Request to save SysEmployee : {}", sysEmployeeDTO);
        SysEmployee sysEmployee = sysEmployeeMapper.toEntity(sysEmployeeDTO);
        sysEmployee = sysEmployeeRepository.save(sysEmployee);
        return sysEmployeeMapper.toDto(sysEmployee);
    }

    /**
     * Get all the sysEmployees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysEmployeeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysEmployees");
        return sysEmployeeRepository.findAll(pageable)
            .map(sysEmployeeMapper::toDto);
    }


    /**
     * Get one sysEmployee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysEmployeeDTO> findOne(Long id) {
        log.debug("Request to get SysEmployee : {}", id);
        return sysEmployeeRepository.findById(id)
            .map(sysEmployeeMapper::toDto);
    }

    /**
     * Delete the sysEmployee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysEmployee : {}", id);
        sysEmployeeRepository.deleteById(id);
    }
}
