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

import static org.egov.ptis.constants.PropertyTaxConstants.ADMIN_HIERARCHY_TYPE;
import static org.egov.ptis.constants.PropertyTaxConstants.WARD;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.BpaScheme;
import org.egov.bpa.application.entity.BpaStatus;
import org.egov.bpa.application.entity.BuildingCategory;
import org.egov.bpa.application.entity.ConstructionStages;
import org.egov.bpa.application.entity.Occupancy;
import org.egov.bpa.application.entity.ServiceType;
import org.egov.bpa.application.entity.VillageName;
import org.egov.bpa.application.entity.enums.ApplicantMode;
import org.egov.bpa.application.entity.enums.BpaUom;
import org.egov.bpa.application.entity.enums.GovernmentType;
import org.egov.bpa.application.entity.enums.StakeHolderType;
import org.egov.bpa.application.service.ApplicationBpaService;
import org.egov.bpa.application.service.BpaApplicationValidationService;
import org.egov.bpa.application.service.BuildingFloorDetailsService;
import org.egov.bpa.application.service.CheckListDetailService;
import org.egov.bpa.application.service.ExistingBuildingFloorDetailsService;
import org.egov.bpa.application.workflow.BpaWorkFlowService;
import org.egov.bpa.masters.service.BpaSchemeService;
import org.egov.bpa.masters.service.BuildingCategoryService;
import org.egov.bpa.masters.service.ConstructionStagesService;
import org.egov.bpa.masters.service.OccupancyService;
import org.egov.bpa.masters.service.ServiceTypeService;
import org.egov.bpa.masters.service.VillageNameService;
import org.egov.bpa.service.BpaDemandService;
import org.egov.bpa.service.BpaStatusService;
import org.egov.bpa.service.BpaThirdPartyService;
import org.egov.bpa.service.BpaUtils;
import org.egov.bpa.utils.BpaConstants;
import org.egov.dcb.bean.Receipt;
import org.egov.demand.model.EgDemandDetails;
import org.egov.demand.model.EgdmCollectedReceipt;
import org.egov.eis.web.contract.WorkflowContainer;
import org.egov.eis.web.controller.workflow.GenericWorkFlowController;
import org.egov.infra.admin.master.entity.AppConfigValues;
import org.egov.infra.admin.master.entity.Boundary;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.admin.master.service.AppConfigValueService;
import org.egov.infra.admin.master.service.BoundaryService;
import org.egov.infra.admin.master.service.UserService;
import org.egov.infra.filestore.service.FileStoreService;
import org.egov.infra.persistence.entity.enums.UserType;
import org.egov.infra.security.utils.SecurityUtils;
import org.egov.infra.utils.FileStoreUtils;
import org.egov.infra.workflow.entity.StateAware;
import org.egov.pims.commons.Position;
import org.egov.ptis.constants.PropertyTaxConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BpaGenericApplicationController extends GenericWorkFlowController {

    @Autowired
    private BoundaryService boundaryService;
    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private OccupancyService occupancyService;

    @Autowired
    private VillageNameService villageNameService;

    @Autowired
    private ConstructionStagesService constructionStagesService;

    @Autowired
    protected CheckListDetailService checkListDetailService;
    @Autowired
    @Qualifier("fileStoreService")
    protected FileStoreService fileStoreService;
    @Autowired
    private BuildingCategoryService buildingCategoryService;
    @Autowired
    protected ApplicationBpaService applicationBpaService;
    @Autowired
    protected BpaThirdPartyService bpaThirdPartyService;
    @Autowired
    protected FileStoreUtils fileStoreUtils;
    @Autowired
    protected BpaDemandService bpaDemandService;
    @Autowired
    protected BpaWorkFlowService bpaWorkFlowService;
    @Autowired
    protected ResourceBundleMessageSource messageSource;
    @Autowired
    protected BpaStatusService bpaStatusService;
    @Autowired
    protected BpaSchemeService bpaSchemeService;
    @Autowired
    private AppConfigValueService appConfigValueService;
    @Autowired
    protected BpaUtils bpaUtils;
    @Autowired
    protected UserService userService;
    @Autowired
    protected SecurityUtils securityUtils;
    @Autowired
    protected BpaApplicationValidationService bpaApplicationValidationService;
    @Autowired
    protected BuildingFloorDetailsService proposedBuildingFloorDetailsService;
    @Autowired
    protected ExistingBuildingFloorDetailsService existingBuildingFloorDetailsService;

    @ModelAttribute("occupancyList")
    public List<Occupancy> getOccupancy() {
        return occupancyService.findAllOrderByOrderNumber();
    }

    @ModelAttribute("zones")
    public List<Boundary> zones() {
        return boundaryService.getActiveBoundariesByBndryTypeNameAndHierarchyTypeName(BpaConstants.ZONE,
                PropertyTaxConstants.REVENUE_HIERARCHY_TYPE);
    }

    @ModelAttribute("serviceTypeList")
    public List<ServiceType> getServiceTypeList() {
        return serviceTypeService.getAllActiveMainServiceTypes();
    }

    @ModelAttribute("amenityTypeList")
    public List<ServiceType> getAmenityTypeList() {
        return serviceTypeService.getAllActiveAmenities();
    }

    @ModelAttribute("buildingCategorYlist")
    public List<BuildingCategory> getAllBuildingCategoryList() {
        return buildingCategoryService.findAll();
    }

    @ModelAttribute("stakeHolderTypeList")
    public List<StakeHolderType> getStakeHolderType() {
        return Arrays.asList(StakeHolderType.values());
    }

    @ModelAttribute("governmentTypeList")
    public List<GovernmentType> getGovernmentType() {
        return Arrays.asList(GovernmentType.values());
    }

    @ModelAttribute("villageNames")
    public List<VillageName> getVillage() {
        return villageNameService.findAll();
    }

    @ModelAttribute("constStages")
    public List<ConstructionStages> getCOnstructionStage() {
        return constructionStagesService.findAll();
    }

    @ModelAttribute("electionwards")
    public List<Boundary> wards() {

        return boundaryService
                .getActiveBoundariesByBndryTypeNameAndHierarchyTypeName(WARD, ADMIN_HIERARCHY_TYPE);
    }

    @ModelAttribute("wards")
    public List<Boundary> adminWards() {
        return boundaryService.getActiveBoundariesByBndryTypeNameAndHierarchyTypeName(WARD,
                BpaConstants.REVENUE_HIERARCHY_TYPE);
    }

    @ModelAttribute("street")
    public List<Boundary> blocks() {
        return boundaryService.getActiveBoundariesByBndryTypeNameAndHierarchyTypeName(BpaConstants.STREET,
                PropertyTaxConstants.REVENUE_HIERARCHY_TYPE);
    }

    @ModelAttribute("localitys")
    public List<Boundary> localitys() {
        return boundaryService
                .getActiveBoundariesByBndryTypeNameAndHierarchyTypeName(BpaConstants.LOCALITY,
                        BpaConstants.LOCATION_HIERARCHY_TYPE);
    }

    @ModelAttribute("applicationModes")
    public Map<String, String> applicationModes() {
        return getApplicationModeMap();
    }

    @ModelAttribute("buildingFloorList")
    public List<String> getBuildingFLoorList() {
        return BpaConstants.getBuildingFloorsList();
    }

    @ModelAttribute("uomList")
    public BpaUom[] getUomList() {
        return BpaUom.values();
    }

    @ModelAttribute("applnStatusList")
    public List<BpaStatus> getApplnStatusList() {
        return bpaStatusService.findAll();
    }

    @ModelAttribute("schemesList")
    public List<BpaScheme> getSchemesList() {
        return bpaSchemeService.findAll();
    }

    public Map<String, String> getApplicationModeMap() {
        final Map<String, String> applicationModeMap = new LinkedHashMap<>(0);
        applicationModeMap.put(ApplicantMode.NEW.toString(), ApplicantMode.NEW.name());
        applicationModeMap.put(ApplicantMode.OTHERS.name(), ApplicantMode.OTHERS.name());
        return applicationModeMap;
    }

    /**
     * @param prepareModel
     * @param model
     * @param container This method we are calling In GET Method..
     */
    @Override
    protected void prepareWorkflow(final Model prepareModel, final StateAware model, final WorkflowContainer container) {
        prepareModel.addAttribute("approverDepartmentList", addAllDepartments());
        prepareModel.addAttribute("validActionList", bpaWorkFlowService.getValidActions(model, container));
        prepareModel.addAttribute("nextAction", bpaWorkFlowService.getNextAction(model, container));

    }

    protected void prepareCommonModelAttribute(final Model model, final BpaApplication bpaApplication) {
        Boolean citizenUser = bpaUtils.logedInuserIsCitizen();
        model.addAttribute("isCitizen", citizenUser);
        List<AppConfigValues> appConfigValueList = appConfigValueService.getConfigValuesByModuleAndKey(
                BpaConstants.APPLICATION_MODULE_TYPE, BpaConstants.BPA_CITIZENACCEPTANCE_CHECK);
        String validateCitizenAcceptance = !appConfigValueList.isEmpty() ? appConfigValueList.get(0).getValue() : "";
        model.addAttribute("validateCitizenAcceptance",
                (validateCitizenAcceptance.equalsIgnoreCase("YES") ? Boolean.TRUE : Boolean.FALSE));
        if (StringUtils.isNotBlank(validateCitizenAcceptance)) {
            model.addAttribute("citizenDisclaimerAccepted", bpaApplication.isCitizenAccepted());
        }
        String enableOrDisablePayOnline = bpaUtils.getAppconfigValueByKeyName(BpaConstants.ENABLEONLINEPAYMENT);
        model.addAttribute("onlinePaymentEnable",
                (enableOrDisablePayOnline.equalsIgnoreCase("YES") ? Boolean.TRUE : Boolean.FALSE));
        model.addAttribute("citizenOrBusinessUser", bpaUtils.logedInuseCitizenOrBusinessUser());
    }

    protected void buildReceiptDetails(final BpaApplication application) {
        for (final EgDemandDetails demandDtl : application.getDemand().getEgDemandDetails())
            for (final EgdmCollectedReceipt collRecpt : demandDtl.getEgdmCollectedReceipts())
                if (!collRecpt.isCancelled()) {
                    Receipt receipt = new Receipt();
                    receipt.setReceiptNumber(collRecpt.getReceiptNumber());
                    receipt.setReceiptDate(collRecpt.getReceiptDate());
                    receipt.setReceiptAmt(collRecpt.getAmount());
                    application.getReceipts().add(receipt);
                }
    }

    protected void buildOwnerDetails(final BpaApplication bpaApplication) {
        List<User> users = userService.getUserByNameAndMobileNumberAndGenderForUserType(bpaApplication.getOwner().getUser().getName(),
                bpaApplication.getOwner().getUser().getMobileNumber(), bpaApplication.getOwner().getUser().getGender(),
                UserType.CITIZEN);
        if (!users.isEmpty()) {
            bpaApplication.getOwner().setUser(users.get(0));
            if (!bpaApplication.getOwner().getUser().isActive())
                bpaApplication.getOwner().getUser().setActive(true);
        } else {
            bpaApplication.getOwner().setUser(applicationBpaService.createApplicantAsUser(bpaApplication));
            bpaApplication.setMailPwdRequired(true);
        }
    }
    
    protected String getDesinationNameByPosition(Position pos) {
        return pos.getDeptDesig() != null && pos.getDeptDesig().getDesignation() != null
                ? pos.getDeptDesig().getDesignation().getName() : "";
    }

}