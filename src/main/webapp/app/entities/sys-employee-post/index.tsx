import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysEmployeePost from './sys-employee-post';
import SysEmployeePostDetail from './sys-employee-post-detail';
import SysEmployeePostUpdate from './sys-employee-post-update';
import SysEmployeePostDeleteDialog from './sys-employee-post-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysEmployeePostUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysEmployeePostUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysEmployeePostDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysEmployeePost} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysEmployeePostDeleteDialog} />
  </>
);

export default Routes;
