package com.ruowei.service.impl;

import com.ruowei.modules.sys.service.SysCompanyService;
import com.ruowei.domain.SysCompany;
import com.ruowei.repository.SysCompanyRepository;
import com.ruowei.service.dto.SysCompanyDTO;
import com.ruowei.service.mapper.SysCompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysCompany}.
 */
@Service
@Transactional
public class SysCompanyServiceImpl implements SysCompanyService {

    private final Logger log = LoggerFactory.getLogger(SysCompanyServiceImpl.class);

    private final SysCompanyRepository sysCompanyRepository;

    private final SysCompanyMapper sysCompanyMapper;

    public SysCompanyServiceImpl(SysCompanyRepository sysCompanyRepository, SysCompanyMapper sysCompanyMapper) {
        this.sysCompanyRepository = sysCompanyRepository;
        this.sysCompanyMapper = sysCompanyMapper;
    }

    /**
     * Save a sysCompany.
     *
     * @param sysCompanyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysCompanyDTO save(SysCompanyDTO sysCompanyDTO) {
        log.debug("Request to save SysCompany : {}", sysCompanyDTO);
        SysCompany sysCompany = sysCompanyMapper.toEntity(sysCompanyDTO);
        sysCompany = sysCompanyRepository.save(sysCompany);
        return sysCompanyMapper.toDto(sysCompany);
    }

    /**
     * Get all the sysCompanies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysCompanyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysCompanies");
        return sysCompanyRepository.findAll(pageable)
            .map(sysCompanyMapper::toDto);
    }


    /**
     * Get one sysCompany by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysCompanyDTO> findOne(Long id) {
        log.debug("Request to get SysCompany : {}", id);
        return sysCompanyRepository.findById(id)
            .map(sysCompanyMapper::toDto);
    }

    /**
     * Delete the sysCompany by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysCompany : {}", id);
        sysCompanyRepository.deleteById(id);
    }
}
