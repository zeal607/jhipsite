import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysRoleMenu from './sys-role-menu';
import SysRoleMenuDetail from './sys-role-menu-detail';
import SysRoleMenuUpdate from './sys-role-menu-update';
import SysRoleMenuDeleteDialog from './sys-role-menu-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysRoleMenuUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysRoleMenuUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysRoleMenuDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysRoleMenu} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysRoleMenuDeleteDialog} />
  </>
);

export default Routes;
