import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-user-role.reducer';
import { ISysUserRole } from 'app/shared/model/sys-user-role.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysUserRoleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysUserRoleDetail extends React.Component<ISysUserRoleDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysUserRoleEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysUserRole.detail.title">SysUserRole</Translate> [<b>{sysUserRoleEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sysUserId">
                <Translate contentKey="jhipsiteApp.sysUserRole.sysUserId">Sys User Id</Translate>
              </span>
              <UncontrolledTooltip target="sysUserId">
                <Translate contentKey="jhipsiteApp.sysUserRole.help.sysUserId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserRoleEntity.sysUserId}</dd>
            <dt>
              <span id="sysRoleId">
                <Translate contentKey="jhipsiteApp.sysUserRole.sysRoleId">Sys Role Id</Translate>
              </span>
              <UncontrolledTooltip target="sysRoleId">
                <Translate contentKey="jhipsiteApp.sysUserRole.help.sysRoleId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserRoleEntity.sysRoleId}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-user-role" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-user-role/${sysUserRoleEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysUserRole }: IRootState) => ({
  sysUserRoleEntity: sysUserRole.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysUserRoleDetail);
