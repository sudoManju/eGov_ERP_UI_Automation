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
package org.egov.bpa.web.controller.application;

import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_APPROVED;
import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_CREATED;
import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_DIGI_SIGNED;
import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_FIELD_INS;
import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_NOCUPDATED;
import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_RECORD_APPROVED;
import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_REGISTERED;
import static org.egov.bpa.utils.BpaConstants.APPLN_STATUS_FIELD_INSPECTION_INITIATED;
import static org.egov.bpa.utils.BpaConstants.BPA_STATUS_SUPERINDENT_APPROVED;
import static org.egov.bpa.utils.BpaConstants.CHECKLIST_TYPE_NOC;
import static org.egov.bpa.utils.BpaConstants.CREATE_ADDITIONAL_RULE_CREATE;
import static org.egov.bpa.utils.BpaConstants.GENERATEPERMITORDER;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_05;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_08;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_09;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_14;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_15;
import static org.egov.bpa.utils.BpaConstants.WF_CANCELAPPLICATION_BUTTON;
import static org.egov.bpa.utils.BpaConstants.WF_REJECT_BUTTON;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.BpaAppointmentSchedule;
import org.egov.bpa.application.entity.LettertoParty;
import org.egov.bpa.application.entity.enums.AppointmentSchedulePurpose;
import org.egov.bpa.application.service.InspectionService;
import org.egov.bpa.application.service.LettertoPartyService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.eis.service.PositionMasterService;
import org.egov.eis.web.contract.WorkflowContainer;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.persistence.entity.PermanentAddress;
import org.egov.infra.workflow.entity.StateHistory;
import org.egov.pims.commons.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/application")
public class UpdateBpaApplicationController extends BpaGenericApplicationController {
    private static final String FWD_TO_AE_FOR_FIELD_ISPECTION = "Forwarded to Assistant Engineer for field ispection";
    private static final String COLLECT_FEE_VALIDATE = "collectFeeValidate";
    private static final String FWD_TO_SUPERINTENDENT = "Forwarded to Superintendent";
    private static final String FWD_TO_AE_FOR_APPROVAL = "Forwarded to Assistant Engineer For Approval";
    private static final String WORK_FLOW_ACTION = "workFlowAction";
    private static final String MSG_REJECT_FORWARD_REGISTRATION = "msg.reject.forward.registration";
    private static final String MSG_CANCEL_REGISTRATION = "msg.cancel.registration";
    private static final String MSG_UPDATE_FORWARD_REGISTRATION = "msg.update.forward.registration";
    private static final String APPLICATION_VIEW = "application-view";
    private static final String AMOUNT_RULE = "amountRule";
    private static final String FWD_TO_OVRSR_FOR_FIELD_INS = "Forwarded to Overseer for field inspection";
    private static final String FORWARDED_TO_NOC_UPDATE = "Forwarded to Superintendent for Noc Updation";
    private static final String BPA_APPLICATION = "bpaApplication";

    private static final String MESSAGE = "message";

    private static final String CREATEDOCUMENTSCRUTINY_FORM = "createdocumentscrutiny-form";

    private static final String DOCUMENTSCRUTINY_FORM = "documentscrutiny-form";

    private static final String BPAAPPLICATION_FORM = "bpaapplication-Form";

    private static final String BPA_APPLICATION_RESULT = "bpa-application-result";

    private static final String APPRIVALPOSITION = "approvalPosition";

    private static final String APPLICATION_HISTORY = "applicationHistory";

    private static final String ADDITIONALRULE = "additionalRule";

    @Autowired
    private InspectionService inspectionService;
    @Autowired
    private PositionMasterService positionMasterService;
    @Autowired
    LettertoPartyService lettertoPartyService;

    @ModelAttribute
    public BpaApplication getBpaApplication(@PathVariable final String applicationNumber) {
        return applicationBpaService.findByApplicationNumber(applicationNumber);
    }

