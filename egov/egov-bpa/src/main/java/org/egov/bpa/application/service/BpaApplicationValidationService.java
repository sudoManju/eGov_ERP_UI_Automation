/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2017>  eGovernments Foundation
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
package org.egov.bpa.application.service;

import static org.egov.bpa.utils.BpaConstants.EGMODULE_NAME;
import static org.egov.bpa.utils.BpaConstants.getServicesForValidation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.egov.bpa.application.entity.ApplicationFloorDetail;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.Occupancy;
import org.egov.infra.admin.master.entity.AppConfigValues;
import org.egov.infra.admin.master.service.AppConfigValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 * @author vinoth
 *
 */

@Service
@Transactional(readOnly = true)
public class BpaApplicationValidationService {

    private static final String BUILDINGDETAILSVALIDATIONREQUIRED = "BUILDINGDETAILSVALIDATIONREQUIRED";
    private static final String MSG_VIOLATION_WO_ADDNL_FEE = "msg.violation.wo.addnl.fee";
    private static final String MSG_VIOLATION_WITH_ADDNL_FEE = "msg.violation.with.addnl.fee";
    private static final String MIXED = "Mixed";
    private static final String VIOLATION_MESSAGE = "violationMessage";
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String VIOLATION_MESSAGE_FOR_COVERAGE = "violationMessageForCoverage";
    private static final String MAXIMUM_ALLOWED_AREA_WITH_ADDNL_FEE = "maximumAllowedAreaWithAddnlFee";
    private static final String MAXIMUM_ALLOWED_AREA_WO_ADDNL_FEE = "maximumAllowedAreaWOAddnlFee";
    private static final String IS_VIOLATING = "isViolating";
    @Autowired
    @Qualifier("parentMessageSource")
    private MessageSource bpaMessageSource;
    @Autowired
    private ApplicationBpaFeeCalculationService applicationBpaFeeCalculationService;
    @Autowired
    private AppConfigValueService appConfigValuesService;

