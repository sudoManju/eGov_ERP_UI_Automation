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
<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		<spring:message code="lbl.inspection.appln" />
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Location of
		the plot<span
		class="mandatory"></span></label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation"
			data-pattern="alphanumericwithspace" maxlength="128" id="locationOfPlot"
			path="docket[0].locationOfPlot" />
		<form:errors path="docket[0].locationOfPlot" required="required"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Land Area</label>
	<div class="col-sm-2 add-margin">
		<form:input class="form-control patternvalidation"
			data-pattern="number"  id="lndRegularizationArea"
			path="lndRegularizationArea" />
		<form:errors path="lndRegularizationArea" 
			cssClass="add-margin error-msg" />
	</div>
	</div>
	<div class="form-group">
	<label class="col-sm-3 control-label text-right">Building Area</label>
	<div class="col-sm-3 add-margin">
		<form:input  class="form-control patternvalidation" data-pattern="number"
		  id="bldngBuildUpArea"
			path="bldngBuildUpArea" />
		<form:errors path="bldngBuildUpArea" required="required"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Compound Wall</label>
	<div class="col-sm-2 add-margin">
		<form:input  class="form-control patternvalidation" data-pattern="number"
		 id="bldngCompoundWall"
			path="bldngCompoundWall" />
		<form:errors path="bldngCompoundWall" 
			cssClass="add-margin error-msg" />
	</div>
	</div>
	<div class="form-group">
	<label class="col-sm-3 control-label text-right">No of Wells</label>
	<div class="col-sm-3 add-margin">
		<form:input  class="form-control patternvalidation" data-pattern="number"
		  id="bldngwellohtsumptankarea"
			path="bldngwellohtsumptankarea" />
		<form:errors path="bldngwellohtsumptankarea"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Occupancy></label>
		<div class="col-sm-3 add-margin">
	<form:select path="landUsage"
			data-first-option="false" id="" cssClass="form-control"
			>
			<form:option value="">
				<spring:message code="lbl.select" />
			</form:option>
			<form:options items="${occupancyList}" itemValue="id"
				itemLabel="code" />
		</form:select>
		<form:errors path="landUsage"
			cssClass="add-margin error-msg" />
	</div>
	</div>
	
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Extent in Sqmts<span
		class="mandatory"></span></label>
	<div class="col-sm-3 add-margin">
		<form:input  class="form-control patternvalidation" data-pattern="number"
		  id="lndMinPlotExtent" required="required"
			path="lndMinPlotExtent" />
		<form:errors path="lndMinPlotExtent" 
			cssClass="add-margin error-msg" />
	</div>
	</div>
<c:choose>
	<c:when test="${!docketDetailLocList.isEmpty()}">
	<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		Location of the Plot
	</div>
</div>
		<div class="form-group view-content header-color hidden-xs">
			<div class="col-sm-5 text-center">Documents</div>
			<div class="col-sm-3 text-center">Provided</div>
			<div class="col-sm-3 text-center">
				<spring:message code="lbl.remarks" />
			</div>
		</div>
		<c:forEach var="docs" items="${docketDetailLocList}" varStatus="status">
			<div class="form-group">
				<div class="col-sm-5 add-margin check-text text-center">

					<c:out value="${docs.checkListDetail.description}" />


					<form:hidden
						id="docketDetailLocList${status.index}checkListDetail.id"
						path="docketDetailLocList[${status.index}].checkListDetail.id"
						value="${docs.checkListDetail.id}" />

					<form:hidden
						id="docketDetailLocList${status.index}checkListDetail.description"
						path="docketDetailLocList[${status.index}].checkListDetail.description"
						value="${docs.checkListDetail.description}" />
				</div>

				<div class="col-sm-3 add-margin">
				 <c:choose>
				<c:when  test="${mode =='editinsp'}">
				<form:radiobutton
						path="docketDetailLocList[${status.index}].value" value="true"  />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailLocList[${status.index}].value" value="false"
						/>
					<spring:message code="lbl.no" />
					<form:errors path="docketDetailLocList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:when>
				<c:otherwise>
				<form:radiobutton
						path="docketDetailLocList[${status.index}].value" value="true" />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailLocList[${status.index}].value" value="false"
						checked="checked" />
						<spring:message code="lbl.no" />
						<form:errors path="docketDetailLocList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:otherwise>
				</c:choose> 
				</div>

				<div class="col-sm-3 add-margin text-center">
					<form:textarea class="form-control patternvalidation"
						data-pattern="alphanumericwithspace" maxlength="256"
						id="docketDetailLocList${status.index}remarks"
						path="docketDetailLocList[${status.index}].remarks"
						 />

					<form:errors path="docketDetailLocList[${status.index}].remarks"
						cssClass="add-margin error-msg" />
				</div>
			</div>
		</c:forEach>
	</c:when>
