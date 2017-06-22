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

import static org.egov.bpa.utils.BpaConstants.CHECKLIST_TYPE_NOC;
import static org.egov.bpa.utils.BpaConstants.CREATE_ADDITIONAL_RULE_CREATE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.egov.bpa.application.entity.Applicant;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.service.InspectionService;
import org.egov.bpa.application.service.LettertoPartyService;
import org.egov.bpa.application.service.collection.GenericBillGeneratorService;
import org.egov.bpa.service.BpaUtils;
import org.egov.bpa.utils.BpaConstants;
import org.egov.bpa.web.controller.application.BpaGenericApplicationController;
import org.egov.infra.admin.master.entity.AppConfigValues;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.admin.master.service.AppConfigValueService;
import org.egov.infra.admin.master.service.UserService;
import org.egov.infra.persistence.entity.PermanentAddress;
import org.egov.infra.workflow.matrix.entity.WorkFlowMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class CitizenUpdateApplicationController extends BpaGenericApplicationController {
    private static final String BPA_APPLICATION = "bpaApplication";

    private static final String BPA_APPLICATION_RESULT = "bpa-application-result";

    private static final String APPLICATION_HISTORY = "applicationHistory";

    private static final String ADDITIONALRULE = "additionalRule";
    @Autowired
    private GenericBillGeneratorService genericBillGeneratorService;
    @Autowired
    private BpaUtils bpaUtils;
    @Autowired
    LettertoPartyService lettertoPartyService;
    @Autowired
    private InspectionService inspectionService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private AppConfigValueService appConfigValueService;

    @ModelAttribute
    public BpaApplication getBpaApplication(@PathVariable final String applicationNumber) {
        return applicationBpaService.findByApplicationNumber(applicationNumber);
    }

    @RequestMapping(value = "/citizen/update/{applicationNumber}", method = RequestMethod.GET)
    public String updateApplicationForm(final Model model, @PathVariable final String applicationNumber,
            final HttpServletRequest request) {
        final BpaApplication application = getBpaApplication(applicationNumber);
        model.addAttribute("citizenOrBusinessUser", bpaUtils.logedInuseCitizenOrBusinessUser());
        model.addAttribute("isCitizen", bpaUtils.logedInuserIsCitizen());
        model.addAttribute("mode", "newappointment");
        model.addAttribute(APPLICATION_HISTORY, bpaThirdPartyService.getHistory(application));
        loadViewdata(model, application);
        if (application.getStatus() != null
                && application.getStatus().getCode().equals(BpaConstants.APPLICATION_STATUS_CREATED))
            return "bpaapp-citizenForm";
        else
            return "citizen-view";
    }

    private void loadViewdata(final Model model, final BpaApplication application) {
        model.addAttribute("stateType", application.getClass().getSimpleName());
        model.addAttribute(ADDITIONALRULE, CREATE_ADDITIONAL_RULE_CREATE);
        model.addAttribute(BPA_APPLICATION, application);
        // prepareWorkflow(model, application, workflowContainer);
        List<AppConfigValues> appConfigValueList = appConfigValueService.getConfigValuesByModuleAndKey(
                BpaConstants.APPLICATION_MODULE_TYPE, BpaConstants.BPA_CITIZENACCEPTANCE_CHECK);
        String validateCitizenAcceptance = !appConfigValueList.isEmpty() ? appConfigValueList.get(0).getValue() : "";
        model.addAttribute("validateCitizenAcceptance",
                (validateCitizenAcceptance.equalsIgnoreCase("YES") ? Boolean.TRUE : Boolean.FALSE));
        if (validateCitizenAcceptance != null) {
            model.addAttribute("citizenDisclaimerAccepted", application.isCitizenAccepted());
        }
        String enableOrDisablePayOnline = bpaUtils.getAppconfigValueByKeyName(BpaConstants.ENABLEONLINEPAYMENT);
        model.addAttribute("onlinePaymentEnable",
                (enableOrDisablePayOnline.equalsIgnoreCase("YES") ? Boolean.TRUE : Boolean.FALSE));
        model.addAttribute("currentState",
                application.getCurrentState() != null ? application.getCurrentState().getValue() : "");
        model.addAttribute(BPA_APPLICATION, application);
        model.addAttribute("nocCheckListDetails", checkListDetailService
                .findActiveCheckListByServiceType(application.getServiceType().getId(), CHECKLIST_TYPE_NOC));
        model.addAttribute("checkListDetailList", checkListDetailService
                .findActiveCheckListByServiceType(application.getServiceType().getId(), BpaConstants.CHECKLIST_TYPE));
        model.addAttribute("applicationDocumentList", application.getApplicationDocument());
        model.addAttribute("isFeeCollected", bpaDemandService.checkAnyTaxIsPendingToCollect(application));
        model.addAttribute("lettertopartylist", lettertoPartyService.findByBpaApplicationOrderByIdDesc(application));
        model.addAttribute("inspectionList", inspectionService.findByBpaApplicationOrderByIdAsc(application));
        application.getOwner().setPermanentAddress((PermanentAddress) application.getOwner().getAddress().get(0));
    }

    @RequestMapping(value = "/citizen/update/{applicationNumber}", method = RequestMethod.POST)
    public String updateApplication(@Valid @ModelAttribute("") BpaApplication bpaApplication,
            @PathVariable final String applicationNumber, final BindingResult resultBinder,
            final RedirectAttributes redirectAttributes, final HttpServletRequest request, final Model model,
            @RequestParam("files") final MultipartFile[] files, @RequestParam String workFlowAction) {

        User dbUser = validateApplicantDtls_unique_email(bpaApplication.getOwner());
        if (dbUser != null && dbUser.getId() != bpaApplication.getOwner().getId()) {
            model.addAttribute("noJAORSAMessage", "Applicant/User with given emailId already exists.");
            if (bpaApplication.getStatus() != null
                    && bpaApplication.getStatus().getCode().equals(BpaConstants.APPLICATION_STATUS_CREATED))
                return "bpaapp-citizenForm";
            else
				return "citizen-view";
		 } 

		if (resultBinder.hasErrors()) {
			loadViewdata(model, bpaApplication);
			if (bpaApplication.getStatus() != null
					&& bpaApplication.getStatus().getCode().equals(BpaConstants.APPLICATION_STATUS_CREATED))
				return "bpaapp-citizenForm";
			else 
				return "citizen-view";
		}
		workFlowAction = request.getParameter("workFlowAction");
		Long approvalPosition = null;
		applicationBpaService.persistOrUpdateApplicationDocument(bpaApplication, resultBinder);
		bpaApplication.getBuildingDetail().get(0)
				.setApplicationFloorDetails(applicationBpaService.buildApplicationFloorDetails(bpaApplication));
		String enableOrDisablePayOnline=bpaUtils.getAppconfigValueByKeyName(BpaConstants.ENABLEONLINEPAYMENT);
		if (workFlowAction != null
				&& workFlowAction
						.equals(BpaConstants.WF_SURVEYOR_FORWARD_BUTTON)
				&& enableOrDisablePayOnline.equalsIgnoreCase("YES")) {
			return genericBillGeneratorService
					.generateBillAndRedirectToCollection(bpaApplication, model);
		} 
		if (workFlowAction != null
				&& (workFlowAction.equals(BpaConstants.WF_SURVEYOR_FORWARD_BUTTON)
						|| BpaConstants.WF_CANCELAPPLICATION_BUTTON.equalsIgnoreCase(workFlowAction))
				&& (bpaUtils.logedInuseCitizenOrBusinessUser())) {
			if (workFlowAction.equals(BpaConstants.WF_SURVEYOR_FORWARD_BUTTON)) {
				final WorkFlowMatrix wfmatrix = bpaUtils.getWfMatrixByCurrentState(bpaApplication,
						BpaConstants.WF_NEW_STATE);
				if (wfmatrix != null)
					approvalPosition = bpaUtils.getUserPositionByZone(wfmatrix.getNextDesignation(),
							bpaApplication.getSiteDetail().get(0) != null
									&& bpaApplication.getSiteDetail().get(0).getElectionBoundary() != null
											? bpaApplication.getSiteDetail().get(0).getElectionBoundary().getId()
											: null);
				bpaUtils.redirectToBpaWorkFlow(approvalPosition, bpaApplication, BpaConstants.WF_NEW_STATE, null, null,
						null);

			}
			if (BpaConstants.WF_CANCELAPPLICATION_BUTTON.equalsIgnoreCase(workFlowAction)) {
				bpaApplication.setStatus(
						applicationBpaService.getStatusByCodeAndModuleType(BpaConstants.APPLICATION_STATUS_CANCELLED));
				bpaUtils.updatePortalUserinbox(bpaApplication,null);

			}
		}
		bpaApplication.getOwner().setUsername(bpaApplication.getOwner().getEmailId());
        bpaApplication.getOwner().setPassword(passwordEncoder.encode(bpaApplication.getOwner().getMobileNumber()));
        bpaApplication.getOwner().addAddress(bpaApplication.getOwner().getPermanentAddress());
        applicationBpaService.saveAndFlushApplication(bpaApplication);
        bpaUtils.updatePortalUserinbox(bpaApplication, null);
        bpaUtils.sendSmsEmailOnCitizenSubmit(bpaApplication, workFlowAction);
        return BPA_APPLICATION_RESULT;
    }

    private User validateApplicantDtls_unique_email(final Applicant owner) {
        return userService.getUserByEmailId(owner.getEmailId());
    }

}
