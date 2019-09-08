import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysMenu from './sys-menu';
import SysMenuDetail from './sys-menu-detail';
import SysMenuUpdate from './sys-menu-update';
import SysMenuDeleteDialog from './sys-menu-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysMenuUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysMenuUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysMenuDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysMenu} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysMenuDeleteDialog} />
  </>
);

export default Routes;
