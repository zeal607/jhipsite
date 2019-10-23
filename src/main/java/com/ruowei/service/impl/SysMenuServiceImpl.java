package com.ruowei.service.impl;

import com.ruowei.modules.sys.domain.SysMenu;
import com.ruowei.modules.sys.repository.SysMenuRepository;
import com.ruowei.service.SysMenuService;
import com.ruowei.modules.sys.pojo.SysMenuDTO;
import com.ruowei.service.mapper.SysMenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysMenu}.
 */
@Service
@Transactional
public class SysMenuServiceImpl implements SysMenuService {

    private final Logger log = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    private final SysMenuRepository sysMenuRepository;

    private final SysMenuMapper sysMenuMapper;

    public SysMenuServiceImpl(SysMenuRepository sysMenuRepository, SysMenuMapper sysMenuMapper) {
        this.sysMenuRepository = sysMenuRepository;
        this.sysMenuMapper = sysMenuMapper;
    }

    /**
     * Save a sysMenu.
     *
     * @param sysMenuDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysMenuDTO save(SysMenuDTO sysMenuDTO) {
        log.debug("Request to save SysMenu : {}", sysMenuDTO);
        SysMenu sysMenu = sysMenuMapper.toEntity(sysMenuDTO);
        sysMenu = sysMenuRepository.save(sysMenu);
        return sysMenuMapper.toDto(sysMenu);
    }

    /**
     * Get all the sysMenus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysMenuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysMenus");
        return sysMenuRepository.findAll(pageable)
            .map(sysMenuMapper::toDto);
    }


    /**
     * Get one sysMenu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysMenuDTO> findOne(Long id) {
        log.debug("Request to get SysMenu : {}", id);
        return sysMenuRepository.findById(id)
            .map(sysMenuMapper::toDto);
    }

    /**
     * Delete the sysMenu by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysMenu : {}", id);
        sysMenuRepository.deleteById(id);
    }
}
