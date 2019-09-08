package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysOffice;
import com.ruowei.repository.SysOfficeRepository;
import com.ruowei.service.SysOfficeService;
import com.ruowei.service.dto.SysOfficeDTO;
import com.ruowei.service.mapper.SysOfficeMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysOfficeCriteria;
import com.ruowei.service.SysOfficeQueryService;

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

import com.ruowei.domain.enumeration.OfficeType;
import com.ruowei.domain.enumeration.OfficeStatusType;
/**
 * Integration tests for the {@link SysOfficeResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysOfficeResourceIT {

    private static final String DEFAULT_OFFICE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_CODE = "BBBBBBBBBB";

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

    private static final String DEFAULT_OFFICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final OfficeType DEFAULT_OFFICE_TYPE = OfficeType.NATIONAL;
    private static final OfficeType UPDATED_OFFICE_TYPE = OfficeType.PROVINCIAL;

    private static final String DEFAULT_LEADER = "AAAAAAAAAA";
    private static final String UPDATED_LEADER = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final OfficeStatusType DEFAULT_STATUS = OfficeStatusType.NORMAL;
    private static final OfficeStatusType UPDATED_STATUS = OfficeStatusType.DELETE;

    @Autowired
    private SysOfficeRepository sysOfficeRepository;

    @Autowired
    private SysOfficeMapper sysOfficeMapper;

    @Autowired
    private SysOfficeService sysOfficeService;

    @Autowired
    private SysOfficeQueryService sysOfficeQueryService;

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

    private MockMvc restSysOfficeMockMvc;

    private SysOffice sysOffice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysOfficeResource sysOfficeResource = new SysOfficeResource(sysOfficeService, sysOfficeQueryService);
        this.restSysOfficeMockMvc = MockMvcBuilders.standaloneSetup(sysOfficeResource)
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
    public static SysOffice createEntity(EntityManager em) {
        SysOffice sysOffice = new SysOffice()
            .officeCode(DEFAULT_OFFICE_CODE)
            .parentCode(DEFAULT_PARENT_CODE)
            .parentCodes(DEFAULT_PARENT_CODES)
            .treeSort(DEFAULT_TREE_SORT)
            .treeSorts(DEFAULT_TREE_SORTS)
            .treeLeaf(DEFAULT_TREE_LEAF)
            .treeLevel(DEFAULT_TREE_LEVEL)
            .treeNames(DEFAULT_TREE_NAMES)
            .viewCode(DEFAULT_VIEW_CODE)
            .officeName(DEFAULT_OFFICE_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .officeType(DEFAULT_OFFICE_TYPE)
            .leader(DEFAULT_LEADER)
            .phone(DEFAULT_PHONE)
            .address(DEFAULT_ADDRESS)
            .zipCode(DEFAULT_ZIP_CODE)
            .email(DEFAULT_EMAIL)
            .remarks(DEFAULT_REMARKS)
            .status(DEFAULT_STATUS);
        return sysOffice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysOffice createUpdatedEntity(EntityManager em) {
        SysOffice sysOffice = new SysOffice()
            .officeCode(UPDATED_OFFICE_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .parentCodes(UPDATED_PARENT_CODES)
            .treeSort(UPDATED_TREE_SORT)
            .treeSorts(UPDATED_TREE_SORTS)
            .treeLeaf(UPDATED_TREE_LEAF)
            .treeLevel(UPDATED_TREE_LEVEL)
            .treeNames(UPDATED_TREE_NAMES)
            .viewCode(UPDATED_VIEW_CODE)
            .officeName(UPDATED_OFFICE_NAME)
            .fullName(UPDATED_FULL_NAME)
            .officeType(UPDATED_OFFICE_TYPE)
            .leader(UPDATED_LEADER)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .zipCode(UPDATED_ZIP_CODE)
            .email(UPDATED_EMAIL)
            .remarks(UPDATED_REMARKS)
            .status(UPDATED_STATUS);
        return sysOffice;
    }

    @BeforeEach
    public void initTest() {
        sysOffice = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysOffice() throws Exception {
        int databaseSizeBeforeCreate = sysOfficeRepository.findAll().size();

        // Create the SysOffice
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);
        restSysOfficeMockMvc.perform(post("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isCreated());

        // Validate the SysOffice in the database
        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeCreate + 1);
        SysOffice testSysOffice = sysOfficeList.get(sysOfficeList.size() - 1);
        assertThat(testSysOffice.getOfficeCode()).isEqualTo(DEFAULT_OFFICE_CODE);
        assertThat(testSysOffice.getParentCode()).isEqualTo(DEFAULT_PARENT_CODE);
        assertThat(testSysOffice.getParentCodes()).isEqualTo(DEFAULT_PARENT_CODES);
        assertThat(testSysOffice.getTreeSort()).isEqualTo(DEFAULT_TREE_SORT);
        assertThat(testSysOffice.getTreeSorts()).isEqualTo(DEFAULT_TREE_SORTS);
        assertThat(testSysOffice.isTreeLeaf()).isEqualTo(DEFAULT_TREE_LEAF);
        assertThat(testSysOffice.getTreeLevel()).isEqualTo(DEFAULT_TREE_LEVEL);
        assertThat(testSysOffice.getTreeNames()).isEqualTo(DEFAULT_TREE_NAMES);
        assertThat(testSysOffice.getViewCode()).isEqualTo(DEFAULT_VIEW_CODE);
        assertThat(testSysOffice.getOfficeName()).isEqualTo(DEFAULT_OFFICE_NAME);
        assertThat(testSysOffice.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testSysOffice.getOfficeType()).isEqualTo(DEFAULT_OFFICE_TYPE);
        assertThat(testSysOffice.getLeader()).isEqualTo(DEFAULT_LEADER);
        assertThat(testSysOffice.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testSysOffice.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSysOffice.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testSysOffice.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSysOffice.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testSysOffice.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createSysOfficeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysOfficeRepository.findAll().size();

        // Create the SysOffice with an existing ID
        sysOffice.setId(1L);
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysOfficeMockMvc.perform(post("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysOffice in the database
        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOfficeCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysOfficeRepository.findAll().size();
        // set the field null
        sysOffice.setOfficeCode(null);

        // Create the SysOffice, which fails.
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);

        restSysOfficeMockMvc.perform(post("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTreeLeafIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysOfficeRepository.findAll().size();
        // set the field null
        sysOffice.setTreeLeaf(null);

        // Create the SysOffice, which fails.
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);

        restSysOfficeMockMvc.perform(post("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTreeLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysOfficeRepository.findAll().size();
        // set the field null
        sysOffice.setTreeLevel(null);

        // Create the SysOffice, which fails.
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);

        restSysOfficeMockMvc.perform(post("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTreeNamesIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysOfficeRepository.findAll().size();
        // set the field null
        sysOffice.setTreeNames(null);

        // Create the SysOffice, which fails.
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);

        restSysOfficeMockMvc.perform(post("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOfficeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysOfficeRepository.findAll().size();
        // set the field null
        sysOffice.setOfficeName(null);

        // Create the SysOffice, which fails.
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);

        restSysOfficeMockMvc.perform(post("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysOfficeRepository.findAll().size();
        // set the field null
        sysOffice.setFullName(null);

        // Create the SysOffice, which fails.
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);

        restSysOfficeMockMvc.perform(post("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOfficeTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysOfficeRepository.findAll().size();
        // set the field null
        sysOffice.setOfficeType(null);

        // Create the SysOffice, which fails.
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);

        restSysOfficeMockMvc.perform(post("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysOfficeRepository.findAll().size();
        // set the field null
        sysOffice.setStatus(null);

        // Create the SysOffice, which fails.
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);

        restSysOfficeMockMvc.perform(post("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysOffices() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList
        restSysOfficeMockMvc.perform(get("/api/sys-offices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysOffice.getId().intValue())))
            .andExpect(jsonPath("$.[*].officeCode").value(hasItem(DEFAULT_OFFICE_CODE.toString())))
            .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE.toString())))
            .andExpect(jsonPath("$.[*].parentCodes").value(hasItem(DEFAULT_PARENT_CODES.toString())))
            .andExpect(jsonPath("$.[*].treeSort").value(hasItem(DEFAULT_TREE_SORT)))
            .andExpect(jsonPath("$.[*].treeSorts").value(hasItem(DEFAULT_TREE_SORTS)))
            .andExpect(jsonPath("$.[*].treeLeaf").value(hasItem(DEFAULT_TREE_LEAF.booleanValue())))
            .andExpect(jsonPath("$.[*].treeLevel").value(hasItem(DEFAULT_TREE_LEVEL)))
            .andExpect(jsonPath("$.[*].treeNames").value(hasItem(DEFAULT_TREE_NAMES.toString())))
            .andExpect(jsonPath("$.[*].viewCode").value(hasItem(DEFAULT_VIEW_CODE.toString())))
            .andExpect(jsonPath("$.[*].officeName").value(hasItem(DEFAULT_OFFICE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].officeType").value(hasItem(DEFAULT_OFFICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].leader").value(hasItem(DEFAULT_LEADER.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getSysOffice() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get the sysOffice
        restSysOfficeMockMvc.perform(get("/api/sys-offices/{id}", sysOffice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysOffice.getId().intValue()))
            .andExpect(jsonPath("$.officeCode").value(DEFAULT_OFFICE_CODE.toString()))
            .andExpect(jsonPath("$.parentCode").value(DEFAULT_PARENT_CODE.toString()))
            .andExpect(jsonPath("$.parentCodes").value(DEFAULT_PARENT_CODES.toString()))
            .andExpect(jsonPath("$.treeSort").value(DEFAULT_TREE_SORT))
            .andExpect(jsonPath("$.treeSorts").value(DEFAULT_TREE_SORTS))
            .andExpect(jsonPath("$.treeLeaf").value(DEFAULT_TREE_LEAF.booleanValue()))
            .andExpect(jsonPath("$.treeLevel").value(DEFAULT_TREE_LEVEL))
            .andExpect(jsonPath("$.treeNames").value(DEFAULT_TREE_NAMES.toString()))
            .andExpect(jsonPath("$.viewCode").value(DEFAULT_VIEW_CODE.toString()))
            .andExpect(jsonPath("$.officeName").value(DEFAULT_OFFICE_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.officeType").value(DEFAULT_OFFICE_TYPE.toString()))
            .andExpect(jsonPath("$.leader").value(DEFAULT_LEADER.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getAllSysOfficesByOfficeCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where officeCode equals to DEFAULT_OFFICE_CODE
        defaultSysOfficeShouldBeFound("officeCode.equals=" + DEFAULT_OFFICE_CODE);

        // Get all the sysOfficeList where officeCode equals to UPDATED_OFFICE_CODE
        defaultSysOfficeShouldNotBeFound("officeCode.equals=" + UPDATED_OFFICE_CODE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByOfficeCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where officeCode in DEFAULT_OFFICE_CODE or UPDATED_OFFICE_CODE
        defaultSysOfficeShouldBeFound("officeCode.in=" + DEFAULT_OFFICE_CODE + "," + UPDATED_OFFICE_CODE);

        // Get all the sysOfficeList where officeCode equals to UPDATED_OFFICE_CODE
        defaultSysOfficeShouldNotBeFound("officeCode.in=" + UPDATED_OFFICE_CODE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByOfficeCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where officeCode is not null
        defaultSysOfficeShouldBeFound("officeCode.specified=true");

        // Get all the sysOfficeList where officeCode is null
        defaultSysOfficeShouldNotBeFound("officeCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByParentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where parentCode equals to DEFAULT_PARENT_CODE
        defaultSysOfficeShouldBeFound("parentCode.equals=" + DEFAULT_PARENT_CODE);

        // Get all the sysOfficeList where parentCode equals to UPDATED_PARENT_CODE
        defaultSysOfficeShouldNotBeFound("parentCode.equals=" + UPDATED_PARENT_CODE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByParentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where parentCode in DEFAULT_PARENT_CODE or UPDATED_PARENT_CODE
        defaultSysOfficeShouldBeFound("parentCode.in=" + DEFAULT_PARENT_CODE + "," + UPDATED_PARENT_CODE);

        // Get all the sysOfficeList where parentCode equals to UPDATED_PARENT_CODE
        defaultSysOfficeShouldNotBeFound("parentCode.in=" + UPDATED_PARENT_CODE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByParentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where parentCode is not null
        defaultSysOfficeShouldBeFound("parentCode.specified=true");

        // Get all the sysOfficeList where parentCode is null
        defaultSysOfficeShouldNotBeFound("parentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByParentCodesIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where parentCodes equals to DEFAULT_PARENT_CODES
        defaultSysOfficeShouldBeFound("parentCodes.equals=" + DEFAULT_PARENT_CODES);

        // Get all the sysOfficeList where parentCodes equals to UPDATED_PARENT_CODES
        defaultSysOfficeShouldNotBeFound("parentCodes.equals=" + UPDATED_PARENT_CODES);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByParentCodesIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where parentCodes in DEFAULT_PARENT_CODES or UPDATED_PARENT_CODES
        defaultSysOfficeShouldBeFound("parentCodes.in=" + DEFAULT_PARENT_CODES + "," + UPDATED_PARENT_CODES);

        // Get all the sysOfficeList where parentCodes equals to UPDATED_PARENT_CODES
        defaultSysOfficeShouldNotBeFound("parentCodes.in=" + UPDATED_PARENT_CODES);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByParentCodesIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where parentCodes is not null
        defaultSysOfficeShouldBeFound("parentCodes.specified=true");

        // Get all the sysOfficeList where parentCodes is null
        defaultSysOfficeShouldNotBeFound("parentCodes.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSort equals to DEFAULT_TREE_SORT
        defaultSysOfficeShouldBeFound("treeSort.equals=" + DEFAULT_TREE_SORT);

        // Get all the sysOfficeList where treeSort equals to UPDATED_TREE_SORT
        defaultSysOfficeShouldNotBeFound("treeSort.equals=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSort in DEFAULT_TREE_SORT or UPDATED_TREE_SORT
        defaultSysOfficeShouldBeFound("treeSort.in=" + DEFAULT_TREE_SORT + "," + UPDATED_TREE_SORT);

        // Get all the sysOfficeList where treeSort equals to UPDATED_TREE_SORT
        defaultSysOfficeShouldNotBeFound("treeSort.in=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSort is not null
        defaultSysOfficeShouldBeFound("treeSort.specified=true");

        // Get all the sysOfficeList where treeSort is null
        defaultSysOfficeShouldNotBeFound("treeSort.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSort is greater than or equal to DEFAULT_TREE_SORT
        defaultSysOfficeShouldBeFound("treeSort.greaterThanOrEqual=" + DEFAULT_TREE_SORT);

        // Get all the sysOfficeList where treeSort is greater than or equal to UPDATED_TREE_SORT
        defaultSysOfficeShouldNotBeFound("treeSort.greaterThanOrEqual=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSort is less than or equal to DEFAULT_TREE_SORT
        defaultSysOfficeShouldBeFound("treeSort.lessThanOrEqual=" + DEFAULT_TREE_SORT);

        // Get all the sysOfficeList where treeSort is less than or equal to SMALLER_TREE_SORT
        defaultSysOfficeShouldNotBeFound("treeSort.lessThanOrEqual=" + SMALLER_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortIsLessThanSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSort is less than DEFAULT_TREE_SORT
        defaultSysOfficeShouldNotBeFound("treeSort.lessThan=" + DEFAULT_TREE_SORT);

        // Get all the sysOfficeList where treeSort is less than UPDATED_TREE_SORT
        defaultSysOfficeShouldBeFound("treeSort.lessThan=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSort is greater than DEFAULT_TREE_SORT
        defaultSysOfficeShouldNotBeFound("treeSort.greaterThan=" + DEFAULT_TREE_SORT);

        // Get all the sysOfficeList where treeSort is greater than SMALLER_TREE_SORT
        defaultSysOfficeShouldBeFound("treeSort.greaterThan=" + SMALLER_TREE_SORT);
    }


    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortsIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSorts equals to DEFAULT_TREE_SORTS
        defaultSysOfficeShouldBeFound("treeSorts.equals=" + DEFAULT_TREE_SORTS);

        // Get all the sysOfficeList where treeSorts equals to UPDATED_TREE_SORTS
        defaultSysOfficeShouldNotBeFound("treeSorts.equals=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortsIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSorts in DEFAULT_TREE_SORTS or UPDATED_TREE_SORTS
        defaultSysOfficeShouldBeFound("treeSorts.in=" + DEFAULT_TREE_SORTS + "," + UPDATED_TREE_SORTS);

        // Get all the sysOfficeList where treeSorts equals to UPDATED_TREE_SORTS
        defaultSysOfficeShouldNotBeFound("treeSorts.in=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortsIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSorts is not null
        defaultSysOfficeShouldBeFound("treeSorts.specified=true");

        // Get all the sysOfficeList where treeSorts is null
        defaultSysOfficeShouldNotBeFound("treeSorts.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSorts is greater than or equal to DEFAULT_TREE_SORTS
        defaultSysOfficeShouldBeFound("treeSorts.greaterThanOrEqual=" + DEFAULT_TREE_SORTS);

        // Get all the sysOfficeList where treeSorts is greater than or equal to UPDATED_TREE_SORTS
        defaultSysOfficeShouldNotBeFound("treeSorts.greaterThanOrEqual=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSorts is less than or equal to DEFAULT_TREE_SORTS
        defaultSysOfficeShouldBeFound("treeSorts.lessThanOrEqual=" + DEFAULT_TREE_SORTS);

        // Get all the sysOfficeList where treeSorts is less than or equal to SMALLER_TREE_SORTS
        defaultSysOfficeShouldNotBeFound("treeSorts.lessThanOrEqual=" + SMALLER_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortsIsLessThanSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSorts is less than DEFAULT_TREE_SORTS
        defaultSysOfficeShouldNotBeFound("treeSorts.lessThan=" + DEFAULT_TREE_SORTS);

        // Get all the sysOfficeList where treeSorts is less than UPDATED_TREE_SORTS
        defaultSysOfficeShouldBeFound("treeSorts.lessThan=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeSortsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeSorts is greater than DEFAULT_TREE_SORTS
        defaultSysOfficeShouldNotBeFound("treeSorts.greaterThan=" + DEFAULT_TREE_SORTS);

        // Get all the sysOfficeList where treeSorts is greater than SMALLER_TREE_SORTS
        defaultSysOfficeShouldBeFound("treeSorts.greaterThan=" + SMALLER_TREE_SORTS);
    }


    @Test
    @Transactional
    public void getAllSysOfficesByTreeLeafIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeLeaf equals to DEFAULT_TREE_LEAF
        defaultSysOfficeShouldBeFound("treeLeaf.equals=" + DEFAULT_TREE_LEAF);

        // Get all the sysOfficeList where treeLeaf equals to UPDATED_TREE_LEAF
        defaultSysOfficeShouldNotBeFound("treeLeaf.equals=" + UPDATED_TREE_LEAF);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeLeafIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeLeaf in DEFAULT_TREE_LEAF or UPDATED_TREE_LEAF
        defaultSysOfficeShouldBeFound("treeLeaf.in=" + DEFAULT_TREE_LEAF + "," + UPDATED_TREE_LEAF);

        // Get all the sysOfficeList where treeLeaf equals to UPDATED_TREE_LEAF
        defaultSysOfficeShouldNotBeFound("treeLeaf.in=" + UPDATED_TREE_LEAF);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeLeafIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeLeaf is not null
        defaultSysOfficeShouldBeFound("treeLeaf.specified=true");

        // Get all the sysOfficeList where treeLeaf is null
        defaultSysOfficeShouldNotBeFound("treeLeaf.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeLevel equals to DEFAULT_TREE_LEVEL
        defaultSysOfficeShouldBeFound("treeLevel.equals=" + DEFAULT_TREE_LEVEL);

        // Get all the sysOfficeList where treeLevel equals to UPDATED_TREE_LEVEL
        defaultSysOfficeShouldNotBeFound("treeLevel.equals=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeLevelIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeLevel in DEFAULT_TREE_LEVEL or UPDATED_TREE_LEVEL
        defaultSysOfficeShouldBeFound("treeLevel.in=" + DEFAULT_TREE_LEVEL + "," + UPDATED_TREE_LEVEL);

        // Get all the sysOfficeList where treeLevel equals to UPDATED_TREE_LEVEL
        defaultSysOfficeShouldNotBeFound("treeLevel.in=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeLevel is not null
        defaultSysOfficeShouldBeFound("treeLevel.specified=true");

        // Get all the sysOfficeList where treeLevel is null
        defaultSysOfficeShouldNotBeFound("treeLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeLevel is greater than or equal to DEFAULT_TREE_LEVEL
        defaultSysOfficeShouldBeFound("treeLevel.greaterThanOrEqual=" + DEFAULT_TREE_LEVEL);

        // Get all the sysOfficeList where treeLevel is greater than or equal to UPDATED_TREE_LEVEL
        defaultSysOfficeShouldNotBeFound("treeLevel.greaterThanOrEqual=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeLevelIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeLevel is less than or equal to DEFAULT_TREE_LEVEL
        defaultSysOfficeShouldBeFound("treeLevel.lessThanOrEqual=" + DEFAULT_TREE_LEVEL);

        // Get all the sysOfficeList where treeLevel is less than or equal to SMALLER_TREE_LEVEL
        defaultSysOfficeShouldNotBeFound("treeLevel.lessThanOrEqual=" + SMALLER_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeLevel is less than DEFAULT_TREE_LEVEL
        defaultSysOfficeShouldNotBeFound("treeLevel.lessThan=" + DEFAULT_TREE_LEVEL);

        // Get all the sysOfficeList where treeLevel is less than UPDATED_TREE_LEVEL
        defaultSysOfficeShouldBeFound("treeLevel.lessThan=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeLevelIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeLevel is greater than DEFAULT_TREE_LEVEL
        defaultSysOfficeShouldNotBeFound("treeLevel.greaterThan=" + DEFAULT_TREE_LEVEL);

        // Get all the sysOfficeList where treeLevel is greater than SMALLER_TREE_LEVEL
        defaultSysOfficeShouldBeFound("treeLevel.greaterThan=" + SMALLER_TREE_LEVEL);
    }


    @Test
    @Transactional
    public void getAllSysOfficesByTreeNamesIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeNames equals to DEFAULT_TREE_NAMES
        defaultSysOfficeShouldBeFound("treeNames.equals=" + DEFAULT_TREE_NAMES);

        // Get all the sysOfficeList where treeNames equals to UPDATED_TREE_NAMES
        defaultSysOfficeShouldNotBeFound("treeNames.equals=" + UPDATED_TREE_NAMES);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeNamesIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeNames in DEFAULT_TREE_NAMES or UPDATED_TREE_NAMES
        defaultSysOfficeShouldBeFound("treeNames.in=" + DEFAULT_TREE_NAMES + "," + UPDATED_TREE_NAMES);

        // Get all the sysOfficeList where treeNames equals to UPDATED_TREE_NAMES
        defaultSysOfficeShouldNotBeFound("treeNames.in=" + UPDATED_TREE_NAMES);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByTreeNamesIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where treeNames is not null
        defaultSysOfficeShouldBeFound("treeNames.specified=true");

        // Get all the sysOfficeList where treeNames is null
        defaultSysOfficeShouldNotBeFound("treeNames.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByViewCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where viewCode equals to DEFAULT_VIEW_CODE
        defaultSysOfficeShouldBeFound("viewCode.equals=" + DEFAULT_VIEW_CODE);

        // Get all the sysOfficeList where viewCode equals to UPDATED_VIEW_CODE
        defaultSysOfficeShouldNotBeFound("viewCode.equals=" + UPDATED_VIEW_CODE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByViewCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where viewCode in DEFAULT_VIEW_CODE or UPDATED_VIEW_CODE
        defaultSysOfficeShouldBeFound("viewCode.in=" + DEFAULT_VIEW_CODE + "," + UPDATED_VIEW_CODE);

        // Get all the sysOfficeList where viewCode equals to UPDATED_VIEW_CODE
        defaultSysOfficeShouldNotBeFound("viewCode.in=" + UPDATED_VIEW_CODE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByViewCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where viewCode is not null
        defaultSysOfficeShouldBeFound("viewCode.specified=true");

        // Get all the sysOfficeList where viewCode is null
        defaultSysOfficeShouldNotBeFound("viewCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByOfficeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where officeName equals to DEFAULT_OFFICE_NAME
        defaultSysOfficeShouldBeFound("officeName.equals=" + DEFAULT_OFFICE_NAME);

        // Get all the sysOfficeList where officeName equals to UPDATED_OFFICE_NAME
        defaultSysOfficeShouldNotBeFound("officeName.equals=" + UPDATED_OFFICE_NAME);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByOfficeNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where officeName in DEFAULT_OFFICE_NAME or UPDATED_OFFICE_NAME
        defaultSysOfficeShouldBeFound("officeName.in=" + DEFAULT_OFFICE_NAME + "," + UPDATED_OFFICE_NAME);

        // Get all the sysOfficeList where officeName equals to UPDATED_OFFICE_NAME
        defaultSysOfficeShouldNotBeFound("officeName.in=" + UPDATED_OFFICE_NAME);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByOfficeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where officeName is not null
        defaultSysOfficeShouldBeFound("officeName.specified=true");

        // Get all the sysOfficeList where officeName is null
        defaultSysOfficeShouldNotBeFound("officeName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where fullName equals to DEFAULT_FULL_NAME
        defaultSysOfficeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the sysOfficeList where fullName equals to UPDATED_FULL_NAME
        defaultSysOfficeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultSysOfficeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the sysOfficeList where fullName equals to UPDATED_FULL_NAME
        defaultSysOfficeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where fullName is not null
        defaultSysOfficeShouldBeFound("fullName.specified=true");

        // Get all the sysOfficeList where fullName is null
        defaultSysOfficeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByOfficeTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where officeType equals to DEFAULT_OFFICE_TYPE
        defaultSysOfficeShouldBeFound("officeType.equals=" + DEFAULT_OFFICE_TYPE);

        // Get all the sysOfficeList where officeType equals to UPDATED_OFFICE_TYPE
        defaultSysOfficeShouldNotBeFound("officeType.equals=" + UPDATED_OFFICE_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByOfficeTypeIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where officeType in DEFAULT_OFFICE_TYPE or UPDATED_OFFICE_TYPE
        defaultSysOfficeShouldBeFound("officeType.in=" + DEFAULT_OFFICE_TYPE + "," + UPDATED_OFFICE_TYPE);

        // Get all the sysOfficeList where officeType equals to UPDATED_OFFICE_TYPE
        defaultSysOfficeShouldNotBeFound("officeType.in=" + UPDATED_OFFICE_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByOfficeTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where officeType is not null
        defaultSysOfficeShouldBeFound("officeType.specified=true");

        // Get all the sysOfficeList where officeType is null
        defaultSysOfficeShouldNotBeFound("officeType.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByLeaderIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where leader equals to DEFAULT_LEADER
        defaultSysOfficeShouldBeFound("leader.equals=" + DEFAULT_LEADER);

        // Get all the sysOfficeList where leader equals to UPDATED_LEADER
        defaultSysOfficeShouldNotBeFound("leader.equals=" + UPDATED_LEADER);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByLeaderIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where leader in DEFAULT_LEADER or UPDATED_LEADER
        defaultSysOfficeShouldBeFound("leader.in=" + DEFAULT_LEADER + "," + UPDATED_LEADER);

        // Get all the sysOfficeList where leader equals to UPDATED_LEADER
        defaultSysOfficeShouldNotBeFound("leader.in=" + UPDATED_LEADER);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByLeaderIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where leader is not null
        defaultSysOfficeShouldBeFound("leader.specified=true");

        // Get all the sysOfficeList where leader is null
        defaultSysOfficeShouldNotBeFound("leader.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where phone equals to DEFAULT_PHONE
        defaultSysOfficeShouldBeFound("phone.equals=" + DEFAULT_PHONE);

        // Get all the sysOfficeList where phone equals to UPDATED_PHONE
        defaultSysOfficeShouldNotBeFound("phone.equals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where phone in DEFAULT_PHONE or UPDATED_PHONE
        defaultSysOfficeShouldBeFound("phone.in=" + DEFAULT_PHONE + "," + UPDATED_PHONE);

        // Get all the sysOfficeList where phone equals to UPDATED_PHONE
        defaultSysOfficeShouldNotBeFound("phone.in=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where phone is not null
        defaultSysOfficeShouldBeFound("phone.specified=true");

        // Get all the sysOfficeList where phone is null
        defaultSysOfficeShouldNotBeFound("phone.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where address equals to DEFAULT_ADDRESS
        defaultSysOfficeShouldBeFound("address.equals=" + DEFAULT_ADDRESS);

        // Get all the sysOfficeList where address equals to UPDATED_ADDRESS
        defaultSysOfficeShouldNotBeFound("address.equals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByAddressIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where address in DEFAULT_ADDRESS or UPDATED_ADDRESS
        defaultSysOfficeShouldBeFound("address.in=" + DEFAULT_ADDRESS + "," + UPDATED_ADDRESS);

        // Get all the sysOfficeList where address equals to UPDATED_ADDRESS
        defaultSysOfficeShouldNotBeFound("address.in=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where address is not null
        defaultSysOfficeShouldBeFound("address.specified=true");

        // Get all the sysOfficeList where address is null
        defaultSysOfficeShouldNotBeFound("address.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByZipCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where zipCode equals to DEFAULT_ZIP_CODE
        defaultSysOfficeShouldBeFound("zipCode.equals=" + DEFAULT_ZIP_CODE);

        // Get all the sysOfficeList where zipCode equals to UPDATED_ZIP_CODE
        defaultSysOfficeShouldNotBeFound("zipCode.equals=" + UPDATED_ZIP_CODE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByZipCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where zipCode in DEFAULT_ZIP_CODE or UPDATED_ZIP_CODE
        defaultSysOfficeShouldBeFound("zipCode.in=" + DEFAULT_ZIP_CODE + "," + UPDATED_ZIP_CODE);

        // Get all the sysOfficeList where zipCode equals to UPDATED_ZIP_CODE
        defaultSysOfficeShouldNotBeFound("zipCode.in=" + UPDATED_ZIP_CODE);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByZipCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where zipCode is not null
        defaultSysOfficeShouldBeFound("zipCode.specified=true");

        // Get all the sysOfficeList where zipCode is null
        defaultSysOfficeShouldNotBeFound("zipCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where email equals to DEFAULT_EMAIL
        defaultSysOfficeShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the sysOfficeList where email equals to UPDATED_EMAIL
        defaultSysOfficeShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultSysOfficeShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the sysOfficeList where email equals to UPDATED_EMAIL
        defaultSysOfficeShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where email is not null
        defaultSysOfficeShouldBeFound("email.specified=true");

        // Get all the sysOfficeList where email is null
        defaultSysOfficeShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where remarks equals to DEFAULT_REMARKS
        defaultSysOfficeShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the sysOfficeList where remarks equals to UPDATED_REMARKS
        defaultSysOfficeShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultSysOfficeShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the sysOfficeList where remarks equals to UPDATED_REMARKS
        defaultSysOfficeShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where remarks is not null
        defaultSysOfficeShouldBeFound("remarks.specified=true");

        // Get all the sysOfficeList where remarks is null
        defaultSysOfficeShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysOfficesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where status equals to DEFAULT_STATUS
        defaultSysOfficeShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the sysOfficeList where status equals to UPDATED_STATUS
        defaultSysOfficeShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultSysOfficeShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the sysOfficeList where status equals to UPDATED_STATUS
        defaultSysOfficeShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysOfficesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        // Get all the sysOfficeList where status is not null
        defaultSysOfficeShouldBeFound("status.specified=true");

        // Get all the sysOfficeList where status is null
        defaultSysOfficeShouldNotBeFound("status.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysOfficeShouldBeFound(String filter) throws Exception {
        restSysOfficeMockMvc.perform(get("/api/sys-offices?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysOffice.getId().intValue())))
            .andExpect(jsonPath("$.[*].officeCode").value(hasItem(DEFAULT_OFFICE_CODE)))
            .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE)))
            .andExpect(jsonPath("$.[*].parentCodes").value(hasItem(DEFAULT_PARENT_CODES)))
            .andExpect(jsonPath("$.[*].treeSort").value(hasItem(DEFAULT_TREE_SORT)))
            .andExpect(jsonPath("$.[*].treeSorts").value(hasItem(DEFAULT_TREE_SORTS)))
            .andExpect(jsonPath("$.[*].treeLeaf").value(hasItem(DEFAULT_TREE_LEAF.booleanValue())))
            .andExpect(jsonPath("$.[*].treeLevel").value(hasItem(DEFAULT_TREE_LEVEL)))
            .andExpect(jsonPath("$.[*].treeNames").value(hasItem(DEFAULT_TREE_NAMES)))
            .andExpect(jsonPath("$.[*].viewCode").value(hasItem(DEFAULT_VIEW_CODE)))
            .andExpect(jsonPath("$.[*].officeName").value(hasItem(DEFAULT_OFFICE_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].officeType").value(hasItem(DEFAULT_OFFICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].leader").value(hasItem(DEFAULT_LEADER)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));

        // Check, that the count call also returns 1
        restSysOfficeMockMvc.perform(get("/api/sys-offices/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysOfficeShouldNotBeFound(String filter) throws Exception {
        restSysOfficeMockMvc.perform(get("/api/sys-offices?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysOfficeMockMvc.perform(get("/api/sys-offices/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysOffice() throws Exception {
        // Get the sysOffice
        restSysOfficeMockMvc.perform(get("/api/sys-offices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysOffice() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        int databaseSizeBeforeUpdate = sysOfficeRepository.findAll().size();

        // Update the sysOffice
        SysOffice updatedSysOffice = sysOfficeRepository.findById(sysOffice.getId()).get();
        // Disconnect from session so that the updates on updatedSysOffice are not directly saved in db
        em.detach(updatedSysOffice);
        updatedSysOffice
            .officeCode(UPDATED_OFFICE_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .parentCodes(UPDATED_PARENT_CODES)
            .treeSort(UPDATED_TREE_SORT)
            .treeSorts(UPDATED_TREE_SORTS)
            .treeLeaf(UPDATED_TREE_LEAF)
            .treeLevel(UPDATED_TREE_LEVEL)
            .treeNames(UPDATED_TREE_NAMES)
            .viewCode(UPDATED_VIEW_CODE)
            .officeName(UPDATED_OFFICE_NAME)
            .fullName(UPDATED_FULL_NAME)
            .officeType(UPDATED_OFFICE_TYPE)
            .leader(UPDATED_LEADER)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .zipCode(UPDATED_ZIP_CODE)
            .email(UPDATED_EMAIL)
            .remarks(UPDATED_REMARKS)
            .status(UPDATED_STATUS);
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(updatedSysOffice);

        restSysOfficeMockMvc.perform(put("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isOk());

        // Validate the SysOffice in the database
        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeUpdate);
        SysOffice testSysOffice = sysOfficeList.get(sysOfficeList.size() - 1);
        assertThat(testSysOffice.getOfficeCode()).isEqualTo(UPDATED_OFFICE_CODE);
        assertThat(testSysOffice.getParentCode()).isEqualTo(UPDATED_PARENT_CODE);
        assertThat(testSysOffice.getParentCodes()).isEqualTo(UPDATED_PARENT_CODES);
        assertThat(testSysOffice.getTreeSort()).isEqualTo(UPDATED_TREE_SORT);
        assertThat(testSysOffice.getTreeSorts()).isEqualTo(UPDATED_TREE_SORTS);
        assertThat(testSysOffice.isTreeLeaf()).isEqualTo(UPDATED_TREE_LEAF);
        assertThat(testSysOffice.getTreeLevel()).isEqualTo(UPDATED_TREE_LEVEL);
        assertThat(testSysOffice.getTreeNames()).isEqualTo(UPDATED_TREE_NAMES);
        assertThat(testSysOffice.getViewCode()).isEqualTo(UPDATED_VIEW_CODE);
        assertThat(testSysOffice.getOfficeName()).isEqualTo(UPDATED_OFFICE_NAME);
        assertThat(testSysOffice.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testSysOffice.getOfficeType()).isEqualTo(UPDATED_OFFICE_TYPE);
        assertThat(testSysOffice.getLeader()).isEqualTo(UPDATED_LEADER);
        assertThat(testSysOffice.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSysOffice.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSysOffice.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testSysOffice.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSysOffice.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testSysOffice.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingSysOffice() throws Exception {
        int databaseSizeBeforeUpdate = sysOfficeRepository.findAll().size();

        // Create the SysOffice
        SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysOfficeMockMvc.perform(put("/api/sys-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOfficeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysOffice in the database
        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysOffice() throws Exception {
        // Initialize the database
        sysOfficeRepository.saveAndFlush(sysOffice);

        int databaseSizeBeforeDelete = sysOfficeRepository.findAll().size();

        // Delete the sysOffice
        restSysOfficeMockMvc.perform(delete("/api/sys-offices/{id}", sysOffice.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysOffice> sysOfficeList = sysOfficeRepository.findAll();
        assertThat(sysOfficeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysOffice.class);
        SysOffice sysOffice1 = new SysOffice();
        sysOffice1.setId(1L);
        SysOffice sysOffice2 = new SysOffice();
        sysOffice2.setId(sysOffice1.getId());
        assertThat(sysOffice1).isEqualTo(sysOffice2);
        sysOffice2.setId(2L);
        assertThat(sysOffice1).isNotEqualTo(sysOffice2);
        sysOffice1.setId(null);
        assertThat(sysOffice1).isNotEqualTo(sysOffice2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysOfficeDTO.class);
        SysOfficeDTO sysOfficeDTO1 = new SysOfficeDTO();
        sysOfficeDTO1.setId(1L);
        SysOfficeDTO sysOfficeDTO2 = new SysOfficeDTO();
        assertThat(sysOfficeDTO1).isNotEqualTo(sysOfficeDTO2);
        sysOfficeDTO2.setId(sysOfficeDTO1.getId());
        assertThat(sysOfficeDTO1).isEqualTo(sysOfficeDTO2);
        sysOfficeDTO2.setId(2L);
        assertThat(sysOfficeDTO1).isNotEqualTo(sysOfficeDTO2);
        sysOfficeDTO1.setId(null);
        assertThat(sysOfficeDTO1).isNotEqualTo(sysOfficeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysOfficeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysOfficeMapper.fromId(null)).isNull();
    }
}
