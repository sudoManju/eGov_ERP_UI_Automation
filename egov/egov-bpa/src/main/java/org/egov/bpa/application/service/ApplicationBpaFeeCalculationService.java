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
		ApplicationFee applicationFee = new ApplicationFee();
		if (application != null) {
			List<ApplicationFee> applicationFeeList = applicationFeeService
					.getApplicationFeeListByApplicationId(application.getId());
			if (applicationFeeList.isEmpty()) {
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
		// TODO : if extending beyond permissible area, need to calculate fee for additional area
		if (applicationFee.getApplicationFeeDetail().isEmpty()) {
			for (BpaFee bpaFee : bpaFeeService.getActiveSanctionFeeForListOfServices(serviceTypeList)) {
				if (!"Additional Fees".equalsIgnoreCase(bpaFee.getDescription()))
					applicationFee
							.addApplicationFeeDetail(calculateFeeByServiceType(application, bpaFee, applicationFee));
			}
		}

		return applicationFee;
	}
	
	/**
	 * @param application
	 * @param bpaFee
	 * @param applicationFee
	 * @return feeDetail
	 */
	private ApplicationFeeDetail calculateFeeByServiceType(final BpaApplication application, final BpaFee bpaFee,
			final ApplicationFee applicationFee) {
		ApplicationFeeDetail feeDetail = new ApplicationFeeDetail();
		BigDecimal inputUnit = getInputUnitForEachServiceType(application, bpaFee.getServiceType().getCode());
		BigDecimal rateMstr = getBpaFeeObjByOccupancyType(application.getOccupancy().getDescription(), bpaFee);
		BigDecimal amount = calculateFeeByRateAndInputUnit(inputUnit, rateMstr);
		if (checkIsEligibleForDiscountOnPermitFee(inputUnit, bpaFee.getServiceType().getCode())) {
			amount = calculateAndGetDiscountedPermitFee(amount);
		}
		if (checkIsWorkAlreadyStarted(application)
				&& BpaConstants.getServicesForValidation().contains(bpaFee.getServiceType().getCode())) {
			amount = amount.multiply(BigDecimal.valueOf(3));
		}
		feeDetail.setAmount(amount.setScale(2, BigDecimal.ROUND_HALF_UP));
		feeDetail.setBpaFee(bpaFee);
		feeDetail.setApplicationFee(applicationFee);
		return feeDetail;
	}
	
	/**
	 * @param application
	 * @return is work already started or not ?
	 */
	private Boolean checkIsWorkAlreadyStarted(final BpaApplication application) {
		if (application.getSiteDetail().get(0).getConstStages() != null
				&& "Started".equals(application.getSiteDetail().get(0).getConstStages().getDescription())) {
			return true;
		}
		return false;
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
	 * @param bpaFee
	 * @return master rate value for each service type based on occupancy type
	 */
	private BigDecimal getBpaFeeObjByOccupancyType(final String occupancyType, final BpaFee bpaFee) {
		BigDecimal rate = BigDecimal.ZERO;
		for (BpaFeeDetail feeDetail : bpaFee.getFeeDetail()) {
			if (feeDetail.getAdditionalType() != null
					&& occupancyType.equalsIgnoreCase(feeDetail.getAdditionalType())) {
				rate = BigDecimal.valueOf(feeDetail.getAmount());
				break;
			} else {
				rate = BigDecimal.valueOf(feeDetail.getAmount());
			}
		}
		return rate;
	}
	/**
	 * @param inputUnit
	 * @param rate
	 * @return total amount 
	 */
	private BigDecimal calculateFeeByRateAndInputUnit(final BigDecimal inputUnit, final BigDecimal rate) {
		return inputUnit.multiply(rate);
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
