import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysRole from './sys-role';
import SysRoleDetail from './sys-role-detail';
import SysRoleUpdate from './sys-role-update';
import SysRoleDeleteDialog from './sys-role-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysRoleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysRoleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysRoleDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysRole} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysRoleDeleteDialog} />
  </>
);

export default Routes;
