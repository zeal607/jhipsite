import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-post.reducer';
import { ISysPost } from 'app/shared/model/sys-post.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysPostDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysPostDetail extends React.Component<ISysPostDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysPostEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysPost.detail.title">SysPost</Translate> [<b>{sysPostEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="postCode">
                <Translate contentKey="jhipsiteApp.sysPost.postCode">Post Code</Translate>
              </span>
              <UncontrolledTooltip target="postCode">
                <Translate contentKey="jhipsiteApp.sysPost.help.postCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysPostEntity.postCode}</dd>
            <dt>
              <span id="postName">
                <Translate contentKey="jhipsiteApp.sysPost.postName">Post Name</Translate>
              </span>
              <UncontrolledTooltip target="postName">
                <Translate contentKey="jhipsiteApp.sysPost.help.postName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysPostEntity.postName}</dd>
            <dt>
              <span id="postType">
                <Translate contentKey="jhipsiteApp.sysPost.postType">Post Type</Translate>
              </span>
              <UncontrolledTooltip target="postType">
                <Translate contentKey="jhipsiteApp.sysPost.help.postType" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysPostEntity.postType}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="jhipsiteApp.sysPost.status">Status</Translate>
              </span>
              <UncontrolledTooltip target="status">
                <Translate contentKey="jhipsiteApp.sysPost.help.status" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysPostEntity.status}</dd>
            <dt>
              <span id="remarks">
                <Translate contentKey="jhipsiteApp.sysPost.remarks">Remarks</Translate>
              </span>
              <UncontrolledTooltip target="remarks">
                <Translate contentKey="jhipsiteApp.sysPost.help.remarks" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysPostEntity.remarks}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-post" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-post/${sysPostEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysPost }: IRootState) => ({
  sysPostEntity: sysPost.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysPostDetail);
