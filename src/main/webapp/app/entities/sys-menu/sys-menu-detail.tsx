import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-menu.reducer';
import { ISysMenu } from 'app/shared/model/sys-menu.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysMenuDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysMenuDetail extends React.Component<ISysMenuDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysMenuEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysMenu.detail.title">SysMenu</Translate> [<b>{sysMenuEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="menuCode">
                <Translate contentKey="jhipsiteApp.sysMenu.menuCode">Menu Code</Translate>
              </span>
              <UncontrolledTooltip target="menuCode">
                <Translate contentKey="jhipsiteApp.sysMenu.help.menuCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.menuCode}</dd>
            <dt>
              <span id="parentCode">
                <Translate contentKey="jhipsiteApp.sysMenu.parentCode">Parent Code</Translate>
              </span>
              <UncontrolledTooltip target="parentCode">
                <Translate contentKey="jhipsiteApp.sysMenu.help.parentCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.parentCode}</dd>
            <dt>
              <span id="parentCodes">
                <Translate contentKey="jhipsiteApp.sysMenu.parentCodes">Parent Codes</Translate>
              </span>
              <UncontrolledTooltip target="parentCodes">
                <Translate contentKey="jhipsiteApp.sysMenu.help.parentCodes" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.parentCodes}</dd>
            <dt>
              <span id="treeSort">
                <Translate contentKey="jhipsiteApp.sysMenu.treeSort">Tree Sort</Translate>
              </span>
              <UncontrolledTooltip target="treeSort">
                <Translate contentKey="jhipsiteApp.sysMenu.help.treeSort" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.treeSort}</dd>
            <dt>
              <span id="treeSorts">
                <Translate contentKey="jhipsiteApp.sysMenu.treeSorts">Tree Sorts</Translate>
              </span>
              <UncontrolledTooltip target="treeSorts">
                <Translate contentKey="jhipsiteApp.sysMenu.help.treeSorts" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.treeSorts}</dd>
            <dt>
              <span id="treeLeaf">
                <Translate contentKey="jhipsiteApp.sysMenu.treeLeaf">Tree Leaf</Translate>
              </span>
              <UncontrolledTooltip target="treeLeaf">
                <Translate contentKey="jhipsiteApp.sysMenu.help.treeLeaf" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.treeLeaf ? 'true' : 'false'}</dd>
            <dt>
              <span id="treeLevel">
                <Translate contentKey="jhipsiteApp.sysMenu.treeLevel">Tree Level</Translate>
              </span>
              <UncontrolledTooltip target="treeLevel">
                <Translate contentKey="jhipsiteApp.sysMenu.help.treeLevel" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.treeLevel}</dd>
            <dt>
              <span id="treeNames">
                <Translate contentKey="jhipsiteApp.sysMenu.treeNames">Tree Names</Translate>
              </span>
              <UncontrolledTooltip target="treeNames">
                <Translate contentKey="jhipsiteApp.sysMenu.help.treeNames" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.treeNames}</dd>
            <dt>
              <span id="menuName">
                <Translate contentKey="jhipsiteApp.sysMenu.menuName">Menu Name</Translate>
              </span>
              <UncontrolledTooltip target="menuName">
                <Translate contentKey="jhipsiteApp.sysMenu.help.menuName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.menuName}</dd>
            <dt>
              <span id="menuType">
                <Translate contentKey="jhipsiteApp.sysMenu.menuType">Menu Type</Translate>
              </span>
              <UncontrolledTooltip target="menuType">
                <Translate contentKey="jhipsiteApp.sysMenu.help.menuType" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.menuType}</dd>
            <dt>
              <span id="menuHref">
                <Translate contentKey="jhipsiteApp.sysMenu.menuHref">Menu Href</Translate>
              </span>
              <UncontrolledTooltip target="menuHref">
                <Translate contentKey="jhipsiteApp.sysMenu.help.menuHref" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.menuHref}</dd>
            <dt>
              <span id="menuIcon">
                <Translate contentKey="jhipsiteApp.sysMenu.menuIcon">Menu Icon</Translate>
              </span>
              <UncontrolledTooltip target="menuIcon">
                <Translate contentKey="jhipsiteApp.sysMenu.help.menuIcon" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.menuIcon}</dd>
            <dt>
              <span id="menuTitle">
                <Translate contentKey="jhipsiteApp.sysMenu.menuTitle">Menu Title</Translate>
              </span>
              <UncontrolledTooltip target="menuTitle">
                <Translate contentKey="jhipsiteApp.sysMenu.help.menuTitle" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.menuTitle}</dd>
            <dt>
              <span id="permission">
                <Translate contentKey="jhipsiteApp.sysMenu.permission">Permission</Translate>
              </span>
              <UncontrolledTooltip target="permission">
                <Translate contentKey="jhipsiteApp.sysMenu.help.permission" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.permission}</dd>
            <dt>
              <span id="menuSort">
                <Translate contentKey="jhipsiteApp.sysMenu.menuSort">Menu Sort</Translate>
              </span>
              <UncontrolledTooltip target="menuSort">
                <Translate contentKey="jhipsiteApp.sysMenu.help.menuSort" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.menuSort}</dd>
            <dt>
              <span id="isShow">
                <Translate contentKey="jhipsiteApp.sysMenu.isShow">Is Show</Translate>
              </span>
              <UncontrolledTooltip target="isShow">
                <Translate contentKey="jhipsiteApp.sysMenu.help.isShow" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.isShow ? 'true' : 'false'}</dd>
            <dt>
              <span id="sysCode">
                <Translate contentKey="jhipsiteApp.sysMenu.sysCode">Sys Code</Translate>
              </span>
              <UncontrolledTooltip target="sysCode">
                <Translate contentKey="jhipsiteApp.sysMenu.help.sysCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.sysCode}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="jhipsiteApp.sysMenu.status">Status</Translate>
              </span>
              <UncontrolledTooltip target="status">
                <Translate contentKey="jhipsiteApp.sysMenu.help.status" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.status}</dd>
            <dt>
              <span id="remarks">
                <Translate contentKey="jhipsiteApp.sysMenu.remarks">Remarks</Translate>
              </span>
              <UncontrolledTooltip target="remarks">
                <Translate contentKey="jhipsiteApp.sysMenu.help.remarks" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysMenuEntity.remarks}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-menu" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-menu/${sysMenuEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ sysMenu }: IRootState) => ({
  sysMenuEntity: sysMenu.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysMenuDetail);
