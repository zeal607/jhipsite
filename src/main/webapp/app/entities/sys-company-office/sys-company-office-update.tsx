import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-company-office.reducer';
import { ISysCompanyOffice } from 'app/shared/model/sys-company-office.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysCompanyOfficeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysCompanyOfficeUpdateState {
  isNew: boolean;
}

export class SysCompanyOfficeUpdate extends React.Component<ISysCompanyOfficeUpdateProps, ISysCompanyOfficeUpdateState> {
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
      const { sysCompanyOfficeEntity } = this.props;
      const entity = {
        ...sysCompanyOfficeEntity,
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
    this.props.history.push('/entity/sys-company-office');
  };

  render() {
    const { sysCompanyOfficeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysCompanyOffice.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysCompanyOffice.home.createOrEditLabel">Create or edit a SysCompanyOffice</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysCompanyOfficeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-company-office-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-company-office-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="sysCompanyIdLabel" for="sys-company-office-sysCompanyId">
                    <Translate contentKey="jhipsiteApp.sysCompanyOffice.sysCompanyId">Sys Company Id</Translate>
                  </Label>
                  <AvField
                    id="sys-company-office-sysCompanyId"
                    type="text"
                    name="sysCompanyId"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysCompanyIdLabel">
                    <Translate contentKey="jhipsiteApp.sysCompanyOffice.help.sysCompanyId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="sysOfficeIdLabel" for="sys-company-office-sysOfficeId">
                    <Translate contentKey="jhipsiteApp.sysCompanyOffice.sysOfficeId">Sys Office Id</Translate>
                  </Label>
                  <AvField
                    id="sys-company-office-sysOfficeId"
                    type="text"
                    name="sysOfficeId"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysOfficeIdLabel">
                    <Translate contentKey="jhipsiteApp.sysCompanyOffice.help.sysOfficeId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-company-office" replace color="info">
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
  sysCompanyOfficeEntity: storeState.sysCompanyOffice.entity,
  loading: storeState.sysCompanyOffice.loading,
  updating: storeState.sysCompanyOffice.updating,
  updateSuccess: storeState.sysCompanyOffice.updateSuccess
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
)(SysCompanyOfficeUpdate);
