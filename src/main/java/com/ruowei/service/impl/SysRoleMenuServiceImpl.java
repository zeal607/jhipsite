package com.ruowei.service.impl;

import com.ruowei.modules.sys.domain.SysRoleMenu;
import com.ruowei.modules.sys.repository.SysRoleMenuRepository;
import com.ruowei.modules.sys.service.role.SysRoleMenuService;
import com.ruowei.modules.sys.pojo.SysRoleMenuDTO;
import com.ruowei.service.mapper.SysRoleMenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysRoleMenu}.
 */
@Service
@Transactional
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    private final Logger log = LoggerFactory.getLogger(SysRoleMenuServiceImpl.class);

    private final SysRoleMenuRepository sysRoleMenuRepository;

    private final SysRoleMenuMapper sysRoleMenuMapper;

    public SysRoleMenuServiceImpl(SysRoleMenuRepository sysRoleMenuRepository, SysRoleMenuMapper sysRoleMenuMapper) {
        this.sysRoleMenuRepository = sysRoleMenuRepository;
        this.sysRoleMenuMapper = sysRoleMenuMapper;
    }

    /**
     * Save a sysRoleMenu.
     *
     * @param sysRoleMenuDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysRoleMenuDTO save(SysRoleMenuDTO sysRoleMenuDTO) {
        log.debug("Request to save SysRoleMenu : {}", sysRoleMenuDTO);
        SysRoleMenu sysRoleMenu = sysRoleMenuMapper.toEntity(sysRoleMenuDTO);
        sysRoleMenu = sysRoleMenuRepository.save(sysRoleMenu);
        return sysRoleMenuMapper.toDto(sysRoleMenu);
    }

    /**
     * Get all the sysRoleMenus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysRoleMenuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysRoleMenus");
        return sysRoleMenuRepository.findAll(pageable)
            .map(sysRoleMenuMapper::toDto);
    }


    /**
     * Get one sysRoleMenu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysRoleMenuDTO> findOne(Long id) {
        log.debug("Request to get SysRoleMenu : {}", id);
        return sysRoleMenuRepository.findById(id)
            .map(sysRoleMenuMapper::toDto);
    }

    /**
     * Delete the sysRoleMenu by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysRoleMenu : {}", id);
        sysRoleMenuRepository.deleteById(id);
    }
}
