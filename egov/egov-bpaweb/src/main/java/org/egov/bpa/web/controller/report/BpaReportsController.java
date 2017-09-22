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
package org.egov.bpa.web.controller.report;

import static org.egov.infra.utils.JsonUtils.toJSON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.egov.bpa.transaction.entity.dto.SearchBpaApplicationForm;
import org.egov.bpa.transaction.entity.dto.SearchBpaApplicationReport;
import org.egov.bpa.transaction.service.SearchBpaApplicationService;
import org.egov.bpa.transaction.service.report.BpaReportsService;
import org.egov.bpa.web.controller.adaptor.SearchBpaApplicationFormAdaptor;
import org.egov.bpa.web.controller.adaptor.SearchBpaApplicationReportAdaptor;
import org.egov.bpa.web.controller.transaction.BpaGenericApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/reports")
public class BpaReportsController extends BpaGenericApplicationController {

    private static final String DATA = "{ \"data\":";

    @Autowired
    private BpaReportsService bpaReportsService;
    @Autowired
    private SearchBpaApplicationService searchBpaApplicationService;

    @RequestMapping(value = "/servicewise-statusreport", method = RequestMethod.GET)
    public String searchStatusCountByServicetypeForm(final Model model) {
        model.addAttribute("searchBpaApplicationForm", new SearchBpaApplicationForm());
        return "search-servicewise-status-report";
    }

    @RequestMapping(value = "/servicewise-statusreport", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getStatusCountByServicetypeResult(final Model model,
            @ModelAttribute final SearchBpaApplicationForm searchBpaApplicationForm) {
        final List<SearchBpaApplicationReport> searchResultList = bpaReportsService
                .getResultsByServicetypeAndStatus(searchBpaApplicationForm);
        return new StringBuilder(DATA)
                .append(toJSON(searchResultList, SearchBpaApplicationReport.class, SearchBpaApplicationReportAdaptor.class))
                .append("}")
                .toString();
    }

    @RequestMapping(value = "/servicewise-statusreport/view", method = RequestMethod.GET)
    public String viewStatusCountByServicetypeDetails(@RequestParam final String applicantName,
            @RequestParam final String applicationNumber,
            @RequestParam final Long ward, @RequestParam final Date fromDate,
            @RequestParam final Date toDate, @RequestParam final Long revenueWard, @RequestParam final Long electionWard,
            @RequestParam final Long zoneId, @RequestParam final String status, @RequestParam final String serviceType,
            @RequestParam final String zone, final Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        model.addAttribute("applicantName", applicantName);
        model.addAttribute("applicationNumber", applicationNumber);
        model.addAttribute("ward", ward);
        if (fromDate != null) {
            model.addAttribute("fromDate", dateFormat.format(fromDate));
        } else {
            model.addAttribute("fromDate", fromDate);
        }
        if (toDate != null) {
            model.addAttribute("toDate", dateFormat.format(toDate));
        } else {
            model.addAttribute("toDate", toDate);
        }
        model.addAttribute("revenueWard", revenueWard);
        model.addAttribute("electionWard", electionWard);
        model.addAttribute("zone", zone);
        model.addAttribute("zoneId", zoneId);
        model.addAttribute("status", status);
        model.addAttribute("serviceType", serviceType);
        return "view-servicewise-appln-details";
    }

    @RequestMapping(value = "/servicewise-statusreport/view", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String viewStatusCountByServicetypeDetails(@ModelAttribute final SearchBpaApplicationForm searchBpaApplicationForm,
            final Model model) {
        final List<SearchBpaApplicationForm> searchResultList = searchBpaApplicationService.search(searchBpaApplicationForm);
        return new StringBuilder(DATA)
                .append(toJSON(searchResultList, SearchBpaApplicationForm.class, SearchBpaApplicationFormAdaptor.class))
                .append("}")
                .toString();
    }

    @RequestMapping(value = "/zonewisedetails", method = RequestMethod.GET)
    public String searchZoneWiseServicesForm(final Model model) {
        model.addAttribute("searchBpaApplicationForm", new SearchBpaApplicationForm());
        return "search-zonewise-report";
    }

    @RequestMapping(value = "/zonewisedetails", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getZoneWiseServicesResult(final Model model,
            @ModelAttribute final SearchBpaApplicationForm searchBpaApplicationForm) {
        final List<SearchBpaApplicationReport> searchResultList = bpaReportsService
                .getResultsForEachServicetypeByZone(searchBpaApplicationForm);
        return new StringBuilder(DATA)
                .append(toJSON(searchResultList, SearchBpaApplicationReport.class, SearchBpaApplicationReportAdaptor.class))
                .append("}")
                .toString();
    }

}