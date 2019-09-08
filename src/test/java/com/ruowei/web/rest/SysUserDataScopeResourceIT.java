package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysUserDataScope;
import com.ruowei.repository.SysUserDataScopeRepository;
import com.ruowei.service.SysUserDataScopeService;
import com.ruowei.service.dto.SysUserDataScopeDTO;
import com.ruowei.service.mapper.SysUserDataScopeMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysUserDataScopeCriteria;
import com.ruowei.service.SysUserDataScopeQueryService;

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

import com.ruowei.domain.enumeration.ControlType;
/**
 * Integration tests for the {@link SysUserDataScopeResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysUserDataScopeResourceIT {

    private static final String DEFAULT_SYS_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_USER_ID = "BBBBBBBBBB";

    private static final ControlType DEFAULT_CTRL_TYPE = ControlType.COMPANY;
    private static final ControlType UPDATED_CTRL_TYPE = ControlType.OFFICE;

    private static final String DEFAULT_CTRL_DATA = "AAAAAAAAAA";
    private static final String UPDATED_CTRL_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_CTRL_PERMI = "AAAAAAAAAA";
    private static final String UPDATED_CTRL_PERMI = "BBBBBBBBBB";

    @Autowired
    private SysUserDataScopeRepository sysUserDataScopeRepository;

    @Autowired
    private SysUserDataScopeMapper sysUserDataScopeMapper;

    @Autowired
    private SysUserDataScopeService sysUserDataScopeService;

    @Autowired
    private SysUserDataScopeQueryService sysUserDataScopeQueryService;

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

    private MockMvc restSysUserDataScopeMockMvc;

    private SysUserDataScope sysUserDataScope;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysUserDataScopeResource sysUserDataScopeResource = new SysUserDataScopeResource(sysUserDataScopeService, sysUserDataScopeQueryService);
        this.restSysUserDataScopeMockMvc = MockMvcBuilders.standaloneSetup(sysUserDataScopeResource)
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
    public static SysUserDataScope createEntity(EntityManager em) {
        SysUserDataScope sysUserDataScope = new SysUserDataScope()
            .sysUserId(DEFAULT_SYS_USER_ID)
            .ctrlType(DEFAULT_CTRL_TYPE)
            .ctrlData(DEFAULT_CTRL_DATA)
            .ctrlPermi(DEFAULT_CTRL_PERMI);
        return sysUserDataScope;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysUserDataScope createUpdatedEntity(EntityManager em) {
        SysUserDataScope sysUserDataScope = new SysUserDataScope()
            .sysUserId(UPDATED_SYS_USER_ID)
            .ctrlType(UPDATED_CTRL_TYPE)
            .ctrlData(UPDATED_CTRL_DATA)
            .ctrlPermi(UPDATED_CTRL_PERMI);
        return sysUserDataScope;
    }

    @BeforeEach
    public void initTest() {
        sysUserDataScope = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysUserDataScope() throws Exception {
        int databaseSizeBeforeCreate = sysUserDataScopeRepository.findAll().size();

        // Create the SysUserDataScope
        SysUserDataScopeDTO sysUserDataScopeDTO = sysUserDataScopeMapper.toDto(sysUserDataScope);
        restSysUserDataScopeMockMvc.perform(post("/api/sys-user-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDataScopeDTO)))
            .andExpect(status().isCreated());

        // Validate the SysUserDataScope in the database
        List<SysUserDataScope> sysUserDataScopeList = sysUserDataScopeRepository.findAll();
        assertThat(sysUserDataScopeList).hasSize(databaseSizeBeforeCreate + 1);
        SysUserDataScope testSysUserDataScope = sysUserDataScopeList.get(sysUserDataScopeList.size() - 1);
        assertThat(testSysUserDataScope.getSysUserId()).isEqualTo(DEFAULT_SYS_USER_ID);
        assertThat(testSysUserDataScope.getCtrlType()).isEqualTo(DEFAULT_CTRL_TYPE);
        assertThat(testSysUserDataScope.getCtrlData()).isEqualTo(DEFAULT_CTRL_DATA);
        assertThat(testSysUserDataScope.getCtrlPermi()).isEqualTo(DEFAULT_CTRL_PERMI);
    }

    @Test
    @Transactional
    public void createSysUserDataScopeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysUserDataScopeRepository.findAll().size();

        // Create the SysUserDataScope with an existing ID
        sysUserDataScope.setId(1L);
        SysUserDataScopeDTO sysUserDataScopeDTO = sysUserDataScopeMapper.toDto(sysUserDataScope);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysUserDataScopeMockMvc.perform(post("/api/sys-user-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDataScopeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysUserDataScope in the database
        List<SysUserDataScope> sysUserDataScopeList = sysUserDataScopeRepository.findAll();
        assertThat(sysUserDataScopeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSysUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysUserDataScopeRepository.findAll().size();
        // set the field null
        sysUserDataScope.setSysUserId(null);

        // Create the SysUserDataScope, which fails.
        SysUserDataScopeDTO sysUserDataScopeDTO = sysUserDataScopeMapper.toDto(sysUserDataScope);

        restSysUserDataScopeMockMvc.perform(post("/api/sys-user-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDataScopeDTO)))
            .andExpect(status().isBadRequest());

        List<SysUserDataScope> sysUserDataScopeList = sysUserDataScopeRepository.findAll();
        assertThat(sysUserDataScopeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCtrlTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysUserDataScopeRepository.findAll().size();
        // set the field null
        sysUserDataScope.setCtrlType(null);

        // Create the SysUserDataScope, which fails.
        SysUserDataScopeDTO sysUserDataScopeDTO = sysUserDataScopeMapper.toDto(sysUserDataScope);

        restSysUserDataScopeMockMvc.perform(post("/api/sys-user-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDataScopeDTO)))
            .andExpect(status().isBadRequest());

        List<SysUserDataScope> sysUserDataScopeList = sysUserDataScopeRepository.findAll();
        assertThat(sysUserDataScopeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopes() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList
        restSysUserDataScopeMockMvc.perform(get("/api/sys-user-data-scopes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUserDataScope.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysUserId").value(hasItem(DEFAULT_SYS_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].ctrlType").value(hasItem(DEFAULT_CTRL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].ctrlData").value(hasItem(DEFAULT_CTRL_DATA.toString())))
            .andExpect(jsonPath("$.[*].ctrlPermi").value(hasItem(DEFAULT_CTRL_PERMI.toString())));
    }
    
    @Test
    @Transactional
    public void getSysUserDataScope() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get the sysUserDataScope
        restSysUserDataScopeMockMvc.perform(get("/api/sys-user-data-scopes/{id}", sysUserDataScope.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysUserDataScope.getId().intValue()))
            .andExpect(jsonPath("$.sysUserId").value(DEFAULT_SYS_USER_ID.toString()))
            .andExpect(jsonPath("$.ctrlType").value(DEFAULT_CTRL_TYPE.toString()))
            .andExpect(jsonPath("$.ctrlData").value(DEFAULT_CTRL_DATA.toString()))
            .andExpect(jsonPath("$.ctrlPermi").value(DEFAULT_CTRL_PERMI.toString()));
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesBySysUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where sysUserId equals to DEFAULT_SYS_USER_ID
        defaultSysUserDataScopeShouldBeFound("sysUserId.equals=" + DEFAULT_SYS_USER_ID);

        // Get all the sysUserDataScopeList where sysUserId equals to UPDATED_SYS_USER_ID
        defaultSysUserDataScopeShouldNotBeFound("sysUserId.equals=" + UPDATED_SYS_USER_ID);
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesBySysUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where sysUserId in DEFAULT_SYS_USER_ID or UPDATED_SYS_USER_ID
        defaultSysUserDataScopeShouldBeFound("sysUserId.in=" + DEFAULT_SYS_USER_ID + "," + UPDATED_SYS_USER_ID);

        // Get all the sysUserDataScopeList where sysUserId equals to UPDATED_SYS_USER_ID
        defaultSysUserDataScopeShouldNotBeFound("sysUserId.in=" + UPDATED_SYS_USER_ID);
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesBySysUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where sysUserId is not null
        defaultSysUserDataScopeShouldBeFound("sysUserId.specified=true");

        // Get all the sysUserDataScopeList where sysUserId is null
        defaultSysUserDataScopeShouldNotBeFound("sysUserId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesByCtrlTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where ctrlType equals to DEFAULT_CTRL_TYPE
        defaultSysUserDataScopeShouldBeFound("ctrlType.equals=" + DEFAULT_CTRL_TYPE);

        // Get all the sysUserDataScopeList where ctrlType equals to UPDATED_CTRL_TYPE
        defaultSysUserDataScopeShouldNotBeFound("ctrlType.equals=" + UPDATED_CTRL_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesByCtrlTypeIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where ctrlType in DEFAULT_CTRL_TYPE or UPDATED_CTRL_TYPE
        defaultSysUserDataScopeShouldBeFound("ctrlType.in=" + DEFAULT_CTRL_TYPE + "," + UPDATED_CTRL_TYPE);

        // Get all the sysUserDataScopeList where ctrlType equals to UPDATED_CTRL_TYPE
        defaultSysUserDataScopeShouldNotBeFound("ctrlType.in=" + UPDATED_CTRL_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesByCtrlTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where ctrlType is not null
        defaultSysUserDataScopeShouldBeFound("ctrlType.specified=true");

        // Get all the sysUserDataScopeList where ctrlType is null
        defaultSysUserDataScopeShouldNotBeFound("ctrlType.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesByCtrlDataIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where ctrlData equals to DEFAULT_CTRL_DATA
        defaultSysUserDataScopeShouldBeFound("ctrlData.equals=" + DEFAULT_CTRL_DATA);

        // Get all the sysUserDataScopeList where ctrlData equals to UPDATED_CTRL_DATA
        defaultSysUserDataScopeShouldNotBeFound("ctrlData.equals=" + UPDATED_CTRL_DATA);
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesByCtrlDataIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where ctrlData in DEFAULT_CTRL_DATA or UPDATED_CTRL_DATA
        defaultSysUserDataScopeShouldBeFound("ctrlData.in=" + DEFAULT_CTRL_DATA + "," + UPDATED_CTRL_DATA);

        // Get all the sysUserDataScopeList where ctrlData equals to UPDATED_CTRL_DATA
        defaultSysUserDataScopeShouldNotBeFound("ctrlData.in=" + UPDATED_CTRL_DATA);
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesByCtrlDataIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where ctrlData is not null
        defaultSysUserDataScopeShouldBeFound("ctrlData.specified=true");

        // Get all the sysUserDataScopeList where ctrlData is null
        defaultSysUserDataScopeShouldNotBeFound("ctrlData.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesByCtrlPermiIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where ctrlPermi equals to DEFAULT_CTRL_PERMI
        defaultSysUserDataScopeShouldBeFound("ctrlPermi.equals=" + DEFAULT_CTRL_PERMI);

        // Get all the sysUserDataScopeList where ctrlPermi equals to UPDATED_CTRL_PERMI
        defaultSysUserDataScopeShouldNotBeFound("ctrlPermi.equals=" + UPDATED_CTRL_PERMI);
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesByCtrlPermiIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where ctrlPermi in DEFAULT_CTRL_PERMI or UPDATED_CTRL_PERMI
        defaultSysUserDataScopeShouldBeFound("ctrlPermi.in=" + DEFAULT_CTRL_PERMI + "," + UPDATED_CTRL_PERMI);

        // Get all the sysUserDataScopeList where ctrlPermi equals to UPDATED_CTRL_PERMI
        defaultSysUserDataScopeShouldNotBeFound("ctrlPermi.in=" + UPDATED_CTRL_PERMI);
    }

    @Test
    @Transactional
    public void getAllSysUserDataScopesByCtrlPermiIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        // Get all the sysUserDataScopeList where ctrlPermi is not null
        defaultSysUserDataScopeShouldBeFound("ctrlPermi.specified=true");

        // Get all the sysUserDataScopeList where ctrlPermi is null
        defaultSysUserDataScopeShouldNotBeFound("ctrlPermi.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysUserDataScopeShouldBeFound(String filter) throws Exception {
        restSysUserDataScopeMockMvc.perform(get("/api/sys-user-data-scopes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUserDataScope.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysUserId").value(hasItem(DEFAULT_SYS_USER_ID)))
            .andExpect(jsonPath("$.[*].ctrlType").value(hasItem(DEFAULT_CTRL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].ctrlData").value(hasItem(DEFAULT_CTRL_DATA)))
            .andExpect(jsonPath("$.[*].ctrlPermi").value(hasItem(DEFAULT_CTRL_PERMI)));

        // Check, that the count call also returns 1
        restSysUserDataScopeMockMvc.perform(get("/api/sys-user-data-scopes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysUserDataScopeShouldNotBeFound(String filter) throws Exception {
        restSysUserDataScopeMockMvc.perform(get("/api/sys-user-data-scopes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysUserDataScopeMockMvc.perform(get("/api/sys-user-data-scopes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysUserDataScope() throws Exception {
        // Get the sysUserDataScope
        restSysUserDataScopeMockMvc.perform(get("/api/sys-user-data-scopes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysUserDataScope() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        int databaseSizeBeforeUpdate = sysUserDataScopeRepository.findAll().size();

        // Update the sysUserDataScope
        SysUserDataScope updatedSysUserDataScope = sysUserDataScopeRepository.findById(sysUserDataScope.getId()).get();
        // Disconnect from session so that the updates on updatedSysUserDataScope are not directly saved in db
        em.detach(updatedSysUserDataScope);
        updatedSysUserDataScope
            .sysUserId(UPDATED_SYS_USER_ID)
            .ctrlType(UPDATED_CTRL_TYPE)
            .ctrlData(UPDATED_CTRL_DATA)
            .ctrlPermi(UPDATED_CTRL_PERMI);
        SysUserDataScopeDTO sysUserDataScopeDTO = sysUserDataScopeMapper.toDto(updatedSysUserDataScope);

        restSysUserDataScopeMockMvc.perform(put("/api/sys-user-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDataScopeDTO)))
            .andExpect(status().isOk());

        // Validate the SysUserDataScope in the database
        List<SysUserDataScope> sysUserDataScopeList = sysUserDataScopeRepository.findAll();
        assertThat(sysUserDataScopeList).hasSize(databaseSizeBeforeUpdate);
        SysUserDataScope testSysUserDataScope = sysUserDataScopeList.get(sysUserDataScopeList.size() - 1);
        assertThat(testSysUserDataScope.getSysUserId()).isEqualTo(UPDATED_SYS_USER_ID);
        assertThat(testSysUserDataScope.getCtrlType()).isEqualTo(UPDATED_CTRL_TYPE);
        assertThat(testSysUserDataScope.getCtrlData()).isEqualTo(UPDATED_CTRL_DATA);
        assertThat(testSysUserDataScope.getCtrlPermi()).isEqualTo(UPDATED_CTRL_PERMI);
    }

    @Test
    @Transactional
    public void updateNonExistingSysUserDataScope() throws Exception {
        int databaseSizeBeforeUpdate = sysUserDataScopeRepository.findAll().size();

        // Create the SysUserDataScope
        SysUserDataScopeDTO sysUserDataScopeDTO = sysUserDataScopeMapper.toDto(sysUserDataScope);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysUserDataScopeMockMvc.perform(put("/api/sys-user-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDataScopeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysUserDataScope in the database
        List<SysUserDataScope> sysUserDataScopeList = sysUserDataScopeRepository.findAll();
        assertThat(sysUserDataScopeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysUserDataScope() throws Exception {
        // Initialize the database
        sysUserDataScopeRepository.saveAndFlush(sysUserDataScope);

        int databaseSizeBeforeDelete = sysUserDataScopeRepository.findAll().size();

        // Delete the sysUserDataScope
        restSysUserDataScopeMockMvc.perform(delete("/api/sys-user-data-scopes/{id}", sysUserDataScope.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysUserDataScope> sysUserDataScopeList = sysUserDataScopeRepository.findAll();
        assertThat(sysUserDataScopeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserDataScope.class);
        SysUserDataScope sysUserDataScope1 = new SysUserDataScope();
        sysUserDataScope1.setId(1L);
        SysUserDataScope sysUserDataScope2 = new SysUserDataScope();
        sysUserDataScope2.setId(sysUserDataScope1.getId());
        assertThat(sysUserDataScope1).isEqualTo(sysUserDataScope2);
        sysUserDataScope2.setId(2L);
        assertThat(sysUserDataScope1).isNotEqualTo(sysUserDataScope2);
        sysUserDataScope1.setId(null);
        assertThat(sysUserDataScope1).isNotEqualTo(sysUserDataScope2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserDataScopeDTO.class);
        SysUserDataScopeDTO sysUserDataScopeDTO1 = new SysUserDataScopeDTO();
        sysUserDataScopeDTO1.setId(1L);
        SysUserDataScopeDTO sysUserDataScopeDTO2 = new SysUserDataScopeDTO();
        assertThat(sysUserDataScopeDTO1).isNotEqualTo(sysUserDataScopeDTO2);
        sysUserDataScopeDTO2.setId(sysUserDataScopeDTO1.getId());
        assertThat(sysUserDataScopeDTO1).isEqualTo(sysUserDataScopeDTO2);
        sysUserDataScopeDTO2.setId(2L);
        assertThat(sysUserDataScopeDTO1).isNotEqualTo(sysUserDataScopeDTO2);
        sysUserDataScopeDTO1.setId(null);
        assertThat(sysUserDataScopeDTO1).isNotEqualTo(sysUserDataScopeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysUserDataScopeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysUserDataScopeMapper.fromId(null)).isNull();
    }
}