    @RequestMapping(value = "/update/{applicationNumber}", method = RequestMethod.GET)
    public String updateApplicationForm(final Model model, @PathVariable final String applicationNumber,
            final HttpServletRequest request) {
        List<String> purposeInsList = new ArrayList<>();
        List<String> purposeDocList = new ArrayList<>();
        final BpaApplication application = getBpaApplication(applicationNumber);
        for (BpaAppointmentSchedule schedule : application.getAppointmentSchedule()) {
            if (AppointmentSchedulePurpose.INSPECTION.equals(schedule.getPurpose())) {
                purposeInsList.add(schedule.getPurpose().name());
            } else if (AppointmentSchedulePurpose.DOCUMENTSCRUTINY.equals(schedule.getPurpose())) {
                purposeDocList.add(schedule.getPurpose().name());
            }
        }
        getModeForUpdateApplication(model, purposeInsList, purposeDocList, application);
        model.addAttribute("inspectionList", inspectionService.findByBpaApplicationOrderByIdAsc(application));
        model.addAttribute("lettertopartylist", lettertoPartyService.findByBpaApplicationOrderByIdDesc(application));
        if (FWD_TO_AE_FOR_FIELD_ISPECTION.equals(application.getState().getNextAction())
                || APPLICATION_STATUS_FIELD_INS.equals(application.getStatus().getCode())
                || APPLICATION_STATUS_NOCUPDATED.equalsIgnoreCase(application.getStatus().getCode())) {
            model.addAttribute("createlettertoparty", true);
        }
        model.addAttribute("workFlowByNonEmp", applicationBpaService.applicationinitiatedByNonEmployee(application));
        model.addAttribute(APPLICATION_HISTORY,
                bpaThirdPartyService.getHistory(application));

        if (application != null) {
            loadViewdata(model, application);
            if (application.getState() != null
                    && application.getState().getValue().equalsIgnoreCase(BPA_STATUS_SUPERINDENT_APPROVED)) {
                return DOCUMENTSCRUTINY_FORM;
            }
        }
        if (APPLICATION_STATUS_CREATED.equals(application.getStatus().getCode())
                || APPLICATION_STATUS_REGISTERED.equals(application.getStatus().getCode())) {
            if (applicationBpaService.applicationinitiatedByNonEmployee(application)
                    && applicationBpaService.checkAnyTaxIsPendingToCollect(application)) {
                model.addAttribute(COLLECT_FEE_VALIDATE, "Collect Fees to Process Application");
            } else
                model.addAttribute(COLLECT_FEE_VALIDATE, "");
        }
        return APPLICATION_VIEW;
    }

    private void getModeForUpdateApplication(final Model model, List<String> purposeInsList,
            List<String> purposeDocList, final BpaApplication application) {
        String mode = null;
        AppointmentSchedulePurpose scheduleType = null;
        if (BpaConstants.WF_CREATED_STATE.equalsIgnoreCase(application.getStatus().getCode())) {
            mode = "view";
        } else if (!BpaConstants.WF_CREATED_STATE.equalsIgnoreCase(application.getStatus().getCode())
                && (!FWD_TO_SUPERINTENDENT
                        .equalsIgnoreCase(application.getState().getNextAction())
                        && application.getAppointmentSchedule().isEmpty())
                || (APPLN_STATUS_FIELD_INSPECTION_INITIATED.equalsIgnoreCase(application.getStatus().getCode())
                        && FWD_TO_OVRSR_FOR_FIELD_INS
                                .equalsIgnoreCase(application.getState().getNextAction())
                        && purposeInsList.isEmpty())) {
            mode = "newappointment";
        } else if (BpaConstants.APPLICATION_STATUS_REGISTERED.equalsIgnoreCase(application.getStatus().getCode())
                && purposeDocList.contains(AppointmentSchedulePurpose.DOCUMENTSCRUTINY.name())) {
            mode = "postponeappointment";
            scheduleType = AppointmentSchedulePurpose.DOCUMENTSCRUTINY;
        } else if (FWD_TO_OVRSR_FOR_FIELD_INS.equalsIgnoreCase(application.getState().getNextAction())
                && APPLN_STATUS_FIELD_INSPECTION_INITIATED.equalsIgnoreCase(application.getStatus().getCode())
                && application.getInspections().isEmpty()) {
            mode = "captureInspection";
            scheduleType = AppointmentSchedulePurpose.INSPECTION;
        } else if (FWD_TO_OVRSR_FOR_FIELD_INS.equalsIgnoreCase(application.getState().getNextAction())
                && APPLN_STATUS_FIELD_INSPECTION_INITIATED.equalsIgnoreCase(application.getStatus().getCode())
                && !application.getInspections().isEmpty()) {
            mode = "modifyInspection";
        } else if (FORWARDED_TO_NOC_UPDATE.equalsIgnoreCase(application.getState().getNextAction())
                && APPLICATION_STATUS_FIELD_INS.equalsIgnoreCase(application.getStatus().getCode())) {
            model.addAttribute("showUpdateNoc", true);
        } else if (FWD_TO_AE_FOR_APPROVAL.equalsIgnoreCase(application.getState().getNextAction())
                && !application.getInspections().isEmpty()) {
            mode = "initialtedApprove";
        }

        if (mode == null) {
            mode = "edit";
        }
        model.addAttribute("scheduleType", scheduleType);
        model.addAttribute("mode", mode);
    }

