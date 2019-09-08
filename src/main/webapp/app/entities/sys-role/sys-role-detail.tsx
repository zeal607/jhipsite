import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-role.reducer';
import { ISysRole } from 'app/shared/model/sys-role.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysRoleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysRoleDetail extends React.Component<ISysRoleDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysRoleEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysRole.detail.title">SysRole</Translate> [<b>{sysRoleEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="roleCode">
                <Translate contentKey="jhipsiteApp.sysRole.roleCode">Role Code</Translate>
              </span>
              <UncontrolledTooltip target="roleCode">
                <Translate contentKey="jhipsiteApp.sysRole.help.roleCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleEntity.roleCode}</dd>
            <dt>
              <span id="roleName">
                <Translate contentKey="jhipsiteApp.sysRole.roleName">Role Name</Translate>
              </span>
              <UncontrolledTooltip target="roleName">
                <Translate contentKey="jhipsiteApp.sysRole.help.roleName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleEntity.roleName}</dd>
            <dt>
              <span id="roleType">
                <Translate contentKey="jhipsiteApp.sysRole.roleType">Role Type</Translate>
              </span>
              <UncontrolledTooltip target="roleType">
                <Translate contentKey="jhipsiteApp.sysRole.help.roleType" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleEntity.roleType}</dd>
            <dt>
              <span id="roleSort">
                <Translate contentKey="jhipsiteApp.sysRole.roleSort">Role Sort</Translate>
              </span>
              <UncontrolledTooltip target="roleSort">
                <Translate contentKey="jhipsiteApp.sysRole.help.roleSort" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleEntity.roleSort}</dd>
            <dt>
              <span id="isSys">
                <Translate contentKey="jhipsiteApp.sysRole.isSys">Is Sys</Translate>
              </span>
              <UncontrolledTooltip target="isSys">
                <Translate contentKey="jhipsiteApp.sysRole.help.isSys" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleEntity.isSys ? 'true' : 'false'}</dd>
            <dt>
              <span id="dataScope">
                <Translate contentKey="jhipsiteApp.sysRole.dataScope">Data Scope</Translate>
              </span>
              <UncontrolledTooltip target="dataScope">
                <Translate contentKey="jhipsiteApp.sysRole.help.dataScope" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleEntity.dataScope}</dd>
            <dt>
              <span id="bizScope">
                <Translate contentKey="jhipsiteApp.sysRole.bizScope">Biz Scope</Translate>
              </span>
              <UncontrolledTooltip target="bizScope">
                <Translate contentKey="jhipsiteApp.sysRole.help.bizScope" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleEntity.bizScope}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="jhipsiteApp.sysRole.status">Status</Translate>
              </span>
              <UncontrolledTooltip target="status">
                <Translate contentKey="jhipsiteApp.sysRole.help.status" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleEntity.status}</dd>
            <dt>
              <span id="remarks">
                <Translate contentKey="jhipsiteApp.sysRole.remarks">Remarks</Translate>
              </span>
              <UncontrolledTooltip target="remarks">
                <Translate contentKey="jhipsiteApp.sysRole.help.remarks" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleEntity.remarks}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-role" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-role/${sysRoleEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysRole }: IRootState) => ({
  sysRoleEntity: sysRole.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysRoleDetail);
