import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './sys-menu.reducer';
import { ISysMenu } from 'app/shared/model/sys-menu.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ISysMenuProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ISysMenuState = IPaginationBaseState;

export class SysMenu extends React.Component<ISysMenuProps, ISysMenuState> {
  state: ISysMenuState = {
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
    const { sysMenuList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="sys-menu-heading">
          <Translate contentKey="jhipsiteApp.sysMenu.home.title">Sys Menus</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsiteApp.sysMenu.home.createLabel">Create a new Sys Menu</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {sysMenuList && sysMenuList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('menuCode')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.menuCode">Menu Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('parentCode')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.parentCode">Parent Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('parentCodes')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.parentCodes">Parent Codes</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeSort')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.treeSort">Tree Sort</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeSorts')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.treeSorts">Tree Sorts</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeLeaf')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.treeLeaf">Tree Leaf</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeLevel')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.treeLevel">Tree Level</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('treeNames')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.treeNames">Tree Names</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('menuName')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.menuName">Menu Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('menuType')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.menuType">Menu Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('menuHref')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.menuHref">Menu Href</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('menuIcon')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.menuIcon">Menu Icon</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('menuTitle')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.menuTitle">Menu Title</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('permission')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.permission">Permission</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('menuSort')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.menuSort">Menu Sort</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isShow')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.isShow">Is Show</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('sysCode')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.sysCode">Sys Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('status')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('remarks')}>
                    <Translate contentKey="jhipsiteApp.sysMenu.remarks">Remarks</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {sysMenuList.map((sysMenu, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${sysMenu.id}`} color="link" size="sm">
                        {sysMenu.id}
                      </Button>
                    </td>
                    <td>{sysMenu.menuCode}</td>
                    <td>{sysMenu.parentCode}</td>
                    <td>{sysMenu.parentCodes}</td>
                    <td>{sysMenu.treeSort}</td>
                    <td>{sysMenu.treeSorts}</td>
                    <td>{sysMenu.treeLeaf ? 'true' : 'false'}</td>
                    <td>{sysMenu.treeLevel}</td>
                    <td>{sysMenu.treeNames}</td>
                    <td>{sysMenu.menuName}</td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.MenuType.${sysMenu.menuType}`} />
                    </td>
                    <td>{sysMenu.menuHref}</td>
                    <td>{sysMenu.menuIcon}</td>
                    <td>{sysMenu.menuTitle}</td>
                    <td>{sysMenu.permission}</td>
                    <td>{sysMenu.menuSort}</td>
                    <td>{sysMenu.isShow ? 'true' : 'false'}</td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.SysType.${sysMenu.sysCode}`} />
                    </td>
                    <td>
                      <Translate contentKey={`jhipsiteApp.MenuStatusType.${sysMenu.status}`} />
                    </td>
                    <td>{sysMenu.remarks}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${sysMenu.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysMenu.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${sysMenu.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="jhipsiteApp.sysMenu.home.notFound">No Sys Menus found</Translate>
            </div>
          )}
        </div>
        <div className={sysMenuList && sysMenuList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ sysMenu }: IRootState) => ({
  sysMenuList: sysMenu.entities,
  totalItems: sysMenu.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysMenu);
