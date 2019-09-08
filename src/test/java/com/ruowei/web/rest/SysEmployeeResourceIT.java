package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysEmployee;
import com.ruowei.repository.SysEmployeeRepository;
import com.ruowei.service.SysEmployeeService;
import com.ruowei.service.dto.SysEmployeeDTO;
import com.ruowei.service.mapper.SysEmployeeMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysEmployeeCriteria;
import com.ruowei.service.SysEmployeeQueryService;

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

import com.ruowei.domain.enumeration.EmployeeStatusType;
/**
 * Integration tests for the {@link SysEmployeeResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysEmployeeResourceIT {

    private static final String DEFAULT_EMP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EMP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EMP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMP_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_EMP_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_OFFICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_OFFICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_COMPANY_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_COMPANY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final EmployeeStatusType DEFAULT_STATUS = EmployeeStatusType.NORMAL;
    private static final EmployeeStatusType UPDATED_STATUS = EmployeeStatusType.DELETE;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private SysEmployeeRepository sysEmployeeRepository;

    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;

    @Autowired
    private SysEmployeeService sysEmployeeService;

    @Autowired
    private SysEmployeeQueryService sysEmployeeQueryService;

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

    private MockMvc restSysEmployeeMockMvc;

    private SysEmployee sysEmployee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysEmployeeResource sysEmployeeResource = new SysEmployeeResource(sysEmployeeService, sysEmployeeQueryService);
        this.restSysEmployeeMockMvc = MockMvcBuilders.standaloneSetup(sysEmployeeResource)
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
    public static SysEmployee createEntity(EntityManager em) {
        SysEmployee sysEmployee = new SysEmployee()
            .empCode(DEFAULT_EMP_CODE)
            .empName(DEFAULT_EMP_NAME)
            .empNameEn(DEFAULT_EMP_NAME_EN)
            .sysOfficeId(DEFAULT_SYS_OFFICE_ID)
            .officeName(DEFAULT_OFFICE_NAME)
            .sysCompanyId(DEFAULT_SYS_COMPANY_ID)
            .companyName(DEFAULT_COMPANY_NAME)
            .status(DEFAULT_STATUS)
            .remarks(DEFAULT_REMARKS);
        return sysEmployee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysEmployee createUpdatedEntity(EntityManager em) {
        SysEmployee sysEmployee = new SysEmployee()
            .empCode(UPDATED_EMP_CODE)
            .empName(UPDATED_EMP_NAME)
            .empNameEn(UPDATED_EMP_NAME_EN)
            .sysOfficeId(UPDATED_SYS_OFFICE_ID)
            .officeName(UPDATED_OFFICE_NAME)
            .sysCompanyId(UPDATED_SYS_COMPANY_ID)
            .companyName(UPDATED_COMPANY_NAME)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        return sysEmployee;
    }

    @BeforeEach
    public void initTest() {
        sysEmployee = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysEmployee() throws Exception {
        int databaseSizeBeforeCreate = sysEmployeeRepository.findAll().size();

        // Create the SysEmployee
        SysEmployeeDTO sysEmployeeDTO = sysEmployeeMapper.toDto(sysEmployee);
        restSysEmployeeMockMvc.perform(post("/api/sys-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeDTO)))
            .andExpect(status().isCreated());

        // Validate the SysEmployee in the database
        List<SysEmployee> sysEmployeeList = sysEmployeeRepository.findAll();
        assertThat(sysEmployeeList).hasSize(databaseSizeBeforeCreate + 1);
        SysEmployee testSysEmployee = sysEmployeeList.get(sysEmployeeList.size() - 1);
        assertThat(testSysEmployee.getEmpCode()).isEqualTo(DEFAULT_EMP_CODE);
        assertThat(testSysEmployee.getEmpName()).isEqualTo(DEFAULT_EMP_NAME);
        assertThat(testSysEmployee.getEmpNameEn()).isEqualTo(DEFAULT_EMP_NAME_EN);
        assertThat(testSysEmployee.getSysOfficeId()).isEqualTo(DEFAULT_SYS_OFFICE_ID);
        assertThat(testSysEmployee.getOfficeName()).isEqualTo(DEFAULT_OFFICE_NAME);
        assertThat(testSysEmployee.getSysCompanyId()).isEqualTo(DEFAULT_SYS_COMPANY_ID);
        assertThat(testSysEmployee.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testSysEmployee.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysEmployee.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createSysEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysEmployeeRepository.findAll().size();

        // Create the SysEmployee with an existing ID
        sysEmployee.setId(1L);
        SysEmployeeDTO sysEmployeeDTO = sysEmployeeMapper.toDto(sysEmployee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysEmployeeMockMvc.perform(post("/api/sys-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysEmployee in the database
        List<SysEmployee> sysEmployeeList = sysEmployeeRepository.findAll();
        assertThat(sysEmployeeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEmpCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysEmployeeRepository.findAll().size();
        // set the field null
        sysEmployee.setEmpCode(null);

        // Create the SysEmployee, which fails.
        SysEmployeeDTO sysEmployeeDTO = sysEmployeeMapper.toDto(sysEmployee);

        restSysEmployeeMockMvc.perform(post("/api/sys-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeDTO)))
            .andExpect(status().isBadRequest());

        List<SysEmployee> sysEmployeeList = sysEmployeeRepository.findAll();
        assertThat(sysEmployeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmpNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysEmployeeRepository.findAll().size();
        // set the field null
        sysEmployee.setEmpName(null);

        // Create the SysEmployee, which fails.
        SysEmployeeDTO sysEmployeeDTO = sysEmployeeMapper.toDto(sysEmployee);

        restSysEmployeeMockMvc.perform(post("/api/sys-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeDTO)))
            .andExpect(status().isBadRequest());

        List<SysEmployee> sysEmployeeList = sysEmployeeRepository.findAll();
        assertThat(sysEmployeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysEmployeeRepository.findAll().size();
        // set the field null
        sysEmployee.setStatus(null);

        // Create the SysEmployee, which fails.
        SysEmployeeDTO sysEmployeeDTO = sysEmployeeMapper.toDto(sysEmployee);

        restSysEmployeeMockMvc.perform(post("/api/sys-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeDTO)))
            .andExpect(status().isBadRequest());

        List<SysEmployee> sysEmployeeList = sysEmployeeRepository.findAll();
        assertThat(sysEmployeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysEmployees() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList
        restSysEmployeeMockMvc.perform(get("/api/sys-employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysEmployee.getId().intValue())))
            .andExpect(jsonPath("$.[*].empCode").value(hasItem(DEFAULT_EMP_CODE.toString())))
            .andExpect(jsonPath("$.[*].empName").value(hasItem(DEFAULT_EMP_NAME.toString())))
            .andExpect(jsonPath("$.[*].empNameEn").value(hasItem(DEFAULT_EMP_NAME_EN.toString())))
            .andExpect(jsonPath("$.[*].sysOfficeId").value(hasItem(DEFAULT_SYS_OFFICE_ID.toString())))
            .andExpect(jsonPath("$.[*].officeName").value(hasItem(DEFAULT_OFFICE_NAME.toString())))
            .andExpect(jsonPath("$.[*].sysCompanyId").value(hasItem(DEFAULT_SYS_COMPANY_ID.toString())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }
    
    @Test
    @Transactional
    public void getSysEmployee() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get the sysEmployee
        restSysEmployeeMockMvc.perform(get("/api/sys-employees/{id}", sysEmployee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysEmployee.getId().intValue()))
            .andExpect(jsonPath("$.empCode").value(DEFAULT_EMP_CODE.toString()))
            .andExpect(jsonPath("$.empName").value(DEFAULT_EMP_NAME.toString()))
            .andExpect(jsonPath("$.empNameEn").value(DEFAULT_EMP_NAME_EN.toString()))
            .andExpect(jsonPath("$.sysOfficeId").value(DEFAULT_SYS_OFFICE_ID.toString()))
            .andExpect(jsonPath("$.officeName").value(DEFAULT_OFFICE_NAME.toString()))
            .andExpect(jsonPath("$.sysCompanyId").value(DEFAULT_SYS_COMPANY_ID.toString()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByEmpCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where empCode equals to DEFAULT_EMP_CODE
        defaultSysEmployeeShouldBeFound("empCode.equals=" + DEFAULT_EMP_CODE);

        // Get all the sysEmployeeList where empCode equals to UPDATED_EMP_CODE
        defaultSysEmployeeShouldNotBeFound("empCode.equals=" + UPDATED_EMP_CODE);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByEmpCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where empCode in DEFAULT_EMP_CODE or UPDATED_EMP_CODE
        defaultSysEmployeeShouldBeFound("empCode.in=" + DEFAULT_EMP_CODE + "," + UPDATED_EMP_CODE);

        // Get all the sysEmployeeList where empCode equals to UPDATED_EMP_CODE
        defaultSysEmployeeShouldNotBeFound("empCode.in=" + UPDATED_EMP_CODE);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByEmpCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where empCode is not null
        defaultSysEmployeeShouldBeFound("empCode.specified=true");

        // Get all the sysEmployeeList where empCode is null
        defaultSysEmployeeShouldNotBeFound("empCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByEmpNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where empName equals to DEFAULT_EMP_NAME
        defaultSysEmployeeShouldBeFound("empName.equals=" + DEFAULT_EMP_NAME);

        // Get all the sysEmployeeList where empName equals to UPDATED_EMP_NAME
        defaultSysEmployeeShouldNotBeFound("empName.equals=" + UPDATED_EMP_NAME);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByEmpNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where empName in DEFAULT_EMP_NAME or UPDATED_EMP_NAME
        defaultSysEmployeeShouldBeFound("empName.in=" + DEFAULT_EMP_NAME + "," + UPDATED_EMP_NAME);

        // Get all the sysEmployeeList where empName equals to UPDATED_EMP_NAME
        defaultSysEmployeeShouldNotBeFound("empName.in=" + UPDATED_EMP_NAME);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByEmpNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where empName is not null
        defaultSysEmployeeShouldBeFound("empName.specified=true");

        // Get all the sysEmployeeList where empName is null
        defaultSysEmployeeShouldNotBeFound("empName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByEmpNameEnIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where empNameEn equals to DEFAULT_EMP_NAME_EN
        defaultSysEmployeeShouldBeFound("empNameEn.equals=" + DEFAULT_EMP_NAME_EN);

        // Get all the sysEmployeeList where empNameEn equals to UPDATED_EMP_NAME_EN
        defaultSysEmployeeShouldNotBeFound("empNameEn.equals=" + UPDATED_EMP_NAME_EN);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByEmpNameEnIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where empNameEn in DEFAULT_EMP_NAME_EN or UPDATED_EMP_NAME_EN
        defaultSysEmployeeShouldBeFound("empNameEn.in=" + DEFAULT_EMP_NAME_EN + "," + UPDATED_EMP_NAME_EN);

        // Get all the sysEmployeeList where empNameEn equals to UPDATED_EMP_NAME_EN
        defaultSysEmployeeShouldNotBeFound("empNameEn.in=" + UPDATED_EMP_NAME_EN);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByEmpNameEnIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where empNameEn is not null
        defaultSysEmployeeShouldBeFound("empNameEn.specified=true");

        // Get all the sysEmployeeList where empNameEn is null
        defaultSysEmployeeShouldNotBeFound("empNameEn.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeesBySysOfficeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where sysOfficeId equals to DEFAULT_SYS_OFFICE_ID
        defaultSysEmployeeShouldBeFound("sysOfficeId.equals=" + DEFAULT_SYS_OFFICE_ID);

        // Get all the sysEmployeeList where sysOfficeId equals to UPDATED_SYS_OFFICE_ID
        defaultSysEmployeeShouldNotBeFound("sysOfficeId.equals=" + UPDATED_SYS_OFFICE_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesBySysOfficeIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where sysOfficeId in DEFAULT_SYS_OFFICE_ID or UPDATED_SYS_OFFICE_ID
        defaultSysEmployeeShouldBeFound("sysOfficeId.in=" + DEFAULT_SYS_OFFICE_ID + "," + UPDATED_SYS_OFFICE_ID);

        // Get all the sysEmployeeList where sysOfficeId equals to UPDATED_SYS_OFFICE_ID
        defaultSysEmployeeShouldNotBeFound("sysOfficeId.in=" + UPDATED_SYS_OFFICE_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesBySysOfficeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where sysOfficeId is not null
        defaultSysEmployeeShouldBeFound("sysOfficeId.specified=true");

        // Get all the sysEmployeeList where sysOfficeId is null
        defaultSysEmployeeShouldNotBeFound("sysOfficeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByOfficeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where officeName equals to DEFAULT_OFFICE_NAME
        defaultSysEmployeeShouldBeFound("officeName.equals=" + DEFAULT_OFFICE_NAME);

        // Get all the sysEmployeeList where officeName equals to UPDATED_OFFICE_NAME
        defaultSysEmployeeShouldNotBeFound("officeName.equals=" + UPDATED_OFFICE_NAME);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByOfficeNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where officeName in DEFAULT_OFFICE_NAME or UPDATED_OFFICE_NAME
        defaultSysEmployeeShouldBeFound("officeName.in=" + DEFAULT_OFFICE_NAME + "," + UPDATED_OFFICE_NAME);

        // Get all the sysEmployeeList where officeName equals to UPDATED_OFFICE_NAME
        defaultSysEmployeeShouldNotBeFound("officeName.in=" + UPDATED_OFFICE_NAME);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByOfficeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where officeName is not null
        defaultSysEmployeeShouldBeFound("officeName.specified=true");

        // Get all the sysEmployeeList where officeName is null
        defaultSysEmployeeShouldNotBeFound("officeName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeesBySysCompanyIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where sysCompanyId equals to DEFAULT_SYS_COMPANY_ID
        defaultSysEmployeeShouldBeFound("sysCompanyId.equals=" + DEFAULT_SYS_COMPANY_ID);

        // Get all the sysEmployeeList where sysCompanyId equals to UPDATED_SYS_COMPANY_ID
        defaultSysEmployeeShouldNotBeFound("sysCompanyId.equals=" + UPDATED_SYS_COMPANY_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesBySysCompanyIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where sysCompanyId in DEFAULT_SYS_COMPANY_ID or UPDATED_SYS_COMPANY_ID
        defaultSysEmployeeShouldBeFound("sysCompanyId.in=" + DEFAULT_SYS_COMPANY_ID + "," + UPDATED_SYS_COMPANY_ID);

        // Get all the sysEmployeeList where sysCompanyId equals to UPDATED_SYS_COMPANY_ID
        defaultSysEmployeeShouldNotBeFound("sysCompanyId.in=" + UPDATED_SYS_COMPANY_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesBySysCompanyIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where sysCompanyId is not null
        defaultSysEmployeeShouldBeFound("sysCompanyId.specified=true");

        // Get all the sysEmployeeList where sysCompanyId is null
        defaultSysEmployeeShouldNotBeFound("sysCompanyId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByCompanyNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where companyName equals to DEFAULT_COMPANY_NAME
        defaultSysEmployeeShouldBeFound("companyName.equals=" + DEFAULT_COMPANY_NAME);

        // Get all the sysEmployeeList where companyName equals to UPDATED_COMPANY_NAME
        defaultSysEmployeeShouldNotBeFound("companyName.equals=" + UPDATED_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByCompanyNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where companyName in DEFAULT_COMPANY_NAME or UPDATED_COMPANY_NAME
        defaultSysEmployeeShouldBeFound("companyName.in=" + DEFAULT_COMPANY_NAME + "," + UPDATED_COMPANY_NAME);

        // Get all the sysEmployeeList where companyName equals to UPDATED_COMPANY_NAME
        defaultSysEmployeeShouldNotBeFound("companyName.in=" + UPDATED_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByCompanyNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where companyName is not null
        defaultSysEmployeeShouldBeFound("companyName.specified=true");

        // Get all the sysEmployeeList where companyName is null
        defaultSysEmployeeShouldNotBeFound("companyName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where status equals to DEFAULT_STATUS
        defaultSysEmployeeShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the sysEmployeeList where status equals to UPDATED_STATUS
        defaultSysEmployeeShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultSysEmployeeShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the sysEmployeeList where status equals to UPDATED_STATUS
        defaultSysEmployeeShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where status is not null
        defaultSysEmployeeShouldBeFound("status.specified=true");

        // Get all the sysEmployeeList where status is null
        defaultSysEmployeeShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where remarks equals to DEFAULT_REMARKS
        defaultSysEmployeeShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the sysEmployeeList where remarks equals to UPDATED_REMARKS
        defaultSysEmployeeShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultSysEmployeeShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the sysEmployeeList where remarks equals to UPDATED_REMARKS
        defaultSysEmployeeShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysEmployeesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        // Get all the sysEmployeeList where remarks is not null
        defaultSysEmployeeShouldBeFound("remarks.specified=true");

        // Get all the sysEmployeeList where remarks is null
        defaultSysEmployeeShouldNotBeFound("remarks.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysEmployeeShouldBeFound(String filter) throws Exception {
        restSysEmployeeMockMvc.perform(get("/api/sys-employees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysEmployee.getId().intValue())))
            .andExpect(jsonPath("$.[*].empCode").value(hasItem(DEFAULT_EMP_CODE)))
            .andExpect(jsonPath("$.[*].empName").value(hasItem(DEFAULT_EMP_NAME)))
            .andExpect(jsonPath("$.[*].empNameEn").value(hasItem(DEFAULT_EMP_NAME_EN)))
            .andExpect(jsonPath("$.[*].sysOfficeId").value(hasItem(DEFAULT_SYS_OFFICE_ID)))
            .andExpect(jsonPath("$.[*].officeName").value(hasItem(DEFAULT_OFFICE_NAME)))
            .andExpect(jsonPath("$.[*].sysCompanyId").value(hasItem(DEFAULT_SYS_COMPANY_ID)))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));

        // Check, that the count call also returns 1
        restSysEmployeeMockMvc.perform(get("/api/sys-employees/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysEmployeeShouldNotBeFound(String filter) throws Exception {
        restSysEmployeeMockMvc.perform(get("/api/sys-employees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysEmployeeMockMvc.perform(get("/api/sys-employees/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysEmployee() throws Exception {
        // Get the sysEmployee
        restSysEmployeeMockMvc.perform(get("/api/sys-employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysEmployee() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        int databaseSizeBeforeUpdate = sysEmployeeRepository.findAll().size();

        // Update the sysEmployee
        SysEmployee updatedSysEmployee = sysEmployeeRepository.findById(sysEmployee.getId()).get();
        // Disconnect from session so that the updates on updatedSysEmployee are not directly saved in db
        em.detach(updatedSysEmployee);
        updatedSysEmployee
            .empCode(UPDATED_EMP_CODE)
            .empName(UPDATED_EMP_NAME)
            .empNameEn(UPDATED_EMP_NAME_EN)
            .sysOfficeId(UPDATED_SYS_OFFICE_ID)
            .officeName(UPDATED_OFFICE_NAME)
            .sysCompanyId(UPDATED_SYS_COMPANY_ID)
            .companyName(UPDATED_COMPANY_NAME)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        SysEmployeeDTO sysEmployeeDTO = sysEmployeeMapper.toDto(updatedSysEmployee);

        restSysEmployeeMockMvc.perform(put("/api/sys-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeDTO)))
            .andExpect(status().isOk());

        // Validate the SysEmployee in the database
        List<SysEmployee> sysEmployeeList = sysEmployeeRepository.findAll();
        assertThat(sysEmployeeList).hasSize(databaseSizeBeforeUpdate);
        SysEmployee testSysEmployee = sysEmployeeList.get(sysEmployeeList.size() - 1);
        assertThat(testSysEmployee.getEmpCode()).isEqualTo(UPDATED_EMP_CODE);
        assertThat(testSysEmployee.getEmpName()).isEqualTo(UPDATED_EMP_NAME);
        assertThat(testSysEmployee.getEmpNameEn()).isEqualTo(UPDATED_EMP_NAME_EN);
        assertThat(testSysEmployee.getSysOfficeId()).isEqualTo(UPDATED_SYS_OFFICE_ID);
        assertThat(testSysEmployee.getOfficeName()).isEqualTo(UPDATED_OFFICE_NAME);
        assertThat(testSysEmployee.getSysCompanyId()).isEqualTo(UPDATED_SYS_COMPANY_ID);
        assertThat(testSysEmployee.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testSysEmployee.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysEmployee.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingSysEmployee() throws Exception {
        int databaseSizeBeforeUpdate = sysEmployeeRepository.findAll().size();

        // Create the SysEmployee
        SysEmployeeDTO sysEmployeeDTO = sysEmployeeMapper.toDto(sysEmployee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysEmployeeMockMvc.perform(put("/api/sys-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysEmployee in the database
        List<SysEmployee> sysEmployeeList = sysEmployeeRepository.findAll();
        assertThat(sysEmployeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysEmployee() throws Exception {
        // Initialize the database
        sysEmployeeRepository.saveAndFlush(sysEmployee);

        int databaseSizeBeforeDelete = sysEmployeeRepository.findAll().size();

        // Delete the sysEmployee
        restSysEmployeeMockMvc.perform(delete("/api/sys-employees/{id}", sysEmployee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysEmployee> sysEmployeeList = sysEmployeeRepository.findAll();
        assertThat(sysEmployeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysEmployee.class);
        SysEmployee sysEmployee1 = new SysEmployee();
        sysEmployee1.setId(1L);
        SysEmployee sysEmployee2 = new SysEmployee();
        sysEmployee2.setId(sysEmployee1.getId());
        assertThat(sysEmployee1).isEqualTo(sysEmployee2);
        sysEmployee2.setId(2L);
        assertThat(sysEmployee1).isNotEqualTo(sysEmployee2);
        sysEmployee1.setId(null);
        assertThat(sysEmployee1).isNotEqualTo(sysEmployee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysEmployeeDTO.class);
        SysEmployeeDTO sysEmployeeDTO1 = new SysEmployeeDTO();
        sysEmployeeDTO1.setId(1L);
        SysEmployeeDTO sysEmployeeDTO2 = new SysEmployeeDTO();
        assertThat(sysEmployeeDTO1).isNotEqualTo(sysEmployeeDTO2);
        sysEmployeeDTO2.setId(sysEmployeeDTO1.getId());
        assertThat(sysEmployeeDTO1).isEqualTo(sysEmployeeDTO2);
        sysEmployeeDTO2.setId(2L);
        assertThat(sysEmployeeDTO1).isNotEqualTo(sysEmployeeDTO2);
        sysEmployeeDTO1.setId(null);
        assertThat(sysEmployeeDTO1).isNotEqualTo(sysEmployeeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysEmployeeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysEmployeeMapper.fromId(null)).isNull();
    }
}
