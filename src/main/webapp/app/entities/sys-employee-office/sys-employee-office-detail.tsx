import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-employee-office.reducer';
import { ISysEmployeeOffice } from 'app/shared/model/sys-employee-office.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysEmployeeOfficeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysEmployeeOfficeDetail extends React.Component<ISysEmployeeOfficeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysEmployeeOfficeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysEmployeeOffice.detail.title">SysEmployeeOffice</Translate> [
            <b>{sysEmployeeOfficeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sysEmployeeId">
                <Translate contentKey="jhipsiteApp.sysEmployeeOffice.sysEmployeeId">Sys Employee Id</Translate>
              </span>
              <UncontrolledTooltip target="sysEmployeeId">
                <Translate contentKey="jhipsiteApp.sysEmployeeOffice.help.sysEmployeeId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeOfficeEntity.sysEmployeeId}</dd>
            <dt>
              <span id="sysOfficeId">
                <Translate contentKey="jhipsiteApp.sysEmployeeOffice.sysOfficeId">Sys Office Id</Translate>
              </span>
              <UncontrolledTooltip target="sysOfficeId">
                <Translate contentKey="jhipsiteApp.sysEmployeeOffice.help.sysOfficeId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeOfficeEntity.sysOfficeId}</dd>
            <dt>
              <span id="sysPostId">
                <Translate contentKey="jhipsiteApp.sysEmployeeOffice.sysPostId">Sys Post Id</Translate>
              </span>
              <UncontrolledTooltip target="sysPostId">
                <Translate contentKey="jhipsiteApp.sysEmployeeOffice.help.sysPostId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeOfficeEntity.sysPostId}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-employee-office" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-employee-office/${sysEmployeeOfficeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysEmployeeOffice }: IRootState) => ({
  sysEmployeeOfficeEntity: sysEmployeeOffice.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysEmployeeOfficeDetail);
