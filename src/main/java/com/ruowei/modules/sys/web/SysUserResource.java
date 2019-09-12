package com.ruowei.modules.sys.web;

import com.ruowei.modules.sys.service.SysUserQueryService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import com.ruowei.modules.sys.pojo.SysUserCriteria;

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
 * REST controller for managing {@link com.ruowei.modules.sys.domain.SysUser}.
 */
@RestController
@RequestMapping("/api")
public class SysUserResource {

    private final Logger log = LoggerFactory.getLogger(SysUserResource.class);

    private static final String ENTITY_NAME = "sysUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

//    private final SysUserServiceSupport sysUserService;
//
    private final SysUserQueryService sysUserQueryService;

    public SysUserResource( SysUserQueryService sysUserQueryService) {
        this.sysUserQueryService = sysUserQueryService;
    }

    /**
     * {@code POST  /sys-users} : Create a new sysUser.
     *
     * @param sysUserDTO the sysUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysUserDTO, or with status {@code 400 (Bad Request)} if the sysUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PostMapping("/sys-users")
//    public ResponseEntity<SysUserDTO> createSysUser(@Valid @RequestBody SysUserDTO sysUserDTO) throws URISyntaxException {
//        log.debug("REST request to save SysUser : {}", sysUserDTO);
//        if (sysUserDTO.getId() != null) {
//            throw new BadRequestAlertException("A new sysUser cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        SysUserDTO result = sysUserService.save(sysUserDTO);
//        return ResponseEntity.created(new URI("/api/sys-users/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * {@code PUT  /sys-users} : Updates an existing sysUser.
     *
     * @param sysUserDTO the sysUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysUserDTO,
     * or with status {@code 400 (Bad Request)} if the sysUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PutMapping("/sys-users")
//    public ResponseEntity<SysUserDTO> updateSysUser(@Valid @RequestBody SysUserDTO sysUserDTO) throws URISyntaxException {
//        log.debug("REST request to update SysUser : {}", sysUserDTO);
//        if (sysUserDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        SysUserDTO result = sysUserService.save(sysUserDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysUserDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * {@code GET  /sys-users} : get all the sysUsers.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysUsers in body.
     */
    @GetMapping("/sys-users")
    public ResponseEntity<List<SysUserDTO>> getAllSysUsers(SysUserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysUsers by criteria: {}", criteria);
        Page<SysUserDTO> page = sysUserQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-users/count} : count all the sysUsers.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
//    @GetMapping("/sys-users/count")
//    public ResponseEntity<Long> countSysUsers(SysUserCriteria criteria) {
//        log.debug("REST request to count SysUsers by criteria: {}", criteria);
//        return ResponseEntity.ok().body(sysUserQueryService.countByCriteria(criteria));
//    }

    /**
     * {@code GET  /sys-users/:id} : get the "id" sysUser.
     *
     * @param id the id of the sysUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysUserDTO, or with status {@code 404 (Not Found)}.
     */
//    @GetMapping("/sys-users/{id}")
//    public ResponseEntity<SysUserDTO> getSysUser(@PathVariable Long id) {
//        log.debug("REST request to get SysUser : {}", id);
//        Optional<SysUserDTO> sysUserDTO = sysUserService.get(id);
//        return ResponseUtil.wrapOrNotFound(sysUserDTO);
//    }

    /**
     * {@code DELETE  /sys-users/:id} : delete the "id" sysUser.
     *
     * @param id the id of the sysUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
//    @DeleteMapping("/sys-users/{id}")
//    public ResponseEntity<Void> deleteSysUser(@PathVariable Long id) {
//        log.debug("REST request to delete SysUser : {}", id);
//        sysUserService.delete(id);
//        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
//    }
}
