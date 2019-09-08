import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './sys-company.reducer';
import { ISysCompany } from 'app/shared/model/sys-company.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ISysCompanyProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ISysCompanyState = IPaginationBaseState;

export class SysCompany extends React.Component<ISysCompanyProps, ISysCompanyState> {
  state: ISysCompanyState = {
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
    const { sysCompanyList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="sys-company-heading">
          <Translate contentKey="jhipsiteApp.sysCompany.home.title">Sys Companies</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsiteApp.sysCompany.home.createLabel">Create a new Sys Company</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {sysCompanyList && sysCompanyList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('companyCode')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.companyCode">Company Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('parentCode')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.parentCode">Parent Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('parentCodes')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.parentCodes">Parent Codes</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeSort')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.treeSort">Tree Sort</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeSorts')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.treeSorts">Tree Sorts</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeLeaf')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.treeLeaf">Tree Leaf</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeLevel')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.treeLevel">Tree Level</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeNames')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.treeNames">Tree Names</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('viewCode')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.viewCode">View Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('companyName')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.companyName">Company Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('fullName')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.fullName">Full Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('areaCode')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.areaCode">Area Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('status')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('remarks')}>
                    <Translate contentKey="jhipsiteApp.sysCompany.remarks">Remarks</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {sysCompanyList.map((sysCompany, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${sysCompany.id}`} color="link" size="sm">
                        {sysCompany.id}
                      </Button>
                    </td>
                    <td>{sysCompany.companyCode}</td>
                    <td>{sysCompany.parentCode}</td>
                    <td>{sysCompany.parentCodes}</td>
                    <td>{sysCompany.treeSort}</td>
                    <td>{sysCompany.treeSorts}</td>
                    <td>{sysCompany.treeLeaf ? 'true' : 'false'}</td>
                    <td>{sysCompany.treeLevel}</td>
                    <td>{sysCompany.treeNames}</td>
                    <td>{sysCompany.viewCode}</td>
                    <td>{sysCompany.companyName}</td>
                    <td>{sysCompany.fullName}</td>
                    <td>{sysCompany.areaCode}</td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.CompanyStatusType.${sysCompany.status}`} />
                    </td>
                    <td>{sysCompany.remarks}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${sysCompany.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysCompany.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysCompany.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhipsiteApp.sysCompany.home.notFound">No Sys Companies found</Translate>
            </div>
          )}
        </div>
        <div className={sysCompanyList && sysCompanyList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ sysCompany }: IRootState) => ({
  sysCompanyList: sysCompany.entities,
  totalItems: sysCompany.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysCompany);
