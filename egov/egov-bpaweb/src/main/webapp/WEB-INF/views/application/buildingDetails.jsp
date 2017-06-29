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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="panel-heading toggle-header custom_form_panel_heading">
	<div class="panel-title">
		<spring:message code="lbl.build.details" />
	</div>
	<div class="history-icon toggle-icon">
		<i class="fa fa-angle-up fa-2x"></i>
	</div>
</div>
<div class="panel-body display-hide">
	<div class="form-group">
		<label
			class="col-sm-3 control-label text-right handle-mandatory show-hide totalPlintArea"><spring:message
				code="lbl.build.total.plinth" /><span class="mandatory"></span> </label> <label
			class="col-sm-3 control-label text-right handle-mandatory show-hide noofhutorshed"><spring:message
				code="lbl.area.hut.shed" /> <span class="mandatory"></span> </label> <label
			class="col-sm-3 control-label text-right handle-mandatory show-hide alterationInArea"><spring:message
				code="lbl.alteration.area" /> <span class="mandatory"></span> </label> <label
			class="col-sm-3 control-label text-right handle-mandatory show-hide additionInArea"><spring:message
				code="lbl.extension.area" /> <span class="mandatory"></span> </label> <label
			class="col-sm-3 control-label text-right handle-mandatory show-hide changeInOccupancyArea"><spring:message
				code="lbl.change.occupancy.area" /> <span class="mandatory"></span>
		</label>
		<div class="col-sm-3 add-margin">
			<form:input
				class="form-control patternvalidation handle-mandatory clear-values"
				maxlength="10" data-pattern="decimalvalue" id="totalPlintArea"
				path="buildingDetail[0].totalPlintArea" required="required" />
			<form:errors path="buildingDetail[0].totalPlintArea"
				cssClass="add-margin error-msg" />
		</div>
		<label
			class="col-sm-2 control-label text-right handle-mandatory floorCount"><spring:message
				code="lbl.floor.count" /><span class="mandatory"></span></label>
		<div class="col-sm-3 add-margin">
			<form:input
				class="form-control patternvalidation clear-values handle-mandatory floorCount"
				data-pattern="number" maxlength="5" id="floorCount"
				path="buildingDetail[0].floorCount" required="required" />
			<form:errors path="buildingDetail[0].floorCount"
				cssClass="add-margin error-msg" />
		</div>
		<%-- <label
		class="col-sm-2 control-label text-right handle-mandatory buildingheightGround"><spring:message
			code="lbl.build.height" /> <span class="mandatory"></span> </label>
	<div class="col-sm-3 add-margin">
		<form:input
			class="form-control patternvalidation clear-values handle-mandatory buildingheightGround"
			maxlength="10" data-pattern="decimalvalue" id="buildingheightGround"
			path="buildingDetail[0].buildingheightGround" required="required" />
		<form:errors path="buildingDetail[0].buildingheightGround"
			cssClass="add-margin error-msg" />
	</div> --%>
	</div>
	<%-- <div class="form-group">
	
	<label class="col-sm-2 control-label text-right proposedfloorArea"><spring:message
			code="lbl.build.propsed.floor.area" /> </label>
	<div class="col-sm-3 add-margin">
		<form:input
			class="form-control patternvalidation clear-values proposedfloorArea"
			maxlength="10" data-pattern="decimalvalue"
			id="buildingDetail[0].proposedfloorArea"
			path="buildingDetail[0].proposedfloorArea" />
		<form:errors path="buildingDetail[0].proposedfloorArea"
			cssClass="add-margin error-msg" />
	</div>
