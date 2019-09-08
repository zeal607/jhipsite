package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysMenu;
import com.ruowei.repository.SysMenuRepository;
import com.ruowei.service.SysMenuService;
import com.ruowei.service.dto.SysMenuDTO;
import com.ruowei.service.mapper.SysMenuMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysMenuCriteria;
import com.ruowei.service.SysMenuQueryService;

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

import com.ruowei.domain.enumeration.MenuType;
import com.ruowei.domain.enumeration.SysType;
import com.ruowei.domain.enumeration.MenuStatusType;
/**
 * Integration tests for the {@link SysMenuResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysMenuResourceIT {

    private static final String DEFAULT_MENU_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MENU_CODE = "BBBBBBBBBB";

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

    private static final String DEFAULT_MENU_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MENU_NAME = "BBBBBBBBBB";

    private static final MenuType DEFAULT_MENU_TYPE = MenuType.MENU;
    private static final MenuType UPDATED_MENU_TYPE = MenuType.PERMISSION;

    private static final String DEFAULT_MENU_HREF = "AAAAAAAAAA";
    private static final String UPDATED_MENU_HREF = "BBBBBBBBBB";

    private static final String DEFAULT_MENU_ICON = "AAAAAAAAAA";
    private static final String UPDATED_MENU_ICON = "BBBBBBBBBB";

    private static final String DEFAULT_MENU_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_MENU_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_PERMISSION = "AAAAAAAAAA";
    private static final String UPDATED_PERMISSION = "BBBBBBBBBB";

    private static final Integer DEFAULT_MENU_SORT = 1;
    private static final Integer UPDATED_MENU_SORT = 2;
    private static final Integer SMALLER_MENU_SORT = 1 - 1;

    private static final Boolean DEFAULT_IS_SHOW = false;
    private static final Boolean UPDATED_IS_SHOW = true;

    private static final SysType DEFAULT_SYS_CODE = SysType.WEB;
    private static final SysType UPDATED_SYS_CODE = SysType.MOBILE;

    private static final MenuStatusType DEFAULT_STATUS = MenuStatusType.NORMAL;
    private static final MenuStatusType UPDATED_STATUS = MenuStatusType.DELETE;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysMenuQueryService sysMenuQueryService;

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

    private MockMvc restSysMenuMockMvc;

    private SysMenu sysMenu;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysMenuResource sysMenuResource = new SysMenuResource(sysMenuService, sysMenuQueryService);
        this.restSysMenuMockMvc = MockMvcBuilders.standaloneSetup(sysMenuResource)
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
    public static SysMenu createEntity(EntityManager em) {
        SysMenu sysMenu = new SysMenu()
            .menuCode(DEFAULT_MENU_CODE)
            .parentCode(DEFAULT_PARENT_CODE)
            .parentCodes(DEFAULT_PARENT_CODES)
            .treeSort(DEFAULT_TREE_SORT)
            .treeSorts(DEFAULT_TREE_SORTS)
            .treeLeaf(DEFAULT_TREE_LEAF)
            .treeLevel(DEFAULT_TREE_LEVEL)
            .treeNames(DEFAULT_TREE_NAMES)
            .menuName(DEFAULT_MENU_NAME)
            .menuType(DEFAULT_MENU_TYPE)
            .menuHref(DEFAULT_MENU_HREF)
            .menuIcon(DEFAULT_MENU_ICON)
            .menuTitle(DEFAULT_MENU_TITLE)
            .permission(DEFAULT_PERMISSION)
            .menuSort(DEFAULT_MENU_SORT)
            .isShow(DEFAULT_IS_SHOW)
            .sysCode(DEFAULT_SYS_CODE)
            .status(DEFAULT_STATUS)
            .remarks(DEFAULT_REMARKS);
        return sysMenu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysMenu createUpdatedEntity(EntityManager em) {
        SysMenu sysMenu = new SysMenu()
            .menuCode(UPDATED_MENU_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .parentCodes(UPDATED_PARENT_CODES)
            .treeSort(UPDATED_TREE_SORT)
            .treeSorts(UPDATED_TREE_SORTS)
            .treeLeaf(UPDATED_TREE_LEAF)
            .treeLevel(UPDATED_TREE_LEVEL)
            .treeNames(UPDATED_TREE_NAMES)
            .menuName(UPDATED_MENU_NAME)
            .menuType(UPDATED_MENU_TYPE)
            .menuHref(UPDATED_MENU_HREF)
            .menuIcon(UPDATED_MENU_ICON)
            .menuTitle(UPDATED_MENU_TITLE)
            .permission(UPDATED_PERMISSION)
            .menuSort(UPDATED_MENU_SORT)
            .isShow(UPDATED_IS_SHOW)
            .sysCode(UPDATED_SYS_CODE)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        return sysMenu;
    }

    @BeforeEach
    public void initTest() {
        sysMenu = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysMenu() throws Exception {
        int databaseSizeBeforeCreate = sysMenuRepository.findAll().size();

        // Create the SysMenu
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);
        restSysMenuMockMvc.perform(post("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isCreated());

        // Validate the SysMenu in the database
        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeCreate + 1);
        SysMenu testSysMenu = sysMenuList.get(sysMenuList.size() - 1);
        assertThat(testSysMenu.getMenuCode()).isEqualTo(DEFAULT_MENU_CODE);
        assertThat(testSysMenu.getParentCode()).isEqualTo(DEFAULT_PARENT_CODE);
        assertThat(testSysMenu.getParentCodes()).isEqualTo(DEFAULT_PARENT_CODES);
        assertThat(testSysMenu.getTreeSort()).isEqualTo(DEFAULT_TREE_SORT);
        assertThat(testSysMenu.getTreeSorts()).isEqualTo(DEFAULT_TREE_SORTS);
        assertThat(testSysMenu.isTreeLeaf()).isEqualTo(DEFAULT_TREE_LEAF);
        assertThat(testSysMenu.getTreeLevel()).isEqualTo(DEFAULT_TREE_LEVEL);
        assertThat(testSysMenu.getTreeNames()).isEqualTo(DEFAULT_TREE_NAMES);
        assertThat(testSysMenu.getMenuName()).isEqualTo(DEFAULT_MENU_NAME);
        assertThat(testSysMenu.getMenuType()).isEqualTo(DEFAULT_MENU_TYPE);
        assertThat(testSysMenu.getMenuHref()).isEqualTo(DEFAULT_MENU_HREF);
        assertThat(testSysMenu.getMenuIcon()).isEqualTo(DEFAULT_MENU_ICON);
        assertThat(testSysMenu.getMenuTitle()).isEqualTo(DEFAULT_MENU_TITLE);
        assertThat(testSysMenu.getPermission()).isEqualTo(DEFAULT_PERMISSION);
        assertThat(testSysMenu.getMenuSort()).isEqualTo(DEFAULT_MENU_SORT);
        assertThat(testSysMenu.isIsShow()).isEqualTo(DEFAULT_IS_SHOW);
        assertThat(testSysMenu.getSysCode()).isEqualTo(DEFAULT_SYS_CODE);
        assertThat(testSysMenu.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysMenu.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createSysMenuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysMenuRepository.findAll().size();

        // Create the SysMenu with an existing ID
        sysMenu.setId(1L);
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysMenuMockMvc.perform(post("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysMenu in the database
        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMenuCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysMenuRepository.findAll().size();
        // set the field null
        sysMenu.setMenuCode(null);

        // Create the SysMenu, which fails.
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);

        restSysMenuMockMvc.perform(post("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTreeLeafIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysMenuRepository.findAll().size();
        // set the field null
        sysMenu.setTreeLeaf(null);

        // Create the SysMenu, which fails.
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);

        restSysMenuMockMvc.perform(post("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTreeLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysMenuRepository.findAll().size();
        // set the field null
        sysMenu.setTreeLevel(null);

        // Create the SysMenu, which fails.
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);

        restSysMenuMockMvc.perform(post("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTreeNamesIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysMenuRepository.findAll().size();
        // set the field null
        sysMenu.setTreeNames(null);

        // Create the SysMenu, which fails.
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);

        restSysMenuMockMvc.perform(post("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMenuTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysMenuRepository.findAll().size();
        // set the field null
        sysMenu.setMenuType(null);

        // Create the SysMenu, which fails.
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);

        restSysMenuMockMvc.perform(post("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsShowIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysMenuRepository.findAll().size();
        // set the field null
        sysMenu.setIsShow(null);

        // Create the SysMenu, which fails.
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);

        restSysMenuMockMvc.perform(post("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSysCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysMenuRepository.findAll().size();
        // set the field null
        sysMenu.setSysCode(null);

        // Create the SysMenu, which fails.
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);

        restSysMenuMockMvc.perform(post("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysMenuRepository.findAll().size();
        // set the field null
        sysMenu.setStatus(null);

        // Create the SysMenu, which fails.
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);

        restSysMenuMockMvc.perform(post("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isBadRequest());

        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysMenus() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList
        restSysMenuMockMvc.perform(get("/api/sys-menus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuCode").value(hasItem(DEFAULT_MENU_CODE.toString())))
            .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE.toString())))
            .andExpect(jsonPath("$.[*].parentCodes").value(hasItem(DEFAULT_PARENT_CODES.toString())))
            .andExpect(jsonPath("$.[*].treeSort").value(hasItem(DEFAULT_TREE_SORT)))
            .andExpect(jsonPath("$.[*].treeSorts").value(hasItem(DEFAULT_TREE_SORTS)))
            .andExpect(jsonPath("$.[*].treeLeaf").value(hasItem(DEFAULT_TREE_LEAF.booleanValue())))
            .andExpect(jsonPath("$.[*].treeLevel").value(hasItem(DEFAULT_TREE_LEVEL)))
            .andExpect(jsonPath("$.[*].treeNames").value(hasItem(DEFAULT_TREE_NAMES.toString())))
            .andExpect(jsonPath("$.[*].menuName").value(hasItem(DEFAULT_MENU_NAME.toString())))
            .andExpect(jsonPath("$.[*].menuType").value(hasItem(DEFAULT_MENU_TYPE.toString())))
            .andExpect(jsonPath("$.[*].menuHref").value(hasItem(DEFAULT_MENU_HREF.toString())))
            .andExpect(jsonPath("$.[*].menuIcon").value(hasItem(DEFAULT_MENU_ICON.toString())))
            .andExpect(jsonPath("$.[*].menuTitle").value(hasItem(DEFAULT_MENU_TITLE.toString())))
            .andExpect(jsonPath("$.[*].permission").value(hasItem(DEFAULT_PERMISSION.toString())))
            .andExpect(jsonPath("$.[*].menuSort").value(hasItem(DEFAULT_MENU_SORT)))
            .andExpect(jsonPath("$.[*].isShow").value(hasItem(DEFAULT_IS_SHOW.booleanValue())))
            .andExpect(jsonPath("$.[*].sysCode").value(hasItem(DEFAULT_SYS_CODE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }
    
    @Test
    @Transactional
    public void getSysMenu() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get the sysMenu
        restSysMenuMockMvc.perform(get("/api/sys-menus/{id}", sysMenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysMenu.getId().intValue()))
            .andExpect(jsonPath("$.menuCode").value(DEFAULT_MENU_CODE.toString()))
            .andExpect(jsonPath("$.parentCode").value(DEFAULT_PARENT_CODE.toString()))
            .andExpect(jsonPath("$.parentCodes").value(DEFAULT_PARENT_CODES.toString()))
            .andExpect(jsonPath("$.treeSort").value(DEFAULT_TREE_SORT))
            .andExpect(jsonPath("$.treeSorts").value(DEFAULT_TREE_SORTS))
            .andExpect(jsonPath("$.treeLeaf").value(DEFAULT_TREE_LEAF.booleanValue()))
            .andExpect(jsonPath("$.treeLevel").value(DEFAULT_TREE_LEVEL))
            .andExpect(jsonPath("$.treeNames").value(DEFAULT_TREE_NAMES.toString()))
            .andExpect(jsonPath("$.menuName").value(DEFAULT_MENU_NAME.toString()))
            .andExpect(jsonPath("$.menuType").value(DEFAULT_MENU_TYPE.toString()))
            .andExpect(jsonPath("$.menuHref").value(DEFAULT_MENU_HREF.toString()))
            .andExpect(jsonPath("$.menuIcon").value(DEFAULT_MENU_ICON.toString()))
            .andExpect(jsonPath("$.menuTitle").value(DEFAULT_MENU_TITLE.toString()))
            .andExpect(jsonPath("$.permission").value(DEFAULT_PERMISSION.toString()))
            .andExpect(jsonPath("$.menuSort").value(DEFAULT_MENU_SORT))
            .andExpect(jsonPath("$.isShow").value(DEFAULT_IS_SHOW.booleanValue()))
            .andExpect(jsonPath("$.sysCode").value(DEFAULT_SYS_CODE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuCode equals to DEFAULT_MENU_CODE
        defaultSysMenuShouldBeFound("menuCode.equals=" + DEFAULT_MENU_CODE);

        // Get all the sysMenuList where menuCode equals to UPDATED_MENU_CODE
        defaultSysMenuShouldNotBeFound("menuCode.equals=" + UPDATED_MENU_CODE);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuCode in DEFAULT_MENU_CODE or UPDATED_MENU_CODE
        defaultSysMenuShouldBeFound("menuCode.in=" + DEFAULT_MENU_CODE + "," + UPDATED_MENU_CODE);

        // Get all the sysMenuList where menuCode equals to UPDATED_MENU_CODE
        defaultSysMenuShouldNotBeFound("menuCode.in=" + UPDATED_MENU_CODE);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuCode is not null
        defaultSysMenuShouldBeFound("menuCode.specified=true");

        // Get all the sysMenuList where menuCode is null
        defaultSysMenuShouldNotBeFound("menuCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByParentCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where parentCode equals to DEFAULT_PARENT_CODE
        defaultSysMenuShouldBeFound("parentCode.equals=" + DEFAULT_PARENT_CODE);

        // Get all the sysMenuList where parentCode equals to UPDATED_PARENT_CODE
        defaultSysMenuShouldNotBeFound("parentCode.equals=" + UPDATED_PARENT_CODE);
    }

    @Test
    @Transactional
    public void getAllSysMenusByParentCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where parentCode in DEFAULT_PARENT_CODE or UPDATED_PARENT_CODE
        defaultSysMenuShouldBeFound("parentCode.in=" + DEFAULT_PARENT_CODE + "," + UPDATED_PARENT_CODE);

        // Get all the sysMenuList where parentCode equals to UPDATED_PARENT_CODE
        defaultSysMenuShouldNotBeFound("parentCode.in=" + UPDATED_PARENT_CODE);
    }

    @Test
    @Transactional
    public void getAllSysMenusByParentCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where parentCode is not null
        defaultSysMenuShouldBeFound("parentCode.specified=true");

        // Get all the sysMenuList where parentCode is null
        defaultSysMenuShouldNotBeFound("parentCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByParentCodesIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where parentCodes equals to DEFAULT_PARENT_CODES
        defaultSysMenuShouldBeFound("parentCodes.equals=" + DEFAULT_PARENT_CODES);

        // Get all the sysMenuList where parentCodes equals to UPDATED_PARENT_CODES
        defaultSysMenuShouldNotBeFound("parentCodes.equals=" + UPDATED_PARENT_CODES);
    }

    @Test
    @Transactional
    public void getAllSysMenusByParentCodesIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where parentCodes in DEFAULT_PARENT_CODES or UPDATED_PARENT_CODES
        defaultSysMenuShouldBeFound("parentCodes.in=" + DEFAULT_PARENT_CODES + "," + UPDATED_PARENT_CODES);

        // Get all the sysMenuList where parentCodes equals to UPDATED_PARENT_CODES
        defaultSysMenuShouldNotBeFound("parentCodes.in=" + UPDATED_PARENT_CODES);
    }

    @Test
    @Transactional
    public void getAllSysMenusByParentCodesIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where parentCodes is not null
        defaultSysMenuShouldBeFound("parentCodes.specified=true");

        // Get all the sysMenuList where parentCodes is null
        defaultSysMenuShouldNotBeFound("parentCodes.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSort equals to DEFAULT_TREE_SORT
        defaultSysMenuShouldBeFound("treeSort.equals=" + DEFAULT_TREE_SORT);

        // Get all the sysMenuList where treeSort equals to UPDATED_TREE_SORT
        defaultSysMenuShouldNotBeFound("treeSort.equals=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSort in DEFAULT_TREE_SORT or UPDATED_TREE_SORT
        defaultSysMenuShouldBeFound("treeSort.in=" + DEFAULT_TREE_SORT + "," + UPDATED_TREE_SORT);

        // Get all the sysMenuList where treeSort equals to UPDATED_TREE_SORT
        defaultSysMenuShouldNotBeFound("treeSort.in=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSort is not null
        defaultSysMenuShouldBeFound("treeSort.specified=true");

        // Get all the sysMenuList where treeSort is null
        defaultSysMenuShouldNotBeFound("treeSort.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSort is greater than or equal to DEFAULT_TREE_SORT
        defaultSysMenuShouldBeFound("treeSort.greaterThanOrEqual=" + DEFAULT_TREE_SORT);

        // Get all the sysMenuList where treeSort is greater than or equal to UPDATED_TREE_SORT
        defaultSysMenuShouldNotBeFound("treeSort.greaterThanOrEqual=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSort is less than or equal to DEFAULT_TREE_SORT
        defaultSysMenuShouldBeFound("treeSort.lessThanOrEqual=" + DEFAULT_TREE_SORT);

        // Get all the sysMenuList where treeSort is less than or equal to SMALLER_TREE_SORT
        defaultSysMenuShouldNotBeFound("treeSort.lessThanOrEqual=" + SMALLER_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortIsLessThanSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSort is less than DEFAULT_TREE_SORT
        defaultSysMenuShouldNotBeFound("treeSort.lessThan=" + DEFAULT_TREE_SORT);

        // Get all the sysMenuList where treeSort is less than UPDATED_TREE_SORT
        defaultSysMenuShouldBeFound("treeSort.lessThan=" + UPDATED_TREE_SORT);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSort is greater than DEFAULT_TREE_SORT
        defaultSysMenuShouldNotBeFound("treeSort.greaterThan=" + DEFAULT_TREE_SORT);

        // Get all the sysMenuList where treeSort is greater than SMALLER_TREE_SORT
        defaultSysMenuShouldBeFound("treeSort.greaterThan=" + SMALLER_TREE_SORT);
    }


    @Test
    @Transactional
    public void getAllSysMenusByTreeSortsIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSorts equals to DEFAULT_TREE_SORTS
        defaultSysMenuShouldBeFound("treeSorts.equals=" + DEFAULT_TREE_SORTS);

        // Get all the sysMenuList where treeSorts equals to UPDATED_TREE_SORTS
        defaultSysMenuShouldNotBeFound("treeSorts.equals=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortsIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSorts in DEFAULT_TREE_SORTS or UPDATED_TREE_SORTS
        defaultSysMenuShouldBeFound("treeSorts.in=" + DEFAULT_TREE_SORTS + "," + UPDATED_TREE_SORTS);

        // Get all the sysMenuList where treeSorts equals to UPDATED_TREE_SORTS
        defaultSysMenuShouldNotBeFound("treeSorts.in=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortsIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSorts is not null
        defaultSysMenuShouldBeFound("treeSorts.specified=true");

        // Get all the sysMenuList where treeSorts is null
        defaultSysMenuShouldNotBeFound("treeSorts.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSorts is greater than or equal to DEFAULT_TREE_SORTS
        defaultSysMenuShouldBeFound("treeSorts.greaterThanOrEqual=" + DEFAULT_TREE_SORTS);

        // Get all the sysMenuList where treeSorts is greater than or equal to UPDATED_TREE_SORTS
        defaultSysMenuShouldNotBeFound("treeSorts.greaterThanOrEqual=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSorts is less than or equal to DEFAULT_TREE_SORTS
        defaultSysMenuShouldBeFound("treeSorts.lessThanOrEqual=" + DEFAULT_TREE_SORTS);

        // Get all the sysMenuList where treeSorts is less than or equal to SMALLER_TREE_SORTS
        defaultSysMenuShouldNotBeFound("treeSorts.lessThanOrEqual=" + SMALLER_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortsIsLessThanSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSorts is less than DEFAULT_TREE_SORTS
        defaultSysMenuShouldNotBeFound("treeSorts.lessThan=" + DEFAULT_TREE_SORTS);

        // Get all the sysMenuList where treeSorts is less than UPDATED_TREE_SORTS
        defaultSysMenuShouldBeFound("treeSorts.lessThan=" + UPDATED_TREE_SORTS);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeSortsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeSorts is greater than DEFAULT_TREE_SORTS
        defaultSysMenuShouldNotBeFound("treeSorts.greaterThan=" + DEFAULT_TREE_SORTS);

        // Get all the sysMenuList where treeSorts is greater than SMALLER_TREE_SORTS
        defaultSysMenuShouldBeFound("treeSorts.greaterThan=" + SMALLER_TREE_SORTS);
    }


    @Test
    @Transactional
    public void getAllSysMenusByTreeLeafIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeLeaf equals to DEFAULT_TREE_LEAF
        defaultSysMenuShouldBeFound("treeLeaf.equals=" + DEFAULT_TREE_LEAF);

        // Get all the sysMenuList where treeLeaf equals to UPDATED_TREE_LEAF
        defaultSysMenuShouldNotBeFound("treeLeaf.equals=" + UPDATED_TREE_LEAF);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeLeafIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeLeaf in DEFAULT_TREE_LEAF or UPDATED_TREE_LEAF
        defaultSysMenuShouldBeFound("treeLeaf.in=" + DEFAULT_TREE_LEAF + "," + UPDATED_TREE_LEAF);

        // Get all the sysMenuList where treeLeaf equals to UPDATED_TREE_LEAF
        defaultSysMenuShouldNotBeFound("treeLeaf.in=" + UPDATED_TREE_LEAF);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeLeafIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeLeaf is not null
        defaultSysMenuShouldBeFound("treeLeaf.specified=true");

        // Get all the sysMenuList where treeLeaf is null
        defaultSysMenuShouldNotBeFound("treeLeaf.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeLevel equals to DEFAULT_TREE_LEVEL
        defaultSysMenuShouldBeFound("treeLevel.equals=" + DEFAULT_TREE_LEVEL);

        // Get all the sysMenuList where treeLevel equals to UPDATED_TREE_LEVEL
        defaultSysMenuShouldNotBeFound("treeLevel.equals=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeLevelIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeLevel in DEFAULT_TREE_LEVEL or UPDATED_TREE_LEVEL
        defaultSysMenuShouldBeFound("treeLevel.in=" + DEFAULT_TREE_LEVEL + "," + UPDATED_TREE_LEVEL);

        // Get all the sysMenuList where treeLevel equals to UPDATED_TREE_LEVEL
        defaultSysMenuShouldNotBeFound("treeLevel.in=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeLevel is not null
        defaultSysMenuShouldBeFound("treeLevel.specified=true");

        // Get all the sysMenuList where treeLevel is null
        defaultSysMenuShouldNotBeFound("treeLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeLevel is greater than or equal to DEFAULT_TREE_LEVEL
        defaultSysMenuShouldBeFound("treeLevel.greaterThanOrEqual=" + DEFAULT_TREE_LEVEL);

        // Get all the sysMenuList where treeLevel is greater than or equal to UPDATED_TREE_LEVEL
        defaultSysMenuShouldNotBeFound("treeLevel.greaterThanOrEqual=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeLevelIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeLevel is less than or equal to DEFAULT_TREE_LEVEL
        defaultSysMenuShouldBeFound("treeLevel.lessThanOrEqual=" + DEFAULT_TREE_LEVEL);

        // Get all the sysMenuList where treeLevel is less than or equal to SMALLER_TREE_LEVEL
        defaultSysMenuShouldNotBeFound("treeLevel.lessThanOrEqual=" + SMALLER_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeLevel is less than DEFAULT_TREE_LEVEL
        defaultSysMenuShouldNotBeFound("treeLevel.lessThan=" + DEFAULT_TREE_LEVEL);

        // Get all the sysMenuList where treeLevel is less than UPDATED_TREE_LEVEL
        defaultSysMenuShouldBeFound("treeLevel.lessThan=" + UPDATED_TREE_LEVEL);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeLevelIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeLevel is greater than DEFAULT_TREE_LEVEL
        defaultSysMenuShouldNotBeFound("treeLevel.greaterThan=" + DEFAULT_TREE_LEVEL);

        // Get all the sysMenuList where treeLevel is greater than SMALLER_TREE_LEVEL
        defaultSysMenuShouldBeFound("treeLevel.greaterThan=" + SMALLER_TREE_LEVEL);
    }


    @Test
    @Transactional
    public void getAllSysMenusByTreeNamesIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeNames equals to DEFAULT_TREE_NAMES
        defaultSysMenuShouldBeFound("treeNames.equals=" + DEFAULT_TREE_NAMES);

        // Get all the sysMenuList where treeNames equals to UPDATED_TREE_NAMES
        defaultSysMenuShouldNotBeFound("treeNames.equals=" + UPDATED_TREE_NAMES);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeNamesIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeNames in DEFAULT_TREE_NAMES or UPDATED_TREE_NAMES
        defaultSysMenuShouldBeFound("treeNames.in=" + DEFAULT_TREE_NAMES + "," + UPDATED_TREE_NAMES);

        // Get all the sysMenuList where treeNames equals to UPDATED_TREE_NAMES
        defaultSysMenuShouldNotBeFound("treeNames.in=" + UPDATED_TREE_NAMES);
    }

    @Test
    @Transactional
    public void getAllSysMenusByTreeNamesIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where treeNames is not null
        defaultSysMenuShouldBeFound("treeNames.specified=true");

        // Get all the sysMenuList where treeNames is null
        defaultSysMenuShouldNotBeFound("treeNames.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuName equals to DEFAULT_MENU_NAME
        defaultSysMenuShouldBeFound("menuName.equals=" + DEFAULT_MENU_NAME);

        // Get all the sysMenuList where menuName equals to UPDATED_MENU_NAME
        defaultSysMenuShouldNotBeFound("menuName.equals=" + UPDATED_MENU_NAME);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuName in DEFAULT_MENU_NAME or UPDATED_MENU_NAME
        defaultSysMenuShouldBeFound("menuName.in=" + DEFAULT_MENU_NAME + "," + UPDATED_MENU_NAME);

        // Get all the sysMenuList where menuName equals to UPDATED_MENU_NAME
        defaultSysMenuShouldNotBeFound("menuName.in=" + UPDATED_MENU_NAME);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuName is not null
        defaultSysMenuShouldBeFound("menuName.specified=true");

        // Get all the sysMenuList where menuName is null
        defaultSysMenuShouldNotBeFound("menuName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuType equals to DEFAULT_MENU_TYPE
        defaultSysMenuShouldBeFound("menuType.equals=" + DEFAULT_MENU_TYPE);

        // Get all the sysMenuList where menuType equals to UPDATED_MENU_TYPE
        defaultSysMenuShouldNotBeFound("menuType.equals=" + UPDATED_MENU_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuTypeIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuType in DEFAULT_MENU_TYPE or UPDATED_MENU_TYPE
        defaultSysMenuShouldBeFound("menuType.in=" + DEFAULT_MENU_TYPE + "," + UPDATED_MENU_TYPE);

        // Get all the sysMenuList where menuType equals to UPDATED_MENU_TYPE
        defaultSysMenuShouldNotBeFound("menuType.in=" + UPDATED_MENU_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuType is not null
        defaultSysMenuShouldBeFound("menuType.specified=true");

        // Get all the sysMenuList where menuType is null
        defaultSysMenuShouldNotBeFound("menuType.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuHrefIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuHref equals to DEFAULT_MENU_HREF
        defaultSysMenuShouldBeFound("menuHref.equals=" + DEFAULT_MENU_HREF);

        // Get all the sysMenuList where menuHref equals to UPDATED_MENU_HREF
        defaultSysMenuShouldNotBeFound("menuHref.equals=" + UPDATED_MENU_HREF);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuHrefIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuHref in DEFAULT_MENU_HREF or UPDATED_MENU_HREF
        defaultSysMenuShouldBeFound("menuHref.in=" + DEFAULT_MENU_HREF + "," + UPDATED_MENU_HREF);

        // Get all the sysMenuList where menuHref equals to UPDATED_MENU_HREF
        defaultSysMenuShouldNotBeFound("menuHref.in=" + UPDATED_MENU_HREF);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuHrefIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuHref is not null
        defaultSysMenuShouldBeFound("menuHref.specified=true");

        // Get all the sysMenuList where menuHref is null
        defaultSysMenuShouldNotBeFound("menuHref.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuIconIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuIcon equals to DEFAULT_MENU_ICON
        defaultSysMenuShouldBeFound("menuIcon.equals=" + DEFAULT_MENU_ICON);

        // Get all the sysMenuList where menuIcon equals to UPDATED_MENU_ICON
        defaultSysMenuShouldNotBeFound("menuIcon.equals=" + UPDATED_MENU_ICON);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuIconIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuIcon in DEFAULT_MENU_ICON or UPDATED_MENU_ICON
        defaultSysMenuShouldBeFound("menuIcon.in=" + DEFAULT_MENU_ICON + "," + UPDATED_MENU_ICON);

        // Get all the sysMenuList where menuIcon equals to UPDATED_MENU_ICON
        defaultSysMenuShouldNotBeFound("menuIcon.in=" + UPDATED_MENU_ICON);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuIconIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuIcon is not null
        defaultSysMenuShouldBeFound("menuIcon.specified=true");

        // Get all the sysMenuList where menuIcon is null
        defaultSysMenuShouldNotBeFound("menuIcon.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuTitle equals to DEFAULT_MENU_TITLE
        defaultSysMenuShouldBeFound("menuTitle.equals=" + DEFAULT_MENU_TITLE);

        // Get all the sysMenuList where menuTitle equals to UPDATED_MENU_TITLE
        defaultSysMenuShouldNotBeFound("menuTitle.equals=" + UPDATED_MENU_TITLE);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuTitleIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuTitle in DEFAULT_MENU_TITLE or UPDATED_MENU_TITLE
        defaultSysMenuShouldBeFound("menuTitle.in=" + DEFAULT_MENU_TITLE + "," + UPDATED_MENU_TITLE);

        // Get all the sysMenuList where menuTitle equals to UPDATED_MENU_TITLE
        defaultSysMenuShouldNotBeFound("menuTitle.in=" + UPDATED_MENU_TITLE);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuTitle is not null
        defaultSysMenuShouldBeFound("menuTitle.specified=true");

        // Get all the sysMenuList where menuTitle is null
        defaultSysMenuShouldNotBeFound("menuTitle.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByPermissionIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where permission equals to DEFAULT_PERMISSION
        defaultSysMenuShouldBeFound("permission.equals=" + DEFAULT_PERMISSION);

        // Get all the sysMenuList where permission equals to UPDATED_PERMISSION
        defaultSysMenuShouldNotBeFound("permission.equals=" + UPDATED_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllSysMenusByPermissionIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where permission in DEFAULT_PERMISSION or UPDATED_PERMISSION
        defaultSysMenuShouldBeFound("permission.in=" + DEFAULT_PERMISSION + "," + UPDATED_PERMISSION);

        // Get all the sysMenuList where permission equals to UPDATED_PERMISSION
        defaultSysMenuShouldNotBeFound("permission.in=" + UPDATED_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllSysMenusByPermissionIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where permission is not null
        defaultSysMenuShouldBeFound("permission.specified=true");

        // Get all the sysMenuList where permission is null
        defaultSysMenuShouldNotBeFound("permission.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuSortIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuSort equals to DEFAULT_MENU_SORT
        defaultSysMenuShouldBeFound("menuSort.equals=" + DEFAULT_MENU_SORT);

        // Get all the sysMenuList where menuSort equals to UPDATED_MENU_SORT
        defaultSysMenuShouldNotBeFound("menuSort.equals=" + UPDATED_MENU_SORT);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuSortIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuSort in DEFAULT_MENU_SORT or UPDATED_MENU_SORT
        defaultSysMenuShouldBeFound("menuSort.in=" + DEFAULT_MENU_SORT + "," + UPDATED_MENU_SORT);

        // Get all the sysMenuList where menuSort equals to UPDATED_MENU_SORT
        defaultSysMenuShouldNotBeFound("menuSort.in=" + UPDATED_MENU_SORT);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuSortIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuSort is not null
        defaultSysMenuShouldBeFound("menuSort.specified=true");

        // Get all the sysMenuList where menuSort is null
        defaultSysMenuShouldNotBeFound("menuSort.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuSortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuSort is greater than or equal to DEFAULT_MENU_SORT
        defaultSysMenuShouldBeFound("menuSort.greaterThanOrEqual=" + DEFAULT_MENU_SORT);

        // Get all the sysMenuList where menuSort is greater than or equal to UPDATED_MENU_SORT
        defaultSysMenuShouldNotBeFound("menuSort.greaterThanOrEqual=" + UPDATED_MENU_SORT);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuSortIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuSort is less than or equal to DEFAULT_MENU_SORT
        defaultSysMenuShouldBeFound("menuSort.lessThanOrEqual=" + DEFAULT_MENU_SORT);

        // Get all the sysMenuList where menuSort is less than or equal to SMALLER_MENU_SORT
        defaultSysMenuShouldNotBeFound("menuSort.lessThanOrEqual=" + SMALLER_MENU_SORT);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuSortIsLessThanSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuSort is less than DEFAULT_MENU_SORT
        defaultSysMenuShouldNotBeFound("menuSort.lessThan=" + DEFAULT_MENU_SORT);

        // Get all the sysMenuList where menuSort is less than UPDATED_MENU_SORT
        defaultSysMenuShouldBeFound("menuSort.lessThan=" + UPDATED_MENU_SORT);
    }

    @Test
    @Transactional
    public void getAllSysMenusByMenuSortIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where menuSort is greater than DEFAULT_MENU_SORT
        defaultSysMenuShouldNotBeFound("menuSort.greaterThan=" + DEFAULT_MENU_SORT);

        // Get all the sysMenuList where menuSort is greater than SMALLER_MENU_SORT
        defaultSysMenuShouldBeFound("menuSort.greaterThan=" + SMALLER_MENU_SORT);
    }


    @Test
    @Transactional
    public void getAllSysMenusByIsShowIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where isShow equals to DEFAULT_IS_SHOW
        defaultSysMenuShouldBeFound("isShow.equals=" + DEFAULT_IS_SHOW);

        // Get all the sysMenuList where isShow equals to UPDATED_IS_SHOW
        defaultSysMenuShouldNotBeFound("isShow.equals=" + UPDATED_IS_SHOW);
    }

    @Test
    @Transactional
    public void getAllSysMenusByIsShowIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where isShow in DEFAULT_IS_SHOW or UPDATED_IS_SHOW
        defaultSysMenuShouldBeFound("isShow.in=" + DEFAULT_IS_SHOW + "," + UPDATED_IS_SHOW);

        // Get all the sysMenuList where isShow equals to UPDATED_IS_SHOW
        defaultSysMenuShouldNotBeFound("isShow.in=" + UPDATED_IS_SHOW);
    }

    @Test
    @Transactional
    public void getAllSysMenusByIsShowIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where isShow is not null
        defaultSysMenuShouldBeFound("isShow.specified=true");

        // Get all the sysMenuList where isShow is null
        defaultSysMenuShouldNotBeFound("isShow.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusBySysCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where sysCode equals to DEFAULT_SYS_CODE
        defaultSysMenuShouldBeFound("sysCode.equals=" + DEFAULT_SYS_CODE);

        // Get all the sysMenuList where sysCode equals to UPDATED_SYS_CODE
        defaultSysMenuShouldNotBeFound("sysCode.equals=" + UPDATED_SYS_CODE);
    }

    @Test
    @Transactional
    public void getAllSysMenusBySysCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where sysCode in DEFAULT_SYS_CODE or UPDATED_SYS_CODE
        defaultSysMenuShouldBeFound("sysCode.in=" + DEFAULT_SYS_CODE + "," + UPDATED_SYS_CODE);

        // Get all the sysMenuList where sysCode equals to UPDATED_SYS_CODE
        defaultSysMenuShouldNotBeFound("sysCode.in=" + UPDATED_SYS_CODE);
    }

    @Test
    @Transactional
    public void getAllSysMenusBySysCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where sysCode is not null
        defaultSysMenuShouldBeFound("sysCode.specified=true");

        // Get all the sysMenuList where sysCode is null
        defaultSysMenuShouldNotBeFound("sysCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where status equals to DEFAULT_STATUS
        defaultSysMenuShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the sysMenuList where status equals to UPDATED_STATUS
        defaultSysMenuShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysMenusByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultSysMenuShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the sysMenuList where status equals to UPDATED_STATUS
        defaultSysMenuShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysMenusByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where status is not null
        defaultSysMenuShouldBeFound("status.specified=true");

        // Get all the sysMenuList where status is null
        defaultSysMenuShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysMenusByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where remarks equals to DEFAULT_REMARKS
        defaultSysMenuShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the sysMenuList where remarks equals to UPDATED_REMARKS
        defaultSysMenuShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysMenusByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultSysMenuShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the sysMenuList where remarks equals to UPDATED_REMARKS
        defaultSysMenuShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysMenusByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        // Get all the sysMenuList where remarks is not null
        defaultSysMenuShouldBeFound("remarks.specified=true");

        // Get all the sysMenuList where remarks is null
        defaultSysMenuShouldNotBeFound("remarks.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysMenuShouldBeFound(String filter) throws Exception {
        restSysMenuMockMvc.perform(get("/api/sys-menus?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuCode").value(hasItem(DEFAULT_MENU_CODE)))
            .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE)))
            .andExpect(jsonPath("$.[*].parentCodes").value(hasItem(DEFAULT_PARENT_CODES)))
            .andExpect(jsonPath("$.[*].treeSort").value(hasItem(DEFAULT_TREE_SORT)))
            .andExpect(jsonPath("$.[*].treeSorts").value(hasItem(DEFAULT_TREE_SORTS)))
            .andExpect(jsonPath("$.[*].treeLeaf").value(hasItem(DEFAULT_TREE_LEAF.booleanValue())))
            .andExpect(jsonPath("$.[*].treeLevel").value(hasItem(DEFAULT_TREE_LEVEL)))
            .andExpect(jsonPath("$.[*].treeNames").value(hasItem(DEFAULT_TREE_NAMES)))
            .andExpect(jsonPath("$.[*].menuName").value(hasItem(DEFAULT_MENU_NAME)))
            .andExpect(jsonPath("$.[*].menuType").value(hasItem(DEFAULT_MENU_TYPE.toString())))
            .andExpect(jsonPath("$.[*].menuHref").value(hasItem(DEFAULT_MENU_HREF)))
            .andExpect(jsonPath("$.[*].menuIcon").value(hasItem(DEFAULT_MENU_ICON)))
            .andExpect(jsonPath("$.[*].menuTitle").value(hasItem(DEFAULT_MENU_TITLE)))
            .andExpect(jsonPath("$.[*].permission").value(hasItem(DEFAULT_PERMISSION)))
            .andExpect(jsonPath("$.[*].menuSort").value(hasItem(DEFAULT_MENU_SORT)))
            .andExpect(jsonPath("$.[*].isShow").value(hasItem(DEFAULT_IS_SHOW.booleanValue())))
            .andExpect(jsonPath("$.[*].sysCode").value(hasItem(DEFAULT_SYS_CODE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));

        // Check, that the count call also returns 1
        restSysMenuMockMvc.perform(get("/api/sys-menus/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysMenuShouldNotBeFound(String filter) throws Exception {
        restSysMenuMockMvc.perform(get("/api/sys-menus?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysMenuMockMvc.perform(get("/api/sys-menus/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysMenu() throws Exception {
        // Get the sysMenu
        restSysMenuMockMvc.perform(get("/api/sys-menus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysMenu() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        int databaseSizeBeforeUpdate = sysMenuRepository.findAll().size();

        // Update the sysMenu
        SysMenu updatedSysMenu = sysMenuRepository.findById(sysMenu.getId()).get();
        // Disconnect from session so that the updates on updatedSysMenu are not directly saved in db
        em.detach(updatedSysMenu);
        updatedSysMenu
            .menuCode(UPDATED_MENU_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .parentCodes(UPDATED_PARENT_CODES)
            .treeSort(UPDATED_TREE_SORT)
            .treeSorts(UPDATED_TREE_SORTS)
            .treeLeaf(UPDATED_TREE_LEAF)
            .treeLevel(UPDATED_TREE_LEVEL)
            .treeNames(UPDATED_TREE_NAMES)
            .menuName(UPDATED_MENU_NAME)
            .menuType(UPDATED_MENU_TYPE)
            .menuHref(UPDATED_MENU_HREF)
            .menuIcon(UPDATED_MENU_ICON)
            .menuTitle(UPDATED_MENU_TITLE)
            .permission(UPDATED_PERMISSION)
            .menuSort(UPDATED_MENU_SORT)
            .isShow(UPDATED_IS_SHOW)
            .sysCode(UPDATED_SYS_CODE)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(updatedSysMenu);

        restSysMenuMockMvc.perform(put("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isOk());

        // Validate the SysMenu in the database
        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeUpdate);
        SysMenu testSysMenu = sysMenuList.get(sysMenuList.size() - 1);
        assertThat(testSysMenu.getMenuCode()).isEqualTo(UPDATED_MENU_CODE);
        assertThat(testSysMenu.getParentCode()).isEqualTo(UPDATED_PARENT_CODE);
        assertThat(testSysMenu.getParentCodes()).isEqualTo(UPDATED_PARENT_CODES);
        assertThat(testSysMenu.getTreeSort()).isEqualTo(UPDATED_TREE_SORT);
        assertThat(testSysMenu.getTreeSorts()).isEqualTo(UPDATED_TREE_SORTS);
        assertThat(testSysMenu.isTreeLeaf()).isEqualTo(UPDATED_TREE_LEAF);
        assertThat(testSysMenu.getTreeLevel()).isEqualTo(UPDATED_TREE_LEVEL);
        assertThat(testSysMenu.getTreeNames()).isEqualTo(UPDATED_TREE_NAMES);
        assertThat(testSysMenu.getMenuName()).isEqualTo(UPDATED_MENU_NAME);
        assertThat(testSysMenu.getMenuType()).isEqualTo(UPDATED_MENU_TYPE);
        assertThat(testSysMenu.getMenuHref()).isEqualTo(UPDATED_MENU_HREF);
        assertThat(testSysMenu.getMenuIcon()).isEqualTo(UPDATED_MENU_ICON);
        assertThat(testSysMenu.getMenuTitle()).isEqualTo(UPDATED_MENU_TITLE);
        assertThat(testSysMenu.getPermission()).isEqualTo(UPDATED_PERMISSION);
        assertThat(testSysMenu.getMenuSort()).isEqualTo(UPDATED_MENU_SORT);
        assertThat(testSysMenu.isIsShow()).isEqualTo(UPDATED_IS_SHOW);
        assertThat(testSysMenu.getSysCode()).isEqualTo(UPDATED_SYS_CODE);
        assertThat(testSysMenu.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysMenu.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingSysMenu() throws Exception {
        int databaseSizeBeforeUpdate = sysMenuRepository.findAll().size();

        // Create the SysMenu
        SysMenuDTO sysMenuDTO = sysMenuMapper.toDto(sysMenu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysMenuMockMvc.perform(put("/api/sys-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysMenu in the database
        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysMenu() throws Exception {
        // Initialize the database
        sysMenuRepository.saveAndFlush(sysMenu);

        int databaseSizeBeforeDelete = sysMenuRepository.findAll().size();

        // Delete the sysMenu
        restSysMenuMockMvc.perform(delete("/api/sys-menus/{id}", sysMenu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysMenu> sysMenuList = sysMenuRepository.findAll();
        assertThat(sysMenuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysMenu.class);
        SysMenu sysMenu1 = new SysMenu();
        sysMenu1.setId(1L);
        SysMenu sysMenu2 = new SysMenu();
        sysMenu2.setId(sysMenu1.getId());
        assertThat(sysMenu1).isEqualTo(sysMenu2);
        sysMenu2.setId(2L);
        assertThat(sysMenu1).isNotEqualTo(sysMenu2);
        sysMenu1.setId(null);
        assertThat(sysMenu1).isNotEqualTo(sysMenu2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysMenuDTO.class);
        SysMenuDTO sysMenuDTO1 = new SysMenuDTO();
        sysMenuDTO1.setId(1L);
        SysMenuDTO sysMenuDTO2 = new SysMenuDTO();
        assertThat(sysMenuDTO1).isNotEqualTo(sysMenuDTO2);
        sysMenuDTO2.setId(sysMenuDTO1.getId());
        assertThat(sysMenuDTO1).isEqualTo(sysMenuDTO2);
        sysMenuDTO2.setId(2L);
        assertThat(sysMenuDTO1).isNotEqualTo(sysMenuDTO2);
        sysMenuDTO1.setId(null);
        assertThat(sysMenuDTO1).isNotEqualTo(sysMenuDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysMenuMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysMenuMapper.fromId(null)).isNull();
    }
}
