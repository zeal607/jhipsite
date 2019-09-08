import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysUserDataScope from './sys-user-data-scope';
import SysUserDataScopeDetail from './sys-user-data-scope-detail';
import SysUserDataScopeUpdate from './sys-user-data-scope-update';
import SysUserDataScopeDeleteDialog from './sys-user-data-scope-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysUserDataScopeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysUserDataScopeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysUserDataScopeDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysUserDataScope} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysUserDataScopeDeleteDialog} />
  </>
);

export default Routes;