</div> --%>
	<div class="form-group">
		<label
			class="col-sm-3 control-label text-right handle-mandatory heightFromGroundWithStairRoom"><spring:message
				code="lbl.grnd.with.stair" /> <span class="mandatory"></span> </label>
		<div class="col-sm-3 add-margin">
			<form:input
				class="form-control patternvalidation clear-values  handle-mandatory heightFromGroundWithStairRoom"
				maxlength="10" data-pattern="decimalvalue"
				id="heightFromGroundWithStairRoom"
				path="buildingDetail[0].heightFromGroundWithStairRoom"
				required="required" />
			<form:errors path="buildingDetail[0].heightFromGroundWithStairRoom"
				cssClass="add-margin error-msg" />
		</div>
		<label
			class="col-sm-2 control-label text-right handle-mandatory heightFromGroundWithOutStairRoom"><spring:message
				code="lbl.grnd.wo.stair" /> <span class="mandatory"></span> </label>
		<div class="col-sm-3 add-margin">
			<form:input
				class="form-control patternvalidation clear-values  handle-mandatory heightFromGroundWithOutStairRoom"
				maxlength="10" data-pattern="decimalvalue"
				id="heightFromGroundWithOutStairRoom"
				path="buildingDetail[0].heightFromGroundWithOutStairRoom"
				required="required" />
			<form:errors
				path="buildingDetail[0].heightFromGroundWithOutStairRoom"
				cssClass="add-margin error-msg" />
		</div>
	</div>
	<div class="form-group">
		<label
			class="col-sm-3 control-label text-right handle-mandatory fromStreetLevelWithStairRoom"><spring:message
				code="lbl.street.with.stair" /> <span class="mandatory"></span> </label>
		<div class="col-sm-3 add-margin">
			<form:input
				class="form-control patternvalidation handle-mandatory clear-values fromStreetLevelWithStairRoom"
				maxlength="10" data-pattern="decimalvalue"
				id="fromStreetLevelWithStairRoom"
				path="buildingDetail[0].fromStreetLevelWithStairRoom"
				required="required" />
			<form:errors path="buildingDetail[0].fromStreetLevelWithStairRoom"
				cssClass="add-margin error-msg" />
		</div>
		<label
			class="col-sm-2 control-label text-right handle-mandatory fromStreetLevelWithOutStairRoom"><spring:message
				code="lbl.street.wo.stair" /> <span class="mandatory"></span> </label>
		<div class="col-sm-3 add-margin">
			<form:input
				class="form-control patternvalidation handle-mandatory clear-values fromStreetLevelWithOutStairRoom"
				maxlength="10" data-pattern="decimalvalue"
				id="fromStreetLevelWithOutStairRoom"
				path="buildingDetail[0].fromStreetLevelWithOutStairRoom"
				required="required" />
			<form:errors path="buildingDetail[0].fromStreetLevelWithOutStairRoom"
				cssClass="add-margin error-msg" />
		</div>
	</div>

	<%-- <div class="form-group">
	<label
		class="col-sm-3 control-label text-right handle-mandatory machineRoom"><spring:message
			code="lbl.machine.room" /> <span class="mandatory"></span> </label>
	<div class="col-sm-3 add-margin">
		<form:input
			class="form-control patternvalidation clear-values handle-mandatory machineRoom"
			maxlength="10" data-pattern="decimalvalue" id="machineRoom"
			path="buildingDetail[0].machineRoom" required="required" />
		<form:errors path="buildingDetail[0].machineRoom"
			cssClass="add-margin error-msg" />
	</div>
