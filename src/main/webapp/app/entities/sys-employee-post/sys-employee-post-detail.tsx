import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-employee-post.reducer';
import { ISysEmployeePost } from 'app/shared/model/sys-employee-post.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysEmployeePostDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysEmployeePostDetail extends React.Component<ISysEmployeePostDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysEmployeePostEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysEmployeePost.detail.title">SysEmployeePost</Translate> [<b>{sysEmployeePostEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sysEmployeeId">
                <Translate contentKey="jhipsiteApp.sysEmployeePost.sysEmployeeId">Sys Employee Id</Translate>
              </span>
              <UncontrolledTooltip target="sysEmployeeId">
                <Translate contentKey="jhipsiteApp.sysEmployeePost.help.sysEmployeeId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeePostEntity.sysEmployeeId}</dd>
            <dt>
              <span id="sysPostId">
                <Translate contentKey="jhipsiteApp.sysEmployeePost.sysPostId">Sys Post Id</Translate>
              </span>
              <UncontrolledTooltip target="sysPostId">
                <Translate contentKey="jhipsiteApp.sysEmployeePost.help.sysPostId" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysEmployeePostEntity.sysPostId}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-employee-post" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-employee-post/${sysEmployeePostEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysEmployeePost }: IRootState) => ({
  sysEmployeePostEntity: sysEmployeePost.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysEmployeePostDetail);
