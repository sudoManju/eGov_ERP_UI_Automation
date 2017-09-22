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
package org.egov.bpa.transaction.service;

import static org.egov.bpa.utils.BpaConstants.INDUSTRIAL;
import static org.egov.bpa.utils.BpaConstants.MERCANTILE_COMMERCIAL;
import static org.egov.bpa.utils.BpaConstants.MIXED_OCCUPANCY;
import static org.egov.bpa.utils.BpaConstants.RESIDENTIAL;
import static org.egov.bpa.utils.BpaConstants.THATCHED_TILED_HOUSE;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.egov.bpa.master.entity.BpaFee;
import org.egov.bpa.master.entity.BpaFeeDetail;
import org.egov.bpa.master.entity.Occupancy;
import org.egov.bpa.master.entity.ServiceType;
import org.egov.bpa.master.service.BpaFeeService;
import org.egov.bpa.transaction.entity.ApplicationFee;
import org.egov.bpa.transaction.entity.ApplicationFeeDetail;
import org.egov.bpa.transaction.entity.ApplicationFloorDetail;
import org.egov.bpa.transaction.entity.BpaApplication;
import org.egov.bpa.transaction.entity.BuildingDetail;
import org.egov.bpa.utils.BpaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author vinoth
 *
 */
@Service
@Transactional(readOnly = true)
public class ApplicationBpaFeeCalculationService {
    private static final String OTHERS = "Others";
    @Autowired
    private BpaFeeService bpaFeeService;
    @Autowired
    private ApplicationFeeService applicationFeeService;

    private ApplicationFee getbpaFee(final BpaApplication application) {
        ApplicationFee applicationFee = null;
        if (application != null) {
            List<ApplicationFee> applicationFeeList = applicationFeeService
                    .getApplicationFeeListByApplicationId(application.getId());
            if (applicationFeeList.isEmpty()) {
                applicationFee = new ApplicationFee();
                applicationFee.setApplication(application);
                return applicationFee;
            } else {
                return applicationFeeList.get(0);
            }
        }
        return applicationFee;
    }

    public ApplicationFee calculateBpaSanctionFees(final BpaApplication application) {
        List<Long> serviceTypeList = new ArrayList<>();
        // getting all service type and amenities to retrieve fee details
        serviceTypeList.add(application.getServiceType().getId());
        if (!application.getApplicationAmenity().isEmpty()) {
            for (ServiceType serviceType : application.getApplicationAmenity()) {
                serviceTypeList.add(serviceType.getId());
            }
        }
        ApplicationFee applicationFee = getbpaFee(application);
        // If record rejected and recalculation required again, then this logic has to be change.
        if (applicationFee.getApplicationFeeDetail().isEmpty()) {
            calculateFeeByServiceType(application, serviceTypeList, applicationFee);
        }

        return applicationFee;
    }

    /**
     * 
     * @param application
     * @param serviceTypeList
     * @param applicationFee
     */

