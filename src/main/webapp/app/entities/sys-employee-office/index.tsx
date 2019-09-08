import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysEmployeeOffice from './sys-employee-office';
import SysEmployeeOfficeDetail from './sys-employee-office-detail';
import SysEmployeeOfficeUpdate from './sys-employee-office-update';
import SysEmployeeOfficeDeleteDialog from './sys-employee-office-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysEmployeeOfficeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysEmployeeOfficeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysEmployeeOfficeDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysEmployeeOffice} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysEmployeeOfficeDeleteDialog} />
  </>
);

export default Routes;
