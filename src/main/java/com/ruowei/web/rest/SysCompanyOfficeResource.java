package com.ruowei.web.rest;

import com.ruowei.service.SysCompanyOfficeService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.service.dto.SysCompanyOfficeDTO;
import com.ruowei.service.dto.SysCompanyOfficeCriteria;
import com.ruowei.service.SysCompanyOfficeQueryService;

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
 * REST controller for managing {@link com.ruowei.domain.SysCompanyOffice}.
 */
@RestController
@RequestMapping("/api")
public class SysCompanyOfficeResource {

    private final Logger log = LoggerFactory.getLogger(SysCompanyOfficeResource.class);

    private static final String ENTITY_NAME = "sysCompanyOffice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysCompanyOfficeService sysCompanyOfficeService;

    private final SysCompanyOfficeQueryService sysCompanyOfficeQueryService;

    public SysCompanyOfficeResource(SysCompanyOfficeService sysCompanyOfficeService, SysCompanyOfficeQueryService sysCompanyOfficeQueryService) {
        this.sysCompanyOfficeService = sysCompanyOfficeService;
        this.sysCompanyOfficeQueryService = sysCompanyOfficeQueryService;
    }

    /**
     * {@code POST  /sys-company-offices} : Create a new sysCompanyOffice.
     *
     * @param sysCompanyOfficeDTO the sysCompanyOfficeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysCompanyOfficeDTO, or with status {@code 400 (Bad Request)} if the sysCompanyOffice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-company-offices")
    public ResponseEntity<SysCompanyOfficeDTO> createSysCompanyOffice(@Valid @RequestBody SysCompanyOfficeDTO sysCompanyOfficeDTO) throws URISyntaxException {
        log.debug("REST request to save SysCompanyOffice : {}", sysCompanyOfficeDTO);
        if (sysCompanyOfficeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysCompanyOffice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCompanyOfficeDTO result = sysCompanyOfficeService.save(sysCompanyOfficeDTO);
        return ResponseEntity.created(new URI("/api/sys-company-offices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-company-offices} : Updates an existing sysCompanyOffice.
     *
     * @param sysCompanyOfficeDTO the sysCompanyOfficeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysCompanyOfficeDTO,
     * or with status {@code 400 (Bad Request)} if the sysCompanyOfficeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysCompanyOfficeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-company-offices")
    public ResponseEntity<SysCompanyOfficeDTO> updateSysCompanyOffice(@Valid @RequestBody SysCompanyOfficeDTO sysCompanyOfficeDTO) throws URISyntaxException {
        log.debug("REST request to update SysCompanyOffice : {}", sysCompanyOfficeDTO);
        if (sysCompanyOfficeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysCompanyOfficeDTO result = sysCompanyOfficeService.save(sysCompanyOfficeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysCompanyOfficeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-company-offices} : get all the sysCompanyOffices.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysCompanyOffices in body.
     */
    @GetMapping("/sys-company-offices")
    public ResponseEntity<List<SysCompanyOfficeDTO>> getAllSysCompanyOffices(SysCompanyOfficeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysCompanyOffices by criteria: {}", criteria);
        Page<SysCompanyOfficeDTO> page = sysCompanyOfficeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-company-offices/count} : count all the sysCompanyOffices.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-company-offices/count")
    public ResponseEntity<Long> countSysCompanyOffices(SysCompanyOfficeCriteria criteria) {
        log.debug("REST request to count SysCompanyOffices by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysCompanyOfficeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-company-offices/:id} : get the "id" sysCompanyOffice.
     *
     * @param id the id of the sysCompanyOfficeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysCompanyOfficeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-company-offices/{id}")
    public ResponseEntity<SysCompanyOfficeDTO> getSysCompanyOffice(@PathVariable Long id) {
        log.debug("REST request to get SysCompanyOffice : {}", id);
        Optional<SysCompanyOfficeDTO> sysCompanyOfficeDTO = sysCompanyOfficeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysCompanyOfficeDTO);
    }

    /**
     * {@code DELETE  /sys-company-offices/:id} : delete the "id" sysCompanyOffice.
     *
     * @param id the id of the sysCompanyOfficeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-company-offices/{id}")
    public ResponseEntity<Void> deleteSysCompanyOffice(@PathVariable Long id) {
        log.debug("REST request to delete SysCompanyOffice : {}", id);
        sysCompanyOfficeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
