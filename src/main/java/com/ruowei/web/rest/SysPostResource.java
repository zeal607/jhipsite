package com.ruowei.web.rest;

import com.ruowei.modules.sys.domain.SysPost;
import com.ruowei.modules.sys.service.post.SysPostService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.modules.sys.pojo.SysPostDTO;
import com.ruowei.modules.sys.pojo.SysPostCriteria;
import com.ruowei.modules.sys.service.post.SysPostQueryService;

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
 * REST controller for managing {@link SysPost}.
 */
@RestController
@RequestMapping("/api")
public class SysPostResource {

    private final Logger log = LoggerFactory.getLogger(SysPostResource.class);

    private static final String ENTITY_NAME = "sysPost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysPostService sysPostService;

    private final SysPostQueryService sysPostQueryService;

    public SysPostResource(SysPostService sysPostService, SysPostQueryService sysPostQueryService) {
        this.sysPostService = sysPostService;
        this.sysPostQueryService = sysPostQueryService;
    }

    /**
     * {@code POST  /sys-posts} : Create a new sysPost.
     *
     * @param sysPostDTO the sysPostDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysPostDTO, or with status {@code 400 (Bad Request)} if the sysPost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-posts")
    public ResponseEntity<SysPostDTO> createSysPost(@Valid @RequestBody SysPostDTO sysPostDTO) throws URISyntaxException {
        log.debug("REST request to save SysPost : {}", sysPostDTO);
        if (sysPostDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysPost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysPostDTO result = sysPostService.save(sysPostDTO);
        return ResponseEntity.created(new URI("/api/sys-posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-posts} : Updates an existing sysPost.
     *
     * @param sysPostDTO the sysPostDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysPostDTO,
     * or with status {@code 400 (Bad Request)} if the sysPostDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysPostDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-posts")
    public ResponseEntity<SysPostDTO> updateSysPost(@Valid @RequestBody SysPostDTO sysPostDTO) throws URISyntaxException {
        log.debug("REST request to update SysPost : {}", sysPostDTO);
        if (sysPostDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysPostDTO result = sysPostService.save(sysPostDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysPostDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-posts} : get all the sysPosts.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysPosts in body.
     */
    @GetMapping("/sys-posts")
    public ResponseEntity<List<SysPostDTO>> getAllSysPosts(SysPostCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SysPosts by criteria: {}", criteria);
        Page<SysPostDTO> page = sysPostQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /sys-posts/count} : count all the sysPosts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/sys-posts/count")
    public ResponseEntity<Long> countSysPosts(SysPostCriteria criteria) {
        log.debug("REST request to count SysPosts by criteria: {}", criteria);
        return ResponseEntity.ok().body(sysPostQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sys-posts/:id} : get the "id" sysPost.
     *
     * @param id the id of the sysPostDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysPostDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-posts/{id}")
    public ResponseEntity<SysPostDTO> getSysPost(@PathVariable Long id) {
        log.debug("REST request to get SysPost : {}", id);
        Optional<SysPostDTO> sysPostDTO = sysPostService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysPostDTO);
    }

    /**
     * {@code DELETE  /sys-posts/:id} : delete the "id" sysPost.
     *
     * @param id the id of the sysPostDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-posts/{id}")
    public ResponseEntity<Void> deleteSysPost(@PathVariable Long id) {
        log.debug("REST request to delete SysPost : {}", id);
        sysPostService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
