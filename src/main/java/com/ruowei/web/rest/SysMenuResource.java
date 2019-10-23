package com.ruowei.web.rest;

import com.ruowei.service.SysMenuService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.modules.sys.pojo.SysMenuDTO;
import com.ruowei.modules.sys.pojo.SysMenuCriteria;
import com.ruowei.modules.sys.service.menu.SysMenuQueryService;

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
 * REST controller for managing {@link com.ruowei.domain.SysMenu}.
 */
@RestController
@RequestMapping("/api")
public class SysMenuResource {

    private final Logger log = LoggerFactory.getLogger(SysMenuResource.class);

    private static final String ENTITY_NAME = "sysMenu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysMenuService sysMenuService;

    private final SysMenuQueryService sysMenuQueryService;

    public SysMenuResource(SysMenuService sysMenuService, SysMenuQueryService sysMenuQueryService) {
        this.sysMenuService = sysMenuService;
        this.sysMenuQueryService = sysMenuQueryService;
    }

    /**
     * {@code POST  /sys-menus} : Create a new sysMenu.
     *
     * @param sysMenuDTO the sysMenuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysMenuDTO, or with status {@code 400 (Bad Request)} if the sysMenu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-menus")
    public ResponseEntity<SysMenuDTO> createSysMenu(@Valid @RequestBody SysMenuDTO sysMenuDTO) throws URISyntaxException {
        log.debug("REST request to save SysMenu : {}", sysMenuDTO);
        if (sysMenuDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysMenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysMenuDTO result = sysMenuService.save(sysMenuDTO);
        return ResponseEntity.created(new URI("/api/sys-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-menus} : Updates an existing sysMenu.
     *
     * @param sysMenuDTO the sysMenuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysMenuDTO,
     * or with status {@code 400 (Bad Request)} if the sysMenuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysMenuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-menus")
    public ResponseEntity<SysMenuDTO> updateSysMenu(@Valid @RequestBody SysMenuDTO sysMenuDTO) throws URISyntaxException {
        log.debug("REST request to update SysMenu : {}", sysMenuDTO);
        if (sysMenuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysMenuDTO result = sysMenuService.save(sysMenuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysMenuDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-menus} : get all the sysMenus.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysMenus in body.
     */
    @GetMapping("/sys-menus")
    public ResponseEntity<List<SysMenuDTO>> getAllSysMenus(SysMenuCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysMenus by criteria: {}", criteria);
        Page<SysMenuDTO> page = sysMenuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-menus/count} : count all the sysMenus.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-menus/count")
    public ResponseEntity<Long> countSysMenus(SysMenuCriteria criteria) {
        log.debug("REST request to count SysMenus by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysMenuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-menus/:id} : get the "id" sysMenu.
     *
     * @param id the id of the sysMenuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysMenuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-menus/{id}")
    public ResponseEntity<SysMenuDTO> getSysMenu(@PathVariable Long id) {
        log.debug("REST request to get SysMenu : {}", id);
        Optional<SysMenuDTO> sysMenuDTO = sysMenuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysMenuDTO);
    }

    /**
     * {@code DELETE  /sys-menus/:id} : delete the "id" sysMenu.
     *
     * @param id the id of the sysMenuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-menus/{id}")
    public ResponseEntity<Void> deleteSysMenu(@PathVariable Long id) {
        log.debug("REST request to delete SysMenu : {}", id);
        sysMenuService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
