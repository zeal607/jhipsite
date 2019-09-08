import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './sys-employee-office.reducer';
import { ISysEmployeeOffice } from 'app/shared/model/sys-employee-office.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ISysEmployeeOfficeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ISysEmployeeOfficeState = IPaginationBaseState;

export class SysEmployeeOffice extends React.Component<ISysEmployeeOfficeProps, ISysEmployeeOfficeState> {
  state: ISysEmployeeOfficeState = {
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
    const { sysEmployeeOfficeList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="sys-employee-office-heading">
          <Translate contentKey="jhipsiteApp.sysEmployeeOffice.home.title">Sys Employee Offices</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsiteApp.sysEmployeeOffice.home.createLabel">Create a new Sys Employee Office</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {sysEmployeeOfficeList && sysEmployeeOfficeList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('sysEmployeeId')}>
                    <Translate contentKey="jhipsiteApp.sysEmployeeOffice.sysEmployeeId">Sys Employee Id</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('sysOfficeId')}>
                    <Translate contentKey="jhipsiteApp.sysEmployeeOffice.sysOfficeId">Sys Office Id</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('sysPostId')}>
                    <Translate contentKey="jhipsiteApp.sysEmployeeOffice.sysPostId">Sys Post Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {sysEmployeeOfficeList.map((sysEmployeeOffice, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${sysEmployeeOffice.id}`} color="link" size="sm">
                        {sysEmployeeOffice.id}
                      </Button>
                    </td>
                    <td>{sysEmployeeOffice.sysEmployeeId}</td>
                    <td>{sysEmployeeOffice.sysOfficeId}</td>
                    <td>{sysEmployeeOffice.sysPostId}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${sysEmployeeOffice.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysEmployeeOffice.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysEmployeeOffice.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhipsiteApp.sysEmployeeOffice.home.notFound">No Sys Employee Offices found</Translate>
            </div>
          )}
        </div>
        <div className={sysEmployeeOfficeList && sysEmployeeOfficeList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ sysEmployeeOffice }: IRootState) => ({
  sysEmployeeOfficeList: sysEmployeeOffice.entities,
  totalItems: sysEmployeeOffice.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysEmployeeOffice);
