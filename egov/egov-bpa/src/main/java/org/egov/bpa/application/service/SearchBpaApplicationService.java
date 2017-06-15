/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.bpa.application.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.dto.SearchBpaApplicationForm;
import org.egov.bpa.service.BpaThirdPartyService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SearchBpaApplicationService  {
	
    private static final String SITE_DETAIL = "siteDetail";
    private static final String BPA_APPLICATION_DOT_SITE_DETAIL = "bpaApplication.siteDetail";


	   @Autowired
	    private BpaThirdPartyService bpaThirdPartyService;
	   
	   @PersistenceContext
	    private EntityManager entityManager;

	 

	    public Session getCurrentSession() {
	        return entityManager.unwrap(Session.class);
	    }

    @SuppressWarnings("unchecked")
    public List<SearchBpaApplicationForm> search(final SearchBpaApplicationForm bpaApplicationForm) {
        final Criteria criteria = buildSearchCriteria(bpaApplicationForm);
        List<SearchBpaApplicationForm> searchBpaApplicationFormList = new ArrayList<>();
        for (BpaApplication bpaApplication : (List<BpaApplication>) criteria.list()) {
            SearchBpaApplicationForm searchBpaApplicationForm = new SearchBpaApplicationForm();
            searchBpaApplicationForm.setId(bpaApplication.getId());
            searchBpaApplicationForm.setApplicationNumber(bpaApplication.getApplicationNumber());
            searchBpaApplicationForm.setBuildingplanapprovalnumber(bpaApplication.getBuildingplanapprovalnumber());
            searchBpaApplicationForm.setApplicationDate(bpaApplication.getApplicationDate());
            searchBpaApplicationForm.setAssessmentNumber(bpaApplication.getAssessmentNumber());
            searchBpaApplicationForm.setServiceType(
                    bpaApplication.getServiceType() != null ? bpaApplication.getServiceType().getDescription() : "");
            searchBpaApplicationForm.setStatus(bpaApplication.getStatus().getDescription());
            searchBpaApplicationForm.setPlanPermissionNumber(bpaApplication.getPlanPermissionNumber());
            searchBpaApplicationForm
                    .setApplicantName(bpaApplication.getOwner() != null ? bpaApplication.getOwner().getName() : "");
            searchBpaApplicationForm.setStakeHolderName(
                    !bpaApplication.getStakeHolder().isEmpty() && bpaApplication.getStakeHolder().get(0).getStakeHolder() != null
                            ? bpaApplication.getStakeHolder().get(0).getStakeHolder().getName() : "");
            if (bpaApplication.getState() != null && bpaApplication.getState().getOwnerPosition() != null) {
                searchBpaApplicationForm.setCurrentOwner(bpaThirdPartyService
                        .getUserPositionByPassingPosition(bpaApplication.getState().getOwnerPosition().getId()).getName());
                searchBpaApplicationForm.setPendingAction(bpaApplication.getState().getNextAction());
            }
            if (bpaApplication.getSiteDetail()!=null && bpaApplication.getSiteDetail().size()>0 && bpaApplication.getSiteDetail().get(0) != null) { 
                searchBpaApplicationForm.setElectionWard(bpaApplication.getSiteDetail().get(0).getElectionBoundary() != null
                        ? bpaApplication.getSiteDetail().get(0).getElectionBoundary().getName() : "");
                searchBpaApplicationForm.setWard(bpaApplication.getSiteDetail().get(0).getAdminBoundary() != null
                        ? bpaApplication.getSiteDetail().get(0).getAdminBoundary().getName() : "");
                searchBpaApplicationForm.setZone(bpaApplication.getSiteDetail().get(0).getLocationBoundary() != null
                        ? bpaApplication.getSiteDetail().get(0).getLocationBoundary().getName() : "");
            }
            searchBpaApplicationForm.setFeeCollected(bpaApplication.getDemand().getBaseDemand().compareTo(bpaApplication.getDemand().getAmtCollected()) <= 0 ? true : false);
            searchBpaApplicationFormList.add(searchBpaApplicationForm);
        }
        return searchBpaApplicationFormList;
    }

    public Criteria buildSearchCriteria(final SearchBpaApplicationForm searchBpaApplicationForm) {
        final Criteria criteria = getCurrentSession().createCriteria(BpaApplication.class, "bpaApplication");

        if (searchBpaApplicationForm.getApplicantName() != null) {
            criteria.createAlias("bpaApplication.owner", "owner")
                    .add(Restrictions.ilike("owner.applicantName", searchBpaApplicationForm.getApplicantName(),
                            MatchMode.ANYWHERE));
        }
        if (searchBpaApplicationForm.getApplicationNumber() != null) {
            criteria.add(Restrictions.eq("bpaApplication.applicationNumber", searchBpaApplicationForm.getApplicationNumber()));
        }
        if (searchBpaApplicationForm.getElectionWardId() != null && searchBpaApplicationForm.getWardId() != null) {
            criteria.createAlias(BPA_APPLICATION_DOT_SITE_DETAIL, SITE_DETAIL);
            criteria.createAlias("siteDetail.electionBoundary", "electionBoundary")
                    .add(Restrictions.eq("electionBoundary.id", searchBpaApplicationForm.getElectionWardId()));
            criteria.createAlias("siteDetail.adminBoundary", "adminBoundary")
                    .add(Restrictions.eq("adminBoundary.id", searchBpaApplicationForm.getWardId()));
        } else if (searchBpaApplicationForm.getElectionWardId() != null && searchBpaApplicationForm.getWardId() == null) {
            criteria.createAlias(BPA_APPLICATION_DOT_SITE_DETAIL, SITE_DETAIL)
                    .createAlias("siteDetail.electionBoundary", "electionBoundary")
                    .add(Restrictions.eq("electionBoundary.id", searchBpaApplicationForm.getElectionWardId()));
        } else if (searchBpaApplicationForm.getWardId() != null && searchBpaApplicationForm.getElectionWardId() == null) {
            criteria.createAlias(BPA_APPLICATION_DOT_SITE_DETAIL, SITE_DETAIL)
                    .createAlias("siteDetail.adminBoundary", "adminBoundary")
                    .add(Restrictions.eq("adminBoundary.id", searchBpaApplicationForm.getWardId()));
        }

        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria;
    }
  

}
