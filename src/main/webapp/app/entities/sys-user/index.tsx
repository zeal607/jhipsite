import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysUser from './sys-user';
import SysUserDetail from './sys-user-detail';
import SysUserUpdate from './sys-user-update';
import SysUserDeleteDialog from './sys-user-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysUserDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysUser} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysUserDeleteDialog} />
  </>
);

export default Routes;
