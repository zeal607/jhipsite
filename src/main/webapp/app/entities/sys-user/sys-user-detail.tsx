import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-user.reducer';
import { ISysUser } from 'app/shared/model/sys-user.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysUserDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysUserDetail extends React.Component<ISysUserDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysUserEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysUser.detail.title">SysUser</Translate> [<b>{sysUserEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="userCode">
                <Translate contentKey="jhipsiteApp.sysUser.userCode">User Code</Translate>
              </span>
              <UncontrolledTooltip target="userCode">
                <Translate contentKey="jhipsiteApp.sysUser.help.userCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.userCode}</dd>
            <dt>
              <span id="loginCode">
                <Translate contentKey="jhipsiteApp.sysUser.loginCode">Login Code</Translate>
              </span>
              <UncontrolledTooltip target="loginCode">
                <Translate contentKey="jhipsiteApp.sysUser.help.loginCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.loginCode}</dd>
            <dt>
              <span id="userName">
                <Translate contentKey="jhipsiteApp.sysUser.userName">User Name</Translate>
              </span>
              <UncontrolledTooltip target="userName">
                <Translate contentKey="jhipsiteApp.sysUser.help.userName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.userName}</dd>
            <dt>
              <span id="password">
                <Translate contentKey="jhipsiteApp.sysUser.password">Password</Translate>
              </span>
              <UncontrolledTooltip target="password">
                <Translate contentKey="jhipsiteApp.sysUser.help.password" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.password}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="jhipsiteApp.sysUser.email">Email</Translate>
              </span>
              <UncontrolledTooltip target="email">
                <Translate contentKey="jhipsiteApp.sysUser.help.email" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.email}</dd>
            <dt>
              <span id="mobile">
                <Translate contentKey="jhipsiteApp.sysUser.mobile">Mobile</Translate>
              </span>
              <UncontrolledTooltip target="mobile">
                <Translate contentKey="jhipsiteApp.sysUser.help.mobile" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.mobile}</dd>
            <dt>
              <span id="phone">
                <Translate contentKey="jhipsiteApp.sysUser.phone">Phone</Translate>
              </span>
              <UncontrolledTooltip target="phone">
                <Translate contentKey="jhipsiteApp.sysUser.help.phone" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.phone}</dd>
            <dt>
              <span id="sex">
                <Translate contentKey="jhipsiteApp.sysUser.sex">Sex</Translate>
              </span>
              <UncontrolledTooltip target="sex">
                <Translate contentKey="jhipsiteApp.sysUser.help.sex" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.sex}</dd>
            <dt>
              <span id="avatar">
                <Translate contentKey="jhipsiteApp.sysUser.avatar">Avatar</Translate>
              </span>
              <UncontrolledTooltip target="avatar">
                <Translate contentKey="jhipsiteApp.sysUser.help.avatar" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.avatar}</dd>
            <dt>
              <span id="sign">
                <Translate contentKey="jhipsiteApp.sysUser.sign">Sign</Translate>
              </span>
              <UncontrolledTooltip target="sign">
                <Translate contentKey="jhipsiteApp.sysUser.help.sign" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.sign}</dd>
            <dt>
              <span id="wxOpenid">
                <Translate contentKey="jhipsiteApp.sysUser.wxOpenid">Wx Openid</Translate>
              </span>
              <UncontrolledTooltip target="wxOpenid">
                <Translate contentKey="jhipsiteApp.sysUser.help.wxOpenid" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.wxOpenid}</dd>
            <dt>
              <span id="mobileImei">
                <Translate contentKey="jhipsiteApp.sysUser.mobileImei">Mobile Imei</Translate>
              </span>
              <UncontrolledTooltip target="mobileImei">
                <Translate contentKey="jhipsiteApp.sysUser.help.mobileImei" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.mobileImei}</dd>
            <dt>
              <span id="userType">
                <Translate contentKey="jhipsiteApp.sysUser.userType">User Type</Translate>
              </span>
              <UncontrolledTooltip target="userType">
                <Translate contentKey="jhipsiteApp.sysUser.help.userType" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.userType}</dd>
            <dt>
              <span id="refCode">
                <Translate contentKey="jhipsiteApp.sysUser.refCode">Ref Code</Translate>
              </span>
              <UncontrolledTooltip target="refCode">
                <Translate contentKey="jhipsiteApp.sysUser.help.refCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.refCode}</dd>
            <dt>
              <span id="refName">
                <Translate contentKey="jhipsiteApp.sysUser.refName">Ref Name</Translate>
              </span>
              <UncontrolledTooltip target="refName">
                <Translate contentKey="jhipsiteApp.sysUser.help.refName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.refName}</dd>
            <dt>
              <span id="lastLoginIp">
                <Translate contentKey="jhipsiteApp.sysUser.lastLoginIp">Last Login Ip</Translate>
              </span>
              <UncontrolledTooltip target="lastLoginIp">
                <Translate contentKey="jhipsiteApp.sysUser.help.lastLoginIp" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.lastLoginIp}</dd>
            <dt>
              <span id="lastLoginDate">
                <Translate contentKey="jhipsiteApp.sysUser.lastLoginDate">Last Login Date</Translate>
              </span>
              <UncontrolledTooltip target="lastLoginDate">
                <Translate contentKey="jhipsiteApp.sysUser.help.lastLoginDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={sysUserEntity.lastLoginDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="freezeDate">
                <Translate contentKey="jhipsiteApp.sysUser.freezeDate">Freeze Date</Translate>
              </span>
              <UncontrolledTooltip target="freezeDate">
                <Translate contentKey="jhipsiteApp.sysUser.help.freezeDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={sysUserEntity.freezeDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="freezeCause">
                <Translate contentKey="jhipsiteApp.sysUser.freezeCause">Freeze Cause</Translate>
              </span>
              <UncontrolledTooltip target="freezeCause">
                <Translate contentKey="jhipsiteApp.sysUser.help.freezeCause" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.freezeCause}</dd>
            <dt>
              <span id="userSort">
                <Translate contentKey="jhipsiteApp.sysUser.userSort">User Sort</Translate>
              </span>
              <UncontrolledTooltip target="userSort">
                <Translate contentKey="jhipsiteApp.sysUser.help.userSort" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.userSort}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="jhipsiteApp.sysUser.status">Status</Translate>
              </span>
              <UncontrolledTooltip target="status">
                <Translate contentKey="jhipsiteApp.sysUser.help.status" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.status}</dd>
            <dt>
              <span id="remarks">
                <Translate contentKey="jhipsiteApp.sysUser.remarks">Remarks</Translate>
              </span>
              <UncontrolledTooltip target="remarks">
                <Translate contentKey="jhipsiteApp.sysUser.help.remarks" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysUserEntity.remarks}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-user" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-user/${sysUserEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysUser }: IRootState) => ({
  sysUserEntity: sysUser.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysUserDetail);
