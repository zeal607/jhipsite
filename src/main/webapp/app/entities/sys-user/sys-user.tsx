import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './sys-user.reducer';
import { ISysUser } from 'app/shared/model/sys-user.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ISysUserProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ISysUserState = IPaginationBaseState;

export class SysUser extends React.Component<ISysUserProps, ISysUserState> {
  state: ISysUserState = {
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
    const { sysUserList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="sys-user-heading">
          <Translate contentKey="jhipsiteApp.sysUser.home.title">Sys Users</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsiteApp.sysUser.home.createLabel">Create a new Sys User</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {sysUserList && sysUserList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('userCode')}>
                    <Translate contentKey="jhipsiteApp.sysUser.userCode">User Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('loginCode')}>
                    <Translate contentKey="jhipsiteApp.sysUser.loginCode">Login Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('userName')}>
                    <Translate contentKey="jhipsiteApp.sysUser.userName">User Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('password')}>
                    <Translate contentKey="jhipsiteApp.sysUser.password">Password</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('email')}>
                    <Translate contentKey="jhipsiteApp.sysUser.email">Email</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('mobile')}>
                    <Translate contentKey="jhipsiteApp.sysUser.mobile">Mobile</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('phone')}>
                    <Translate contentKey="jhipsiteApp.sysUser.phone">Phone</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('sex')}>
                    <Translate contentKey="jhipsiteApp.sysUser.sex">Sex</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('avatar')}>
                    <Translate contentKey="jhipsiteApp.sysUser.avatar">Avatar</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('sign')}>
                    <Translate contentKey="jhipsiteApp.sysUser.sign">Sign</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('wxOpenid')}>
                    <Translate contentKey="jhipsiteApp.sysUser.wxOpenid">Wx Openid</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('mobileImei')}>
                    <Translate contentKey="jhipsiteApp.sysUser.mobileImei">Mobile Imei</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('userType')}>
                    <Translate contentKey="jhipsiteApp.sysUser.userType">User Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('refCode')}>
                    <Translate contentKey="jhipsiteApp.sysUser.refCode">Ref Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('refName')}>
                    <Translate contentKey="jhipsiteApp.sysUser.refName">Ref Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('lastLoginIp')}>
                    <Translate contentKey="jhipsiteApp.sysUser.lastLoginIp">Last Login Ip</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('lastLoginDate')}>
                    <Translate contentKey="jhipsiteApp.sysUser.lastLoginDate">Last Login Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('freezeDate')}>
                    <Translate contentKey="jhipsiteApp.sysUser.freezeDate">Freeze Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('freezeCause')}>
                    <Translate contentKey="jhipsiteApp.sysUser.freezeCause">Freeze Cause</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('userSort')}>
                    <Translate contentKey="jhipsiteApp.sysUser.userSort">User Sort</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('status')}>
                    <Translate contentKey="jhipsiteApp.sysUser.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('remarks')}>
                    <Translate contentKey="jhipsiteApp.sysUser.remarks">Remarks</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {sysUserList.map((sysUser, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${sysUser.id}`} color="link" size="sm">
                        {sysUser.id}
                      </Button>
                    </td>
                    <td>{sysUser.userCode}</td>
                    <td>{sysUser.loginCode}</td>
                    <td>{sysUser.userName}</td>
                    <td>{sysUser.password}</td>
                    <td>{sysUser.email}</td>
                    <td>{sysUser.mobile}</td>
                    <td>{sysUser.phone}</td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.GenderType.${sysUser.sex}`} />
                    </td>
                    <td>{sysUser.avatar}</td>
                    <td>{sysUser.sign}</td>
                    <td>{sysUser.wxOpenid}</td>
                    <td>{sysUser.mobileImei}</td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.UserType.${sysUser.userType}`} />
                    </td>
                    <td>{sysUser.refCode}</td>
                    <td>{sysUser.refName}</td>
                    <td>{sysUser.lastLoginIp}</td>
                    <td>
                      <TextFormat type="date" value={sysUser.lastLoginDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={sysUser.freezeDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{sysUser.freezeCause}</td>
                    <td>{sysUser.userSort}</td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.UserStatusType.${sysUser.status}`} />
                    </td>
                    <td>{sysUser.remarks}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${sysUser.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysUser.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysUser.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhipsiteApp.sysUser.home.notFound">No Sys Users found</Translate>
            </div>
          )}
        </div>
        <div className={sysUserList && sysUserList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ sysUser }: IRootState) => ({
  sysUserList: sysUser.entities,
  totalItems: sysUser.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysUser);
