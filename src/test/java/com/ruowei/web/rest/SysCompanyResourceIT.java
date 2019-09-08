package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysCompany;
import com.ruowei.repository.SysCompanyRepository;
import com.ruowei.service.SysCompanyService;
import com.ruowei.service.dto.SysCompanyDTO;
import com.ruowei.service.mapper.SysCompanyMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysCompanyCriteria;
import com.ruowei.service.SysCompanyQueryService;

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

import com.ruowei.domain.enumeration.CompanyStatusType;
/**
 * Integration tests for the {@link SysCompanyResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysCompanyResourceIT {

    private static final String DEFAULT_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_CODES = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_CODES = "BBBBBBBBBB";

    private static final Integer DEFAULT_TREE_SORT = 1;
    private static final Integer UPDATED_TREE_SORT = 2;
    private static final Integer SMALLER_TREE_SORT = 1 - 1;

    private static final Integer DEFAULT_TREE_SORTS = 1;
    private static final Integer UPDATED_TREE_SORTS = 2;
    private static final Integer SMALLER_TREE_SORTS = 1 - 1;

    private static final Boolean DEFAULT_TREE_LEAF = false;
    private static final Boolean UPDATED_TREE_LEAF = true;

    private static final Integer DEFAULT_TREE_LEVEL = 1;
    private static final Integer UPDATED_TREE_LEVEL = 2;
    private static final Integer SMALLER_TREE_LEVEL = 1 - 1;

    private static final String DEFAULT_TREE_NAMES = "AAAAAAAAAA";
    private static final String UPDATED_TREE_NAMES = "BBBBBBBBBB";

    private static final String DEFAULT_VIEW_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VIEW_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_CODE = "AAAAAAAAAA";
    private static final String UPDATED_AREA_CODE = "BBBBBBBBBB";

    private static final CompanyStatusType DEFAULT_STATUS = CompanyStatusType.NORMAL;
    private static final CompanyStatusType UPDATED_STATUS = CompanyStatusType.DELETE;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private SysCompanyRepository sysCompanyRepository;

    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    @Autowired
    private SysCompanyService sysCompanyService;

    @Autowired
    private SysCompanyQueryService sysCompanyQueryService;

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

    private MockMvc restSysCompanyMockMvc;

    private SysCompany sysCompany;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysCompanyResource sysCompanyResource = new SysCompanyResource(sysCompanyService, sysCompanyQueryService);
        this.restSysCompanyMockMvc = MockMvcBuilders.standaloneSetup(sysCompanyResource)
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
    public static SysCompany createEntity(EntityManager em) {
        SysCompany sysCompany = new SysCompany()
            .companyCode(DEFAULT_COMPANY_CODE)
            .parentCode(DEFAULT_PARENT_CODE)
            .parentCodes(DEFAULT_PARENT_CODES)
            .treeSort(DEFAULT_TREE_SORT)
            .treeSorts(DEFAULT_TREE_SORTS)
            .treeLeaf(DEFAULT_TREE_LEAF)
            .treeLevel(DEFAULT_TREE_LEVEL)
            .treeNames(DEFAULT_TREE_NAMES)
            .viewCode(DEFAULT_VIEW_CODE)
            .companyName(DEFAULT_COMPANY_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .areaCode(DEFAULT_AREA_CODE)
            .status(DEFAULT_STATUS)
            .remarks(DEFAULT_REMARKS);
        return sysCompany;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysCompany createUpdatedEntity(EntityManager em) {
        SysCompany sysCompany = new SysCompany()
            .companyCode(UPDATED_COMPANY_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .parentCodes(UPDATED_PARENT_CODES)
            .treeSort(UPDATED_TREE_SORT)
            .treeSorts(UPDATED_TREE_SORTS)
            .treeLeaf(UPDATED_TREE_LEAF)
            .treeLevel(UPDATED_TREE_LEVEL)
            .treeNames(UPDATED_TREE_NAMES)
            .viewCode(UPDATED_VIEW_CODE)
            .companyName(UPDATED_COMPANY_NAME)
            .fullName(UPDATED_FULL_NAME)
            .areaCode(UPDATED_AREA_CODE)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        return sysCompany;
    }

    @BeforeEach
    public void initTest() {
        sysCompany = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysCompany() throws Exception {
        int databaseSizeBeforeCreate = sysCompanyRepository.findAll().size();

        // Create the SysCompany
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);
        restSysCompanyMockMvc.perform(post("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isCreated());

        // Validate the SysCompany in the database
        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        SysCompany testSysCompany = sysCompanyList.get(sysCompanyList.size() - 1);
        assertThat(testSysCompany.getCompanyCode()).isEqualTo(DEFAULT_COMPANY_CODE);
        assertThat(testSysCompany.getParentCode()).isEqualTo(DEFAULT_PARENT_CODE);
        assertThat(testSysCompany.getParentCodes()).isEqualTo(DEFAULT_PARENT_CODES);
        assertThat(testSysCompany.getTreeSort()).isEqualTo(DEFAULT_TREE_SORT);
        assertThat(testSysCompany.getTreeSorts()).isEqualTo(DEFAULT_TREE_SORTS);
        assertThat(testSysCompany.isTreeLeaf()).isEqualTo(DEFAULT_TREE_LEAF);
        assertThat(testSysCompany.getTreeLevel()).isEqualTo(DEFAULT_TREE_LEVEL);
        assertThat(testSysCompany.getTreeNames()).isEqualTo(DEFAULT_TREE_NAMES);
        assertThat(testSysCompany.getViewCode()).isEqualTo(DEFAULT_VIEW_CODE);
        assertThat(testSysCompany.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testSysCompany.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testSysCompany.getAreaCode()).isEqualTo(DEFAULT_AREA_CODE);
        assertThat(testSysCompany.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysCompany.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createSysCompanyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysCompanyRepository.findAll().size();

        // Create the SysCompany with an existing ID
        sysCompany.setId(1L);
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysCompanyMockMvc.perform(post("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysCompany in the database
        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCompanyCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysCompanyRepository.findAll().size();
        // set the field null
        sysCompany.setCompanyCode(null);

        // Create the SysCompany, which fails.
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);

        restSysCompanyMockMvc.perform(post("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isBadRequest());

        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTreeLeafIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysCompanyRepository.findAll().size();
        // set the field null
        sysCompany.setTreeLeaf(null);

        // Create the SysCompany, which fails.
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);

        restSysCompanyMockMvc.perform(post("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isBadRequest());

        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTreeLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysCompanyRepository.findAll().size();
        // set the field null
        sysCompany.setTreeLevel(null);

        // Create the SysCompany, which fails.
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);

        restSysCompanyMockMvc.perform(post("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isBadRequest());

        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTreeNamesIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysCompanyRepository.findAll().size();
        // set the field null
        sysCompany.setTreeNames(null);

        // Create the SysCompany, which fails.
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);

        restSysCompanyMockMvc.perform(post("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isBadRequest());

        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCompanyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysCompanyRepository.findAll().size();
        // set the field null
        sysCompany.setCompanyName(null);

        // Create the SysCompany, which fails.
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);

        restSysCompanyMockMvc.perform(post("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isBadRequest());

        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysCompanyRepository.findAll().size();
        // set the field null
        sysCompany.setFullName(null);

        // Create the SysCompany, which fails.
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);

        restSysCompanyMockMvc.perform(post("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isBadRequest());

        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysCompanyRepository.findAll().size();
        // set the field null
        sysCompany.setStatus(null);

        // Create the SysCompany, which fails.
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);

        restSysCompanyMockMvc.perform(post("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isBadRequest());

        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysCompanies() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList
        restSysCompanyMockMvc.perform(get("/api/sys-companies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyCode").value(hasItem(DEFAULT_COMPANY_CODE.toString())))
            .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE.toString())))
            .andExpect(jsonPath("$.[*].parentCodes").value(hasItem(DEFAULT_PARENT_CODES.toString())))
            .andExpect(jsonPath("$.[*].treeSort").value(hasItem(DEFAULT_TREE_SORT)))
            .andExpect(jsonPath("$.[*].treeSorts").value(hasItem(DEFAULT_TREE_SORTS)))
            .andExpect(jsonPath("$.[*].treeLeaf").value(hasItem(DEFAULT_TREE_LEAF.booleanValue())))
            .andExpect(jsonPath("$.[*].treeLevel").value(hasItem(DEFAULT_TREE_LEVEL)))
            .andExpect(jsonPath("$.[*].treeNames").value(hasItem(DEFAULT_TREE_NAMES.toString())))
            .andExpect(jsonPath("$.[*].viewCode").value(hasItem(DEFAULT_VIEW_CODE.toString())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].areaCode").value(hasItem(DEFAULT_AREA_CODE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }
    
    @Test
    @Transactional
    public void getSysCompany() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get the sysCompany
        restSysCompanyMockMvc.perform(get("/api/sys-companies/{id}", sysCompany.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysCompany.getId().intValue()))
            .andExpect(jsonPath("$.companyCode").value(DEFAULT_COMPANY_CODE.toString()))
            .andExpect(jsonPath("$.parentCode").value(DEFAULT_PARENT_CODE.toString()))
            .andExpect(jsonPath("$.parentCodes").value(DEFAULT_PARENT_CODES.toString()))
            .andExpect(jsonPath("$.treeSort").value(DEFAULT_TREE_SORT))
            .andExpect(jsonPath("$.treeSorts").value(DEFAULT_TREE_SORTS))
            .andExpect(jsonPath("$.treeLeaf").value(DEFAULT_TREE_LEAF.booleanValue()))
            .andExpect(jsonPath("$.treeLevel").value(DEFAULT_TREE_LEVEL))
            .andExpect(jsonPath("$.treeNames").value(DEFAULT_TREE_NAMES.toString()))
            .andExpect(jsonPath("$.viewCode").value(DEFAULT_VIEW_CODE.toString()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.areaCode").value(DEFAULT_AREA_CODE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByCompanyCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where companyCode equals to DEFAULT_COMPANY_CODE
        defaultSysCompanyShouldBeFound("companyCode.equals=" + DEFAULT_COMPANY_CODE);

        // Get all the sysCompanyList where companyCode equals to UPDATED_COMPANY_CODE
        defaultSysCompanyShouldNotBeFound("companyCode.equals=" + UPDATED_COMPANY_CODE);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByCompanyCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where companyCode in DEFAULT_COMPANY_CODE or UPDATED_COMPANY_CODE
        defaultSysCompanyShouldBeFound("companyCode.in=" + DEFAULT_COMPANY_CODE + "," + UPDATED_COMPANY_CODE);

        // Get all the sysCompanyList where companyCode equals to UPDATED_COMPANY_CODE
        defaultSysCompanyShouldNotBeFound("companyCode.in=" + UPDATED_COMPANY_CODE);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByCompanyCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where companyCode is not null
        defaultSysCompanyShouldBeFound("companyCode.specified=true");

        // Get all the sysCompanyList where companyCode is null
        defaultSysCompanyShouldNotBeFound("companyCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByParentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where parentCode equals to DEFAULT_PARENT_CODE
        defaultSysCompanyShouldBeFound("parentCode.equals=" + DEFAULT_PARENT_CODE);

        // Get all the sysCompanyList where parentCode equals to UPDATED_PARENT_CODE
        defaultSysCompanyShouldNotBeFound("parentCode.equals=" + UPDATED_PARENT_CODE);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByParentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where parentCode in DEFAULT_PARENT_CODE or UPDATED_PARENT_CODE
        defaultSysCompanyShouldBeFound("parentCode.in=" + DEFAULT_PARENT_CODE + "," + UPDATED_PARENT_CODE);

        // Get all the sysCompanyList where parentCode equals to UPDATED_PARENT_CODE
        defaultSysCompanyShouldNotBeFound("parentCode.in=" + UPDATED_PARENT_CODE);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByParentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where parentCode is not null
        defaultSysCompanyShouldBeFound("parentCode.specified=true");

        // Get all the sysCompanyList where parentCode is null
        defaultSysCompanyShouldNotBeFound("parentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByParentCodesIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where parentCodes equals to DEFAULT_PARENT_CODES
        defaultSysCompanyShouldBeFound("parentCodes.equals=" + DEFAULT_PARENT_CODES);

        // Get all the sysCompanyList where parentCodes equals to UPDATED_PARENT_CODES
        defaultSysCompanyShouldNotBeFound("parentCodes.equals=" + UPDATED_PARENT_CODES);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByParentCodesIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where parentCodes in DEFAULT_PARENT_CODES or UPDATED_PARENT_CODES
        defaultSysCompanyShouldBeFound("parentCodes.in=" + DEFAULT_PARENT_CODES + "," + UPDATED_PARENT_CODES);

        // Get all the sysCompanyList where parentCodes equals to UPDATED_PARENT_CODES
        defaultSysCompanyShouldNotBeFound("parentCodes.in=" + UPDATED_PARENT_CODES);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByParentCodesIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where parentCodes is not null
        defaultSysCompanyShouldBeFound("parentCodes.specified=true");

        // Get all the sysCompanyList where parentCodes is null
        defaultSysCompanyShouldNotBeFound("parentCodes.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSort equals to DEFAULT_TREE_SORT
        defaultSysCompanyShouldBeFound("treeSort.equals=" + DEFAULT_TREE_SORT);

        // Get all the sysCompanyList where treeSort equals to UPDATED_TREE_SORT
        defaultSysCompanyShouldNotBeFound("treeSort.equals=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSort in DEFAULT_TREE_SORT or UPDATED_TREE_SORT
        defaultSysCompanyShouldBeFound("treeSort.in=" + DEFAULT_TREE_SORT + "," + UPDATED_TREE_SORT);

        // Get all the sysCompanyList where treeSort equals to UPDATED_TREE_SORT
        defaultSysCompanyShouldNotBeFound("treeSort.in=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSort is not null
        defaultSysCompanyShouldBeFound("treeSort.specified=true");

        // Get all the sysCompanyList where treeSort is null
        defaultSysCompanyShouldNotBeFound("treeSort.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSort is greater than or equal to DEFAULT_TREE_SORT
        defaultSysCompanyShouldBeFound("treeSort.greaterThanOrEqual=" + DEFAULT_TREE_SORT);

        // Get all the sysCompanyList where treeSort is greater than or equal to UPDATED_TREE_SORT
        defaultSysCompanyShouldNotBeFound("treeSort.greaterThanOrEqual=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSort is less than or equal to DEFAULT_TREE_SORT
        defaultSysCompanyShouldBeFound("treeSort.lessThanOrEqual=" + DEFAULT_TREE_SORT);

        // Get all the sysCompanyList where treeSort is less than or equal to SMALLER_TREE_SORT
        defaultSysCompanyShouldNotBeFound("treeSort.lessThanOrEqual=" + SMALLER_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortIsLessThanSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSort is less than DEFAULT_TREE_SORT
        defaultSysCompanyShouldNotBeFound("treeSort.lessThan=" + DEFAULT_TREE_SORT);

        // Get all the sysCompanyList where treeSort is less than UPDATED_TREE_SORT
        defaultSysCompanyShouldBeFound("treeSort.lessThan=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSort is greater than DEFAULT_TREE_SORT
        defaultSysCompanyShouldNotBeFound("treeSort.greaterThan=" + DEFAULT_TREE_SORT);

        // Get all the sysCompanyList where treeSort is greater than SMALLER_TREE_SORT
        defaultSysCompanyShouldBeFound("treeSort.greaterThan=" + SMALLER_TREE_SORT);
    }


    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortsIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSorts equals to DEFAULT_TREE_SORTS
        defaultSysCompanyShouldBeFound("treeSorts.equals=" + DEFAULT_TREE_SORTS);

        // Get all the sysCompanyList where treeSorts equals to UPDATED_TREE_SORTS
        defaultSysCompanyShouldNotBeFound("treeSorts.equals=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortsIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSorts in DEFAULT_TREE_SORTS or UPDATED_TREE_SORTS
        defaultSysCompanyShouldBeFound("treeSorts.in=" + DEFAULT_TREE_SORTS + "," + UPDATED_TREE_SORTS);

        // Get all the sysCompanyList where treeSorts equals to UPDATED_TREE_SORTS
        defaultSysCompanyShouldNotBeFound("treeSorts.in=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortsIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSorts is not null
        defaultSysCompanyShouldBeFound("treeSorts.specified=true");

        // Get all the sysCompanyList where treeSorts is null
        defaultSysCompanyShouldNotBeFound("treeSorts.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSorts is greater than or equal to DEFAULT_TREE_SORTS
        defaultSysCompanyShouldBeFound("treeSorts.greaterThanOrEqual=" + DEFAULT_TREE_SORTS);

        // Get all the sysCompanyList where treeSorts is greater than or equal to UPDATED_TREE_SORTS
        defaultSysCompanyShouldNotBeFound("treeSorts.greaterThanOrEqual=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSorts is less than or equal to DEFAULT_TREE_SORTS
        defaultSysCompanyShouldBeFound("treeSorts.lessThanOrEqual=" + DEFAULT_TREE_SORTS);

        // Get all the sysCompanyList where treeSorts is less than or equal to SMALLER_TREE_SORTS
        defaultSysCompanyShouldNotBeFound("treeSorts.lessThanOrEqual=" + SMALLER_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortsIsLessThanSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSorts is less than DEFAULT_TREE_SORTS
        defaultSysCompanyShouldNotBeFound("treeSorts.lessThan=" + DEFAULT_TREE_SORTS);

        // Get all the sysCompanyList where treeSorts is less than UPDATED_TREE_SORTS
        defaultSysCompanyShouldBeFound("treeSorts.lessThan=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeSortsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeSorts is greater than DEFAULT_TREE_SORTS
        defaultSysCompanyShouldNotBeFound("treeSorts.greaterThan=" + DEFAULT_TREE_SORTS);

        // Get all the sysCompanyList where treeSorts is greater than SMALLER_TREE_SORTS
        defaultSysCompanyShouldBeFound("treeSorts.greaterThan=" + SMALLER_TREE_SORTS);
    }


    @Test
    @Transactional
    public void getAllSysCompaniesByTreeLeafIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeLeaf equals to DEFAULT_TREE_LEAF
        defaultSysCompanyShouldBeFound("treeLeaf.equals=" + DEFAULT_TREE_LEAF);

        // Get all the sysCompanyList where treeLeaf equals to UPDATED_TREE_LEAF
        defaultSysCompanyShouldNotBeFound("treeLeaf.equals=" + UPDATED_TREE_LEAF);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeLeafIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeLeaf in DEFAULT_TREE_LEAF or UPDATED_TREE_LEAF
        defaultSysCompanyShouldBeFound("treeLeaf.in=" + DEFAULT_TREE_LEAF + "," + UPDATED_TREE_LEAF);

        // Get all the sysCompanyList where treeLeaf equals to UPDATED_TREE_LEAF
        defaultSysCompanyShouldNotBeFound("treeLeaf.in=" + UPDATED_TREE_LEAF);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeLeafIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeLeaf is not null
        defaultSysCompanyShouldBeFound("treeLeaf.specified=true");

        // Get all the sysCompanyList where treeLeaf is null
        defaultSysCompanyShouldNotBeFound("treeLeaf.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeLevel equals to DEFAULT_TREE_LEVEL
        defaultSysCompanyShouldBeFound("treeLevel.equals=" + DEFAULT_TREE_LEVEL);

        // Get all the sysCompanyList where treeLevel equals to UPDATED_TREE_LEVEL
        defaultSysCompanyShouldNotBeFound("treeLevel.equals=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeLevelIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeLevel in DEFAULT_TREE_LEVEL or UPDATED_TREE_LEVEL
        defaultSysCompanyShouldBeFound("treeLevel.in=" + DEFAULT_TREE_LEVEL + "," + UPDATED_TREE_LEVEL);

        // Get all the sysCompanyList where treeLevel equals to UPDATED_TREE_LEVEL
        defaultSysCompanyShouldNotBeFound("treeLevel.in=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeLevel is not null
        defaultSysCompanyShouldBeFound("treeLevel.specified=true");

        // Get all the sysCompanyList where treeLevel is null
        defaultSysCompanyShouldNotBeFound("treeLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeLevel is greater than or equal to DEFAULT_TREE_LEVEL
        defaultSysCompanyShouldBeFound("treeLevel.greaterThanOrEqual=" + DEFAULT_TREE_LEVEL);

        // Get all the sysCompanyList where treeLevel is greater than or equal to UPDATED_TREE_LEVEL
        defaultSysCompanyShouldNotBeFound("treeLevel.greaterThanOrEqual=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeLevelIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeLevel is less than or equal to DEFAULT_TREE_LEVEL
        defaultSysCompanyShouldBeFound("treeLevel.lessThanOrEqual=" + DEFAULT_TREE_LEVEL);

        // Get all the sysCompanyList where treeLevel is less than or equal to SMALLER_TREE_LEVEL
        defaultSysCompanyShouldNotBeFound("treeLevel.lessThanOrEqual=" + SMALLER_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeLevel is less than DEFAULT_TREE_LEVEL
        defaultSysCompanyShouldNotBeFound("treeLevel.lessThan=" + DEFAULT_TREE_LEVEL);

        // Get all the sysCompanyList where treeLevel is less than UPDATED_TREE_LEVEL
        defaultSysCompanyShouldBeFound("treeLevel.lessThan=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeLevelIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeLevel is greater than DEFAULT_TREE_LEVEL
        defaultSysCompanyShouldNotBeFound("treeLevel.greaterThan=" + DEFAULT_TREE_LEVEL);

        // Get all the sysCompanyList where treeLevel is greater than SMALLER_TREE_LEVEL
        defaultSysCompanyShouldBeFound("treeLevel.greaterThan=" + SMALLER_TREE_LEVEL);
    }


    @Test
    @Transactional
    public void getAllSysCompaniesByTreeNamesIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeNames equals to DEFAULT_TREE_NAMES
        defaultSysCompanyShouldBeFound("treeNames.equals=" + DEFAULT_TREE_NAMES);

        // Get all the sysCompanyList where treeNames equals to UPDATED_TREE_NAMES
        defaultSysCompanyShouldNotBeFound("treeNames.equals=" + UPDATED_TREE_NAMES);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeNamesIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeNames in DEFAULT_TREE_NAMES or UPDATED_TREE_NAMES
        defaultSysCompanyShouldBeFound("treeNames.in=" + DEFAULT_TREE_NAMES + "," + UPDATED_TREE_NAMES);

        // Get all the sysCompanyList where treeNames equals to UPDATED_TREE_NAMES
        defaultSysCompanyShouldNotBeFound("treeNames.in=" + UPDATED_TREE_NAMES);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByTreeNamesIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where treeNames is not null
        defaultSysCompanyShouldBeFound("treeNames.specified=true");

        // Get all the sysCompanyList where treeNames is null
        defaultSysCompanyShouldNotBeFound("treeNames.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByViewCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where viewCode equals to DEFAULT_VIEW_CODE
        defaultSysCompanyShouldBeFound("viewCode.equals=" + DEFAULT_VIEW_CODE);

        // Get all the sysCompanyList where viewCode equals to UPDATED_VIEW_CODE
        defaultSysCompanyShouldNotBeFound("viewCode.equals=" + UPDATED_VIEW_CODE);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByViewCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where viewCode in DEFAULT_VIEW_CODE or UPDATED_VIEW_CODE
        defaultSysCompanyShouldBeFound("viewCode.in=" + DEFAULT_VIEW_CODE + "," + UPDATED_VIEW_CODE);

        // Get all the sysCompanyList where viewCode equals to UPDATED_VIEW_CODE
        defaultSysCompanyShouldNotBeFound("viewCode.in=" + UPDATED_VIEW_CODE);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByViewCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where viewCode is not null
        defaultSysCompanyShouldBeFound("viewCode.specified=true");

        // Get all the sysCompanyList where viewCode is null
        defaultSysCompanyShouldNotBeFound("viewCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByCompanyNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where companyName equals to DEFAULT_COMPANY_NAME
        defaultSysCompanyShouldBeFound("companyName.equals=" + DEFAULT_COMPANY_NAME);

        // Get all the sysCompanyList where companyName equals to UPDATED_COMPANY_NAME
        defaultSysCompanyShouldNotBeFound("companyName.equals=" + UPDATED_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByCompanyNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where companyName in DEFAULT_COMPANY_NAME or UPDATED_COMPANY_NAME
        defaultSysCompanyShouldBeFound("companyName.in=" + DEFAULT_COMPANY_NAME + "," + UPDATED_COMPANY_NAME);

        // Get all the sysCompanyList where companyName equals to UPDATED_COMPANY_NAME
        defaultSysCompanyShouldNotBeFound("companyName.in=" + UPDATED_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByCompanyNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where companyName is not null
        defaultSysCompanyShouldBeFound("companyName.specified=true");

        // Get all the sysCompanyList where companyName is null
        defaultSysCompanyShouldNotBeFound("companyName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where fullName equals to DEFAULT_FULL_NAME
        defaultSysCompanyShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the sysCompanyList where fullName equals to UPDATED_FULL_NAME
        defaultSysCompanyShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultSysCompanyShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the sysCompanyList where fullName equals to UPDATED_FULL_NAME
        defaultSysCompanyShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where fullName is not null
        defaultSysCompanyShouldBeFound("fullName.specified=true");

        // Get all the sysCompanyList where fullName is null
        defaultSysCompanyShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByAreaCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where areaCode equals to DEFAULT_AREA_CODE
        defaultSysCompanyShouldBeFound("areaCode.equals=" + DEFAULT_AREA_CODE);

        // Get all the sysCompanyList where areaCode equals to UPDATED_AREA_CODE
        defaultSysCompanyShouldNotBeFound("areaCode.equals=" + UPDATED_AREA_CODE);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByAreaCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where areaCode in DEFAULT_AREA_CODE or UPDATED_AREA_CODE
        defaultSysCompanyShouldBeFound("areaCode.in=" + DEFAULT_AREA_CODE + "," + UPDATED_AREA_CODE);

        // Get all the sysCompanyList where areaCode equals to UPDATED_AREA_CODE
        defaultSysCompanyShouldNotBeFound("areaCode.in=" + UPDATED_AREA_CODE);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByAreaCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where areaCode is not null
        defaultSysCompanyShouldBeFound("areaCode.specified=true");

        // Get all the sysCompanyList where areaCode is null
        defaultSysCompanyShouldNotBeFound("areaCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where status equals to DEFAULT_STATUS
        defaultSysCompanyShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the sysCompanyList where status equals to UPDATED_STATUS
        defaultSysCompanyShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultSysCompanyShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the sysCompanyList where status equals to UPDATED_STATUS
        defaultSysCompanyShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where status is not null
        defaultSysCompanyShouldBeFound("status.specified=true");

        // Get all the sysCompanyList where status is null
        defaultSysCompanyShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where remarks equals to DEFAULT_REMARKS
        defaultSysCompanyShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the sysCompanyList where remarks equals to UPDATED_REMARKS
        defaultSysCompanyShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultSysCompanyShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the sysCompanyList where remarks equals to UPDATED_REMARKS
        defaultSysCompanyShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysCompaniesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        // Get all the sysCompanyList where remarks is not null
        defaultSysCompanyShouldBeFound("remarks.specified=true");

        // Get all the sysCompanyList where remarks is null
        defaultSysCompanyShouldNotBeFound("remarks.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysCompanyShouldBeFound(String filter) throws Exception {
        restSysCompanyMockMvc.perform(get("/api/sys-companies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyCode").value(hasItem(DEFAULT_COMPANY_CODE)))
            .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE)))
            .andExpect(jsonPath("$.[*].parentCodes").value(hasItem(DEFAULT_PARENT_CODES)))
            .andExpect(jsonPath("$.[*].treeSort").value(hasItem(DEFAULT_TREE_SORT)))
            .andExpect(jsonPath("$.[*].treeSorts").value(hasItem(DEFAULT_TREE_SORTS)))
            .andExpect(jsonPath("$.[*].treeLeaf").value(hasItem(DEFAULT_TREE_LEAF.booleanValue())))
            .andExpect(jsonPath("$.[*].treeLevel").value(hasItem(DEFAULT_TREE_LEVEL)))
            .andExpect(jsonPath("$.[*].treeNames").value(hasItem(DEFAULT_TREE_NAMES)))
            .andExpect(jsonPath("$.[*].viewCode").value(hasItem(DEFAULT_VIEW_CODE)))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].areaCode").value(hasItem(DEFAULT_AREA_CODE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));

        // Check, that the count call also returns 1
        restSysCompanyMockMvc.perform(get("/api/sys-companies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysCompanyShouldNotBeFound(String filter) throws Exception {
        restSysCompanyMockMvc.perform(get("/api/sys-companies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysCompanyMockMvc.perform(get("/api/sys-companies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysCompany() throws Exception {
        // Get the sysCompany
        restSysCompanyMockMvc.perform(get("/api/sys-companies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysCompany() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        int databaseSizeBeforeUpdate = sysCompanyRepository.findAll().size();

        // Update the sysCompany
        SysCompany updatedSysCompany = sysCompanyRepository.findById(sysCompany.getId()).get();
        // Disconnect from session so that the updates on updatedSysCompany are not directly saved in db
        em.detach(updatedSysCompany);
        updatedSysCompany
            .companyCode(UPDATED_COMPANY_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .parentCodes(UPDATED_PARENT_CODES)
            .treeSort(UPDATED_TREE_SORT)
            .treeSorts(UPDATED_TREE_SORTS)
            .treeLeaf(UPDATED_TREE_LEAF)
            .treeLevel(UPDATED_TREE_LEVEL)
            .treeNames(UPDATED_TREE_NAMES)
            .viewCode(UPDATED_VIEW_CODE)
            .companyName(UPDATED_COMPANY_NAME)
            .fullName(UPDATED_FULL_NAME)
            .areaCode(UPDATED_AREA_CODE)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(updatedSysCompany);

        restSysCompanyMockMvc.perform(put("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isOk());

        // Validate the SysCompany in the database
        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeUpdate);
        SysCompany testSysCompany = sysCompanyList.get(sysCompanyList.size() - 1);
        assertThat(testSysCompany.getCompanyCode()).isEqualTo(UPDATED_COMPANY_CODE);
        assertThat(testSysCompany.getParentCode()).isEqualTo(UPDATED_PARENT_CODE);
        assertThat(testSysCompany.getParentCodes()).isEqualTo(UPDATED_PARENT_CODES);
        assertThat(testSysCompany.getTreeSort()).isEqualTo(UPDATED_TREE_SORT);
        assertThat(testSysCompany.getTreeSorts()).isEqualTo(UPDATED_TREE_SORTS);
        assertThat(testSysCompany.isTreeLeaf()).isEqualTo(UPDATED_TREE_LEAF);
        assertThat(testSysCompany.getTreeLevel()).isEqualTo(UPDATED_TREE_LEVEL);
        assertThat(testSysCompany.getTreeNames()).isEqualTo(UPDATED_TREE_NAMES);
        assertThat(testSysCompany.getViewCode()).isEqualTo(UPDATED_VIEW_CODE);
        assertThat(testSysCompany.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testSysCompany.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testSysCompany.getAreaCode()).isEqualTo(UPDATED_AREA_CODE);
        assertThat(testSysCompany.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysCompany.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingSysCompany() throws Exception {
        int databaseSizeBeforeUpdate = sysCompanyRepository.findAll().size();

        // Create the SysCompany
        SysCompanyDTO sysCompanyDTO = sysCompanyMapper.toDto(sysCompany);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCompanyMockMvc.perform(put("/api/sys-companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysCompany in the database
        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysCompany() throws Exception {
        // Initialize the database
        sysCompanyRepository.saveAndFlush(sysCompany);

        int databaseSizeBeforeDelete = sysCompanyRepository.findAll().size();

        // Delete the sysCompany
        restSysCompanyMockMvc.perform(delete("/api/sys-companies/{id}", sysCompany.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysCompany> sysCompanyList = sysCompanyRepository.findAll();
        assertThat(sysCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCompany.class);
        SysCompany sysCompany1 = new SysCompany();
        sysCompany1.setId(1L);
        SysCompany sysCompany2 = new SysCompany();
        sysCompany2.setId(sysCompany1.getId());
        assertThat(sysCompany1).isEqualTo(sysCompany2);
        sysCompany2.setId(2L);
        assertThat(sysCompany1).isNotEqualTo(sysCompany2);
        sysCompany1.setId(null);
        assertThat(sysCompany1).isNotEqualTo(sysCompany2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCompanyDTO.class);
        SysCompanyDTO sysCompanyDTO1 = new SysCompanyDTO();
        sysCompanyDTO1.setId(1L);
        SysCompanyDTO sysCompanyDTO2 = new SysCompanyDTO();
        assertThat(sysCompanyDTO1).isNotEqualTo(sysCompanyDTO2);
        sysCompanyDTO2.setId(sysCompanyDTO1.getId());
        assertThat(sysCompanyDTO1).isEqualTo(sysCompanyDTO2);
        sysCompanyDTO2.setId(2L);
        assertThat(sysCompanyDTO1).isNotEqualTo(sysCompanyDTO2);
        sysCompanyDTO1.setId(null);
        assertThat(sysCompanyDTO1).isNotEqualTo(sysCompanyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysCompanyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysCompanyMapper.fromId(null)).isNull();
    }
}
