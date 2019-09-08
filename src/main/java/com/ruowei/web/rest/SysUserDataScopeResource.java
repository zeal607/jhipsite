package com.ruowei.web.rest;

import com.ruowei.service.SysUserDataScopeService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.service.dto.SysUserDataScopeDTO;
import com.ruowei.service.dto.SysUserDataScopeCriteria;
import com.ruowei.service.SysUserDataScopeQueryService;

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
 * REST controller for managing {@link com.ruowei.domain.SysUserDataScope}.
 */
@RestController
@RequestMapping("/api")
public class SysUserDataScopeResource {

    private final Logger log = LoggerFactory.getLogger(SysUserDataScopeResource.class);

    private static final String ENTITY_NAME = "sysUserDataScope";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysUserDataScopeService sysUserDataScopeService;

    private final SysUserDataScopeQueryService sysUserDataScopeQueryService;

    public SysUserDataScopeResource(SysUserDataScopeService sysUserDataScopeService, SysUserDataScopeQueryService sysUserDataScopeQueryService) {
        this.sysUserDataScopeService = sysUserDataScopeService;
        this.sysUserDataScopeQueryService = sysUserDataScopeQueryService;
    }

    /**
     * {@code POST  /sys-user-data-scopes} : Create a new sysUserDataScope.
     *
     * @param sysUserDataScopeDTO the sysUserDataScopeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysUserDataScopeDTO, or with status {@code 400 (Bad Request)} if the sysUserDataScope has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-user-data-scopes")
    public ResponseEntity<SysUserDataScopeDTO> createSysUserDataScope(@Valid @RequestBody SysUserDataScopeDTO sysUserDataScopeDTO) throws URISyntaxException {
        log.debug("REST request to save SysUserDataScope : {}", sysUserDataScopeDTO);
        if (sysUserDataScopeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysUserDataScope cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysUserDataScopeDTO result = sysUserDataScopeService.save(sysUserDataScopeDTO);
        return ResponseEntity.created(new URI("/api/sys-user-data-scopes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-user-data-scopes} : Updates an existing sysUserDataScope.
     *
     * @param sysUserDataScopeDTO the sysUserDataScopeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysUserDataScopeDTO,
     * or with status {@code 400 (Bad Request)} if the sysUserDataScopeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysUserDataScopeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-user-data-scopes")
    public ResponseEntity<SysUserDataScopeDTO> updateSysUserDataScope(@Valid @RequestBody SysUserDataScopeDTO sysUserDataScopeDTO) throws URISyntaxException {
        log.debug("REST request to update SysUserDataScope : {}", sysUserDataScopeDTO);
        if (sysUserDataScopeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysUserDataScopeDTO result = sysUserDataScopeService.save(sysUserDataScopeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysUserDataScopeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-user-data-scopes} : get all the sysUserDataScopes.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysUserDataScopes in body.
     */
    @GetMapping("/sys-user-data-scopes")
    public ResponseEntity<List<SysUserDataScopeDTO>> getAllSysUserDataScopes(SysUserDataScopeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysUserDataScopes by criteria: {}", criteria);
        Page<SysUserDataScopeDTO> page = sysUserDataScopeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-user-data-scopes/count} : count all the sysUserDataScopes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-user-data-scopes/count")
    public ResponseEntity<Long> countSysUserDataScopes(SysUserDataScopeCriteria criteria) {
        log.debug("REST request to count SysUserDataScopes by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysUserDataScopeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-user-data-scopes/:id} : get the "id" sysUserDataScope.
     *
     * @param id the id of the sysUserDataScopeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysUserDataScopeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-user-data-scopes/{id}")
    public ResponseEntity<SysUserDataScopeDTO> getSysUserDataScope(@PathVariable Long id) {
        log.debug("REST request to get SysUserDataScope : {}", id);
        Optional<SysUserDataScopeDTO> sysUserDataScopeDTO = sysUserDataScopeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysUserDataScopeDTO);
    }

    /**
     * {@code DELETE  /sys-user-data-scopes/:id} : delete the "id" sysUserDataScope.
     *
     * @param id the id of the sysUserDataScopeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-user-data-scopes/{id}")
    public ResponseEntity<Void> deleteSysUserDataScope(@PathVariable Long id) {
        log.debug("REST request to delete SysUserDataScope : {}", id);
        sysUserDataScopeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
