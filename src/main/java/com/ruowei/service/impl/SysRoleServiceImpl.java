package com.ruowei.service.impl;

import com.ruowei.modules.sys.service.role.SysRoleService;
import com.ruowei.modules.sys.domain.SysRole;
import com.ruowei.modules.sys.repository.SysRoleRepository;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import com.ruowei.modules.sys.mapper.SysRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysRole}.
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    private final Logger log = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    private final SysRoleRepository sysRoleRepository;

    private final SysRoleMapper sysRoleMapper;

    public SysRoleServiceImpl(SysRoleRepository sysRoleRepository, SysRoleMapper sysRoleMapper) {
        this.sysRoleRepository = sysRoleRepository;
        this.sysRoleMapper = sysRoleMapper;
    }

    /**
     * Save a sysRole.
     *
     * @param sysRoleDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysRoleDTO save(SysRoleDTO sysRoleDTO) {
        log.debug("Request to save SysRole : {}", sysRoleDTO);
        SysRole sysRole = sysRoleMapper.toEntity(sysRoleDTO);
        sysRole = sysRoleRepository.save(sysRole);
        return sysRoleMapper.toDto(sysRole);
    }

    /**
     * Get all the sysRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysRoles");
        return sysRoleRepository.findAll(pageable)
            .map(sysRoleMapper::toDto);
    }


    /**
     * Get one sysRole by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysRoleDTO> findOne(Long id) {
        log.debug("Request to get SysRole : {}", id);
        return sysRoleRepository.findById(id)
            .map(sysRoleMapper::toDto);
    }

    /**
     * Delete the sysRole by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysRole : {}", id);
        sysRoleRepository.deleteById(id);
    }
}
