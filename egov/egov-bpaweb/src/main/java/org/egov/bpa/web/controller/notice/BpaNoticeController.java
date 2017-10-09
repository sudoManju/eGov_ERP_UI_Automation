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

package org.egov.bpa.web.controller.notice;

import static org.egov.bpa.utils.BpaConstants.BUILDINGPERMITFILENAME;
import static org.egov.bpa.utils.BpaConstants.DEMANDNOCFILENAME;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.egov.bpa.transaction.service.ApplicationBpaService;
import org.egov.bpa.transaction.service.notice.BpaNoticeService;
import org.egov.bpa.utils.BpaConstants;
import org.egov.infra.reporting.engine.ReportOutput;
import org.egov.infra.reporting.util.ReportUtil;
import org.egov.infra.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BpaNoticeController {

    private static final String PDFEXTN = ".pdf";
    private static final String INLINE_FILENAME = "inline;filename=";
    private static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String APPLICATION_PDF = "application/pdf";

    @Autowired
    private ApplicationBpaService applicationBpaService;
    @Autowired
    private BpaNoticeService bpaReportService;

    @RequestMapping(value = "/application/demandnotice/{applicationNumber}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> viewDemandNoticeReport(@PathVariable final String applicationNumber, HttpServletRequest request)
            throws IOException {
        Map<String, Object> ulbDetailsReportParams = buildUlbDetails(request);
        ReportOutput reportOutput = bpaReportService
                .generateDemandNotice(applicationBpaService.findByApplicationNumber(applicationNumber), ulbDetailsReportParams);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(APPLICATION_PDF));
        headers.add(CONTENT_DISPOSITION, INLINE_FILENAME + DEMANDNOCFILENAME + PDFEXTN);
        return new ResponseEntity<>(reportOutput.getReportOutputData(), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/application/generatepermitorder/{applicationNumber}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> generateBuildingPermitOrder(@PathVariable final String applicationNumber,
            HttpServletRequest request) throws IOException {
        Map<String, Object> ulbDetailsReportParams = buildUlbDetails(request);
        ReportOutput reportOutput = bpaReportService
                .generatePermitOrder(applicationBpaService.findByApplicationNumber(applicationNumber), ulbDetailsReportParams);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(APPLICATION_PDF));
        headers.add(CONTENT_DISPOSITION, INLINE_FILENAME + BUILDINGPERMITFILENAME + "order" + PDFEXTN);
        return new ResponseEntity<>(reportOutput.getReportOutputData(), headers, HttpStatus.CREATED);
    }

    private Map<String, Object> buildUlbDetails(HttpServletRequest request) {
        final Map<String, Object> ulbDetailsReportParams = new HashMap<>();
        final String url = WebUtils.extractRequestDomainURL(request, false);
        final String cityLogo = url.concat(BpaConstants.IMAGE_CONTEXT_PATH)
                .concat((String) request.getSession().getAttribute("citylogo"));
        ulbDetailsReportParams.put("cityName", request.getSession().getAttribute("cityname").toString());
        ulbDetailsReportParams.put("logoPath", cityLogo);
        ulbDetailsReportParams.put("ulbName", request.getSession().getAttribute("citymunicipalityname").toString());
        ulbDetailsReportParams.put("duplicateWatermarkPath", ReportUtil.duplicateWatermarkAbsolutePath(request));
        return ulbDetailsReportParams;
    }
}