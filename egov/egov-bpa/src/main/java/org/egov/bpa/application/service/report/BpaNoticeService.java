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
package org.egov.bpa.application.service.report;

import static org.egov.bpa.utils.BpaConstants.BPADEMANDNOTICETITLE;
import 
static org.egov.bpa.utils.BpaConstants.BPA_ADM_FEE;
import static org.egov.bpa.utils.BpaConstants.BPA_COMPOUND_FEE;
import static org.egov.bpa.utils.BpaConstants.BPA_PERMIT_FEE;
import static org.egov.bpa.utils.BpaConstants.BPA_WELL_FEE;
import static org.egov.bpa.utils.BpaConstants.BUILDINGDEVELOPPERMITFILENAME;
import static org.egov.bpa.utils.BpaConstants.BUILDINGPERMITFILENAME;
import static org.egov.bpa.utils.BpaConstants.DEMANDNOCFILENAME;
import static org.egov.infra.utils.DateUtils.currentDateToDefaultDateFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.WordUtils;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.utils.BpaConstants;
import org.egov.commons.Installment;
import org.egov.demand.model.EgDemandDetails;
import org.egov.infra.reporting.engine.ReportOutput;
import org.egov.infra.reporting.engine.ReportRequest;
import org.egov.infra.reporting.engine.ReportService;
import org.egov.infra.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BpaNoticeService {
    @Autowired
    private ReportService reportService;

    public ResponseEntity<byte[]> generateDemandNotice(HttpServletRequest request, final BpaApplication bpaApplication) {
        ReportRequest reportInput = null;
        ReportOutput reportOutput;
        if (null != bpaApplication) {
            final Map<String, Object> reportParams = buildParametersForReport(request, bpaApplication);
            reportParams.putAll(buildParametersForDemandDetails(bpaApplication));
            reportInput = new ReportRequest(DEMANDNOCFILENAME, bpaApplication, reportParams);
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("content-disposition", "inline;filename=Demand Notice.pdf");
        reportOutput = reportService.createReport(reportInput);
        return new ResponseEntity<>(reportOutput.getReportOutputData(), headers, HttpStatus.CREATED);
    }
    
    public ResponseEntity<byte[]> generateBuildingPermit(HttpServletRequest request, final BpaApplication bpaApplication) {
        ReportRequest reportInput = null;
        ReportOutput reportOutput;
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
            reportInput = new ReportRequest(reportFileName, bpaApplication.getBuildingDetail().get(0), reportParams);
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("content-disposition", "inline;filename="+reportFileName+".pdf");
        reportOutput = reportService.createReport(reportInput);
        return new ResponseEntity<>(reportOutput.getReportOutputData(), headers, HttpStatus.CREATED);
    }
    
    private Map<String, Object> buildParametersForDemandDetails(final BpaApplication bpaApplication) {
        BigDecimal permitFee = BigDecimal.ZERO;
        BigDecimal wallCharges = BigDecimal.ZERO;
        BigDecimal compoundFee = BigDecimal.ZERO;
        BigDecimal totalPendingAmt = BigDecimal.ZERO;
        final Map<String, Object> reportParams = new HashMap<>();
        Installment currentInstallemnt = bpaApplication.getDemand().getEgInstallmentMaster();
        for (final EgDemandDetails demandDtl : bpaApplication.getDemand().getEgDemandDetails()) {
            if (!BPA_ADM_FEE.equalsIgnoreCase(demandDtl.getEgDemandReason().getEgDemandReasonMaster().getReasonMaster())
                    && demandDtl.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                if (BPA_PERMIT_FEE.equalsIgnoreCase(demandDtl.getEgDemandReason().getEgDemandReasonMaster().getReasonMaster())) {
                    permitFee = demandDtl.getBalance().setScale(2, BigDecimal.ROUND_HALF_EVEN);
                } else if (BPA_WELL_FEE
                        .equalsIgnoreCase(demandDtl.getEgDemandReason().getEgDemandReasonMaster().getReasonMaster())) {
                    wallCharges = demandDtl.getBalance().setScale(2, BigDecimal.ROUND_HALF_EVEN);
                } else if (BPA_COMPOUND_FEE
                        .equalsIgnoreCase(demandDtl.getEgDemandReason().getEgDemandReasonMaster().getReasonMaster())) {
                    compoundFee = demandDtl.getBalance().setScale(2, BigDecimal.ROUND_HALF_EVEN);
                }
            }
        }
        totalPendingAmt = totalPendingAmt.add(permitFee).add(wallCharges).add(compoundFee);
        reportParams.put("installmentDesc",
                currentInstallemnt.getDescription() != null ? currentInstallemnt.getDescription() : "");
        reportParams.put("permitFee", permitFee);
        reportParams.put("wellCharges", wallCharges);
        reportParams.put("compoundFee", compoundFee);
        reportParams.put("totalPendingAmt", totalPendingAmt.setScale(2, BigDecimal.ROUND_HALF_EVEN));

        return reportParams;
    }

    private Map<String, Object> buildParametersForReport(HttpServletRequest request,
            final BpaApplication bpaApplication) {
        final Map<String, Object> reportParams = new HashMap<>();

        final String url = WebUtils.extractRequestDomainURL(request, false);
        final String cityLogo = url.concat(BpaConstants.IMAGE_CONTEXT_PATH).concat(
                (String) request.getSession().getAttribute("citylogo"));
        final String ulbName = request.getSession().getAttribute("citymunicipalityname").toString();
        final String cityName = request.getSession().getAttribute("cityname").toString();
        reportParams.put("cityName", cityName);
        reportParams.put("logoPath", cityLogo);
        reportParams.put("ulbName", ulbName);
        reportParams.put("bpademandtitle",
                WordUtils.capitalize(BPADEMANDNOTICETITLE));

        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        reportParams.put("currentDate", currentDateToDefaultDateFormat());
        reportParams.put("lawAct", "Kerala Municipal Building Development Act");
        reportParams.put("applicationNumber", bpaApplication.getApplicationNumber());
        reportParams.put("buildingPermitNumber", bpaApplication.getPlanPermissionNumber() != null ? bpaApplication.getPlanPermissionNumber() : "");
        reportParams.put("applicantName", bpaApplication.getOwner().getApplicantName());
        reportParams.put("applicantAddress", bpaApplication.getOwner().getAddress());
        reportParams.put("applicationDate", formatter.format(bpaApplication.getApplicationDate()));
        reportParams.put("serviceTypeDesc", bpaApplication.getServiceType().getDescription());
        reportParams.put("projectName", bpaApplication.getProjectName() != null ? bpaApplication.getProjectName() : "");
        reportParams.put("occupancy", bpaApplication.getOccupancy() != null ? bpaApplication.getOccupancy().toString() : "");
        reportParams.put("electionWard", bpaApplication.getSiteDetail().get(0).getElectionBoundary().getName());
        reportParams.put("revenueWard", bpaApplication.getSiteDetail().get(0).getAdminBoundary() != null
                ? bpaApplication.getSiteDetail().get(0).getAdminBoundary().getName() : "");
        if(!bpaApplication.getSiteDetail().isEmpty()) {
            reportParams.put("landExtent", bpaApplication.getSiteDetail().get(0).getExtentinsqmts());
        reportParams.put("buildingNo", bpaApplication.getSiteDetail().get(0).getPlotnumber() != null ? bpaApplication.getSiteDetail().get(0).getPlotnumber() : "");
        reportParams.put("nearestBuildingNo", bpaApplication.getSiteDetail().get(0).getNearestbuildingnumber() != null ? bpaApplication.getSiteDetail().get(0).getNearestbuildingnumber() : "");
        reportParams.put("surveyNo", bpaApplication.getSiteDetail().get(0).getPlotsurveynumber() != null ? bpaApplication.getSiteDetail().get(0).getPlotsurveynumber() : "");
        reportParams.put("village", bpaApplication.getSiteDetail().get(0).getVillage() != null ? bpaApplication.getSiteDetail().get(0).getVillage().getName() : "");
        reportParams.put("taluk", bpaApplication.getSiteDetail().get(0).getTaluk() != null ? bpaApplication.getSiteDetail().get(0).getTaluk() : "");
        reportParams.put("district", bpaApplication.getSiteDetail().get(0).getDistrict() != null ? bpaApplication.getSiteDetail().get(0).getDistrict() : "");
        }
        if(!bpaApplication.getBuildingDetail().isEmpty()) {
            reportParams.put("basement", bpaApplication.getBuildingDetail().get(0).getNoofbasementUnit() != null ? bpaApplication.getBuildingDetail().get(0).getNoofbasementUnit() : "");
            
            }
        return reportParams;
    }
}
