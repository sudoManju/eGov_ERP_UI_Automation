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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.egov.bpa.transaction.entity.ApplicationFloorDetail;
import org.egov.bpa.transaction.entity.BpaApplication;
import org.egov.bpa.transaction.entity.BuildingDetail;
import org.egov.bpa.transaction.repository.BuildingFloorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BuildingFloorDetailsService {
    @Autowired
    private BuildingFloorDetailsRepository buildingFloorDetailsRepository;

    @Transactional
    public void delete(final List<ApplicationFloorDetail> applicationFloorDetails) {
        buildingFloorDetailsRepository.deleteInBatch(applicationFloorDetails);
    }

    @Transactional
    public ApplicationFloorDetail findById(final Long id) {
        return buildingFloorDetailsRepository.findOne(id);
    }

    public void buildProposedBuildingFloorDetails(final BpaApplication application) {

        if (!application.getBuildingDetail().isEmpty()
                && null != application.getBuildingDetail().get(0).getTotalPlintArea()
                && !application.getBuildingDetail().get(0).getApplicationFloorDetails().isEmpty()) {
            buildAndDeleteFloorDetails(application);
            List<ApplicationFloorDetail> floorDetailsList = new ArrayList<>();
            application.getBuildingDetail().get(0).setApplication(application);
            for (ApplicationFloorDetail applicationFloorDetails : application.getBuildingDetail().get(0)
                    .getApplicationFloorDetails()) {
                if (null != applicationFloorDetails && null == applicationFloorDetails.getId()
                        && applicationFloorDetails.getFloorDescription() != null) {
                    ApplicationFloorDetail floorDetails = new ApplicationFloorDetail();
                    floorDetails.setBuildingDetail(application.getBuildingDetail().get(0));
                    floorDetails.setOccupancy(applicationFloorDetails.getOccupancy());
                    floorDetails.setOrderOfFloor(applicationFloorDetails.getOrderOfFloor());
                    floorDetails.setFloorNumber(applicationFloorDetails.getFloorNumber());
                    floorDetails.setFloorDescription(applicationFloorDetails.getFloorDescription());
                    floorDetails.setPlinthArea(applicationFloorDetails.getPlinthArea());
                    floorDetails.setCarpetArea(applicationFloorDetails.getCarpetArea());
                    floorDetails.setFloorArea(applicationFloorDetails.getFloorArea());
                    floorDetailsList.add(floorDetails);
                } else if (null != applicationFloorDetails && null != applicationFloorDetails.getId()
                        && applicationFloorDetails.getFloorDescription() != null) {
                    floorDetailsList.add(applicationFloorDetails);
                }
            }
            application.getBuildingDetail().get(0).getApplicationFloorDetails().clear();
            application.getBuildingDetail().get(0).setApplicationFloorDetails(floorDetailsList);
        }

        validateAndBuildBuildingDetails(application);
    }

    private void validateAndBuildBuildingDetails(final BpaApplication application) {
        List<BuildingDetail> newBuildingDetailsList = new ArrayList<>();
        for (BuildingDetail buildingDetail : application.getBuildingDetail()) {
            if (buildingDetail != null && null != buildingDetail.getApplication() && null != buildingDetail.getTotalPlintArea()) {
                newBuildingDetailsList.add(buildingDetail);
            }
        }
        application.getBuildingDetail().clear();
        if (!newBuildingDetailsList.isEmpty())
            application.setBuildingDetail(newBuildingDetailsList);
    }

    public void buildNewlyAddedFloorDetails(final BpaApplication application) {
        if (!application.getBuildingDetail().get(0).getApplicationFloorDetailsForUpdate().isEmpty()) {
            List<ApplicationFloorDetail> newFloorDetails = new ArrayList<>();
            for (ApplicationFloorDetail applicationFloorDetail : application.getBuildingDetail().get(0)
                    .getApplicationFloorDetailsForUpdate()) {
                if (applicationFloorDetail != null && StringUtils.isNotBlank(applicationFloorDetail.getFloorDescription()))
                    newFloorDetails.add(applicationFloorDetail);
            }
            application.getBuildingDetail().get(0).getApplicationFloorDetails().addAll(newFloorDetails);
        }
    }

    private void buildAndDeleteFloorDetails(final BpaApplication application) {
        List<ApplicationFloorDetail> existingFloorDetails = new ArrayList<>();
        if (application.getBuildingDetail().get(0).getDeletedFloorIds() != null
                && application.getBuildingDetail().get(0).getDeletedFloorIds().length > 0) {
            for (Long id : application.getBuildingDetail().get(0).getDeletedFloorIds()) {
                existingFloorDetails.add(findById(id));
            }
            application.getBuildingDetail().get(0).delete(existingFloorDetails);
            delete(existingFloorDetails);
        }
    }

    public void removeDuplicateProposedBuildFloorDetails(final BpaApplication application) {
        if (!application.getBuildingDetail().isEmpty() && application.getBuildingDetail().get(0) != null
                && !application.getBuildingDetail().get(0).getApplicationFloorDetails().isEmpty()
                && application.getBuildingDetail().get(0).getTotalPlintArea() != null) {
            application.getBuildingDetail().get(0).setApplicationFloorDetails(
                    new ArrayList<>(new HashSet<>(application.getBuildingDetail().get(0).getApplicationFloorDetails())));
        }
    }
}
