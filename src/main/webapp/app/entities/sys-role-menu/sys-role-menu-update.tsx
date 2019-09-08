import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-role-menu.reducer';
import { ISysRoleMenu } from 'app/shared/model/sys-role-menu.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysRoleMenuUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysRoleMenuUpdateState {
  isNew: boolean;
}

export class SysRoleMenuUpdate extends React.Component<ISysRoleMenuUpdateProps, ISysRoleMenuUpdateState> {
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
      const { sysRoleMenuEntity } = this.props;
      const entity = {
        ...sysRoleMenuEntity,
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
    this.props.history.push('/entity/sys-role-menu');
  };

  render() {
    const { sysRoleMenuEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysRoleMenu.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysRoleMenu.home.createOrEditLabel">Create or edit a SysRoleMenu</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysRoleMenuEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-role-menu-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-role-menu-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="sysRoleIdLabel" for="sys-role-menu-sysRoleId">
                    <Translate contentKey="jhipsiteApp.sysRoleMenu.sysRoleId">Sys Role Id</Translate>
                  </Label>
                  <AvField
                    id="sys-role-menu-sysRoleId"
                    type="text"
                    name="sysRoleId"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysRoleIdLabel">
                    <Translate contentKey="jhipsiteApp.sysRoleMenu.help.sysRoleId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="sysMenuIdLabel" for="sys-role-menu-sysMenuId">
                    <Translate contentKey="jhipsiteApp.sysRoleMenu.sysMenuId">Sys Menu Id</Translate>
                  </Label>
                  <AvField
                    id="sys-role-menu-sysMenuId"
                    type="text"
                    name="sysMenuId"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="sysMenuIdLabel">
                    <Translate contentKey="jhipsiteApp.sysRoleMenu.help.sysMenuId" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-role-menu" replace color="info">
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
  sysRoleMenuEntity: storeState.sysRoleMenu.entity,
  loading: storeState.sysRoleMenu.loading,
  updating: storeState.sysRoleMenu.updating,
  updateSuccess: storeState.sysRoleMenu.updateSuccess
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
)(SysRoleMenuUpdate);
