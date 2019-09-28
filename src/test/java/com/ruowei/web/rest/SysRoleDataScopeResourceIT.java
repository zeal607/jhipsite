package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysRoleDataScope;
import com.ruowei.repository.SysRoleDataScopeRepository;
import com.ruowei.modules.sys.service.role.SysRoleDataScopeService;
import com.ruowei.service.dto.SysRoleDataScopeDTO;
import com.ruowei.service.mapper.SysRoleDataScopeMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.modules.sys.service.role.impl.SysRoleDataScopeQueryService;

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
 * Integration tests for the {@link SysRoleDataScopeResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysRoleDataScopeResourceIT {

    private static final String DEFAULT_SYS_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_ROLE_ID = "BBBBBBBBBB";

    private static final ControlType DEFAULT_CTRL_TYPE = ControlType.COMPANY;
    private static final ControlType UPDATED_CTRL_TYPE = ControlType.OFFICE;

    private static final String DEFAULT_CTRL_DATA = "AAAAAAAAAA";
    private static final String UPDATED_CTRL_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_CTRL_PERMI = "AAAAAAAAAA";
    private static final String UPDATED_CTRL_PERMI = "BBBBBBBBBB";

    @Autowired
    private SysRoleDataScopeRepository sysRoleDataScopeRepository;

    @Autowired
    private SysRoleDataScopeMapper sysRoleDataScopeMapper;

    @Autowired
    private SysRoleDataScopeService sysRoleDataScopeService;

    @Autowired
    private SysRoleDataScopeQueryService sysRoleDataScopeQueryService;

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

    private MockMvc restSysRoleDataScopeMockMvc;

    private SysRoleDataScope sysRoleDataScope;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysRoleDataScopeResource sysRoleDataScopeResource = new SysRoleDataScopeResource(sysRoleDataScopeService, sysRoleDataScopeQueryService);
        this.restSysRoleDataScopeMockMvc = MockMvcBuilders.standaloneSetup(sysRoleDataScopeResource)
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
    public static SysRoleDataScope createEntity(EntityManager em) {
        SysRoleDataScope sysRoleDataScope = new SysRoleDataScope()
            .sysRoleId(DEFAULT_SYS_ROLE_ID)
            .ctrlType(DEFAULT_CTRL_TYPE)
            .ctrlData(DEFAULT_CTRL_DATA)
            .ctrlPermi(DEFAULT_CTRL_PERMI);
        return sysRoleDataScope;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRoleDataScope createUpdatedEntity(EntityManager em) {
        SysRoleDataScope sysRoleDataScope = new SysRoleDataScope()
            .sysRoleId(UPDATED_SYS_ROLE_ID)
            .ctrlType(UPDATED_CTRL_TYPE)
            .ctrlData(UPDATED_CTRL_DATA)
            .ctrlPermi(UPDATED_CTRL_PERMI);
        return sysRoleDataScope;
    }

    @BeforeEach
    public void initTest() {
        sysRoleDataScope = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysRoleDataScope() throws Exception {
        int databaseSizeBeforeCreate = sysRoleDataScopeRepository.findAll().size();

        // Create the SysRoleDataScope
        SysRoleDataScopeDTO sysRoleDataScopeDTO = sysRoleDataScopeMapper.toDto(sysRoleDataScope);
        restSysRoleDataScopeMockMvc.perform(post("/api/sys-role-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDataScopeDTO)))
            .andExpect(status().isCreated());

        // Validate the SysRoleDataScope in the database
        List<SysRoleDataScope> sysRoleDataScopeList = sysRoleDataScopeRepository.findAll();
        assertThat(sysRoleDataScopeList).hasSize(databaseSizeBeforeCreate + 1);
        SysRoleDataScope testSysRoleDataScope = sysRoleDataScopeList.get(sysRoleDataScopeList.size() - 1);
        assertThat(testSysRoleDataScope.getSysRoleId()).isEqualTo(DEFAULT_SYS_ROLE_ID);
        assertThat(testSysRoleDataScope.getCtrlType()).isEqualTo(DEFAULT_CTRL_TYPE);
        assertThat(testSysRoleDataScope.getCtrlData()).isEqualTo(DEFAULT_CTRL_DATA);
        assertThat(testSysRoleDataScope.getCtrlPermi()).isEqualTo(DEFAULT_CTRL_PERMI);
    }

    @Test
    @Transactional
    public void createSysRoleDataScopeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysRoleDataScopeRepository.findAll().size();

        // Create the SysRoleDataScope with an existing ID
        sysRoleDataScope.setId(1L);
        SysRoleDataScopeDTO sysRoleDataScopeDTO = sysRoleDataScopeMapper.toDto(sysRoleDataScope);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysRoleDataScopeMockMvc.perform(post("/api/sys-role-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDataScopeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysRoleDataScope in the database
        List<SysRoleDataScope> sysRoleDataScopeList = sysRoleDataScopeRepository.findAll();
        assertThat(sysRoleDataScopeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSysRoleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysRoleDataScopeRepository.findAll().size();
        // set the field null
        sysRoleDataScope.setSysRoleId(null);

        // Create the SysRoleDataScope, which fails.
        SysRoleDataScopeDTO sysRoleDataScopeDTO = sysRoleDataScopeMapper.toDto(sysRoleDataScope);

        restSysRoleDataScopeMockMvc.perform(post("/api/sys-role-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDataScopeDTO)))
            .andExpect(status().isBadRequest());

        List<SysRoleDataScope> sysRoleDataScopeList = sysRoleDataScopeRepository.findAll();
        assertThat(sysRoleDataScopeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCtrlTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysRoleDataScopeRepository.findAll().size();
        // set the field null
        sysRoleDataScope.setCtrlType(null);

        // Create the SysRoleDataScope, which fails.
        SysRoleDataScopeDTO sysRoleDataScopeDTO = sysRoleDataScopeMapper.toDto(sysRoleDataScope);

        restSysRoleDataScopeMockMvc.perform(post("/api/sys-role-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDataScopeDTO)))
            .andExpect(status().isBadRequest());

        List<SysRoleDataScope> sysRoleDataScopeList = sysRoleDataScopeRepository.findAll();
        assertThat(sysRoleDataScopeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopes() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList
        restSysRoleDataScopeMockMvc.perform(get("/api/sys-role-data-scopes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRoleDataScope.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysRoleId").value(hasItem(DEFAULT_SYS_ROLE_ID.toString())))
            .andExpect(jsonPath("$.[*].ctrlType").value(hasItem(DEFAULT_CTRL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].ctrlData").value(hasItem(DEFAULT_CTRL_DATA.toString())))
            .andExpect(jsonPath("$.[*].ctrlPermi").value(hasItem(DEFAULT_CTRL_PERMI.toString())));
    }
    
    @Test
    @Transactional
    public void getSysRoleDataScope() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get the sysRoleDataScope
        restSysRoleDataScopeMockMvc.perform(get("/api/sys-role-data-scopes/{id}", sysRoleDataScope.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysRoleDataScope.getId().intValue()))
            .andExpect(jsonPath("$.sysRoleId").value(DEFAULT_SYS_ROLE_ID.toString()))
            .andExpect(jsonPath("$.ctrlType").value(DEFAULT_CTRL_TYPE.toString()))
            .andExpect(jsonPath("$.ctrlData").value(DEFAULT_CTRL_DATA.toString()))
            .andExpect(jsonPath("$.ctrlPermi").value(DEFAULT_CTRL_PERMI.toString()));
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesBySysRoleIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where sysRoleId equals to DEFAULT_SYS_ROLE_ID
        defaultSysRoleDataScopeShouldBeFound("sysRoleId.equals=" + DEFAULT_SYS_ROLE_ID);

        // Get all the sysRoleDataScopeList where sysRoleId equals to UPDATED_SYS_ROLE_ID
        defaultSysRoleDataScopeShouldNotBeFound("sysRoleId.equals=" + UPDATED_SYS_ROLE_ID);
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesBySysRoleIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where sysRoleId in DEFAULT_SYS_ROLE_ID or UPDATED_SYS_ROLE_ID
        defaultSysRoleDataScopeShouldBeFound("sysRoleId.in=" + DEFAULT_SYS_ROLE_ID + "," + UPDATED_SYS_ROLE_ID);

        // Get all the sysRoleDataScopeList where sysRoleId equals to UPDATED_SYS_ROLE_ID
        defaultSysRoleDataScopeShouldNotBeFound("sysRoleId.in=" + UPDATED_SYS_ROLE_ID);
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesBySysRoleIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where sysRoleId is not null
        defaultSysRoleDataScopeShouldBeFound("sysRoleId.specified=true");

        // Get all the sysRoleDataScopeList where sysRoleId is null
        defaultSysRoleDataScopeShouldNotBeFound("sysRoleId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesByCtrlTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where ctrlType equals to DEFAULT_CTRL_TYPE
        defaultSysRoleDataScopeShouldBeFound("ctrlType.equals=" + DEFAULT_CTRL_TYPE);

        // Get all the sysRoleDataScopeList where ctrlType equals to UPDATED_CTRL_TYPE
        defaultSysRoleDataScopeShouldNotBeFound("ctrlType.equals=" + UPDATED_CTRL_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesByCtrlTypeIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where ctrlType in DEFAULT_CTRL_TYPE or UPDATED_CTRL_TYPE
        defaultSysRoleDataScopeShouldBeFound("ctrlType.in=" + DEFAULT_CTRL_TYPE + "," + UPDATED_CTRL_TYPE);

        // Get all the sysRoleDataScopeList where ctrlType equals to UPDATED_CTRL_TYPE
        defaultSysRoleDataScopeShouldNotBeFound("ctrlType.in=" + UPDATED_CTRL_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesByCtrlTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where ctrlType is not null
        defaultSysRoleDataScopeShouldBeFound("ctrlType.specified=true");

        // Get all the sysRoleDataScopeList where ctrlType is null
        defaultSysRoleDataScopeShouldNotBeFound("ctrlType.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesByCtrlDataIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where ctrlData equals to DEFAULT_CTRL_DATA
        defaultSysRoleDataScopeShouldBeFound("ctrlData.equals=" + DEFAULT_CTRL_DATA);

        // Get all the sysRoleDataScopeList where ctrlData equals to UPDATED_CTRL_DATA
        defaultSysRoleDataScopeShouldNotBeFound("ctrlData.equals=" + UPDATED_CTRL_DATA);
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesByCtrlDataIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where ctrlData in DEFAULT_CTRL_DATA or UPDATED_CTRL_DATA
        defaultSysRoleDataScopeShouldBeFound("ctrlData.in=" + DEFAULT_CTRL_DATA + "," + UPDATED_CTRL_DATA);

        // Get all the sysRoleDataScopeList where ctrlData equals to UPDATED_CTRL_DATA
        defaultSysRoleDataScopeShouldNotBeFound("ctrlData.in=" + UPDATED_CTRL_DATA);
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesByCtrlDataIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where ctrlData is not null
        defaultSysRoleDataScopeShouldBeFound("ctrlData.specified=true");

        // Get all the sysRoleDataScopeList where ctrlData is null
        defaultSysRoleDataScopeShouldNotBeFound("ctrlData.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesByCtrlPermiIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where ctrlPermi equals to DEFAULT_CTRL_PERMI
        defaultSysRoleDataScopeShouldBeFound("ctrlPermi.equals=" + DEFAULT_CTRL_PERMI);

        // Get all the sysRoleDataScopeList where ctrlPermi equals to UPDATED_CTRL_PERMI
        defaultSysRoleDataScopeShouldNotBeFound("ctrlPermi.equals=" + UPDATED_CTRL_PERMI);
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesByCtrlPermiIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where ctrlPermi in DEFAULT_CTRL_PERMI or UPDATED_CTRL_PERMI
        defaultSysRoleDataScopeShouldBeFound("ctrlPermi.in=" + DEFAULT_CTRL_PERMI + "," + UPDATED_CTRL_PERMI);

        // Get all the sysRoleDataScopeList where ctrlPermi equals to UPDATED_CTRL_PERMI
        defaultSysRoleDataScopeShouldNotBeFound("ctrlPermi.in=" + UPDATED_CTRL_PERMI);
    }

    @Test
    @Transactional
    public void getAllSysRoleDataScopesByCtrlPermiIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        // Get all the sysRoleDataScopeList where ctrlPermi is not null
        defaultSysRoleDataScopeShouldBeFound("ctrlPermi.specified=true");

        // Get all the sysRoleDataScopeList where ctrlPermi is null
        defaultSysRoleDataScopeShouldNotBeFound("ctrlPermi.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysRoleDataScopeShouldBeFound(String filter) throws Exception {
        restSysRoleDataScopeMockMvc.perform(get("/api/sys-role-data-scopes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRoleDataScope.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysRoleId").value(hasItem(DEFAULT_SYS_ROLE_ID)))
            .andExpect(jsonPath("$.[*].ctrlType").value(hasItem(DEFAULT_CTRL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].ctrlData").value(hasItem(DEFAULT_CTRL_DATA)))
            .andExpect(jsonPath("$.[*].ctrlPermi").value(hasItem(DEFAULT_CTRL_PERMI)));

        // Check, that the count call also returns 1
        restSysRoleDataScopeMockMvc.perform(get("/api/sys-role-data-scopes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysRoleDataScopeShouldNotBeFound(String filter) throws Exception {
        restSysRoleDataScopeMockMvc.perform(get("/api/sys-role-data-scopes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysRoleDataScopeMockMvc.perform(get("/api/sys-role-data-scopes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysRoleDataScope() throws Exception {
        // Get the sysRoleDataScope
        restSysRoleDataScopeMockMvc.perform(get("/api/sys-role-data-scopes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysRoleDataScope() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        int databaseSizeBeforeUpdate = sysRoleDataScopeRepository.findAll().size();

        // Update the sysRoleDataScope
        SysRoleDataScope updatedSysRoleDataScope = sysRoleDataScopeRepository.findById(sysRoleDataScope.getId()).get();
        // Disconnect from session so that the updates on updatedSysRoleDataScope are not directly saved in db
        em.detach(updatedSysRoleDataScope);
        updatedSysRoleDataScope
            .sysRoleId(UPDATED_SYS_ROLE_ID)
            .ctrlType(UPDATED_CTRL_TYPE)
            .ctrlData(UPDATED_CTRL_DATA)
            .ctrlPermi(UPDATED_CTRL_PERMI);
        SysRoleDataScopeDTO sysRoleDataScopeDTO = sysRoleDataScopeMapper.toDto(updatedSysRoleDataScope);

        restSysRoleDataScopeMockMvc.perform(put("/api/sys-role-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDataScopeDTO)))
            .andExpect(status().isOk());

        // Validate the SysRoleDataScope in the database
        List<SysRoleDataScope> sysRoleDataScopeList = sysRoleDataScopeRepository.findAll();
        assertThat(sysRoleDataScopeList).hasSize(databaseSizeBeforeUpdate);
        SysRoleDataScope testSysRoleDataScope = sysRoleDataScopeList.get(sysRoleDataScopeList.size() - 1);
        assertThat(testSysRoleDataScope.getSysRoleId()).isEqualTo(UPDATED_SYS_ROLE_ID);
        assertThat(testSysRoleDataScope.getCtrlType()).isEqualTo(UPDATED_CTRL_TYPE);
        assertThat(testSysRoleDataScope.getCtrlData()).isEqualTo(UPDATED_CTRL_DATA);
        assertThat(testSysRoleDataScope.getCtrlPermi()).isEqualTo(UPDATED_CTRL_PERMI);
    }

    @Test
    @Transactional
    public void updateNonExistingSysRoleDataScope() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleDataScopeRepository.findAll().size();

        // Create the SysRoleDataScope
        SysRoleDataScopeDTO sysRoleDataScopeDTO = sysRoleDataScopeMapper.toDto(sysRoleDataScope);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRoleDataScopeMockMvc.perform(put("/api/sys-role-data-scopes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDataScopeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysRoleDataScope in the database
        List<SysRoleDataScope> sysRoleDataScopeList = sysRoleDataScopeRepository.findAll();
        assertThat(sysRoleDataScopeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysRoleDataScope() throws Exception {
        // Initialize the database
        sysRoleDataScopeRepository.saveAndFlush(sysRoleDataScope);

        int databaseSizeBeforeDelete = sysRoleDataScopeRepository.findAll().size();

        // Delete the sysRoleDataScope
        restSysRoleDataScopeMockMvc.perform(delete("/api/sys-role-data-scopes/{id}", sysRoleDataScope.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysRoleDataScope> sysRoleDataScopeList = sysRoleDataScopeRepository.findAll();
        assertThat(sysRoleDataScopeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRoleDataScope.class);
        SysRoleDataScope sysRoleDataScope1 = new SysRoleDataScope();
        sysRoleDataScope1.setId(1L);
        SysRoleDataScope sysRoleDataScope2 = new SysRoleDataScope();
        sysRoleDataScope2.setId(sysRoleDataScope1.getId());
        assertThat(sysRoleDataScope1).isEqualTo(sysRoleDataScope2);
        sysRoleDataScope2.setId(2L);
        assertThat(sysRoleDataScope1).isNotEqualTo(sysRoleDataScope2);
        sysRoleDataScope1.setId(null);
        assertThat(sysRoleDataScope1).isNotEqualTo(sysRoleDataScope2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRoleDataScopeDTO.class);
        SysRoleDataScopeDTO sysRoleDataScopeDTO1 = new SysRoleDataScopeDTO();
        sysRoleDataScopeDTO1.setId(1L);
        SysRoleDataScopeDTO sysRoleDataScopeDTO2 = new SysRoleDataScopeDTO();
        assertThat(sysRoleDataScopeDTO1).isNotEqualTo(sysRoleDataScopeDTO2);
        sysRoleDataScopeDTO2.setId(sysRoleDataScopeDTO1.getId());
        assertThat(sysRoleDataScopeDTO1).isEqualTo(sysRoleDataScopeDTO2);
        sysRoleDataScopeDTO2.setId(2L);
        assertThat(sysRoleDataScopeDTO1).isNotEqualTo(sysRoleDataScopeDTO2);
        sysRoleDataScopeDTO1.setId(null);
        assertThat(sysRoleDataScopeDTO1).isNotEqualTo(sysRoleDataScopeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysRoleDataScopeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysRoleDataScopeMapper.fromId(null)).isNull();
    }
}
