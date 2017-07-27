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
import static org.egov.bpa.utils.BpaConstants.INDUSTRIAL;
import static org.egov.bpa.utils.BpaConstants.MERCANTILE_COMMERCIAL;
import static org.egov.bpa.utils.BpaConstants.RESIDENTIAL;
import static org.egov.bpa.utils.BpaConstants.THATCHED_TILED_HOUSE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.egov.bpa.application.entity.ApplicationFee;
import org.egov.bpa.application.entity.ApplicationFeeDetail;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.BpaFee;
import org.egov.bpa.application.entity.BpaFeeDetail;
import org.egov.bpa.application.entity.ServiceType;
import org.egov.bpa.masters.service.BpaFeeService;
import org.egov.bpa.service.ApplicationFeeService;
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

    private void calculateFeeByServiceType(final BpaApplication application, final List<Long> serviceTypeList,
            final ApplicationFee applicationFee) {
        if (application != null) {
            for (Long serviceTypdId : serviceTypeList) {

                BigDecimal maximumAlloWedArea = BigDecimal.ZERO;
                BigDecimal beyondPermissibleArea = BigDecimal.ZERO;
                //  RESTRICT TO FEW SERVICES
                if (application.getOccupancy() != null && application.getSiteDetail() != null &&
                        !application.getSiteDetail().isEmpty() && application.getSiteDetail().get(0).getExtentinsqmts() != null) {
                    if (application.getOccupancy().getNumOfTimesAreaPermissible() != null)
                        maximumAlloWedArea = application.getSiteDetail().get(0).getExtentinsqmts()
                                .multiply(BigDecimal.valueOf(application.getOccupancy().getNumOfTimesAreaPermissible()))
                                .setScale(0, BigDecimal.ROUND_HALF_UP);
                    if (application.getOccupancy().getNumOfTimesAreaPermWitAddnlFee() != null)
                        beyondPermissibleArea = application.getSiteDetail().get(0).getExtentinsqmts()
                                .multiply(BigDecimal.valueOf(application.getOccupancy().getNumOfTimesAreaPermWitAddnlFee()))
                                .setScale(0, BigDecimal.ROUND_HALF_UP);
                }
                for (BpaFee bpaFee : bpaFeeService.getActiveSanctionFeeForListOfServices(serviceTypdId)) {
                    if (bpaFee != null) {
                        BigDecimal amount = BigDecimal.ZERO;
                     if(!application.getIsEconomicallyWeakerSection()){//In case of economically weaker section, amount will be zero.
                        String occupancy;
						BigDecimal inputArea = getInputUnitForEachServiceType(application,
								bpaFee.getServiceType().getCode());
						if (BpaConstants.getBpaFeeCateory2().contains(bpaFee.getServiceType().getCode())
								&& RESIDENTIAL.equalsIgnoreCase(application.getOccupancy().getDescription())
								|| INDUSTRIAL.equalsIgnoreCase(application.getOccupancy().getDescription())
								|| MERCANTILE_COMMERCIAL
										.equalsIgnoreCase(application.getOccupancy().getDescription())) {
							occupancy = application.getOccupancy().getDescription();
						} else if (RESIDENTIAL.equalsIgnoreCase(application.getOccupancy().getDescription())
								|| THATCHED_TILED_HOUSE
										.equalsIgnoreCase(application.getOccupancy().getDescription())) {
							occupancy = application.getOccupancy().getDescription();
						} else {
							occupancy = "Others";
						}
                        BigDecimal feeAmount = getBpaFeeObjByOccupancyType(bpaFee.getCode(),occupancy, bpaFee);

                        if (("101").equals(bpaFee.getCode()) || ("301").equals(bpaFee.getCode())
                                || ("401").equals(bpaFee.getCode()) || ("601").equals(bpaFee.getCode())
                                || ("701").equals(bpaFee.getCode())) {

                          
                            //  CHECK WHETHER THIS APPLICABLE TO ONLY 701 OCCUPANCY TYPE.. ALSO HERE WORK
                            // STARTED,INPROGRSS,COMPLETED TO BE CONSIDER.
                            if (checkIsWorkAlreadyStarted(application)
                                    && BpaConstants.getServicesForValidation().contains(bpaFee.getServiceType().getCode())) {
                                amount = inputArea.multiply(feeAmount).multiply(BigDecimal.valueOf(3));
                            }else if (checkIsEligibleForDiscountOnPermitFee(inputArea, bpaFee.getServiceType().getCode())) {
                                    amount = calculateAndGetDiscountedPermitFee(inputArea.multiply(feeAmount)); // 50% off if area less than 150 mts
                                }
                             // If input area greater than maximum allowed area, then calculate penalty rate for remaining area
                            else if (inputArea.compareTo(BigDecimal.ZERO) > 0 && maximumAlloWedArea.compareTo(BigDecimal.ZERO) > 0
                                    && inputArea.compareTo(maximumAlloWedArea) > 0) {
                                // calculate permit fee for maximumAlloWedArea
                                amount = calculatePermitFee(maximumAlloWedArea, feeAmount);

                            } else {
                                amount = calculatePermitFee(inputArea, feeAmount);
                            }

                        } else if (("102").equals(bpaFee.getCode()) || ("302").equals(bpaFee.getCode())
                                || ("402").equals(bpaFee.getCode()) || ("602").equals(bpaFee.getCode())
                                || ("702").equals(bpaFee.getCode())) {
                             
                            // calculate beyondpermissblearea tax for other
                            // If input area greater than maximum allowed area, then calculate penalty rate for remaining area
                            if (inputArea.compareTo(BigDecimal.ZERO) > 0 && beyondPermissibleArea.compareTo(BigDecimal.ZERO) > 0
                                    && maximumAlloWedArea.compareTo(BigDecimal.ZERO) > 0
                                    && inputArea.compareTo(maximumAlloWedArea) > 0
                                   /* && beyondPermissibleArea.subtract(maximumAlloWedArea)
                                            .compareTo(inputArea.subtract(maximumAlloWedArea)) >= 0*/) {
                                // calculate permit fee for maximumAlloWedArea
                                amount = calculateAdditionalFee(inputArea.subtract(maximumAlloWedArea), feeAmount);

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
        if (BpaConstants.getServicesForValidation().contains(serviceTypeCode)
                && inputUnit.compareTo(BigDecimal.valueOf(150)) <= 0) {
            return true;
        }
        return false;
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
            if(feeCode!=null && feeCode.equalsIgnoreCase(bpaFee.getCode())) {
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
            inputUnit = application.getBuildingDetail().get(0).getTotalPlintArea();
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
}