package com.ruowei.service.impl;

import com.ruowei.service.SysUserRoleService;
import com.ruowei.domain.SysUserRole;
import com.ruowei.repository.SysUserRoleRepository;
import com.ruowei.service.dto.SysUserRoleDTO;
import com.ruowei.service.mapper.SysUserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysUserRole}.
 */
@Service
@Transactional
public class SysUserRoleServiceImpl implements SysUserRoleService {

    private final Logger log = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    private final SysUserRoleRepository sysUserRoleRepository;

    private final SysUserRoleMapper sysUserRoleMapper;

    public SysUserRoleServiceImpl(SysUserRoleRepository sysUserRoleRepository, SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleRepository = sysUserRoleRepository;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    /**
     * Save a sysUserRole.
     *
     * @param sysUserRoleDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysUserRoleDTO save(SysUserRoleDTO sysUserRoleDTO) {
        log.debug("Request to save SysUserRole : {}", sysUserRoleDTO);
        SysUserRole sysUserRole = sysUserRoleMapper.toEntity(sysUserRoleDTO);
        sysUserRole = sysUserRoleRepository.save(sysUserRole);
        return sysUserRoleMapper.toDto(sysUserRole);
    }

    /**
     * Get all the sysUserRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysUserRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysUserRoles");
        return sysUserRoleRepository.findAll(pageable)
            .map(sysUserRoleMapper::toDto);
    }


    /**
     * Get one sysUserRole by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysUserRoleDTO> findOne(Long id) {
        log.debug("Request to get SysUserRole : {}", id);
        return sysUserRoleRepository.findById(id)
            .map(sysUserRoleMapper::toDto);
    }

    /**
     * Delete the sysUserRole by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysUserRole : {}", id);
        sysUserRoleRepository.deleteById(id);
    }
}
