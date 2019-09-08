package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysPost;
import com.ruowei.repository.SysPostRepository;
import com.ruowei.service.SysPostService;
import com.ruowei.service.dto.SysPostDTO;
import com.ruowei.service.mapper.SysPostMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysPostCriteria;
import com.ruowei.service.SysPostQueryService;

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

import com.ruowei.domain.enumeration.PostType;
import com.ruowei.domain.enumeration.PostStatusType;
/**
 * Integration tests for the {@link SysPostResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysPostResourceIT {

    private static final String DEFAULT_POST_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POST_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_POST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_POST_NAME = "BBBBBBBBBB";

    private static final PostType DEFAULT_POST_TYPE = PostType.SENIOR;
    private static final PostType UPDATED_POST_TYPE = PostType.MIDDLE;

    private static final PostStatusType DEFAULT_STATUS = PostStatusType.NORMAL;
    private static final PostStatusType UPDATED_STATUS = PostStatusType.DELETE;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private SysPostRepository sysPostRepository;

    @Autowired
    private SysPostMapper sysPostMapper;

    @Autowired
    private SysPostService sysPostService;

    @Autowired
    private SysPostQueryService sysPostQueryService;

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

    private MockMvc restSysPostMockMvc;

    private SysPost sysPost;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysPostResource sysPostResource = new SysPostResource(sysPostService, sysPostQueryService);
        this.restSysPostMockMvc = MockMvcBuilders.standaloneSetup(sysPostResource)
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
    public static SysPost createEntity(EntityManager em) {
        SysPost sysPost = new SysPost()
            .postCode(DEFAULT_POST_CODE)
            .postName(DEFAULT_POST_NAME)
            .postType(DEFAULT_POST_TYPE)
            .status(DEFAULT_STATUS)
            .remarks(DEFAULT_REMARKS);
        return sysPost;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysPost createUpdatedEntity(EntityManager em) {
        SysPost sysPost = new SysPost()
            .postCode(UPDATED_POST_CODE)
            .postName(UPDATED_POST_NAME)
            .postType(UPDATED_POST_TYPE)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        return sysPost;
    }

    @BeforeEach
    public void initTest() {
        sysPost = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysPost() throws Exception {
        int databaseSizeBeforeCreate = sysPostRepository.findAll().size();

        // Create the SysPost
        SysPostDTO sysPostDTO = sysPostMapper.toDto(sysPost);
        restSysPostMockMvc.perform(post("/api/sys-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPostDTO)))
            .andExpect(status().isCreated());

        // Validate the SysPost in the database
        List<SysPost> sysPostList = sysPostRepository.findAll();
        assertThat(sysPostList).hasSize(databaseSizeBeforeCreate + 1);
        SysPost testSysPost = sysPostList.get(sysPostList.size() - 1);
        assertThat(testSysPost.getPostCode()).isEqualTo(DEFAULT_POST_CODE);
        assertThat(testSysPost.getPostName()).isEqualTo(DEFAULT_POST_NAME);
        assertThat(testSysPost.getPostType()).isEqualTo(DEFAULT_POST_TYPE);
        assertThat(testSysPost.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysPost.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createSysPostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysPostRepository.findAll().size();

        // Create the SysPost with an existing ID
        sysPost.setId(1L);
        SysPostDTO sysPostDTO = sysPostMapper.toDto(sysPost);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysPostMockMvc.perform(post("/api/sys-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysPost in the database
        List<SysPost> sysPostList = sysPostRepository.findAll();
        assertThat(sysPostList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPostCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysPostRepository.findAll().size();
        // set the field null
        sysPost.setPostCode(null);

        // Create the SysPost, which fails.
        SysPostDTO sysPostDTO = sysPostMapper.toDto(sysPost);

        restSysPostMockMvc.perform(post("/api/sys-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPostDTO)))
            .andExpect(status().isBadRequest());

        List<SysPost> sysPostList = sysPostRepository.findAll();
        assertThat(sysPostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPostNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysPostRepository.findAll().size();
        // set the field null
        sysPost.setPostName(null);

        // Create the SysPost, which fails.
        SysPostDTO sysPostDTO = sysPostMapper.toDto(sysPost);

        restSysPostMockMvc.perform(post("/api/sys-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPostDTO)))
            .andExpect(status().isBadRequest());

        List<SysPost> sysPostList = sysPostRepository.findAll();
        assertThat(sysPostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysPostRepository.findAll().size();
        // set the field null
        sysPost.setStatus(null);

        // Create the SysPost, which fails.
        SysPostDTO sysPostDTO = sysPostMapper.toDto(sysPost);

        restSysPostMockMvc.perform(post("/api/sys-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPostDTO)))
            .andExpect(status().isBadRequest());

        List<SysPost> sysPostList = sysPostRepository.findAll();
        assertThat(sysPostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysPosts() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList
        restSysPostMockMvc.perform(get("/api/sys-posts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysPost.getId().intValue())))
            .andExpect(jsonPath("$.[*].postCode").value(hasItem(DEFAULT_POST_CODE.toString())))
            .andExpect(jsonPath("$.[*].postName").value(hasItem(DEFAULT_POST_NAME.toString())))
            .andExpect(jsonPath("$.[*].postType").value(hasItem(DEFAULT_POST_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }
    
    @Test
    @Transactional
    public void getSysPost() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get the sysPost
        restSysPostMockMvc.perform(get("/api/sys-posts/{id}", sysPost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysPost.getId().intValue()))
            .andExpect(jsonPath("$.postCode").value(DEFAULT_POST_CODE.toString()))
            .andExpect(jsonPath("$.postName").value(DEFAULT_POST_NAME.toString()))
            .andExpect(jsonPath("$.postType").value(DEFAULT_POST_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getAllSysPostsByPostCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where postCode equals to DEFAULT_POST_CODE
        defaultSysPostShouldBeFound("postCode.equals=" + DEFAULT_POST_CODE);

        // Get all the sysPostList where postCode equals to UPDATED_POST_CODE
        defaultSysPostShouldNotBeFound("postCode.equals=" + UPDATED_POST_CODE);
    }

    @Test
    @Transactional
    public void getAllSysPostsByPostCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where postCode in DEFAULT_POST_CODE or UPDATED_POST_CODE
        defaultSysPostShouldBeFound("postCode.in=" + DEFAULT_POST_CODE + "," + UPDATED_POST_CODE);

        // Get all the sysPostList where postCode equals to UPDATED_POST_CODE
        defaultSysPostShouldNotBeFound("postCode.in=" + UPDATED_POST_CODE);
    }

    @Test
    @Transactional
    public void getAllSysPostsByPostCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where postCode is not null
        defaultSysPostShouldBeFound("postCode.specified=true");

        // Get all the sysPostList where postCode is null
        defaultSysPostShouldNotBeFound("postCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysPostsByPostNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where postName equals to DEFAULT_POST_NAME
        defaultSysPostShouldBeFound("postName.equals=" + DEFAULT_POST_NAME);

        // Get all the sysPostList where postName equals to UPDATED_POST_NAME
        defaultSysPostShouldNotBeFound("postName.equals=" + UPDATED_POST_NAME);
    }

    @Test
    @Transactional
    public void getAllSysPostsByPostNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where postName in DEFAULT_POST_NAME or UPDATED_POST_NAME
        defaultSysPostShouldBeFound("postName.in=" + DEFAULT_POST_NAME + "," + UPDATED_POST_NAME);

        // Get all the sysPostList where postName equals to UPDATED_POST_NAME
        defaultSysPostShouldNotBeFound("postName.in=" + UPDATED_POST_NAME);
    }

    @Test
    @Transactional
    public void getAllSysPostsByPostNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where postName is not null
        defaultSysPostShouldBeFound("postName.specified=true");

        // Get all the sysPostList where postName is null
        defaultSysPostShouldNotBeFound("postName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysPostsByPostTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where postType equals to DEFAULT_POST_TYPE
        defaultSysPostShouldBeFound("postType.equals=" + DEFAULT_POST_TYPE);

        // Get all the sysPostList where postType equals to UPDATED_POST_TYPE
        defaultSysPostShouldNotBeFound("postType.equals=" + UPDATED_POST_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysPostsByPostTypeIsInShouldWork() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where postType in DEFAULT_POST_TYPE or UPDATED_POST_TYPE
        defaultSysPostShouldBeFound("postType.in=" + DEFAULT_POST_TYPE + "," + UPDATED_POST_TYPE);

        // Get all the sysPostList where postType equals to UPDATED_POST_TYPE
        defaultSysPostShouldNotBeFound("postType.in=" + UPDATED_POST_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysPostsByPostTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where postType is not null
        defaultSysPostShouldBeFound("postType.specified=true");

        // Get all the sysPostList where postType is null
        defaultSysPostShouldNotBeFound("postType.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysPostsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where status equals to DEFAULT_STATUS
        defaultSysPostShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the sysPostList where status equals to UPDATED_STATUS
        defaultSysPostShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysPostsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultSysPostShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the sysPostList where status equals to UPDATED_STATUS
        defaultSysPostShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysPostsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where status is not null
        defaultSysPostShouldBeFound("status.specified=true");

        // Get all the sysPostList where status is null
        defaultSysPostShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysPostsByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where remarks equals to DEFAULT_REMARKS
        defaultSysPostShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the sysPostList where remarks equals to UPDATED_REMARKS
        defaultSysPostShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysPostsByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultSysPostShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the sysPostList where remarks equals to UPDATED_REMARKS
        defaultSysPostShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysPostsByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        // Get all the sysPostList where remarks is not null
        defaultSysPostShouldBeFound("remarks.specified=true");

        // Get all the sysPostList where remarks is null
        defaultSysPostShouldNotBeFound("remarks.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysPostShouldBeFound(String filter) throws Exception {
        restSysPostMockMvc.perform(get("/api/sys-posts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysPost.getId().intValue())))
            .andExpect(jsonPath("$.[*].postCode").value(hasItem(DEFAULT_POST_CODE)))
            .andExpect(jsonPath("$.[*].postName").value(hasItem(DEFAULT_POST_NAME)))
            .andExpect(jsonPath("$.[*].postType").value(hasItem(DEFAULT_POST_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));

        // Check, that the count call also returns 1
        restSysPostMockMvc.perform(get("/api/sys-posts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysPostShouldNotBeFound(String filter) throws Exception {
        restSysPostMockMvc.perform(get("/api/sys-posts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysPostMockMvc.perform(get("/api/sys-posts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysPost() throws Exception {
        // Get the sysPost
        restSysPostMockMvc.perform(get("/api/sys-posts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysPost() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        int databaseSizeBeforeUpdate = sysPostRepository.findAll().size();

        // Update the sysPost
        SysPost updatedSysPost = sysPostRepository.findById(sysPost.getId()).get();
        // Disconnect from session so that the updates on updatedSysPost are not directly saved in db
        em.detach(updatedSysPost);
        updatedSysPost
            .postCode(UPDATED_POST_CODE)
            .postName(UPDATED_POST_NAME)
            .postType(UPDATED_POST_TYPE)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        SysPostDTO sysPostDTO = sysPostMapper.toDto(updatedSysPost);

        restSysPostMockMvc.perform(put("/api/sys-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPostDTO)))
            .andExpect(status().isOk());

        // Validate the SysPost in the database
        List<SysPost> sysPostList = sysPostRepository.findAll();
        assertThat(sysPostList).hasSize(databaseSizeBeforeUpdate);
        SysPost testSysPost = sysPostList.get(sysPostList.size() - 1);
        assertThat(testSysPost.getPostCode()).isEqualTo(UPDATED_POST_CODE);
        assertThat(testSysPost.getPostName()).isEqualTo(UPDATED_POST_NAME);
        assertThat(testSysPost.getPostType()).isEqualTo(UPDATED_POST_TYPE);
        assertThat(testSysPost.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysPost.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingSysPost() throws Exception {
        int databaseSizeBeforeUpdate = sysPostRepository.findAll().size();

        // Create the SysPost
        SysPostDTO sysPostDTO = sysPostMapper.toDto(sysPost);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysPostMockMvc.perform(put("/api/sys-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysPost in the database
        List<SysPost> sysPostList = sysPostRepository.findAll();
        assertThat(sysPostList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysPost() throws Exception {
        // Initialize the database
        sysPostRepository.saveAndFlush(sysPost);

        int databaseSizeBeforeDelete = sysPostRepository.findAll().size();

        // Delete the sysPost
        restSysPostMockMvc.perform(delete("/api/sys-posts/{id}", sysPost.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysPost> sysPostList = sysPostRepository.findAll();
        assertThat(sysPostList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPost.class);
        SysPost sysPost1 = new SysPost();
        sysPost1.setId(1L);
        SysPost sysPost2 = new SysPost();
        sysPost2.setId(sysPost1.getId());
        assertThat(sysPost1).isEqualTo(sysPost2);
        sysPost2.setId(2L);
        assertThat(sysPost1).isNotEqualTo(sysPost2);
        sysPost1.setId(null);
        assertThat(sysPost1).isNotEqualTo(sysPost2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPostDTO.class);
        SysPostDTO sysPostDTO1 = new SysPostDTO();
        sysPostDTO1.setId(1L);
        SysPostDTO sysPostDTO2 = new SysPostDTO();
        assertThat(sysPostDTO1).isNotEqualTo(sysPostDTO2);
        sysPostDTO2.setId(sysPostDTO1.getId());
        assertThat(sysPostDTO1).isEqualTo(sysPostDTO2);
        sysPostDTO2.setId(2L);
        assertThat(sysPostDTO1).isNotEqualTo(sysPostDTO2);
        sysPostDTO1.setId(null);
        assertThat(sysPostDTO1).isNotEqualTo(sysPostDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysPostMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysPostMapper.fromId(null)).isNull();
    }
}
