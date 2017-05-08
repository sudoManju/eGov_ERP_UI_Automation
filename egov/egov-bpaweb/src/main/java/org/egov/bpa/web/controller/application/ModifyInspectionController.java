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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.DocketDetail;
import org.egov.bpa.application.entity.Inspection;
import org.egov.bpa.application.service.InspectionService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.eis.web.contract.WorkflowContainer;
import org.egov.infra.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/application")
public class ModifyInspectionController extends BpaGenericApplicationController {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private InspectionService inspectionService;

    @ModelAttribute
    public Inspection getInspectionForBpaAPplication(@PathVariable final String applicationNumber) {
        Inspection inspection = null;
        final BpaApplication bpaApplication = applicationBpaService.findByApplicationNumber(applicationNumber);
        final List<Inspection> inspections = inspectionService.findByBpaApplicationOrderByIdAsc(bpaApplication);
        if (!inspections.isEmpty())
            inspection = inspections.get(0);

        return inspection;
    }

    @RequestMapping(value = "/modify-inspection/{applicationNumber}", method = RequestMethod.GET)
    public String editInspectionAppointment(
            @PathVariable final String applicationNumber, final Model model) {
        loadApplication(model, applicationNumber);
        model.addAttribute("mode", "editinsp");
        return "inspection-edit";
    }

    @RequestMapping(value = "/modify-inspection/{applicationNumber}", method = RequestMethod.POST)
    public String updateInspection(@Valid @ModelAttribute final Inspection inspection,
            @PathVariable final String applicationNumber, final Model model, final BindingResult resultBinder,
            final RedirectAttributes redirectAttributes,
            final HttpServletRequest request) {
        final BpaApplication application = applicationBpaService.findByApplicationNumber(applicationNumber);
        if (resultBinder.hasErrors()) {
            loadApplication(model, applicationNumber);
            return "inspection-edit";
        }
        final List<DocketDetail> docketDetailTempList = new ArrayList<>();
        final List<DocketDetail> docketDetailList = inspectionService.buildDocketDetail(inspection);
        for (final DocketDetail docketDet : inspection.getDocket().get(0).getDocketDetail())
            for (final DocketDetail temploc : docketDetailList)
                if (docketDet.getCheckListDetail().getId().equals(temploc.getCheckListDetail().getId())) {
                    docketDet.setValue(temploc.getValue());
                    docketDet.setRemarks(temploc.getRemarks());
                    docketDetailTempList.add(docketDet);

                }
        inspection.getDocket().get(0).setDocketDetail(docketDetailTempList);
        final Inspection savedInspection = inspectionService.save(inspection, application);
        model.addAttribute("message", "Inspection Saved Successfully");
        return "redirect:/application/view-inspection/" + savedInspection.getId();
    }

    private void loadApplication(final Model model, final String applicationNumber) {
        final BpaApplication application = applicationBpaService.findByApplicationNumber(applicationNumber);
        if (application != null && application.getState() != null
                && application.getState().getValue().equalsIgnoreCase(BpaConstants.BPA_STATUS_SUPERINDENT_APPROVED)) {
            loadViewdata(model, application);
            model.addAttribute("loginUser", securityUtils.getCurrentUser());
            model.addAttribute(BpaConstants.APPLICATION_HISTORY,
                    bpaThirdPartyService.getHistory(application));
        }
        final Inspection inspection = getInspectionForBpaAPplication(applicationNumber);
        if (inspection != null)
            inspection.setInspectionDate(new Date());
       
        inspectionService.buildDocketDetailForModifyAndViewList(inspection,model);
        model.addAttribute("inspection", inspection);
        model.addAttribute(BpaConstants.BPA_APPLICATION, application);
    }

    
    private void loadViewdata(final Model model, final BpaApplication application) {
        model.addAttribute("stateType", application.getClass().getSimpleName());
        final WorkflowContainer workflowContainer = new WorkflowContainer();
        model.addAttribute(BpaConstants.ADDITIONALRULE, BpaConstants.CREATE_ADDITIONAL_RULE_CREATE);
        workflowContainer.setAdditionalRule(BpaConstants.CREATE_ADDITIONAL_RULE_CREATE);
        prepareWorkflow(model, application, workflowContainer);
        model.addAttribute("currentState", application.getCurrentState().getValue());
        model.addAttribute(BpaConstants.BPA_APPLICATION, application);
    }

}
