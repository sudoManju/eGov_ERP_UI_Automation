package org.egov.bpa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.workflow.BpaApplicationWorkflowCustomDefaultImpl;
import org.egov.bpa.utils.BPASmsAndEmailService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.demand.model.EgDemandDetails;
import org.egov.eis.entity.Assignment;
import org.egov.eis.service.AssignmentService;
import org.egov.eis.service.DesignationService;
import org.egov.infra.admin.master.entity.Boundary;
import org.egov.infra.admin.master.entity.Module;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.admin.master.service.BoundaryService;
import org.egov.infra.admin.master.service.ModuleService;
import org.egov.infra.admin.master.service.UserService;
import org.egov.infra.persistence.entity.enums.UserType;
import org.egov.infra.security.utils.SecurityUtils;
import org.egov.infra.workflow.matrix.entity.WorkFlowMatrix;
import org.egov.infra.workflow.service.SimpleWorkflowService;
import org.egov.portal.entity.PortalInbox;
import org.egov.portal.entity.PortalInboxBuilder;
import org.egov.portal.service.PortalInboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BpaUtils {

    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private SecurityUtils securityUtils;
    
    @Autowired
    private ModuleService moduleService;

    @Autowired
    private AssignmentService assignmentService;
    
    @Autowired
    private PortalInboxService portalInboxService;
    @Autowired
    private BPASmsAndEmailService bpaSmsAndEmailService;


    @Autowired
    private BoundaryService boundaryService;
    
    

    @Autowired
    @Qualifier("workflowService")
    private SimpleWorkflowService<BpaApplication> bpaApplicationWorkflowService;

    @Autowired
    private DesignationService designationService;
    
    @Autowired
    private UserService userService;
    
    public Boolean checkAnyTaxIsPendingToCollect(final BpaApplication bpaApplication) {
        Boolean pendingTaxCollection = false;

        if (bpaApplication != null && bpaApplication.getDemand() != null)
            for (final EgDemandDetails demandDtl : bpaApplication.getDemand().getEgDemandDetails())
                if (demandDtl.getAmount().subtract(demandDtl.getAmtCollected()).compareTo(BigDecimal.ZERO) > 0) {
                    pendingTaxCollection = true;
                    break;
                }
        return pendingTaxCollection;
    }

    public String loggedInUserDesignation(final BpaApplication application) {
        String loggedInUserDesignation = "";
        final User user = securityUtils.getCurrentUser();
        List<Assignment> loggedInUserAssign;
        if (application.getState() != null && application.getState().getOwnerPosition() != null) {
            loggedInUserAssign = assignmentService.getAssignmentByPositionAndUserAsOnDate(
            		application.getState().getOwnerPosition().getId(), user.getId(), new Date());
            loggedInUserDesignation = !loggedInUserAssign.isEmpty()
                    ? loggedInUserAssign.get(0).getDesignation().getName() : null;
        }
        return loggedInUserDesignation;
    }
    public Boolean applicationinitiatedByNonEmployee(BpaApplication application)
    {
    	Boolean initiatedByNonEmployee=false;
    	User applicationInitiator =null;
    	if(application.getCreatedBy()!=null)
    	 applicationInitiator = userService.getUserById(application.getCreatedBy().getId());
    	else
    		applicationInitiator = securityUtils.getCurrentUser();
    	if(applicationInitiator !=null && !applicationInitiator.getType().equals(UserType.EMPLOYEE)){
    		initiatedByNonEmployee=Boolean.TRUE;
    	}
    	
    	return initiatedByNonEmployee;
    }
    public BpaApplicationWorkflowCustomDefaultImpl getInitialisedWorkFlowBean() {
        BpaApplicationWorkflowCustomDefaultImpl applicationWorkflowCustomDefaultImpl = null;
        if (null != context)
            applicationWorkflowCustomDefaultImpl = (BpaApplicationWorkflowCustomDefaultImpl) context
                    .getBean("bpaApplicationWorkflowCustomDefaultImpl");
        return applicationWorkflowCustomDefaultImpl;
    }

    public WorkFlowMatrix getWfMatrixByCurrentState(final BpaApplication application, final String currentState) {
        return bpaApplicationWorkflowService.getWfMatrix(application.getStateType(), null,
                null, BpaConstants.CREATE_ADDITIONAL_RULE_CREATE, currentState, null);
    }
    @Transactional
    public void updateCitizeninboxApplication(final BpaApplication application) {
        Module module = moduleService.getModuleByName(BpaConstants.EGMODULE_NAME);
        boolean isResolved=false;
        if((application.getState()!=null &&  application.getState().equals("END")) ||(application.getStatus()!=null && application.getStatus().getCode().equals(BpaConstants.APPLICATION_STATUS_CANCELLED)))
             isResolved=true;
        String  url = "/bpa/application/citizen/update/" + application.getApplicationNumber();
        if(application.getStatus()!=null )
        portalInboxService.updateInboxMessage(application.getApplicationNumber(), module.getId(),  (application.getStatus().getDescription()), 
        		isResolved, new Date(), application.getState(), securityUtils.getCurrentUser(),application.getPlanPermissionNumber(),url);
    }
    @Transactional
    public void pushMessage(final BpaApplication application) {
        Module module = moduleService.getModuleByName(BpaConstants.EGMODULE_NAME);
        boolean isResolved=false;
        if(application.getState()!=null &&  application.getState().getNextAction().equals("END"))
             isResolved=true;
        String  url = "/bpa/application/citizen/update/" + application.getApplicationNumber();
        final PortalInboxBuilder portalInboxBuilder = new PortalInboxBuilder(module,application.getServiceType().getDescription(),
        		application.getApplicationNumber(),application.getPlanPermissionNumber(),application.getId(),
                "Sucess","suceess1",url,
                isResolved,"to be Subitted",new Date(),application.getState(),securityUtils.getCurrentUser());

        final PortalInbox portalInbox = portalInboxBuilder.build();
        portalInboxService.pushInboxMessage(portalInbox);
    }
    @Transactional(readOnly = true)
    public Long getUserPositionByZone(final String designation, final Long boundary) {
        final Boundary boundaryObj = getBoundaryById(boundary);
        final String[] designationarr = designation.split(",");
        List<Assignment> assignment = new ArrayList<>();
        for (final String desg : designationarr) {
            assignment = assignmentService.findByDepartmentDesignationAndBoundary(null,
                    designationService.getDesignationByName(desg).getId(), boundaryObj.getId());
            if (assignment.isEmpty()) {
                // Ward->Zone
                if (boundaryObj.getParent() != null && boundaryObj.getParent().getBoundaryType() != null
                        && boundaryObj.getParent().getBoundaryType().getName().equals(BpaConstants.BOUNDARY_TYPE_ZONE)) {
                    assignment = assignmentService.findByDeptDesgnAndParentAndActiveChildBoundaries(
                            null,
                            designationService.getDesignationByName(desg).getId(), boundaryObj.getParent().getId());
                    if (assignment.isEmpty() && boundaryObj.getParent() != null && boundaryObj.getParent().getParent() != null
                            && boundaryObj.getParent().getParent().getBoundaryType().getName()
                                    .equals(BpaConstants.BOUNDARY_TYPE_CITY))
                        assignment = assignmentService.findByDeptDesgnAndParentAndActiveChildBoundaries(null,
                                designationService.getDesignationByName(desg).getId(),
                                boundaryObj.getParent().getParent().getId());
                }
                // ward->City mapp
                if (assignment.isEmpty() && boundaryObj.getParent() != null && boundaryObj.getParent().getBoundaryType().getName()
                        .equals(BpaConstants.BOUNDARY_TYPE_CITY))
                    assignment = assignmentService.findByDeptDesgnAndParentAndActiveChildBoundaries(
                            null,
                            designationService.getDesignationByName(desg).getId(),
                            boundaryObj.getParent().getId());
            }
            if (!assignment.isEmpty())
                break;
        }
        return !assignment.isEmpty() ? assignment.get(0).getPosition().getId() : 0;
    }

    public Boundary getBoundaryById(final Long boundary) {

        return boundaryService.getBoundaryById(boundary);
    }
    public Boolean logedInuseCitizenOrBusinessUser() {
        Boolean citizenOrbusiness = Boolean.FALSE;
        User applicationInitiator = securityUtils.getCurrentUser();
       if( applicationInitiator!=null && (applicationInitiator.getType().equals(UserType.CITIZEN) || applicationInitiator.getType().equals(UserType.BUSINESS))){
    	   citizenOrbusiness=Boolean.TRUE;
       }
        return citizenOrbusiness;
    }
    @Transactional
    public void redirectToBpaWorkFlow(Long approvalPosition, final BpaApplication application, final String currentState,
            final String remarks,final String workFlowAction,final BigDecimal amountRule) {

        final WorkFlowMatrix wfmatrix = getWfMatrixByCurrentState(application, currentState);
        final BpaApplicationWorkflowCustomDefaultImpl applicationWorkflowCustomDefaultImpl = getInitialisedWorkFlowBean();
        if (approvalPosition == null) {
            approvalPosition = getUserPositionByZone(wfmatrix.getNextDesignation(), application.getSiteDetail().get(0) != null
                    ? application.getSiteDetail().get(0).getElectionBoundary().getId() : null);
        }
        if (currentState.equals(BpaConstants.LETTERTOPARTYINITIATE))
            applicationWorkflowCustomDefaultImpl.createCommonWorkflowTransition(application,
                    approvalPosition, remarks,
                    BpaConstants.CREATE_ADDITIONAL_RULE_CREATE, BpaConstants.LETTERTOPARTYINITIATE,amountRule);
        else if (currentState.equals(BpaConstants.LETTERTOPARTYINITIATED))
            applicationWorkflowCustomDefaultImpl.createCommonWorkflowTransition(application,
                    approvalPosition, remarks,
                    BpaConstants.CREATE_ADDITIONAL_RULE_CREATE, BpaConstants.LETTERTOPARTYINITIATED,amountRule);
        else
            applicationWorkflowCustomDefaultImpl.createCommonWorkflowTransition(application,
                    approvalPosition, remarks,
                    BpaConstants.CREATE_ADDITIONAL_RULE_CREATE, workFlowAction,amountRule);
    }
    
    public void sendSmsEmailOnCitizenSubmit(BpaApplication bpaApplication, String workFlowAction) {
		if(workFlowAction!=null && workFlowAction.equals(BpaConstants.WF_SURVEYOR_FORWARD_BUTTON) && (logedInuseCitizenOrBusinessUser()))
        bpaSmsAndEmailService.sendSMSAndEmail(bpaApplication);
	}
}
