package com.ruowei.web.rest;

import com.ruowei.service.SysRoleService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import com.ruowei.service.dto.SysRoleCriteria;
import com.ruowei.service.SysRoleQueryService;

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
 * REST controller for managing {@link com.ruowei.domain.SysRole}.
 */
@RestController
@RequestMapping("/api")
public class SysRoleResource {

    private final Logger log = LoggerFactory.getLogger(SysRoleResource.class);

    private static final String ENTITY_NAME = "sysRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRoleService sysRoleService;

    private final SysRoleQueryService sysRoleQueryService;

    public SysRoleResource(SysRoleService sysRoleService, SysRoleQueryService sysRoleQueryService) {
        this.sysRoleService = sysRoleService;
        this.sysRoleQueryService = sysRoleQueryService;
    }

    /**
     * {@code POST  /sys-roles} : Create a new sysRole.
     *
     * @param sysRoleDTO the sysRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRoleDTO, or with status {@code 400 (Bad Request)} if the sysRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-roles")
    public ResponseEntity<SysRoleDTO> createSysRole(@Valid @RequestBody SysRoleDTO sysRoleDTO) throws URISyntaxException {
        log.debug("REST request to save SysRole : {}", sysRoleDTO);
        if (sysRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRoleDTO result = sysRoleService.save(sysRoleDTO);
        return ResponseEntity.created(new URI("/api/sys-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-roles} : Updates an existing sysRole.
     *
     * @param sysRoleDTO the sysRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRoleDTO,
     * or with status {@code 400 (Bad Request)} if the sysRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-roles")
    public ResponseEntity<SysRoleDTO> updateSysRole(@Valid @RequestBody SysRoleDTO sysRoleDTO) throws URISyntaxException {
        log.debug("REST request to update SysRole : {}", sysRoleDTO);
        if (sysRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysRoleDTO result = sysRoleService.save(sysRoleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysRoleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-roles} : get all the sysRoles.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRoles in body.
     */
    @GetMapping("/sys-roles")
    public ResponseEntity<List<SysRoleDTO>> getAllSysRoles(SysRoleCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysRoles by criteria: {}", criteria);
        Page<SysRoleDTO> page = sysRoleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-roles/count} : count all the sysRoles.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-roles/count")
    public ResponseEntity<Long> countSysRoles(SysRoleCriteria criteria) {
        log.debug("REST request to count SysRoles by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysRoleQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-roles/:id} : get the "id" sysRole.
     *
     * @param id the id of the sysRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-roles/{id}")
    public ResponseEntity<SysRoleDTO> getSysRole(@PathVariable Long id) {
        log.debug("REST request to get SysRole : {}", id);
        Optional<SysRoleDTO> sysRoleDTO = sysRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRoleDTO);
    }

    /**
     * {@code DELETE  /sys-roles/:id} : delete the "id" sysRole.
     *
     * @param id the id of the sysRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-roles/{id}")
    public ResponseEntity<Void> deleteSysRole(@PathVariable Long id) {
        log.debug("REST request to delete SysRole : {}", id);
        sysRoleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
