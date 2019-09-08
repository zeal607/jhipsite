import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-employee.reducer';
import { ISysEmployee } from 'app/shared/model/sys-employee.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysEmployeeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysEmployeeUpdateState {
  isNew: boolean;
}

export class SysEmployeeUpdate extends React.Component<ISysEmployeeUpdateProps, ISysEmployeeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { sysEmployeeEntity } = this.props;
      const entity = {
        ...sysEmployeeEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/sys-employee');
  };

  render() {
    const { sysEmployeeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysEmployee.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysEmployee.home.createOrEditLabel">Create or edit a SysEmployee</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysEmployeeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-employee-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-employee-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="empCodeLabel" for="sys-employee-empCode">
                    <Translate contentKey="jhipsiteApp.sysEmployee.empCode">Emp Code</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-empCode"
                    type="text"
                    name="empCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="empCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployee.help.empCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="empNameLabel" for="sys-employee-empName">
                    <Translate contentKey="jhipsiteApp.sysEmployee.empName">Emp Name</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-empName"
                    type="text"
                    name="empName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="empNameLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployee.help.empName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="empNameEnLabel" for="sys-employee-empNameEn">
                    <Translate contentKey="jhipsiteApp.sysEmployee.empNameEn">Emp Name En</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-empNameEn"
                    type="text"
                    name="empNameEn"
                    validate={{
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="empNameEnLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployee.help.empNameEn" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="sysOfficeIdLabel" for="sys-employee-sysOfficeId">
                    <Translate contentKey="jhipsiteApp.sysEmployee.sysOfficeId">Sys Office Id</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-sysOfficeId"
                    type="text"
                    name="sysOfficeId"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysOfficeIdLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployee.help.sysOfficeId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="officeNameLabel" for="sys-employee-officeName">
                    <Translate contentKey="jhipsiteApp.sysEmployee.officeName">Office Name</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-officeName"
                    type="text"
                    name="officeName"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="officeNameLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployee.help.officeName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="sysCompanyIdLabel" for="sys-employee-sysCompanyId">
                    <Translate contentKey="jhipsiteApp.sysEmployee.sysCompanyId">Sys Company Id</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-sysCompanyId"
                    type="text"
                    name="sysCompanyId"
                    validate={{
                      maxLength: { value: 200, errorMessage: translate('entity.validation.maxlength', { max: 200 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysCompanyIdLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployee.help.sysCompanyId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="companyNameLabel" for="sys-employee-companyName">
                    <Translate contentKey="jhipsiteApp.sysEmployee.companyName">Company Name</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-companyName"
                    type="text"
                    name="companyName"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="companyNameLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployee.help.companyName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="sys-employee-status">
                    <Translate contentKey="jhipsiteApp.sysEmployee.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="sys-employee-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && sysEmployeeEntity.status) || 'NORMAL'}
                  >
                    <option value="NORMAL">{translate('jhipsiteApp.EmployeeStatusType.NORMAL')}</option>
                    <option value="DELETE">{translate('jhipsiteApp.EmployeeStatusType.DELETE')}</option>
                    <option value="DEPARTUTE">{translate('jhipsiteApp.EmployeeStatusType.DEPARTUTE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="statusLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployee.help.status" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="remarksLabel" for="sys-employee-remarks">
                    <Translate contentKey="jhipsiteApp.sysEmployee.remarks">Remarks</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-remarks"
                    type="text"
                    name="remarks"
                    validate={{
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                  <UncontrolledTooltip target="remarksLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployee.help.remarks" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-employee" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  sysEmployeeEntity: storeState.sysEmployee.entity,
  loading: storeState.sysEmployee.loading,
  updating: storeState.sysEmployee.updating,
  updateSuccess: storeState.sysEmployee.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysEmployeeUpdate);
