import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-user-data-scope.reducer';
import { ISysUserDataScope } from 'app/shared/model/sys-user-data-scope.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysUserDataScopeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysUserDataScopeDetail extends React.Component<ISysUserDataScopeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysUserDataScopeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysUserDataScope.detail.title">SysUserDataScope</Translate> [
            <b>{sysUserDataScopeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sysUserId">
                <Translate contentKey="jhipsiteApp.sysUserDataScope.sysUserId">Sys User Id</Translate>
              </span>
              <UncontrolledTooltip target="sysUserId">
                <Translate contentKey="jhipsiteApp.sysUserDataScope.help.sysUserId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserDataScopeEntity.sysUserId}</dd>
            <dt>
              <span id="ctrlType">
                <Translate contentKey="jhipsiteApp.sysUserDataScope.ctrlType">Ctrl Type</Translate>
              </span>
              <UncontrolledTooltip target="ctrlType">
                <Translate contentKey="jhipsiteApp.sysUserDataScope.help.ctrlType" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserDataScopeEntity.ctrlType}</dd>
            <dt>
              <span id="ctrlData">
                <Translate contentKey="jhipsiteApp.sysUserDataScope.ctrlData">Ctrl Data</Translate>
              </span>
              <UncontrolledTooltip target="ctrlData">
                <Translate contentKey="jhipsiteApp.sysUserDataScope.help.ctrlData" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserDataScopeEntity.ctrlData}</dd>
            <dt>
              <span id="ctrlPermi">
                <Translate contentKey="jhipsiteApp.sysUserDataScope.ctrlPermi">Ctrl Permi</Translate>
              </span>
              <UncontrolledTooltip target="ctrlPermi">
                <Translate contentKey="jhipsiteApp.sysUserDataScope.help.ctrlPermi" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserDataScopeEntity.ctrlPermi}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-user-data-scope" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-user-data-scope/${sysUserDataScopeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysUserDataScope }: IRootState) => ({
  sysUserDataScopeEntity: sysUserDataScope.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysUserDataScopeDetail);
