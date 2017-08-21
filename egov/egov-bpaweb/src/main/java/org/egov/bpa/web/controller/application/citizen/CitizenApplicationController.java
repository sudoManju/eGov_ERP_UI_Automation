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

import static org.egov.bpa.utils.BpaConstants.DISCLIMER_MESSAGE_ONSAVE;
import static org.egov.bpa.utils.BpaConstants.WF_SURVEYOR_FORWARD_BUTTON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.egov.bpa.application.entity.ApplicationDocument;
import org.egov.bpa.application.entity.ApplicationStakeHolder;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.CheckListDetail;
import org.egov.bpa.application.entity.ServiceType;
import org.egov.bpa.application.entity.StakeHolder;
import org.egov.bpa.application.entity.enums.ApplicantMode;
import org.egov.bpa.application.service.collection.GenericBillGeneratorService;
import org.egov.bpa.masters.service.ServiceTypeService;
import org.egov.bpa.masters.service.StakeHolderService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.bpa.web.controller.application.BpaGenericApplicationController;
import org.egov.commons.entity.Source;
import org.egov.eis.service.PositionMasterService;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.workflow.matrix.entity.WorkFlowMatrix;
import org.egov.pims.commons.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/application/citizen")
public class CitizenApplicationController extends BpaGenericApplicationController {

    private static final String ONLINE_PAYMENT_ENABLE = "onlinePaymentEnable";

    private static final String WORK_FLOW_ACTION = "workFlowAction";

    private static final String TRUE = "TRUE";

    private static final String CITIZEN_OR_BUSINESS_USER = "citizenOrBusinessUser";

    private static final String IS_CITIZEN = "isCitizen";

    private static final String SUPERINTENDANT_NOT_EXISTS = "No Superintendant exists to forward the application.";

    private static final String MSG_PORTAL_FORWARD_REGISTRATION = "msg.portal.forward.registration";

    private static final String MESSAGE = "message";

    private static final String BPAAPPLICATION_CITIZEN = "citizen_suceess";

    @Autowired
    private ServiceTypeService serviceTypeService;
    @Autowired
    private StakeHolderService stakeHolderService;
    @Autowired
    private GenericBillGeneratorService genericBillGeneratorService;
    @Autowired
    private PositionMasterService positionMasterService;

