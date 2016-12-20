<%--
  ~ eGov suite of products aim to improve the internal efficiency,transparency,
  ~    accountability and the service delivery of the government  organizations.
  ~
  ~     Copyright (C) <2015>  eGovernments Foundation
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
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${revisionEstimate.changeQuantityLSActivities.size() != 0}">
	<div class="panel panel-primary" data-collapsed="0">
		<div class="panel-heading">
			<div class="panel-title">
				<spring:message code="title.lumpsum" />
			</div>
		</div>
		<div class="panel-body">
			
			<table class="table table-bordered" >
				<thead>
					<tr>
					<th><spring:message code="lbl.slNo" /></th>
						<th><spring:message code="lbl.description" /></th>
						<th><spring:message code="lbl.uom" /></th>
						<th><spring:message code="lbl.rate" /></th>
						<th><spring:message code="lbl.estimatedquantity" /></th>
						<th><spring:message code="lbl.estimatedamount" /></th>
						<c:if test="${isServiceVATRequired == true }">
							<th><spring:message code="lbl.service.vat" /></th>
							<th><spring:message code="lbl.service.vat.amount" /></th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${revisionEstimate.changeQuantityLSActivities.size() != 0}">
							<c:forEach items="${revisionEstimate.changeQuantityLSActivities}" var="nonSorDtls" varStatus="item">
								<tr>
									<td><span class="spansno"><c:out value="${item.index + 1}" /></span></td>
									<td><c:out value="${nonSorDtls.nonSor.description}"></c:out></td>
								 	<td><c:out value="${nonSorDtls.uom.uom}"></c:out></td>
								 	<td class="text-right"><fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2"><c:out value="${nonSorDtls.estimateRate}"></c:out></fmt:formatNumber></td>
								 	<c:choose>
										<c:when test="${nonSorDtls.quantityChanged == true }">
											<td class="text-right" style="color: blue;">
										</c:when>
										<c:otherwise>
											<td class="text-right">
										</c:otherwise>
									</c:choose>
								 		<c:out value="${nonSorDtls.quantity}">
								 	</c:out>
								 	<c:if test="${nonSorDtls.measurementSheetList.size() > 0 }">
								 	 <button class="btn btn-default openmsheet" name="lumpSumNONSORActivities[${item.index}].msadd" id="lumpSumNONSORActivities[${item.index}].msadd" data-idx="0" onclick="addMSheet(this);return false;"><i  class="fa fa-plus-circle" aria-hidden="true"></i></button>
								 	</c:if>
								 	</td>
								 		<%@ include file="../measurementsheet/nontenderednonsor-measurementsheet-formtableview.jsp"%>  
								 	<td class="text-right"><fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2"><c:out value="${nonSorDtls.getAmount().value}" /></fmt:formatNumber></td>
								 	<c:if test="${isServiceVATRequired == true }">
										<td class="text-right"><c:out value="${nonSorDtls.serviceTaxPerc}"></c:out></td>
										<td class="text-right"><fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2">${(nonSorDtls.getAmount().value) * (nonSorDtls.serviceTaxPerc / 100) }</fmt:formatNumber></td>
									</c:if>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose> 
				</tbody>
				<tfoot>
					<c:set var="nonsortotal" value="${0}" scope="session" />
					<c:if test="${revisionEstimate.changeQuantityLSActivities != null}">
						<c:forEach items="${revisionEstimate.changeQuantityLSActivities}" var="nonSor">
							<c:set var="nonsortotal" value="${nonsortotal + nonSor.getAmount().value }" />
						</c:forEach>
					</c:if>
					<tr>
					<c:if test="${isServiceVATRequired == true }">
						<td colspan="7" class="text-right"><spring:message code="lbl.total" /></td>
					</c:if>
					<c:if test="${isServiceVATRequired == false }">
						<td colspan="5" class="text-right"><spring:message code="lbl.total" /></td>
					</c:if>
						<td class="text-right">
							<span><fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2"><c:out value="${nonsortotal}" /></fmt:formatNumber></span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</c:if>