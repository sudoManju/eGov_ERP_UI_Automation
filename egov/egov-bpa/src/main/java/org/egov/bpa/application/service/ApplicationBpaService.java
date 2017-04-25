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

import static org.egov.bpa.utils.BpaConstants.FILESTORE_MODULECODE;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.egov.bpa.application.entity.ApplicationNocDocument;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.BpaFeeDetail;
import org.egov.bpa.application.entity.BpaStatus;
import org.egov.bpa.application.entity.CheckListDetail;
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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Transactional
    public BpaApplication createNewApplication(final BpaApplication application) {
        final Boundary boundaryObj = bpaUtils.getBoundaryById(application.getWardId() != null ? application.getWardId()
                : application.getZoneId() != null ? application.getZoneId() : null);
        application.getSiteDetail().get(0).setAdminBoundary(boundaryObj);
        application.getSiteDetail().get(0).setApplication(application);
        application.getBuildingDetail().get(0).setApplication(application);
        if (!application.getBuildingDetail().isEmpty())
            application.getBuildingDetail().get(0).setApplication(application);
        application.setApplicationNumber(applicationBpaBillService.generateApplicationnumber(application));
        final BpaStatus bpaStatus = getStatusByCodeAndModuleType("Registered");
        application.setStatus(bpaStatus);
        application.setSource(Source.SYSTEM);
        /*
         * final BpaApplicationWorkflowCustomDefaultImpl applicationWorkflowCustomDefaultImpl = bpaUtils
         * .getInitialisedWorkFlowBean(); applicationWorkflowCustomDefaultImpl.createCommonWorkflowTransition(application,
         * null,BpaConstants.WF_NEW_STATE, BpaConstants.CREATE_ADDITIONAL_RULE_CREATE, null);
         */
        application.setDemand(applicationBpaBillService.createDemand(application));
        return applicationBpaRepository.save(application);
    }

    private void persistBpaNocDocuments(final BpaApplication application) {
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
    public BpaApplication updateApplication(final BpaApplication application, final Long approvalPosition) {

        application.setSource(Source.SYSTEM);
        persistBpaNocDocuments(application);
        final BpaApplication updatedApplication = applicationBpaRepository
                .saveAndFlush(application);

        bpaUtils.redirectToBpaWorkFlow(approvalPosition, application, application.getCurrentState().getValue(), null);

        return updatedApplication;
    }

    public BigDecimal setAdmissionFeeAmountForRegistration(final String serviceType) {
        BigDecimal admissionfeeAmount;
        if (serviceType != null)
            admissionfeeAmount = getTotalFeeAmountByPassingServiceTypeandArea(
                    Long.valueOf(serviceType), BpaConstants.BPAFEETYPE);
        else
            admissionfeeAmount = BigDecimal.ZERO;
        return admissionfeeAmount;
    }

    public BigDecimal getTotalFeeAmountByPassingServiceTypeandArea(final Long serviceTypeId, final String feeType) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        if (serviceTypeId != null) {
            final Criteria feeCrit = applicationBpaBillService.getBpaFeeCriteria(serviceTypeId, feeType);
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

    

}
