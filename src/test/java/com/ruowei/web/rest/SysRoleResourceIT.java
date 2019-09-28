package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.modules.sys.domain.SysRole;
import com.ruowei.modules.sys.repository.SysRoleRepository;
import com.ruowei.modules.sys.service.role.SysRoleService;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import com.ruowei.modules.sys.mapper.SysRoleMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.modules.sys.service.role.impl.SysRoleQueryService;

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

import com.ruowei.domain.enumeration.RoleType;
import com.ruowei.domain.enumeration.DataScopeType;
import com.ruowei.domain.enumeration.RoleStatusType;
/**
 * Integration tests for the {@link SysRoleResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysRoleResourceIT {

    private static final String DEFAULT_ROLE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_NAME = "BBBBBBBBBB";

    private static final RoleType DEFAULT_ROLE_TYPE = RoleType.ORGANIZARION;
    private static final RoleType UPDATED_ROLE_TYPE = RoleType.USER;

    private static final Integer DEFAULT_ROLE_SORT = 1;
    private static final Integer UPDATED_ROLE_SORT = 2;
    private static final Integer SMALLER_ROLE_SORT = 1 - 1;

    private static final Boolean DEFAULT_IS_SYS = false;
    private static final Boolean UPDATED_IS_SYS = true;

    private static final DataScopeType DEFAULT_DATA_SCOPE = DataScopeType.NONE;
    private static final DataScopeType UPDATED_DATA_SCOPE = DataScopeType.ALL;

    private static final String DEFAULT_BIZ_SCOPE = "AAAAAAAAAA";
    private static final String UPDATED_BIZ_SCOPE = "BBBBBBBBBB";

    private static final RoleStatusType DEFAULT_STATUS = RoleStatusType.NORMAl;
    private static final RoleStatusType UPDATED_STATUS = RoleStatusType.DELETE;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleQueryService sysRoleQueryService;

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

    private MockMvc restSysRoleMockMvc;

    private SysRole sysRole;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysRoleResource sysRoleResource = new SysRoleResource(sysRoleService, sysRoleQueryService);
        this.restSysRoleMockMvc = MockMvcBuilders.standaloneSetup(sysRoleResource)
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
    public static SysRole createEntity(EntityManager em) {
        SysRole sysRole = new SysRole()
            .roleCode(DEFAULT_ROLE_CODE)
            .roleName(DEFAULT_ROLE_NAME)
            .roleType(DEFAULT_ROLE_TYPE)
            .roleSort(DEFAULT_ROLE_SORT)
            .isSys(DEFAULT_IS_SYS)
            .dataScope(DEFAULT_DATA_SCOPE)
            .bizScope(DEFAULT_BIZ_SCOPE)
            .status(DEFAULT_STATUS)
            .remarks(DEFAULT_REMARKS);
        return sysRole;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRole createUpdatedEntity(EntityManager em) {
        SysRole sysRole = new SysRole()
            .roleCode(UPDATED_ROLE_CODE)
            .roleName(UPDATED_ROLE_NAME)
            .roleType(UPDATED_ROLE_TYPE)
            .roleSort(UPDATED_ROLE_SORT)
            .isSys(UPDATED_IS_SYS)
            .dataScope(UPDATED_DATA_SCOPE)
            .bizScope(UPDATED_BIZ_SCOPE)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        return sysRole;
    }

    @BeforeEach
    public void initTest() {
        sysRole = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysRole() throws Exception {
        int databaseSizeBeforeCreate = sysRoleRepository.findAll().size();

        // Create the SysRole
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);
        restSysRoleMockMvc.perform(post("/api/sys-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isCreated());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeCreate + 1);
        SysRole testSysRole = sysRoleList.get(sysRoleList.size() - 1);
        assertThat(testSysRole.getRoleCode()).isEqualTo(DEFAULT_ROLE_CODE);
        assertThat(testSysRole.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
        assertThat(testSysRole.getRoleType()).isEqualTo(DEFAULT_ROLE_TYPE);
        assertThat(testSysRole.getRoleSort()).isEqualTo(DEFAULT_ROLE_SORT);
        assertThat(testSysRole.isIsSys()).isEqualTo(DEFAULT_IS_SYS);
        assertThat(testSysRole.getDataScope()).isEqualTo(DEFAULT_DATA_SCOPE);
        assertThat(testSysRole.getBizScope()).isEqualTo(DEFAULT_BIZ_SCOPE);
        assertThat(testSysRole.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysRole.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createSysRoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysRoleRepository.findAll().size();

        // Create the SysRole with an existing ID
        sysRole.setId(1L);
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysRoleMockMvc.perform(post("/api/sys-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRoleCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysRoleRepository.findAll().size();
        // set the field null
        sysRole.setRoleCode(null);

        // Create the SysRole, which fails.
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        restSysRoleMockMvc.perform(post("/api/sys-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isBadRequest());

        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysRoleRepository.findAll().size();
        // set the field null
        sysRole.setRoleName(null);

        // Create the SysRole, which fails.
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        restSysRoleMockMvc.perform(post("/api/sys-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isBadRequest());

        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysRoleRepository.findAll().size();
        // set the field null
        sysRole.setRoleType(null);

        // Create the SysRole, which fails.
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        restSysRoleMockMvc.perform(post("/api/sys-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isBadRequest());

        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsSysIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysRoleRepository.findAll().size();
        // set the field null
        sysRole.setIsSys(null);

        // Create the SysRole, which fails.
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        restSysRoleMockMvc.perform(post("/api/sys-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isBadRequest());

        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysRoleRepository.findAll().size();
        // set the field null
        sysRole.setStatus(null);

        // Create the SysRole, which fails.
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        restSysRoleMockMvc.perform(post("/api/sys-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isBadRequest());

        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysRoles() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList
        restSysRoleMockMvc.perform(get("/api/sys-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].roleCode").value(hasItem(DEFAULT_ROLE_CODE.toString())))
            .andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].roleType").value(hasItem(DEFAULT_ROLE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].roleSort").value(hasItem(DEFAULT_ROLE_SORT)))
            .andExpect(jsonPath("$.[*].isSys").value(hasItem(DEFAULT_IS_SYS.booleanValue())))
            .andExpect(jsonPath("$.[*].dataScope").value(hasItem(DEFAULT_DATA_SCOPE.toString())))
            .andExpect(jsonPath("$.[*].bizScope").value(hasItem(DEFAULT_BIZ_SCOPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }
    
    @Test
    @Transactional
    public void getSysRole() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get the sysRole
        restSysRoleMockMvc.perform(get("/api/sys-roles/{id}", sysRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysRole.getId().intValue()))
            .andExpect(jsonPath("$.roleCode").value(DEFAULT_ROLE_CODE.toString()))
            .andExpect(jsonPath("$.roleName").value(DEFAULT_ROLE_NAME.toString()))
            .andExpect(jsonPath("$.roleType").value(DEFAULT_ROLE_TYPE.toString()))
            .andExpect(jsonPath("$.roleSort").value(DEFAULT_ROLE_SORT))
            .andExpect(jsonPath("$.isSys").value(DEFAULT_IS_SYS.booleanValue()))
            .andExpect(jsonPath("$.dataScope").value(DEFAULT_DATA_SCOPE.toString()))
            .andExpect(jsonPath("$.bizScope").value(DEFAULT_BIZ_SCOPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleCode equals to DEFAULT_ROLE_CODE
        defaultSysRoleShouldBeFound("roleCode.equals=" + DEFAULT_ROLE_CODE);

        // Get all the sysRoleList where roleCode equals to UPDATED_ROLE_CODE
        defaultSysRoleShouldNotBeFound("roleCode.equals=" + UPDATED_ROLE_CODE);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleCode in DEFAULT_ROLE_CODE or UPDATED_ROLE_CODE
        defaultSysRoleShouldBeFound("roleCode.in=" + DEFAULT_ROLE_CODE + "," + UPDATED_ROLE_CODE);

        // Get all the sysRoleList where roleCode equals to UPDATED_ROLE_CODE
        defaultSysRoleShouldNotBeFound("roleCode.in=" + UPDATED_ROLE_CODE);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleCode is not null
        defaultSysRoleShouldBeFound("roleCode.specified=true");

        // Get all the sysRoleList where roleCode is null
        defaultSysRoleShouldNotBeFound("roleCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleName equals to DEFAULT_ROLE_NAME
        defaultSysRoleShouldBeFound("roleName.equals=" + DEFAULT_ROLE_NAME);

        // Get all the sysRoleList where roleName equals to UPDATED_ROLE_NAME
        defaultSysRoleShouldNotBeFound("roleName.equals=" + UPDATED_ROLE_NAME);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleName in DEFAULT_ROLE_NAME or UPDATED_ROLE_NAME
        defaultSysRoleShouldBeFound("roleName.in=" + DEFAULT_ROLE_NAME + "," + UPDATED_ROLE_NAME);

        // Get all the sysRoleList where roleName equals to UPDATED_ROLE_NAME
        defaultSysRoleShouldNotBeFound("roleName.in=" + UPDATED_ROLE_NAME);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleName is not null
        defaultSysRoleShouldBeFound("roleName.specified=true");

        // Get all the sysRoleList where roleName is null
        defaultSysRoleShouldNotBeFound("roleName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleType equals to DEFAULT_ROLE_TYPE
        defaultSysRoleShouldBeFound("roleType.equals=" + DEFAULT_ROLE_TYPE);

        // Get all the sysRoleList where roleType equals to UPDATED_ROLE_TYPE
        defaultSysRoleShouldNotBeFound("roleType.equals=" + UPDATED_ROLE_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleTypeIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleType in DEFAULT_ROLE_TYPE or UPDATED_ROLE_TYPE
        defaultSysRoleShouldBeFound("roleType.in=" + DEFAULT_ROLE_TYPE + "," + UPDATED_ROLE_TYPE);

        // Get all the sysRoleList where roleType equals to UPDATED_ROLE_TYPE
        defaultSysRoleShouldNotBeFound("roleType.in=" + UPDATED_ROLE_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleType is not null
        defaultSysRoleShouldBeFound("roleType.specified=true");

        // Get all the sysRoleList where roleType is null
        defaultSysRoleShouldNotBeFound("roleType.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleSortIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleSort equals to DEFAULT_ROLE_SORT
        defaultSysRoleShouldBeFound("roleSort.equals=" + DEFAULT_ROLE_SORT);

        // Get all the sysRoleList where roleSort equals to UPDATED_ROLE_SORT
        defaultSysRoleShouldNotBeFound("roleSort.equals=" + UPDATED_ROLE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleSortIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleSort in DEFAULT_ROLE_SORT or UPDATED_ROLE_SORT
        defaultSysRoleShouldBeFound("roleSort.in=" + DEFAULT_ROLE_SORT + "," + UPDATED_ROLE_SORT);

        // Get all the sysRoleList where roleSort equals to UPDATED_ROLE_SORT
        defaultSysRoleShouldNotBeFound("roleSort.in=" + UPDATED_ROLE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleSortIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleSort is not null
        defaultSysRoleShouldBeFound("roleSort.specified=true");

        // Get all the sysRoleList where roleSort is null
        defaultSysRoleShouldNotBeFound("roleSort.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleSortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleSort is greater than or equal to DEFAULT_ROLE_SORT
        defaultSysRoleShouldBeFound("roleSort.greaterThanOrEqual=" + DEFAULT_ROLE_SORT);

        // Get all the sysRoleList where roleSort is greater than or equal to UPDATED_ROLE_SORT
        defaultSysRoleShouldNotBeFound("roleSort.greaterThanOrEqual=" + UPDATED_ROLE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleSortIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleSort is less than or equal to DEFAULT_ROLE_SORT
        defaultSysRoleShouldBeFound("roleSort.lessThanOrEqual=" + DEFAULT_ROLE_SORT);

        // Get all the sysRoleList where roleSort is less than or equal to SMALLER_ROLE_SORT
        defaultSysRoleShouldNotBeFound("roleSort.lessThanOrEqual=" + SMALLER_ROLE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleSortIsLessThanSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleSort is less than DEFAULT_ROLE_SORT
        defaultSysRoleShouldNotBeFound("roleSort.lessThan=" + DEFAULT_ROLE_SORT);

        // Get all the sysRoleList where roleSort is less than UPDATED_ROLE_SORT
        defaultSysRoleShouldBeFound("roleSort.lessThan=" + UPDATED_ROLE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRoleSortIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where roleSort is greater than DEFAULT_ROLE_SORT
        defaultSysRoleShouldNotBeFound("roleSort.greaterThan=" + DEFAULT_ROLE_SORT);

        // Get all the sysRoleList where roleSort is greater than SMALLER_ROLE_SORT
        defaultSysRoleShouldBeFound("roleSort.greaterThan=" + SMALLER_ROLE_SORT);
    }


    @Test
    @Transactional
    public void getAllSysRolesByIsSysIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where isSys equals to DEFAULT_IS_SYS
        defaultSysRoleShouldBeFound("isSys.equals=" + DEFAULT_IS_SYS);

        // Get all the sysRoleList where isSys equals to UPDATED_IS_SYS
        defaultSysRoleShouldNotBeFound("isSys.equals=" + UPDATED_IS_SYS);
    }

    @Test
    @Transactional
    public void getAllSysRolesByIsSysIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where isSys in DEFAULT_IS_SYS or UPDATED_IS_SYS
        defaultSysRoleShouldBeFound("isSys.in=" + DEFAULT_IS_SYS + "," + UPDATED_IS_SYS);

        // Get all the sysRoleList where isSys equals to UPDATED_IS_SYS
        defaultSysRoleShouldNotBeFound("isSys.in=" + UPDATED_IS_SYS);
    }

    @Test
    @Transactional
    public void getAllSysRolesByIsSysIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where isSys is not null
        defaultSysRoleShouldBeFound("isSys.specified=true");

        // Get all the sysRoleList where isSys is null
        defaultSysRoleShouldNotBeFound("isSys.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRolesByDataScopeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where dataScope equals to DEFAULT_DATA_SCOPE
        defaultSysRoleShouldBeFound("dataScope.equals=" + DEFAULT_DATA_SCOPE);

        // Get all the sysRoleList where dataScope equals to UPDATED_DATA_SCOPE
        defaultSysRoleShouldNotBeFound("dataScope.equals=" + UPDATED_DATA_SCOPE);
    }

    @Test
    @Transactional
    public void getAllSysRolesByDataScopeIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where dataScope in DEFAULT_DATA_SCOPE or UPDATED_DATA_SCOPE
        defaultSysRoleShouldBeFound("dataScope.in=" + DEFAULT_DATA_SCOPE + "," + UPDATED_DATA_SCOPE);

        // Get all the sysRoleList where dataScope equals to UPDATED_DATA_SCOPE
        defaultSysRoleShouldNotBeFound("dataScope.in=" + UPDATED_DATA_SCOPE);
    }

    @Test
    @Transactional
    public void getAllSysRolesByDataScopeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where dataScope is not null
        defaultSysRoleShouldBeFound("dataScope.specified=true");

        // Get all the sysRoleList where dataScope is null
        defaultSysRoleShouldNotBeFound("dataScope.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRolesByBizScopeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where bizScope equals to DEFAULT_BIZ_SCOPE
        defaultSysRoleShouldBeFound("bizScope.equals=" + DEFAULT_BIZ_SCOPE);

        // Get all the sysRoleList where bizScope equals to UPDATED_BIZ_SCOPE
        defaultSysRoleShouldNotBeFound("bizScope.equals=" + UPDATED_BIZ_SCOPE);
    }

    @Test
    @Transactional
    public void getAllSysRolesByBizScopeIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where bizScope in DEFAULT_BIZ_SCOPE or UPDATED_BIZ_SCOPE
        defaultSysRoleShouldBeFound("bizScope.in=" + DEFAULT_BIZ_SCOPE + "," + UPDATED_BIZ_SCOPE);

        // Get all the sysRoleList where bizScope equals to UPDATED_BIZ_SCOPE
        defaultSysRoleShouldNotBeFound("bizScope.in=" + UPDATED_BIZ_SCOPE);
    }

    @Test
    @Transactional
    public void getAllSysRolesByBizScopeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where bizScope is not null
        defaultSysRoleShouldBeFound("bizScope.specified=true");

        // Get all the sysRoleList where bizScope is null
        defaultSysRoleShouldNotBeFound("bizScope.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRolesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where status equals to DEFAULT_STATUS
        defaultSysRoleShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the sysRoleList where status equals to UPDATED_STATUS
        defaultSysRoleShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysRolesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultSysRoleShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the sysRoleList where status equals to UPDATED_STATUS
        defaultSysRoleShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysRolesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where status is not null
        defaultSysRoleShouldBeFound("status.specified=true");

        // Get all the sysRoleList where status is null
        defaultSysRoleShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRolesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where remarks equals to DEFAULT_REMARKS
        defaultSysRoleShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the sysRoleList where remarks equals to UPDATED_REMARKS
        defaultSysRoleShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultSysRoleShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the sysRoleList where remarks equals to UPDATED_REMARKS
        defaultSysRoleShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysRolesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList where remarks is not null
        defaultSysRoleShouldBeFound("remarks.specified=true");

        // Get all the sysRoleList where remarks is null
        defaultSysRoleShouldNotBeFound("remarks.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysRoleShouldBeFound(String filter) throws Exception {
        restSysRoleMockMvc.perform(get("/api/sys-roles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].roleCode").value(hasItem(DEFAULT_ROLE_CODE)))
            .andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME)))
            .andExpect(jsonPath("$.[*].roleType").value(hasItem(DEFAULT_ROLE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].roleSort").value(hasItem(DEFAULT_ROLE_SORT)))
            .andExpect(jsonPath("$.[*].isSys").value(hasItem(DEFAULT_IS_SYS.booleanValue())))
            .andExpect(jsonPath("$.[*].dataScope").value(hasItem(DEFAULT_DATA_SCOPE.toString())))
            .andExpect(jsonPath("$.[*].bizScope").value(hasItem(DEFAULT_BIZ_SCOPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));

        // Check, that the count call also returns 1
        restSysRoleMockMvc.perform(get("/api/sys-roles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysRoleShouldNotBeFound(String filter) throws Exception {
        restSysRoleMockMvc.perform(get("/api/sys-roles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysRoleMockMvc.perform(get("/api/sys-roles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysRole() throws Exception {
        // Get the sysRole
        restSysRoleMockMvc.perform(get("/api/sys-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysRole() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();

        // Update the sysRole
        SysRole updatedSysRole = sysRoleRepository.findById(sysRole.getId()).get();
        // Disconnect from session so that the updates on updatedSysRole are not directly saved in db
        em.detach(updatedSysRole);
        updatedSysRole
            .roleCode(UPDATED_ROLE_CODE)
            .roleName(UPDATED_ROLE_NAME)
            .roleType(UPDATED_ROLE_TYPE)
            .roleSort(UPDATED_ROLE_SORT)
            .isSys(UPDATED_IS_SYS)
            .dataScope(UPDATED_DATA_SCOPE)
            .bizScope(UPDATED_BIZ_SCOPE)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(updatedSysRole);

        restSysRoleMockMvc.perform(put("/api/sys-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isOk());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
        SysRole testSysRole = sysRoleList.get(sysRoleList.size() - 1);
        assertThat(testSysRole.getRoleCode()).isEqualTo(UPDATED_ROLE_CODE);
        assertThat(testSysRole.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testSysRole.getRoleType()).isEqualTo(UPDATED_ROLE_TYPE);
        assertThat(testSysRole.getRoleSort()).isEqualTo(UPDATED_ROLE_SORT);
        assertThat(testSysRole.isIsSys()).isEqualTo(UPDATED_IS_SYS);
        assertThat(testSysRole.getDataScope()).isEqualTo(UPDATED_DATA_SCOPE);
        assertThat(testSysRole.getBizScope()).isEqualTo(UPDATED_BIZ_SCOPE);
        assertThat(testSysRole.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysRole.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingSysRole() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();

        // Create the SysRole
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRoleMockMvc.perform(put("/api/sys-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysRole() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        int databaseSizeBeforeDelete = sysRoleRepository.findAll().size();

        // Delete the sysRole
        restSysRoleMockMvc.perform(delete("/api/sys-roles/{id}", sysRole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRole.class);
        SysRole sysRole1 = new SysRole();
        sysRole1.setId(1L);
        SysRole sysRole2 = new SysRole();
        sysRole2.setId(sysRole1.getId());
        assertThat(sysRole1).isEqualTo(sysRole2);
        sysRole2.setId(2L);
        assertThat(sysRole1).isNotEqualTo(sysRole2);
        sysRole1.setId(null);
        assertThat(sysRole1).isNotEqualTo(sysRole2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRoleDTO.class);
        SysRoleDTO sysRoleDTO1 = new SysRoleDTO();
        sysRoleDTO1.setId(1L);
        SysRoleDTO sysRoleDTO2 = new SysRoleDTO();
        assertThat(sysRoleDTO1).isNotEqualTo(sysRoleDTO2);
        sysRoleDTO2.setId(sysRoleDTO1.getId());
        assertThat(sysRoleDTO1).isEqualTo(sysRoleDTO2);
        sysRoleDTO2.setId(2L);
        assertThat(sysRoleDTO1).isNotEqualTo(sysRoleDTO2);
        sysRoleDTO1.setId(null);
        assertThat(sysRoleDTO1).isNotEqualTo(sysRoleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysRoleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysRoleMapper.fromId(null)).isNull();
    }
}