    /**
     * checking each floor wise coverage area is violating for all occupancy where ever building details capturing to those
     * services this validation is applicable
     * @param application
     * @return
     */
    public Map<String, String> checkIsViolatingCoverageArea(final BpaApplication application) {
        Map<String, BigDecimal[]> floorMap = new HashMap<>();
        for (ApplicationFloorDetail floorDetail : application.getBuildingDetail().get(0).getApplicationFloorDetails()) {
            String floorDesc = floorDetail.getFloorDescription().concat("-").concat(floorDetail.getFloorNumber().toString());
            if (floorMap
                    .containsKey(floorDesc)) {
                BigDecimal permissableFloorCoveredArea = floorMap.get(floorDesc)[0]
                        .add(new BigDecimal(floorDetail.getOccupancy().getPermissibleAreaInPercentage())
                                .multiply(floorDetail.getPlinthArea()));
                BigDecimal floorWiseArea = floorMap.get(floorDesc)[1].add(floorDetail.getPlinthArea());
                floorMap.put(floorDesc, new BigDecimal[] { permissableFloorCoveredArea, floorWiseArea });
            } else {
                BigDecimal permissableFloorCoveredArea = new BigDecimal(
                        floorDetail.getOccupancy().getPermissibleAreaInPercentage()).multiply(floorDetail.getPlinthArea());
                floorMap.put(floorDesc, new BigDecimal[] { permissableFloorCoveredArea, floorDetail.getPlinthArea() });
            }
        }
        Map<String, String> violationCoverage = new HashMap<>();
        StringBuilder floorDescBuilder = new StringBuilder();
        StringBuilder floorAreaBuilder = new StringBuilder();
        for (Entry<String, BigDecimal[]> floorDescSet : floorMap.entrySet()) {
            String floorDesc = floorDescSet.getKey();
            BigDecimal permissableCoveredArea = floorDescSet.getValue()[0];
            BigDecimal floorArea = floorDescSet.getValue()[1];
            BigDecimal weightedCoverage = permissableCoveredArea.divide(floorArea, 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal coverageProvided = (floorArea.divide(application.getSiteDetail().get(0).getExtentinsqmts(), 2,
                    BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal permitedFloorArea = (weightedCoverage.multiply(application.getSiteDetail().get(0).getExtentinsqmts())
                    .divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP);

            if (coverageProvided.compareTo(weightedCoverage) > 0) {
                floorDescBuilder.append(floorDesc).append(",");
                floorAreaBuilder.append(permitedFloorArea).append(",");
            }
        }
        if (floorDescBuilder.length() > 0) {
            String message = bpaMessageSource.getMessage("msg.coverage.violate",
                    new String[] { floorDescBuilder.substring(0, floorDescBuilder.length() - 1),
                            floorAreaBuilder.substring(0, floorAreaBuilder.length() - 1) },
                    null);
            violationCoverage.put(VIOLATION_MESSAGE_FOR_COVERAGE, message);
            violationCoverage.put(IS_VIOLATING, TRUE);
        } else {
            violationCoverage.put(IS_VIOLATING, FALSE);
        }
        return violationCoverage;
    }

    /**
     * 
     * @param application
     * @param model
     * @return
     */
    public Boolean validateBuildingDetails(final BpaApplication application, final Model model) {
        if (isBuildingFloorDetailsValidationRequired()
                && getServicesForValidation().contains(application.getServiceType().getCode())) {
            List<ApplicationFloorDetail> existingFloorDetails = new ArrayList<>();
            for (ApplicationFloorDetail applicationFloorDetails : application.getBuildingDetail().get(0)
                    .getApplicationFloorDetails()) {
                if (Arrays.asList(application.getBuildingDetail().get(0).getDeletedFloorIds())
                        .contains(applicationFloorDetails.getId())) {
                    existingFloorDetails.add(applicationFloorDetails);
                }
            }
            application.getBuildingDetail().get(0).delete(existingFloorDetails);
            Map<String, String> violationCoverage = checkIsViolatingCoverageArea(application);
            if (TRUE.equalsIgnoreCase(violationCoverage.get(IS_VIOLATING))) {
                model.addAttribute(VIOLATION_MESSAGE, violationCoverage.get(VIOLATION_MESSAGE_FOR_COVERAGE));
                return true;
            }

            Map<String, String> violationWOAddnlFee = checkIsViolatingMaximumPermissableWOAddnlFee(application);
            Map<String, String> violationWithAddnlFee = checkIsViolatingMaximumPermissableWithAddnlFee(application);
            if (TRUE.equalsIgnoreCase(violationWOAddnlFee.get(IS_VIOLATING))
                    && !application.getBuildingDetail().get(0).getAdditionalFeePaymentAccepted()) {
                String message = bpaMessageSource.getMessage(MSG_VIOLATION_WO_ADDNL_FEE, new String[] {
                        application.getOccupancy().getDescription(), violationWOAddnlFee.get(MAXIMUM_ALLOWED_AREA_WO_ADDNL_FEE) },
                        null);
                model.addAttribute(VIOLATION_MESSAGE, message);
                return true;
            }

            if (TRUE.equalsIgnoreCase(violationWithAddnlFee.get(IS_VIOLATING))
                    && application.getBuildingDetail().get(0).getAdditionalFeePaymentAccepted()) {
                String message = bpaMessageSource.getMessage(MSG_VIOLATION_WITH_ADDNL_FEE, new String[] {
                        application.getOccupancy().getDescription(),
                        violationWithAddnlFee.get(MAXIMUM_ALLOWED_AREA_WITH_ADDNL_FEE) },
                        null);
                model.addAttribute(VIOLATION_MESSAGE, message);
                return true;
            }
        }
        return false;
    }

    /**
     * checking total floor area is violating against maximum permissable area with out additional fee and where ever building
     * details capturing to those services this validation is applicable
     * @param application
     * @return isViolated and maximum allowed area with out additional fee
     */
    public Map<String, String> checkIsViolatingMaximumPermissableWOAddnlFee(final BpaApplication application) {
        Map<String, String> violation = new HashMap<>();
        BigDecimal sumOfFloorArea = applicationBpaFeeCalculationService
                .getOccupancyWiseSumOfFloorArea(application.getBuildingDetail().get(0)).entrySet().stream()
                .map(Entry<Occupancy, BigDecimal>::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal maximumAllowedAreaWOAddnlFee = BigDecimal.ZERO;
        if (MIXED.equalsIgnoreCase(application.getOccupancy().getDescription())) {
            if (application.getSiteDetail().get(0).getExtentinsqmts().compareTo(new BigDecimal(5000)) <= 0) {
                maximumAllowedAreaWOAddnlFee = application.getSiteDetail().get(0).getExtentinsqmts()
                        .multiply(applicationBpaFeeCalculationService.minimumFARWithoutAdditionalFee(application));
            } else if (application.getSiteDetail().get(0).getExtentinsqmts().compareTo(new BigDecimal(5000)) > 0) {
                maximumAllowedAreaWOAddnlFee = application.getSiteDetail().get(0).getExtentinsqmts()
                        .multiply(applicationBpaFeeCalculationService.weightageAverageFarWithoutAdditionalFee(
                                applicationBpaFeeCalculationService
                                        .getOccupancyWiseSumOfFloorArea(application.getBuildingDetail().get(0))));
            }
        } else {
            maximumAllowedAreaWOAddnlFee = application.getSiteDetail().get(0).getExtentinsqmts()
                    .multiply(new BigDecimal(application.getOccupancy().getNumOfTimesAreaPermissible()));
        }
        violation.put(IS_VIOLATING, sumOfFloorArea.compareTo(maximumAllowedAreaWOAddnlFee) > 0 ? TRUE : FALSE);
        violation.put(MAXIMUM_ALLOWED_AREA_WO_ADDNL_FEE, maximumAllowedAreaWOAddnlFee.toString());
        return violation;
    }

    /**
     * checking total floor area is violating against maximum permissable area with additional fee and is additional fee
     * applicable or not.
     * @param application
     * @return isViolated and maximum allowed area with additional fee
     */
    public Map<String, String> checkIsViolatingMaximumPermissableWithAddnlFee(final BpaApplication application) {
        Map<String, String> violation = new HashMap<>();
        BigDecimal sumOfFloorArea = applicationBpaFeeCalculationService
                .getOccupancyWiseSumOfFloorArea(application.getBuildingDetail().get(0)).entrySet().stream()
                .map(Entry<Occupancy, BigDecimal>::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal maximumAllowedAreaWithAddnlFee = BigDecimal.ZERO;
        if (MIXED.equalsIgnoreCase(application.getOccupancy().getDescription())) {
            if (application.getSiteDetail().get(0).getExtentinsqmts().compareTo(new BigDecimal(5000)) <= 0) {
                maximumAllowedAreaWithAddnlFee = application.getSiteDetail().get(0).getExtentinsqmts()
                        .multiply(applicationBpaFeeCalculationService.minimumFARWithAdditionalFee(application));
            } else if (application.getSiteDetail().get(0).getExtentinsqmts().compareTo(new BigDecimal(5000)) > 0) {
                maximumAllowedAreaWithAddnlFee = application.getSiteDetail().get(0).getExtentinsqmts()
                        .multiply(applicationBpaFeeCalculationService.weightageAverageFarWithAdditionalFee(
                                applicationBpaFeeCalculationService
                                        .getOccupancyWiseSumOfFloorArea(application.getBuildingDetail().get(0))));
            }
        } else {
            maximumAllowedAreaWithAddnlFee = application.getSiteDetail().get(0).getExtentinsqmts()
                    .multiply(new BigDecimal(application.getOccupancy().getNumOfTimesAreaPermWitAddnlFee()));
        }

        violation.put(IS_VIOLATING, sumOfFloorArea.compareTo(maximumAllowedAreaWithAddnlFee) > 0 ? TRUE : FALSE);
        violation.put(MAXIMUM_ALLOWED_AREA_WITH_ADDNL_FEE, maximumAllowedAreaWithAddnlFee.toString());
        return violation;
    }

    public Boolean isBuildingFloorDetailsValidationRequired() {
        return getAppConfigValueByPassingModuleAndType(EGMODULE_NAME, BUILDINGDETAILSVALIDATIONREQUIRED);
    }

    public Boolean getAppConfigValueByPassingModuleAndType(String moduleName, String sendsmsoremail) {
        final List<AppConfigValues> appConfigValue = appConfigValuesService.getConfigValuesByModuleAndKey(moduleName,
                sendsmsoremail);
        return "YES".equalsIgnoreCase(
                appConfigValue != null && !appConfigValue.isEmpty() ? appConfigValue.get(0).getValue() : "NO");
    }
}
