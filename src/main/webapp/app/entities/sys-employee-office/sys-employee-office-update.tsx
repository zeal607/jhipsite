import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-employee-office.reducer';
import { ISysEmployeeOffice } from 'app/shared/model/sys-employee-office.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysEmployeeOfficeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysEmployeeOfficeUpdateState {
  isNew: boolean;
}

export class SysEmployeeOfficeUpdate extends React.Component<ISysEmployeeOfficeUpdateProps, ISysEmployeeOfficeUpdateState> {
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
      const { sysEmployeeOfficeEntity } = this.props;
      const entity = {
        ...sysEmployeeOfficeEntity,
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
    this.props.history.push('/entity/sys-employee-office');
  };

  render() {
    const { sysEmployeeOfficeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysEmployeeOffice.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysEmployeeOffice.home.createOrEditLabel">Create or edit a SysEmployeeOffice</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysEmployeeOfficeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-employee-office-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-employee-office-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="sysEmployeeIdLabel" for="sys-employee-office-sysEmployeeId">
                    <Translate contentKey="jhipsiteApp.sysEmployeeOffice.sysEmployeeId">Sys Employee Id</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-office-sysEmployeeId"
                    type="text"
                    name="sysEmployeeId"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysEmployeeIdLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployeeOffice.help.sysEmployeeId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="sysOfficeIdLabel" for="sys-employee-office-sysOfficeId">
                    <Translate contentKey="jhipsiteApp.sysEmployeeOffice.sysOfficeId">Sys Office Id</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-office-sysOfficeId"
                    type="text"
                    name="sysOfficeId"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysOfficeIdLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployeeOffice.help.sysOfficeId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="sysPostIdLabel" for="sys-employee-office-sysPostId">
                    <Translate contentKey="jhipsiteApp.sysEmployeeOffice.sysPostId">Sys Post Id</Translate>
                  </Label>
                  <AvField
                    id="sys-employee-office-sysPostId"
                    type="text"
                    name="sysPostId"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysPostIdLabel">
                    <Translate contentKey="jhipsiteApp.sysEmployeeOffice.help.sysPostId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-employee-office" replace color="info">
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
  sysEmployeeOfficeEntity: storeState.sysEmployeeOffice.entity,
  loading: storeState.sysEmployeeOffice.loading,
  updating: storeState.sysEmployeeOffice.updating,
  updateSuccess: storeState.sysEmployeeOffice.updateSuccess
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
)(SysEmployeeOfficeUpdate);