</c:choose>



<c:choose>
	<c:when test="${!docketDetailMeasumentList.isEmpty()}">
	<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		Measurement of the PlotCC
	</div>
</div>
		<div class="form-group view-content header-color hidden-xs">
			<div class="col-sm-5 text-center">Documents</div>
			<div class="col-sm-3 text-center">Provided</div>
			<div class="col-sm-3 text-center">
				<spring:message code="lbl.remarks" />
			</div>
		</div>
		<c:forEach var="docs" items="${docketDetailMeasumentList}" varStatus="status">
			<div class="form-group">
				<div class="col-sm-5 add-margin check-text text-center">

					<c:out value="${docs.checkListDetail.description}" />


					<form:hidden
						id="docketDetailMeasumentList${status.index}checkListDetail.id"
						path="docketDetailMeasumentList[${status.index}].checkListDetail.id"
						value="${docs.checkListDetail.id}" />

					<form:hidden
						id="docketDetailMeasumentList${status.index}checkListDetail.description"
						path="docketDetailMeasumentList[${status.index}].checkListDetail.description"
						value="${docs.checkListDetail.description}" />
				</div>

				<div class="col-sm-3 add-margin">
				 <c:choose>
				<c:when  test="${mode =='editinsp'}">
				<form:radiobutton
						path="docketDetailMeasumentList[${status.index}].value" value="true"  />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailMeasumentList[${status.index}].value" value="false"
						/>
					<spring:message code="lbl.no" />
					<form:errors path="docketDetailMeasumentList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:when>
				<c:otherwise>
				<form:radiobutton
						path="docketDetailMeasumentList[${status.index}].value" value="true" />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailMeasumentList[${status.index}].value" value="false"
						checked="checked" />
						<spring:message code="lbl.no" />
						<form:errors path="docketDetailMeasumentList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:otherwise>
				</c:choose> 
					
				</div>

				<div class="col-sm-3 add-margin text-center">
					<form:textarea class="form-control patternvalidation"
						data-pattern="alphanumericwithspace" maxlength="256"
						id="docketDetailMeasumentList${status.index}remarks"
						path="docketDetailMeasumentList[${status.index}].remarks"
						 />

					<form:errors path="docketDetailMeasumentList[${status.index}].remarks"
						cssClass="add-margin error-msg" />
				</div>
			</div>
		</c:forEach>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${!docketDetailAccessList.isEmpty()}">
	<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		Access To Plot
	</div>
</div>
		<div class="form-group view-content header-color hidden-xs">
			<div class="col-sm-5 text-center">Documents</div>
			<div class="col-sm-3 text-center">Provided</div>
			<div class="col-sm-3 text-center">
				<spring:message code="lbl.remarks" />
			</div>
		</div>
		<c:forEach var="docs" items="${docketDetailAccessList}" varStatus="status">
			<div class="form-group">
				<div class="col-sm-5 add-margin check-text text-center">

					<c:out value="${docs.checkListDetail.description}" />


					<form:hidden
						id="docketDetailAccessList${status.index}checkListDetail.id"
						path="docketDetailAccessList[${status.index}].checkListDetail.id"
						value="${docs.checkListDetail.id}" />

					<form:hidden
						id="docketDetailAccessList${status.index}checkListDetail.description"
						path="docketDetailAccessList[${status.index}].checkListDetail.description"
						value="${docs.checkListDetail.description}" />
				</div>

				<div class="col-sm-3 add-margin">
				 <c:choose>
				<c:when  test="${mode =='editinsp'}">
				<form:radiobutton
						path="docketDetailAccessList[${status.index}].value" value="true"  />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailAccessList[${status.index}].value" value="false"
						/>
					<spring:message code="lbl.no" />
					<form:errors path="docketDetailAccessList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:when>
				<c:otherwise>
				<form:radiobutton
						path="docketDetailAccessList[${status.index}].value" value="true" />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailAccessList[${status.index}].value" value="false"
						checked="checked" />
						<spring:message code="lbl.no" />
						<form:errors path="docketDetailAccessList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:otherwise>
				</c:choose> 
				</div>

				<div class="col-sm-3 add-margin text-center">
					<form:textarea class="form-control patternvalidation"
						data-pattern="alphanumericwithspace" maxlength="256"
						id="docketDetailAccessList${status.index}remarks"
						path="docketDetailAccessList[${status.index}].remarks"
						 />

					<form:errors path="docketDetailAccessList[${status.index}].remarks"
						cssClass="add-margin error-msg" />
				</div>
			</div>
		</c:forEach>
	</c:when>
