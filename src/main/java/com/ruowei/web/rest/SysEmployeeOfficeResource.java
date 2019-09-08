package com.ruowei.web.rest;

import com.ruowei.service.SysEmployeeOfficeService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.service.dto.SysEmployeeOfficeDTO;
import com.ruowei.service.dto.SysEmployeeOfficeCriteria;
import com.ruowei.service.SysEmployeeOfficeQueryService;

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
 * REST controller for managing {@link com.ruowei.domain.SysEmployeeOffice}.
 */
@RestController
@RequestMapping("/api")
public class SysEmployeeOfficeResource {

    private final Logger log = LoggerFactory.getLogger(SysEmployeeOfficeResource.class);

    private static final String ENTITY_NAME = "sysEmployeeOffice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysEmployeeOfficeService sysEmployeeOfficeService;

    private final SysEmployeeOfficeQueryService sysEmployeeOfficeQueryService;

    public SysEmployeeOfficeResource(SysEmployeeOfficeService sysEmployeeOfficeService, SysEmployeeOfficeQueryService sysEmployeeOfficeQueryService) {
        this.sysEmployeeOfficeService = sysEmployeeOfficeService;
        this.sysEmployeeOfficeQueryService = sysEmployeeOfficeQueryService;
    }

    /**
     * {@code POST  /sys-employee-offices} : Create a new sysEmployeeOffice.
     *
     * @param sysEmployeeOfficeDTO the sysEmployeeOfficeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysEmployeeOfficeDTO, or with status {@code 400 (Bad Request)} if the sysEmployeeOffice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-employee-offices")
    public ResponseEntity<SysEmployeeOfficeDTO> createSysEmployeeOffice(@Valid @RequestBody SysEmployeeOfficeDTO sysEmployeeOfficeDTO) throws URISyntaxException {
        log.debug("REST request to save SysEmployeeOffice : {}", sysEmployeeOfficeDTO);
        if (sysEmployeeOfficeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysEmployeeOffice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysEmployeeOfficeDTO result = sysEmployeeOfficeService.save(sysEmployeeOfficeDTO);
        return ResponseEntity.created(new URI("/api/sys-employee-offices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-employee-offices} : Updates an existing sysEmployeeOffice.
     *
     * @param sysEmployeeOfficeDTO the sysEmployeeOfficeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysEmployeeOfficeDTO,
     * or with status {@code 400 (Bad Request)} if the sysEmployeeOfficeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysEmployeeOfficeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-employee-offices")
    public ResponseEntity<SysEmployeeOfficeDTO> updateSysEmployeeOffice(@Valid @RequestBody SysEmployeeOfficeDTO sysEmployeeOfficeDTO) throws URISyntaxException {
        log.debug("REST request to update SysEmployeeOffice : {}", sysEmployeeOfficeDTO);
        if (sysEmployeeOfficeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysEmployeeOfficeDTO result = sysEmployeeOfficeService.save(sysEmployeeOfficeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysEmployeeOfficeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-employee-offices} : get all the sysEmployeeOffices.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysEmployeeOffices in body.
     */
    @GetMapping("/sys-employee-offices")
    public ResponseEntity<List<SysEmployeeOfficeDTO>> getAllSysEmployeeOffices(SysEmployeeOfficeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysEmployeeOffices by criteria: {}", criteria);
        Page<SysEmployeeOfficeDTO> page = sysEmployeeOfficeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-employee-offices/count} : count all the sysEmployeeOffices.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-employee-offices/count")
    public ResponseEntity<Long> countSysEmployeeOffices(SysEmployeeOfficeCriteria criteria) {
        log.debug("REST request to count SysEmployeeOffices by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysEmployeeOfficeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-employee-offices/:id} : get the "id" sysEmployeeOffice.
     *
     * @param id the id of the sysEmployeeOfficeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysEmployeeOfficeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-employee-offices/{id}")
    public ResponseEntity<SysEmployeeOfficeDTO> getSysEmployeeOffice(@PathVariable Long id) {
        log.debug("REST request to get SysEmployeeOffice : {}", id);
        Optional<SysEmployeeOfficeDTO> sysEmployeeOfficeDTO = sysEmployeeOfficeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysEmployeeOfficeDTO);
    }

    /**
     * {@code DELETE  /sys-employee-offices/:id} : delete the "id" sysEmployeeOffice.
     *
     * @param id the id of the sysEmployeeOfficeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-employee-offices/{id}")
    public ResponseEntity<Void> deleteSysEmployeeOffice(@PathVariable Long id) {
        log.debug("REST request to delete SysEmployeeOffice : {}", id);
        sysEmployeeOfficeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
