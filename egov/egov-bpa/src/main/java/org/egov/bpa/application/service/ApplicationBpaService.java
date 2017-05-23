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
package org.egov.bpa.application.service;

import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_APPROVED;
import static org.egov.bpa.utils.BpaConstants.FILESTORE_MODULECODE;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.ArrayUtils;
import org.egov.bpa.application.autonumber.PlanPermissionNumberGenerator;
import org.egov.bpa.application.entity.ApplicationDocument;
import org.egov.bpa.application.entity.ApplicationFloorDetail;
import org.egov.bpa.application.entity.ApplicationNocDocument;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.BpaFeeDetail;
import org.egov.bpa.application.entity.BpaStatus;
import org.egov.bpa.application.entity.CheckListDetail;
import org.egov.bpa.application.entity.ServiceType;
import org.egov.bpa.application.entity.enums.BpaUom;
import org.egov.bpa.application.repository.ApplicationBpaRepository;
import org.egov.bpa.application.service.collection.GenericBillGeneratorService;
import org.egov.bpa.service.BpaStatusService;
import org.egov.bpa.service.BpaUtils;
import org.egov.bpa.utils.BpaConstants;
import org.egov.commons.entity.Source;
import org.egov.demand.model.EgDemand;
import org.egov.infra.admin.master.entity.Boundary;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.exception.ApplicationRuntimeException;
import org.egov.infra.filestore.entity.FileStoreMapper;
import org.egov.infra.filestore.service.FileStoreService;
import org.egov.infra.security.utils.SecurityUtils;
import org.egov.infra.utils.autonumber.AutonumberServiceBeanResolver;
import org.egov.infra.workflow.matrix.entity.WorkFlowMatrix;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class ApplicationBpaService extends GenericBillGeneratorService {

    @Autowired
    private ApplicationBpaRepository applicationBpaRepository;

    @Autowired
    private BpaStatusService bpaStatusService;
    
    @Autowired
    private BpaUtils bpaUtils;

    @Autowired
    private ApplicationBpaBillService applicationBpaBillService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FileStoreService fileStoreService;
    @Autowired
    private CheckListDetailService checkListDetailService;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
	private AutonumberServiceBeanResolver beanResolver;

    public Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Transactional
    public BpaApplication createNewApplication(final BpaApplication application,String workFlowAction) {
        final Boundary boundaryObj = bpaUtils.getBoundaryById(application.getWardId() != null ? application.getWardId()
                : application.getZoneId() != null ? application.getZoneId() : null);
        application.getSiteDetail().get(0).setAdminBoundary(boundaryObj);
        application.getSiteDetail().get(0).setApplication(application);
        application.getBuildingDetail().get(0).setApplication(application);
        application.getBuildingDetail().get(0).setApplicationFloorDetails(buildApplicationFloorDetails(application));
        application.setApplicationNumber(applicationBpaBillService.generateApplicationnumber(application));
        final BpaStatus bpaStatus = getStatusByCodeAndModuleType(BpaConstants.APPLICATION_STATUS_REGISTERED);
        application.setStatus(bpaStatus);
        if( bpaUtils.logedInuseCitizenOrBusinessUser())
        application.setSource(Source.CITIZENPORTAL);
        else
        	 application.setSource(Source.SYSTEM);
        application.getSiteDetail().get(0).setExtentinsqmts(convertExtendOfLandToSqmts(application.getSiteDetail().get(0).getExtentOfLand(), application.getSiteDetail().get(0).getUnitOfMeasurement()));
        Long approvalPosition=null;
        application.setDemand(applicationBpaBillService.createDemand(application));
        if(workFlowAction!=null && workFlowAction.equals(BpaConstants.WF_SURVEYOR_FORWARD_BUTTON) && (bpaUtils.logedInuseCitizenOrBusinessUser()))
    	{
    	 final WorkFlowMatrix wfmatrix = bpaUtils.getWfMatrixByCurrentState(application, BpaConstants.WF_NEW_STATE);
         if (wfmatrix != null)
        	 approvalPosition = bpaUtils.getUserPositionByZone(wfmatrix.getNextDesignation(), application.getSiteDetail().get(0) != null &&
        			application.getSiteDetail().get(0).getElectionBoundary()!=null
                     ?  application.getSiteDetail().get(0).getElectionBoundary().getId() : null);
    	bpaUtils.redirectToBpaWorkFlow(approvalPosition,application, BpaConstants.WF_NEW_STATE, null,null,null);
    	}
        return applicationBpaRepository.save(application);
    }

    
	private List<ApplicationFloorDetail> buildApplicationFloorDetails(final BpaApplication application) {
		List<ApplicationFloorDetail> floorDetailsList = new ArrayList<>();
		if (!application.getBuildingDetail().isEmpty()) {
			application.getBuildingDetail().get(0).setApplication(application);
			for (ApplicationFloorDetail applicationFloorDetails : application.getBuildingDetail().get(0)
					.getApplicationFloorDetails()) {
				if (null == applicationFloorDetails.getId() && applicationFloorDetails.getFloorDescription() != null) {
					ApplicationFloorDetail floorDetails = new ApplicationFloorDetail();
					floorDetails.setBuildingDetail(application.getBuildingDetail().get(0));
					floorDetails.setFloorDescription(applicationFloorDetails.getFloorDescription());
					floorDetails.setPlinthArea(applicationFloorDetails.getPlinthArea());
					floorDetails.setCarpetArea(applicationFloorDetails.getCarpetArea());
					floorDetailsList.add(floorDetails);
				} else if (null != applicationFloorDetails.getId()
						&& applicationFloorDetails.getFloorDescription() != null) {
					floorDetailsList.add(applicationFloorDetails);
				}
			}
		}
		return floorDetailsList;
	}

    public void persistBpaNocDocuments(final BpaApplication application) {
        final Map<Long, CheckListDetail> generalDocumentAndId = new HashMap<>();
        checkListDetailService
                .findActiveCheckListByServiceType(application.getServiceType().getId(), BpaConstants.CHECKLIST_TYPE_NOC)
                .forEach(document -> generalDocumentAndId.put(document.getId(), document));
        addDocumentsToFileStore(application, generalDocumentAndId);
    }

    public BpaStatus getStatusByCodeAndModuleType(final String code) {
        return bpaStatusService
                .findByModuleTypeAndCode(BpaConstants.BPASTATUS_MODULETYPE, code);
    }

    @Transactional
    public void saveAndFlushApplication(final BpaApplication application) {
        applicationBpaRepository.saveAndFlush(application);
    }

    @Transactional
    public BpaApplication updateApplication(final BpaApplication application, final Long approvalPosition,String workFlowAction,BigDecimal amountRule) {

        application.setSource(Source.SYSTEM);
        persistBpaNocDocuments(application);
        application.getBuildingDetail().get(0).setApplicationFloorDetails(buildApplicationFloorDetails(application));
        application.getSiteDetail().get(0).setExtentinsqmts(convertExtendOfLandToSqmts(application.getSiteDetail().get(0).getExtentOfLand(), application.getSiteDetail().get(0).getUnitOfMeasurement()));
        if(APPLICATION_STATUS_APPROVED.equalsIgnoreCase(application.getStatus().getCode())) {
        	application.setPlanPermissionNumber(generatePlanPermissionNumber(application));
        }
        final BpaApplication updatedApplication = applicationBpaRepository
                .save(application);

        bpaUtils.redirectToBpaWorkFlow(approvalPosition, application, application.getCurrentState().getValue(), null,workFlowAction,amountRule);

        return updatedApplication;
    }
    public void persistOrUpdateApplicationDocument(final BpaApplication bpaApplication, final BindingResult resultBinder) {
		final List<ApplicationDocument> applicationDocs = new ArrayList<>(0);
        int i = 0;
        if (!bpaApplication.getApplicationDocument().isEmpty())
            for (final ApplicationDocument applicationDocument : bpaApplication.getApplicationDocument()) {
                validateDocuments(applicationDocs, applicationDocument, i, resultBinder);
                i++;
            }
        bpaApplication.setApplicationDocument(applicationDocs);
        processAndStoreApplicationDocuments(bpaApplication);
	}
    private void validateDocuments(final List<ApplicationDocument> applicationDocs,
            final ApplicationDocument applicationDocument, final int i, final BindingResult resultBinder) {
        Iterator<MultipartFile> stream = null;
        if (ArrayUtils.isNotEmpty(applicationDocument.getFiles()))
            stream = Arrays.asList(applicationDocument.getFiles()).stream().filter(file -> !file.isEmpty())
                    .iterator();
        if (stream == null) {
            final String fieldError = "applicationDocument[" + i + "].files";
            resultBinder.rejectValue(fieldError, "files.required");
        } else
            applicationDocs.add(applicationDocument);
    }
    public BigDecimal setAdmissionFeeAmountForRegistration(final String serviceType) {
        BigDecimal admissionfeeAmount;
        if (serviceType != null)
            admissionfeeAmount = getTotalFeeAmountByPassingServiceTypeandArea(
                    Long.valueOf(serviceType),new ArrayList<>(), BpaConstants.BPAFEETYPE);
        else
            admissionfeeAmount = BigDecimal.ZERO;
        return admissionfeeAmount;
    }
    
    public BigDecimal setAdmissionFeeAmountForRegistrationWithAmenities(final String serviceType,List<ServiceType> amenityList) {
        BigDecimal admissionfeeAmount;
        if (serviceType != null)
            admissionfeeAmount = getTotalFeeAmountByPassingServiceTypeandArea(
                    Long.valueOf(serviceType),amenityList, BpaConstants.BPAFEETYPE);
        else
            admissionfeeAmount = BigDecimal.ZERO;
        return admissionfeeAmount;
    }

    public BigDecimal getTotalFeeAmountByPassingServiceTypeandArea(final Long serviceTypeId, List<ServiceType> amenityList,final String feeType) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<Long>serviceTypeList=new ArrayList<>();
        serviceTypeList.add(serviceTypeId);
		for(ServiceType temp:amenityList)
		{
			serviceTypeList.add(temp.getId());
		}
        if (serviceTypeId != null) {
            final Criteria feeCrit = applicationBpaBillService.getBpaFeeCriteria(serviceTypeList, feeType);
            final List<BpaFeeDetail> bpaFeeDetails = feeCrit.list();
            for (final BpaFeeDetail feeDetail : bpaFeeDetails)
                totalAmount = totalAmount.add(BigDecimal.valueOf(feeDetail.getAmount()));
        } else
            throw new ApplicationRuntimeException("Service Type Id is mandatory.");

        return totalAmount;
    }

    public BpaApplication getApplicationByDemand(final EgDemand demand) {
        return applicationBpaRepository.findByDemand(demand);
    }

    public BpaApplication findByApplicationNumber(final String applicationNumber) {
        return applicationBpaRepository.findByApplicationNumber(applicationNumber);
    }

    private void addDocumentsToFileStore(final BpaApplication bpaApplication,
            final Map<Long, CheckListDetail> documentAndId) {
        final User user = securityUtils.getCurrentUser();
        final List<CheckListDetail> documents = bpaApplication.getCheckListDocumentsForNOC();
        documents.stream()
                .filter(document -> !document.getFile().isEmpty() && document.getFile().getSize() > 0)
                .map(document -> {
                    for(ApplicationNocDocument applicationNocDocument : bpaApplication.getApplicationNOCDocument()){
                        if(documentAndId.get(document.getId()).equals(applicationNocDocument.getChecklist())){
                        applicationNocDocument.setApplication(bpaApplication);
                        applicationNocDocument.setCreatedBy(user);
                        applicationNocDocument.setChecklist(documentAndId.get(document.getId()));
                        applicationNocDocument.setNocFileStore(addToFileStore(document.getFile()));
                        return applicationNocDocument;
                        }
                    }
                    return null;
                }).collect(Collectors.toList())
                .forEach(doc -> bpaApplication.addApplicationNocDocument(doc));
    }

    private FileStoreMapper addToFileStore(final MultipartFile file) {
        FileStoreMapper fileStoreMapper = null;
        try {
            fileStoreMapper = fileStoreService.store(file.getInputStream(), file.getOriginalFilename(),
                    file.getContentType(), FILESTORE_MODULECODE);
        } catch (final IOException e) {
            throw new ApplicationRuntimeException("Error occurred while getting inputstream", e);
        }
        return fileStoreMapper;
    }

    
    protected void processAndStoreApplicationDocuments(final BpaApplication bpaApplication) {
        if (!bpaApplication.getApplicationDocument().isEmpty())
            for (final ApplicationDocument applicationDocument : bpaApplication.getApplicationDocument()) {
                applicationDocument.setChecklistDetail(checkListDetailService.load(applicationDocument.getChecklistDetail()
                        .getId()));
                applicationDocument.setApplication(bpaApplication);
                applicationDocument.setSupportDocs(addToFileStore(applicationDocument.getFiles()));
            }
    }
    
    protected Set<FileStoreMapper> addToFileStore(final MultipartFile[] files) {
        if (ArrayUtils.isNotEmpty(files))
            return Arrays
                    .asList(files)
                    .stream()
                    .filter(file -> !file.isEmpty())
                    .map(file -> {
                        try {
                            return fileStoreService.store(file.getInputStream(), file.getOriginalFilename(),
                                    file.getContentType(), BpaConstants.FILESTORE_MODULECODE);
                        } catch (final Exception e) {
                            throw new ApplicationRuntimeException("Error occurred while getting inputstream", e);
                        }
                    }).collect(Collectors.toSet());
        else
            return null;
    }
    
	public String generatePlanPermissionNumber(final BpaApplication application) {
		final PlanPermissionNumberGenerator planPermissionNumber = beanResolver
				.getAutoNumberServiceFor(PlanPermissionNumberGenerator.class);
		return planPermissionNumber.generatePlanPermissionNumber(application.getServiceType());
	}
	
	public BigDecimal convertExtendOfLandToSqmts(BigDecimal extentLand, BpaUom uom){
    	if(uom.equals(BpaUom.ARE)){
    		return convertAreToSqmtrs(extentLand);
    	} else if(uom.equals(BpaUom.HECTARE)){
    		return convertHectareToSqmtrs(extentLand);
    	} else {
    		return extentLand;
    	}
    }
	
	public BigDecimal convertAreToSqmtrs(BigDecimal extentLand){
    	return extentLand.multiply(BigDecimal.valueOf(100));
    }
    
    public BigDecimal convertHectareToSqmtrs(BigDecimal extentLand){
    	return extentLand.multiply(BigDecimal.valueOf(10000));
    }
    
    public Boolean checkAnyTaxIsPendingToCollect(BpaApplication bpaApplication)
    {
    	return bpaUtils.checkAnyTaxIsPendingToCollect(bpaApplication);
    }
    public Boolean workFlowinitiatedByNonEmployee(BpaApplication bpaApplication)
    {
    	return bpaUtils.workFlowinitiatedByNonEmployee(bpaApplication);
    }
}
