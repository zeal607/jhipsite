package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysEmployeeOffice;
import com.ruowei.repository.SysEmployeeOfficeRepository;
import com.ruowei.service.SysEmployeeOfficeService;
import com.ruowei.service.dto.SysEmployeeOfficeDTO;
import com.ruowei.service.mapper.SysEmployeeOfficeMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysEmployeeOfficeCriteria;
import com.ruowei.service.SysEmployeeOfficeQueryService;

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
 * Integration tests for the {@link SysEmployeeOfficeResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysEmployeeOfficeResourceIT {

    private static final String DEFAULT_SYS_EMPLOYEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_EMPLOYEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_OFFICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_OFFICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_POST_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_POST_ID = "BBBBBBBBBB";

    @Autowired
    private SysEmployeeOfficeRepository sysEmployeeOfficeRepository;

    @Autowired
    private SysEmployeeOfficeMapper sysEmployeeOfficeMapper;

    @Autowired
    private SysEmployeeOfficeService sysEmployeeOfficeService;

    @Autowired
    private SysEmployeeOfficeQueryService sysEmployeeOfficeQueryService;

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

    private MockMvc restSysEmployeeOfficeMockMvc;

    private SysEmployeeOffice sysEmployeeOffice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysEmployeeOfficeResource sysEmployeeOfficeResource = new SysEmployeeOfficeResource(sysEmployeeOfficeService, sysEmployeeOfficeQueryService);
        this.restSysEmployeeOfficeMockMvc = MockMvcBuilders.standaloneSetup(sysEmployeeOfficeResource)
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
    public static SysEmployeeOffice createEntity(EntityManager em) {
        SysEmployeeOffice sysEmployeeOffice = new SysEmployeeOffice()
            .sysEmployeeId(DEFAULT_SYS_EMPLOYEE_ID)
            .sysOfficeId(DEFAULT_SYS_OFFICE_ID)
            .sysPostId(DEFAULT_SYS_POST_ID);
        return sysEmployeeOffice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysEmployeeOffice createUpdatedEntity(EntityManager em) {
        SysEmployeeOffice sysEmployeeOffice = new SysEmployeeOffice()
            .sysEmployeeId(UPDATED_SYS_EMPLOYEE_ID)
            .sysOfficeId(UPDATED_SYS_OFFICE_ID)
            .sysPostId(UPDATED_SYS_POST_ID);
        return sysEmployeeOffice;
    }

    @BeforeEach
    public void initTest() {
        sysEmployeeOffice = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysEmployeeOffice() throws Exception {
        int databaseSizeBeforeCreate = sysEmployeeOfficeRepository.findAll().size();

        // Create the SysEmployeeOffice
        SysEmployeeOfficeDTO sysEmployeeOfficeDTO = sysEmployeeOfficeMapper.toDto(sysEmployeeOffice);
        restSysEmployeeOfficeMockMvc.perform(post("/api/sys-employee-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeOfficeDTO)))
            .andExpect(status().isCreated());

        // Validate the SysEmployeeOffice in the database
        List<SysEmployeeOffice> sysEmployeeOfficeList = sysEmployeeOfficeRepository.findAll();
        assertThat(sysEmployeeOfficeList).hasSize(databaseSizeBeforeCreate + 1);
        SysEmployeeOffice testSysEmployeeOffice = sysEmployeeOfficeList.get(sysEmployeeOfficeList.size() - 1);
        assertThat(testSysEmployeeOffice.getSysEmployeeId()).isEqualTo(DEFAULT_SYS_EMPLOYEE_ID);
        assertThat(testSysEmployeeOffice.getSysOfficeId()).isEqualTo(DEFAULT_SYS_OFFICE_ID);
        assertThat(testSysEmployeeOffice.getSysPostId()).isEqualTo(DEFAULT_SYS_POST_ID);
    }

    @Test
    @Transactional
    public void createSysEmployeeOfficeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysEmployeeOfficeRepository.findAll().size();

        // Create the SysEmployeeOffice with an existing ID
        sysEmployeeOffice.setId(1L);
        SysEmployeeOfficeDTO sysEmployeeOfficeDTO = sysEmployeeOfficeMapper.toDto(sysEmployeeOffice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysEmployeeOfficeMockMvc.perform(post("/api/sys-employee-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeOfficeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysEmployeeOffice in the database
        List<SysEmployeeOffice> sysEmployeeOfficeList = sysEmployeeOfficeRepository.findAll();
        assertThat(sysEmployeeOfficeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSysEmployeeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysEmployeeOfficeRepository.findAll().size();
        // set the field null
        sysEmployeeOffice.setSysEmployeeId(null);

        // Create the SysEmployeeOffice, which fails.
        SysEmployeeOfficeDTO sysEmployeeOfficeDTO = sysEmployeeOfficeMapper.toDto(sysEmployeeOffice);

        restSysEmployeeOfficeMockMvc.perform(post("/api/sys-employee-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysEmployeeOffice> sysEmployeeOfficeList = sysEmployeeOfficeRepository.findAll();
        assertThat(sysEmployeeOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSysOfficeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysEmployeeOfficeRepository.findAll().size();
        // set the field null
        sysEmployeeOffice.setSysOfficeId(null);

        // Create the SysEmployeeOffice, which fails.
        SysEmployeeOfficeDTO sysEmployeeOfficeDTO = sysEmployeeOfficeMapper.toDto(sysEmployeeOffice);

        restSysEmployeeOfficeMockMvc.perform(post("/api/sys-employee-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysEmployeeOffice> sysEmployeeOfficeList = sysEmployeeOfficeRepository.findAll();
        assertThat(sysEmployeeOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSysPostIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysEmployeeOfficeRepository.findAll().size();
        // set the field null
        sysEmployeeOffice.setSysPostId(null);

        // Create the SysEmployeeOffice, which fails.
        SysEmployeeOfficeDTO sysEmployeeOfficeDTO = sysEmployeeOfficeMapper.toDto(sysEmployeeOffice);

        restSysEmployeeOfficeMockMvc.perform(post("/api/sys-employee-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysEmployeeOffice> sysEmployeeOfficeList = sysEmployeeOfficeRepository.findAll();
        assertThat(sysEmployeeOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysEmployeeOffices() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get all the sysEmployeeOfficeList
        restSysEmployeeOfficeMockMvc.perform(get("/api/sys-employee-offices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysEmployeeOffice.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysEmployeeId").value(hasItem(DEFAULT_SYS_EMPLOYEE_ID.toString())))
            .andExpect(jsonPath("$.[*].sysOfficeId").value(hasItem(DEFAULT_SYS_OFFICE_ID.toString())))
            .andExpect(jsonPath("$.[*].sysPostId").value(hasItem(DEFAULT_SYS_POST_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getSysEmployeeOffice() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get the sysEmployeeOffice
        restSysEmployeeOfficeMockMvc.perform(get("/api/sys-employee-offices/{id}", sysEmployeeOffice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysEmployeeOffice.getId().intValue()))
            .andExpect(jsonPath("$.sysEmployeeId").value(DEFAULT_SYS_EMPLOYEE_ID.toString()))
            .andExpect(jsonPath("$.sysOfficeId").value(DEFAULT_SYS_OFFICE_ID.toString()))
            .andExpect(jsonPath("$.sysPostId").value(DEFAULT_SYS_POST_ID.toString()));
    }

    @Test
    @Transactional
    public void getAllSysEmployeeOfficesBySysEmployeeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get all the sysEmployeeOfficeList where sysEmployeeId equals to DEFAULT_SYS_EMPLOYEE_ID
        defaultSysEmployeeOfficeShouldBeFound("sysEmployeeId.equals=" + DEFAULT_SYS_EMPLOYEE_ID);

        // Get all the sysEmployeeOfficeList where sysEmployeeId equals to UPDATED_SYS_EMPLOYEE_ID
        defaultSysEmployeeOfficeShouldNotBeFound("sysEmployeeId.equals=" + UPDATED_SYS_EMPLOYEE_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeeOfficesBySysEmployeeIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get all the sysEmployeeOfficeList where sysEmployeeId in DEFAULT_SYS_EMPLOYEE_ID or UPDATED_SYS_EMPLOYEE_ID
        defaultSysEmployeeOfficeShouldBeFound("sysEmployeeId.in=" + DEFAULT_SYS_EMPLOYEE_ID + "," + UPDATED_SYS_EMPLOYEE_ID);

        // Get all the sysEmployeeOfficeList where sysEmployeeId equals to UPDATED_SYS_EMPLOYEE_ID
        defaultSysEmployeeOfficeShouldNotBeFound("sysEmployeeId.in=" + UPDATED_SYS_EMPLOYEE_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeeOfficesBySysEmployeeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get all the sysEmployeeOfficeList where sysEmployeeId is not null
        defaultSysEmployeeOfficeShouldBeFound("sysEmployeeId.specified=true");

        // Get all the sysEmployeeOfficeList where sysEmployeeId is null
        defaultSysEmployeeOfficeShouldNotBeFound("sysEmployeeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeeOfficesBySysOfficeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get all the sysEmployeeOfficeList where sysOfficeId equals to DEFAULT_SYS_OFFICE_ID
        defaultSysEmployeeOfficeShouldBeFound("sysOfficeId.equals=" + DEFAULT_SYS_OFFICE_ID);

        // Get all the sysEmployeeOfficeList where sysOfficeId equals to UPDATED_SYS_OFFICE_ID
        defaultSysEmployeeOfficeShouldNotBeFound("sysOfficeId.equals=" + UPDATED_SYS_OFFICE_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeeOfficesBySysOfficeIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get all the sysEmployeeOfficeList where sysOfficeId in DEFAULT_SYS_OFFICE_ID or UPDATED_SYS_OFFICE_ID
        defaultSysEmployeeOfficeShouldBeFound("sysOfficeId.in=" + DEFAULT_SYS_OFFICE_ID + "," + UPDATED_SYS_OFFICE_ID);

        // Get all the sysEmployeeOfficeList where sysOfficeId equals to UPDATED_SYS_OFFICE_ID
        defaultSysEmployeeOfficeShouldNotBeFound("sysOfficeId.in=" + UPDATED_SYS_OFFICE_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeeOfficesBySysOfficeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get all the sysEmployeeOfficeList where sysOfficeId is not null
        defaultSysEmployeeOfficeShouldBeFound("sysOfficeId.specified=true");

        // Get all the sysEmployeeOfficeList where sysOfficeId is null
        defaultSysEmployeeOfficeShouldNotBeFound("sysOfficeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysEmployeeOfficesBySysPostIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get all the sysEmployeeOfficeList where sysPostId equals to DEFAULT_SYS_POST_ID
        defaultSysEmployeeOfficeShouldBeFound("sysPostId.equals=" + DEFAULT_SYS_POST_ID);

        // Get all the sysEmployeeOfficeList where sysPostId equals to UPDATED_SYS_POST_ID
        defaultSysEmployeeOfficeShouldNotBeFound("sysPostId.equals=" + UPDATED_SYS_POST_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeeOfficesBySysPostIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get all the sysEmployeeOfficeList where sysPostId in DEFAULT_SYS_POST_ID or UPDATED_SYS_POST_ID
        defaultSysEmployeeOfficeShouldBeFound("sysPostId.in=" + DEFAULT_SYS_POST_ID + "," + UPDATED_SYS_POST_ID);

        // Get all the sysEmployeeOfficeList where sysPostId equals to UPDATED_SYS_POST_ID
        defaultSysEmployeeOfficeShouldNotBeFound("sysPostId.in=" + UPDATED_SYS_POST_ID);
    }

    @Test
    @Transactional
    public void getAllSysEmployeeOfficesBySysPostIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        // Get all the sysEmployeeOfficeList where sysPostId is not null
        defaultSysEmployeeOfficeShouldBeFound("sysPostId.specified=true");

        // Get all the sysEmployeeOfficeList where sysPostId is null
        defaultSysEmployeeOfficeShouldNotBeFound("sysPostId.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysEmployeeOfficeShouldBeFound(String filter) throws Exception {
        restSysEmployeeOfficeMockMvc.perform(get("/api/sys-employee-offices?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysEmployeeOffice.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysEmployeeId").value(hasItem(DEFAULT_SYS_EMPLOYEE_ID)))
            .andExpect(jsonPath("$.[*].sysOfficeId").value(hasItem(DEFAULT_SYS_OFFICE_ID)))
            .andExpect(jsonPath("$.[*].sysPostId").value(hasItem(DEFAULT_SYS_POST_ID)));

        // Check, that the count call also returns 1
        restSysEmployeeOfficeMockMvc.perform(get("/api/sys-employee-offices/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysEmployeeOfficeShouldNotBeFound(String filter) throws Exception {
        restSysEmployeeOfficeMockMvc.perform(get("/api/sys-employee-offices?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysEmployeeOfficeMockMvc.perform(get("/api/sys-employee-offices/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysEmployeeOffice() throws Exception {
        // Get the sysEmployeeOffice
        restSysEmployeeOfficeMockMvc.perform(get("/api/sys-employee-offices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysEmployeeOffice() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        int databaseSizeBeforeUpdate = sysEmployeeOfficeRepository.findAll().size();

        // Update the sysEmployeeOffice
        SysEmployeeOffice updatedSysEmployeeOffice = sysEmployeeOfficeRepository.findById(sysEmployeeOffice.getId()).get();
        // Disconnect from session so that the updates on updatedSysEmployeeOffice are not directly saved in db
        em.detach(updatedSysEmployeeOffice);
        updatedSysEmployeeOffice
            .sysEmployeeId(UPDATED_SYS_EMPLOYEE_ID)
            .sysOfficeId(UPDATED_SYS_OFFICE_ID)
            .sysPostId(UPDATED_SYS_POST_ID);
        SysEmployeeOfficeDTO sysEmployeeOfficeDTO = sysEmployeeOfficeMapper.toDto(updatedSysEmployeeOffice);

        restSysEmployeeOfficeMockMvc.perform(put("/api/sys-employee-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeOfficeDTO)))
            .andExpect(status().isOk());

        // Validate the SysEmployeeOffice in the database
        List<SysEmployeeOffice> sysEmployeeOfficeList = sysEmployeeOfficeRepository.findAll();
        assertThat(sysEmployeeOfficeList).hasSize(databaseSizeBeforeUpdate);
        SysEmployeeOffice testSysEmployeeOffice = sysEmployeeOfficeList.get(sysEmployeeOfficeList.size() - 1);
        assertThat(testSysEmployeeOffice.getSysEmployeeId()).isEqualTo(UPDATED_SYS_EMPLOYEE_ID);
        assertThat(testSysEmployeeOffice.getSysOfficeId()).isEqualTo(UPDATED_SYS_OFFICE_ID);
        assertThat(testSysEmployeeOffice.getSysPostId()).isEqualTo(UPDATED_SYS_POST_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSysEmployeeOffice() throws Exception {
        int databaseSizeBeforeUpdate = sysEmployeeOfficeRepository.findAll().size();

        // Create the SysEmployeeOffice
        SysEmployeeOfficeDTO sysEmployeeOfficeDTO = sysEmployeeOfficeMapper.toDto(sysEmployeeOffice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysEmployeeOfficeMockMvc.perform(put("/api/sys-employee-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysEmployeeOfficeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysEmployeeOffice in the database
        List<SysEmployeeOffice> sysEmployeeOfficeList = sysEmployeeOfficeRepository.findAll();
        assertThat(sysEmployeeOfficeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysEmployeeOffice() throws Exception {
        // Initialize the database
        sysEmployeeOfficeRepository.saveAndFlush(sysEmployeeOffice);

        int databaseSizeBeforeDelete = sysEmployeeOfficeRepository.findAll().size();

        // Delete the sysEmployeeOffice
        restSysEmployeeOfficeMockMvc.perform(delete("/api/sys-employee-offices/{id}", sysEmployeeOffice.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysEmployeeOffice> sysEmployeeOfficeList = sysEmployeeOfficeRepository.findAll();
        assertThat(sysEmployeeOfficeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysEmployeeOffice.class);
        SysEmployeeOffice sysEmployeeOffice1 = new SysEmployeeOffice();
        sysEmployeeOffice1.setId(1L);
        SysEmployeeOffice sysEmployeeOffice2 = new SysEmployeeOffice();
        sysEmployeeOffice2.setId(sysEmployeeOffice1.getId());
        assertThat(sysEmployeeOffice1).isEqualTo(sysEmployeeOffice2);
        sysEmployeeOffice2.setId(2L);
        assertThat(sysEmployeeOffice1).isNotEqualTo(sysEmployeeOffice2);
        sysEmployeeOffice1.setId(null);
        assertThat(sysEmployeeOffice1).isNotEqualTo(sysEmployeeOffice2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysEmployeeOfficeDTO.class);
        SysEmployeeOfficeDTO sysEmployeeOfficeDTO1 = new SysEmployeeOfficeDTO();
        sysEmployeeOfficeDTO1.setId(1L);
        SysEmployeeOfficeDTO sysEmployeeOfficeDTO2 = new SysEmployeeOfficeDTO();
        assertThat(sysEmployeeOfficeDTO1).isNotEqualTo(sysEmployeeOfficeDTO2);
        sysEmployeeOfficeDTO2.setId(sysEmployeeOfficeDTO1.getId());
        assertThat(sysEmployeeOfficeDTO1).isEqualTo(sysEmployeeOfficeDTO2);
        sysEmployeeOfficeDTO2.setId(2L);
        assertThat(sysEmployeeOfficeDTO1).isNotEqualTo(sysEmployeeOfficeDTO2);
        sysEmployeeOfficeDTO1.setId(null);
        assertThat(sysEmployeeOfficeDTO1).isNotEqualTo(sysEmployeeOfficeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysEmployeeOfficeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysEmployeeOfficeMapper.fromId(null)).isNull();
    }
}
