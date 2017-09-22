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
package org.egov.bpa.web.controller.adaptor;

import java.lang.reflect.Type;

import org.apache.commons.lang.StringUtils;
import org.egov.bpa.transaction.entity.dto.SearchBpaApplicationReport;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SearchBpaApplicationReportAdaptor implements JsonSerializer<SearchBpaApplicationReport> {
    @Override
    public JsonElement serialize(final SearchBpaApplicationReport reportResultFormObj, final Type type,
            final JsonSerializationContext jsc) {
        final JsonObject jsonObject = new JsonObject();
        if (reportResultFormObj != null) {
            jsonObject.addProperty("applicationNumber", reportResultFormObj.getApplicationNumber());
            jsonObject.addProperty("status", reportResultFormObj.getStatus());
            jsonObject.addProperty("serviceType",
                    StringUtils.defaultString(reportResultFormObj.getServiceType()));
            jsonObject.addProperty("serviceType01",
                    reportResultFormObj.getServiceType01() != null ? reportResultFormObj.getServiceType01() : 0l);
            jsonObject.addProperty("serviceType02",
                    reportResultFormObj.getServiceType02() != null ? reportResultFormObj.getServiceType02() : 0l);
            jsonObject.addProperty("serviceType03",
                    reportResultFormObj.getServiceType03() != null ? reportResultFormObj.getServiceType03() : 0l);
            jsonObject.addProperty("serviceType04",
                    reportResultFormObj.getServiceType04() != null ? reportResultFormObj.getServiceType04() : 0l);
            jsonObject.addProperty("serviceType05",
                    reportResultFormObj.getServiceType05() != null ? reportResultFormObj.getServiceType05() : 0l);
            jsonObject.addProperty("serviceType06",
                    reportResultFormObj.getServiceType06() != null ? reportResultFormObj.getServiceType06() : 0l);
            jsonObject.addProperty("serviceType07",
                    reportResultFormObj.getServiceType07() != null ? reportResultFormObj.getServiceType07() : 0l);
            jsonObject.addProperty("serviceType08",
                    reportResultFormObj.getServiceType08() != null ? reportResultFormObj.getServiceType08() : 0l);
            jsonObject.addProperty("serviceType09",
                    reportResultFormObj.getServiceType09() != null ? reportResultFormObj.getServiceType09() : 0l);
            jsonObject.addProperty("serviceType14",
                    reportResultFormObj.getServiceType14() != null ? reportResultFormObj.getServiceType14() : 0l);
            jsonObject.addProperty("serviceType15",
                    reportResultFormObj.getServiceType15() != null ? reportResultFormObj.getServiceType15() : 0l);
            jsonObject.addProperty("zone1", reportResultFormObj.getZone1() != null ? reportResultFormObj.getZone1() : 0l);
            jsonObject.addProperty("zone2", reportResultFormObj.getZone2() != null ? reportResultFormObj.getZone2() : 0l);
            jsonObject.addProperty("zone3", reportResultFormObj.getZone3() != null ? reportResultFormObj.getZone3() : 0l);
            jsonObject.addProperty("zone4", reportResultFormObj.getZone4() != null ? reportResultFormObj.getZone4() : 0l);
            jsonObject.addProperty("id", reportResultFormObj.getId());
        }
        return jsonObject;
    }
}