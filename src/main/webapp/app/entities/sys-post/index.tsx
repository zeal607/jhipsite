import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysPost from './sys-post';
import SysPostDetail from './sys-post-detail';
import SysPostUpdate from './sys-post-update';
import SysPostDeleteDialog from './sys-post-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysPostUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysPostUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysPostDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysPost} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SysPostDeleteDialog} />
  </>
);

export default Routes;
