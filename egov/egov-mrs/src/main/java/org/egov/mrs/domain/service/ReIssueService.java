/* eGov suite of products aim to improve the internal efficiency,transparency,
   accountability and the service delivery of the government  organizations.

    Copyright (C) <2016>  eGovernments Foundation

    The updated version of eGov suite of products as by eGovernments Foundation
    is available at http://www.egovernments.org

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see http://www.gnu.org/licenses/ or
    http://www.gnu.org/licenses/gpl.html .

    In addition to the terms of the GPL license to be adhered to in using this
    program, the following additional terms are to be complied with:

        1) All versions of this program, verbatim or modified must carry this
           Legal Notice.

        2) Any misrepresentation of the origin of the material is prohibited. It
           is required that all modified versions of this material be marked in
           reasonable ways as different from the original version.

        3) This license does not grant any rights to any user of the program
           with regards to rights under trademark law for use of the trade names
           or trademarks of eGovernments Foundation.

  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.mrs.domain.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.egov.eis.service.EisCommonService;
import org.egov.eis.web.contract.WorkflowContainer;
import org.egov.infra.admin.master.entity.AppConfigValues;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.admin.master.service.AppConfigValueService;
import org.egov.infra.workflow.entity.State;
import org.egov.infra.workflow.entity.StateHistory;
import org.egov.mrs.application.MarriageConstants;
import org.egov.mrs.application.MarriageUtils;
import org.egov.mrs.application.service.MarriageCertificateService;
import org.egov.mrs.application.service.ReIssueDemandService;
import org.egov.mrs.application.service.workflow.RegistrationWorkflowService;
import org.egov.mrs.autonumber.MarriageRegistrationApplicationNumberGenerator;
import org.egov.mrs.domain.entity.MarriageCertificate;
import org.egov.mrs.domain.entity.MarriageDocument;
import org.egov.mrs.domain.entity.MarriageRegistration;
import org.egov.mrs.domain.entity.ReIssue;
import org.egov.mrs.domain.enums.MarriageCertificateType;
import org.egov.mrs.domain.repository.ReIssueRepository;
import org.egov.mrs.masters.service.MarriageFeeService;
import org.egov.mrs.service.es.ReIssueCertificateUpdateIndexesService;
import org.egov.pims.commons.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReIssueService {

    private final ReIssueRepository reIssueRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Qualifier("parentMessageSource")
    private MessageSource mrsMessageSource;

    @Autowired
    private RegistrationWorkflowService workflowService;

    @Autowired
    private ReIssueDemandService reIssueDemandService;

    @Autowired
    private MarriageRegistrationApplicationNumberGenerator marriageRegistrationApplicationNumberGenerator;
 
    @Autowired
    private MarriageDocumentService marriageDocumentService;

    @Autowired
    private MarriageApplicantService marriageApplicantService;
    
    @Autowired
    private MarriageUtils marriageUtils;
    
    @Autowired
    private EisCommonService eisCommonService;
    
    @Autowired
    private MarriageFeeService marriageFeeService;
    
    @Autowired
    private MarriageCertificateService marriageCertificateService;
    @Autowired
    private MarriageSmsAndEmailService marriageSmsAndEmailService;
    @Autowired
    private ReIssueCertificateUpdateIndexesService reiSsueUpdateIndexesService;
    @Autowired
    public ReIssueService(final ReIssueRepository reIssueRepository) {
        this.reIssueRepository = reIssueRepository;
    }

    @Autowired
    protected AppConfigValueService appConfigValuesService;
    
    @Transactional
    public void create(final ReIssue reIssue) {
        reIssueRepository.save(reIssue);
    }

    @Transactional
    public ReIssue update(final ReIssue reIssue) {
        return reIssueRepository.saveAndFlush(reIssue);
    }

    public ReIssue get(final Long id) {
        return reIssueRepository.findById(id);
    }

    @Transactional
    public String createReIssueApplication(final ReIssue reIssue, final WorkflowContainer workflowContainer) {

        if (StringUtils.isBlank(reIssue.getApplicationNo())) {
            reIssue.setApplicationNo(marriageRegistrationApplicationNumberGenerator.getNextReIssueApplicationNumber(reIssue));
            reIssue.setApplicationDate(new Date());
        }
        if(reIssue.getFeeCriteria()!=null)
            reIssue.setFeeCriteria(marriageFeeService.getFee(reIssue.getFeeCriteria().getId()));
        reIssue.setStatus(marriageUtils.getStatusByCodeAndModuleType(ReIssue.ReIssueStatus.CREATED.toString(), MarriageConstants.MODULE_NAME));
        if (reIssue.getFeePaid() != null && reIssue.getDemand() == null){
        	reIssue.setDemand(reIssueDemandService.createDemand(new BigDecimal(reIssue.getFeePaid())));
        }

        final Map<Long, MarriageDocument> applicantDocumentAndId = new HashMap<Long, MarriageDocument>();
        marriageDocumentService.getIndividualDocuments().forEach(document -> applicantDocumentAndId.put(document.getId(), document));

        marriageApplicantService.addDocumentsToFileStore(reIssue.getApplicant(), applicantDocumentAndId);

        workflowService.transition(reIssue, workflowContainer, reIssue.getApprovalComent());

        create(reIssue);
        reiSsueUpdateIndexesService.createReIssueAppIndex(reIssue); 
        marriageSmsAndEmailService.sendSMSForReIssueApplication(reIssue);
        marriageSmsAndEmailService.sendEmailForReIssueApplication(reIssue); 
        return reIssue.getApplicationNo();
    }

    @Transactional
    public ReIssue forwardReIssue(final Long id, final ReIssue reissue,
            final WorkflowContainer workflowContainer) {
        updateReIssueData(reissue);
        reissue.setStatus(
                marriageUtils.getStatusByCodeAndModuleType(ReIssue.ReIssueStatus.CREATED.toString(), MarriageConstants.MODULE_NAME));
        update(reissue);
        workflowService.transition(reissue, workflowContainer, reissue.getApprovalComent());
        return reissue;
    }

    @Transactional
    public ReIssue approveReIssue(ReIssue reissue, final WorkflowContainer workflowContainer) {
        reissue.setStatus(
                marriageUtils.getStatusByCodeAndModuleType(ReIssue.ReIssueStatus.APPROVED.toString(), MarriageConstants.MODULE_NAME));
        reissue = update(reissue);
        workflowService.transition(reissue, workflowContainer, workflowContainer.getApproverComments());
        marriageSmsAndEmailService.sendSMSForReIssueApplication(reissue);
        marriageSmsAndEmailService.sendEmailForReIssueApplication(reissue);
        reiSsueUpdateIndexesService.updateReIssueAppIndex(reissue); 
        return reissue;
    }

    @Transactional
    public ReIssue rejectReIssue(ReIssue reissue, final WorkflowContainer workflowContainer, final HttpServletRequest request) throws IOException {
        reissue.setStatus(workflowContainer.getWorkFlowAction().equalsIgnoreCase(MarriageConstants.WFLOW_ACTION_STEP_REJECT)?
                marriageUtils.getStatusByCodeAndModuleType(ReIssue.ReIssueStatus.REJECTED.toString(), MarriageConstants.MODULE_NAME)
                :marriageUtils.getStatusByCodeAndModuleType(ReIssue.ReIssueStatus.CANCELLED.toString(), MarriageConstants.MODULE_NAME));
        
        if(workflowContainer.getWorkFlowAction().equalsIgnoreCase(MarriageConstants.WFLOW_ACTION_STEP_CANCEL_REISSUE)){
            List<AppConfigValues> appConfigValues = appConfigValuesService.getConfigValuesByModuleAndKey(
                    MarriageConstants.MODULE_NAME, MarriageConstants.REISSUE_PRINTREJECTIONCERTIFICATE);
   // As per configuration, save rejection certificate.
            if (appConfigValues != null && appConfigValues.size() > 0
                    && appConfigValues.get(0).getValue().equalsIgnoreCase("YES")) {

                MarriageCertificate marriageCertificate = marriageCertificateService.reIssueCertificate(reissue, request,
                        MarriageCertificateType.REJECTION.toString());
                reissue.addCertificate(marriageCertificate);
            }
         }
        reiSsueUpdateIndexesService.updateReIssueAppIndex(reissue); 
        marriageSmsAndEmailService.sendSMSForReIssueApplication(reissue);
        marriageSmsAndEmailService.sendEmailForReIssueApplication(reissue);
        reissue.setRejectionReason(workflowContainer.getApproverComments());
        workflowService.transition(reissue, workflowContainer, workflowContainer.getApproverComments());
       
        return reissue;
    }
    
    
    private void updateReIssueData( final ReIssue reissue) {
        if(reissue.getFeeCriteria()!=null)
            reissue.setFeeCriteria(marriageFeeService.getFee(reissue.getFeeCriteria().getId()));
        reissue.setFeePaid(reissue.getFeePaid());
        if (reissue.getFeePaid() != null){
        	if(reissue.getDemand() == null){
        		reissue.setDemand(reIssueDemandService.createDemand(new BigDecimal(reissue.getFeePaid())));
        	}
        	else{
        		reIssueDemandService.updateDemand(reissue.getDemand(),new BigDecimal(reissue.getFeePaid()));
        	}
        }
        reissue.setStatus(marriageUtils.getStatusByCodeAndModuleType(ReIssue.ReIssueStatus.CREATED.toString(), MarriageConstants.MODULE_NAME));

        updateDocuments(reissue);
    }

    private void updateDocuments(final ReIssue reissue) {

        final Map<Long, MarriageDocument> individualDocumentAndId = new HashMap<Long, MarriageDocument>();
        marriageDocumentService.getIndividualDocuments().forEach(document -> individualDocumentAndId.put(document.getId(), document));

        marriageApplicantService.addDocumentsToFileStore(reissue.getApplicant(), individualDocumentAndId);
    }

   
    
    @Transactional
    public ReIssue printCertificate(ReIssue reIssue, final WorkflowContainer workflowContainer,final HttpServletRequest request) throws IOException {
        MarriageCertificate marriageCertificate = marriageCertificateService.reIssueCertificate(reIssue,request,MarriageCertificateType.REJECTION.REISSUE.toString());
        reIssue.setStatus(
                marriageUtils.getStatusByCodeAndModuleType(ReIssue.ReIssueStatus.CERTIFICATEREISSUED.toString(), MarriageConstants.MODULE_NAME));
        reIssue.addCertificate(marriageCertificate);
        reIssue.setActive(true);
        workflowService.transition(reIssue, workflowContainer, workflowContainer.getApproverComments()); 
  /*      marriageSmsAndEmailService.sendSMSForReIssueApplication(reIssue);
        marriageSmsAndEmailService.sendEmailForReIssueApplication(reIssue);*/
        return reIssue;
    }
    
    /**
     * @param registration
     * @return
     */
    public List<Hashtable<String, Object>> getHistory(final ReIssue reIssue) {
        User user = null;
        final List<Hashtable<String, Object>> historyTable = new ArrayList<Hashtable<String, Object>>();
        final State state = reIssue.getState();
        final Hashtable<String, Object> map = new Hashtable<String, Object>(0);
        if (null != state) {
            if (!reIssue.getStateHistory().isEmpty()
                    && reIssue.getStateHistory() != null)
                Collections.reverse(reIssue.getStateHistory());
            for (final StateHistory stateHistory : reIssue.getStateHistory()) {
                final Hashtable<String, Object> HistoryMap = new Hashtable<String, Object>(0);
                HistoryMap.put("date", stateHistory.getDateInfo());
                HistoryMap.put("comments", stateHistory.getComments()!=null?stateHistory.getComments():"");
                HistoryMap.put("updatedBy", stateHistory.getLastModifiedBy().getUsername() + "::"
                        + stateHistory.getLastModifiedBy().getName());
                HistoryMap.put("status", stateHistory.getValue());
                final Position owner = stateHistory.getOwnerPosition();
                user = stateHistory.getOwnerUser();
                if (null != user) {
                    HistoryMap.put("user", user.getUsername() + "::" + user.getName());
                    HistoryMap.put("department",
                            null != eisCommonService.getDepartmentForUser(user.getId()) ? eisCommonService
                                    .getDepartmentForUser(user.getId()).getName() : "");
                } else if (null != owner && null != owner.getDeptDesig()) {
                    user = eisCommonService.getUserForPosition(owner.getId(), new Date());
                    HistoryMap
                            .put("user", null != user.getUsername() ? user.getUsername() + "::" + user.getName() : "");
                    HistoryMap.put("department", null != owner.getDeptDesig().getDepartment() ? owner.getDeptDesig()
                            .getDepartment().getName() : "");
                }
                historyTable.add(HistoryMap);
            }

            map.put("date", state.getDateInfo());
            map.put("comments", state.getComments() != null ? state.getComments() : "");
            map.put("updatedBy", state.getLastModifiedBy().getUsername() + "::" + state.getLastModifiedBy().getName());
            map.put("status", state.getValue());
            final Position ownerPosition = state.getOwnerPosition();
            user = state.getOwnerUser();
            if (null != user) {
                map.put("user", user.getUsername() + "::" + user.getName());
                map.put("department", null != eisCommonService.getDepartmentForUser(user.getId()) ? eisCommonService
                        .getDepartmentForUser(user.getId()).getName() : "");
            } else if (null != ownerPosition && null != ownerPosition.getDeptDesig()) {
                user = eisCommonService.getUserForPosition(ownerPosition.getId(), new Date());
                map.put("user", null != user.getUsername() ? user.getUsername() + "::" + user.getName() : "");
                map.put("department", null != ownerPosition.getDeptDesig().getDepartment() ? ownerPosition
                        .getDeptDesig().getDepartment().getName() : "");
            }
            historyTable.add(map);
        }
        return historyTable;
    }

	public boolean checkAnyWorkFlowInProgressForRegistration(MarriageRegistration registration) {
		if(reIssueRepository.findReIssueInProgressForRegistration(registration.getRegistrationNo())==null) 
		    return false; 
	return true;
	}

}