import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-role-menu.reducer';
import { ISysRoleMenu } from 'app/shared/model/sys-role-menu.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysRoleMenuDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysRoleMenuDetail extends React.Component<ISysRoleMenuDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysRoleMenuEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysRoleMenu.detail.title">SysRoleMenu</Translate> [<b>{sysRoleMenuEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sysRoleId">
                <Translate contentKey="jhipsiteApp.sysRoleMenu.sysRoleId">Sys Role Id</Translate>
              </span>
              <UncontrolledTooltip target="sysRoleId">
                <Translate contentKey="jhipsiteApp.sysRoleMenu.help.sysRoleId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleMenuEntity.sysRoleId}</dd>
            <dt>
              <span id="sysMenuId">
                <Translate contentKey="jhipsiteApp.sysRoleMenu.sysMenuId">Sys Menu Id</Translate>
              </span>
              <UncontrolledTooltip target="sysMenuId">
                <Translate contentKey="jhipsiteApp.sysRoleMenu.help.sysMenuId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleMenuEntity.sysMenuId}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-role-menu" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-role-menu/${sysRoleMenuEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysRoleMenu }: IRootState) => ({
  sysRoleMenuEntity: sysRoleMenu.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysRoleMenuDetail);
