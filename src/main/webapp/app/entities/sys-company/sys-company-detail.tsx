import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-company.reducer';
import { ISysCompany } from 'app/shared/model/sys-company.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysCompanyDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SysCompanyDetail extends React.Component<ISysCompanyDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sysCompanyEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsiteApp.sysCompany.detail.title">SysCompany</Translate> [<b>{sysCompanyEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="companyCode">
                <Translate contentKey="jhipsiteApp.sysCompany.companyCode">Company Code</Translate>
              </span>
              <UncontrolledTooltip target="companyCode">
                <Translate contentKey="jhipsiteApp.sysCompany.help.companyCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.companyCode}</dd>
            <dt>
              <span id="parentCode">
                <Translate contentKey="jhipsiteApp.sysCompany.parentCode">Parent Code</Translate>
              </span>
              <UncontrolledTooltip target="parentCode">
                <Translate contentKey="jhipsiteApp.sysCompany.help.parentCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.parentCode}</dd>
            <dt>
              <span id="parentCodes">
                <Translate contentKey="jhipsiteApp.sysCompany.parentCodes">Parent Codes</Translate>
              </span>
              <UncontrolledTooltip target="parentCodes">
                <Translate contentKey="jhipsiteApp.sysCompany.help.parentCodes" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.parentCodes}</dd>
            <dt>
              <span id="treeSort">
                <Translate contentKey="jhipsiteApp.sysCompany.treeSort">Tree Sort</Translate>
              </span>
              <UncontrolledTooltip target="treeSort">
                <Translate contentKey="jhipsiteApp.sysCompany.help.treeSort" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.treeSort}</dd>
            <dt>
              <span id="treeSorts">
                <Translate contentKey="jhipsiteApp.sysCompany.treeSorts">Tree Sorts</Translate>
              </span>
              <UncontrolledTooltip target="treeSorts">
                <Translate contentKey="jhipsiteApp.sysCompany.help.treeSorts" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.treeSorts}</dd>
            <dt>
              <span id="treeLeaf">
                <Translate contentKey="jhipsiteApp.sysCompany.treeLeaf">Tree Leaf</Translate>
              </span>
              <UncontrolledTooltip target="treeLeaf">
                <Translate contentKey="jhipsiteApp.sysCompany.help.treeLeaf" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.treeLeaf ? 'true' : 'false'}</dd>
            <dt>
              <span id="treeLevel">
                <Translate contentKey="jhipsiteApp.sysCompany.treeLevel">Tree Level</Translate>
              </span>
              <UncontrolledTooltip target="treeLevel">
                <Translate contentKey="jhipsiteApp.sysCompany.help.treeLevel" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.treeLevel}</dd>
            <dt>
              <span id="treeNames">
                <Translate contentKey="jhipsiteApp.sysCompany.treeNames">Tree Names</Translate>
              </span>
              <UncontrolledTooltip target="treeNames">
                <Translate contentKey="jhipsiteApp.sysCompany.help.treeNames" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.treeNames}</dd>
            <dt>
              <span id="viewCode">
                <Translate contentKey="jhipsiteApp.sysCompany.viewCode">View Code</Translate>
              </span>
              <UncontrolledTooltip target="viewCode">
                <Translate contentKey="jhipsiteApp.sysCompany.help.viewCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.viewCode}</dd>
            <dt>
              <span id="companyName">
                <Translate contentKey="jhipsiteApp.sysCompany.companyName">Company Name</Translate>
              </span>
              <UncontrolledTooltip target="companyName">
                <Translate contentKey="jhipsiteApp.sysCompany.help.companyName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.companyName}</dd>
            <dt>
              <span id="fullName">
                <Translate contentKey="jhipsiteApp.sysCompany.fullName">Full Name</Translate>
              </span>
              <UncontrolledTooltip target="fullName">
                <Translate contentKey="jhipsiteApp.sysCompany.help.fullName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.fullName}</dd>
            <dt>
              <span id="areaCode">
                <Translate contentKey="jhipsiteApp.sysCompany.areaCode">Area Code</Translate>
              </span>
              <UncontrolledTooltip target="areaCode">
                <Translate contentKey="jhipsiteApp.sysCompany.help.areaCode" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.areaCode}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="jhipsiteApp.sysCompany.status">Status</Translate>
              </span>
              <UncontrolledTooltip target="status">
                <Translate contentKey="jhipsiteApp.sysCompany.help.status" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.status}</dd>
            <dt>
              <span id="remarks">
                <Translate contentKey="jhipsiteApp.sysCompany.remarks">Remarks</Translate>
              </span>
              <UncontrolledTooltip target="remarks">
                <Translate contentKey="jhipsiteApp.sysCompany.help.remarks" />
              </UncontrolledTooltip>
            </dt>
            <dd>{sysCompanyEntity.remarks}</dd>
          </dl>
          <Button tag={Link} to="/entity/sys-company" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/sys-company/${sysCompanyEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sysCompany }: IRootState) => ({
  sysCompanyEntity: sysCompany.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SysCompanyDetail);