    public void calculateFeeByServiceType(final BpaApplication application, final List<Long> serviceTypeList,
            final ApplicationFee applicationFee) {
        if (application != null) {
            for (Long serviceTypdId : serviceTypeList) {

                BigDecimal beyondPermissibleArea = BigDecimal.ZERO;

                // RESTRICT TO FEW SERVICES
                if (!application.getBuildingDetail().isEmpty() && application.getOccupancy() != null
                        && !application.getSiteDetail().isEmpty()
                        && application.getSiteDetail().get(0).getExtentinsqmts() != null) {
                    beyondPermissibleArea = calculateAreaForAdditionalFeeCalculation(application).setScale(2,
                            BigDecimal.ROUND_HALF_UP);
                }
                for (BpaFee bpaFee : bpaFeeService.getActiveSanctionFeeForListOfServices(serviceTypdId)) {
                    if (bpaFee != null) {
                        BigDecimal amount = BigDecimal.ZERO;
                        if (!application.getIsEconomicallyWeakerSection()) {// In case of economically weaker section, amount will
                                                                            // be zero.
                            String occupancy = null;
                            BigDecimal inputArea = getInputUnitForEachServiceType(application,
                                    bpaFee.getServiceType().getCode());
                                if (BpaConstants.getBpaFeeCateory2().contains(bpaFee.getServiceType().getCode())
                                        && (RESIDENTIAL.equalsIgnoreCase(application.getOccupancy().getDescription())
                                                || INDUSTRIAL.equalsIgnoreCase(application.getOccupancy().getDescription())
                                                || MERCANTILE_COMMERCIAL
                                                        .equalsIgnoreCase(application.getOccupancy().getDescription()))) {
                                    occupancy = application.getOccupancy().getDescription();
                                } else if (RESIDENTIAL.equalsIgnoreCase(application.getOccupancy().getDescription())
                                        || THATCHED_TILED_HOUSE
                                                .equalsIgnoreCase(application.getOccupancy().getDescription())) {
                                    occupancy = application.getOccupancy().getDescription();
                                } else {
                                    occupancy = OTHERS;
                                }
                                BigDecimal feeAmount = getBpaFeeObjByOccupancyType(bpaFee.getCode(), occupancy, bpaFee);

                            if (("101").equals(bpaFee.getCode()) || ("301").equals(bpaFee.getCode())
                                    || ("401").equals(bpaFee.getCode()) || ("601").equals(bpaFee.getCode())
                                    || ("701").equals(bpaFee.getCode())) {

                                if (MIXED_OCCUPANCY.equalsIgnoreCase(application.getOccupancy().getDescription())) {
                                    for (Entry<Occupancy, BigDecimal> occupancyWiseArea : getOccupancyWiseSumOfFloorArea(
                                            application.getBuildingDetail().get(0)).entrySet()) {
                                        inputArea = inputArea.add(occupancyWiseArea.getValue());
                                        occupancy = getOccupancyToGetFeeAmt(occupancyWiseArea);
                                        // set occupancy type and get fee and calculate amount.
                                        feeAmount = getBpaFeeObjByOccupancyType(bpaFee.getCode(), occupancy, bpaFee);
                                        amount = amount.add(calculatePermitFee(occupancyWiseArea.getValue(), feeAmount));
                                    }

                                } else {
                                    amount = calculatePermitFee(inputArea, feeAmount);
                                }
                                // CHECK WHETHER THIS APPLICABLE TO ONLY 701 OCCUPANCY TYPE.. ALSO HERE WORK
                                // STARTED,INPROGRSS,COMPLETED TO BE CONSIDER.
                                if (checkIsWorkAlreadyStarted(application)
                                        && BpaConstants.getServicesForValidation().contains(bpaFee.getServiceType().getCode())) {
                                    amount = amount.multiply(BigDecimal.valueOf(3));
                                } else if (checkIsEligibleForDiscountOnPermitFee(inputArea, bpaFee.getServiceType().getCode())) {
                                    amount = calculateAndGetDiscountedPermitFee(inputArea.multiply(feeAmount)); // 50% off if area
                                                                                                                // less than 150
                                                                                                                // mts
                                }

                            } else if (("102").equals(bpaFee.getCode()) || ("302").equals(bpaFee.getCode())
                                    || ("402").equals(bpaFee.getCode()) || ("602").equals(bpaFee.getCode())
                                    || ("702").equals(bpaFee.getCode())) {
                                feeAmount = getBpaFeeObjByOccupancyType(bpaFee.getCode(), OTHERS, bpaFee);
                                // calculate beyondpermissblearea tax for other
                                if (beyondPermissibleArea.compareTo(BigDecimal.ZERO) > 0) {
                                    amount = calculateAdditionalFee(beyondPermissibleArea, feeAmount);
                                }

                            } else if (("201").equals(bpaFee.getCode()))
                                amount = calculateDemolitionFee(inputArea, feeAmount);
                            else if (("501").equals(bpaFee.getCode()))
                                amount = calculateLandDevelopmentCharges(inputArea, feeAmount);
                            else if (("901").equals(bpaFee.getCode())) {
                                amount = calculatePermitFeeForHut(inputArea, feeAmount);
                            } else if (("1001").equals(bpaFee.getCode()))
                                amount = calculateChargesForWell(inputArea, feeAmount);
                            else if (("1002").equals(bpaFee.getCode()))
                                amount = calculateChargesForCompuntWall(inputArea, feeAmount);
                            else if (("1003").equals(bpaFee.getCode()))
                                amount = calculateShutterDoorConversionCharges(inputArea, feeAmount);
                            else if (("1004").equals(bpaFee.getCode()))
                                amount = calculateRoofConversionCharges(inputArea, feeAmount);
                            else if (("1005").equals(bpaFee.getCode()))
                                amount = calculateTowerConstuctionCharges(inputArea, feeAmount);
                            else if (("1006").equals(bpaFee.getCode()))
                                amount = calculatePoleStructureConstuctionCharges(inputArea, feeAmount);
                        }
                        applicationFee
                                .addApplicationFeeDetail(buildApplicationFeeDetail(bpaFee, applicationFee, amount));
                    }
                }
            }
        }
    }