</c:choose>


<c:choose>
	<c:when test="${!docketDetlSurroundingPlotList.isEmpty()}">
	<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		Required details surrounding the plot	
	</div>
</div>
		<div class="form-group view-content header-color hidden-xs">
			<div class="col-sm-5 text-center">Documents</div>
			<div class="col-sm-3 text-center">Provided</div>
			<div class="col-sm-3 text-center">
				<spring:message code="lbl.remarks" />
			</div>
		</div>
		<c:forEach var="docs" items="${docketDetlSurroundingPlotList}" varStatus="status">
			<div class="form-group">
				<div class="col-sm-5 add-margin check-text text-center">

					<c:out value="${docs.checkListDetail.description}" />


					<form:hidden
						id="docketDetlSurroundingPlotList${status.index}checkListDetail.id"
						path="docketDetlSurroundingPlotList[${status.index}].checkListDetail.id"
						value="${docs.checkListDetail.id}" />

					<form:hidden
						id="docketDetlSurroundingPlotList${status.index}checkListDetail.description"
						path="docketDetlSurroundingPlotList[${status.index}].checkListDetail.description"
						value="${docs.checkListDetail.description}" />
				</div>

				<div class="col-sm-3 add-margin">
				 <c:choose>
				<c:when  test="${mode =='editinsp'}">
				<form:radiobutton
						path="docketDetlSurroundingPlotList[${status.index}].value" value="true"  />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetlSurroundingPlotList[${status.index}].value" value="false"
						/>
					<spring:message code="lbl.no" />
					<form:errors path="docketDetlSurroundingPlotList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:when>
				<c:otherwise>
				<form:radiobutton
						path="docketDetlSurroundingPlotList[${status.index}].value" value="true" />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetlSurroundingPlotList[${status.index}].value" value="false"
						checked="checked" />
						<spring:message code="lbl.no" />
						<form:errors path="docketDetlSurroundingPlotList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:otherwise>
				</c:choose> 
				</div>

				<div class="col-sm-3 add-margin text-center">
					<form:textarea class="form-control patternvalidation"
						data-pattern="alphanumericwithspace" maxlength="256"
						id="docketDetlSurroundingPlotList${status.index}remarks"
						path="docketDetlSurroundingPlotList[${status.index}].remarks"
						 />

					<form:errors path="docketDetlSurroundingPlotList[${status.index}].remarks"
						cssClass="add-margin error-msg" />
				</div>
			</div>
		</c:forEach>
	</c:when>
</c:choose>


<c:choose>
	<c:when test="${!docketDetailLandTypeList.isEmpty()}">
	<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		Type of land	
	</div>
