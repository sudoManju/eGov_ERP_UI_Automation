<%--
  ~ eGov suite of products aim to improve the internal efficiency,transparency,
  ~    accountability and the service delivery of the government  organizations.
  ~
  ~     Copyright (C) <2017>  eGovernments Foundation
  ~
  ~     The updated version of eGov suite of products as by eGovernments Foundation
  ~     is available at http://www.egovernments.org
  ~
  ~     This program is free software: you can redistribute it and/or modify
  ~     it under the terms of the GNU General Public License as published by
  ~     the Free Software Foundation, either version 3 of the License, or
  ~     any later version.
  ~
  ~     This program is distributed in the hope that it will be useful,
  ~     but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~     GNU General Public License for more details.
  ~
  ~     You should have received a copy of the GNU General Public License
  ~     along with this program. If not, see http://www.gnu.org/licenses/ or
  ~     http://www.gnu.org/licenses/gpl.html .
  ~
  ~     In addition to the terms of the GPL license to be adhered to in using this
  ~     program, the following additional terms are to be complied with:
  ~
  ~         1) All versions of this program, verbatim or modified must carry this
  ~            Legal Notice.
  ~
  ~         2) Any misrepresentation of the origin of the material is prohibited. It
  ~            is required that all modified versions of this material be marked in
  ~            reasonable ways as different from the original version.
  ~
  ~         3) This license does not grant any rights to any user of the program
  ~            with regards to rights under trademark law for use of the trade names
  ~            or trademarks of eGovernments Foundation.
  ~
  ~   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
  --%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="panel-heading">
	<div class="panel-title">Status of NOC from the Following
		Departments.</div>
</div>

<div class="panel-body custom">
	<table class="table table-bordered  multiheadertbl"
		name="bpaupdatenocdetails" id=bpaupdatenocdetails>
		<thead>
			<th><spring:message code="lbl.srl.no" /></th>
			<th><spring:message code="lbl.department" /></th>
			<th><spring:message code="lbl.nature.noc.req" /></th>
			<th><spring:message code="lbl.letr.sent.on" /></th>
			<th><spring:message code="lbl.reply.recv.on" /></th>
			<th><spring:message code="lbl.noc.reject" /></th>
			<th><spring:message code="lbl.noc.not.aplicable" /></th>
			<th width="8%"><spring:message code="lbl.obtained" /></th>
			<th><spring:message code="lbl.remarks" /></th>
			<th><spring:message code="lbl.attachdocument" /></th>
		</thead>
		<tbody>

			<c:forEach var="nocdoc"
				items="${bpaApplication.applicationNOCDocument}" varStatus="status">
				<tr>
					<td><c:out value="${status.index+1}"></c:out></td>
					<td><c:out value="${nocdoc.checklist.description}"></c:out></td>
					<td><c:out value="${nocdoc.natureOfRequest}"></c:out></td>
					<td><c:out value="${nocdoc.letterSentOn}"></c:out></td>
					<td><c:out value="${nocdoc.replyReceivedOn}"></c:out></td>
					<td class="text-center"><c:out value="${nocdoc.rejection ? 'YES' : 'NO'}"
							default="N/A"></c:out></td>
					<td class="text-center"><c:out value="${nocdoc.notApplicable ? 'YES' : 'NO'}"
							default="N/A"></c:out></td>
					<td class="text-center"><c:out value="${nocdoc.issubmitted ? 'YES' : 'NO'}"
							default="N/A"></c:out></td>
					<td><c:out value="${nocdoc.remarks}" default="N/A"></c:out></td>
					<td>
						<a
								href="/bpa/application/downloadfile/${nocdoc.nocFileStore.fileStoreId}"
								data-gallery>
								${nocdoc.nocFileStore.fileName}
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