</div> --%>


	<div class="panel-title header-color">
		<spring:message code="lbl.plint.carpet.details" />
	</div>
	<div class="text-right add-padding">
		<button type="button" class="btn btn-sm btn-primary"
			id="addBuildAreaRow">ADD ROW</button>
	</div>
	<input type="hidden" id="buildingFloorList"
		value="${buildingFloorList}"> <input type="hidden"
		id="occupancyList" value=""> <input type="hidden"
		id="sumOfPlinthArea" value="">
	<table class="table table-striped table-bordered"
		id="buildingAreaDetails">
		<thead>
			<tr>
				<th class="text-center"><spring:message code="lbl.srl.no" /></th>
				<th class="text-center"><spring:message code="lbl.floor.name" /></th>
				<th class="text-center"><spring:message code="lbl.plinth.area" /></th>
				<th class="text-center"><spring:message code="lbl.carpet.area" /></th>
				<th class="text-center"><spring:message code="lbl.action" /></th>
			</tr>
		</thead>
		<tbody
			data-existing-len="${fn:length(bpaApplication.buildingDetail[0].applicationFloorDetails)}">
			<c:choose>
				<c:when
					test="${!bpaApplication.buildingDetail[0].applicationFloorDetails.isEmpty()}">
					<c:forEach
						items="${bpaApplication.buildingDetail[0].applicationFloorDetails}"
						var="buildingAreaDetails" varStatus="counter">
						<form:hidden id="table_fieldInspections${counter.index}"
							path="buildingDetail[0].applicationFloorDetails[${counter.index}].id" />
						<tr class="data-fetched">
							<td class="text-center"><span class="serialNo" id="slNoInsp">${counter.index+1}</span></td>
							<td><form:input type="text"
									class="form-control table-input patternvalidation"
									path="buildingDetail[0].applicationFloorDetails[${counter.index}].floorDescription"
									id="applicationFloorDetails[${counter.index}]floorDescription"
									maxlength="128" value="${buildingAreaDetails.floorDescription}" /></td>
							<td class="text-right"><form:input type="text"
									class="form-control table-input patternvalidation plinthArea text-right"
									data-pattern="number"
									path="buildingDetail[0].applicationFloorDetails[${counter.index}].plinthArea"
									id="applicationFloorDetails${counter.index}plinthArea"
									maxlength="15" value="${buildingAreaDetails.plinthArea}" /></td>
							<td class="text-right"><form:input type="text"
									class="form-control table-input text-right patternvalidation carpetArea"
									data-pattern="number"
									path="buildingDetail[0].applicationFloorDetails[${counter.index}].carpetArea"
									id="applicationFloorDetails${counter.index}carpetArea"
									maxlength="8" value="${buildingAreaDetails.carpetArea}" /></td>
							<%-- <c:if test="${counter.index!=0}">
							<td class="text-center"><a href="javascript:void(0);"
								class="btn-sm btn-danger" id="deleteBuildAreaRow"
								data-record-id="${var1.id}"><i class="fa fa-trash"></i></a></td>
						</c:if> --%>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="data-fetched">
						<td class="text-center"><span class="serialNo" id="slNoInsp">1</span></td>
						<td><form:select
								path="buildingDetail[0].applicationFloorDetails[0].floorDescription"
								data-first-option="false"
								id="applicationFloorDetails[0]floorDescription"
								cssClass="form-control" maxlength="128">
								<form:option value="">
									<spring:message code="lbl.select" />
								</form:option>
								<form:options items="${buildingFloorList}" />
							</form:select></td>
						<td class="text-right"><form:input type="text"
								class="form-control table-input text-right patternvalidation plinthArea"
								data-pattern="number"
								path="buildingDetail[0].applicationFloorDetails[0].plinthArea"
								id="applicationFloorDetails0plinthArea" maxlength="15" value=""
								onblur="validateFloorDetails(this)" /></td>
						<td class="text-right"><form:input type="text"
								class="form-control table-input text-right patternvalidation carpetArea"
								data-pattern="number"
								path="buildingDetail[0].applicationFloorDetails[0].carpetArea"
								id="applicationFloorDetails0carpetArea" maxlength="15" value="" /></td>
						<td class="text-center"><a href="javascript:void(0);"
							class="btn-sm btn-danger" id="deleteBuildAreaRow"
							data-record-id="${buildingAreaDetails.id}"><i
								class="fa fa-trash"></i></a></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td class="text-right">Total</td>
				<td class="text-right"></td>
				<td class="text-right"></td>
				<td></td>
			</tr>
		</tfoot>
	</table>
</div>
