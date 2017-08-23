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
import static org.egov.bpa.utils.BpaConstants.APPLICATION_STATUS_FIELD_INS;
import static org.egov.bpa.utils.BpaConstants.BUILDINGHEIGHT_GROUND;
import static org.egov.bpa.utils.BpaConstants.EXTENTINSQMTS;
import static org.egov.bpa.utils.BpaConstants.FILESTORE_MODULECODE;
import static org.egov.bpa.utils.BpaConstants.FLOOR_COUNT;
import static org.egov.bpa.utils.BpaConstants.TOTAL_PLINT_AREA;
import static org.egov.bpa.utils.BpaConstants.getStakeholderType1Restrictions;
import static org.egov.bpa.utils.BpaConstants.getStakeholderType2Restrictions;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.egov.bpa.application.autonumber.PlanPermissionNumberGenerator;
import org.egov.bpa.application.entity.ApplicationDocument;
import org.egov.bpa.application.entity.ApplicationFloorDetail;
import org.egov.bpa.application.entity.ApplicationNocDocument;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.BpaFeeDetail;
import org.egov.bpa.application.entity.BpaStatus;
import org.egov.bpa.application.entity.BuildingDetail;
import org.egov.bpa.application.entity.CheckListDetail;
import org.egov.bpa.application.entity.ServiceType;
import org.egov.bpa.application.repository.ApplicationBpaRepository;
import org.egov.bpa.application.service.collection.GenericBillGeneratorService;
import org.egov.bpa.masters.service.BpaSchemeLandUsageService;
import org.egov.bpa.service.ApplicationFeeService;
import org.egov.bpa.service.BpaDemandService;
import org.egov.bpa.service.BpaStatusService;
import org.egov.bpa.service.BpaUtils;
import org.egov.bpa.utils.BpaConstants;
import org.egov.commons.entity.Source;
import org.egov.demand.model.EgDemand;
import org.egov.infra.admin.master.entity.Boundary;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.admin.master.service.RoleService;
import org.egov.infra.admin.master.service.UserService;
import org.egov.infra.config.properties.ApplicationProperties;
import org.egov.infra.exception.ApplicationRuntimeException;
import org.egov.infra.filestore.entity.FileStoreMapper;
import org.egov.infra.filestore.service.FileStoreService;
import org.egov.infra.persistence.entity.enums.UserType;
import org.egov.infra.security.utils.SecurityUtils;
import org.egov.infra.utils.ApplicationNumberGenerator;
import org.egov.infra.utils.autonumber.AutonumberServiceBeanResolver;
import org.egov.infra.workflow.matrix.entity.WorkFlowMatrix;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class ApplicationBpaService extends GenericBillGeneratorService {

    private static final String TOWN_PLANNER_B = "Town Planner - B";

    private static final String FORWARDED_DIGI_SIGN = "Forwarded to Digital Signature";

    private static final String NOC_UPDATION_IN_PROGRESS = "NOC updation in progress";

    @Autowired
    private ApplicationBpaRepository applicationBpaRepository;

    @Autowired
    private BpaStatusService bpaStatusService;

    @Autowired
    private BpaUtils bpaUtils;

    @Autowired
    private ApplicationBpaBillService applicationBpaBillService;

    @Autowired
    private GenericBillGeneratorService genericBillGeneratorService;

    @Autowired
    private ApplicationNumberGenerator applicationNumberGenerator;

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
    @Autowired
    @Qualifier("parentMessageSource")
    private MessageSource messageSource;
    @Autowired
    private ApplicationBpaFeeCalculationService applicationBpaFeeCalculationService;
    @Autowired
    protected ApplicationFeeService applicationFeeService;
    @Autowired
    protected BpaDemandService bpaDemandService;
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostalAddressService postalAddressService;
    @Autowired
    private BpaSchemeLandUsageService bpaSchemeLandUsageService;
    @Autowired
    private BuildingFloorDetailsService buildingFloorDetailsService;

    public Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Transactional
    public BpaApplication createNewApplication(final BpaApplication application, String workFlowAction) {
        final Boundary boundaryObj = bpaUtils.getBoundaryById(application.getWardId() != null ? application.getWardId()
                : getZone(application));
        application.getSiteDetail().get(0).setAdminBoundary(boundaryObj);
        application.getSiteDetail().get(0).setApplication(application);
        application.getBuildingDetail().get(0).setApplication(application);
        buildApplicationFloorDetailsForNew(application);
        application.getSiteDetail().get(0)
                .setPostalAddress(postalAddressService.findById(application.getSiteDetail().get(0).getPostalId()));
        application.setApplicationNumber(applicationNumberGenerator.generate());
        if (application.getSiteDetail().get(0).getLandUsageId() != null)
            application.getSiteDetail().get(0)
                    .setLandUsage((bpaSchemeLandUsageService.findById(application.getSiteDetail().get(0).getLandUsageId())));

        final BpaStatus bpaStatus = getStatusByCodeAndModuleType(BpaConstants.APPLICATION_STATUS_CREATED);
        application.setStatus(bpaStatus);
        if (bpaUtils.logedInuseCitizenOrBusinessUser())
            application.setSource(Source.CITIZENPORTAL);
        else
            application.setSource(Source.SYSTEM);
        Long approvalPosition = null;
        application.setDemand(applicationBpaBillService.createDemand(application));
        if (!bpaUtils.logedInuseCitizenOrBusinessUser()) {
            WorkFlowMatrix wfmatrix = bpaUtils.getWfMatrixByCurrentState(application,
                    BpaConstants.WF_CREATED_STATE);
            String curentState = BpaConstants.WF_CREATED_STATE;
            if (application.getAdmissionfeeAmount() != null
                    && application.getAdmissionfeeAmount().compareTo(BigDecimal.ZERO) == 0) {
                wfmatrix = bpaUtils.getWfMatrixByCurrentState(application,
                        BpaConstants.WF_NEW_STATE);
                curentState = BpaConstants.WF_NEW_STATE;
            }
            if (wfmatrix != null)
                approvalPosition = bpaUtils.getUserPositionIdByZone(wfmatrix.getNextDesignation(),
                        application.getSiteDetail().get(0) != null
                                && application.getSiteDetail().get(0).getElectionBoundary() != null
                                        ? application.getSiteDetail().get(0).getElectionBoundary().getId() : null);
            bpaUtils.redirectToBpaWorkFlow(approvalPosition, application, curentState, null, null,
                    null);
        }
        if (workFlowAction != null && workFlowAction.equals(BpaConstants.WF_SURVEYOR_FORWARD_BUTTON)
                && (bpaUtils.logedInuseCitizenOrBusinessUser())) {
            final WorkFlowMatrix wfmatrix = bpaUtils.getWfMatrixByCurrentState(application, BpaConstants.WF_NEW_STATE);
            if (wfmatrix != null)
                approvalPosition = bpaUtils.getUserPositionIdByZone(wfmatrix.getNextDesignation(),
                        application.getSiteDetail().get(0) != null
                                && application.getSiteDetail().get(0).getElectionBoundary() != null
                                        ? application.getSiteDetail().get(0).getElectionBoundary().getId() : null);
            bpaUtils.redirectToBpaWorkFlow(approvalPosition, application, BpaConstants.WF_NEW_STATE,
                    application.getApprovalComent(), null, null);
        }
        return applicationBpaRepository.save(application);
    }

    private Long getZone(final BpaApplication application) {
        return application.getZoneId() != null ? application.getZoneId() : null;
    }
    
    public void buildApplicationFloorDetailsForNew(final BpaApplication application) {
        buildAndDeleteFloorDetails(application);
        buildNewlyAddedFloorDetails(application);
        if (!application.getBuildingDetail().isEmpty()
                && !application.getBuildingDetail().get(0).getApplicationFloorDetails().isEmpty()) {
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
            application.getBuildingDetail().get(0).setApplicationFloorDetails(floorDetailsList);
        }
    }

    public void buildApplicationFloorDetailsForUpdate(final BpaApplication application) {

        buildAndDeleteFloorDetails(application);
        buildNewlyAddedFloorDetails(application);
        
        if (!application.getBuildingDetail().isEmpty()
                && !application.getBuildingDetail().get(0).getApplicationFloorDetails().isEmpty()) {
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
        
        List<BuildingDetail> newBuildingDetailsList = new ArrayList<>();
        for (BuildingDetail buildingDetail : application.getBuildingDetail()) {
            if (buildingDetail != null && null != buildingDetail.getId() && null != buildingDetail.getApplication()) {
                newBuildingDetailsList.add(buildingDetail);
            }
        }
        application.getBuildingDetail().clear();
        application.setBuildingDetail(newBuildingDetailsList);
    }

    private void buildNewlyAddedFloorDetails(final BpaApplication application) {
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
                for(Long id : application.getBuildingDetail().get(0).getDeletedFloorIds()) {
                    existingFloorDetails.add(buildingFloorDetailsService.findById(id));
                }
            application.getBuildingDetail().get(0).delete(existingFloorDetails);
            buildingFloorDetailsService.delete(existingFloorDetails);
        }
    }

    public void persistBpaNocDocuments(final BpaApplication application) {
        final Map<Long, CheckListDetail> generalDocumentAndId = new HashMap<>();
        checkListDetailService
                .findActiveCheckListByServiceType(application.getServiceType().getId(), BpaConstants.CHECKLIST_TYPE_NOC)
                .forEach(document -> generalDocumentAndId.put(document.getId(), document));
        addDocumentsToFileStore(application, generalDocumentAndId);
    }

    public BpaStatus getStatusByCodeAndModuleType(final String code) {
        return bpaStatusService.findByModuleTypeAndCode(BpaConstants.BPASTATUS_MODULETYPE, code);
    }

    @Transactional
    public void saveAndFlushApplication(final BpaApplication application) {
        persistPostalAddress(application);
        buildSchemeLandUsage(application);
        applicationBpaRepository.saveAndFlush(application);
    }

    private void persistPostalAddress(final BpaApplication application) {
        if (application.getSiteDetail().get(0).getPostalId() != null) {
            application.getSiteDetail().get(0)
                    .setPostalAddress(postalAddressService.findById(application.getSiteDetail().get(0).getPostalId()));
        }
    }

    private void buildSchemeLandUsage(final BpaApplication application) {
        if (application.getSiteDetail() != null && application.getSiteDetail().get(0) != null
                && application.getSiteDetail().get(0).getLandUsageId() != null) {
            application.getSiteDetail().get(0)
                    .setLandUsage(bpaSchemeLandUsageService.findById(application.getSiteDetail().get(0).getLandUsageId()));
        }
    }

    @Transactional
    public String redirectToCollectionOnForward(final BpaApplication application, Model model) {
        persistBpaNocDocuments(application);
        buildApplicationFloorDetailsForUpdate(application);
        return genericBillGeneratorService.generateBillAndRedirectToCollection(application, model);
    }

    @Transactional
    public BpaApplication updateApplication(final BpaApplication application, final Long approvalPosition,
            String workFlowAction, BigDecimal amountRule) {

        application.setSource(Source.SYSTEM);
        persistBpaNocDocuments(application);
        buildApplicationFloorDetailsForUpdate(application);
        persistPostalAddress(application);
        buildSchemeLandUsage(application);
        if (APPLICATION_STATUS_FIELD_INS.equalsIgnoreCase(application.getStatus().getCode())
                && NOC_UPDATION_IN_PROGRESS.equalsIgnoreCase(application.getState().getValue())) {
            bpaDemandService.generateDemandUsingSanctionFeeList(applicationFeeService
                    .saveApplicationFee(applicationBpaFeeCalculationService.calculateBpaSanctionFees(application)));
        }
        if (APPLICATION_STATUS_APPROVED.equalsIgnoreCase(application.getStatus().getCode())
                && FORWARDED_DIGI_SIGN.equalsIgnoreCase(application.getState().getNextAction())) {
            application.setPlanPermissionNumber(generatePlanPermissionNumber(application));
            application.setPlanPermissionDate(new Date());
        }
        final BpaApplication updatedApplication = applicationBpaRepository.save(application);
        if (updatedApplication.getCurrentState() != null
                && !updatedApplication.getCurrentState().getValue().equals(BpaConstants.WF_NEW_STATE)) {
            bpaUtils.redirectToBpaWorkFlow(approvalPosition, application, application.getCurrentState().getValue(),
                    application.getApprovalComent(), workFlowAction, amountRule);
        }
        return updatedApplication;
    }

    public void persistOrUpdateApplicationDocument(final BpaApplication bpaApplication) {
        processAndStoreApplicationDocuments(bpaApplication);
    }

    public BigDecimal setAdmissionFeeAmountForRegistrationWithAmenities(final Long serviceType, List<ServiceType> amenityList) {
        BigDecimal admissionfeeAmount;
        if (serviceType != null)
            admissionfeeAmount = getTotalFeeAmountByPassingServiceTypeandArea(serviceType, amenityList,
                    BpaConstants.BPAFEETYPE);
        else
            admissionfeeAmount = BigDecimal.ZERO;
        return admissionfeeAmount;
    }

    public BigDecimal getTotalFeeAmountByPassingServiceTypeandArea(final Long serviceTypeId, List<ServiceType> amenityList,
            final String feeType) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<Long> serviceTypeList = new ArrayList<>();
        serviceTypeList.add(serviceTypeId);
        for (ServiceType temp : amenityList) {
            serviceTypeList.add(temp.getId());
        }
        if (serviceTypeId != null) {
            final Criteria feeCrit = applicationBpaBillService.getBpaFeeCriteria(serviceTypeList, feeType);
            @SuppressWarnings("unchecked")
            final List<BpaFeeDetail> bpaFeeDetails = feeCrit.list();
            for (final BpaFeeDetail feeDetail : bpaFeeDetails)
                totalAmount = totalAmount.add(BigDecimal.valueOf(feeDetail.getAmount()));
        } else
            throw new ApplicationRuntimeException("Service Type Id is mandatory.");

        return totalAmount;
    }

    public BigDecimal getTotalFeeAmountByPassingServiceTypeAndAmenities(List<Long> serviceTypeIds) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        if (!serviceTypeIds.isEmpty()) {
            final Criteria feeCrit = applicationBpaBillService.getBpaFeeCriteria(serviceTypeIds, BpaConstants.BPAFEETYPE);
            @SuppressWarnings("unchecked")
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
        documents.stream().filter(document -> !document.getFile().isEmpty() && document.getFile().getSize() > 0)
                .map(document -> {
                    for (ApplicationNocDocument applicationNocDocument : bpaApplication.getApplicationNOCDocument()) {
                        if (documentAndId.get(document.getId()).equals(applicationNocDocument.getChecklist())) {
                            applicationNocDocument.setApplication(bpaApplication);
                            applicationNocDocument.setCreatedBy(user);
                            applicationNocDocument.setChecklist(documentAndId.get(document.getId()));
                            applicationNocDocument.setNocFileStore(addToFileStore(document.getFile()));
                            return applicationNocDocument;
                        }
                    }
                    return null;
                }).collect(Collectors.toList()).forEach(doc -> bpaApplication.addApplicationNocDocument(doc));
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
                applicationDocument.setChecklistDetail(
                        checkListDetailService.load(applicationDocument.getChecklistDetail().getId()));
                applicationDocument.setApplication(bpaApplication);
                if (applicationDocument.getFiles() != null) {
                    for (MultipartFile tempFile : applicationDocument.getFiles()) {
                        if (!tempFile.isEmpty()) {
                            applicationDocument.setSupportDocs(addToFileStore(applicationDocument.getFiles()));
                        }
                    }
                }
            }
    }

    protected Set<FileStoreMapper> addToFileStore(final MultipartFile[] files) {
        if (ArrayUtils.isNotEmpty(files))
            return Arrays.asList(files).stream().filter(file -> !file.isEmpty()).map(file -> {
                try {
                    return fileStoreService.store(file.getInputStream(), file.getOriginalFilename(),
                            file.getContentType(), BpaConstants.FILESTORE_MODULECODE);
                } catch (final Exception e) {
                    throw new ApplicationRuntimeException("Error occurred while getting inputstream", e);
                }
            }).collect(Collectors.toSet());
        else
            return Collections.emptySet();
    }

    public String generatePlanPermissionNumber(final BpaApplication application) {
        final PlanPermissionNumberGenerator planPermissionNumber = beanResolver
                .getAutoNumberServiceFor(PlanPermissionNumberGenerator.class);
        return planPermissionNumber.generatePlanPermissionNumber(application.getServiceType());
    }

    public Boolean checkAnyTaxIsPendingToCollect(BpaApplication bpaApplication) {
        return bpaUtils.checkAnyTaxIsPendingToCollect(bpaApplication);
    }

    public Boolean applicationinitiatedByNonEmployee(BpaApplication bpaApplication) {
        return bpaUtils.applicationinitiatedByNonEmployee(bpaApplication);
    }

    public boolean checkStakeholderIsValid(final BpaApplication bpaApplication) {
        return validateStakeholder(bpaApplication.getServiceType().getCode(),
                bpaApplication.getStakeHolder().get(0).getStakeHolder().getStakeHolderType().getStakeHolderTypeVal(),
                bpaApplication.getSiteDetail().get(0).getExtentinsqmts(),
                bpaApplication.getBuildingDetail().get(0).getFloorCount(),
                bpaApplication.getBuildingDetail().get(0).getBuildingheightGround(),
                bpaApplication.getBuildingDetail().get(0).getTotalPlintArea());
    }

    private boolean validateStakeholder(final String serviceType, final String type, final BigDecimal extentInArea,
            final Integer floorCount,
            final BigDecimal buildingHeight, final BigDecimal totalPlinthArea) {
        if (BpaConstants.ST_CODE_08.equalsIgnoreCase(serviceType)
                || BpaConstants.ST_CODE_09.equalsIgnoreCase(serviceType)) {
            // For service type of Amenities and Permission for Temporary hut or
            // shed any registered business user can apply and no validations.
            return true;
        } else if (BpaConstants.getStakeholderType3Restrictions().containsKey(type.toLowerCase())) {
            // For Supervisor - A and Supervisor - B currently doesn't have any
            // role to submit bpa application
            return false;
        } else if ("Town Planner - A".equalsIgnoreCase(type.toLowerCase())) {
            // For Town Planner - A there is no restrictions to submit
            // applications
            return true;
        } else if ((getStakeholderType1Restrictions().containsKey(type.toLowerCase())
                && BpaConstants.getServicesForDevelopPermit().contains(serviceType))) {
            Map<String, BigDecimal> stakeHolderType1Restriction = getStakeholderType1Restrictions()
                    .get(type.toLowerCase());
            BigDecimal extentinsqmtsInput = stakeHolderType1Restriction.get(EXTENTINSQMTS);
            return extentInArea.compareTo(extentinsqmtsInput) <= 0 ? true : false;
        } else if (TOWN_PLANNER_B.equalsIgnoreCase(type.toLowerCase())
                && BpaConstants.getServicesForBuildPermit().contains(serviceType)) {
            return false;
        } else if (getStakeholderType1Restrictions().containsKey(type.toLowerCase())
                && BpaConstants.getServicesForBuildPermit().contains(serviceType)) {
            return true;
        } else if (getStakeholderType2Restrictions().containsKey(type.toLowerCase())
                && BpaConstants.getServicesForDevelopPermit().contains(serviceType)) {
            Map<String, BigDecimal> stakeHolderType2Restriction = getStakeholderType2Restrictions()
                    .get(type.toLowerCase());
            BigDecimal extentinsqmtsInput = stakeHolderType2Restriction.get(EXTENTINSQMTS);
            return extentInArea.compareTo(extentinsqmtsInput) <= 0 ? true : false;
        } else if (getStakeholderType2Restrictions().containsKey(type.toLowerCase())
                && BpaConstants.getServicesForBuildPermit().contains(serviceType)) {
            Map<String, BigDecimal> stakeHolderType2Restriction = getStakeholderType2Restrictions()
                    .get(type.toLowerCase());
            BigDecimal extentinsqmtsInput = stakeHolderType2Restriction.get(EXTENTINSQMTS);
            BigDecimal plinthAreaInput = stakeHolderType2Restriction.get(TOTAL_PLINT_AREA);
            BigDecimal floorCountInput = stakeHolderType2Restriction.get(FLOOR_COUNT);
            BigDecimal buildingHeightInput = stakeHolderType2Restriction.get(BUILDINGHEIGHT_GROUND);
            return (extentInArea.compareTo(extentinsqmtsInput) <= 0 && totalPlinthArea.compareTo(plinthAreaInput) <= 0
                    && buildingHeight.compareTo(buildingHeightInput) <= 0
                    && BigDecimal.valueOf(floorCount).compareTo(floorCountInput) <= 0) ? true : false;
        }
        return true;
    }

    public String getValidationMessageForBusinessResgistration(final BpaApplication bpaApplication) {
        String stakeHolderType = bpaApplication.getStakeHolder().get(0).getStakeHolder().getStakeHolderType()
                .getStakeHolderTypeVal();
        String serviceType = bpaApplication.getServiceType().getCode();
        BigDecimal extentinsqmtsInput = BigDecimal.ZERO;
        BigDecimal plinthAreaInput = BigDecimal.ZERO;
        BigDecimal floorCountInput = BigDecimal.ZERO;
        BigDecimal buildingHeightInput = BigDecimal.ZERO;
        String message;

        if ((getStakeholderType1Restrictions().containsKey(stakeHolderType.toLowerCase())
                && BpaConstants.getServicesForDevelopPermit().contains(serviceType))
                || (TOWN_PLANNER_B.equalsIgnoreCase(stakeHolderType.toLowerCase())
                        && BpaConstants.getServicesForDevelopPermit().contains(serviceType))) {
            Map<String, BigDecimal> stakeHolderType1Restriction = getStakeholderType1Restrictions()
                    .get(stakeHolderType.toLowerCase());
            extentinsqmtsInput = stakeHolderType1Restriction.get(EXTENTINSQMTS);
        } else if (getStakeholderType2Restrictions().containsKey(stakeHolderType.toLowerCase())
                && BpaConstants.getServicesForDevelopPermit().contains(serviceType)) {
            Map<String, BigDecimal> stakeHolderType2Restriction = getStakeholderType2Restrictions()
                    .get(stakeHolderType.toLowerCase());
            extentinsqmtsInput = stakeHolderType2Restriction.get(EXTENTINSQMTS);
        } else if (getStakeholderType2Restrictions().containsKey(stakeHolderType.toLowerCase())
                && BpaConstants.getServicesForBuildPermit().contains(serviceType)) {
            Map<String, BigDecimal> stakeHolderType2Restriction = getStakeholderType2Restrictions()
                    .get(stakeHolderType.toLowerCase());
            extentinsqmtsInput = stakeHolderType2Restriction.get(EXTENTINSQMTS);
            plinthAreaInput = stakeHolderType2Restriction.get(TOTAL_PLINT_AREA);
            floorCountInput = stakeHolderType2Restriction.get(FLOOR_COUNT);
            buildingHeightInput = stakeHolderType2Restriction.get(BUILDINGHEIGHT_GROUND);
        }
        if (TOWN_PLANNER_B.equalsIgnoreCase(stakeHolderType.toLowerCase())
                && BpaConstants.getServicesForBuildPermit().contains(serviceType)) {
            message = messageSource.getMessage("msg.invalid.stakeholder4", new String[] { stakeHolderType },
                    LocaleContextHolder.getLocale());
        } else if (BpaConstants.getStakeholderType3Restrictions().containsKey(stakeHolderType.toLowerCase())) {
            message = messageSource.getMessage("msg.invalid.stakeholder3", new String[] { stakeHolderType },
                    LocaleContextHolder.getLocale());
        } else if (!extentinsqmtsInput.equals(BigDecimal.ZERO) && plinthAreaInput.equals(BigDecimal.ZERO)) {
            message = messageSource.getMessage("msg.invalid.stakeholder2",
                    new String[] { stakeHolderType, extentinsqmtsInput.toString(),
                            bpaApplication.getServiceType().getDescription() },
                    LocaleContextHolder.getLocale());
        } else {
            message = messageSource.getMessage("msg.invalid.stakeholder1",
                    new String[] { stakeHolderType, extentinsqmtsInput.toString(), plinthAreaInput.toString(),
                            floorCountInput.toString(), buildingHeightInput.toString(),
                            bpaApplication.getServiceType().getDescription() },
                    LocaleContextHolder.getLocale());
        }
        return message;
    }

    /**
     * @param bpaApplication
     * @return
     */
    public User createApplicantAsUser(BpaApplication bpaApplication) {
        User applicantUser = new User();
        applicantUser.setName(bpaApplication.getOwner().getUser().getName());
        applicantUser.setMobileNumber(bpaApplication.getOwner().getUser().getMobileNumber());
        applicantUser.setEmailId(bpaApplication.getOwner().getUser().getEmailId());
        applicantUser.setGender(bpaApplication.getOwner().getUser().getGender());
        applicantUser.setUsername(bpaUtils.generateUserName(bpaApplication.getOwner().getUser().getName()));
        applicantUser.updateNextPwdExpiryDate(applicationProperties.userPasswordExpiryInDays());
        applicantUser.setPassword(passwordEncoder.encode(bpaApplication.getOwner().getUser().getMobileNumber()));
        applicantUser.setType(UserType.CITIZEN);
        applicantUser.setActive(true);
        applicantUser.addRole(roleService.getRoleByName(BpaConstants.ROLE_CITIZEN));
        applicantUser.addAddress(bpaApplication.getOwner().getPermanentAddress());
        return userService.createUser(applicantUser);
    }
}
