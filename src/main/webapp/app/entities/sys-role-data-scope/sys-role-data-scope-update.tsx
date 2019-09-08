import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-role-data-scope.reducer';
import { ISysRoleDataScope } from 'app/shared/model/sys-role-data-scope.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysRoleDataScopeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysRoleDataScopeUpdateState {
  isNew: boolean;
}

export class SysRoleDataScopeUpdate extends React.Component<ISysRoleDataScopeUpdateProps, ISysRoleDataScopeUpdateState> {
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
      const { sysRoleDataScopeEntity } = this.props;
      const entity = {
        ...sysRoleDataScopeEntity,
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
    this.props.history.push('/entity/sys-role-data-scope');
  };

  render() {
    const { sysRoleDataScopeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysRoleDataScope.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysRoleDataScope.home.createOrEditLabel">Create or edit a SysRoleDataScope</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysRoleDataScopeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-role-data-scope-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-role-data-scope-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="sysRoleIdLabel" for="sys-role-data-scope-sysRoleId">
                    <Translate contentKey="jhipsiteApp.sysRoleDataScope.sysRoleId">Sys Role Id</Translate>
                  </Label>
                  <AvField
                    id="sys-role-data-scope-sysRoleId"
                    type="text"
                    name="sysRoleId"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysRoleIdLabel">
                    <Translate contentKey="jhipsiteApp.sysRoleDataScope.help.sysRoleId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ctrlTypeLabel" for="sys-role-data-scope-ctrlType">
                    <Translate contentKey="jhipsiteApp.sysRoleDataScope.ctrlType">Ctrl Type</Translate>
                  </Label>
                  <AvInput
                    id="sys-role-data-scope-ctrlType"
                    type="select"
                    className="form-control"
                    name="ctrlType"
                    value={(!isNew && sysRoleDataScopeEntity.ctrlType) || 'COMPANY'}
                  >
                    <option value="COMPANY">{translate('jhipsiteApp.ControlType.COMPANY')}</option>
                    <option value="OFFICE">{translate('jhipsiteApp.ControlType.OFFICE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="ctrlTypeLabel">
                    <Translate contentKey="jhipsiteApp.sysRoleDataScope.help.ctrlType" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ctrlDataLabel" for="sys-role-data-scope-ctrlData">
                    <Translate contentKey="jhipsiteApp.sysRoleDataScope.ctrlData">Ctrl Data</Translate>
                  </Label>
                  <AvField
                    id="sys-role-data-scope-ctrlData"
                    type="text"
                    name="ctrlData"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="ctrlDataLabel">
                    <Translate contentKey="jhipsiteApp.sysRoleDataScope.help.ctrlData" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ctrlPermiLabel" for="sys-role-data-scope-ctrlPermi">
                    <Translate contentKey="jhipsiteApp.sysRoleDataScope.ctrlPermi">Ctrl Permi</Translate>
                  </Label>
                  <AvField
                    id="sys-role-data-scope-ctrlPermi"
                    type="text"
                    name="ctrlPermi"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="ctrlPermiLabel">
                    <Translate contentKey="jhipsiteApp.sysRoleDataScope.help.ctrlPermi" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-role-data-scope" replace color="info">
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
  sysRoleDataScopeEntity: storeState.sysRoleDataScope.entity,
  loading: storeState.sysRoleDataScope.loading,
  updating: storeState.sysRoleDataScope.updating,
  updateSuccess: storeState.sysRoleDataScope.updateSuccess
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
)(SysRoleDataScopeUpdate);
