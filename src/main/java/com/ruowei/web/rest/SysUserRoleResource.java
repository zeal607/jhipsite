package com.ruowei.web.rest;

import com.ruowei.modules.sys.domain.SysUserRole;
import com.ruowei.modules.sys.service.user.SysUserRoleService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.modules.sys.pojo.SysUserRoleDTO;
import com.ruowei.modules.sys.pojo.SysUserRoleCriteria;
import com.ruowei.modules.sys.service.user.SysUserRoleQueryService;

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
 * REST controller for managing {@link SysUserRole}.
 */
@RestController
@RequestMapping("/api")
public class SysUserRoleResource {

    private final Logger log = LoggerFactory.getLogger(SysUserRoleResource.class);

    private static final String ENTITY_NAME = "sysUserRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysUserRoleService sysUserRoleService;

    private final SysUserRoleQueryService sysUserRoleQueryService;

    public SysUserRoleResource(SysUserRoleService sysUserRoleService, SysUserRoleQueryService sysUserRoleQueryService) {
        this.sysUserRoleService = sysUserRoleService;
        this.sysUserRoleQueryService = sysUserRoleQueryService;
    }

    /**
     * {@code POST  /sys-user-roles} : Create a new sysUserRole.
     *
     * @param sysUserRoleDTO the sysUserRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysUserRoleDTO, or with status {@code 400 (Bad Request)} if the sysUserRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-user-roles")
    public ResponseEntity<SysUserRoleDTO> createSysUserRole(@Valid @RequestBody SysUserRoleDTO sysUserRoleDTO) throws URISyntaxException {
        log.debug("REST request to save SysUserRole : {}", sysUserRoleDTO);
        if (sysUserRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysUserRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysUserRoleDTO result = sysUserRoleService.save(sysUserRoleDTO);
        return ResponseEntity.created(new URI("/api/sys-user-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-user-roles} : Updates an existing sysUserRole.
     *
     * @param sysUserRoleDTO the sysUserRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysUserRoleDTO,
     * or with status {@code 400 (Bad Request)} if the sysUserRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysUserRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-user-roles")
    public ResponseEntity<SysUserRoleDTO> updateSysUserRole(@Valid @RequestBody SysUserRoleDTO sysUserRoleDTO) throws URISyntaxException {
        log.debug("REST request to update SysUserRole : {}", sysUserRoleDTO);
        if (sysUserRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysUserRoleDTO result = sysUserRoleService.save(sysUserRoleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysUserRoleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-user-roles} : get all the sysUserRoles.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysUserRoles in body.
     */
    @GetMapping("/sys-user-roles")
    public ResponseEntity<List<SysUserRoleDTO>> getAllSysUserRoles(SysUserRoleCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysUserRoles by criteria: {}", criteria);
        Page<SysUserRoleDTO> page = sysUserRoleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-user-roles/count} : count all the sysUserRoles.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-user-roles/count")
    public ResponseEntity<Long> countSysUserRoles(SysUserRoleCriteria criteria) {
        log.debug("REST request to count SysUserRoles by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysUserRoleQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-user-roles/:id} : get the "id" sysUserRole.
     *
     * @param id the id of the sysUserRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysUserRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-user-roles/{id}")
    public ResponseEntity<SysUserRoleDTO> getSysUserRole(@PathVariable Long id) {
        log.debug("REST request to get SysUserRole : {}", id);
        Optional<SysUserRoleDTO> sysUserRoleDTO = sysUserRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysUserRoleDTO);
    }

    /**
     * {@code DELETE  /sys-user-roles/:id} : delete the "id" sysUserRole.
     *
     * @param id the id of the sysUserRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-user-roles/{id}")
    public ResponseEntity<Void> deleteSysUserRole(@PathVariable Long id) {
        log.debug("REST request to delete SysUserRole : {}", id);
        sysUserRoleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
