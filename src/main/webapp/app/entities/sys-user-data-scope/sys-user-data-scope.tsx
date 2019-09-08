import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './sys-user-data-scope.reducer';
import { ISysUserDataScope } from 'app/shared/model/sys-user-data-scope.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ISysUserDataScopeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ISysUserDataScopeState = IPaginationBaseState;

export class SysUserDataScope extends React.Component<ISysUserDataScopeProps, ISysUserDataScopeState> {
  state: ISysUserDataScopeState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { sysUserDataScopeList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="sys-user-data-scope-heading">
          <Translate contentKey="jhipsiteApp.sysUserDataScope.home.title">Sys User Data Scopes</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsiteApp.sysUserDataScope.home.createLabel">Create a new Sys User Data Scope</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {sysUserDataScopeList && sysUserDataScopeList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('sysUserId')}>
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.sysUserId">Sys User Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('ctrlType')}>
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.ctrlType">Ctrl Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('ctrlData')}>
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.ctrlData">Ctrl Data</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('ctrlPermi')}>
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.ctrlPermi">Ctrl Permi</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {sysUserDataScopeList.map((sysUserDataScope, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${sysUserDataScope.id}`} color="link" size="sm">
                        {sysUserDataScope.id}
                      </Button>
                    </td>
                    <td>{sysUserDataScope.sysUserId}</td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.ControlType.${sysUserDataScope.ctrlType}`} />
                    </td>
                    <td>{sysUserDataScope.ctrlData}</td>
                    <td>{sysUserDataScope.ctrlPermi}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${sysUserDataScope.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysUserDataScope.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysUserDataScope.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">
              <Translate contentKey="jhipsiteApp.sysUserDataScope.home.notFound">No Sys User Data Scopes found</Translate>
            </div>
          )}
        </div>
        <div className={sysUserDataScopeList && sysUserDataScopeList.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={this.state.activePage} total={totalItems} itemsPerPage={this.state.itemsPerPage} i18nEnabled />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={this.state.activePage}
              onSelect={this.handlePagination}
              maxButtons={5}
              itemsPerPage={this.state.itemsPerPage}
              totalItems={this.props.totalItems}
            />
          </Row>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ sysUserDataScope }: IRootState) => ({
  sysUserDataScopeList: sysUserDataScope.entities,
  totalItems: sysUserDataScope.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysUserDataScope);
