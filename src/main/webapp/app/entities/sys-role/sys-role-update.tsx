import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-role.reducer';
import { ISysRole } from 'app/shared/model/sys-role.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysRoleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysRoleUpdateState {
  isNew: boolean;
}

export class SysRoleUpdate extends React.Component<ISysRoleUpdateProps, ISysRoleUpdateState> {
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
      const { sysRoleEntity } = this.props;
      const entity = {
        ...sysRoleEntity,
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
    this.props.history.push('/entity/sys-role');
  };

  render() {
    const { sysRoleEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysRole.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysRole.home.createOrEditLabel">Create or edit a SysRole</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysRoleEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-role-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-role-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="roleCodeLabel" for="sys-role-roleCode">
                    <Translate contentKey="jhipsiteApp.sysRole.roleCode">Role Code</Translate>
                  </Label>
                  <AvField
                    id="sys-role-roleCode"
                    type="text"
                    name="roleCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="roleCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysRole.help.roleCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="roleNameLabel" for="sys-role-roleName">
                    <Translate contentKey="jhipsiteApp.sysRole.roleName">Role Name</Translate>
                  </Label>
                  <AvField
                    id="sys-role-roleName"
                    type="text"
                    name="roleName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="roleNameLabel">
                    <Translate contentKey="jhipsiteApp.sysRole.help.roleName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="roleTypeLabel" for="sys-role-roleType">
                    <Translate contentKey="jhipsiteApp.sysRole.roleType">Role Type</Translate>
                  </Label>
                  <AvInput
                    id="sys-role-roleType"
                    type="select"
                    className="form-control"
                    name="roleType"
                    value={(!isNew && sysRoleEntity.roleType) || 'ORGANIZARION'}
                  >
                    <option value="ORGANIZARION">{translate('jhipsiteApp.RoleType.ORGANIZARION')}</option>
                    <option value="USER">{translate('jhipsiteApp.RoleType.USER')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="roleTypeLabel">
                    <Translate contentKey="jhipsiteApp.sysRole.help.roleType" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="roleSortLabel" for="sys-role-roleSort">
                    <Translate contentKey="jhipsiteApp.sysRole.roleSort">Role Sort</Translate>
                  </Label>
                  <AvField id="sys-role-roleSort" type="string" className="form-control" name="roleSort" />
                  <UncontrolledTooltip target="roleSortLabel">
                    <Translate contentKey="jhipsiteApp.sysRole.help.roleSort" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSysLabel" check>
                    <AvInput id="sys-role-isSys" type="checkbox" className="form-control" name="isSys" />
                    <Translate contentKey="jhipsiteApp.sysRole.isSys">Is Sys</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSysLabel">
                    <Translate contentKey="jhipsiteApp.sysRole.help.isSys" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="dataScopeLabel" for="sys-role-dataScope">
                    <Translate contentKey="jhipsiteApp.sysRole.dataScope">Data Scope</Translate>
                  </Label>
                  <AvInput
                    id="sys-role-dataScope"
                    type="select"
                    className="form-control"
                    name="dataScope"
                    value={(!isNew && sysRoleEntity.dataScope) || 'NONE'}
                  >
                    <option value="NONE">{translate('jhipsiteApp.DataScopeType.NONE')}</option>
                    <option value="ALL">{translate('jhipsiteApp.DataScopeType.ALL')}</option>
                    <option value="CUSTOM">{translate('jhipsiteApp.DataScopeType.CUSTOM')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="dataScopeLabel">
                    <Translate contentKey="jhipsiteApp.sysRole.help.dataScope" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="bizScopeLabel" for="sys-role-bizScope">
                    <Translate contentKey="jhipsiteApp.sysRole.bizScope">Biz Scope</Translate>
                  </Label>
                  <AvField
                    id="sys-role-bizScope"
                    type="text"
                    name="bizScope"
                    validate={{
                      maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                    }}
                  />
                  <UncontrolledTooltip target="bizScopeLabel">
                    <Translate contentKey="jhipsiteApp.sysRole.help.bizScope" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="sys-role-status">
                    <Translate contentKey="jhipsiteApp.sysRole.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="sys-role-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && sysRoleEntity.status) || 'NORMAl'}
                  >
                    <option value="NORMAl">{translate('jhipsiteApp.RoleStatusType.NORMAl')}</option>
                    <option value="DELETE">{translate('jhipsiteApp.RoleStatusType.DELETE')}</option>
                    <option value="DISABLE">{translate('jhipsiteApp.RoleStatusType.DISABLE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="statusLabel">
                    <Translate contentKey="jhipsiteApp.sysRole.help.status" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="remarksLabel" for="sys-role-remarks">
                    <Translate contentKey="jhipsiteApp.sysRole.remarks">Remarks</Translate>
                  </Label>
                  <AvField
                    id="sys-role-remarks"
                    type="text"
                    name="remarks"
                    validate={{
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                  <UncontrolledTooltip target="remarksLabel">
                    <Translate contentKey="jhipsiteApp.sysRole.help.remarks" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-role" replace color="info">
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
  sysRoleEntity: storeState.sysRole.entity,
  loading: storeState.sysRole.loading,
  updating: storeState.sysRole.updating,
  updateSuccess: storeState.sysRole.updateSuccess
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
)(SysRoleUpdate);
