import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysCompanyOffice from './sys-company-office';
import SysCompanyOfficeDetail from './sys-company-office-detail';
import SysCompanyOfficeUpdate from './sys-company-office-update';
import SysCompanyOfficeDeleteDialog from './sys-company-office-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysCompanyOfficeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysCompanyOfficeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysCompanyOfficeDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysCompanyOffice} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysCompanyOfficeDeleteDialog} />
  </>
);

export default Routes;
