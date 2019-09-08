import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-post.reducer';
import { ISysPost } from 'app/shared/model/sys-post.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysPostUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISysPostUpdateState {
  isNew: boolean;
}

export class SysPostUpdate extends React.Component<ISysPostUpdateProps, ISysPostUpdateState> {
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
      const { sysPostEntity } = this.props;
      const entity = {
        ...sysPostEntity,
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
    this.props.history.push('/entity/sys-post');
  };

  render() {
    const { sysPostEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsiteApp.sysPost.home.createOrEditLabel">
              <Translate contentKey="jhipsiteApp.sysPost.home.createOrEditLabel">Create or edit a SysPost</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sysPostEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="sys-post-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="sys-post-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="postCodeLabel" for="sys-post-postCode">
                    <Translate contentKey="jhipsiteApp.sysPost.postCode">Post Code</Translate>
                  </Label>
                  <AvField
                    id="sys-post-postCode"
                    type="text"
                    name="postCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="postCodeLabel">
                    <Translate contentKey="jhipsiteApp.sysPost.help.postCode" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="postNameLabel" for="sys-post-postName">
                    <Translate contentKey="jhipsiteApp.sysPost.postName">Post Name</Translate>
                  </Label>
                  <AvField
                    id="sys-post-postName"
                    type="text"
                    name="postName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                  <UncontrolledTooltip target="postNameLabel">
                    <Translate contentKey="jhipsiteApp.sysPost.help.postName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="postTypeLabel" for="sys-post-postType">
                    <Translate contentKey="jhipsiteApp.sysPost.postType">Post Type</Translate>
                  </Label>
                  <AvInput
                    id="sys-post-postType"
                    type="select"
                    className="form-control"
                    name="postType"
                    value={(!isNew && sysPostEntity.postType) || 'SENIOR'}
                  >
                    <option value="SENIOR">{translate('jhipsiteApp.PostType.SENIOR')}</option>
                    <option value="MIDDLE">{translate('jhipsiteApp.PostType.MIDDLE')}</option>
                    <option value="BASIC">{translate('jhipsiteApp.PostType.BASIC')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="postTypeLabel">
                    <Translate contentKey="jhipsiteApp.sysPost.help.postType" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="sys-post-status">
                    <Translate contentKey="jhipsiteApp.sysPost.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="sys-post-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && sysPostEntity.status) || 'NORMAL'}
                  >
                    <option value="NORMAL">{translate('jhipsiteApp.PostStatusType.NORMAL')}</option>
                    <option value="DELETE">{translate('jhipsiteApp.PostStatusType.DELETE')}</option>
                    <option value="DISABLE">{translate('jhipsiteApp.PostStatusType.DISABLE')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="statusLabel">
                    <Translate contentKey="jhipsiteApp.sysPost.help.status" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="remarksLabel" for="sys-post-remarks">
                    <Translate contentKey="jhipsiteApp.sysPost.remarks">Remarks</Translate>
                  </Label>
                  <AvField
                    id="sys-post-remarks"
                    type="text"
                    name="remarks"
                    validate={{
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                  <UncontrolledTooltip target="remarksLabel">
                    <Translate contentKey="jhipsiteApp.sysPost.help.remarks" />
                  </UncontrolledTooltip>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/sys-post" replace color="info">
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
  sysPostEntity: storeState.sysPost.entity,
  loading: storeState.sysPost.loading,
  updating: storeState.sysPost.updating,
  updateSuccess: storeState.sysPost.updateSuccess
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
)(SysPostUpdate);
