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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/taglib/cdn.tld" prefix="cdn"%>
<div class="row">
	<div class="col-md-12">
			<form:form role="form" method="post" modelAttribute="bpaApplication"
			id="editWaterConnectionform"
			cssClass="form-horizontal form-groups-bordered"
			enctype="multipart/form-data">
						<form:hidden path="" id="workFlowAction" name="workFlowAction"/>		
			<form:hidden path="" id="wfstateDesc"
				value="${bpaApplication.state.value}" />
				<input type="text" id="statusCode"
				value="${bpaApplication.status.code}" />
					<input type="hidden" name="citizenOrBusinessUser"
				value="${citizenOrBusinessUser}">
			<ul class="nav nav-tabs" id="settingstab">
				<li class="active"><a data-toggle="tab"
					href="#appliccation-info" data-tabidx=0><spring:message
							code='lbl.appln.details' /></a></li>
				<li><a data-toggle="tab" href="#document-info" data-tabidx=1><spring:message
							code='title.documentdetail' /></a></li>
				<c:if test="${not empty bpaApplication.documentScrutiny}">
						<li><a data-toggle="tab" href="#doc-scrnty" data-tabidx=2><spring:message
									code='lbl.document.scrutiny' /></a></li>
				</c:if>
				<c:if test="${showUpdateNoc}">
					<li><a data-toggle="tab" href="#checklist-info" data-tabidx=3><spring:message
								code='lbl.noc.doc.details' /></a></li>
				</c:if>

				<c:if test="${not empty bpaApplication.applicationNOCDocument}">
					<c:if test="${showNOCDetails}">
						<li><a data-toggle="tab" href="#noc-info" data-tabidx=3><spring:message
									code='lbl.noc.details' /></a></li>
					</c:if>
				</c:if>
				
				<c:if test="${not empty bpaApplication.inspections}">
					<li><a data-toggle="tab" href="#view-inspection" data-tabidx=4><spring:message
								code='lbl.inspection.appln' /></a></li>
				</c:if>
				<c:if test="${not empty bpaApplication.applicationFee}">
					<li><a data-toggle="tab" href="#view-fee" data-tabidx=5><spring:message
								code='lbl.applicationFee' /></a></li>
				</c:if>
			</ul>
			<div class="tab-content">
				<div id="document-info" class="tab-pane fade">
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="view-bpaDocumentdetails.jsp"></jsp:include>
					</div>
				</div>
				<div id="appliccation-info" class="tab-pane fade in active">
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="view-applicantdetails.jsp"></jsp:include>
					</div>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="viewapplication-details.jsp"></jsp:include>
					</div>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="view-sitedetail.jsp"></jsp:include>
					</div>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="view-building-details.jsp" />
					</div>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="applicationhistory-view.jsp"></jsp:include>
					</div>
				</div>
				
				<c:if test="${not empty bpaApplication.documentScrutiny}">
						<div id="doc-scrnty" class="tab-pane fade">
							<div class="panel panel-primary" data-collapsed="0">
								<jsp:include page="view-documentscrutiny.jsp"></jsp:include>
							</div>
						</div>
				</c:if>
				<c:if test="${not empty bpaApplication.applicationNOCDocument}">
					<c:if test="${showNOCDetails}">
						<div id="noc-info" class="tab-pane fade">
							<div class="panel panel-primary" data-collapsed="0">
								<jsp:include page="view-noc-document.jsp"></jsp:include>
							</div>
						</div>
					</c:if>
				</c:if>
				<c:if test="${showUpdateNoc}">
					<input type="hidden" id="showUpdateNoc" value="${showUpdateNoc}">
					<div id="checklist-info" class="tab-pane fade">
						<div class="panel panel-primary" data-collapsed="0">
							<jsp:include page="noc-document-updation.jsp"></jsp:include>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty bpaApplication.inspections}">
						<div id="view-inspection" class="tab-pane fade">
							<div class="panel panel-primary" data-collapsed="0">
								<jsp:include page="view-inspection-details.jsp"></jsp:include>
							</div>
						</div>
				</c:if>
				<c:if test="${not empty bpaApplication.inspections}">
						<div id="view-fee" class="tab-pane fade">
							<div class="panel panel-primary" data-collapsed="0">
								<jsp:include page="view-bpa-fee-details.jsp"></jsp:include>
							</div>
						</div>
				</c:if>
			</div>
			<div class="buttonbottom" align="center">
				<table>
					<tr>
					<td><c:choose>
					<c:when test="${citizenOrBusinessUser && bpaApplication.id !=null && bpaApplication.state==null && statusCode == 'Created'}">
				<form:button type="submit" id="buttonSave" class="btn btn-primary"
					value="Save">Save</form:button>
				<td><form:button type="submit" id="buttonSubmit" class="btn btn-primary"
					value="Submit">Submit</form:button></td>
					</c:when>
					<c:otherwise>
					</c:otherwise>
					</c:choose><td>
						<td><input type="button" name="button2" id="button2" value="Close"
							class="btn btn-primary" onclick="window.close();" />
						</td>
					</tr>
				</table>
			</div>
		</form:form>
	</div>
</div>

<script>
 $('#buttonSave').click(function() {
					var button=$('#buttonSave').val();
					document.getElementById("workFlowAction").value=button;
						document.forms[0].submit();
					
				});
 $('#buttonSubmit').click(function() {
		var button=$('#buttonSubmit').val();
		document.getElementById("workFlowAction").value=button;
			document.forms[0].submit();
		
	});

 </script>
<script
	src="<cdn:url value='/resources/global/js/egov/inbox.js?rnd=${app_release_no}' context='/egi'/>"></script>
