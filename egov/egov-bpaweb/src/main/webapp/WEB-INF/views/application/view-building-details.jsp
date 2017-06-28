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

<%@page import="org.python.modules.jarray"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglib/cdn.tld" prefix="cdn"%>

<div class="panel-heading toggle-header custom_form_panel_heading">
	<div class="panel-title">
		<spring:message code="lbl.build.details" />
	</div>
	<div class="history-icon toggle-icon">
			<i class="fa fa-angle-up fa-2x"></i>
		</div>
</div>

<div class="panel-body display-hide">
	<div class="row add-border">
		<div class="col-sm-3 add-margin show-hide totalPlintArea">
			<spring:message code="lbl.build.total.plinth" />
		</div>
		<div class="col-sm-3 add-margin show-hide noofhutorshed">
			<spring:message code="lbl.area.hut.shed" />
		</div>
		<div class="col-sm-3 add-margin show-hide alterationInArea">
			<spring:message code="lbl.alteration.area" />
		</div>
		<div class="col-sm-3 add-margin show-hide additionInArea">
			<spring:message code="lbl.extension.area" />
		</div>
		<div class="col-sm-3 add-margin show-hide changeInOccupancyArea">
			<spring:message code="lbl.change.occupancy.area" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out value="${bpaApplication.buildingDetail[0].totalPlintArea}"
				default="N/A"></c:out>
		</div>
		<div class="col-sm-3 add-margin">
			<spring:message code="lbl.floor.count" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out value="${bpaApplication.buildingDetail[0].floorCount}"
				default="N/A"></c:out>
		</div>
	</div>

	<%-- <div class="row add-border">
		<div class="col-sm-3 add-margin">
			<spring:message code="lbl.build.height" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out
				value="${bpaApplication.buildingDetail[0].buildingheightGround}"
				default="N/A"></c:out>
		</div>
		<div class="col-sm-3 add-margin">
			<spring:message code="lbl.build.propsed.floor.area" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out value="${bpaApplication.buildingDetail[0].proposedfloorArea}"
				default="N/A"></c:out>
		</div>
	</div> --%>

	<div class="row add-border">
		<div class="col-sm-3 add-margin">
			<spring:message code="lbl.grnd.with.stair" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out
				value="${bpaApplication.buildingDetail[0].heightFromGroundWithStairRoom}"
				default="N/A"></c:out>
		</div>
		<div class="col-sm-3 add-margin">
			<spring:message code="lbl.grnd.wo.stair" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out
				value="${bpaApplication.buildingDetail[0].heightFromGroundWithOutStairRoom}"
				default="N/A"></c:out>
		</div>
	</div>

	<div class="row add-border">
		<div class="col-sm-3 add-margin">
			<spring:message code="lbl.street.with.stair" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out
				value="${bpaApplication.buildingDetail[0].fromStreetLevelWithStairRoom}"
				default="N/A"></c:out>
		</div>
		<div class="col-sm-3 add-margin">
			<spring:message code="lbl.street.wo.stair" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out
				value="${bpaApplication.buildingDetail[0].fromStreetLevelWithOutStairRoom}"
				default="N/A"></c:out>
		</div>
	</div>

	<div class="row add-border">
		<div class="col-sm-3 add-margin">
			<spring:message code="lbl.machine.room" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out value="${bpaApplication.buildingDetail[0].machineRoom}"
				default="N/A"></c:out>
		</div>
		<div class="col-sm-3 add-margin">
			<spring:message code="lbl.town.plan.zone" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out value="${bpaApplication.buildingDetail[0].townPlanningZone}"
				default="N/A"></c:out>
		</div>
	</div>
	<div class="row add-border">
		<div class="col-sm-3 add-margin">
			<spring:message code="lbl.crz.zone" />
		</div>
		<div class="col-sm-3 add-margin view-content">
			<c:out value="${bpaApplication.buildingDetail[0].crzZone}"
				default="N/A"></c:out>
		</div>
	</div>

	<c:if
		test="${not empty bpaApplication.buildingDetail[0].applicationFloorDetails}">
		<div class="panel-heading custom_form_panel_heading">
			<div class="panel-title">
				<spring:message code="lbl.plint.carpet.details" />
			</div>
		</div>
		<table class="table table-striped table-bordered"
			id="buildingAreaDetails">
			<thead>
				<tr>
					<th class="text-center"><spring:message code="lbl.srl.no" /></th>
					<th class="text-center"><spring:message code="lbl.floor.name" /></th>
					<th class="text-center"><spring:message code="lbl.plinth.area" /></th>
					<th class="text-center"><spring:message code="lbl.carpet.area" /></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="plinthAreaTotal" value="${0}" />
				<c:set var="carpetAreaTotal" value="${0}" />
				<c:forEach
					items="${bpaApplication.buildingDetail[0].applicationFloorDetails}"
					var="floorDetails" varStatus="counter">
					<c:set var="plinthAreaTotal"
						value="${plinthAreaTotal + floorDetails.plinthArea}" />
					<c:set var="carpetAreaTotal"
						value="${carpetAreaTotal + floorDetails.carpetArea}" />
					<input type="hidden" value="${floorDetails.id}"
						id="table_fieldInspections${counter.index}"
						name="bpaApplication.buildingDetail[0].applicationFloorDetails[${counter.index}].id" />
					<tr class="data-fetched">
						<td class="text-center"><span class="serialNo" id="slNoInsp">${counter.index+1}</span></td>
						<td class="text-center"><c:out
								value="${floorDetails.floorDescription}" default="N/A"></c:out>
						<td class="text-center"><c:out
								value="${floorDetails.plinthArea}" default="N/A"></c:out>
						<td class="text-center"><c:out
								value="${floorDetails.carpetArea}" default="N/A"></c:out>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td></td>
					<td class="text-right">Total</td>
					<td class="text-center"><c:out value="${plinthAreaTotal}"
							default="0"></c:out></td>
					<td class="text-center"><c:out value="${carpetAreaTotal}"
							default="0"></c:out></td>
				</tr>
			</tfoot>
		</table>
	</c:if>
</div>
