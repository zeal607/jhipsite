import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysEmployee from './sys-employee';
import SysEmployeeDetail from './sys-employee-detail';
import SysEmployeeUpdate from './sys-employee-update';
import SysEmployeeDeleteDialog from './sys-employee-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysEmployeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysEmployeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysEmployeeDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysEmployee} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysEmployeeDeleteDialog} />
  </>
);

export default Routes;