    @RequestMapping(value = "/documentscrutiny/{applicationNumber}", method = RequestMethod.GET)
    public String documentScrutinyForm(final Model model, @PathVariable final String applicationNumber,
            final HttpServletRequest request) {
        final BpaApplication application = getBpaApplication(applicationNumber);
        if (application != null && application.getState() != null
                && application.getState().getValue().equalsIgnoreCase(BPA_STATUS_SUPERINDENT_APPROVED)) {
            loadViewdata(model, application);
            model.addAttribute("loginUser", securityUtils.getCurrentUser());
            model.addAttribute(APPLICATION_HISTORY,
                    bpaThirdPartyService.getHistory(application));
        }
        // return to error page if status is not superindent approved.
        return CREATEDOCUMENTSCRUTINY_FORM;
    }

    @RequestMapping(value = "/documentscrutiny/{applicationNumber}", method = RequestMethod.POST)
    public String documentScrutinyForm(@Valid @ModelAttribute(BPA_APPLICATION) BpaApplication bpaApplication,
            @PathVariable final String applicationNumber,
            final BindingResult resultBinder, final RedirectAttributes redirectAttributes,
            final HttpServletRequest request,
            @RequestParam final BigDecimal amountRule, final Model model, @RequestParam("files") final MultipartFile[] files) {
        if (resultBinder.hasErrors()) {
            loadViewdata(model, bpaApplication);
            return CREATEDOCUMENTSCRUTINY_FORM;
        }
        Long approvalPosition;
        if (request.getParameter(APPRIVALPOSITION) != null) {
            approvalPosition = Long.valueOf(request.getParameter(APPRIVALPOSITION));
            Position pos = positionMasterService.getPositionById(approvalPosition);
            User user = bpaThirdPartyService.getUserPositionByPassingPosition(approvalPosition);
            // for document scrutiny if not scheduled, by default captured the current time and saved.
            if (bpaApplication.getAppointmentSchedule().isEmpty()) {
                SimpleDateFormat sf = new SimpleDateFormat("hh:mm a");
                BpaAppointmentSchedule appointmentSchedule = new BpaAppointmentSchedule();
                Date now = new Date();
                appointmentSchedule.setAppointmentDate(now);
                appointmentSchedule.setPurpose(AppointmentSchedulePurpose.DOCUMENTSCRUTINY);
                appointmentSchedule.setApplication(bpaApplication);
                appointmentSchedule.setAppointmentTime(sf.format(now));
                bpaApplication.getAppointmentSchedule().add(appointmentSchedule);
            }
            String workFlowAction = request.getParameter(WORK_FLOW_ACTION);
            if (!bpaApplication.getApplicationDocument().isEmpty())
                applicationBpaService.persistOrUpdateApplicationDocument(bpaApplication);
            BpaApplication bpaAppln = applicationBpaService.updateApplication(bpaApplication, approvalPosition, workFlowAction,
                    amountRule);
            String message = messageSource.getMessage("msg.update.forward.documentscrutiny", new String[] {
                    user != null ? user.getUsername().concat("~")
                            .concat(getDesinationNameByPosition(pos))
                            : "",
                    bpaAppln.getApplicationNumber() }, LocaleContextHolder.getLocale());
            model.addAttribute(MESSAGE, message);
        }
        return BPA_APPLICATION_RESULT;
    }

