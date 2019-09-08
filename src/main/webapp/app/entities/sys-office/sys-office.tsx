import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './sys-office.reducer';
import { ISysOffice } from 'app/shared/model/sys-office.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ISysOfficeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ISysOfficeState = IPaginationBaseState;

export class SysOffice extends React.Component<ISysOfficeProps, ISysOfficeState> {
  state: ISysOfficeState = {
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
    const { sysOfficeList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="sys-office-heading">
          <Translate contentKey="jhipsiteApp.sysOffice.home.title">Sys Offices</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsiteApp.sysOffice.home.createLabel">Create a new Sys Office</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {sysOfficeList && sysOfficeList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('officeCode')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.officeCode">Office Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('parentCode')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.parentCode">Parent Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('parentCodes')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.parentCodes">Parent Codes</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeSort')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.treeSort">Tree Sort</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeSorts')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.treeSorts">Tree Sorts</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeLeaf')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.treeLeaf">Tree Leaf</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeLevel')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.treeLevel">Tree Level</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeNames')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.treeNames">Tree Names</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('viewCode')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.viewCode">View Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('officeName')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.officeName">Office Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('fullName')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.fullName">Full Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('officeType')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.officeType">Office Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('leader')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.leader">Leader</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('phone')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.phone">Phone</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('address')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.address">Address</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('zipCode')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.zipCode">Zip Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('email')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.email">Email</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('remarks')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.remarks">Remarks</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('status')}>
                    <Translate contentKey="jhipsiteApp.sysOffice.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {sysOfficeList.map((sysOffice, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${sysOffice.id}`} color="link" size="sm">
                        {sysOffice.id}
                      </Button>
                    </td>
                    <td>{sysOffice.officeCode}</td>
                    <td>{sysOffice.parentCode}</td>
                    <td>{sysOffice.parentCodes}</td>
                    <td>{sysOffice.treeSort}</td>
                    <td>{sysOffice.treeSorts}</td>
                    <td>{sysOffice.treeLeaf ? 'true' : 'false'}</td>
                    <td>{sysOffice.treeLevel}</td>
                    <td>{sysOffice.treeNames}</td>
                    <td>{sysOffice.viewCode}</td>
                    <td>{sysOffice.officeName}</td>
                    <td>{sysOffice.fullName}</td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.OfficeType.${sysOffice.officeType}`} />
                    </td>
                    <td>{sysOffice.leader}</td>
                    <td>{sysOffice.phone}</td>
                    <td>{sysOffice.address}</td>
                    <td>{sysOffice.zipCode}</td>
                    <td>{sysOffice.email}</td>
                    <td>{sysOffice.remarks}</td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.OfficeStatusType.${sysOffice.status}`} />
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${sysOffice.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysOffice.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysOffice.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhipsiteApp.sysOffice.home.notFound">No Sys Offices found</Translate>
            </div>
          )}
        </div>
        <div className={sysOfficeList && sysOfficeList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ sysOffice }: IRootState) => ({
  sysOfficeList: sysOffice.entities,
  totalItems: sysOffice.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysOffice);
