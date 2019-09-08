import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-company.reducer';
import { ISysCompany } from 'app/shared/model/sys-company.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysCompanyUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysCompanyUpdateState {
  isNew: boolean;
}

export class SysCompanyUpdate extends React.Component<ISysCompanyUpdateProps, ISysCompanyUpdateState> {
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
      const { sysCompanyEntity } = this.props;
      const entity = {
        ...sysCompanyEntity,
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
    this.props.history.push('/entity/sys-company');
  };

  render() {
    const { sysCompanyEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysCompany.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysCompany.home.createOrEditLabel">Create or edit a SysCompany</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysCompanyEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-company-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-company-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="companyCodeLabel" for="sys-company-companyCode">
                    <Translate contentKey="jhipsiteApp.sysCompany.companyCode">Company Code</Translate>
                  </Label>
                  <AvField
                    id="sys-company-companyCode"
                    type="text"
                    name="companyCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="companyCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.companyCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="parentCodeLabel" for="sys-company-parentCode">
                    <Translate contentKey="jhipsiteApp.sysCompany.parentCode">Parent Code</Translate>
                  </Label>
                  <AvField
                    id="sys-company-parentCode"
                    type="text"
                    name="parentCode"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="parentCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.parentCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="parentCodesLabel" for="sys-company-parentCodes">
                    <Translate contentKey="jhipsiteApp.sysCompany.parentCodes">Parent Codes</Translate>
                  </Label>
                  <AvField
                    id="sys-company-parentCodes"
                    type="text"
                    name="parentCodes"
                    validate={{
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="parentCodesLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.parentCodes" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeSortLabel" for="sys-company-treeSort">
                    <Translate contentKey="jhipsiteApp.sysCompany.treeSort">Tree Sort</Translate>
                  </Label>
                  <AvField id="sys-company-treeSort" type="string" className="form-control" name="treeSort" />
                  <UncontrolledTooltip target="treeSortLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.treeSort" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeSortsLabel" for="sys-company-treeSorts">
                    <Translate contentKey="jhipsiteApp.sysCompany.treeSorts">Tree Sorts</Translate>
                  </Label>
                  <AvField id="sys-company-treeSorts" type="string" className="form-control" name="treeSorts" />
                  <UncontrolledTooltip target="treeSortsLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.treeSorts" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeLeafLabel" check>
                    <AvInput id="sys-company-treeLeaf" type="checkbox" className="form-control" name="treeLeaf" />
                    <Translate contentKey="jhipsiteApp.sysCompany.treeLeaf">Tree Leaf</Translate>
                  </Label>
                  <UncontrolledTooltip target="treeLeafLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.treeLeaf" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeLevelLabel" for="sys-company-treeLevel">
                    <Translate contentKey="jhipsiteApp.sysCompany.treeLevel">Tree Level</Translate>
                  </Label>
                  <AvField
                    id="sys-company-treeLevel"
                    type="string"
                    className="form-control"
                    name="treeLevel"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                  <UncontrolledTooltip target="treeLevelLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.treeLevel" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="treeNamesLabel" for="sys-company-treeNames">
                    <Translate contentKey="jhipsiteApp.sysCompany.treeNames">Tree Names</Translate>
                  </Label>
                  <AvField
                    id="sys-company-treeNames"
                    type="text"
                    name="treeNames"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 1000, errorMessage: translate('entity.validation.maxlength', { max: 1000 }) }
                    }}
                  />
                  <UncontrolledTooltip target="treeNamesLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.treeNames" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="viewCodeLabel" for="sys-company-viewCode">
                    <Translate contentKey="jhipsiteApp.sysCompany.viewCode">View Code</Translate>
                  </Label>
                  <AvField
                    id="sys-company-viewCode"
                    type="text"
                    name="viewCode"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="viewCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.viewCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="companyNameLabel" for="sys-company-companyName">
                    <Translate contentKey="jhipsiteApp.sysCompany.companyName">Company Name</Translate>
                  </Label>
                  <AvField
                    id="sys-company-companyName"
                    type="text"
                    name="companyName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 200, errorMessage: translate('entity.validation.maxlength', { max: 200 }) }
                    }}
                  />
                  <UncontrolledTooltip target="companyNameLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.companyName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="fullNameLabel" for="sys-company-fullName">
                    <Translate contentKey="jhipsiteApp.sysCompany.fullName">Full Name</Translate>
                  </Label>
                  <AvField
                    id="sys-company-fullName"
                    type="text"
                    name="fullName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 200, errorMessage: translate('entity.validation.maxlength', { max: 200 }) }
                    }}
                  />
                  <UncontrolledTooltip target="fullNameLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.fullName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="areaCodeLabel" for="sys-company-areaCode">
                    <Translate contentKey="jhipsiteApp.sysCompany.areaCode">Area Code</Translate>
                  </Label>
                  <AvField
                    id="sys-company-areaCode"
                    type="text"
                    name="areaCode"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="areaCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.areaCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="sys-company-status">
                    <Translate contentKey="jhipsiteApp.sysCompany.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="sys-company-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && sysCompanyEntity.status) || 'NORMAL'}
                  >
                    <option value="NORMAL">{translate('jhipsiteApp.CompanyStatusType.NORMAL')}</option>
                    <option value="DELETE">{translate('jhipsiteApp.CompanyStatusType.DELETE')}</option>
                    <option value="DISABLE">{translate('jhipsiteApp.CompanyStatusType.DISABLE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="statusLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.status" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="remarksLabel" for="sys-company-remarks">
                    <Translate contentKey="jhipsiteApp.sysCompany.remarks">Remarks</Translate>
                  </Label>
                  <AvField
                    id="sys-company-remarks"
                    type="text"
                    name="remarks"
                    validate={{
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                  <UncontrolledTooltip target="remarksLabel">
                    <Translate contentKey="jhipsiteApp.sysCompany.help.remarks" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-company" replace color="info">
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
  sysCompanyEntity: storeState.sysCompany.entity,
  loading: storeState.sysCompany.loading,
  updating: storeState.sysCompany.updating,
  updateSuccess: storeState.sysCompany.updateSuccess
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
)(SysCompanyUpdate);
