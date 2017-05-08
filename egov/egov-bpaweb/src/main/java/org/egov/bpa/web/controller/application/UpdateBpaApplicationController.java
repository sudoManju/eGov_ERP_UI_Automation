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
import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_FIELD_INS;
import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_NOCUPDATED;
import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_RECORD_APPROVED;
import static org.egov.bpa.utils.BpaConstants.APPLN_STATUS_FIELD_INSPECTION_INITIATED;
import static org.egov.bpa.utils.BpaConstants.BPA_STATUS_SUPERINDENT_APPROVED;
import static org.egov.bpa.utils.BpaConstants.CHECKLIST_TYPE_NOC;
import static org.egov.bpa.utils.BpaConstants.CREATE_ADDITIONAL_RULE_CREATE;
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
import org.egov.bpa.application.entity.enums.AppointmentSchedulePurpose;
import org.egov.bpa.application.service.InspectionService;
import org.egov.bpa.application.service.LettertoPartyService;
import org.egov.bpa.masters.service.StakeHolderService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.eis.service.PositionMasterService;
import org.egov.eis.web.contract.WorkflowContainer;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.security.utils.SecurityUtils;
import org.egov.infra.workflow.entity.StateHistory;
import org.egov.pims.commons.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
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
    private SecurityUtils securityUtils;
    @Autowired
    private InspectionService inspectionService;
    @Autowired
    private StakeHolderService stakeHolderService;
    @Autowired
    private PositionMasterService positionMasterService;
    @Autowired
    protected ResourceBundleMessageSource messageSource;
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
        String mode = null;
        AppointmentSchedulePurpose scheduleType = null;
        for (BpaAppointmentSchedule schedule : application.getAppointmentSchedule()) {
            if (AppointmentSchedulePurpose.INSPECTION.equals(schedule.getPurpose())) {
                purposeInsList.add(schedule.getPurpose().name());
            } else if (AppointmentSchedulePurpose.DOCUMENTSCRUTINY.equals(schedule.getPurpose())) {
                purposeDocList.add(schedule.getPurpose().name());
            }
        }
        if (application.getAppointmentSchedule().isEmpty()
                || (APPLN_STATUS_FIELD_INSPECTION_INITIATED.equalsIgnoreCase(application.getStatus().getCode())
                        && FWD_TO_OVRSR_FOR_FIELD_INS
                                .equalsIgnoreCase(application.getState().getNextAction())
                        && purposeInsList.isEmpty())) {
            mode = "newappointment";
        } else if (BpaConstants.APPLICATION_STATUS_REGISTERED.equalsIgnoreCase(application.getStatus().getCode())
                && purposeDocList.contains(AppointmentSchedulePurpose.DOCUMENTSCRUTINY.name())) {
            mode = "postponeappointment";
            scheduleType = AppointmentSchedulePurpose.DOCUMENTSCRUTINY;
        } 
        if (FWD_TO_OVRSR_FOR_FIELD_INS.equalsIgnoreCase(application.getState().getNextAction())
                && APPLN_STATUS_FIELD_INSPECTION_INITIATED.equalsIgnoreCase(application.getStatus().getCode())
                && application.getInspections().isEmpty()) {
            mode = "captureInspection";
        } else if (FWD_TO_OVRSR_FOR_FIELD_INS.equalsIgnoreCase(application.getState().getNextAction())
                && APPLN_STATUS_FIELD_INSPECTION_INITIATED.equalsIgnoreCase(application.getStatus().getCode())
                && !application.getInspections().isEmpty()) {
            mode = "modifyInspection";
            scheduleType = AppointmentSchedulePurpose.INSPECTION;
        } else if (FORWARDED_TO_NOC_UPDATE.equalsIgnoreCase(application.getState().getNextAction())
                && APPLICATION_STATUS_FIELD_INS.equalsIgnoreCase(application.getStatus().getCode())) {
            model.addAttribute("showUpdateNoc", true);
        } else if ("Forwarded to Assistant Engineer For Approval".equalsIgnoreCase(application.getState().getNextAction())
                && !application.getInspections().isEmpty()) {
            mode = "initialtedApprove";
        }
        model.addAttribute("inspectionList", inspectionService.findByBpaApplicationOrderByIdAsc(application));
        if (BpaConstants.LETTERTOPARTYSENT.equalsIgnoreCase(application.getState().getNextAction())) {
            model.addAttribute("showlettertoparty", true);
            model.addAttribute("lettertopartylist", lettertoPartyService.findByBpaApplicationOrderByIdAsc(application));
        } else if (application.getState().getValue().equals(BpaConstants.APPLN_STATUS_FIELD_INSPECTION_INITIATED)
                || application.getState().getValue().equals(BpaConstants.BPA_STATUS_SUPERINDENT_APPROVED)) {
            model.addAttribute("createlettertoparty", true);
        }
        if (mode == null) {
            mode = "edit";
        }
        model.addAttribute("scheduleType", scheduleType);
        model.addAttribute("mode", mode);
        model.addAttribute(APPLICATION_HISTORY,
                bpaThirdPartyService.getHistory(application));
        if (!application.getStakeHolder().isEmpty())
            model.addAttribute("stakeHolderList", stakeHolderService
                    .getStakeHolderListByType(application.getStakeHolder().get(0).getStakeHolder().getStakeHolderType()));
        if (application != null) {
            loadViewdata(model, application);
            if (application.getState() != null
                    && application.getState().getValue().equalsIgnoreCase(BPA_STATUS_SUPERINDENT_APPROVED)) {
                return DOCUMENTSCRUTINY_FORM;
            }
        }
        if ("Registered".equals(application.getStatus().getCode())) {
            return BPAAPPLICATION_FORM;
        } else {
            return "application-view";
        }
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
            final HttpServletRequest request, final Model model, @RequestParam("files") final MultipartFile[] files) {
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
            String workFlowAction = request.getParameter("workFlowAction");
            BpaApplication bpaAppln = applicationBpaService.updateApplication(bpaApplication, approvalPosition, workFlowAction);
            String message = messageSource.getMessage("msg.update.forward.documentscrutiny", new String[] {
                    user != null ? user.getUsername().concat("~")
                            .concat(pos.getDeptDesig() != null && pos.getDeptDesig().getDesignation() != null
                                    ? pos.getDeptDesig().getDesignation().getName() : "")
                            : "",
                    bpaAppln.getApplicationNumber() }, LocaleContextHolder.getLocale());
            model.addAttribute(MESSAGE, message);
        }
        return BPA_APPLICATION_RESULT;
    }

    private void loadViewdata(final Model model, final BpaApplication application) {
        model.addAttribute("stateType", application.getClass().getSimpleName());
        final WorkflowContainer workflowContainer = new WorkflowContainer();
        model.addAttribute(ADDITIONALRULE, CREATE_ADDITIONAL_RULE_CREATE);
        workflowContainer.setAdditionalRule(CREATE_ADDITIONAL_RULE_CREATE);
        if (APPLICATION_STATUS_NOCUPDATED.equals(application.getStatus().getCode())) {
            workflowContainer.setAmountRule(!application.getSiteDetail().isEmpty() ?application.getSiteDetail().get(0).getExtentinsqmts():new BigDecimal(1));
            workflowContainer.setPendingActions(application.getState().getNextAction());
        } else if (APPLICATION_STATUS_APPROVED.equals(application.getStatus().getCode())
                && !APPLICATION_STATUS_RECORD_APPROVED.equalsIgnoreCase(application.getState().getValue())) {
            workflowContainer.setAmountRule(!application.getSiteDetail().isEmpty() ?application.getSiteDetail().get(0).getExtentinsqmts():new BigDecimal(1));
        }
        workflowContainer.setAdditionalRule(CREATE_ADDITIONAL_RULE_CREATE);
        prepareWorkflow(model, application, workflowContainer);
        model.addAttribute("pendingActions", workflowContainer.getPendingActions());
        model.addAttribute("amountRule", workflowContainer.getAmountRule());
        model.addAttribute("currentState", application.getCurrentState().getValue());
        model.addAttribute(BPA_APPLICATION, application);
        model.addAttribute("nocCheckListDetails", checkListDetailService
                .findActiveCheckListByServiceType(application.getServiceType().getId(), CHECKLIST_TYPE_NOC));
        model.addAttribute("checkListDetailList", checkListDetailService
                .findActiveCheckListByServiceType(application.getServiceType().getId(), BpaConstants.CHECKLIST_TYPE));
    }

    @RequestMapping(value = "/update/{applicationNumber}", method = RequestMethod.POST)
    public String updateApplication(@Valid @ModelAttribute(BPA_APPLICATION) BpaApplication bpaApplication,
            @PathVariable final String applicationNumber,
            final BindingResult resultBinder, final RedirectAttributes redirectAttributes,
            final HttpServletRequest request, final Model model, @RequestParam("files") final MultipartFile[] files) {

        if (resultBinder.hasErrors()) {
            loadViewdata(model, bpaApplication);
            return BPAAPPLICATION_FORM;
        }
        String workFlowAction = request.getParameter("workFlowAction");
        String approvalComent = request.getParameter("approvalComent");
        String message;
        Long approvalPosition = null;
        Position pos = null;
        if (BpaConstants.LETTERTOPARTYSENT.equalsIgnoreCase(bpaApplication.getState().getNextAction()))
            approvalPosition = bpaApplication.getState().getPreviousOwner().getId();
        else if (request.getParameter(APPRIVALPOSITION) != null && !WF_REJECT_BUTTON.equalsIgnoreCase(workFlowAction)
                && !WF_CANCELAPPLICATION_BUTTON.equalsIgnoreCase(workFlowAction)) {
            approvalPosition = Long.valueOf(request.getParameter(APPRIVALPOSITION));
        } else if (WF_REJECT_BUTTON.equalsIgnoreCase(workFlowAction)) {
            for (StateHistory stateHistory : bpaApplication.getStateHistory()) {
                if (BPA_STATUS_SUPERINDENT_APPROVED.equalsIgnoreCase(stateHistory.getValue())) {
                    approvalPosition = stateHistory.getOwnerPosition().getId();
                }
            }
        }
        applicationBpaService.persistOrUpdateApplicationDocument(bpaApplication, resultBinder);
        BpaApplication bpaAppln = applicationBpaService.updateApplication(bpaApplication, approvalPosition, workFlowAction);
        if (null != approvalPosition) {
            pos = positionMasterService.getPositionById(approvalPosition);
        }
        User user = bpaThirdPartyService.getUserPositionByPassingPosition(approvalPosition);
        if (WF_REJECT_BUTTON.equalsIgnoreCase(workFlowAction)) {
            message = messageSource.getMessage("msg.reject.forward.registration", new String[] {
                    user != null ? user.getUsername().concat("~")
                            .concat(pos.getDeptDesig() != null && pos.getDeptDesig().getDesignation() != null
                                    ? pos.getDeptDesig().getDesignation().getName() : "")
                            : "",
                    bpaAppln.getApplicationNumber(), approvalComent }, LocaleContextHolder.getLocale());
        } else if (WF_CANCELAPPLICATION_BUTTON.equalsIgnoreCase(workFlowAction)) {
            message = messageSource.getMessage("msg.cancel.registration", new String[] {
                    bpaAppln.getApplicationNumber(), approvalComent }, LocaleContextHolder.getLocale());
        } else {
            message = messageSource.getMessage("msg.update.forward.registration", new String[] {
                    user != null ? user.getUsername().concat("~")
                            .concat(pos.getDeptDesig() != null && pos.getDeptDesig().getDesignation() != null
                                    ? pos.getDeptDesig().getDesignation().getName() : "")
                            : "",
                    bpaAppln.getApplicationNumber() }, LocaleContextHolder.getLocale());
        }
        model.addAttribute(MESSAGE, message);
        return BPA_APPLICATION_RESULT;
    }
}
