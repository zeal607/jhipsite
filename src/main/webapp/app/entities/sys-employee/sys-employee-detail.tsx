import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-employee.reducer';
import { ISysEmployee } from 'app/shared/model/sys-employee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysEmployeeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysEmployeeDetail extends React.Component<ISysEmployeeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysEmployeeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysEmployee.detail.title">SysEmployee</Translate> [<b>{sysEmployeeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="empCode">
                <Translate contentKey="jhipsiteApp.sysEmployee.empCode">Emp Code</Translate>
              </span>
              <UncontrolledTooltip target="empCode">
                <Translate contentKey="jhipsiteApp.sysEmployee.help.empCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeEntity.empCode}</dd>
            <dt>
              <span id="empName">
                <Translate contentKey="jhipsiteApp.sysEmployee.empName">Emp Name</Translate>
              </span>
              <UncontrolledTooltip target="empName">
                <Translate contentKey="jhipsiteApp.sysEmployee.help.empName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeEntity.empName}</dd>
            <dt>
              <span id="empNameEn">
                <Translate contentKey="jhipsiteApp.sysEmployee.empNameEn">Emp Name En</Translate>
              </span>
              <UncontrolledTooltip target="empNameEn">
                <Translate contentKey="jhipsiteApp.sysEmployee.help.empNameEn" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeEntity.empNameEn}</dd>
            <dt>
              <span id="sysOfficeId">
                <Translate contentKey="jhipsiteApp.sysEmployee.sysOfficeId">Sys Office Id</Translate>
              </span>
              <UncontrolledTooltip target="sysOfficeId">
                <Translate contentKey="jhipsiteApp.sysEmployee.help.sysOfficeId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeEntity.sysOfficeId}</dd>
            <dt>
              <span id="officeName">
                <Translate contentKey="jhipsiteApp.sysEmployee.officeName">Office Name</Translate>
              </span>
              <UncontrolledTooltip target="officeName">
                <Translate contentKey="jhipsiteApp.sysEmployee.help.officeName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeEntity.officeName}</dd>
            <dt>
              <span id="sysCompanyId">
                <Translate contentKey="jhipsiteApp.sysEmployee.sysCompanyId">Sys Company Id</Translate>
              </span>
              <UncontrolledTooltip target="sysCompanyId">
                <Translate contentKey="jhipsiteApp.sysEmployee.help.sysCompanyId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeEntity.sysCompanyId}</dd>
            <dt>
              <span id="companyName">
                <Translate contentKey="jhipsiteApp.sysEmployee.companyName">Company Name</Translate>
              </span>
              <UncontrolledTooltip target="companyName">
                <Translate contentKey="jhipsiteApp.sysEmployee.help.companyName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeEntity.companyName}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="jhipsiteApp.sysEmployee.status">Status</Translate>
              </span>
              <UncontrolledTooltip target="status">
                <Translate contentKey="jhipsiteApp.sysEmployee.help.status" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeEntity.status}</dd>
            <dt>
              <span id="remarks">
                <Translate contentKey="jhipsiteApp.sysEmployee.remarks">Remarks</Translate>
              </span>
              <UncontrolledTooltip target="remarks">
                <Translate contentKey="jhipsiteApp.sysEmployee.help.remarks" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeeEntity.remarks}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-employee" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-employee/${sysEmployeeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysEmployee }: IRootState) => ({
  sysEmployeeEntity: sysEmployee.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysEmployeeDetail);
