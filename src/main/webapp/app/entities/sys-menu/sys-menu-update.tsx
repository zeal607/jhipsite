import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-menu.reducer';
import { ISysMenu } from 'app/shared/model/sys-menu.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysMenuUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysMenuUpdateState {
  isNew: boolean;
}

export class SysMenuUpdate extends React.Component<ISysMenuUpdateProps, ISysMenuUpdateState> {
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
      const { sysMenuEntity } = this.props;
      const entity = {
        ...sysMenuEntity,
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
    this.props.history.push('/entity/sys-menu');
  };

  render() {
    const { sysMenuEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysMenu.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysMenu.home.createOrEditLabel">Create or edit a SysMenu</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysMenuEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-menu-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-menu-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="menuCodeLabel" for="sys-menu-menuCode">
                    <Translate contentKey="jhipsiteApp.sysMenu.menuCode">Menu Code</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-menuCode"
                    type="text"
                    name="menuCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="menuCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.menuCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="parentCodeLabel" for="sys-menu-parentCode">
                    <Translate contentKey="jhipsiteApp.sysMenu.parentCode">Parent Code</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-parentCode"
                    type="text"
                    name="parentCode"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="parentCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.parentCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="parentCodesLabel" for="sys-menu-parentCodes">
                    <Translate contentKey="jhipsiteApp.sysMenu.parentCodes">Parent Codes</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-parentCodes"
                    type="text"
                    name="parentCodes"
                    validate={{
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="parentCodesLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.parentCodes" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeSortLabel" for="sys-menu-treeSort">
                    <Translate contentKey="jhipsiteApp.sysMenu.treeSort">Tree Sort</Translate>
                  </Label>
                  <AvField id="sys-menu-treeSort" type="string" className="form-control" name="treeSort" />
                  <UncontrolledTooltip target="treeSortLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.treeSort" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeSortsLabel" for="sys-menu-treeSorts">
                    <Translate contentKey="jhipsiteApp.sysMenu.treeSorts">Tree Sorts</Translate>
                  </Label>
                  <AvField id="sys-menu-treeSorts" type="string" className="form-control" name="treeSorts" />
                  <UncontrolledTooltip target="treeSortsLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.treeSorts" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeLeafLabel" check>
                    <AvInput id="sys-menu-treeLeaf" type="checkbox" className="form-control" name="treeLeaf" />
                    <Translate contentKey="jhipsiteApp.sysMenu.treeLeaf">Tree Leaf</Translate>
                  </Label>
                  <UncontrolledTooltip target="treeLeafLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.treeLeaf" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeLevelLabel" for="sys-menu-treeLevel">
                    <Translate contentKey="jhipsiteApp.sysMenu.treeLevel">Tree Level</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-treeLevel"
                    type="string"
                    className="form-control"
                    name="treeLevel"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                  <UncontrolledTooltip target="treeLevelLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.treeLevel" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeNamesLabel" for="sys-menu-treeNames">
                    <Translate contentKey="jhipsiteApp.sysMenu.treeNames">Tree Names</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-treeNames"
                    type="text"
                    name="treeNames"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="treeNamesLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.treeNames" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="menuNameLabel" for="sys-menu-menuName">
                    <Translate contentKey="jhipsiteApp.sysMenu.menuName">Menu Name</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-menuName"
                    type="text"
                    name="menuName"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="menuNameLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.menuName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="menuTypeLabel" for="sys-menu-menuType">
                    <Translate contentKey="jhipsiteApp.sysMenu.menuType">Menu Type</Translate>
                  </Label>
                  <AvInput
                    id="sys-menu-menuType"
                    type="select"
                    className="form-control"
                    name="menuType"
                    value={(!isNew && sysMenuEntity.menuType) || 'MENU'}
                  >
                    <option value="MENU">{translate('jhipsiteApp.MenuType.MENU')}</option>
                    <option value="PERMISSION">{translate('jhipsiteApp.MenuType.PERMISSION')}</option>
                    <option value="DEV">{translate('jhipsiteApp.MenuType.DEV')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="menuTypeLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.menuType" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="menuHrefLabel" for="sys-menu-menuHref">
                    <Translate contentKey="jhipsiteApp.sysMenu.menuHref">Menu Href</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-menuHref"
                    type="text"
                    name="menuHref"
                    validate={{
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="menuHrefLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.menuHref" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="menuIconLabel" for="sys-menu-menuIcon">
                    <Translate contentKey="jhipsiteApp.sysMenu.menuIcon">Menu Icon</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-menuIcon"
                    type="text"
                    name="menuIcon"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="menuIconLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.menuIcon" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="menuTitleLabel" for="sys-menu-menuTitle">
                    <Translate contentKey="jhipsiteApp.sysMenu.menuTitle">Menu Title</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-menuTitle"
                    type="text"
                    name="menuTitle"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="menuTitleLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.menuTitle" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="permissionLabel" for="sys-menu-permission">
                    <Translate contentKey="jhipsiteApp.sysMenu.permission">Permission</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-permission"
                    type="text"
                    name="permission"
                    validate={{
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="permissionLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.permission" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="menuSortLabel" for="sys-menu-menuSort">
                    <Translate contentKey="jhipsiteApp.sysMenu.menuSort">Menu Sort</Translate>
                  </Label>
                  <AvField id="sys-menu-menuSort" type="string" className="form-control" name="menuSort" />
                  <UncontrolledTooltip target="menuSortLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.menuSort" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isShowLabel" check>
                    <AvInput id="sys-menu-isShow" type="checkbox" className="form-control" name="isShow" />
                    <Translate contentKey="jhipsiteApp.sysMenu.isShow">Is Show</Translate>
                  </Label>
                  <UncontrolledTooltip target="isShowLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.isShow" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="sysCodeLabel" for="sys-menu-sysCode">
                    <Translate contentKey="jhipsiteApp.sysMenu.sysCode">Sys Code</Translate>
                  </Label>
                  <AvInput
                    id="sys-menu-sysCode"
                    type="select"
                    className="form-control"
                    name="sysCode"
                    value={(!isNew && sysMenuEntity.sysCode) || 'WEB'}
                  >
                    <option value="WEB">{translate('jhipsiteApp.SysType.WEB')}</option>
                    <option value="MOBILE">{translate('jhipsiteApp.SysType.MOBILE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="sysCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.sysCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="sys-menu-status">
                    <Translate contentKey="jhipsiteApp.sysMenu.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="sys-menu-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && sysMenuEntity.status) || 'NORMAL'}
                  >
                    <option value="NORMAL">{translate('jhipsiteApp.MenuStatusType.NORMAL')}</option>
                    <option value="DELETE">{translate('jhipsiteApp.MenuStatusType.DELETE')}</option>
                    <option value="DISABLE">{translate('jhipsiteApp.MenuStatusType.DISABLE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="statusLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.status" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="remarksLabel" for="sys-menu-remarks">
                    <Translate contentKey="jhipsiteApp.sysMenu.remarks">Remarks</Translate>
                  </Label>
                  <AvField
                    id="sys-menu-remarks"
                    type="text"
                    name="remarks"
                    validate={{
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                  <UncontrolledTooltip target="remarksLabel">
                    <Translate contentKey="jhipsiteApp.sysMenu.help.remarks" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-menu" replace color="info">
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
  sysMenuEntity: storeState.sysMenu.entity,
  loading: storeState.sysMenu.loading,
  updating: storeState.sysMenu.updating,
  updateSuccess: storeState.sysMenu.updateSuccess
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
)(SysMenuUpdate);
