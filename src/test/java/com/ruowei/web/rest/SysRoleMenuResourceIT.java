package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysRoleMenu;
import com.ruowei.repository.SysRoleMenuRepository;
import com.ruowei.service.SysRoleMenuService;
import com.ruowei.service.dto.SysRoleMenuDTO;
import com.ruowei.service.mapper.SysRoleMenuMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysRoleMenuCriteria;
import com.ruowei.service.SysRoleMenuQueryService;

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
 * Integration tests for the {@link SysRoleMenuResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysRoleMenuResourceIT {

    private static final String DEFAULT_SYS_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_ROLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_MENU_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_MENU_ID = "BBBBBBBBBB";

    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysRoleMenuQueryService sysRoleMenuQueryService;

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

    private MockMvc restSysRoleMenuMockMvc;

    private SysRoleMenu sysRoleMenu;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysRoleMenuResource sysRoleMenuResource = new SysRoleMenuResource(sysRoleMenuService, sysRoleMenuQueryService);
        this.restSysRoleMenuMockMvc = MockMvcBuilders.standaloneSetup(sysRoleMenuResource)
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
    public static SysRoleMenu createEntity(EntityManager em) {
        SysRoleMenu sysRoleMenu = new SysRoleMenu()
            .sysRoleId(DEFAULT_SYS_ROLE_ID)
            .sysMenuId(DEFAULT_SYS_MENU_ID);
        return sysRoleMenu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRoleMenu createUpdatedEntity(EntityManager em) {
        SysRoleMenu sysRoleMenu = new SysRoleMenu()
            .sysRoleId(UPDATED_SYS_ROLE_ID)
            .sysMenuId(UPDATED_SYS_MENU_ID);
        return sysRoleMenu;
    }

    @BeforeEach
    public void initTest() {
        sysRoleMenu = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysRoleMenu() throws Exception {
        int databaseSizeBeforeCreate = sysRoleMenuRepository.findAll().size();

        // Create the SysRoleMenu
        SysRoleMenuDTO sysRoleMenuDTO = sysRoleMenuMapper.toDto(sysRoleMenu);
        restSysRoleMenuMockMvc.perform(post("/api/sys-role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleMenuDTO)))
            .andExpect(status().isCreated());

        // Validate the SysRoleMenu in the database
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuRepository.findAll();
        assertThat(sysRoleMenuList).hasSize(databaseSizeBeforeCreate + 1);
        SysRoleMenu testSysRoleMenu = sysRoleMenuList.get(sysRoleMenuList.size() - 1);
        assertThat(testSysRoleMenu.getSysRoleId()).isEqualTo(DEFAULT_SYS_ROLE_ID);
        assertThat(testSysRoleMenu.getSysMenuId()).isEqualTo(DEFAULT_SYS_MENU_ID);
    }

    @Test
    @Transactional
    public void createSysRoleMenuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysRoleMenuRepository.findAll().size();

        // Create the SysRoleMenu with an existing ID
        sysRoleMenu.setId(1L);
        SysRoleMenuDTO sysRoleMenuDTO = sysRoleMenuMapper.toDto(sysRoleMenu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysRoleMenuMockMvc.perform(post("/api/sys-role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleMenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysRoleMenu in the database
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuRepository.findAll();
        assertThat(sysRoleMenuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSysRoleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysRoleMenuRepository.findAll().size();
        // set the field null
        sysRoleMenu.setSysRoleId(null);

        // Create the SysRoleMenu, which fails.
        SysRoleMenuDTO sysRoleMenuDTO = sysRoleMenuMapper.toDto(sysRoleMenu);

        restSysRoleMenuMockMvc.perform(post("/api/sys-role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuRepository.findAll();
        assertThat(sysRoleMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSysMenuIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysRoleMenuRepository.findAll().size();
        // set the field null
        sysRoleMenu.setSysMenuId(null);

        // Create the SysRoleMenu, which fails.
        SysRoleMenuDTO sysRoleMenuDTO = sysRoleMenuMapper.toDto(sysRoleMenu);

        restSysRoleMenuMockMvc.perform(post("/api/sys-role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuRepository.findAll();
        assertThat(sysRoleMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysRoleMenus() throws Exception {
        // Initialize the database
        sysRoleMenuRepository.saveAndFlush(sysRoleMenu);

        // Get all the sysRoleMenuList
        restSysRoleMenuMockMvc.perform(get("/api/sys-role-menus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRoleMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysRoleId").value(hasItem(DEFAULT_SYS_ROLE_ID.toString())))
            .andExpect(jsonPath("$.[*].sysMenuId").value(hasItem(DEFAULT_SYS_MENU_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getSysRoleMenu() throws Exception {
        // Initialize the database
        sysRoleMenuRepository.saveAndFlush(sysRoleMenu);

        // Get the sysRoleMenu
        restSysRoleMenuMockMvc.perform(get("/api/sys-role-menus/{id}", sysRoleMenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysRoleMenu.getId().intValue()))
            .andExpect(jsonPath("$.sysRoleId").value(DEFAULT_SYS_ROLE_ID.toString()))
            .andExpect(jsonPath("$.sysMenuId").value(DEFAULT_SYS_MENU_ID.toString()));
    }

    @Test
    @Transactional
    public void getAllSysRoleMenusBySysRoleIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleMenuRepository.saveAndFlush(sysRoleMenu);

        // Get all the sysRoleMenuList where sysRoleId equals to DEFAULT_SYS_ROLE_ID
        defaultSysRoleMenuShouldBeFound("sysRoleId.equals=" + DEFAULT_SYS_ROLE_ID);

        // Get all the sysRoleMenuList where sysRoleId equals to UPDATED_SYS_ROLE_ID
        defaultSysRoleMenuShouldNotBeFound("sysRoleId.equals=" + UPDATED_SYS_ROLE_ID);
    }

    @Test
    @Transactional
    public void getAllSysRoleMenusBySysRoleIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleMenuRepository.saveAndFlush(sysRoleMenu);

        // Get all the sysRoleMenuList where sysRoleId in DEFAULT_SYS_ROLE_ID or UPDATED_SYS_ROLE_ID
        defaultSysRoleMenuShouldBeFound("sysRoleId.in=" + DEFAULT_SYS_ROLE_ID + "," + UPDATED_SYS_ROLE_ID);

        // Get all the sysRoleMenuList where sysRoleId equals to UPDATED_SYS_ROLE_ID
        defaultSysRoleMenuShouldNotBeFound("sysRoleId.in=" + UPDATED_SYS_ROLE_ID);
    }

    @Test
    @Transactional
    public void getAllSysRoleMenusBySysRoleIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleMenuRepository.saveAndFlush(sysRoleMenu);

        // Get all the sysRoleMenuList where sysRoleId is not null
        defaultSysRoleMenuShouldBeFound("sysRoleId.specified=true");

        // Get all the sysRoleMenuList where sysRoleId is null
        defaultSysRoleMenuShouldNotBeFound("sysRoleId.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysRoleMenusBySysMenuIdIsEqualToSomething() throws Exception {
        // Initialize the database
        sysRoleMenuRepository.saveAndFlush(sysRoleMenu);

        // Get all the sysRoleMenuList where sysMenuId equals to DEFAULT_SYS_MENU_ID
        defaultSysRoleMenuShouldBeFound("sysMenuId.equals=" + DEFAULT_SYS_MENU_ID);

        // Get all the sysRoleMenuList where sysMenuId equals to UPDATED_SYS_MENU_ID
        defaultSysRoleMenuShouldNotBeFound("sysMenuId.equals=" + UPDATED_SYS_MENU_ID);
    }

    @Test
    @Transactional
    public void getAllSysRoleMenusBySysMenuIdIsInShouldWork() throws Exception {
        // Initialize the database
        sysRoleMenuRepository.saveAndFlush(sysRoleMenu);

        // Get all the sysRoleMenuList where sysMenuId in DEFAULT_SYS_MENU_ID or UPDATED_SYS_MENU_ID
        defaultSysRoleMenuShouldBeFound("sysMenuId.in=" + DEFAULT_SYS_MENU_ID + "," + UPDATED_SYS_MENU_ID);

        // Get all the sysRoleMenuList where sysMenuId equals to UPDATED_SYS_MENU_ID
        defaultSysRoleMenuShouldNotBeFound("sysMenuId.in=" + UPDATED_SYS_MENU_ID);
    }

    @Test
    @Transactional
    public void getAllSysRoleMenusBySysMenuIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysRoleMenuRepository.saveAndFlush(sysRoleMenu);

        // Get all the sysRoleMenuList where sysMenuId is not null
        defaultSysRoleMenuShouldBeFound("sysMenuId.specified=true");

        // Get all the sysRoleMenuList where sysMenuId is null
        defaultSysRoleMenuShouldNotBeFound("sysMenuId.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysRoleMenuShouldBeFound(String filter) throws Exception {
        restSysRoleMenuMockMvc.perform(get("/api/sys-role-menus?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRoleMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].sysRoleId").value(hasItem(DEFAULT_SYS_ROLE_ID)))
            .andExpect(jsonPath("$.[*].sysMenuId").value(hasItem(DEFAULT_SYS_MENU_ID)));

        // Check, that the count call also returns 1
        restSysRoleMenuMockMvc.perform(get("/api/sys-role-menus/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysRoleMenuShouldNotBeFound(String filter) throws Exception {
        restSysRoleMenuMockMvc.perform(get("/api/sys-role-menus?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysRoleMenuMockMvc.perform(get("/api/sys-role-menus/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysRoleMenu() throws Exception {
        // Get the sysRoleMenu
        restSysRoleMenuMockMvc.perform(get("/api/sys-role-menus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysRoleMenu() throws Exception {
        // Initialize the database
        sysRoleMenuRepository.saveAndFlush(sysRoleMenu);

        int databaseSizeBeforeUpdate = sysRoleMenuRepository.findAll().size();

        // Update the sysRoleMenu
        SysRoleMenu updatedSysRoleMenu = sysRoleMenuRepository.findById(sysRoleMenu.getId()).get();
        // Disconnect from session so that the updates on updatedSysRoleMenu are not directly saved in db
        em.detach(updatedSysRoleMenu);
        updatedSysRoleMenu
            .sysRoleId(UPDATED_SYS_ROLE_ID)
            .sysMenuId(UPDATED_SYS_MENU_ID);
        SysRoleMenuDTO sysRoleMenuDTO = sysRoleMenuMapper.toDto(updatedSysRoleMenu);

        restSysRoleMenuMockMvc.perform(put("/api/sys-role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleMenuDTO)))
            .andExpect(status().isOk());

        // Validate the SysRoleMenu in the database
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuRepository.findAll();
        assertThat(sysRoleMenuList).hasSize(databaseSizeBeforeUpdate);
        SysRoleMenu testSysRoleMenu = sysRoleMenuList.get(sysRoleMenuList.size() - 1);
        assertThat(testSysRoleMenu.getSysRoleId()).isEqualTo(UPDATED_SYS_ROLE_ID);
        assertThat(testSysRoleMenu.getSysMenuId()).isEqualTo(UPDATED_SYS_MENU_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSysRoleMenu() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleMenuRepository.findAll().size();

        // Create the SysRoleMenu
        SysRoleMenuDTO sysRoleMenuDTO = sysRoleMenuMapper.toDto(sysRoleMenu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRoleMenuMockMvc.perform(put("/api/sys-role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleMenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysRoleMenu in the database
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuRepository.findAll();
        assertThat(sysRoleMenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysRoleMenu() throws Exception {
        // Initialize the database
        sysRoleMenuRepository.saveAndFlush(sysRoleMenu);

        int databaseSizeBeforeDelete = sysRoleMenuRepository.findAll().size();

        // Delete the sysRoleMenu
        restSysRoleMenuMockMvc.perform(delete("/api/sys-role-menus/{id}", sysRoleMenu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuRepository.findAll();
        assertThat(sysRoleMenuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRoleMenu.class);
        SysRoleMenu sysRoleMenu1 = new SysRoleMenu();
        sysRoleMenu1.setId(1L);
        SysRoleMenu sysRoleMenu2 = new SysRoleMenu();
        sysRoleMenu2.setId(sysRoleMenu1.getId());
        assertThat(sysRoleMenu1).isEqualTo(sysRoleMenu2);
        sysRoleMenu2.setId(2L);
        assertThat(sysRoleMenu1).isNotEqualTo(sysRoleMenu2);
        sysRoleMenu1.setId(null);
        assertThat(sysRoleMenu1).isNotEqualTo(sysRoleMenu2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRoleMenuDTO.class);
        SysRoleMenuDTO sysRoleMenuDTO1 = new SysRoleMenuDTO();
        sysRoleMenuDTO1.setId(1L);
        SysRoleMenuDTO sysRoleMenuDTO2 = new SysRoleMenuDTO();
        assertThat(sysRoleMenuDTO1).isNotEqualTo(sysRoleMenuDTO2);
        sysRoleMenuDTO2.setId(sysRoleMenuDTO1.getId());
        assertThat(sysRoleMenuDTO1).isEqualTo(sysRoleMenuDTO2);
        sysRoleMenuDTO2.setId(2L);
        assertThat(sysRoleMenuDTO1).isNotEqualTo(sysRoleMenuDTO2);
        sysRoleMenuDTO1.setId(null);
        assertThat(sysRoleMenuDTO1).isNotEqualTo(sysRoleMenuDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysRoleMenuMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysRoleMenuMapper.fromId(null)).isNull();
    }
}
