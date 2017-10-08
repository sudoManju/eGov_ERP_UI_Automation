/*
 * eGov  SmartCity eGovernance suite aims to improve the internal efficiency,transparency,
 * accountability and the service delivery of the government  organizations.
 *
 *  Copyright (C) <2017>  eGovernments Foundation
 *
 *  The updated version of eGov suite of products as by eGovernments Foundation
 *  is available at http://www.egovernments.org
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/ or
 *  http://www.gnu.org/licenses/gpl.html .
 *
 *  In addition to the terms of the GPL license to be adhered to in using this
 *  program, the following additional terms are to be complied with:
 *
 *      1) All versions of this program, verbatim or modified must carry this
 *         Legal Notice.
 *      Further, all user interfaces, including but not limited to citizen facing interfaces,
 *         Urban Local Bodies interfaces, dashboards, mobile applications, of the program and any
 *         derived works should carry eGovernments Foundation logo on the top right corner.
 *
 *      For the logo, please refer http://egovernments.org/html/logo/egov_logo.png.
 *      For any further queries on attribution, including queries on brand guidelines,
 *         please contact contact@egovernments.org
 *
 *      2) Any misrepresentation of the origin of the material is prohibited. It
 *         is required that all modified versions of this material be marked in
 *         reasonable ways as different from the original version.
 *
 *      3) This license does not grant any rights to any user of the program
 *         with regards to rights under trademark law for use of the trade names
 *         or trademarks of eGovernments Foundation.
 *
 *  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.bpa.transaction.service.notice;

import static org.egov.bpa.utils.BpaConstants.APPLICATION_MODULE_TYPE;
import static org.egov.bpa.utils.BpaConstants.BPADEMANDNOTICETITLE;
import static org.egov.bpa.utils.BpaConstants.BPA_ADM_FEE;
import static org.egov.bpa.utils.BpaConstants.BPA_DEMAND_NOTICE_TYPE;
import static org.egov.bpa.utils.BpaConstants.BUILDINGDEVELOPPERMITFILENAME;
import static org.egov.bpa.utils.BpaConstants.BUILDINGPERMITFILENAME;
import static org.egov.bpa.utils.BpaConstants.DEMANDNOCFILENAME;
import static org.egov.bpa.utils.BpaConstants.PERMIT_ORDER_NOTICE_TYPE;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_05;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_08;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_09;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_14;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_15;
import static org.egov.infra.utils.DateUtils.currentDateToDefaultDateFormat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.egov.bpa.master.entity.PermitConditions;
import org.egov.bpa.master.entity.ServiceType;
import org.egov.bpa.transaction.entity.BpaApplication;
import org.egov.bpa.transaction.entity.BpaNotice;
import org.egov.bpa.transaction.entity.BuildingDetail;
import org.egov.bpa.transaction.entity.Response;
import org.egov.bpa.transaction.repository.BpaNoticeRepository;
import org.egov.bpa.transaction.service.ApplicationBpaService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.bpa.utils.BpaUtils;
import org.egov.commons.Installment;
import org.egov.demand.model.EgDemandDetails;
import org.egov.infra.filestore.entity.FileStoreMapper;
import org.egov.infra.filestore.service.FileStoreService;
import org.egov.infra.reporting.engine.ReportFormat;
import org.egov.infra.reporting.engine.ReportOutput;
import org.egov.infra.reporting.engine.ReportRequest;
import org.egov.infra.reporting.engine.ReportService;
import org.egov.infra.reporting.util.ReportUtil;
import org.egov.infra.utils.DateUtils;
import org.egov.infra.web.utils.WebUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BpaNoticeService {
    
    private static final String APPLICATION_PDF = "application/pdf";
    @Autowired
    private ReportService reportService;
    @Autowired
    @Qualifier("fileStoreService")
    protected FileStoreService fileStoreService;
    @Autowired
    private BpaUtils bpaUtils;
    @Autowired
    @Qualifier("parentMessageSource")
    private MessageSource bpaMessageSource;
    @Autowired
    private BpaNoticeRepository bpaNoticeRepository;
    @Autowired
    private ApplicationBpaService applicationBpaService;

    public BpaNotice findByApplicationAndNoticeType(final BpaApplication application, final String noticeType) {
        return bpaNoticeRepository.findByApplicationAndNoticeType(application, noticeType);
    }

    public ReportOutput generateDemandNoticeReportOutput(HttpServletRequest request,
            final BpaApplication bpaApplication) {
        ReportRequest reportInput = null;
        if (null != bpaApplication) {
            final Map<String, Object> reportParams = buildParametersForReport(request, bpaApplication);
            reportParams.putAll(buildParametersForDemandDetails(bpaApplication));
            reportInput = new ReportRequest(DEMANDNOCFILENAME, bpaApplication, reportParams);
        }
        return reportService.createReport(reportInput);
    }

    public ResponseEntity<byte[]> generateDemandNotice(final HttpServletRequest request, final BpaApplication application)
            throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        ReportOutput reportOutput = new ReportOutput();
        String fileName = "bpa-demand-notice".concat(String.valueOf(application.getApplicationNumber()));
        BpaNotice bpaNotice = findByApplicationAndNoticeType(application, BPA_DEMAND_NOTICE_TYPE);
        if (bpaNotice != null && bpaNotice.getNoticeFileStore() != null) {
            final FileStoreMapper fmp = application.getBpaNotice().get(0).getNoticeFileStore();
            final File file = fileStoreService.fetch(fmp, APPLICATION_MODULE_TYPE);
            reportOutput.setReportOutputData(FileUtils.readFileToByteArray(file));
            reportOutput.setReportFormat(ReportFormat.PDF);
        } else {
            reportOutput = generateDemandNoticeReportOutput(request, application);
            saveBpaNotices(application, reportOutput, fileName, BPA_DEMAND_NOTICE_TYPE);
        }
        headers.setContentType(MediaType.parseMediaType(APPLICATION_PDF));
        headers.add("content-disposition", "inline;filename=" + fileName + ".pdf");
        return new ResponseEntity<>(reportOutput.getReportOutputData(), headers, HttpStatus.CREATED);
    }

    public ReportOutput generateBuildingPermitReportOutput(HttpServletRequest request,
            final BpaApplication bpaApplication) {
        ReportRequest reportInput = null;
        String reportFileName = null;
        if (null != bpaApplication) {
            if (BpaConstants.getServicesForBuildPermit().contains(bpaApplication.getServiceType().getCode())) {
                reportFileName = BUILDINGPERMITFILENAME;
            } else if (BpaConstants.getServicesForDevelopPermit().contains(bpaApplication.getServiceType().getCode())) {
                reportFileName = BUILDINGDEVELOPPERMITFILENAME;
            } else {
                reportFileName = BUILDINGPERMITFILENAME;
            }
            final Map<String, Object> reportParams = buildParametersForReport(request, bpaApplication);
            reportInput = new ReportRequest(reportFileName, !bpaApplication.getBuildingDetail().isEmpty()
                    ? bpaApplication.getBuildingDetail().get(0) : new BuildingDetail(), reportParams);
        }
        return reportService.createReport(reportInput);
    }

    private Map<String, Object> buildParametersForDemandDetails(final BpaApplication bpaApplication) {
        List<Response> demandResponseList = new ArrayList<>();
        BigDecimal totalPendingAmt = BigDecimal.ZERO;
        final Map<String, Object> reportParams = new HashMap<>();
        Installment currentInstallemnt = bpaApplication.getDemand().getEgInstallmentMaster();
        for (final EgDemandDetails demandDtl : bpaApplication.getDemand().getEgDemandDetails()) {
            Response response = new Response();
            if (!BPA_ADM_FEE.equalsIgnoreCase(demandDtl.getEgDemandReason().getEgDemandReasonMaster().getReasonMaster())
                    && demandDtl.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                response.setDemandDescription(
                        demandDtl.getEgDemandReason().getEgDemandReasonMaster().getReasonMaster());
                response.setDemandAmount(demandDtl.getBalance().setScale(2, BigDecimal.ROUND_HALF_EVEN));
                totalPendingAmt = totalPendingAmt.add(demandDtl.getBalance());
                demandResponseList.add(response);
            }
        }
        reportParams.put("installmentDesc",
                currentInstallemnt.getDescription() != null ? currentInstallemnt.getDescription() : "");
        reportParams.put("demandResponseList", demandResponseList);
        reportParams.put("totalPendingAmt", totalPendingAmt.setScale(2, BigDecimal.ROUND_HALF_EVEN));
        return reportParams;
    }

    public ResponseEntity<byte[]> generatePermitOrder(final HttpServletRequest request, final BpaApplication application)
            throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        ReportOutput reportOutput = new ReportOutput();
        String fileName;
        BpaNotice bpaNotice = findByApplicationAndNoticeType(application, PERMIT_ORDER_NOTICE_TYPE);
        if (bpaNotice != null && bpaNotice.getNoticeFileStore() != null) {
            final FileStoreMapper fmp = application.getBpaNotice().get(0).getNoticeFileStore();
            final File file = fileStoreService.fetch(fmp, APPLICATION_MODULE_TYPE);
            fileName = file.getName();
            reportOutput.setReportOutputData(FileUtils.readFileToByteArray(file));
            reportOutput.setReportFormat(ReportFormat.PDF);
        } else {
            fileName = application.getPlanPermissionNumber();
            reportOutput = generateBuildingPermitReportOutput(request, application);
            saveBpaNotices(application, reportOutput, fileName, PERMIT_ORDER_NOTICE_TYPE);
        }
        headers.setContentType(MediaType.parseMediaType(APPLICATION_PDF));
        headers.add("content-disposition", "inline;filename=" + fileName + ".pdf");
        return new ResponseEntity<>(reportOutput.getReportOutputData(), headers, HttpStatus.CREATED);
    }

    private BpaNotice saveBpaNotices(final BpaApplication application, ReportOutput reportOutput, String fileName,
            String noticeType) {
        BpaNotice bpaNotice1 = new BpaNotice();
        bpaNotice1.setApplication(application);
        bpaNotice1.setNoticeFileStore(
                fileStoreService.store(new ByteArrayInputStream(reportOutput.getReportOutputData()), fileName, APPLICATION_PDF,
                        APPLICATION_MODULE_TYPE));
        bpaNotice1.setNoticeGeneratedDate(new Date());
        bpaNotice1.setNoticeType(noticeType);
        application.addNotice(bpaNotice1);
        applicationBpaService.saveAndFlushApplication(application);
        return bpaNotice1;
    }

    private Map<String, Object> buildParametersForReport(HttpServletRequest request,
            final BpaApplication bpaApplication) {
        final Map<String, Object> reportParams = new HashMap<>();
        StringBuilder serviceTypeDesc = new StringBuilder();
        final String url = WebUtils.extractRequestDomainURL(request, false);
        final String cityLogo = url.concat(BpaConstants.IMAGE_CONTEXT_PATH)
                .concat((String) request.getSession().getAttribute("citylogo"));
        final String ulbName = request.getSession().getAttribute("citymunicipalityname").toString();
        final String cityName = request.getSession().getAttribute("cityname").toString();
        reportParams.put("cityName", cityName);
        reportParams.put("logoPath", cityLogo);
        reportParams.put("ulbName", ulbName);
        reportParams.put("duplicateWatermarkPath", ReportUtil.duplicateWatermarkAbsolutePath(request));
        reportParams.put("bpademandtitle", WordUtils.capitalize(BPADEMANDNOTICETITLE));
        reportParams.put("currentDate", currentDateToDefaultDateFormat());
        reportParams.put("lawAct", "[See Rule 11 (3)]");
        reportParams.put("applicationNumber", bpaApplication.getApplicationNumber());
        reportParams.put("buildingPermitNumber",
                bpaApplication.getPlanPermissionNumber() != null ? bpaApplication.getPlanPermissionNumber() : "");
        reportParams.put("applicantName", bpaApplication.getOwner().getUser().getName());
        reportParams.put("applicantAddress",
                bpaApplication.getOwner() != null && !bpaApplication.getOwner().getUser().getAddress().isEmpty()
                        ? bpaApplication.getOwner().getUser().getAddress().get(0).getStreetRoadLine() : "");
        reportParams.put("applicationDate", DateUtils.getDefaultFormattedDate(bpaApplication.getApplicationDate()));
        reportParams.put("permitConditions", buildPermitConditions(bpaApplication));
        String amenities = bpaApplication.getApplicationAmenity().stream().map(ServiceType::getDescription)
                .collect(Collectors.joining(", "));
        if (bpaApplication.getApplicationAmenity().isEmpty()) {
            serviceTypeDesc.append(bpaApplication.getServiceType().getDescription());
        } else {
            serviceTypeDesc.append(bpaApplication.getServiceType().getDescription()).append(" with amenities ")
                    .append(amenities);
        }
        reportParams.put("serviceTypeDesc", serviceTypeDesc.toString());
        reportParams.put("serviceTypeForDmd", bpaApplication.getServiceType().getDescription());
        reportParams.put("amenities", amenities != null ? amenities : "");
        reportParams.put("occupancy",
                bpaApplication.getOccupancy() != null ? bpaApplication.getOccupancy().getDescription() : "");
        reportParams.put("electionWard", bpaApplication.getSiteDetail().get(0).getElectionBoundary().getName());
        reportParams.put("revenueWard", bpaApplication.getSiteDetail().get(0).getAdminBoundary() != null
                ? bpaApplication.getSiteDetail().get(0).getAdminBoundary().getName() : "");
        if (!bpaApplication.getSiteDetail().isEmpty()) {
            reportParams.put("landExtent", bpaApplication.getSiteDetail().get(0).getExtentinsqmts());
            reportParams.put("buildingNo", bpaApplication.getSiteDetail().get(0).getPlotnumber() != null
                    ? bpaApplication.getSiteDetail().get(0).getPlotnumber() : "");
            reportParams.put("nearestBuildingNo",
                    bpaApplication.getSiteDetail().get(0).getNearestbuildingnumber() != null
                            ? bpaApplication.getSiteDetail().get(0).getNearestbuildingnumber() : "");
            reportParams.put("surveyNo", bpaApplication.getSiteDetail().get(0).getReSurveyNumber() != null
                    ? bpaApplication.getSiteDetail().get(0).getReSurveyNumber() : "");
            reportParams.put("village", bpaApplication.getSiteDetail().get(0).getLocationBoundary() != null
                    ? bpaApplication.getSiteDetail().get(0).getLocationBoundary().getName() : "");
            reportParams.put("taluk", bpaApplication.getSiteDetail().get(0).getPostalAddress().getTaluk() != null
                    ? bpaApplication.getSiteDetail().get(0).getPostalAddress().getTaluk() : "");
            reportParams.put("district", bpaApplication.getSiteDetail().get(0).getPostalAddress() != null
                    ? bpaApplication.getSiteDetail().get(0).getPostalAddress().getDistrict() : "");
        }
        reportParams.put("certificateValidity",
                getValidityDescription(bpaApplication.getServiceType().getCode(), bpaApplication.getPlanPermissionDate()));
        reportParams.put("isBusinessUser", bpaUtils.logedInuseCitizenOrBusinessUser());
        reportParams.put("designation", getApproverDesignation(getAmountRuleByServiceType(bpaApplication)));
        return reportParams;
    }

    private String getValidityDescription(final String serviceTypeCode, final Date planPermissionDate) {
        StringBuilder certificateValidatiy = new StringBuilder();
        String validityExpiryDate;
        if (serviceTypeCode.equals(ST_CODE_14) || serviceTypeCode.equals(ST_CODE_15)) {
            validityExpiryDate = calculateCertExpryDate(new DateTime(planPermissionDate),
                    getMessageFromPropertyFile("tower.pole.certificate.expiry"));
        } else {
            validityExpiryDate = calculateCertExpryDate(new DateTime(planPermissionDate),
                    getMessageFromPropertyFile("common.services.certificate.expiry"));
        }
        certificateValidatiy.append("\n\nNote : This certificate is valid upto ").append(validityExpiryDate).append(" Only.");
        return certificateValidatiy.toString();
    }

    private String buildPermitConditions(final BpaApplication bpaApplication) {
        int order = 1;
        StringBuilder permitConditions = new StringBuilder();
        if (bpaApplication.getServiceType().getCode().equals(ST_CODE_14)
                || bpaApplication.getServiceType().getCode().equals(ST_CODE_15)) {
            permitConditions.append(getMessageFromPropertyFile("tower.pole.permit.condition1"))
                    .append(getMessageFromPropertyFile("tower.pole.permit.condition2"))
                    .append(getMessageFromPropertyFile("tower.pole.permit.condition3"))
                    .append(getMessageFromPropertyFile("tower.pole.permit.condition4"))
                    .append(getMessageFromPropertyFile("tower.pole.permit.condition5"))
                    .append(getMessageFromPropertyFile("tower.pole.permit.condition6"))
                    .append(getMessageFromPropertyFile("tower.pole.permit.condition7"))
                    .append(getMessageFromPropertyFileWithParameters("tower.pole.permit.condition8",
                            bpaApplication.getOwner().getUser().getName()))
                    .append(getMessageFromPropertyFile("tower.pole.permit.condition9"))
                    .append(getMessageFromPropertyFileWithParameters("tower.pole.permit.condition10",
                            bpaApplication.getPlanPermissionDate().toString()))
                    .append(getMessageFromPropertyFile("tower.pole.permit.condition11"));
            if (StringUtils.isNotBlank(bpaApplication.getAdditionalPermitConditions())) {
                permitConditions
                        .append(String.valueOf("\n\n12").concat(") ").concat(bpaApplication.getAdditionalPermitConditions()));
            }
        } else {
            for (PermitConditions pc : bpaApplication.getPermitConditions()) {
                if (bpaApplication.getPermitConditions().size() - 1 == order - 1
                        && StringUtils.isBlank(bpaApplication.getAdditionalPermitConditions())) {
                    permitConditions.append(String.valueOf(order).concat(") ").concat(pc.getDescription()));
                } else {
                    permitConditions.append(String.valueOf(order).concat(") ").concat(pc.getDescription()).concat("\n\n"));
                }
                order++;
            }
            if (StringUtils.isNotBlank(bpaApplication.getAdditionalPermitConditions())) {
                permitConditions.append(
                        String.valueOf(order).concat(") ").concat(bpaApplication.getAdditionalPermitConditions()));
            }
        }
        return permitConditions.toString();
    }

    private String getMessageFromPropertyFile(String key) {
        return bpaMessageSource.getMessage(key, null, null);
    }

    private String getMessageFromPropertyFileWithParameters(String key, String value) {
        return bpaMessageSource.getMessage(key, new String[] { value }, null);
    }

    private String calculateCertExpryDate(DateTime permissionDate, String noOfYears) {
        DateTimeFormatter fmt = DateUtils.defaultDateFormatter();
        return fmt.print(permissionDate.plusYears(Integer.valueOf(noOfYears)));
    }

    private Integer getAmountRuleByServiceType(final BpaApplication application) {
        BigDecimal amountRule = BigDecimal.ONE;
        if (ST_CODE_14.equalsIgnoreCase(application.getServiceType().getCode())
                || ST_CODE_15.equalsIgnoreCase(application.getServiceType().getCode())) {
            amountRule = new BigDecimal(11000);
        } else if (ST_CODE_05.equalsIgnoreCase(application.getServiceType().getCode())) {
            amountRule = application.getDocumentScrutiny().get(0).getExtentinsqmts();
        } else if (ST_CODE_08.equalsIgnoreCase(application.getServiceType().getCode())
                || ST_CODE_09.equalsIgnoreCase(application.getServiceType().getCode())) {
            amountRule = BigDecimal.ONE;
        } else if (!application.getBuildingDetail().isEmpty()
                && application.getBuildingDetail().get(0).getTotalPlintArea() != null) {
            amountRule = application.getBuildingDetail().get(0).getTotalPlintArea();
        }
        return amountRule.intValue();
    }

    private String getApproverDesignation(final Integer amountRule) {
        String designation = StringUtils.EMPTY;
        if (amountRule >= 0 && amountRule <= 299) {
            designation = "Assistant Engineer";
        } else if (amountRule >= 300 && amountRule <= 749) {
            designation = "Assistant Executive Engineer";
        } else if (amountRule >= 750 && amountRule <= 1499) {
            designation = "Executive Engineer";
        } else if (amountRule >= 1500 && amountRule <= 9999) {
            designation = "Corporation Engineer";
        } else if (amountRule >= 10000 && amountRule <= 1000000) {
            designation = "Secretary";
        }
        return designation;
    }
}
