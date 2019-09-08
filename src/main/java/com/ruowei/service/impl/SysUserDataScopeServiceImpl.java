package com.ruowei.service.impl;

import com.ruowei.service.SysUserDataScopeService;
import com.ruowei.domain.SysUserDataScope;
import com.ruowei.repository.SysUserDataScopeRepository;
import com.ruowei.service.dto.SysUserDataScopeDTO;
import com.ruowei.service.mapper.SysUserDataScopeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysUserDataScope}.
 */
@Service
@Transactional
public class SysUserDataScopeServiceImpl implements SysUserDataScopeService {

    private final Logger log = LoggerFactory.getLogger(SysUserDataScopeServiceImpl.class);

    private final SysUserDataScopeRepository sysUserDataScopeRepository;

    private final SysUserDataScopeMapper sysUserDataScopeMapper;

    public SysUserDataScopeServiceImpl(SysUserDataScopeRepository sysUserDataScopeRepository, SysUserDataScopeMapper sysUserDataScopeMapper) {
        this.sysUserDataScopeRepository = sysUserDataScopeRepository;
        this.sysUserDataScopeMapper = sysUserDataScopeMapper;
    }

    /**
     * Save a sysUserDataScope.
     *
     * @param sysUserDataScopeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysUserDataScopeDTO save(SysUserDataScopeDTO sysUserDataScopeDTO) {
        log.debug("Request to save SysUserDataScope : {}", sysUserDataScopeDTO);
        SysUserDataScope sysUserDataScope = sysUserDataScopeMapper.toEntity(sysUserDataScopeDTO);
        sysUserDataScope = sysUserDataScopeRepository.save(sysUserDataScope);
        return sysUserDataScopeMapper.toDto(sysUserDataScope);
    }

    /**
     * Get all the sysUserDataScopes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysUserDataScopeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysUserDataScopes");
        return sysUserDataScopeRepository.findAll(pageable)
            .map(sysUserDataScopeMapper::toDto);
    }


    /**
     * Get one sysUserDataScope by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysUserDataScopeDTO> findOne(Long id) {
        log.debug("Request to get SysUserDataScope : {}", id);
        return sysUserDataScopeRepository.findById(id)
            .map(sysUserDataScopeMapper::toDto);
    }

    /**
     * Delete the sysUserDataScope by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysUserDataScope : {}", id);
        sysUserDataScopeRepository.deleteById(id);
    }
}
