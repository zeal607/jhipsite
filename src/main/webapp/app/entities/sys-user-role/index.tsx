import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysUserRole from './sys-user-role';
import SysUserRoleDetail from './sys-user-role-detail';
import SysUserRoleUpdate from './sys-user-role-update';
import SysUserRoleDeleteDialog from './sys-user-role-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysUserRoleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysUserRoleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysUserRoleDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysUserRole} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysUserRoleDeleteDialog} />
  </>
);

export default Routes;
