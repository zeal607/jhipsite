import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-user-data-scope.reducer';
import { ISysUserDataScope } from 'app/shared/model/sys-user-data-scope.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysUserDataScopeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysUserDataScopeUpdateState {
  isNew: boolean;
}

export class SysUserDataScopeUpdate extends React.Component<ISysUserDataScopeUpdateProps, ISysUserDataScopeUpdateState> {
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
      const { sysUserDataScopeEntity } = this.props;
      const entity = {
        ...sysUserDataScopeEntity,
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
    this.props.history.push('/entity/sys-user-data-scope');
  };

  render() {
    const { sysUserDataScopeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysUserDataScope.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysUserDataScope.home.createOrEditLabel">Create or edit a SysUserDataScope</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysUserDataScopeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-user-data-scope-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-user-data-scope-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="sysUserIdLabel" for="sys-user-data-scope-sysUserId">
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.sysUserId">Sys User Id</Translate>
                  </Label>
                  <AvField
                    id="sys-user-data-scope-sysUserId"
                    type="text"
                    name="sysUserId"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysUserIdLabel">
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.help.sysUserId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ctrlTypeLabel" for="sys-user-data-scope-ctrlType">
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.ctrlType">Ctrl Type</Translate>
                  </Label>
                  <AvInput
                    id="sys-user-data-scope-ctrlType"
                    type="select"
                    className="form-control"
                    name="ctrlType"
                    value={(!isNew && sysUserDataScopeEntity.ctrlType) || 'COMPANY'}
                  >
                    <option value="COMPANY">{translate('jhipsiteApp.ControlType.COMPANY')}</option>
                    <option value="OFFICE">{translate('jhipsiteApp.ControlType.OFFICE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="ctrlTypeLabel">
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.help.ctrlType" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ctrlDataLabel" for="sys-user-data-scope-ctrlData">
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.ctrlData">Ctrl Data</Translate>
                  </Label>
                  <AvField
                    id="sys-user-data-scope-ctrlData"
                    type="text"
                    name="ctrlData"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="ctrlDataLabel">
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.help.ctrlData" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ctrlPermiLabel" for="sys-user-data-scope-ctrlPermi">
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.ctrlPermi">Ctrl Permi</Translate>
                  </Label>
                  <AvField
                    id="sys-user-data-scope-ctrlPermi"
                    type="text"
                    name="ctrlPermi"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="ctrlPermiLabel">
                    <Translate contentKey="jhipsiteApp.sysUserDataScope.help.ctrlPermi" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-user-data-scope" replace color="info">
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
  sysUserDataScopeEntity: storeState.sysUserDataScope.entity,
  loading: storeState.sysUserDataScope.loading,
  updating: storeState.sysUserDataScope.updating,
  updateSuccess: storeState.sysUserDataScope.updateSuccess
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
)(SysUserDataScopeUpdate);
