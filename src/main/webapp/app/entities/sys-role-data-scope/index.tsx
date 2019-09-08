import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysRoleDataScope from './sys-role-data-scope';
import SysRoleDataScopeDetail from './sys-role-data-scope-detail';
import SysRoleDataScopeUpdate from './sys-role-data-scope-update';
import SysRoleDataScopeDeleteDialog from './sys-role-data-scope-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysRoleDataScopeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysRoleDataScopeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysRoleDataScopeDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysRoleDataScope} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysRoleDataScopeDeleteDialog} />
  </>
);

export default Routes;
