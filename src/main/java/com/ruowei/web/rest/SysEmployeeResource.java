package com.ruowei.web.rest;

import com.ruowei.service.SysEmployeeService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.service.dto.SysEmployeeDTO;
import com.ruowei.service.dto.SysEmployeeCriteria;
import com.ruowei.service.SysEmployeeQueryService;

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
 * REST controller for managing {@link com.ruowei.domain.SysEmployee}.
 */
@RestController
@RequestMapping("/api")
public class SysEmployeeResource {

    private final Logger log = LoggerFactory.getLogger(SysEmployeeResource.class);

    private static final String ENTITY_NAME = "sysEmployee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysEmployeeService sysEmployeeService;

    private final SysEmployeeQueryService sysEmployeeQueryService;

    public SysEmployeeResource(SysEmployeeService sysEmployeeService, SysEmployeeQueryService sysEmployeeQueryService) {
        this.sysEmployeeService = sysEmployeeService;
        this.sysEmployeeQueryService = sysEmployeeQueryService;
    }

    /**
     * {@code POST  /sys-employees} : Create a new sysEmployee.
     *
     * @param sysEmployeeDTO the sysEmployeeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysEmployeeDTO, or with status {@code 400 (Bad Request)} if the sysEmployee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-employees")
    public ResponseEntity<SysEmployeeDTO> createSysEmployee(@Valid @RequestBody SysEmployeeDTO sysEmployeeDTO) throws URISyntaxException {
        log.debug("REST request to save SysEmployee : {}", sysEmployeeDTO);
        if (sysEmployeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysEmployee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysEmployeeDTO result = sysEmployeeService.save(sysEmployeeDTO);
        return ResponseEntity.created(new URI("/api/sys-employees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-employees} : Updates an existing sysEmployee.
     *
     * @param sysEmployeeDTO the sysEmployeeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysEmployeeDTO,
     * or with status {@code 400 (Bad Request)} if the sysEmployeeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysEmployeeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-employees")
    public ResponseEntity<SysEmployeeDTO> updateSysEmployee(@Valid @RequestBody SysEmployeeDTO sysEmployeeDTO) throws URISyntaxException {
        log.debug("REST request to update SysEmployee : {}", sysEmployeeDTO);
        if (sysEmployeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysEmployeeDTO result = sysEmployeeService.save(sysEmployeeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysEmployeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-employees} : get all the sysEmployees.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysEmployees in body.
     */
    @GetMapping("/sys-employees")
    public ResponseEntity<List<SysEmployeeDTO>> getAllSysEmployees(SysEmployeeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysEmployees by criteria: {}", criteria);
        Page<SysEmployeeDTO> page = sysEmployeeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-employees/count} : count all the sysEmployees.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-employees/count")
    public ResponseEntity<Long> countSysEmployees(SysEmployeeCriteria criteria) {
        log.debug("REST request to count SysEmployees by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysEmployeeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-employees/:id} : get the "id" sysEmployee.
     *
     * @param id the id of the sysEmployeeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysEmployeeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-employees/{id}")
    public ResponseEntity<SysEmployeeDTO> getSysEmployee(@PathVariable Long id) {
        log.debug("REST request to get SysEmployee : {}", id);
        Optional<SysEmployeeDTO> sysEmployeeDTO = sysEmployeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysEmployeeDTO);
    }

    /**
     * {@code DELETE  /sys-employees/:id} : delete the "id" sysEmployee.
     *
     * @param id the id of the sysEmployeeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-employees/{id}")
    public ResponseEntity<Void> deleteSysEmployee(@PathVariable Long id) {
        log.debug("REST request to delete SysEmployee : {}", id);
        sysEmployeeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