    private void loadViewdata(final Model model, final BpaApplication application) {
        applicationBpaService.buildApplicationFloorDetailsForUpdate(application);
        model.addAttribute("stateType", application.getClass().getSimpleName());
        final WorkflowContainer workflowContainer = new WorkflowContainer();
        model.addAttribute(ADDITIONALRULE, CREATE_ADDITIONAL_RULE_CREATE);
        workflowContainer.setAdditionalRule(CREATE_ADDITIONAL_RULE_CREATE);
        List<LettertoParty> lettertoParties = lettertoPartyService.findByBpaApplicationOrderByIdDesc(application);

        // Setting AmountRule to decide no. of level approval cycle
        if (APPLICATION_STATUS_NOCUPDATED.equals(application.getStatus().getCode())
                || (!APPLICATION_STATUS_DIGI_SIGNED.equals(application.getStatus().getCode())
                        && !APPLICATION_STATUS_APPROVED.equals(application.getStatus().getCode()) && !lettertoParties.isEmpty()
                        && APPLICATION_STATUS_NOCUPDATED
                                .equals(lettertoParties.get(0).getCurrentApplnStatus().getCode()))) {
            workflowContainer.setAmountRule(getAmountRuleByServiceType(application));
            workflowContainer.setPendingActions(application.getState().getNextAction());
        } else if (APPLICATION_STATUS_APPROVED.equals(application.getStatus().getCode())
                && !APPLICATION_STATUS_RECORD_APPROVED.equalsIgnoreCase(application.getState().getValue())) {
            workflowContainer.setAmountRule(getAmountRuleByServiceType(application));
        }
        workflowContainer.setAdditionalRule(CREATE_ADDITIONAL_RULE_CREATE);
        application.getOwner().setPermanentAddress((PermanentAddress) application.getOwner().getUser().getAddress().get(0));
        prepareWorkflow(model, application, workflowContainer);
        model.addAttribute("pendingActions", workflowContainer.getPendingActions());
        model.addAttribute(AMOUNT_RULE, workflowContainer.getAmountRule());
        model.addAttribute("currentState", application.getCurrentState().getValue());
        model.addAttribute(BPA_APPLICATION, application);
        model.addAttribute("electionBoundary", application.getSiteDetail().get(0).getElectionBoundary().getId());
        model.addAttribute("bpaPrimaryDept", bpaUtils.getAppconfigValueByKeyNameForDefaultDept());
        model.addAttribute("nocCheckListDetails", checkListDetailService
                .findActiveCheckListByServiceType(application.getServiceType().getId(), CHECKLIST_TYPE_NOC));
        model.addAttribute("checkListDetailList", checkListDetailService
                .findActiveCheckListByServiceType(application.getServiceType().getId(), BpaConstants.CHECKLIST_TYPE));
        model.addAttribute("applicationDocumentList", application.getApplicationDocument());
        model.addAttribute("isFeeCollected", bpaDemandService.checkAnyTaxIsPendingToCollect(application));
        model.addAttribute("admissionFee", applicationBpaService.setAdmissionFeeAmountForRegistrationWithAmenities(
                application.getServiceType().getId(), application.getApplicationAmenity()));
        buildReceiptDetails(application);
    }

    private BigDecimal getAmountRuleByServiceType(final BpaApplication application) {
        BigDecimal amountRule = BigDecimal.ONE;
        if (ST_CODE_14.equalsIgnoreCase(application.getServiceType().getCode())
                || ST_CODE_15.equalsIgnoreCase(application.getServiceType().getCode())) {
            amountRule = new BigDecimal(11000);
        } else if (ST_CODE_05.equalsIgnoreCase(application.getServiceType().getCode())) {
            amountRule = application.getDocumentScrutiny().get(0).getExtentinsqmts();
        } else if (ST_CODE_08.equalsIgnoreCase(application.getServiceType().getCode())
                || ST_CODE_09.equalsIgnoreCase(application.getServiceType().getCode())) {
            amountRule = BigDecimal.ONE;
        } else if (!application.getBuildingDetail().isEmpty()
                && application.getBuildingDetail().get(0).getTotalPlintArea() != null) {
            amountRule = application.getBuildingDetail().get(0).getTotalPlintArea();
        }
        return amountRule;
    }

