import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysOffice from './sys-office';
import SysOfficeDetail from './sys-office-detail';
import SysOfficeUpdate from './sys-office-update';
import SysOfficeDeleteDialog from './sys-office-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysOfficeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysOfficeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysOfficeDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysOffice} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysOfficeDeleteDialog} />
  </>
);

export default Routes;
