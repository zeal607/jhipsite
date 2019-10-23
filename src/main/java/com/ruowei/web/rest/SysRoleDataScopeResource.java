package com.ruowei.web.rest;

import com.ruowei.modules.sys.service.role.SysRoleDataScopeService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.modules.sys.pojo.SysRoleDataScopeDTO;
import com.ruowei.modules.sys.pojo.SysRoleDataScopeCriteria;
import com.ruowei.modules.sys.service.role.impl.SysRoleDataScopeQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ruowei.domain.SysRoleDataScope}.
 */
@RestController
@RequestMapping("/api")
public class SysRoleDataScopeResource {

    private final Logger log = LoggerFactory.getLogger(SysRoleDataScopeResource.class);

    private static final String ENTITY_NAME = "sysRoleDataScope";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRoleDataScopeService sysRoleDataScopeService;

    private final SysRoleDataScopeQueryService sysRoleDataScopeQueryService;

    public SysRoleDataScopeResource(SysRoleDataScopeService sysRoleDataScopeService, SysRoleDataScopeQueryService sysRoleDataScopeQueryService) {
        this.sysRoleDataScopeService = sysRoleDataScopeService;
        this.sysRoleDataScopeQueryService = sysRoleDataScopeQueryService;
    }

    /**
     * {@code POST  /sys-role-data-scopes} : Create a new sysRoleDataScope.
     *
     * @param sysRoleDataScopeDTO the sysRoleDataScopeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRoleDataScopeDTO, or with status {@code 400 (Bad Request)} if the sysRoleDataScope has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-role-data-scopes")
    public ResponseEntity<SysRoleDataScopeDTO> createSysRoleDataScope(@Valid @RequestBody SysRoleDataScopeDTO sysRoleDataScopeDTO) throws URISyntaxException {
        log.debug("REST request to save SysRoleDataScope : {}", sysRoleDataScopeDTO);
        if (sysRoleDataScopeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysRoleDataScope cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRoleDataScopeDTO result = sysRoleDataScopeService.save(sysRoleDataScopeDTO);
        return ResponseEntity.created(new URI("/api/sys-role-data-scopes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-role-data-scopes} : Updates an existing sysRoleDataScope.
     *
     * @param sysRoleDataScopeDTO the sysRoleDataScopeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRoleDataScopeDTO,
     * or with status {@code 400 (Bad Request)} if the sysRoleDataScopeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRoleDataScopeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-role-data-scopes")
    public ResponseEntity<SysRoleDataScopeDTO> updateSysRoleDataScope(@Valid @RequestBody SysRoleDataScopeDTO sysRoleDataScopeDTO) throws URISyntaxException {
        log.debug("REST request to update SysRoleDataScope : {}", sysRoleDataScopeDTO);
        if (sysRoleDataScopeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysRoleDataScopeDTO result = sysRoleDataScopeService.save(sysRoleDataScopeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysRoleDataScopeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-role-data-scopes} : get all the sysRoleDataScopes.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRoleDataScopes in body.
     */
    @GetMapping("/sys-role-data-scopes")
    public ResponseEntity<List<SysRoleDataScopeDTO>> getAllSysRoleDataScopes(SysRoleDataScopeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysRoleDataScopes by criteria: {}", criteria);
        Page<SysRoleDataScopeDTO> page = sysRoleDataScopeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-role-data-scopes/count} : count all the sysRoleDataScopes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-role-data-scopes/count")
    public ResponseEntity<Long> countSysRoleDataScopes(SysRoleDataScopeCriteria criteria) {
        log.debug("REST request to count SysRoleDataScopes by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysRoleDataScopeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-role-data-scopes/:id} : get the "id" sysRoleDataScope.
     *
     * @param id the id of the sysRoleDataScopeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRoleDataScopeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-role-data-scopes/{id}")
    public ResponseEntity<SysRoleDataScopeDTO> getSysRoleDataScope(@PathVariable Long id) {
        log.debug("REST request to get SysRoleDataScope : {}", id);
        Optional<SysRoleDataScopeDTO> sysRoleDataScopeDTO = sysRoleDataScopeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRoleDataScopeDTO);
    }

    /**
     * {@code DELETE  /sys-role-data-scopes/:id} : delete the "id" sysRoleDataScope.
     *
     * @param id the id of the sysRoleDataScopeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-role-data-scopes/{id}")
    public ResponseEntity<Void> deleteSysRoleDataScope(@PathVariable Long id) {
        log.debug("REST request to delete SysRoleDataScope : {}", id);
        sysRoleDataScopeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
