import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-company-office.reducer';
import { ISysCompanyOffice } from 'app/shared/model/sys-company-office.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysCompanyOfficeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysCompanyOfficeDetail extends React.Component<ISysCompanyOfficeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysCompanyOfficeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysCompanyOffice.detail.title">SysCompanyOffice</Translate> [
            <b>{sysCompanyOfficeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sysCompanyId">
                <Translate contentKey="jhipsiteApp.sysCompanyOffice.sysCompanyId">Sys Company Id</Translate>
              </span>
              <UncontrolledTooltip target="sysCompanyId">
                <Translate contentKey="jhipsiteApp.sysCompanyOffice.help.sysCompanyId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyOfficeEntity.sysCompanyId}</dd>
            <dt>
              <span id="sysOfficeId">
                <Translate contentKey="jhipsiteApp.sysCompanyOffice.sysOfficeId">Sys Office Id</Translate>
              </span>
              <UncontrolledTooltip target="sysOfficeId">
                <Translate contentKey="jhipsiteApp.sysCompanyOffice.help.sysOfficeId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyOfficeEntity.sysOfficeId}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-company-office" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-company-office/${sysCompanyOfficeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysCompanyOffice }: IRootState) => ({
  sysCompanyOfficeEntity: sysCompanyOffice.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysCompanyOfficeDetail);