</div>
		<div class="form-group view-content header-color hidden-xs">
			<div class="col-sm-5 text-center">Documents</div>
			<div class="col-sm-3 text-center">Provided</div>
			<div class="col-sm-3 text-center">
				<spring:message code="lbl.remarks" />
			</div>
		</div>
		<c:forEach var="docs" items="${docketDetailLandTypeList}" varStatus="status">
			<div class="form-group">
				<div class="col-sm-5 add-margin check-text text-center">

					<c:out value="${docs.checkListDetail.description}" />


					<form:hidden
						id="docketDetailLandTypeList${status.index}checkListDetail.id"
						path="docketDetailLandTypeList[${status.index}].checkListDetail.id"
						value="${docs.checkListDetail.id}" />

					<form:hidden
						id="docketDetailLandTypeList${status.index}checkListDetail.description"
						path="docketDetailLandTypeList[${status.index}].checkListDetail.description"
						value="${docs.checkListDetail.description}" />
				</div>

				<div class="col-sm-3 add-margin">
				 <c:choose>
				<c:when  test="${mode =='editinsp'}">
				<form:radiobutton
						path="docketDetailLandTypeList[${status.index}].value" value="true"  />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailLandTypeList[${status.index}].value" value="false"
						/>
					<spring:message code="lbl.no" />
					<form:errors path="docketDetailLandTypeList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:when>
				<c:otherwise>
				<form:radiobutton
						path="docketDetailLandTypeList[${status.index}].value" value="true" />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailLandTypeList[${status.index}].value" value="false"
						checked="checked" />
						<spring:message code="lbl.no" />
						<form:errors path="docketDetailLandTypeList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:otherwise>
				</c:choose> 
				</div>

				<div class="col-sm-3 add-margin text-center">
					<form:textarea class="form-control patternvalidation"
						data-pattern="alphanumericwithspace" maxlength="256"
						id="docketDetailLandTypeList${status.index}remarks"
						path="docketDetailLandTypeList[${status.index}].remarks"
						 />

					<form:errors path="docketDetailLandTypeList[${status.index}].remarks"
						cssClass="add-margin error-msg" />
				</div>
			</div>
		</c:forEach>
	</c:when>
</c:choose>


<c:choose>
	<c:when test="${!docketDetailProposedWorkList.isEmpty()}">
	<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		Stage of proposed work	
	</div>
</div>
		<div class="form-group view-content header-color hidden-xs">
			<div class="col-sm-5 text-center">Documents</div>
			<div class="col-sm-3 text-center">Provided</div>
			<div class="col-sm-3 text-center">
				<spring:message code="lbl.remarks" />
			</div>
		</div>
		<c:forEach var="docs" items="${docketDetailProposedWorkList}" varStatus="status">
			<div class="form-group">
				<div class="col-sm-5 add-margin check-text text-center">

					<c:out value="${docs.checkListDetail.description}" />


					<form:hidden
						id="docketDetailProposedWorkList${status.index}checkListDetail.id"
						path="docketDetailProposedWorkList[${status.index}].checkListDetail.id"
						value="${docs.checkListDetail.id}" />

					<form:hidden
						id="docketDetailProposedWorkList${status.index}checkListDetail.description"
						path="docketDetailProposedWorkList[${status.index}].checkListDetail.description"
						value="${docs.checkListDetail.description}" />
				</div>

				<div class="col-sm-3 add-margin">
				 <c:choose>
				<c:when  test="${mode =='editinsp'}">
				<form:radiobutton
						path="docketDetailProposedWorkList[${status.index}].value" value="true"  />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailProposedWorkList[${status.index}].value" value="false"
						/>
					<spring:message code="lbl.no" />
					<form:errors path="docketDetailProposedWorkList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:when>
				<c:otherwise>
				<form:radiobutton
						path="docketDetailProposedWorkList[${status.index}].value" value="true" />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailProposedWorkList[${status.index}].value" value="false"
						checked="checked" />
						<spring:message code="lbl.no" />
						<form:errors path="docketDetailProposedWorkList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:otherwise>
				</c:choose> 
				</div>

				<div class="col-sm-3 add-margin text-center">
					<form:textarea class="form-control patternvalidation"
						data-pattern="alphanumericwithspace" maxlength="256"
						id="docketDetailProposedWorkList${status.index}remarks"
						path="docketDetailProposedWorkList[${status.index}].remarks"
						 />

					<form:errors path="docketDetailProposedWorkList[${status.index}].remarks"
						cssClass="add-margin error-msg" />
				</div>
			</div>
		</c:forEach>
	</c:when>
</c:choose>


<c:choose>
	<c:when test="${!docketDetailWorkAsPerPlanList.isEmpty()}">
	<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		If work Started/completed
	</div>