    @RequestMapping(value = "/update/{applicationNumber}", method = RequestMethod.POST)
    public String updateApplication(@Valid @ModelAttribute(BPA_APPLICATION) BpaApplication bpaApplication,
            @PathVariable final String applicationNumber,
            final BindingResult resultBinder, final RedirectAttributes redirectAttributes,
            final HttpServletRequest request, final Model model,
            @RequestParam final BigDecimal amountRule, @RequestParam("files") final MultipartFile[] files) {

        if (resultBinder.hasErrors()) {
            loadViewdata(model, bpaApplication);
            return BPAAPPLICATION_FORM;
        }

        if (bpaApplicationValidationService.validateBuildingDetails(bpaApplication, model)) {
            loadViewdata(model, bpaApplication);
            return BPAAPPLICATION_FORM;
        }

        String workFlowAction = request.getParameter(WORK_FLOW_ACTION);
        String approvalComent = request.getParameter("approvalComent");
        String message;
        Long approvalPosition = null;
        Position pos = null;
        if (BpaConstants.LETTERTOPARTYSENT.equalsIgnoreCase(bpaApplication.getState().getNextAction()))
            approvalPosition = bpaApplication.getState().getPreviousOwner().getId();
        else if (null != request.getParameter(APPRIVALPOSITION) && !"".equals(request.getParameter(APPRIVALPOSITION))
                && !WF_REJECT_BUTTON.equalsIgnoreCase(workFlowAction)
                && !WF_CANCELAPPLICATION_BUTTON.equalsIgnoreCase(workFlowAction)) {
            approvalPosition = Long.valueOf(request.getParameter(APPRIVALPOSITION));
        } else if (WF_REJECT_BUTTON.equalsIgnoreCase(workFlowAction)) {
            for (StateHistory stateHistory : bpaApplication.getStateHistory()) {
                if (BPA_STATUS_SUPERINDENT_APPROVED.equalsIgnoreCase(stateHistory.getValue())) {
                    approvalPosition = stateHistory.getOwnerPosition().getId();
                }
            }
        }
        buildReceiptDetails(bpaApplication);
        if (!bpaApplication.getApplicationDocument().isEmpty())
            applicationBpaService.persistOrUpdateApplicationDocument(bpaApplication);
        if (bpaApplication.getCurrentState().getValue().equals(BpaConstants.WF_NEW_STATE)) {
            return applicationBpaService.redirectToCollectionOnForward(bpaApplication, model);
        }
        BpaApplication bpaAppln = applicationBpaService.updateApplication(bpaApplication, approvalPosition, workFlowAction,
                amountRule);
        bpaUtils.updatePortalUserinbox(bpaAppln, null);
        if (null != approvalPosition) {
            pos = positionMasterService.getPositionById(approvalPosition);
        }
        if (null == approvalPosition) {
            pos = positionMasterService.getPositionById(bpaAppln.getCurrentState().getOwnerPosition().getId());
        }
        User user = bpaThirdPartyService.getUserPositionByPassingPosition(approvalPosition);
        if (WF_REJECT_BUTTON.equalsIgnoreCase(workFlowAction)) {
            message = messageSource.getMessage(MSG_REJECT_FORWARD_REGISTRATION, new String[] {
                    user != null ? user.getUsername().concat("~")
                            .concat(getDesinationNameByPosition(pos))
                            : "",
                    bpaAppln.getApplicationNumber(), approvalComent }, LocaleContextHolder.getLocale());
        } else if (WF_CANCELAPPLICATION_BUTTON.equalsIgnoreCase(workFlowAction)) {
            message = messageSource.getMessage(MSG_CANCEL_REGISTRATION, new String[] {
                    bpaAppln.getApplicationNumber(), approvalComent }, LocaleContextHolder.getLocale());
        } else {
            message = messageSource.getMessage(MSG_UPDATE_FORWARD_REGISTRATION, new String[] {
                    user != null ? user.getUsername().concat("~")
                            .concat(getDesinationNameByPosition(pos))
                            : "",
                    bpaAppln.getApplicationNumber() }, LocaleContextHolder.getLocale());
        }
        model.addAttribute(MESSAGE, message);

        if (workFlowAction != null && workFlowAction.equalsIgnoreCase(GENERATEPERMITORDER)) {
            return "redirect:/application/generatepermitorder/" + bpaAppln.getApplicationNumber();
        }
        return BPA_APPLICATION_RESULT;
    }

}