    private String getOccupancyToGetFeeAmt(Entry<Occupancy, BigDecimal> occupancyWiseArea) {
        String occupancy;
        if (RESIDENTIAL.equalsIgnoreCase(occupancyWiseArea.getKey().getDescription())
                || THATCHED_TILED_HOUSE
                        .equalsIgnoreCase(occupancyWiseArea.getKey().getDescription())) {
            occupancy = occupancyWiseArea.getKey().getDescription();
        } else {
            occupancy = OTHERS;
        }
        return occupancy;
    }

    private ApplicationFeeDetail buildApplicationFeeDetail(final BpaFee bpaFee, final ApplicationFee applicationFee,
            BigDecimal amount) {
        ApplicationFeeDetail feeDetail = new ApplicationFeeDetail();
        feeDetail.setAmount(amount.setScale(0, BigDecimal.ROUND_HALF_UP));
        feeDetail.setBpaFee(bpaFee);
        feeDetail.setApplicationFee(applicationFee);
        return feeDetail;
    }

    private BigDecimal calculatePermitFeeForHut(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);
    }

    private BigDecimal calculatePoleStructureConstuctionCharges(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);
    }

    private BigDecimal calculateTowerConstuctionCharges(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);
    }

    private BigDecimal calculateRoofConversionCharges(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);
    }

    private BigDecimal calculateShutterDoorConversionCharges(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);
    }

    private BigDecimal calculateChargesForCompuntWall(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);
    }

    private BigDecimal calculateChargesForWell(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);
    }

    private BigDecimal calculateLandDevelopmentCharges(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);
    }

    private BigDecimal calculateDemolitionFee(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);
    }

    private BigDecimal calculateAdditionalFee(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);
    }

    private BigDecimal calculatePermitFee(BigDecimal inputArea, BigDecimal feeAmount) {
        return inputArea.multiply(feeAmount);

    }

    /**
     * @param application
     * @return is work already started or not ?
     */
    private Boolean checkIsWorkAlreadyStarted(final BpaApplication application) {
        return application.getSiteDetail().get(0).getIsappForRegularization();
    }

    /**
     * @param inputUnit
     * @param serviceTypeCode
     * @return is eligible for permit fee 50% waive off ?
     */
    private Boolean checkIsEligibleForDiscountOnPermitFee(final BigDecimal inputUnit, final String serviceTypeCode) {
        return BpaConstants.getServicesForValidation().contains(serviceTypeCode)
                && inputUnit.compareTo(BigDecimal.valueOf(150)) <= 0 ? true : false;
    }

    /**
     * @param occupancyType
     * @param occupancyType
     * @param bpaFee
     * @return master rate value for each service type based on occupancy type
     */
    private BigDecimal getBpaFeeObjByOccupancyType(final String feeCode, String occupancyType, final BpaFee bpaFee) {
        BigDecimal rate = BigDecimal.ZERO;
        for (BpaFeeDetail feeDetail : bpaFee.getFeeDetail()) {
            if (feeCode != null && feeCode.equalsIgnoreCase(bpaFee.getCode())) {
                if (feeDetail.getAdditionalType() != null
                        && occupancyType.equalsIgnoreCase(feeDetail.getAdditionalType())) {
                    rate = BigDecimal.valueOf(feeDetail.getAmount());
                    break;
                } else {
                    rate = BigDecimal.valueOf(feeDetail.getAmount());
                }
            }
        }
        return rate;
    }

    /**
     * @param amount
     * @return after discounted amount (eligible if constructing only up to 150 sqmtrs)
     */
    private BigDecimal calculateAndGetDiscountedPermitFee(final BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(50)).divide(BigDecimal.valueOf(100));
    }

    /**
     * @param application
     * @param serviceTypeCode
     * @return input unit value for fee calculation
     */
    private BigDecimal getInputUnitForEachServiceType(final BpaApplication application, final String serviceTypeCode) {
        BigDecimal inputUnit = BigDecimal.ZERO;

        if (BpaConstants.getBpaFeeCateory1().contains(serviceTypeCode)) {
            inputUnit = getTotalFloorArea(application);
        } else if (BpaConstants.getBpaFeeCateory2().contains(serviceTypeCode)) { // Sub-Division of plot/Land Development
            inputUnit = application.getSiteDetail().get(0).getExtentinsqmts();
        } else if ("10".equals(serviceTypeCode)) { // well
            inputUnit = application.getSiteDetail().get(0).getDwellingunitnt();
        } else if ("11".equals(serviceTypeCode)) { // Compound Wall
            inputUnit = application.getSiteDetail().get(0).getLengthOfCompoundWall();
        } else if ("14".equals(serviceTypeCode)) { // Tower Construction
            inputUnit = application.getSiteDetail().get(0).getErectionoftower();
        } else if ("12".equals(serviceTypeCode)) { // Shutter or Door Conversion/Erection
            inputUnit = application.getSiteDetail().get(0).getShutter();
        } else if ("13".equals(serviceTypeCode)) { // Roof Conversion
            inputUnit = application.getSiteDetail().get(0).getRoofConversion();
        } else if ("15".equals(serviceTypeCode)) { // Pole Structures
            inputUnit = application.getSiteDetail().get(0).getNoOfPoles();
        } else if ("09".equals(serviceTypeCode)) { // hut or shed
            inputUnit = application.getSiteDetail().get(0).getNoOfHutOrSheds();
        }
        return inputUnit;
    }

    /***
     * Calculate Area for additional fee calculation.
     * @param application
     * @return
     */

    public BigDecimal calculateAreaForAdditionalFeeCalculation(final BpaApplication application) {
        BigDecimal extentOfLand = application.getSiteDetail().get(0).getExtentinsqmts();
        BigDecimal buildingFloorArea = BigDecimal.ZERO;
        BigDecimal minimumFARwithoutAdditionalFee;
        BigDecimal minimumFARwithAdditionalFee;
        BigDecimal weightageavgFAR;
        BigDecimal maximumPermittedFloorAreaWithAddnFee = BigDecimal.ZERO;
        BigDecimal maximumPermittedFARWithAdditionalFee;
        BigDecimal maximumPermittedFloorArea = BigDecimal.ZERO;
        BigDecimal maximumPermittedFAR;
        BigDecimal additionalFeeCalculationArea = BigDecimal.ZERO;

        Map<Occupancy, BigDecimal> occupancywiseFloorArea = getOccupancyWiseSumOfFloorArea(
                application.getBuildingDetail().get(0));
        for (Entry<Occupancy, BigDecimal> occupancyWiseArea : occupancywiseFloorArea.entrySet()) {
            buildingFloorArea = buildingFloorArea.add(occupancyWiseArea.getValue());
        }

        if (buildingFloorArea.compareTo(BigDecimal.ZERO) > 0) {
            if (extentOfLand.compareTo(new BigDecimal(5000)) <= 0) {

                minimumFARwithoutAdditionalFee = minimumFARWithoutAdditionalFee(application);
                minimumFARwithAdditionalFee = minimumFARWithAdditionalFee(application);
                maximumPermittedFAR = minimumFARwithoutAdditionalFee.multiply(extentOfLand);

                // Mean additional fee has to collect BUT CITIZEN NOT READY TO PAY ADDITIONAL TAX
                if (buildingFloorArea.compareTo(maximumPermittedFAR) > 0
                        && application.getBuildingDetail().get(0).getAdditionalFeePaymentAccepted()) {
                    maximumPermittedFARWithAdditionalFee = minimumFARwithAdditionalFee.multiply(extentOfLand);
                    // Calclulate additional Fee.
                    if (buildingFloorArea.compareTo(maximumPermittedFARWithAdditionalFee) <= 0) {
                        additionalFeeCalculationArea = buildingFloorArea.subtract(maximumPermittedFAR);
                    }
                }
            } else // above area greater than 5000sq.mt.
            {
                weightageavgFAR = weightageAverageFarWithoutAdditionalFee(occupancywiseFloorArea);
                if (weightageavgFAR != null)
                    maximumPermittedFloorArea = weightageavgFAR.multiply(extentOfLand);
                // Mean Aggregate violation of area
                if (buildingFloorArea.compareTo(maximumPermittedFloorArea) > 0
                        && application.getBuildingDetail().get(0).getAdditionalFeePaymentAccepted()) {
                    weightageavgFAR = weightageAverageFarWithAdditionalFee(occupancywiseFloorArea);
                    if (weightageavgFAR != null)
                        maximumPermittedFloorAreaWithAddnFee = weightageavgFAR.multiply(extentOfLand);
                    // Mean Aggregate violation of area
                    if (buildingFloorArea.compareTo(maximumPermittedFloorAreaWithAddnFee) <= 0) {
                        // Calclulate additional Fee.
                        additionalFeeCalculationArea = buildingFloorArea.subtract(maximumPermittedFloorArea);
                    }
                }
            }
        }
        return additionalFeeCalculationArea.setScale(2, RoundingMode.HALF_UP);
    }

    /***
     * Group occupancy wise floor area
     * @param buildingDetail
     * @return
     */
    // Floor Area considered here.
    public Map<Occupancy, BigDecimal> getOccupancyWiseSumOfFloorArea(BuildingDetail buildingDetail) {
        return buildingDetail.getApplicationFloorDetails().stream().collect(
                Collectors.groupingBy(ApplicationFloorDetail::getOccupancy, Collectors
                        .mapping(ApplicationFloorDetail::getFloorArea, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
    }

    public BigDecimal getTotalFloorArea(final BpaApplication application) {
        BigDecimal totalFloorArea = BigDecimal.ZERO;
        for (ApplicationFloorDetail floorDetails : application.getBuildingDetail().get(0).getApplicationFloorDetails()) {
            totalFloorArea = totalFloorArea.add(floorDetails.getFloorArea());
        }
        return totalFloorArea;
    }

    /***
     * Minimum FAR Without Additional Fee
     * @param application
     * @return
     */
    public BigDecimal minimumFARWithoutAdditionalFee(final BpaApplication application) {
        List<Double> minimumFARs = new ArrayList<>();
        for (ApplicationFloorDetail floorDetails : application.getBuildingDetail().get(0).getApplicationFloorDetails()) {
            minimumFARs.add(floorDetails.getOccupancy().getNumOfTimesAreaPermissible());
        }
        return BigDecimal.valueOf(Collections.min(minimumFARs));
    }

    /***
     * Minimum FAR with Additional Fee
     * @param application
     * @return
     */
    public BigDecimal minimumFARWithAdditionalFee(final BpaApplication application) {
        List<Double> maximumFARs = new ArrayList<>();
        for (ApplicationFloorDetail floorDetails : application.getBuildingDetail().get(0).getApplicationFloorDetails()) {
            maximumFARs.add(floorDetails.getOccupancy().getNumOfTimesAreaPermWitAddnlFee());
        }
        return BigDecimal.valueOf(Collections.min(maximumFARs));
    }

    /***
     * Weightage Average FAR Without Additional Fee
     * @param occupancywiseFloorArea
     * @return
     */
    public BigDecimal weightageAverageFarWithoutAdditionalFee(Map<Occupancy, BigDecimal> occupancywiseFloorArea) {
        BigDecimal maxPermitedFloorArea = BigDecimal.ZERO;
        BigDecimal sumOfFloorArea = BigDecimal.ZERO;
        for (Entry<Occupancy, BigDecimal> setOfOccupancy : occupancywiseFloorArea.entrySet()) {
            maxPermitedFloorArea = maxPermitedFloorArea.add(
                    new BigDecimal(setOfOccupancy.getKey().getNumOfTimesAreaPermissible()).multiply(setOfOccupancy.getValue()));
            sumOfFloorArea = sumOfFloorArea.add(setOfOccupancy.getValue());
        }
        if (sumOfFloorArea.compareTo(BigDecimal.ZERO) > 0)
            return maxPermitedFloorArea.divide(sumOfFloorArea, 6, RoundingMode.HALF_UP).setScale(6, RoundingMode.HALF_UP);

        return null;
    }

    /***
     * Weightage Average FAR With Additional Fee
     * @param occupancywiseFloorArea
     * @return
     */
    public BigDecimal weightageAverageFarWithAdditionalFee(Map<Occupancy, BigDecimal> occupancywiseFloorArea) {

        BigDecimal maxPermitedFloorArea = BigDecimal.ZERO;
        BigDecimal sumOfFloorArea = BigDecimal.ZERO;
        for (Entry<Occupancy, BigDecimal> setOfOccupancy : occupancywiseFloorArea.entrySet()) {
            maxPermitedFloorArea = maxPermitedFloorArea
                    .add(new BigDecimal(setOfOccupancy.getKey().getNumOfTimesAreaPermWitAddnlFee())
                            .multiply(setOfOccupancy.getValue()));
            sumOfFloorArea = sumOfFloorArea.add(setOfOccupancy.getValue());
        }
        if (sumOfFloorArea.compareTo(BigDecimal.ZERO) > 0)
            return maxPermitedFloorArea.divide(sumOfFloorArea, 6, RoundingMode.HALF_UP).setScale(6, RoundingMode.HALF_UP);
        return null;
    }

}