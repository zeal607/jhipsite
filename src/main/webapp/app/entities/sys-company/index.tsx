import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysCompany from './sys-company';
import SysCompanyDetail from './sys-company-detail';
import SysCompanyUpdate from './sys-company-update';
import SysCompanyDeleteDialog from './sys-company-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysCompanyUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysCompanyUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysCompanyDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysCompany} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysCompanyDeleteDialog} />
  </>
);

export default Routes;
