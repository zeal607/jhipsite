import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './sys-employee.reducer';
import { ISysEmployee } from 'app/shared/model/sys-employee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ISysEmployeeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ISysEmployeeState = IPaginationBaseState;

export class SysEmployee extends React.Component<ISysEmployeeProps, ISysEmployeeState> {
  state: ISysEmployeeState = {
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
    const { sysEmployeeList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="sys-employee-heading">
          <Translate contentKey="jhipsiteApp.sysEmployee.home.title">Sys Employees</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsiteApp.sysEmployee.home.createLabel">Create a new Sys Employee</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {sysEmployeeList && sysEmployeeList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('empCode')}>
                    <Translate contentKey="jhipsiteApp.sysEmployee.empCode">Emp Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('empName')}>
                    <Translate contentKey="jhipsiteApp.sysEmployee.empName">Emp Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('empNameEn')}>
                    <Translate contentKey="jhipsiteApp.sysEmployee.empNameEn">Emp Name En</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('sysOfficeId')}>
                    <Translate contentKey="jhipsiteApp.sysEmployee.sysOfficeId">Sys Office Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('officeName')}>
                    <Translate contentKey="jhipsiteApp.sysEmployee.officeName">Office Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('sysCompanyId')}>
                    <Translate contentKey="jhipsiteApp.sysEmployee.sysCompanyId">Sys Company Id</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('companyName')}>
                    <Translate contentKey="jhipsiteApp.sysEmployee.companyName">Company Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('status')}>
                    <Translate contentKey="jhipsiteApp.sysEmployee.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('remarks')}>
                    <Translate contentKey="jhipsiteApp.sysEmployee.remarks">Remarks</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {sysEmployeeList.map((sysEmployee, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${sysEmployee.id}`} color="link" size="sm">
                        {sysEmployee.id}
                      </Button>
                    </td>
                    <td>{sysEmployee.empCode}</td>
                    <td>{sysEmployee.empName}</td>
                    <td>{sysEmployee.empNameEn}</td>
                    <td>{sysEmployee.sysOfficeId}</td>
                    <td>{sysEmployee.officeName}</td>
                    <td>{sysEmployee.sysCompanyId}</td>
                    <td>{sysEmployee.companyName}</td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.EmployeeStatusType.${sysEmployee.status}`} />
                    </td>
                    <td>{sysEmployee.remarks}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${sysEmployee.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysEmployee.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysEmployee.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhipsiteApp.sysEmployee.home.notFound">No Sys Employees found</Translate>
            </div>
          )}
        </div>
        <div className={sysEmployeeList && sysEmployeeList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ sysEmployee }: IRootState) => ({
  sysEmployeeList: sysEmployee.entities,
  totalItems: sysEmployee.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysEmployee);
