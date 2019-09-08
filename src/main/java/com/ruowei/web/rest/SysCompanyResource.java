package com.ruowei.web.rest;

import com.ruowei.service.SysCompanyService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.service.dto.SysCompanyDTO;
import com.ruowei.service.dto.SysCompanyCriteria;
import com.ruowei.service.SysCompanyQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ruowei.domain.SysCompany}.
 */
@RestController
@RequestMapping("/api")
public class SysCompanyResource {

    private final Logger log = LoggerFactory.getLogger(SysCompanyResource.class);

    private static final String ENTITY_NAME = "sysCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysCompanyService sysCompanyService;

    private final SysCompanyQueryService sysCompanyQueryService;

    public SysCompanyResource(SysCompanyService sysCompanyService, SysCompanyQueryService sysCompanyQueryService) {
        this.sysCompanyService = sysCompanyService;
        this.sysCompanyQueryService = sysCompanyQueryService;
    }

    /**
     * {@code POST  /sys-companies} : Create a new sysCompany.
     *
     * @param sysCompanyDTO the sysCompanyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysCompanyDTO, or with status {@code 400 (Bad Request)} if the sysCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-companies")
    public ResponseEntity<SysCompanyDTO> createSysCompany(@Valid @RequestBody SysCompanyDTO sysCompanyDTO) throws URISyntaxException {
        log.debug("REST request to save SysCompany : {}", sysCompanyDTO);
        if (sysCompanyDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCompanyDTO result = sysCompanyService.save(sysCompanyDTO);
        return ResponseEntity.created(new URI("/api/sys-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-companies} : Updates an existing sysCompany.
     *
     * @param sysCompanyDTO the sysCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the sysCompanyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-companies")
    public ResponseEntity<SysCompanyDTO> updateSysCompany(@Valid @RequestBody SysCompanyDTO sysCompanyDTO) throws URISyntaxException {
        log.debug("REST request to update SysCompany : {}", sysCompanyDTO);
        if (sysCompanyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysCompanyDTO result = sysCompanyService.save(sysCompanyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysCompanyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-companies} : get all the sysCompanies.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysCompanies in body.
     */
    @GetMapping("/sys-companies")
    public ResponseEntity<List<SysCompanyDTO>> getAllSysCompanies(SysCompanyCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysCompanies by criteria: {}", criteria);
        Page<SysCompanyDTO> page = sysCompanyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-companies/count} : count all the sysCompanies.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-companies/count")
    public ResponseEntity<Long> countSysCompanies(SysCompanyCriteria criteria) {
        log.debug("REST request to count SysCompanies by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysCompanyQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-companies/:id} : get the "id" sysCompany.
     *
     * @param id the id of the sysCompanyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysCompanyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-companies/{id}")
    public ResponseEntity<SysCompanyDTO> getSysCompany(@PathVariable Long id) {
        log.debug("REST request to get SysCompany : {}", id);
        Optional<SysCompanyDTO> sysCompanyDTO = sysCompanyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysCompanyDTO);
    }

    /**
     * {@code DELETE  /sys-companies/:id} : delete the "id" sysCompany.
     *
     * @param id the id of the sysCompanyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-companies/{id}")
    public ResponseEntity<Void> deleteSysCompany(@PathVariable Long id) {
        log.debug("REST request to delete SysCompany : {}", id);
        sysCompanyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
