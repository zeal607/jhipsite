import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysUser from './sys-user';
import SysRole from './sys-role';
import SysMenu from './sys-menu';
import SysUserRole from './sys-user-role';
import SysRoleMenu from './sys-role-menu';
import SysCompany from './sys-company';
import SysOffice from './sys-office';
import SysEmployee from './sys-employee';
import SysEmployeeOffice from './sys-employee-office';
import SysCompanyOffice from './sys-company-office';
import SysPost from './sys-post';
import SysEmployeePost from './sys-employee-post';
import SysRoleDataScope from './sys-role-data-scope';
import SysUserDataScope from './sys-user-data-scope';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/sys-user`} component={SysUser} />
      <ErrorBoundaryRoute path={`${match.url}/sys-role`} component={SysRole} />
      <ErrorBoundaryRoute path={`${match.url}/sys-menu`} component={SysMenu} />
      <ErrorBoundaryRoute path={`${match.url}/sys-user-role`} component={SysUserRole} />
      <ErrorBoundaryRoute path={`${match.url}/sys-role-menu`} component={SysRoleMenu} />
      <ErrorBoundaryRoute path={`${match.url}/sys-company`} component={SysCompany} />
      <ErrorBoundaryRoute path={`${match.url}/sys-office`} component={SysOffice} />
      <ErrorBoundaryRoute path={`${match.url}/sys-employee`} component={SysEmployee} />
      <ErrorBoundaryRoute path={`${match.url}/sys-employee-office`} component={SysEmployeeOffice} />
      <ErrorBoundaryRoute path={`${match.url}/sys-company-office`} component={SysCompanyOffice} />
      <ErrorBoundaryRoute path={`${match.url}/sys-post`} component={SysPost} />
      <ErrorBoundaryRoute path={`${match.url}/sys-employee-post`} component={SysEmployeePost} />
      <ErrorBoundaryRoute path={`${match.url}/sys-role-data-scope`} component={SysRoleDataScope} />
      <ErrorBoundaryRoute path={`${match.url}/sys-user-data-scope`} component={SysUserDataScope} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
