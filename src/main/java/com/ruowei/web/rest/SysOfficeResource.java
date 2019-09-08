package com.ruowei.web.rest;

import com.ruowei.service.SysOfficeService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.service.dto.SysOfficeDTO;
import com.ruowei.service.dto.SysOfficeCriteria;
import com.ruowei.service.SysOfficeQueryService;

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
 * REST controller for managing {@link com.ruowei.domain.SysOffice}.
 */
@RestController
@RequestMapping("/api")
public class SysOfficeResource {

    private final Logger log = LoggerFactory.getLogger(SysOfficeResource.class);

    private static final String ENTITY_NAME = "sysOffice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysOfficeService sysOfficeService;

    private final SysOfficeQueryService sysOfficeQueryService;

    public SysOfficeResource(SysOfficeService sysOfficeService, SysOfficeQueryService sysOfficeQueryService) {
        this.sysOfficeService = sysOfficeService;
        this.sysOfficeQueryService = sysOfficeQueryService;
    }

    /**
     * {@code POST  /sys-offices} : Create a new sysOffice.
     *
     * @param sysOfficeDTO the sysOfficeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysOfficeDTO, or with status {@code 400 (Bad Request)} if the sysOffice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-offices")
    public ResponseEntity<SysOfficeDTO> createSysOffice(@Valid @RequestBody SysOfficeDTO sysOfficeDTO) throws URISyntaxException {
        log.debug("REST request to save SysOffice : {}", sysOfficeDTO);
        if (sysOfficeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysOffice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysOfficeDTO result = sysOfficeService.save(sysOfficeDTO);
        return ResponseEntity.created(new URI("/api/sys-offices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-offices} : Updates an existing sysOffice.
     *
     * @param sysOfficeDTO the sysOfficeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysOfficeDTO,
     * or with status {@code 400 (Bad Request)} if the sysOfficeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysOfficeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-offices")
    public ResponseEntity<SysOfficeDTO> updateSysOffice(@Valid @RequestBody SysOfficeDTO sysOfficeDTO) throws URISyntaxException {
        log.debug("REST request to update SysOffice : {}", sysOfficeDTO);
        if (sysOfficeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysOfficeDTO result = sysOfficeService.save(sysOfficeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysOfficeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-offices} : get all the sysOffices.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysOffices in body.
     */
    @GetMapping("/sys-offices")
    public ResponseEntity<List<SysOfficeDTO>> getAllSysOffices(SysOfficeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysOffices by criteria: {}", criteria);
        Page<SysOfficeDTO> page = sysOfficeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-offices/count} : count all the sysOffices.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-offices/count")
    public ResponseEntity<Long> countSysOffices(SysOfficeCriteria criteria) {
        log.debug("REST request to count SysOffices by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysOfficeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-offices/:id} : get the "id" sysOffice.
     *
     * @param id the id of the sysOfficeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysOfficeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-offices/{id}")
    public ResponseEntity<SysOfficeDTO> getSysOffice(@PathVariable Long id) {
        log.debug("REST request to get SysOffice : {}", id);
        Optional<SysOfficeDTO> sysOfficeDTO = sysOfficeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysOfficeDTO);
    }

    /**
     * {@code DELETE  /sys-offices/:id} : delete the "id" sysOffice.
     *
     * @param id the id of the sysOfficeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-offices/{id}")
    public ResponseEntity<Void> deleteSysOffice(@PathVariable Long id) {
        log.debug("REST request to delete SysOffice : {}", id);
        sysOfficeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
