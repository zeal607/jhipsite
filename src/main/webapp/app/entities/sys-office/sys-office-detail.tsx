import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-office.reducer';
import { ISysOffice } from 'app/shared/model/sys-office.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysOfficeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysOfficeDetail extends React.Component<ISysOfficeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysOfficeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysOffice.detail.title">SysOffice</Translate> [<b>{sysOfficeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="officeCode">
                <Translate contentKey="jhipsiteApp.sysOffice.officeCode">Office Code</Translate>
              </span>
              <UncontrolledTooltip target="officeCode">
                <Translate contentKey="jhipsiteApp.sysOffice.help.officeCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.officeCode}</dd>
            <dt>
              <span id="parentCode">
                <Translate contentKey="jhipsiteApp.sysOffice.parentCode">Parent Code</Translate>
              </span>
              <UncontrolledTooltip target="parentCode">
                <Translate contentKey="jhipsiteApp.sysOffice.help.parentCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.parentCode}</dd>
            <dt>
              <span id="parentCodes">
                <Translate contentKey="jhipsiteApp.sysOffice.parentCodes">Parent Codes</Translate>
              </span>
              <UncontrolledTooltip target="parentCodes">
                <Translate contentKey="jhipsiteApp.sysOffice.help.parentCodes" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.parentCodes}</dd>
            <dt>
              <span id="treeSort">
                <Translate contentKey="jhipsiteApp.sysOffice.treeSort">Tree Sort</Translate>
              </span>
              <UncontrolledTooltip target="treeSort">
                <Translate contentKey="jhipsiteApp.sysOffice.help.treeSort" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.treeSort}</dd>
            <dt>
              <span id="treeSorts">
                <Translate contentKey="jhipsiteApp.sysOffice.treeSorts">Tree Sorts</Translate>
              </span>
              <UncontrolledTooltip target="treeSorts">
                <Translate contentKey="jhipsiteApp.sysOffice.help.treeSorts" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.treeSorts}</dd>
            <dt>
              <span id="treeLeaf">
                <Translate contentKey="jhipsiteApp.sysOffice.treeLeaf">Tree Leaf</Translate>
              </span>
              <UncontrolledTooltip target="treeLeaf">
                <Translate contentKey="jhipsiteApp.sysOffice.help.treeLeaf" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.treeLeaf ? 'true' : 'false'}</dd>
            <dt>
              <span id="treeLevel">
                <Translate contentKey="jhipsiteApp.sysOffice.treeLevel">Tree Level</Translate>
              </span>
              <UncontrolledTooltip target="treeLevel">
                <Translate contentKey="jhipsiteApp.sysOffice.help.treeLevel" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.treeLevel}</dd>
            <dt>
              <span id="treeNames">
                <Translate contentKey="jhipsiteApp.sysOffice.treeNames">Tree Names</Translate>
              </span>
              <UncontrolledTooltip target="treeNames">
                <Translate contentKey="jhipsiteApp.sysOffice.help.treeNames" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.treeNames}</dd>
            <dt>
              <span id="viewCode">
                <Translate contentKey="jhipsiteApp.sysOffice.viewCode">View Code</Translate>
              </span>
              <UncontrolledTooltip target="viewCode">
                <Translate contentKey="jhipsiteApp.sysOffice.help.viewCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.viewCode}</dd>
            <dt>
              <span id="officeName">
                <Translate contentKey="jhipsiteApp.sysOffice.officeName">Office Name</Translate>
              </span>
              <UncontrolledTooltip target="officeName">
                <Translate contentKey="jhipsiteApp.sysOffice.help.officeName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.officeName}</dd>
            <dt>
              <span id="fullName">
                <Translate contentKey="jhipsiteApp.sysOffice.fullName">Full Name</Translate>
              </span>
              <UncontrolledTooltip target="fullName">
                <Translate contentKey="jhipsiteApp.sysOffice.help.fullName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.fullName}</dd>
            <dt>
              <span id="officeType">
                <Translate contentKey="jhipsiteApp.sysOffice.officeType">Office Type</Translate>
              </span>
              <UncontrolledTooltip target="officeType">
                <Translate contentKey="jhipsiteApp.sysOffice.help.officeType" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.officeType}</dd>
            <dt>
              <span id="leader">
                <Translate contentKey="jhipsiteApp.sysOffice.leader">Leader</Translate>
              </span>
              <UncontrolledTooltip target="leader">
                <Translate contentKey="jhipsiteApp.sysOffice.help.leader" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.leader}</dd>
            <dt>
              <span id="phone">
                <Translate contentKey="jhipsiteApp.sysOffice.phone">Phone</Translate>
              </span>
              <UncontrolledTooltip target="phone">
                <Translate contentKey="jhipsiteApp.sysOffice.help.phone" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.phone}</dd>
            <dt>
              <span id="address">
                <Translate contentKey="jhipsiteApp.sysOffice.address">Address</Translate>
              </span>
              <UncontrolledTooltip target="address">
                <Translate contentKey="jhipsiteApp.sysOffice.help.address" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.address}</dd>
            <dt>
              <span id="zipCode">
                <Translate contentKey="jhipsiteApp.sysOffice.zipCode">Zip Code</Translate>
              </span>
              <UncontrolledTooltip target="zipCode">
                <Translate contentKey="jhipsiteApp.sysOffice.help.zipCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.zipCode}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="jhipsiteApp.sysOffice.email">Email</Translate>
              </span>
              <UncontrolledTooltip target="email">
                <Translate contentKey="jhipsiteApp.sysOffice.help.email" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.email}</dd>
            <dt>
              <span id="remarks">
                <Translate contentKey="jhipsiteApp.sysOffice.remarks">Remarks</Translate>
              </span>
              <UncontrolledTooltip target="remarks">
                <Translate contentKey="jhipsiteApp.sysOffice.help.remarks" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.remarks}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="jhipsiteApp.sysOffice.status">Status</Translate>
              </span>
              <UncontrolledTooltip target="status">
                <Translate contentKey="jhipsiteApp.sysOffice.help.status" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysOfficeEntity.status}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-office" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-office/${sysOfficeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysOffice }: IRootState) => ({
  sysOfficeEntity: sysOffice.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysOfficeDetail);
