package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysCompanyOffice;
import com.ruowei.repository.SysCompanyOfficeRepository;
import com.ruowei.service.SysCompanyOfficeService;
import com.ruowei.service.dto.SysCompanyOfficeDTO;
import com.ruowei.service.mapper.SysCompanyOfficeMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysCompanyOfficeCriteria;
import com.ruowei.service.SysCompanyOfficeQueryService;

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
 * Integration tests for the {@link SysCompanyOfficeResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysCompanyOfficeResourceIT {

    private static final String DEFAULT_SYS_COMPANY_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_COMPANY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_OFFICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_OFFICE_ID = "BBBBBBBBBB";

    @Autowired
    private SysCompanyOfficeRepository sysCompanyOfficeRepository;

    @Autowired
    private SysCompanyOfficeMapper sysCompanyOfficeMapper;

    @Autowired
    private SysCompanyOfficeService sysCompanyOfficeService;

    @Autowired
    private SysCompanyOfficeQueryService sysCompanyOfficeQueryService;

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

    private MockMvc restSysCompanyOfficeMockMvc;

    private SysCompanyOffice sysCompanyOffice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysCompanyOfficeResource sysCompanyOfficeResource = new SysCompanyOfficeResource(sysCompanyOfficeService, sysCompanyOfficeQueryService);
        this.restSysCompanyOfficeMockMvc = MockMvcBuilders.standaloneSetup(sysCompanyOfficeResource)
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
    public static SysCompanyOffice createEntity(EntityManager em) {
        SysCompanyOffice sysCompanyOffice = new SysCompanyOffice()
            .sysCompanyId(DEFAULT_SYS_COMPANY_ID)
            .sysOfficeId(DEFAULT_SYS_OFFICE_ID);
        return sysCompanyOffice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysCompanyOffice createUpdatedEntity(EntityManager em) {
        SysCompanyOffice sysCompanyOffice = new SysCompanyOffice()
            .sysCompanyId(UPDATED_SYS_COMPANY_ID)
            .sysOfficeId(UPDATED_SYS_OFFICE_ID);
        return sysCompanyOffice;
    }

    @BeforeEach
    public void initTest() {
        sysCompanyOffice = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysCompanyOffice() throws Exception {
        int databaseSizeBeforeCreate = sysCompanyOfficeRepository.findAll().size();

        // Create the SysCompanyOffice
        SysCompanyOfficeDTO sysCompanyOfficeDTO = sysCompanyOfficeMapper.toDto(sysCompanyOffice);
        restSysCompanyOfficeMockMvc.perform(post("/api/sys-company-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyOfficeDTO)))
            .andExpect(status().isCreated());

        // Validate the SysCompanyOffice in the database
        List<SysCompanyOffice> sysCompanyOfficeList = sysCompanyOfficeRepository.findAll();
        assertThat(sysCompanyOfficeList).hasSize(databaseSizeBeforeCreate + 1);
        SysCompanyOffice testSysCompanyOffice = sysCompanyOfficeList.get(sysCompanyOfficeList.size() - 1);
        assertThat(testSysCompanyOffice.getSysCompanyId()).isEqualTo(DEFAULT_SYS_COMPANY_ID);
        assertThat(testSysCompanyOffice.getSysOfficeId()).isEqualTo(DEFAULT_SYS_OFFICE_ID);
    }

    @Test
    @Transactional
    public void createSysCompanyOfficeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysCompanyOfficeRepository.findAll().size();

        // Create the SysCompanyOffice with an existing ID
        sysCompanyOffice.setId(1L);
        SysCompanyOfficeDTO sysCompanyOfficeDTO = sysCompanyOfficeMapper.toDto(sysCompanyOffice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysCompanyOfficeMockMvc.perform(post("/api/sys-company-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyOfficeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysCompanyOffice in the database
        List<SysCompanyOffice> sysCompanyOfficeList = sysCompanyOfficeRepository.findAll();
        assertThat(sysCompanyOfficeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSysCompanyIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysCompanyOfficeRepository.findAll().size();
        // set the field null
        sysCompanyOffice.setSysCompanyId(null);

        // Create the SysCompanyOffice, which fails.
        SysCompanyOfficeDTO sysCompanyOfficeDTO = sysCompanyOfficeMapper.toDto(sysCompanyOffice);

        restSysCompanyOfficeMockMvc.perform(post("/api/sys-company-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysCompanyOffice> sysCompanyOfficeList = sysCompanyOfficeRepository.findAll();
        assertThat(sysCompanyOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSysOfficeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysCompanyOfficeRepository.findAll().size();
        // set the field null
        sysCompanyOffice.setSysOfficeId(null);

        // Create the SysCompanyOffice, which fails.
        SysCompanyOfficeDTO sysCompanyOfficeDTO = sysCompanyOfficeMapper.toDto(sysCompanyOffice);

        restSysCompanyOfficeMockMvc.perform(post("/api/sys-company-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyOfficeDTO)))
            .andExpect(status().isBadRequest());

        List<SysCompanyOffice> sysCompanyOfficeList = sysCompanyOfficeRepository.findAll();
        assertThat(sysCompanyOfficeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysCompanyOffices() throws Exception {
        // Initialize the database
        sysCompanyOfficeRepository.saveAndFlush(sysCompanyOffice);

        // Get all the sysCompanyOfficeList
        restSysCompanyOfficeMockMvc.perform(get("/api/sys-company-offices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCompanyOffice.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysCompanyId").value(hasItem(DEFAULT_SYS_COMPANY_ID.toString())))
            .andExpect(jsonPath("$.[*].sysOfficeId").value(hasItem(DEFAULT_SYS_OFFICE_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getSysCompanyOffice() throws Exception {
        // Initialize the database
        sysCompanyOfficeRepository.saveAndFlush(sysCompanyOffice);

        // Get the sysCompanyOffice
        restSysCompanyOfficeMockMvc.perform(get("/api/sys-company-offices/{id}", sysCompanyOffice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysCompanyOffice.getId().intValue()))
            .andExpect(jsonPath("$.sysCompanyId").value(DEFAULT_SYS_COMPANY_ID.toString()))
            .andExpect(jsonPath("$.sysOfficeId").value(DEFAULT_SYS_OFFICE_ID.toString()));
    }

    @Test
    @Transactional
    public void getAllSysCompanyOfficesBySysCompanyIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyOfficeRepository.saveAndFlush(sysCompanyOffice);

        // Get all the sysCompanyOfficeList where sysCompanyId equals to DEFAULT_SYS_COMPANY_ID
        defaultSysCompanyOfficeShouldBeFound("sysCompanyId.equals=" + DEFAULT_SYS_COMPANY_ID);

        // Get all the sysCompanyOfficeList where sysCompanyId equals to UPDATED_SYS_COMPANY_ID
        defaultSysCompanyOfficeShouldNotBeFound("sysCompanyId.equals=" + UPDATED_SYS_COMPANY_ID);
    }

    @Test
    @Transactional
    public void getAllSysCompanyOfficesBySysCompanyIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyOfficeRepository.saveAndFlush(sysCompanyOffice);

        // Get all the sysCompanyOfficeList where sysCompanyId in DEFAULT_SYS_COMPANY_ID or UPDATED_SYS_COMPANY_ID
        defaultSysCompanyOfficeShouldBeFound("sysCompanyId.in=" + DEFAULT_SYS_COMPANY_ID + "," + UPDATED_SYS_COMPANY_ID);

        // Get all the sysCompanyOfficeList where sysCompanyId equals to UPDATED_SYS_COMPANY_ID
        defaultSysCompanyOfficeShouldNotBeFound("sysCompanyId.in=" + UPDATED_SYS_COMPANY_ID);
    }

    @Test
    @Transactional
    public void getAllSysCompanyOfficesBySysCompanyIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyOfficeRepository.saveAndFlush(sysCompanyOffice);

        // Get all the sysCompanyOfficeList where sysCompanyId is not null
        defaultSysCompanyOfficeShouldBeFound("sysCompanyId.specified=true");

        // Get all the sysCompanyOfficeList where sysCompanyId is null
        defaultSysCompanyOfficeShouldNotBeFound("sysCompanyId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysCompanyOfficesBySysOfficeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysCompanyOfficeRepository.saveAndFlush(sysCompanyOffice);

        // Get all the sysCompanyOfficeList where sysOfficeId equals to DEFAULT_SYS_OFFICE_ID
        defaultSysCompanyOfficeShouldBeFound("sysOfficeId.equals=" + DEFAULT_SYS_OFFICE_ID);

        // Get all the sysCompanyOfficeList where sysOfficeId equals to UPDATED_SYS_OFFICE_ID
        defaultSysCompanyOfficeShouldNotBeFound("sysOfficeId.equals=" + UPDATED_SYS_OFFICE_ID);
    }

    @Test
    @Transactional
    public void getAllSysCompanyOfficesBySysOfficeIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysCompanyOfficeRepository.saveAndFlush(sysCompanyOffice);

        // Get all the sysCompanyOfficeList where sysOfficeId in DEFAULT_SYS_OFFICE_ID or UPDATED_SYS_OFFICE_ID
        defaultSysCompanyOfficeShouldBeFound("sysOfficeId.in=" + DEFAULT_SYS_OFFICE_ID + "," + UPDATED_SYS_OFFICE_ID);

        // Get all the sysCompanyOfficeList where sysOfficeId equals to UPDATED_SYS_OFFICE_ID
        defaultSysCompanyOfficeShouldNotBeFound("sysOfficeId.in=" + UPDATED_SYS_OFFICE_ID);
    }

    @Test
    @Transactional
    public void getAllSysCompanyOfficesBySysOfficeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysCompanyOfficeRepository.saveAndFlush(sysCompanyOffice);

        // Get all the sysCompanyOfficeList where sysOfficeId is not null
        defaultSysCompanyOfficeShouldBeFound("sysOfficeId.specified=true");

        // Get all the sysCompanyOfficeList where sysOfficeId is null
        defaultSysCompanyOfficeShouldNotBeFound("sysOfficeId.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysCompanyOfficeShouldBeFound(String filter) throws Exception {
        restSysCompanyOfficeMockMvc.perform(get("/api/sys-company-offices?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCompanyOffice.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysCompanyId").value(hasItem(DEFAULT_SYS_COMPANY_ID)))
            .andExpect(jsonPath("$.[*].sysOfficeId").value(hasItem(DEFAULT_SYS_OFFICE_ID)));

        // Check, that the count call also returns 1
        restSysCompanyOfficeMockMvc.perform(get("/api/sys-company-offices/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysCompanyOfficeShouldNotBeFound(String filter) throws Exception {
        restSysCompanyOfficeMockMvc.perform(get("/api/sys-company-offices?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysCompanyOfficeMockMvc.perform(get("/api/sys-company-offices/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysCompanyOffice() throws Exception {
        // Get the sysCompanyOffice
        restSysCompanyOfficeMockMvc.perform(get("/api/sys-company-offices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysCompanyOffice() throws Exception {
        // Initialize the database
        sysCompanyOfficeRepository.saveAndFlush(sysCompanyOffice);

        int databaseSizeBeforeUpdate = sysCompanyOfficeRepository.findAll().size();

        // Update the sysCompanyOffice
        SysCompanyOffice updatedSysCompanyOffice = sysCompanyOfficeRepository.findById(sysCompanyOffice.getId()).get();
        // Disconnect from session so that the updates on updatedSysCompanyOffice are not directly saved in db
        em.detach(updatedSysCompanyOffice);
        updatedSysCompanyOffice
            .sysCompanyId(UPDATED_SYS_COMPANY_ID)
            .sysOfficeId(UPDATED_SYS_OFFICE_ID);
        SysCompanyOfficeDTO sysCompanyOfficeDTO = sysCompanyOfficeMapper.toDto(updatedSysCompanyOffice);

        restSysCompanyOfficeMockMvc.perform(put("/api/sys-company-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyOfficeDTO)))
            .andExpect(status().isOk());

        // Validate the SysCompanyOffice in the database
        List<SysCompanyOffice> sysCompanyOfficeList = sysCompanyOfficeRepository.findAll();
        assertThat(sysCompanyOfficeList).hasSize(databaseSizeBeforeUpdate);
        SysCompanyOffice testSysCompanyOffice = sysCompanyOfficeList.get(sysCompanyOfficeList.size() - 1);
        assertThat(testSysCompanyOffice.getSysCompanyId()).isEqualTo(UPDATED_SYS_COMPANY_ID);
        assertThat(testSysCompanyOffice.getSysOfficeId()).isEqualTo(UPDATED_SYS_OFFICE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSysCompanyOffice() throws Exception {
        int databaseSizeBeforeUpdate = sysCompanyOfficeRepository.findAll().size();

        // Create the SysCompanyOffice
        SysCompanyOfficeDTO sysCompanyOfficeDTO = sysCompanyOfficeMapper.toDto(sysCompanyOffice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCompanyOfficeMockMvc.perform(put("/api/sys-company-offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCompanyOfficeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysCompanyOffice in the database
        List<SysCompanyOffice> sysCompanyOfficeList = sysCompanyOfficeRepository.findAll();
        assertThat(sysCompanyOfficeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysCompanyOffice() throws Exception {
        // Initialize the database
        sysCompanyOfficeRepository.saveAndFlush(sysCompanyOffice);

        int databaseSizeBeforeDelete = sysCompanyOfficeRepository.findAll().size();

        // Delete the sysCompanyOffice
        restSysCompanyOfficeMockMvc.perform(delete("/api/sys-company-offices/{id}", sysCompanyOffice.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysCompanyOffice> sysCompanyOfficeList = sysCompanyOfficeRepository.findAll();
        assertThat(sysCompanyOfficeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCompanyOffice.class);
        SysCompanyOffice sysCompanyOffice1 = new SysCompanyOffice();
        sysCompanyOffice1.setId(1L);
        SysCompanyOffice sysCompanyOffice2 = new SysCompanyOffice();
        sysCompanyOffice2.setId(sysCompanyOffice1.getId());
        assertThat(sysCompanyOffice1).isEqualTo(sysCompanyOffice2);
        sysCompanyOffice2.setId(2L);
        assertThat(sysCompanyOffice1).isNotEqualTo(sysCompanyOffice2);
        sysCompanyOffice1.setId(null);
        assertThat(sysCompanyOffice1).isNotEqualTo(sysCompanyOffice2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCompanyOfficeDTO.class);
        SysCompanyOfficeDTO sysCompanyOfficeDTO1 = new SysCompanyOfficeDTO();
        sysCompanyOfficeDTO1.setId(1L);
        SysCompanyOfficeDTO sysCompanyOfficeDTO2 = new SysCompanyOfficeDTO();
        assertThat(sysCompanyOfficeDTO1).isNotEqualTo(sysCompanyOfficeDTO2);
        sysCompanyOfficeDTO2.setId(sysCompanyOfficeDTO1.getId());
        assertThat(sysCompanyOfficeDTO1).isEqualTo(sysCompanyOfficeDTO2);
        sysCompanyOfficeDTO2.setId(2L);
        assertThat(sysCompanyOfficeDTO1).isNotEqualTo(sysCompanyOfficeDTO2);
        sysCompanyOfficeDTO1.setId(null);
        assertThat(sysCompanyOfficeDTO1).isNotEqualTo(sysCompanyOfficeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysCompanyOfficeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysCompanyOfficeMapper.fromId(null)).isNull();
    }
}
