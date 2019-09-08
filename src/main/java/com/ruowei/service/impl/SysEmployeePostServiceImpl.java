package com.ruowei.service.impl;

import com.ruowei.service.SysEmployeePostService;
import com.ruowei.domain.SysEmployeePost;
import com.ruowei.repository.SysEmployeePostRepository;
import com.ruowei.service.dto.SysEmployeePostDTO;
import com.ruowei.service.mapper.SysEmployeePostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysEmployeePost}.
 */
@Service
@Transactional
public class SysEmployeePostServiceImpl implements SysEmployeePostService {

    private final Logger log = LoggerFactory.getLogger(SysEmployeePostServiceImpl.class);

    private final SysEmployeePostRepository sysEmployeePostRepository;

    private final SysEmployeePostMapper sysEmployeePostMapper;

    public SysEmployeePostServiceImpl(SysEmployeePostRepository sysEmployeePostRepository, SysEmployeePostMapper sysEmployeePostMapper) {
        this.sysEmployeePostRepository = sysEmployeePostRepository;
        this.sysEmployeePostMapper = sysEmployeePostMapper;
    }

    /**
     * Save a sysEmployeePost.
     *
     * @param sysEmployeePostDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysEmployeePostDTO save(SysEmployeePostDTO sysEmployeePostDTO) {
        log.debug("Request to save SysEmployeePost : {}", sysEmployeePostDTO);
        SysEmployeePost sysEmployeePost = sysEmployeePostMapper.toEntity(sysEmployeePostDTO);
        sysEmployeePost = sysEmployeePostRepository.save(sysEmployeePost);
        return sysEmployeePostMapper.toDto(sysEmployeePost);
    }

    /**
     * Get all the sysEmployeePosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysEmployeePostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysEmployeePosts");
        return sysEmployeePostRepository.findAll(pageable)
            .map(sysEmployeePostMapper::toDto);
    }


    /**
     * Get one sysEmployeePost by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysEmployeePostDTO> findOne(Long id) {
        log.debug("Request to get SysEmployeePost : {}", id);
        return sysEmployeePostRepository.findById(id)
            .map(sysEmployeePostMapper::toDto);
    }

    /**
     * Delete the sysEmployeePost by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysEmployeePost : {}", id);
        sysEmployeePostRepository.deleteById(id);
    }
}
