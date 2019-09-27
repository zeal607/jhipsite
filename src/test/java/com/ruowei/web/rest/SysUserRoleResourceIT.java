package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.modules.sys.domain.SysUserRole;
import com.ruowei.modules.sys.repository.SysUserRoleRepository;
import com.ruowei.modules.sys.service.user.SysUserRoleService;
import com.ruowei.modules.sys.pojo.SysUserRoleDTO;
import com.ruowei.modules.sys.mapper.SysUserRoleMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.modules.sys.service.user.SysUserRoleQueryService;

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
 * Integration tests for the {@link SysUserRoleResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysUserRoleResourceIT {

    private static final String DEFAULT_SYS_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_ROLE_ID = "BBBBBBBBBB";

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserRoleQueryService sysUserRoleQueryService;

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

    private MockMvc restSysUserRoleMockMvc;

    private SysUserRole sysUserRole;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysUserRoleResource sysUserRoleResource = new SysUserRoleResource(sysUserRoleService, sysUserRoleQueryService);
        this.restSysUserRoleMockMvc = MockMvcBuilders.standaloneSetup(sysUserRoleResource)
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
    public static SysUserRole createEntity(EntityManager em) {
        SysUserRole sysUserRole = new SysUserRole()
            .sysUserId(DEFAULT_SYS_USER_ID)
            .sysRoleId(DEFAULT_SYS_ROLE_ID);
        return sysUserRole;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysUserRole createUpdatedEntity(EntityManager em) {
        SysUserRole sysUserRole = new SysUserRole()
            .sysUserId(UPDATED_SYS_USER_ID)
            .sysRoleId(UPDATED_SYS_ROLE_ID);
        return sysUserRole;
    }

    @BeforeEach
    public void initTest() {
        sysUserRole = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysUserRole() throws Exception {
        int databaseSizeBeforeCreate = sysUserRoleRepository.findAll().size();

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);
        restSysUserRoleMockMvc.perform(post("/api/sys-user-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO)))
            .andExpect(status().isCreated());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeCreate + 1);
        SysUserRole testSysUserRole = sysUserRoleList.get(sysUserRoleList.size() - 1);
        assertThat(testSysUserRole.getSysUserId()).isEqualTo(DEFAULT_SYS_USER_ID);
        assertThat(testSysUserRole.getSysRoleId()).isEqualTo(DEFAULT_SYS_ROLE_ID);
    }

    @Test
    @Transactional
    public void createSysUserRoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysUserRoleRepository.findAll().size();

        // Create the SysUserRole with an existing ID
        sysUserRole.setId(1L);
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysUserRoleMockMvc.perform(post("/api/sys-user-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSysUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysUserRoleRepository.findAll().size();
        // set the field null
        sysUserRole.setSysUserId(null);

        // Create the SysUserRole, which fails.
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        restSysUserRoleMockMvc.perform(post("/api/sys-user-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO)))
            .andExpect(status().isBadRequest());

        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSysRoleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysUserRoleRepository.findAll().size();
        // set the field null
        sysUserRole.setSysRoleId(null);

        // Create the SysUserRole, which fails.
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        restSysUserRoleMockMvc.perform(post("/api/sys-user-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO)))
            .andExpect(status().isBadRequest());

        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysUserRoles() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get all the sysUserRoleList
        restSysUserRoleMockMvc.perform(get("/api/sys-user-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUserRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysUserId").value(hasItem(DEFAULT_SYS_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].sysRoleId").value(hasItem(DEFAULT_SYS_ROLE_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getSysUserRole() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get the sysUserRole
        restSysUserRoleMockMvc.perform(get("/api/sys-user-roles/{id}", sysUserRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysUserRole.getId().intValue()))
            .andExpect(jsonPath("$.sysUserId").value(DEFAULT_SYS_USER_ID.toString()))
            .andExpect(jsonPath("$.sysRoleId").value(DEFAULT_SYS_ROLE_ID.toString()));
    }

    @Test
    @Transactional
    public void getAllSysUserRolesBySysUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get all the sysUserRoleList where sysUserId equals to DEFAULT_SYS_USER_ID
        defaultSysUserRoleShouldBeFound("sysUserId.equals=" + DEFAULT_SYS_USER_ID);

        // Get all the sysUserRoleList where sysUserId equals to UPDATED_SYS_USER_ID
        defaultSysUserRoleShouldNotBeFound("sysUserId.equals=" + UPDATED_SYS_USER_ID);
    }

    @Test
    @Transactional
    public void getAllSysUserRolesBySysUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get all the sysUserRoleList where sysUserId in DEFAULT_SYS_USER_ID or UPDATED_SYS_USER_ID
        defaultSysUserRoleShouldBeFound("sysUserId.in=" + DEFAULT_SYS_USER_ID + "," + UPDATED_SYS_USER_ID);

        // Get all the sysUserRoleList where sysUserId equals to UPDATED_SYS_USER_ID
        defaultSysUserRoleShouldNotBeFound("sysUserId.in=" + UPDATED_SYS_USER_ID);
    }

    @Test
    @Transactional
    public void getAllSysUserRolesBySysUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get all the sysUserRoleList where sysUserId is not null
        defaultSysUserRoleShouldBeFound("sysUserId.specified=true");

        // Get all the sysUserRoleList where sysUserId is null
        defaultSysUserRoleShouldNotBeFound("sysUserId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUserRolesBySysRoleIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get all the sysUserRoleList where sysRoleId equals to DEFAULT_SYS_ROLE_ID
        defaultSysUserRoleShouldBeFound("sysRoleId.equals=" + DEFAULT_SYS_ROLE_ID);

        // Get all the sysUserRoleList where sysRoleId equals to UPDATED_SYS_ROLE_ID
        defaultSysUserRoleShouldNotBeFound("sysRoleId.equals=" + UPDATED_SYS_ROLE_ID);
    }

    @Test
    @Transactional
    public void getAllSysUserRolesBySysRoleIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get all the sysUserRoleList where sysRoleId in DEFAULT_SYS_ROLE_ID or UPDATED_SYS_ROLE_ID
        defaultSysUserRoleShouldBeFound("sysRoleId.in=" + DEFAULT_SYS_ROLE_ID + "," + UPDATED_SYS_ROLE_ID);

        // Get all the sysUserRoleList where sysRoleId equals to UPDATED_SYS_ROLE_ID
        defaultSysUserRoleShouldNotBeFound("sysRoleId.in=" + UPDATED_SYS_ROLE_ID);
    }

    @Test
    @Transactional
    public void getAllSysUserRolesBySysRoleIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get all the sysUserRoleList where sysRoleId is not null
        defaultSysUserRoleShouldBeFound("sysRoleId.specified=true");

        // Get all the sysUserRoleList where sysRoleId is null
        defaultSysUserRoleShouldNotBeFound("sysRoleId.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysUserRoleShouldBeFound(String filter) throws Exception {
        restSysUserRoleMockMvc.perform(get("/api/sys-user-roles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUserRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysUserId").value(hasItem(DEFAULT_SYS_USER_ID)))
            .andExpect(jsonPath("$.[*].sysRoleId").value(hasItem(DEFAULT_SYS_ROLE_ID)));

        // Check, that the count call also returns 1
        restSysUserRoleMockMvc.perform(get("/api/sys-user-roles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysUserRoleShouldNotBeFound(String filter) throws Exception {
        restSysUserRoleMockMvc.perform(get("/api/sys-user-roles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysUserRoleMockMvc.perform(get("/api/sys-user-roles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysUserRole() throws Exception {
        // Get the sysUserRole
        restSysUserRoleMockMvc.perform(get("/api/sys-user-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysUserRole() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();

        // Update the sysUserRole
        SysUserRole updatedSysUserRole = sysUserRoleRepository.findById(sysUserRole.getId()).get();
        // Disconnect from session so that the updates on updatedSysUserRole are not directly saved in db
        em.detach(updatedSysUserRole);
        updatedSysUserRole
            .sysUserId(UPDATED_SYS_USER_ID)
            .sysRoleId(UPDATED_SYS_ROLE_ID);
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(updatedSysUserRole);

        restSysUserRoleMockMvc.perform(put("/api/sys-user-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO)))
            .andExpect(status().isOk());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
        SysUserRole testSysUserRole = sysUserRoleList.get(sysUserRoleList.size() - 1);
        assertThat(testSysUserRole.getSysUserId()).isEqualTo(UPDATED_SYS_USER_ID);
        assertThat(testSysUserRole.getSysRoleId()).isEqualTo(UPDATED_SYS_ROLE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc.perform(put("/api/sys-user-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysUserRole() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        int databaseSizeBeforeDelete = sysUserRoleRepository.findAll().size();

        // Delete the sysUserRole
        restSysUserRoleMockMvc.perform(delete("/api/sys-user-roles/{id}", sysUserRole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserRole.class);
        SysUserRole sysUserRole1 = new SysUserRole();
        sysUserRole1.setId(1L);
        SysUserRole sysUserRole2 = new SysUserRole();
        sysUserRole2.setId(sysUserRole1.getId());
        assertThat(sysUserRole1).isEqualTo(sysUserRole2);
        sysUserRole2.setId(2L);
        assertThat(sysUserRole1).isNotEqualTo(sysUserRole2);
        sysUserRole1.setId(null);
        assertThat(sysUserRole1).isNotEqualTo(sysUserRole2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserRoleDTO.class);
        SysUserRoleDTO sysUserRoleDTO1 = new SysUserRoleDTO();
        sysUserRoleDTO1.setId(1L);
        SysUserRoleDTO sysUserRoleDTO2 = new SysUserRoleDTO();
        assertThat(sysUserRoleDTO1).isNotEqualTo(sysUserRoleDTO2);
        sysUserRoleDTO2.setId(sysUserRoleDTO1.getId());
        assertThat(sysUserRoleDTO1).isEqualTo(sysUserRoleDTO2);
        sysUserRoleDTO2.setId(2L);
        assertThat(sysUserRoleDTO1).isNotEqualTo(sysUserRoleDTO2);
        sysUserRoleDTO1.setId(null);
        assertThat(sysUserRoleDTO1).isNotEqualTo(sysUserRoleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysUserRoleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysUserRoleMapper.fromId(null)).isNull();
    }
}
