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
package org.egov.bpa.web.controller.application.citizen;

import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_CANCELLED;
import static org.egov.bpa.utils.BpaConstants.CHECKLIST_TYPE_NOC;
import static org.egov.bpa.utils.BpaConstants.CREATE_ADDITIONAL_RULE_CREATE;
import static org.egov.bpa.utils.BpaConstants.DISCLIMER_MESSAGE_ONSAVE;
import static org.egov.bpa.utils.BpaConstants.ENABLEONLINEPAYMENT;
import static org.egov.bpa.utils.BpaConstants.WF_CANCELAPPLICATION_BUTTON;
import static org.egov.bpa.utils.BpaConstants.WF_NEW_STATE;
import static org.egov.bpa.utils.BpaConstants.WF_SURVEYOR_FORWARD_BUTTON;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.service.InspectionService;
import org.egov.bpa.application.service.LettertoPartyService;
import org.egov.bpa.application.service.collection.GenericBillGeneratorService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.bpa.web.controller.application.BpaGenericApplicationController;
import org.egov.eis.service.PositionMasterService;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.persistence.entity.PermanentAddress;
import org.egov.infra.workflow.matrix.entity.WorkFlowMatrix;
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

@Controller
@RequestMapping(value = "/application")
public class CitizenUpdateApplicationController extends BpaGenericApplicationController {
    private static final String IS_CITIZEN = "isCitizen";
    private static final String CITIZEN_VIEW = "citizen-view";
    private static final String BPAAPP_CITIZEN_FORM = "bpaapp-citizenForm";
    private static final String MESSAGE = "message";
    private static final String BPA_APPLICATION = "bpaApplication";
    private static final String APPLICATION_HISTORY = "applicationHistory";
    private static final String ADDITIONALRULE = "additionalRule";
    private static final String BPAAPPLICATION_CITIZEN = "citizen_suceess";
    @Autowired
    private GenericBillGeneratorService genericBillGeneratorService;
    @Autowired
    LettertoPartyService lettertoPartyService;
    @Autowired
    private InspectionService inspectionService;
    @Autowired
    private PositionMasterService positionMasterService;

    @ModelAttribute
    public BpaApplication getBpaApplication(@PathVariable final String applicationNumber) {
        return applicationBpaService.findByApplicationNumber(applicationNumber);
    }

    @RequestMapping(value = "/citizen/update/{applicationNumber}", method = RequestMethod.GET)
    public String updateApplicationForm(final Model model, @PathVariable final String applicationNumber,
            final HttpServletRequest request) {
        final BpaApplication application = getBpaApplication(applicationNumber);
        model.addAttribute("mode", "newappointment");
        model.addAttribute(APPLICATION_HISTORY, bpaThirdPartyService.getHistory(application));
        prepareCommonModelAttribute(model, application);
        return loadViewdata(model, application);
    }

    private String loadViewdata(final Model model, final BpaApplication application) {
        buildReceiptDetails(application);
        applicationBpaService.buildApplicationFloorDetailsForUpdate(application);
        model.addAttribute("stateType", application.getClass().getSimpleName());
        model.addAttribute(ADDITIONALRULE, CREATE_ADDITIONAL_RULE_CREATE);
        model.addAttribute(BPA_APPLICATION, application);
        model.addAttribute("currentState",
                application.getCurrentState() != null ? application.getCurrentState().getValue() : "");
        model.addAttribute("nocCheckListDetails", checkListDetailService
                .findActiveCheckListByServiceType(application.getServiceType().getId(), CHECKLIST_TYPE_NOC));
        model.addAttribute("checkListDetailList", checkListDetailService
                .findActiveCheckListByServiceType(application.getServiceType().getId(), BpaConstants.CHECKLIST_TYPE));
        model.addAttribute("applicationDocumentList", application.getApplicationDocument());
        model.addAttribute("isFeeCollected", bpaDemandService.checkAnyTaxIsPendingToCollect(application));
        model.addAttribute("lettertopartylist", lettertoPartyService.findByBpaApplicationOrderByIdDesc(application));
        model.addAttribute("inspectionList", inspectionService.findByBpaApplicationOrderByIdAsc(application));
        application.getOwner().setPermanentAddress((PermanentAddress) application.getOwner().getUser().getAddress().get(0));
        model.addAttribute("admissionFee", applicationBpaService.setAdmissionFeeAmountForRegistrationWithAmenities(
                application.getServiceType().getId(), application.getApplicationAmenity()));
        Boolean isCitizen = (Boolean) model.asMap().get(IS_CITIZEN);
        Boolean validateCitizenAcceptance = (Boolean) model.asMap().get("validateCitizenAcceptance");
        if (application.getStatus() != null
                && application.getStatus().getCode().equals(BpaConstants.APPLICATION_STATUS_CREATED) &&
                (!isCitizen || (isCitizen && (validateCitizenAcceptance && !application.isCitizenAccepted()))))
            return BPAAPP_CITIZEN_FORM;
        else
            return CITIZEN_VIEW;
    }

