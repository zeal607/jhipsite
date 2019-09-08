import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <MenuItem icon="asterisk" to="/entity/sys-user">
      <Translate contentKey="global.menu.entities.sysUser" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-role">
      <Translate contentKey="global.menu.entities.sysRole" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-menu">
      <Translate contentKey="global.menu.entities.sysMenu" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-user-role">
      <Translate contentKey="global.menu.entities.sysUserRole" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-role-menu">
      <Translate contentKey="global.menu.entities.sysRoleMenu" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-company">
      <Translate contentKey="global.menu.entities.sysCompany" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-office">
      <Translate contentKey="global.menu.entities.sysOffice" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-employee">
      <Translate contentKey="global.menu.entities.sysEmployee" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-employee-office">
      <Translate contentKey="global.menu.entities.sysEmployeeOffice" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-company-office">
      <Translate contentKey="global.menu.entities.sysCompanyOffice" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-post">
      <Translate contentKey="global.menu.entities.sysPost" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-employee-post">
      <Translate contentKey="global.menu.entities.sysEmployeePost" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-role-data-scope">
      <Translate contentKey="global.menu.entities.sysRoleDataScope" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/sys-user-data-scope">
      <Translate contentKey="global.menu.entities.sysUserDataScope" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
