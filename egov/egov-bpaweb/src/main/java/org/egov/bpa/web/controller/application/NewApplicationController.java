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

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.egov.bpa.application.entity.Applicant;
import org.egov.bpa.application.entity.ApplicationStakeHolder;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.CheckListDetail;
import org.egov.bpa.application.entity.ServiceType;
import org.egov.bpa.application.entity.enums.ApplicantMode;
import org.egov.bpa.application.service.collection.GenericBillGeneratorService;
import org.egov.bpa.service.BpaUtils;
import org.egov.bpa.utils.BpaConstants;
import org.egov.commons.entity.Source;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.admin.master.service.RoleService;
import org.egov.infra.admin.master.service.UserService;
import org.egov.infra.config.properties.ApplicationProperties;
import org.egov.infra.persistence.entity.enums.Gender;
import org.egov.infra.workflow.matrix.entity.WorkFlowMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/application")
public class NewApplicationController extends BpaGenericApplicationController {

    private static final String NEWAPPLICATION_FORM = "newapplication-form";

    @Autowired
    private GenericBillGeneratorService genericBillGeneratorService;

    @Autowired
    private BpaUtils bpaUtils;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/newApplication-newform", method = GET)
    public String showNewApplicationForm(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        bpaApplication.setApplicationDate(new Date());
        bpaApplication.setSource(Source.SYSTEM);
        bpaApplication.setApplicantMode(ApplicantMode.NEW);
        model.addAttribute("mode", "new");
        model.addAttribute("citizenOrBusinessUser", bpaUtils.logedInuseCitizenOrBusinessUser());
        model.addAttribute("genderList", Arrays.asList(Gender.values()));
        return NEWAPPLICATION_FORM;
    }

    @RequestMapping(value = "/newApplication-create", method = POST)
    public String createNewConnection(@Valid @ModelAttribute final BpaApplication bpaApplication,
            final BindingResult resultBinder, final RedirectAttributes redirectAttributes,
            final HttpServletRequest request, final Model model, @RequestParam String workFlowAction,
            final BindingResult errors) {

        Long userPosition = null;
        final WorkFlowMatrix wfmatrix = bpaUtils.getWfMatrixByCurrentState(bpaApplication, BpaConstants.WF_NEW_STATE);
        if (wfmatrix != null)
            userPosition = bpaUtils.getUserPositionByZone(wfmatrix.getNextDesignation(),
                    bpaApplication.getSiteDetail().get(0) != null
                            && bpaApplication.getSiteDetail().get(0).getElectionBoundary() != null ? bpaApplication
                            .getSiteDetail().get(0).getElectionBoundary().getId() : null);
        if (userPosition == 0 || userPosition == null) {
            return redirectOnValidationFailure(model);
        }

        if (!applicationBpaService.checkStakeholderIsValid(bpaApplication)) {
            String message = applicationBpaService.getValidationMessageForBusinessResgistration(bpaApplication);
            model.addAttribute("invalidStakeholder", message);
            model.addAttribute("mode", "new");
            return NEWAPPLICATION_FORM;
        }
        if (validateApplicantDtls_unique_email(bpaApplication.getOwner())!=null){
            model.addAttribute("noJAORSAMessage", "Applicant/User with given emailId already exists.");
            bpaApplication.setApplicationDate(new Date());
            model.addAttribute("mode", "new");
            model.addAttribute("citizenOrBusinessUser", bpaUtils.logedInuseCitizenOrBusinessUser());
            model.addAttribute("genderList", Arrays.asList(Gender.values()));
            return NEWAPPLICATION_FORM;
        }

        workFlowAction = request.getParameter("workFlowAction");
        List<ApplicationStakeHolder> applicationStakeHolders = new ArrayList<>();
        ApplicationStakeHolder applicationStakeHolder = new ApplicationStakeHolder();
        applicationStakeHolder.setApplication(bpaApplication);
        applicationStakeHolder.setStakeHolder(bpaApplication.getStakeHolder().get(0).getStakeHolder());
        applicationStakeHolders.add(applicationStakeHolder);
        bpaApplication.setStakeHolder(applicationStakeHolders);
        applicationBpaService.persistOrUpdateApplicationDocument(bpaApplication, resultBinder);
        bpaApplication.setAdmissionfeeAmount(applicationBpaService.setAdmissionFeeAmountForRegistrationWithAmenities(
                String.valueOf(bpaApplication.getServiceType().getId()), new ArrayList<ServiceType>()));
        bpaApplication.getOwner().setUsername(bpaApplication.getOwner().getEmailId());
        bpaApplication.getOwner().updateNextPwdExpiryDate(applicationProperties.userPasswordExpiryInDays());
        bpaApplication.getOwner().setPassword(passwordEncoder.encode(bpaApplication.getOwner().getMobileNumber()));
        bpaApplication.getOwner().addRole(roleService.getRoleByName(BpaConstants.ROLE_CITIZEN));
        bpaApplication.getOwner().addAddress(bpaApplication.getOwner().getPermanentAddress());
        BpaApplication bpaApplicationRes = applicationBpaService.createNewApplication(bpaApplication, workFlowAction);
        bpaUtils.createPortalUserinbox(bpaApplicationRes,Arrays.asList(bpaApplicationRes.getOwner(),bpaApplicationRes.getStakeHolder().get(0).getStakeHolder()));
        bpaApplicationRes.setCitizenAccepted(true);
        bpaApplicationRes.setArchitectAccepted(true);
        return genericBillGeneratorService.generateBillAndRedirectToCollection(bpaApplicationRes, model);
    }
    
    private User validateApplicantDtls_unique_email(final Applicant owner) {
           return userService.getUserByEmailId(owner.getEmailId());
    }

    private String redirectOnValidationFailure(final Model model) {
        model.addAttribute("noJAORSAMessage", "No Superintendant exists to forward the application.");
        model.addAttribute("mode", "new");
        return NEWAPPLICATION_FORM;
    }

    @RequestMapping(value = "/getdocumentlistbyservicetype", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CheckListDetail> getDocumentsByServiceType(final Model model, @RequestParam final Long serviceType,
            final HttpServletRequest request) {
        return checkListDetailService.findActiveCheckListByServiceType(serviceType, BpaConstants.CHECKLIST_TYPE);
    }

}