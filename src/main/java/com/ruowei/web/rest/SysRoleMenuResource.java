package com.ruowei.web.rest;

import com.ruowei.service.SysRoleMenuService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.service.dto.SysRoleMenuDTO;
import com.ruowei.service.dto.SysRoleMenuCriteria;
import com.ruowei.service.SysRoleMenuQueryService;

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
 * REST controller for managing {@link com.ruowei.domain.SysRoleMenu}.
 */
@RestController
@RequestMapping("/api")
public class SysRoleMenuResource {

    private final Logger log = LoggerFactory.getLogger(SysRoleMenuResource.class);

    private static final String ENTITY_NAME = "sysRoleMenu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRoleMenuService sysRoleMenuService;

    private final SysRoleMenuQueryService sysRoleMenuQueryService;

    public SysRoleMenuResource(SysRoleMenuService sysRoleMenuService, SysRoleMenuQueryService sysRoleMenuQueryService) {
        this.sysRoleMenuService = sysRoleMenuService;
        this.sysRoleMenuQueryService = sysRoleMenuQueryService;
    }

    /**
     * {@code POST  /sys-role-menus} : Create a new sysRoleMenu.
     *
     * @param sysRoleMenuDTO the sysRoleMenuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRoleMenuDTO, or with status {@code 400 (Bad Request)} if the sysRoleMenu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-role-menus")
    public ResponseEntity<SysRoleMenuDTO> createSysRoleMenu(@Valid @RequestBody SysRoleMenuDTO sysRoleMenuDTO) throws URISyntaxException {
        log.debug("REST request to save SysRoleMenu : {}", sysRoleMenuDTO);
        if (sysRoleMenuDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysRoleMenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRoleMenuDTO result = sysRoleMenuService.save(sysRoleMenuDTO);
        return ResponseEntity.created(new URI("/api/sys-role-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-role-menus} : Updates an existing sysRoleMenu.
     *
     * @param sysRoleMenuDTO the sysRoleMenuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRoleMenuDTO,
     * or with status {@code 400 (Bad Request)} if the sysRoleMenuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRoleMenuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-role-menus")
    public ResponseEntity<SysRoleMenuDTO> updateSysRoleMenu(@Valid @RequestBody SysRoleMenuDTO sysRoleMenuDTO) throws URISyntaxException {
        log.debug("REST request to update SysRoleMenu : {}", sysRoleMenuDTO);
        if (sysRoleMenuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysRoleMenuDTO result = sysRoleMenuService.save(sysRoleMenuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysRoleMenuDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-role-menus} : get all the sysRoleMenus.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRoleMenus in body.
     */
    @GetMapping("/sys-role-menus")
    public ResponseEntity<List<SysRoleMenuDTO>> getAllSysRoleMenus(SysRoleMenuCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysRoleMenus by criteria: {}", criteria);
        Page<SysRoleMenuDTO> page = sysRoleMenuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-role-menus/count} : count all the sysRoleMenus.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-role-menus/count")
    public ResponseEntity<Long> countSysRoleMenus(SysRoleMenuCriteria criteria) {
        log.debug("REST request to count SysRoleMenus by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysRoleMenuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-role-menus/:id} : get the "id" sysRoleMenu.
     *
     * @param id the id of the sysRoleMenuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRoleMenuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-role-menus/{id}")
    public ResponseEntity<SysRoleMenuDTO> getSysRoleMenu(@PathVariable Long id) {
        log.debug("REST request to get SysRoleMenu : {}", id);
        Optional<SysRoleMenuDTO> sysRoleMenuDTO = sysRoleMenuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRoleMenuDTO);
    }

    /**
     * {@code DELETE  /sys-role-menus/:id} : delete the "id" sysRoleMenu.
     *
     * @param id the id of the sysRoleMenuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-role-menus/{id}")
    public ResponseEntity<Void> deleteSysRoleMenu(@PathVariable Long id) {
        log.debug("REST request to delete SysRoleMenu : {}", id);
        sysRoleMenuService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
