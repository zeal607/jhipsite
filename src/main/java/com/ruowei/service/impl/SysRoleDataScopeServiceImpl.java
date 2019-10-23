package com.ruowei.service.impl;

import com.ruowei.modules.sys.domain.SysRoleDataScope;
import com.ruowei.modules.sys.repository.SysRoleDataScopeRepository;
import com.ruowei.modules.sys.service.role.SysRoleDataScopeService;
import com.ruowei.modules.sys.pojo.SysRoleDataScopeDTO;
import com.ruowei.service.mapper.SysRoleDataScopeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysRoleDataScope}.
 */
@Service
@Transactional
public class SysRoleDataScopeServiceImpl implements SysRoleDataScopeService {

    private final Logger log = LoggerFactory.getLogger(SysRoleDataScopeServiceImpl.class);

    private final SysRoleDataScopeRepository sysRoleDataScopeRepository;

    private final SysRoleDataScopeMapper sysRoleDataScopeMapper;

    public SysRoleDataScopeServiceImpl(SysRoleDataScopeRepository sysRoleDataScopeRepository, SysRoleDataScopeMapper sysRoleDataScopeMapper) {
        this.sysRoleDataScopeRepository = sysRoleDataScopeRepository;
        this.sysRoleDataScopeMapper = sysRoleDataScopeMapper;
    }

    /**
     * Save a sysRoleDataScope.
     *
     * @param sysRoleDataScopeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysRoleDataScopeDTO save(SysRoleDataScopeDTO sysRoleDataScopeDTO) {
        log.debug("Request to save SysRoleDataScope : {}", sysRoleDataScopeDTO);
        SysRoleDataScope sysRoleDataScope = sysRoleDataScopeMapper.toEntity(sysRoleDataScopeDTO);
        sysRoleDataScope = sysRoleDataScopeRepository.save(sysRoleDataScope);
        return sysRoleDataScopeMapper.toDto(sysRoleDataScope);
    }

    /**
     * Get all the sysRoleDataScopes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysRoleDataScopeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysRoleDataScopes");
        return sysRoleDataScopeRepository.findAll(pageable)
            .map(sysRoleDataScopeMapper::toDto);
    }


    /**
     * Get one sysRoleDataScope by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysRoleDataScopeDTO> findOne(Long id) {
        log.debug("Request to get SysRoleDataScope : {}", id);
        return sysRoleDataScopeRepository.findById(id)
            .map(sysRoleDataScopeMapper::toDto);
    }

    /**
     * Delete the sysRoleDataScope by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysRoleDataScope : {}", id);
        sysRoleDataScopeRepository.deleteById(id);
    }
}
