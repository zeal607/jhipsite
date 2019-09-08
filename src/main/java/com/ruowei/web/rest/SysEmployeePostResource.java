package com.ruowei.web.rest;

import com.ruowei.service.SysEmployeePostService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.service.dto.SysEmployeePostDTO;
import com.ruowei.service.dto.SysEmployeePostCriteria;
import com.ruowei.service.SysEmployeePostQueryService;

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
 * REST controller for managing {@link com.ruowei.domain.SysEmployeePost}.
 */
@RestController
@RequestMapping("/api")
public class SysEmployeePostResource {

    private final Logger log = LoggerFactory.getLogger(SysEmployeePostResource.class);

    private static final String ENTITY_NAME = "sysEmployeePost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysEmployeePostService sysEmployeePostService;

    private final SysEmployeePostQueryService sysEmployeePostQueryService;

    public SysEmployeePostResource(SysEmployeePostService sysEmployeePostService, SysEmployeePostQueryService sysEmployeePostQueryService) {
        this.sysEmployeePostService = sysEmployeePostService;
        this.sysEmployeePostQueryService = sysEmployeePostQueryService;
    }

    /**
     * {@code POST  /sys-employee-posts} : Create a new sysEmployeePost.
     *
     * @param sysEmployeePostDTO the sysEmployeePostDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysEmployeePostDTO, or with status {@code 400 (Bad Request)} if the sysEmployeePost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-employee-posts")
    public ResponseEntity<SysEmployeePostDTO> createSysEmployeePost(@Valid @RequestBody SysEmployeePostDTO sysEmployeePostDTO) throws URISyntaxException {
        log.debug("REST request to save SysEmployeePost : {}", sysEmployeePostDTO);
        if (sysEmployeePostDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysEmployeePost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysEmployeePostDTO result = sysEmployeePostService.save(sysEmployeePostDTO);
        return ResponseEntity.created(new URI("/api/sys-employee-posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-employee-posts} : Updates an existing sysEmployeePost.
     *
     * @param sysEmployeePostDTO the sysEmployeePostDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysEmployeePostDTO,
     * or with status {@code 400 (Bad Request)} if the sysEmployeePostDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysEmployeePostDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-employee-posts")
    public ResponseEntity<SysEmployeePostDTO> updateSysEmployeePost(@Valid @RequestBody SysEmployeePostDTO sysEmployeePostDTO) throws URISyntaxException {
        log.debug("REST request to update SysEmployeePost : {}", sysEmployeePostDTO);
        if (sysEmployeePostDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysEmployeePostDTO result = sysEmployeePostService.save(sysEmployeePostDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysEmployeePostDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-employee-posts} : get all the sysEmployeePosts.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysEmployeePosts in body.
     */
    @GetMapping("/sys-employee-posts")
    public ResponseEntity<List<SysEmployeePostDTO>> getAllSysEmployeePosts(SysEmployeePostCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysEmployeePosts by criteria: {}", criteria);
        Page<SysEmployeePostDTO> page = sysEmployeePostQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-employee-posts/count} : count all the sysEmployeePosts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-employee-posts/count")
    public ResponseEntity<Long> countSysEmployeePosts(SysEmployeePostCriteria criteria) {
        log.debug("REST request to count SysEmployeePosts by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysEmployeePostQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-employee-posts/:id} : get the "id" sysEmployeePost.
     *
     * @param id the id of the sysEmployeePostDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysEmployeePostDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-employee-posts/{id}")
    public ResponseEntity<SysEmployeePostDTO> getSysEmployeePost(@PathVariable Long id) {
        log.debug("REST request to get SysEmployeePost : {}", id);
        Optional<SysEmployeePostDTO> sysEmployeePostDTO = sysEmployeePostService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysEmployeePostDTO);
    }

    /**
     * {@code DELETE  /sys-employee-posts/:id} : delete the "id" sysEmployeePost.
     *
     * @param id the id of the sysEmployeePostDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-employee-posts/{id}")
    public ResponseEntity<Void> deleteSysEmployeePost(@PathVariable Long id) {
        log.debug("REST request to delete SysEmployeePost : {}", id);
        sysEmployeePostService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
