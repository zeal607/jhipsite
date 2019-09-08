package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysEmployeePost;
import com.ruowei.repository.SysEmployeePostRepository;
import com.ruowei.service.SysEmployeePostService;
import com.ruowei.service.dto.SysEmployeePostDTO;
import com.ruowei.service.mapper.SysEmployeePostMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysEmployeePostCriteria;
import com.ruowei.service.SysEmployeePostQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ruowei.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SysEmployeePostResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysEmployeePostResourceIT {

    private static final String DEFAULT_SYS_EMPLOYEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_EMPLOYEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_POST_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_POST_ID = "BBBBBBBBBB";

    @Autowired
    private SysEmployeePostRepository sysEmployeePostRepository;

    @Autowired
    private SysEmployeePostMapper sysEmployeePostMapper;

    @Autowired
    private SysEmployeePostService sysEmployeePostService;

    @Autowired
    private SysEmployeePostQueryService sysEmployeePostQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSysEmployeePostMockMvc;

    private SysEmployeePost sysEmployeePost;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysEmployeePostResource sysEmployeePostResource = new SysEmployeePostResource(sysEmployeePostService, sysEmployeePostQueryService);
        this.restSysEmployeePostMockMvc = MockMvcBuilders.standaloneSetup(sysEmployeePostResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysEmployeePost createEntity(EntityManager em) {
        SysEmployeePost sysEmployeePost = new SysEmployeePost()
            .sysEmployeeId(DEFAULT_SYS_EMPLOYEE_ID)
            .sysPostId(DEFAULT_SYS_POST_ID);
        return sysEmployeePost;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysEmployeePost createUpdatedEntity(EntityManager em) {
        SysEmployeePost sysEmployeePost = new SysEmployeePost()
            .sysEmployeeId(UPDATED_SYS_EMPLOYEE_ID)
            .sysPostId(UPDATED_SYS_POST_ID);
        return sysEmployeePost;
    }

    @BeforeEach
    public void initTest() {
        sysEmployeePost = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysEmployeePost() throws Exception {
        int databaseSizeBeforeCreate = sysEmployeePostRepository.findAll().size();

        // Create the SysEmployeePost
        SysEmployeePostDTO sysEmployeePostDTO = sysEmployeePostMapper.toDto(sysEmployeePost);
        restSysEmployeePostMockMvc.perform(post("/api/sys-employee-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeePostDTO)))
            .andExpect(status().isCreated());

        // Validate the SysEmployeePost in the database
        List<SysEmployeePost> sysEmployeePostList = sysEmployeePostRepository.findAll();
        assertThat(sysEmployeePostList).hasSize(databaseSizeBeforeCreate + 1);
        SysEmployeePost testSysEmployeePost = sysEmployeePostList.get(sysEmployeePostList.size() - 1);
        assertThat(testSysEmployeePost.getSysEmployeeId()).isEqualTo(DEFAULT_SYS_EMPLOYEE_ID);
        assertThat(testSysEmployeePost.getSysPostId()).isEqualTo(DEFAULT_SYS_POST_ID);
    }

    @Test
    @Transactional
    public void createSysEmployeePostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysEmployeePostRepository.findAll().size();

        // Create the SysEmployeePost with an existing ID
        sysEmployeePost.setId(1L);
        SysEmployeePostDTO sysEmployeePostDTO = sysEmployeePostMapper.toDto(sysEmployeePost);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysEmployeePostMockMvc.perform(post("/api/sys-employee-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeePostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysEmployeePost in the database
        List<SysEmployeePost> sysEmployeePostList = sysEmployeePostRepository.findAll();
        assertThat(sysEmployeePostList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSysEmployeeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysEmployeePostRepository.findAll().size();
        // set the field null
        sysEmployeePost.setSysEmployeeId(null);

        // Create the SysEmployeePost, which fails.
        SysEmployeePostDTO sysEmployeePostDTO = sysEmployeePostMapper.toDto(sysEmployeePost);

        restSysEmployeePostMockMvc.perform(post("/api/sys-employee-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeePostDTO)))
            .andExpect(status().isBadRequest());

        List<SysEmployeePost> sysEmployeePostList = sysEmployeePostRepository.findAll();
        assertThat(sysEmployeePostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSysPostIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysEmployeePostRepository.findAll().size();
        // set the field null
        sysEmployeePost.setSysPostId(null);

        // Create the SysEmployeePost, which fails.
        SysEmployeePostDTO sysEmployeePostDTO = sysEmployeePostMapper.toDto(sysEmployeePost);

        restSysEmployeePostMockMvc.perform(post("/api/sys-employee-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeePostDTO)))
            .andExpect(status().isBadRequest());

        List<SysEmployeePost> sysEmployeePostList = sysEmployeePostRepository.findAll();
        assertThat(sysEmployeePostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysEmployeePosts() throws Exception {
        // Initialize the database
        sysEmployeePostRepository.saveAndFlush(sysEmployeePost);

        // Get all the sysEmployeePostList
        restSysEmployeePostMockMvc.perform(get("/api/sys-employee-posts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysEmployeePost.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysEmployeeId").value(hasItem(DEFAULT_SYS_EMPLOYEE_ID.toString())))
            .andExpect(jsonPath("$.[*].sysPostId").value(hasItem(DEFAULT_SYS_POST_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getSysEmployeePost() throws Exception {
        // Initialize the database
        sysEmployeePostRepository.saveAndFlush(sysEmployeePost);

        // Get the sysEmployeePost
        restSysEmployeePostMockMvc.perform(get("/api/sys-employee-posts/{id}", sysEmployeePost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysEmployeePost.getId().intValue()))
            .andExpect(jsonPath("$.sysEmployeeId").value(DEFAULT_SYS_EMPLOYEE_ID.toString()))
            .andExpect(jsonPath("$.sysPostId").value(DEFAULT_SYS_POST_ID.toString()));
    }

    @Test
    @Transactional
    public void getAllSysEmployeePostsBySysEmployeeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeePostRepository.saveAndFlush(sysEmployeePost);

        // Get all the sysEmployeePostList where sysEmployeeId equals to DEFAULT_SYS_EMPLOYEE_ID
        defaultSysEmployeePostShouldBeFound("sysEmployeeId.equals=" + DEFAULT_SYS_EMPLOYEE_ID);

        // Get all the sysEmployeePostList where sysEmployeeId equals to UPDATED_SYS_EMPLOYEE_ID
        defaultSysEmployeePostShouldNotBeFound("sysEmployeeId.equals=" + UPDATED_SYS_EMPLOYEE_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeePostsBySysEmployeeIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeePostRepository.saveAndFlush(sysEmployeePost);

        // Get all the sysEmployeePostList where sysEmployeeId in DEFAULT_SYS_EMPLOYEE_ID or UPDATED_SYS_EMPLOYEE_ID
        defaultSysEmployeePostShouldBeFound("sysEmployeeId.in=" + DEFAULT_SYS_EMPLOYEE_ID + "," + UPDATED_SYS_EMPLOYEE_ID);

        // Get all the sysEmployeePostList where sysEmployeeId equals to UPDATED_SYS_EMPLOYEE_ID
        defaultSysEmployeePostShouldNotBeFound("sysEmployeeId.in=" + UPDATED_SYS_EMPLOYEE_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeePostsBySysEmployeeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeePostRepository.saveAndFlush(sysEmployeePost);

        // Get all the sysEmployeePostList where sysEmployeeId is not null
        defaultSysEmployeePostShouldBeFound("sysEmployeeId.specified=true");

        // Get all the sysEmployeePostList where sysEmployeeId is null
        defaultSysEmployeePostShouldNotBeFound("sysEmployeeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeePostsBySysPostIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeePostRepository.saveAndFlush(sysEmployeePost);

        // Get all the sysEmployeePostList where sysPostId equals to DEFAULT_SYS_POST_ID
        defaultSysEmployeePostShouldBeFound("sysPostId.equals=" + DEFAULT_SYS_POST_ID);

        // Get all the sysEmployeePostList where sysPostId equals to UPDATED_SYS_POST_ID
        defaultSysEmployeePostShouldNotBeFound("sysPostId.equals=" + UPDATED_SYS_POST_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeePostsBySysPostIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeePostRepository.saveAndFlush(sysEmployeePost);

        // Get all the sysEmployeePostList where sysPostId in DEFAULT_SYS_POST_ID or UPDATED_SYS_POST_ID
        defaultSysEmployeePostShouldBeFound("sysPostId.in=" + DEFAULT_SYS_POST_ID + "," + UPDATED_SYS_POST_ID);

        // Get all the sysEmployeePostList where sysPostId equals to UPDATED_SYS_POST_ID
        defaultSysEmployeePostShouldNotBeFound("sysPostId.in=" + UPDATED_SYS_POST_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeePostsBySysPostIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeePostRepository.saveAndFlush(sysEmployeePost);

        // Get all the sysEmployeePostList where sysPostId is not null
        defaultSysEmployeePostShouldBeFound("sysPostId.specified=true");

        // Get all the sysEmployeePostList where sysPostId is null
        defaultSysEmployeePostShouldNotBeFound("sysPostId.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysEmployeePostShouldBeFound(String filter) throws Exception {
        restSysEmployeePostMockMvc.perform(get("/api/sys-employee-posts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysEmployeePost.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysEmployeeId").value(hasItem(DEFAULT_SYS_EMPLOYEE_ID)))
            .andExpect(jsonPath("$.[*].sysPostId").value(hasItem(DEFAULT_SYS_POST_ID)));

        // Check, that the count call also returns 1
        restSysEmployeePostMockMvc.perform(get("/api/sys-employee-posts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysEmployeePostShouldNotBeFound(String filter) throws Exception {
        restSysEmployeePostMockMvc.perform(get("/api/sys-employee-posts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysEmployeePostMockMvc.perform(get("/api/sys-employee-posts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysEmployeePost() throws Exception {
        // Get the sysEmployeePost
        restSysEmployeePostMockMvc.perform(get("/api/sys-employee-posts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysEmployeePost() throws Exception {
        // Initialize the database
        sysEmployeePostRepository.saveAndFlush(sysEmployeePost);

        int databaseSizeBeforeUpdate = sysEmployeePostRepository.findAll().size();

        // Update the sysEmployeePost
        SysEmployeePost updatedSysEmployeePost = sysEmployeePostRepository.findById(sysEmployeePost.getId()).get();
        // Disconnect from session so that the updates on updatedSysEmployeePost are not directly saved in db
        em.detach(updatedSysEmployeePost);
        updatedSysEmployeePost
            .sysEmployeeId(UPDATED_SYS_EMPLOYEE_ID)
            .sysPostId(UPDATED_SYS_POST_ID);
        SysEmployeePostDTO sysEmployeePostDTO = sysEmployeePostMapper.toDto(updatedSysEmployeePost);

        restSysEmployeePostMockMvc.perform(put("/api/sys-employee-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeePostDTO)))
            .andExpect(status().isOk());

        // Validate the SysEmployeePost in the database
        List<SysEmployeePost> sysEmployeePostList = sysEmployeePostRepository.findAll();
        assertThat(sysEmployeePostList).hasSize(databaseSizeBeforeUpdate);
        SysEmployeePost testSysEmployeePost = sysEmployeePostList.get(sysEmployeePostList.size() - 1);
        assertThat(testSysEmployeePost.getSysEmployeeId()).isEqualTo(UPDATED_SYS_EMPLOYEE_ID);
        assertThat(testSysEmployeePost.getSysPostId()).isEqualTo(UPDATED_SYS_POST_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSysEmployeePost() throws Exception {
        int databaseSizeBeforeUpdate = sysEmployeePostRepository.findAll().size();

        // Create the SysEmployeePost
        SysEmployeePostDTO sysEmployeePostDTO = sysEmployeePostMapper.toDto(sysEmployeePost);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysEmployeePostMockMvc.perform(put("/api/sys-employee-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeePostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysEmployeePost in the database
        List<SysEmployeePost> sysEmployeePostList = sysEmployeePostRepository.findAll();
        assertThat(sysEmployeePostList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysEmployeePost() throws Exception {
        // Initialize the database
        sysEmployeePostRepository.saveAndFlush(sysEmployeePost);

        int databaseSizeBeforeDelete = sysEmployeePostRepository.findAll().size();

        // Delete the sysEmployeePost
        restSysEmployeePostMockMvc.perform(delete("/api/sys-employee-posts/{id}", sysEmployeePost.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysEmployeePost> sysEmployeePostList = sysEmployeePostRepository.findAll();
        assertThat(sysEmployeePostList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysEmployeePost.class);
        SysEmployeePost sysEmployeePost1 = new SysEmployeePost();
        sysEmployeePost1.setId(1L);
        SysEmployeePost sysEmployeePost2 = new SysEmployeePost();
        sysEmployeePost2.setId(sysEmployeePost1.getId());
        assertThat(sysEmployeePost1).isEqualTo(sysEmployeePost2);
        sysEmployeePost2.setId(2L);
        assertThat(sysEmployeePost1).isNotEqualTo(sysEmployeePost2);
        sysEmployeePost1.setId(null);
        assertThat(sysEmployeePost1).isNotEqualTo(sysEmployeePost2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysEmployeePostDTO.class);
        SysEmployeePostDTO sysEmployeePostDTO1 = new SysEmployeePostDTO();
        sysEmployeePostDTO1.setId(1L);
        SysEmployeePostDTO sysEmployeePostDTO2 = new SysEmployeePostDTO();
        assertThat(sysEmployeePostDTO1).isNotEqualTo(sysEmployeePostDTO2);
        sysEmployeePostDTO2.setId(sysEmployeePostDTO1.getId());
        assertThat(sysEmployeePostDTO1).isEqualTo(sysEmployeePostDTO2);
        sysEmployeePostDTO2.setId(2L);
        assertThat(sysEmployeePostDTO1).isNotEqualTo(sysEmployeePostDTO2);
        sysEmployeePostDTO1.setId(null);
        assertThat(sysEmployeePostDTO1).isNotEqualTo(sysEmployeePostDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysEmployeePostMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysEmployeePostMapper.fromId(null)).isNull();
    }
}
