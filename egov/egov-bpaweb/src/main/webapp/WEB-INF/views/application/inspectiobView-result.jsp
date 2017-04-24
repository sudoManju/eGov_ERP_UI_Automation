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

<div class="alert alert-success" role="alert">
	<c:if test="${not empty message}">
		<strong>${message}</strong>
	</c:if>
</div>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary" data-collapsed="0">
			<div class="panel-heading custom_form_panel_heading">
				<div class="panel-title">Inspection Details</div>
			</div>
	<div class="row add-border">
			<div class="col-sm-3 add-margin">
				Location of the Plot
			</div>
			<div class="col-sm-3 add-margin view-content">
				<c:out value="${docket[0].locationOfPlot}" default="N/A"></c:out>
			</div>
			<div class="col-sm-3 add-margin">
				Land Area
			</div>
			<div class="col-sm-3 add-margin view-content">
				<c:out value="${inspection.lndRegularizationArea}" default="N/A"></c:out>
			</div>
		</div>
		
		
		<div class="row add-border">
			<div class="col-sm-3 add-margin">
				Building Area
			</div>
			<div class="col-sm-3 add-margin view-content">
				<c:out value="${inspection.bldngBuildUpArea}" default="N/A"></c:out>
			</div>
			<div class="col-sm-3 add-margin">
				Coumpound Wall
			</div>
			<div class="col-sm-3 add-margin view-content">
				<c:out value="${inspection.bldngCompoundWall}" default="N/A"></c:out>
			</div>
		</div>
		<div class="row add-border">
			<div class="col-sm-3 add-margin">
			No of Wells
			</div>
			<div class="col-sm-3 add-margin view-content">
				<c:out value="${inspection.bldngWellOhtSumpTankArea}" default="N/A"></c:out>
			</div>
			<div class="col-sm-3 add-margin">
			Occupancy
			</div>
			<div class="col-sm-3 add-margin view-content">
			N/A
			</div>
		</div>
			<div class="row add-border">
			<div class="col-sm-3 add-margin">
			Extent in Sqmtr
			</div>
			<div class="col-sm-3 add-margin view-content">
				<c:out value="${inspection.lndMinPlotExtent}" default="N/A"></c:out>
			</div>
			<div class="col-sm-3 add-margin">
			
			</div>
			<div class="col-sm-3 add-margin view-content">
			</div>
		</div>

			<div class="row add-border">
				<div class="col-sm-5 add-margin">
					<div class="panel-title">Inspection Details CheckList</div>
				</div>
				<div class="col-sm-3 add-margin">
					<div class="panel-title">Document Provided</div>
				</div>
				<div class="col-sm-3 add-margin">Remarks</div>
			</div>
			<c:choose>
	<c:when test="${!docketDetailLocList.isEmpty()}">
			<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		Location of the Plot
	</div>
	</div>
	
			<div class="panel-body">
				<c:forEach items="${docketDetailLocList}" var="doc" varStatus="counter">

					<div class="row add-border">
						<div class="col-sm-5 add-margin view-content">
							<c:out value="${doc.checkListDetail.description}" default="N/A"></c:out>
						</div>
						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.value=='true' ? 'YES' : 'NO'}" default="N/A"></c:out>
						</div>

						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.remarks}" default="N/A"></c:out>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
			</c:choose>
		<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		Measurement of the PlotCC
	</div>
	</div>
				<div class="panel-body">
				<c:forEach items="${docketDetailMeasumentList}" var="doc" varStatus="counter">

					<div class="row add-border">
						<div class="col-sm-5 add-margin view-content">
							<c:out value="${doc.checkListDetail.description}" default="N/A"></c:out>
						</div>
						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.value=='true' ? 'YES' : 'NO'}" default="N/A"></c:out>
						</div>

						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.remarks}" default="N/A"></c:out>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		Access To Plot
	</div>
		</div>		<div class="panel-body">
				<c:forEach items="${docketDetailAccessList}" var="doc" varStatus="counter">

					<div class="row add-border">
						<div class="col-sm-5 add-margin view-content">
							<c:out value="${doc.checkListDetail.description}" default="N/A"></c:out>
						</div>
						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.value=='true' ? 'YES' : 'NO'}" default="N/A"></c:out>
						</div>

						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.remarks}" default="N/A"></c:out>
						</div>
					</div>
				</c:forEach>
			</div>
				<div class="panel-heading custom_form_panel_heading">
			
				<div class="panel-title">
		Required details surrounding the plot	
	</div>
			</div>	<div class="panel-body">
				<c:forEach items="${docketDetlSurroundingPlotList}" var="doc" varStatus="counter">

					<div class="row add-border">
						<div class="col-sm-5 add-margin view-content">
							<c:out value="${doc.checkListDetail.description}" default="N/A"></c:out>
						</div>
						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.value=='true' ? 'YES' : 'NO'}" default="N/A"></c:out>
						</div>

						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.remarks}" default="N/A"></c:out>
						</div>
					</div>
				</c:forEach>
			</div>
			
				<div class="panel-heading custom_form_panel_heading">
			
				<div class="panel-title">
		Type of land	
	</div></div>
				<div class="panel-body">
				<c:forEach items="${docketDetailLandTypeList}" var="doc" varStatus="counter">

					<div class="row add-border">
						<div class="col-sm-5 add-margin view-content">
							<c:out value="${doc.checkListDetail.description}" default="N/A"></c:out>
						</div>
						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.value=='true' ? 'YES' : 'NO'}" default="N/A"></c:out>
						</div>

						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.remarks}" default="N/A"></c:out>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="panel-heading custom_form_panel_heading">
				<div class="panel-title">
		Stage of proposed work	
	</div></div>
				<div class="panel-body">
				<c:forEach items="${docketDetailProposedWorkList}" var="doc" varStatus="counter">

					<div class="row add-border">
						<div class="col-sm-5 add-margin view-content">
							<c:out value="${doc.checkListDetail.description}" default="N/A"></c:out>
						</div>
						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.value=='true' ? 'YES' : 'NO'}" default="N/A"></c:out>
						</div>

						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.remarks}" default="N/A"></c:out>
						</div>
					</div>
				</c:forEach>
			</div>
			
			
			<div class="panel-heading custom_form_panel_heading">
				<div class="panel-title">
		If work Started/completed
	</div></div>
				<div class="panel-body">
				<c:forEach items="${docketDetailWorkAsPerPlanList}" var="doc" varStatus="counter">

					<div class="row add-border">
						<div class="col-sm-5 add-margin view-content">
							<c:out value="${doc.checkListDetail.description}" default="N/A"></c:out>
						</div>
						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.value=='true' ? 'YES' : 'NO'}" default="N/A"></c:out>
						</div>

						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.remarks}" default="N/A"></c:out>
						</div>
					</div>
				</c:forEach>
			</div>
			
				
			<div class="panel-heading custom_form_panel_heading">
				<div class="panel-title">
		Height of building from the abutting road
	</div></div>
				<div class="panel-body">
				<c:forEach items="${docketDetailHgtAbuttRoadList}" var="doc" varStatus="counter">

					<div class="row add-border">
						<div class="col-sm-5 add-margin view-content">
							<c:out value="${doc.checkListDetail.description}" default="N/A"></c:out>
						</div>
						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.value=='true' ? 'YES' : 'NO'}" default="N/A"></c:out>
						</div>

						<div class="col-sm-3 add-margin view-content">
							<c:out value="${doc.remarks}" default="N/A"></c:out>
						</div>
					</div>
				</c:forEach>
			</div> 
			<div align="center">
				<input type="button" name="button2" id="button2" value="Close"
					class="btn btn-default" onclick="window.close();" />
			</div>
		</div>
	</div>
</div>