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
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/taglib/cdn.tld" prefix="cdn"%>
<div align="center" class="overflow-x-scroll">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="tablebottom" id="vacantLandTable">
		<tr>
			<th class="bluebgheadtd"><spring:message code="lbl.slno" /></th>
			<th class="bluebgheadtd"><spring:message code="lbl.lp.no" /></th>
			<th class="bluebgheadtd"><spring:message code="lbl.lp.date" /></th>
			<th class="bluebgheadtd"><spring:message
					code="lbl.lp.description" /></th>
			<th class="bluebgheadtd"><spring:message code="lbl.lp.reason" /></th>
			<th class="bluebgheadtd"><spring:message code="lbl.checklist" /></th>
			<th class="bluebgheadtd"><spring:message code="lbl.modify" /></th>
			<th class="bluebgheadtd"><spring:message code="lbl.print" /></th>
			<th class="bluebgheadtd"><spring:message code="lbl.lp.sentdate" /></th>
			<th class="bluebgheadtd"><spring:message
					code="lbl.lp.reply.checklist" /></th>

		</tr>
		<c:choose>
			<c:when test="${not empty lettertopartylist}">
				<c:forEach items="${lettertopartylist}" var="lp" varStatus="status">
					<tr id="lprow">
						<td class="blueborderfortd" align="center">${status.index+1}</td>
						<td class="blueborderfortd" align="center"><span class="bold"><c:out
									value="${lp.lpNumber}" /></span></td>
						<td class="blueborderfortd" align="center"><span class="bold"><c:out
									value="${lp.letterDate}" /></span></td>
						<td class="blueborderfortd" align="center"><span class="bold"><c:out
									value="${lp.lpDesc}" /></span></td>
						<td class="blueborderfortd" align="center"><span class="bold"><c:out
									value="${lp.lpReason.description}" /></span></td>
						<td class="blueborderfortd" align="center"><a
							onclick="window.open('/bpa/lettertoparty/viewlpchecklist/${lp.id}','print','width=600, height=400,scrollbars=yes')">
								<i class="fa fa-file-o" aria-hidden="true"> <spring:message
										code="lbl.lp.checklist" />
							</i>
						</a></td>
						<td class="blueborderfortd" align="center"><c:if
								test="${status.index == 0 }">
								<c:if test="${mode eq 'modifylettertoparty'}">
									<a
										href="/bpa/lettertoparty/update/${bpaApplication.applicationNumber}">
										<i class="fa fa-pencil" aria-hidden="true"></i> Modify
									</a>
								</c:if>
							</c:if></td>
						<td class="blueborderfortd" align="center"><a
							onclick="window.open('/bpa/lettertoparty/viewlpreplychecklist/${lp.id}','print','width=600, height=400,scrollbars=yes')">
								<i class="fa fa-file-o" aria-hidden="true"> <spring:message
										code="lbl.lp.reply.checklist" /></i>
						</a></td>
						<td class="blueborderfortd" align="center"><a
							href="/bpa/lettertoparty/lettertopartyprint?pathVar=${lp.id}">
								<i class="fa fa-print" aria-hidden="true"></i> <spring:message
									code="lbl.print" />
						</a></td>
						<td class="blueborderfortd" align="center"><span class="bold"><c:out
									value="${lp.sentDate}" /></span></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="col-md-12 col-xs-6  panel-title">
					<spring:message code="lbl.no.lp" />
				</div>
			</c:otherwise>
		</c:choose>

	</table>
</div>
<script
	src="<cdn:url value='/resources/js/app/lettertoparty.js?rnd=${app_release_no}'/> "></script>

