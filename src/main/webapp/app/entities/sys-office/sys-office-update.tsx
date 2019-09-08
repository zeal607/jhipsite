import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-office.reducer';
import { ISysOffice } from 'app/shared/model/sys-office.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysOfficeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysOfficeUpdateState {
  isNew: boolean;
}

export class SysOfficeUpdate extends React.Component<ISysOfficeUpdateProps, ISysOfficeUpdateState> {
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
      const { sysOfficeEntity } = this.props;
      const entity = {
        ...sysOfficeEntity,
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
    this.props.history.push('/entity/sys-office');
  };

  render() {
    const { sysOfficeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysOffice.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysOffice.home.createOrEditLabel">Create or edit a SysOffice</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysOfficeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-office-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-office-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="officeCodeLabel" for="sys-office-officeCode">
                    <Translate contentKey="jhipsiteApp.sysOffice.officeCode">Office Code</Translate>
                  </Label>
                  <AvField
                    id="sys-office-officeCode"
                    type="text"
                    name="officeCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="officeCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.officeCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="parentCodeLabel" for="sys-office-parentCode">
                    <Translate contentKey="jhipsiteApp.sysOffice.parentCode">Parent Code</Translate>
                  </Label>
                  <AvField
                    id="sys-office-parentCode"
                    type="text"
                    name="parentCode"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="parentCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.parentCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="parentCodesLabel" for="sys-office-parentCodes">
                    <Translate contentKey="jhipsiteApp.sysOffice.parentCodes">Parent Codes</Translate>
                  </Label>
                  <AvField
                    id="sys-office-parentCodes"
                    type="text"
                    name="parentCodes"
                    validate={{
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="parentCodesLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.parentCodes" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeSortLabel" for="sys-office-treeSort">
                    <Translate contentKey="jhipsiteApp.sysOffice.treeSort">Tree Sort</Translate>
                  </Label>
                  <AvField id="sys-office-treeSort" type="string" className="form-control" name="treeSort" />
                  <UncontrolledTooltip target="treeSortLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.treeSort" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeSortsLabel" for="sys-office-treeSorts">
                    <Translate contentKey="jhipsiteApp.sysOffice.treeSorts">Tree Sorts</Translate>
                  </Label>
                  <AvField id="sys-office-treeSorts" type="string" className="form-control" name="treeSorts" />
                  <UncontrolledTooltip target="treeSortsLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.treeSorts" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeLeafLabel" check>
                    <AvInput id="sys-office-treeLeaf" type="checkbox" className="form-control" name="treeLeaf" />
                    <Translate contentKey="jhipsiteApp.sysOffice.treeLeaf">Tree Leaf</Translate>
                  </Label>
                  <UncontrolledTooltip target="treeLeafLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.treeLeaf" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeLevelLabel" for="sys-office-treeLevel">
                    <Translate contentKey="jhipsiteApp.sysOffice.treeLevel">Tree Level</Translate>
                  </Label>
                  <AvField
                    id="sys-office-treeLevel"
                    type="string"
                    className="form-control"
                    name="treeLevel"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                  <UncontrolledTooltip target="treeLevelLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.treeLevel" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeNamesLabel" for="sys-office-treeNames">
                    <Translate contentKey="jhipsiteApp.sysOffice.treeNames">Tree Names</Translate>
                  </Label>
                  <AvField
                    id="sys-office-treeNames"
                    type="text"
                    name="treeNames"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="treeNamesLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.treeNames" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="viewCodeLabel" for="sys-office-viewCode">
                    <Translate contentKey="jhipsiteApp.sysOffice.viewCode">View Code</Translate>
                  </Label>
                  <AvField
                    id="sys-office-viewCode"
                    type="text"
                    name="viewCode"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="viewCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.viewCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="officeNameLabel" for="sys-office-officeName">
                    <Translate contentKey="jhipsiteApp.sysOffice.officeName">Office Name</Translate>
                  </Label>
                  <AvField
                    id="sys-office-officeName"
                    type="text"
                    name="officeName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 200, errorMessage: translate('entity.validation.maxlength', { max: 200 }) }
                    }}
                  />
                  <UncontrolledTooltip target="officeNameLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.officeName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="fullNameLabel" for="sys-office-fullName">
                    <Translate contentKey="jhipsiteApp.sysOffice.fullName">Full Name</Translate>
                  </Label>
                  <AvField
                    id="sys-office-fullName"
                    type="text"
                    name="fullName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 200, errorMessage: translate('entity.validation.maxlength', { max: 200 }) }
                    }}
                  />
                  <UncontrolledTooltip target="fullNameLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.fullName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="officeTypeLabel" for="sys-office-officeType">
                    <Translate contentKey="jhipsiteApp.sysOffice.officeType">Office Type</Translate>
                  </Label>
                  <AvInput
                    id="sys-office-officeType"
                    type="select"
                    className="form-control"
                    name="officeType"
                    value={(!isNew && sysOfficeEntity.officeType) || 'NATIONAL'}
                  >
                    <option value="NATIONAL">{translate('jhipsiteApp.OfficeType.NATIONAL')}</option>
                    <option value="PROVINCIAL">{translate('jhipsiteApp.OfficeType.PROVINCIAL')}</option>
                    <option value="CITY">{translate('jhipsiteApp.OfficeType.CITY')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="officeTypeLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.officeType" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="leaderLabel" for="sys-office-leader">
                    <Translate contentKey="jhipsiteApp.sysOffice.leader">Leader</Translate>
                  </Label>
                  <AvField
                    id="sys-office-leader"
                    type="text"
                    name="leader"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="leaderLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.leader" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="phoneLabel" for="sys-office-phone">
                    <Translate contentKey="jhipsiteApp.sysOffice.phone">Phone</Translate>
                  </Label>
                  <AvField
                    id="sys-office-phone"
                    type="text"
                    name="phone"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="phoneLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.phone" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="addressLabel" for="sys-office-address">
                    <Translate contentKey="jhipsiteApp.sysOffice.address">Address</Translate>
                  </Label>
                  <AvField
                    id="sys-office-address"
                    type="text"
                    name="address"
                    validate={{
                      maxLength: { value: 255, errorMessage: translate('entity.validation.maxlength', { max: 255 }) }
                    }}
                  />
                  <UncontrolledTooltip target="addressLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.address" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="zipCodeLabel" for="sys-office-zipCode">
                    <Translate contentKey="jhipsiteApp.sysOffice.zipCode">Zip Code</Translate>
                  </Label>
                  <AvField
                    id="sys-office-zipCode"
                    type="text"
                    name="zipCode"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="zipCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.zipCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="sys-office-email">
                    <Translate contentKey="jhipsiteApp.sysOffice.email">Email</Translate>
                  </Label>
                  <AvField
                    id="sys-office-email"
                    type="text"
                    name="email"
                    validate={{
                      maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                    }}
                  />
                  <UncontrolledTooltip target="emailLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.email" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="remarksLabel" for="sys-office-remarks">
                    <Translate contentKey="jhipsiteApp.sysOffice.remarks">Remarks</Translate>
                  </Label>
                  <AvField
                    id="sys-office-remarks"
                    type="text"
                    name="remarks"
                    validate={{
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                  <UncontrolledTooltip target="remarksLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.remarks" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="sys-office-status">
                    <Translate contentKey="jhipsiteApp.sysOffice.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="sys-office-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && sysOfficeEntity.status) || 'NORMAL'}
                  >
                    <option value="NORMAL">{translate('jhipsiteApp.OfficeStatusType.NORMAL')}</option>
                    <option value="DELETE">{translate('jhipsiteApp.OfficeStatusType.DELETE')}</option>
                    <option value="DISABLE">{translate('jhipsiteApp.OfficeStatusType.DISABLE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="statusLabel">
                    <Translate contentKey="jhipsiteApp.sysOffice.help.status" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-office" replace color="info">
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
  sysOfficeEntity: storeState.sysOffice.entity,
  loading: storeState.sysOffice.loading,
  updating: storeState.sysOffice.updating,
  updateSuccess: storeState.sysOffice.updateSuccess
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
)(SysOfficeUpdate);