    @RequestMapping(value = "/newconstruction-form", method = GET)
    public String showNewApplicationForm(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_01);
    }

    private void setCityName(final Model model, final HttpServletRequest request) {
        if (request.getSession().getAttribute("cityname") != null)
            model.addAttribute("cityName", (String) request.getSession().getAttribute("cityname"));
    }

    private String loadNewForm(final BpaApplication bpaApplication, final Model model, String serviceCode) {
        bpaApplication.setApplicationDate(new Date());
        prepareCommonModelAttribute(model, bpaApplication);
        model.addAttribute("mode", "new");

        bpaApplication.setSource(Source.CITIZENPORTAL);
        bpaApplication.setApplicantMode(ApplicantMode.NEW);
        bpaApplication.setServiceType(serviceTypeService.getServiceTypeByCode(serviceCode));

        model.addAttribute("checkListDetailList",
                checkListDetailService.findActiveCheckListByServiceType(bpaApplication.getServiceType().getId(),
                        BpaConstants.CHECKLIST_TYPE));
        List<CheckListDetail> checkListDetail = checkListDetailService.findActiveCheckListByServiceType(
                bpaApplication.getServiceType().getId(),
                BpaConstants.CHECKLIST_TYPE);
        List<ApplicationDocument> appDocList = new ArrayList<>();
        for (CheckListDetail checkdet : checkListDetail) {
            ApplicationDocument appdoc = new ApplicationDocument();
            appdoc.setChecklistDetail(checkdet);
            appDocList.add(appdoc);
        }
        model.addAttribute("applicationDocumentList", appDocList);
        return "citizenApplication-form";
    }

    @RequestMapping(value = "/demolition-form", method = GET)
    public String showDemolition(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_02);
    }

    @RequestMapping(value = "/reconstruction-form", method = GET)
    public String showReconstruction(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_03);
    }

    @RequestMapping(value = "/alteration-form", method = GET)
    public String showAlteration(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_04);
    }

    @RequestMapping(value = "/subdevland-form", method = GET)
    public String showSubDevlisionOfLand(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_05);
    }

    @RequestMapping(value = "/addextnew-form", method = GET)
    public String loadAddOfExtection(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_06);
    }

    @RequestMapping(value = "/changeofoccupancy-form", method = GET)
    public String showChangeOfOccupancy(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_07);
    }

    @RequestMapping(value = "/permissionhutorshud-form", method = GET)
    public String loadPermOfHutOrShud(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_09);
    }

    @RequestMapping(value = "/amenity-form", method = GET)
    public String loadAmenity(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_08);
    }

    @RequestMapping(value = "/towerconstruction-form", method = GET)
    public String loadTowerConstruction(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_14);
    }

    @RequestMapping(value = "/polestructures-form", method = GET)
    public String loadPoleStruture(@ModelAttribute final BpaApplication bpaApplication, final Model model,
            final HttpServletRequest request) {
        setCityName(model, request);
        return loadNewForm(bpaApplication, model, BpaConstants.ST_CODE_15);
    }

    @RequestMapping(value = "/application-create", method = POST)
    public String createNewConnection(@Valid @ModelAttribute final BpaApplication bpaApplication,
            final BindingResult resultBinder,
            final HttpServletRequest request, final Model model,
            final BindingResult errors) {

        if (bpaApplicationValidationService.validateBuildingDetails(bpaApplication, model)) {
            applicationBpaService.buildApplicationFloorDetails(bpaApplication);
            prepareCommonModelAttribute(model, bpaApplication);
            return loadNewForm(bpaApplication, model, bpaApplication.getServiceType().getCode());
        }
        Long userPosition = null;
        String workFlowAction = request.getParameter(WORK_FLOW_ACTION);
        Boolean isCitizen = request.getParameter(IS_CITIZEN) != null
                && request.getParameter(IS_CITIZEN)
                        .equalsIgnoreCase(TRUE) ? Boolean.TRUE : Boolean.FALSE;
        Boolean citizenOrBusinessUser = request.getParameter(CITIZEN_OR_BUSINESS_USER) != null
                && request.getParameter(CITIZEN_OR_BUSINESS_USER)
                        .equalsIgnoreCase(TRUE) ? Boolean.TRUE : Boolean.FALSE;
        Boolean onlinePaymentEnable = request.getParameter(ONLINE_PAYMENT_ENABLE) != null
                && request.getParameter(ONLINE_PAYMENT_ENABLE)
                        .equalsIgnoreCase(TRUE) ? Boolean.TRUE : Boolean.FALSE;
        final WorkFlowMatrix wfmatrix = bpaUtils.getWfMatrixByCurrentState(bpaApplication, BpaConstants.WF_NEW_STATE);
        if (wfmatrix != null)
            userPosition = bpaUtils.getUserPositionIdByZone(wfmatrix.getNextDesignation(),
                    bpaApplication.getSiteDetail().get(0) != null
                            && bpaApplication.getSiteDetail().get(0).getElectionBoundary() != null
                                    ? bpaApplication.getSiteDetail().get(0).getElectionBoundary().getId() : null);
        if (citizenOrBusinessUser && workFlowAction != null
                && workFlowAction.equals(WF_SURVEYOR_FORWARD_BUTTON)
                && (userPosition == 0 || userPosition == null)) {
            model.addAttribute("noJAORSAMessage", SUPERINTENDANT_NOT_EXISTS);
            return loadNewForm(bpaApplication, model, bpaApplication.getServiceType().getCode());
        }
        if (citizenOrBusinessUser) {
            if (isCitizen) {
                List<ApplicationStakeHolder> applicationStakeHolders = new ArrayList<>();
                ApplicationStakeHolder applicationStakeHolder = new ApplicationStakeHolder();
                applicationStakeHolder.setApplication(bpaApplication);
                applicationStakeHolder.setStakeHolder(bpaApplication
                        .getStakeHolder().get(0).getStakeHolder());
                applicationStakeHolders.add(applicationStakeHolder);
                bpaApplication.setStakeHolder(applicationStakeHolders);
            } else {
                User user = securityUtils.getCurrentUser();
                StakeHolder stakeHolder = stakeHolderService.findById(user
                        .getId());
                ApplicationStakeHolder applicationStakeHolder = new ApplicationStakeHolder();
                applicationStakeHolder.setApplication(bpaApplication);
                applicationStakeHolder.setStakeHolder(stakeHolder);
                bpaApplication.getStakeHolder().add(applicationStakeHolder);
                if (!applicationBpaService
                        .checkStakeholderIsValid(bpaApplication)) {
                    String message = applicationBpaService
                            .getValidationMessageForBusinessResgistration(bpaApplication);
                    model.addAttribute("invalidStakeholder", message);
                    return loadNewForm(bpaApplication, model, bpaApplication
                            .getServiceType().getCode());
                }
            }
        }
        applicationBpaService.persistOrUpdateApplicationDocument(bpaApplication, resultBinder);
        if (workFlowAction != null
                && workFlowAction
                        .equals(WF_SURVEYOR_FORWARD_BUTTON)
                && onlinePaymentEnable) {
            return genericBillGeneratorService
                    .generateBillAndRedirectToCollection(bpaApplication, model);
        }
        bpaApplication.setAdmissionfeeAmount(applicationBpaService.setAdmissionFeeAmountForRegistrationWithAmenities(
                bpaApplication.getServiceType().getId(), new ArrayList<ServiceType>()));
        if (bpaApplication.getOwner().getUser() != null && bpaApplication.getOwner().getUser().getId() == null) {
            buildOwnerDetails(bpaApplication);
        }
        BpaApplication bpaApplicationRes = applicationBpaService.createNewApplication(bpaApplication, workFlowAction);
        if (citizenOrBusinessUser) {
            if (isCitizen)
                bpaUtils.createPortalUserinbox(bpaApplicationRes, Arrays.asList(bpaApplicationRes.getOwner().getUser(),
                        bpaApplicationRes.getStakeHolder().get(0).getStakeHolder()), workFlowAction);
            else
                bpaUtils.createPortalUserinbox(bpaApplicationRes,
                        Arrays.asList(bpaApplicationRes.getOwner().getUser(), securityUtils.getCurrentUser()), workFlowAction);
            if (workFlowAction != null
                    && workFlowAction
                            .equals(WF_SURVEYOR_FORWARD_BUTTON)) {
                Position pos = positionMasterService
                        .getPositionById(bpaApplicationRes.getCurrentState().getOwnerPosition().getId());
                User wfUser = bpaThirdPartyService.getUserPositionByPassingPosition(pos.getId());
                String message = messageSource.getMessage(MSG_PORTAL_FORWARD_REGISTRATION, new String[] {
                        wfUser != null ? wfUser.getUsername().concat("~")
                                .concat(getDesinationNameByPosition(pos))
                                : "",
                        bpaApplicationRes.getApplicationNumber() }, LocaleContextHolder.getLocale());
                model.addAttribute(MESSAGE, message.concat(DISCLIMER_MESSAGE_ONSAVE));
            } else {
                model.addAttribute(MESSAGE,
                        "Sucessfully saved with ApplicationNumber " + bpaApplicationRes.getApplicationNumber() + ".");
            }
            bpaUtils.sendSmsEmailOnCitizenSubmit(bpaApplication);
        }
        return BPAAPPLICATION_CITIZEN;
    }
}