    @RequestMapping(value = "/citizen/update/{applicationNumber}", method = RequestMethod.POST)
    public String updateApplication(@Valid @ModelAttribute("") BpaApplication bpaApplication,
            @PathVariable final String applicationNumber, final BindingResult resultBinder,
            final HttpServletRequest request, final Model model,
            @RequestParam("files") final MultipartFile[] files) {
        if (resultBinder.hasErrors()) {
            prepareCommonModelAttribute(model, bpaApplication);
            return loadViewdata(model, bpaApplication);
        }
        if (bpaApplicationValidationService.validateBuildingDetails(bpaApplication, model)) {
            prepareCommonModelAttribute(model, bpaApplication);
            return loadViewdata(model, bpaApplication);
        }
        String workFlowAction = request.getParameter("workFlowAction");
        Long approvalPosition = null;
        if (!bpaApplication.getApplicationDocument().isEmpty())
            applicationBpaService.persistOrUpdateApplicationDocument(bpaApplication);
        applicationBpaService.buildApplicationFloorDetailsForUpdate(bpaApplication);
        String enableOrDisablePayOnline = bpaUtils.getAppconfigValueByKeyName(ENABLEONLINEPAYMENT);
        if (workFlowAction != null
                && workFlowAction
                        .equals(WF_SURVEYOR_FORWARD_BUTTON)
                && enableOrDisablePayOnline.equalsIgnoreCase("YES") && !bpaUtils.logedInuserIsCitizen()) {
            return genericBillGeneratorService
                    .generateBillAndRedirectToCollection(bpaApplication, model);
        }
        if (workFlowAction != null && !bpaUtils.logedInuserIsCitizen()
                && (workFlowAction.equals(WF_SURVEYOR_FORWARD_BUTTON)
                        || WF_CANCELAPPLICATION_BUTTON.equalsIgnoreCase(workFlowAction))
                && (bpaUtils.logedInuseCitizenOrBusinessUser())) {
            if (workFlowAction.equals(WF_SURVEYOR_FORWARD_BUTTON)) {
                final WorkFlowMatrix wfmatrix = bpaUtils.getWfMatrixByCurrentState(bpaApplication,
                        WF_NEW_STATE);
                if (wfmatrix != null)
                    approvalPosition = bpaUtils.getUserPositionIdByZone(wfmatrix.getNextDesignation(),
                            bpaApplication.getSiteDetail().get(0) != null
                                    && bpaApplication.getSiteDetail().get(0).getElectionBoundary() != null
                                            ? bpaApplication.getSiteDetail().get(0).getElectionBoundary().getId()
                                            : null);
                bpaUtils.redirectToBpaWorkFlow(approvalPosition, bpaApplication, WF_NEW_STATE, null, null,
                        null);

            }
            if (WF_CANCELAPPLICATION_BUTTON.equalsIgnoreCase(workFlowAction)) {
                bpaApplication.setStatus(
                        applicationBpaService.getStatusByCodeAndModuleType(APPLICATION_STATUS_CANCELLED));
                bpaUtils.updatePortalUserinbox(bpaApplication, null);
            }
        }
        if (bpaApplication.getOwner().getUser() != null && bpaApplication.getOwner().getUser().getId() == null)
            buildOwnerDetails(bpaApplication);
        applicationBpaService.saveAndFlushApplication(bpaApplication);
        bpaUtils.updatePortalUserinbox(bpaApplication, null);
        if (workFlowAction != null
                && workFlowAction
                        .equals(WF_SURVEYOR_FORWARD_BUTTON)
                && !bpaUtils.logedInuserIsCitizen()) {
            Position pos = positionMasterService.getPositionById(bpaApplication.getCurrentState().getOwnerPosition().getId());
            User wfUser = bpaThirdPartyService.getUserPositionByPassingPosition(pos.getId());
            String message = messageSource.getMessage("msg.portal.forward.registration", new String[] {
                    wfUser != null ? wfUser.getUsername().concat("~")
                            .concat(getDesinationNameByPosition(pos))
                            : "",
                    bpaApplication.getApplicationNumber() }, LocaleContextHolder.getLocale());
            model.addAttribute(MESSAGE, message.concat(DISCLIMER_MESSAGE_ONSAVE));
        } else if (workFlowAction != null && workFlowAction.equals(WF_CANCELAPPLICATION_BUTTON)) {
            model.addAttribute(MESSAGE,
                    bpaApplication.getApplicationNumber() + " Application Cancelled Successfully.");
        } else
            model.addAttribute(MESSAGE,
                    "Sucessfully saved with ApplicationNumber " + bpaApplication.getApplicationNumber());
        if (workFlowAction != null && workFlowAction.equals(WF_SURVEYOR_FORWARD_BUTTON))
            bpaUtils.sendSmsEmailOnCitizenSubmit(bpaApplication);
        return BPAAPPLICATION_CITIZEN;
    }

}
