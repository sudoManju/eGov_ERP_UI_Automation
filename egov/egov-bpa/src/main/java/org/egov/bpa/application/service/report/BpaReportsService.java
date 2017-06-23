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

import static org.egov.bpa.utils.BpaConstants.ST_CODE_01;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_02;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_03;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_04;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_05;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_06;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_07;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_08;
import static org.egov.bpa.utils.BpaConstants.ST_CODE_09;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.egov.bpa.application.entity.dto.SearchBpaApplicationForm;
import org.egov.bpa.application.entity.dto.SearchBpaApplicationReport;
import org.egov.bpa.application.service.SearchBpaApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BpaReportsService {

    @Autowired
    private SearchBpaApplicationService searchBpaApplicationService;

    public List<SearchBpaApplicationReport> getResultsByServicetypeAndStatus(
            final SearchBpaApplicationForm searchBpaApplicationForm) {
        List<SearchBpaApplicationReport> searchBpaApplicationReportList = new ArrayList<>();
        List<SearchBpaApplicationForm> searchBpaApplnResultList = searchBpaApplicationService.search(searchBpaApplicationForm);
        Map<String, Map<String, Long>> resultMap = searchBpaApplnResultList.stream().collect(
                Collectors.groupingBy(SearchBpaApplicationForm::getStatus,
                        Collectors.groupingBy(SearchBpaApplicationForm::getServiceCode, Collectors.counting())));
        for (final Entry<String, Map<String, Long>> statusCountResMap : resultMap.entrySet()) {
            SearchBpaApplicationReport bpaApplicationReport = new SearchBpaApplicationReport();
            bpaApplicationReport.setStatus(statusCountResMap.getKey());
            for (final Entry<String, Long> statusCountMap : statusCountResMap.getValue().entrySet()) {
                bpaApplicationReport
                        .setServiceType01(ST_CODE_01.equalsIgnoreCase(statusCountMap.getKey())
                                ? statusCountMap.getValue() : 0l);
                bpaApplicationReport
                        .setServiceType02(ST_CODE_02.equalsIgnoreCase(statusCountMap.getKey())
                                ? statusCountMap.getValue() : 0l);
                bpaApplicationReport.setServiceType03(
                        ST_CODE_03.equalsIgnoreCase(statusCountMap.getKey()) ? statusCountMap.getValue() : 0l);
                bpaApplicationReport
                        .setServiceType04(ST_CODE_04.equalsIgnoreCase(statusCountMap.getKey())
                                ? statusCountMap.getValue() : 0l);
                bpaApplicationReport.setServiceType05(
                        ST_CODE_05.equalsIgnoreCase(statusCountMap.getKey()) ? statusCountMap.getValue()
                                : 0l);
                bpaApplicationReport
                        .setServiceType06(ST_CODE_06.equalsIgnoreCase(statusCountMap.getKey())
                                ? statusCountMap.getValue() : 0l);
                bpaApplicationReport
                        .setServiceType07(ST_CODE_07.equalsIgnoreCase(statusCountMap.getKey())
                                ? statusCountMap.getValue() : 0l);
                bpaApplicationReport.setServiceType08(
                        ST_CODE_08.equalsIgnoreCase(statusCountMap.getKey())
                                ? statusCountMap.getValue() : 0l);
                bpaApplicationReport
                        .setServiceType09(ST_CODE_09.equalsIgnoreCase(statusCountMap.getKey())
                                ? statusCountMap.getValue() : 0l);
            }
            searchBpaApplicationReportList.add(bpaApplicationReport);
        }
        return searchBpaApplicationReportList;
    }

}
