import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-role-data-scope.reducer';
import { ISysRoleDataScope } from 'app/shared/model/sys-role-data-scope.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysRoleDataScopeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysRoleDataScopeDetail extends React.Component<ISysRoleDataScopeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysRoleDataScopeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysRoleDataScope.detail.title">SysRoleDataScope</Translate> [
            <b>{sysRoleDataScopeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sysRoleId">
                <Translate contentKey="jhipsiteApp.sysRoleDataScope.sysRoleId">Sys Role Id</Translate>
              </span>
              <UncontrolledTooltip target="sysRoleId">
                <Translate contentKey="jhipsiteApp.sysRoleDataScope.help.sysRoleId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleDataScopeEntity.sysRoleId}</dd>
            <dt>
              <span id="ctrlType">
                <Translate contentKey="jhipsiteApp.sysRoleDataScope.ctrlType">Ctrl Type</Translate>
              </span>
              <UncontrolledTooltip target="ctrlType">
                <Translate contentKey="jhipsiteApp.sysRoleDataScope.help.ctrlType" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleDataScopeEntity.ctrlType}</dd>
            <dt>
              <span id="ctrlData">
                <Translate contentKey="jhipsiteApp.sysRoleDataScope.ctrlData">Ctrl Data</Translate>
              </span>
              <UncontrolledTooltip target="ctrlData">
                <Translate contentKey="jhipsiteApp.sysRoleDataScope.help.ctrlData" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleDataScopeEntity.ctrlData}</dd>
            <dt>
              <span id="ctrlPermi">
                <Translate contentKey="jhipsiteApp.sysRoleDataScope.ctrlPermi">Ctrl Permi</Translate>
              </span>
              <UncontrolledTooltip target="ctrlPermi">
                <Translate contentKey="jhipsiteApp.sysRoleDataScope.help.ctrlPermi" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysRoleDataScopeEntity.ctrlPermi}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-role-data-scope" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-role-data-scope/${sysRoleDataScopeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysRoleDataScope }: IRootState) => ({
  sysRoleDataScopeEntity: sysRoleDataScope.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysRoleDataScopeDetail);