</div>
		<div class="form-group view-content header-color hidden-xs">
			<div class="col-sm-5 text-center">Documents</div>
			<div class="col-sm-3 text-center">Provided</div>
			<div class="col-sm-3 text-center">
				<spring:message code="lbl.remarks" />
			</div>
		</div>
		<c:forEach var="docs" items="${docketDetailWorkAsPerPlanList}" varStatus="status">
			<div class="form-group">
				<div class="col-sm-5 add-margin check-text text-center">

					<c:out value="${docs.checkListDetail.description}" />


					<form:hidden
						id="docketDetailWorkAsPerPlanList${status.index}checkListDetail.id"
						path="docketDetailWorkAsPerPlanList[${status.index}].checkListDetail.id"
						value="${docs.checkListDetail.id}" />

					<form:hidden
						id="docketDetailWorkAsPerPlanList${status.index}checkListDetail.description"
						path="docketDetailWorkAsPerPlanList[${status.index}].checkListDetail.description"
						value="${docs.checkListDetail.description}" />
				</div>

				<div class="col-sm-3 add-margin">
				 <c:choose>
				<c:when  test="${mode =='editinsp'}">
				<form:radiobutton
						path="docketDetailWorkAsPerPlanList[${status.index}].value" value="true"  />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailWorkAsPerPlanList[${status.index}].value" value="false"
						/>
					<spring:message code="lbl.no" />
					<form:errors path="docketDetailWorkAsPerPlanList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:when>
				<c:otherwise>
				<form:radiobutton
						path="docketDetailWorkAsPerPlanList[${status.index}].value" value="true" />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailWorkAsPerPlanList[${status.index}].value" value="false"
						checked="checked" />
						<spring:message code="lbl.no" />
						<form:errors path="docketDetailWorkAsPerPlanList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:otherwise>
				</c:choose> 
				</div>

				<div class="col-sm-3 add-margin text-center">
					<form:textarea class="form-control patternvalidation"
						data-pattern="alphanumericwithspace" maxlength="256"
						id="docketDetailWorkAsPerPlanList${status.index}remarks"
						path="docketDetailWorkAsPerPlanList[${status.index}].remarks"
						 />

					<form:errors path="docketDetailWorkAsPerPlanList[${status.index}].remarks"
						cssClass="add-margin error-msg" />
				</div>
			</div>
		</c:forEach>
	</c:when>
</c:choose>


<c:choose>
	<c:when test="${!docketDetailHgtAbuttRoadList.isEmpty()}">
	<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		Height of building from the abutting road
	</div>
</div>
		<div class="form-group view-content header-color hidden-xs">
			<div class="col-sm-5 text-center">Documents</div>
			<div class="col-sm-3 text-center">Provided</div>
			<div class="col-sm-3 text-center">
				<spring:message code="lbl.remarks" />
			</div>
		</div>
		<c:forEach var="docs" items="${docketDetailHgtAbuttRoadList}" varStatus="status">
			<div class="form-group">
				<div class="col-sm-5 add-margin check-text text-center">

					<c:out value="${docs.checkListDetail.description}" />


					<form:hidden
						id="docketDetailHgtAbuttRoadList${status.index}checkListDetail.id"
						path="docketDetailHgtAbuttRoadList[${status.index}].checkListDetail.id"
						value="${docs.checkListDetail.id}" />

					<form:hidden
						id="docketDetailHgtAbuttRoadList${status.index}checkListDetail.description"
						path="docketDetailHgtAbuttRoadList[${status.index}].checkListDetail.description"
						value="${docs.checkListDetail.description}" />
				</div>

				<div class="col-sm-3 add-margin">
				 <c:choose>
				<c:when  test="${mode =='editinsp'}">
				<form:radiobutton
						path="docketDetailHgtAbuttRoadList[${status.index}].value" value="true"  />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailHgtAbuttRoadList[${status.index}].value" value="false"
						/>
					<spring:message code="lbl.no" />
					<form:errors path="docketDetailHgtAbuttRoadList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:when>
				<c:otherwise>
				<form:radiobutton
						path="docketDetailHgtAbuttRoadList[${status.index}].value" value="true" />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docketDetailHgtAbuttRoadList[${status.index}].value" value="false"
						checked="checked" />
						<spring:message code="lbl.no" />
						<form:errors path="docketDetailHgtAbuttRoadList[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:otherwise>
				</c:choose> 
				</div>

				<div class="col-sm-3 add-margin text-center">
					<form:textarea class="form-control patternvalidation"
						data-pattern="alphanumericwithspace" maxlength="256"
						id="docketDetailHgtAbuttRoadList${status.index}remarks"
						path="docketDetailHgtAbuttRoadList[${status.index}].remarks"
						 />

					<form:errors path="docketDetailHgtAbuttRoadList[${status.index}].remarks"
						cssClass="add-margin error-msg" />
				</div>
			</div>
		</c:forEach>
	</c:when>
</c:choose>