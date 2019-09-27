package com.ruowei.service.impl;

import com.ruowei.modules.sys.service.post.SysPostService;
import com.ruowei.modules.sys.domain.SysPost;
import com.ruowei.modules.sys.repository.SysPostRepository;
import com.ruowei.modules.sys.pojo.SysPostDTO;
import com.ruowei.modules.sys.mapper.SysPostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysPost}.
 */
@Service
@Transactional
public class SysPostServiceImpl implements SysPostService {

    private final Logger log = LoggerFactory.getLogger(SysPostServiceImpl.class);

    private final SysPostRepository sysPostRepository;

    private final SysPostMapper sysPostMapper;

    public SysPostServiceImpl(SysPostRepository sysPostRepository, SysPostMapper sysPostMapper) {
        this.sysPostRepository = sysPostRepository;
        this.sysPostMapper = sysPostMapper;
    }

    /**
     * Save a sysPost.
     *
     * @param sysPostDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysPostDTO save(SysPostDTO sysPostDTO) {
        log.debug("Request to save SysPost : {}", sysPostDTO);
        SysPost sysPost = sysPostMapper.toEntity(sysPostDTO);
        sysPost = sysPostRepository.save(sysPost);
        return sysPostMapper.toDto(sysPost);
    }

    /**
     * Get all the sysPosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysPostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysPosts");
        return sysPostRepository.findAll(pageable)
            .map(sysPostMapper::toDto);
    }


    /**
     * Get one sysPost by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysPostDTO> findOne(Long id) {
        log.debug("Request to get SysPost : {}", id);
        return sysPostRepository.findById(id)
            .map(sysPostMapper::toDto);
    }

    /**
     * Delete the sysPost by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysPost : {}", id);
        sysPostRepository.deleteById(id);
    }
}
