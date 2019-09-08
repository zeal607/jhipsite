import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-user.reducer';
import { ISysUser } from 'app/shared/model/sys-user.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysUserUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysUserUpdateState {
  isNew: boolean;
}

export class SysUserUpdate extends React.Component<ISysUserUpdateProps, ISysUserUpdateState> {
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
    values.lastLoginDate = convertDateTimeToServer(values.lastLoginDate);
    values.freezeDate = convertDateTimeToServer(values.freezeDate);

    if (errors.length === 0) {
      const { sysUserEntity } = this.props;
      const entity = {
        ...sysUserEntity,
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
    this.props.history.push('/entity/sys-user');
  };

  render() {
    const { sysUserEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysUser.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysUser.home.createOrEditLabel">Create or edit a SysUser</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysUserEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-user-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-user-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="userCodeLabel" for="sys-user-userCode">
                    <Translate contentKey="jhipsiteApp.sysUser.userCode">User Code</Translate>
                  </Label>
                  <AvField
                    id="sys-user-userCode"
                    type="text"
                    name="userCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="userCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.userCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="loginCodeLabel" for="sys-user-loginCode">
                    <Translate contentKey="jhipsiteApp.sysUser.loginCode">Login Code</Translate>
                  </Label>
                  <AvField
                    id="sys-user-loginCode"
                    type="text"
                    name="loginCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="loginCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.loginCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="userNameLabel" for="sys-user-userName">
                    <Translate contentKey="jhipsiteApp.sysUser.userName">User Name</Translate>
                  </Label>
                  <AvField
                    id="sys-user-userName"
                    type="text"
                    name="userName"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="userNameLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.userName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="passwordLabel" for="sys-user-password">
                    <Translate contentKey="jhipsiteApp.sysUser.password">Password</Translate>
                  </Label>
                  <AvField
                    id="sys-user-password"
                    type="text"
                    name="password"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="passwordLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.password" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="sys-user-email">
                    <Translate contentKey="jhipsiteApp.sysUser.email">Email</Translate>
                  </Label>
                  <AvField
                    id="sys-user-email"
                    type="text"
                    name="email"
                    validate={{
                      maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                    }}
                  />
                  <UncontrolledTooltip target="emailLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.email" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="mobileLabel" for="sys-user-mobile">
                    <Translate contentKey="jhipsiteApp.sysUser.mobile">Mobile</Translate>
                  </Label>
                  <AvField
                    id="sys-user-mobile"
                    type="text"
                    name="mobile"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="mobileLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.mobile" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="phoneLabel" for="sys-user-phone">
                    <Translate contentKey="jhipsiteApp.sysUser.phone">Phone</Translate>
                  </Label>
                  <AvField
                    id="sys-user-phone"
                    type="text"
                    name="phone"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="phoneLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.phone" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="sexLabel" for="sys-user-sex">
                    <Translate contentKey="jhipsiteApp.sysUser.sex">Sex</Translate>
                  </Label>
                  <AvInput
                    id="sys-user-sex"
                    type="select"
                    className="form-control"
                    name="sex"
                    value={(!isNew && sysUserEntity.sex) || 'MALE'}
                  >
                    <option value="MALE">{translate('jhipsiteApp.GenderType.MALE')}</option>
                    <option value="FEMALE">{translate('jhipsiteApp.GenderType.FEMALE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="sexLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.sex" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="avatarLabel" for="sys-user-avatar">
                    <Translate contentKey="jhipsiteApp.sysUser.avatar">Avatar</Translate>
                  </Label>
                  <AvField
                    id="sys-user-avatar"
                    type="text"
                    name="avatar"
                    validate={{
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="avatarLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.avatar" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="signLabel" for="sys-user-sign">
                    <Translate contentKey="jhipsiteApp.sysUser.sign">Sign</Translate>
                  </Label>
                  <AvField
                    id="sys-user-sign"
                    type="text"
                    name="sign"
                    validate={{
                      maxLength: { value: 200, errorMessage: translate('entity.validation.maxlength', { max: 200 }) }
                    }}
                  />
                  <UncontrolledTooltip target="signLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.sign" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="wxOpenidLabel" for="sys-user-wxOpenid">
                    <Translate contentKey="jhipsiteApp.sysUser.wxOpenid">Wx Openid</Translate>
                  </Label>
                  <AvField
                    id="sys-user-wxOpenid"
                    type="text"
                    name="wxOpenid"
                    validate={{
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="wxOpenidLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.wxOpenid" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="mobileImeiLabel" for="sys-user-mobileImei">
                    <Translate contentKey="jhipsiteApp.sysUser.mobileImei">Mobile Imei</Translate>
                  </Label>
                  <AvField
                    id="sys-user-mobileImei"
                    type="text"
                    name="mobileImei"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="mobileImeiLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.mobileImei" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="userTypeLabel" for="sys-user-userType">
                    <Translate contentKey="jhipsiteApp.sysUser.userType">User Type</Translate>
                  </Label>
                  <AvInput
                    id="sys-user-userType"
                    type="select"
                    className="form-control"
                    name="userType"
                    value={(!isNew && sysUserEntity.userType) || 'EMPLOYEE'}
                  >
                    <option value="EMPLOYEE">{translate('jhipsiteApp.UserType.EMPLOYEE')}</option>
                    <option value="MEMBER">{translate('jhipsiteApp.UserType.MEMBER')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="userTypeLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.userType" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="refCodeLabel" for="sys-user-refCode">
                    <Translate contentKey="jhipsiteApp.sysUser.refCode">Ref Code</Translate>
                  </Label>
                  <AvField
                    id="sys-user-refCode"
                    type="text"
                    name="refCode"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="refCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.refCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="refNameLabel" for="sys-user-refName">
                    <Translate contentKey="jhipsiteApp.sysUser.refName">Ref Name</Translate>
                  </Label>
                  <AvField
                    id="sys-user-refName"
                    type="text"
                    name="refName"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="refNameLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.refName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="lastLoginIpLabel" for="sys-user-lastLoginIp">
                    <Translate contentKey="jhipsiteApp.sysUser.lastLoginIp">Last Login Ip</Translate>
                  </Label>
                  <AvField
                    id="sys-user-lastLoginIp"
                    type="text"
                    name="lastLoginIp"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="lastLoginIpLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.lastLoginIp" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="lastLoginDateLabel" for="sys-user-lastLoginDate">
                    <Translate contentKey="jhipsiteApp.sysUser.lastLoginDate">Last Login Date</Translate>
                  </Label>
                  <AvInput
                    id="sys-user-lastLoginDate"
                    type="datetime-local"
                    className="form-control"
                    name="lastLoginDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.sysUserEntity.lastLoginDate)}
                  />
                  <UncontrolledTooltip target="lastLoginDateLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.lastLoginDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="freezeDateLabel" for="sys-user-freezeDate">
                    <Translate contentKey="jhipsiteApp.sysUser.freezeDate">Freeze Date</Translate>
                  </Label>
                  <AvInput
                    id="sys-user-freezeDate"
                    type="datetime-local"
                    className="form-control"
                    name="freezeDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.sysUserEntity.freezeDate)}
                  />
                  <UncontrolledTooltip target="freezeDateLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.freezeDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="freezeCauseLabel" for="sys-user-freezeCause">
                    <Translate contentKey="jhipsiteApp.sysUser.freezeCause">Freeze Cause</Translate>
                  </Label>
                  <AvField
                    id="sys-user-freezeCause"
                    type="text"
                    name="freezeCause"
                    validate={{
                      maxLength: { value: 200, errorMessage: translate('entity.validation.maxlength', { max: 200 }) }
                    }}
                  />
                  <UncontrolledTooltip target="freezeCauseLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.freezeCause" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="userSortLabel" for="sys-user-userSort">
                    <Translate contentKey="jhipsiteApp.sysUser.userSort">User Sort</Translate>
                  </Label>
                  <AvField id="sys-user-userSort" type="string" className="form-control" name="userSort" />
                  <UncontrolledTooltip target="userSortLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.userSort" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="sys-user-status">
                    <Translate contentKey="jhipsiteApp.sysUser.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="sys-user-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && sysUserEntity.status) || 'NORMAL'}
                  >
                    <option value="NORMAL">{translate('jhipsiteApp.UserStatusType.NORMAL')}</option>
                    <option value="DELETE">{translate('jhipsiteApp.UserStatusType.DELETE')}</option>
                    <option value="DISABLE">{translate('jhipsiteApp.UserStatusType.DISABLE')}</option>
                    <option value="FREEZE">{translate('jhipsiteApp.UserStatusType.FREEZE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="statusLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.status" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="remarksLabel" for="sys-user-remarks">
                    <Translate contentKey="jhipsiteApp.sysUser.remarks">Remarks</Translate>
                  </Label>
                  <AvField
                    id="sys-user-remarks"
                    type="text"
                    name="remarks"
                    validate={{
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                  <UncontrolledTooltip target="remarksLabel">
                    <Translate contentKey="jhipsiteApp.sysUser.help.remarks" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-user" replace color="info">
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
  sysUserEntity: storeState.sysUser.entity,
  loading: storeState.sysUser.loading,
  updating: storeState.sysUser.updating,
  updateSuccess: storeState.sysUser.updateSuccess
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
)(SysUserUpdate);
