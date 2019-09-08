package com.ruowei.service.impl;

import com.ruowei.service.SysUserService;
import com.ruowei.domain.SysUser;
import com.ruowei.repository.SysUserRepository;
import com.ruowei.service.dto.SysUserDTO;
import com.ruowei.service.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysUser}.
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    private final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    private final SysUserRepository sysUserRepository;

    private final SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserRepository sysUserRepository, SysUserMapper sysUserMapper) {
        this.sysUserRepository = sysUserRepository;
        this.sysUserMapper = sysUserMapper;
    }

    /**
     * Save a sysUser.
     *
     * @param sysUserDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysUserDTO save(SysUserDTO sysUserDTO) {
        log.debug("Request to save SysUser : {}", sysUserDTO);
        SysUser sysUser = sysUserMapper.toEntity(sysUserDTO);
        sysUser = sysUserRepository.save(sysUser);
        return sysUserMapper.toDto(sysUser);
    }

    /**
     * Get all the sysUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysUsers");
        return sysUserRepository.findAll(pageable)
            .map(sysUserMapper::toDto);
    }


    /**
     * Get one sysUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysUserDTO> findOne(Long id) {
        log.debug("Request to get SysUser : {}", id);
        return sysUserRepository.findById(id)
            .map(sysUserMapper::toDto);
    }

    /**
     * Delete the sysUser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysUser : {}", id);
        sysUserRepository.deleteById(id);
    }
}
