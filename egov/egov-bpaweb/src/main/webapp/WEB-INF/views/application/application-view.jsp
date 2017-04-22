<%--
  ~ eGov suite of products aim to improve the internal efficiency,transparency,
  ~      accountability and the service delivery of the government  organizations.
  ~
  ~       Copyright (C) <2017>  eGovernments Foundation
  ~
  ~       The updated version of eGov suite of products as by eGovernments Foundation
  ~       is available at http://www.egovernments.org
  ~
  ~       This program is free software: you can redistribute it and/or modify
  ~       it under the terms of the GNU General Public License as published by
  ~       the Free Software Foundation, either version 3 of the License, or
  ~       any later version.
  ~
  ~       This program is distributed in the hope that it will be useful,
  ~       but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~       GNU General Public License for more details.
  ~
  ~       You should have received a copy of the GNU General Public License
  ~       along with this program. If not, see http://www.gnu.org/licenses/ or
  ~       http://www.gnu.org/licenses/gpl.html .
  ~
  ~       In addition to the terms of the GPL license to be adhered to in using this
  ~       program, the following additional terms are to be complied with:
  ~
  ~           1) All versions of this program, verbatim or modified must carry this
  ~              Legal Notice.
  ~
  ~           2) Any misrepresentation of the origin of the material is prohibited. It
  ~              is required that all modified versions of this material be marked in
  ~              reasonable ways as different from the original version.
  ~
  ~           3) This license does not grant any rights to any user of the program
  ~              with regards to rights under trademark law for use of the trade names
  ~              or trademarks of eGovernments Foundation.
  ~
  ~     In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
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
			<input type="hidden" name="bpaApplication" value="${bpaApplication.id}">
			<form:hidden path="" id="wfstate" value="${bpaApplication.state.id}" />
			<form:hidden path="" id="workFlowAction" name="workFlowAction" />
			<form:hidden path="" id="wfstateDesc" value="${bpaApplication.state.value}" />
			<form:hidden path="" id="mode" name="mode" value="${mode}" />
			<form:hidden path="" id="scheduleType" name="scheduleType" value="${scheduleType}" />
			<ul class="nav nav-tabs" id="settingstab">
				<li class="active"><a data-toggle="tab" href="#applicant-info"
					data-tabidx=0><spring:message code='lbl.appln.details' /></a></li>
				<c:if test="${showUpdateNoc}"> 
				<li ><a data-toggle="tab" href="#checklist-info" data-tabidx=1><spring:message
							code='lbl.noc.doc.details' /></a></li>
				</c:if>
				<c:if test="${showNOCDetails}">
					<li ><a data-toggle="tab" href="#noc-info" data-tabidx=1><spring:message
							code='lbl.noc.details' /></a></li>
				</c:if>
				
			</ul>
			<div class="tab-content">
				<div id="applicant-info" class="tab-pane fade in active">
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
				<c:if test="${showUpdateNoc}"> 
				<input type="hidden" id="showUpdateNoc" value="${showUpdateNoc}">
					<div id="checklist-info" class="tab-pane fade">
						<div class="panel panel-primary" data-collapsed="0">
							<jsp:include page="noc-document-updation.jsp"></jsp:include>
						</div>
					</div>
				</c:if> 
				<c:if test="${showNOCDetails}">
					<div id="noc-info" class="tab-pane fade">
						<div class="panel panel-primary" data-collapsed="0">
							<jsp:include page="view-noc-document.jsp"></jsp:include>
						</div>
					</div>
				</c:if>
			</div>

			<div class="text-center">
			
				<c:if test="${mode eq 'captureInspection'}">
				<a
						href="/bpa/application/createinspectiondetails/${bpaApplication.applicationNumber}"
						class="btn btn-primary">Capture Inspection Details </a>
					
				</c:if>
				<c:if test="${mode eq 'modifyInspection'}">
						<a
						href="/bpa/application/createinspectiondetails/${bpaApplication.applicationNumber}"
						class="btn btn-primary">Capture New Inspection Details </a>
					
						<a
						href="/bpa/application/modify-inspection/${bpaApplication.applicationNumber}"
						class="btn btn-primary">Add/Edit Inspection Details </a>
				</c:if>
				<c:if test="${mode eq 'newappointment'}">
					<a
						href="/bpa/application/scheduleappointment/${bpaApplication.applicationNumber}"
						class="btn btn-primary"> New Appointment </a>
					
					
				</c:if>
				<c:if test="${mode eq 'postponeappointment'}">
					<a
						href="/bpa/application/postponeappointment/${scheduleType}/${bpaApplication.applicationNumber}"
						class="btn btn-primary"> Reschedule Appointment </a>
						
						
				</c:if>
				<c:if test="${mode eq 'initialtedApprove'}">
					<a
						href="/bpa/application/calculateFee/${bpaApplication.applicationNumber}"
						class="btn btn-primary">Calculate Fee </a>
						
				</c:if>
			</div>
			<br>
			<jsp:include page="../common/commonWorkflowMatrix.jsp" />
			<div class="buttonbottom" align="center">
				<jsp:include page="../common/commonWorkflowMatrix-button.jsp" />
			</div>
		</form:form>
	</div>
</div>

<script
	src="<cdn:url value='/resources/global/js/egov/inbox.js?rnd=${app_release_no}' context='/egi'/>"></script>
<script
	src="<cdn:url value='/resources/js/app/application-edit.js?rnd=${app_release_no}'/>"></script>
<script
	src="<cdn:url value='/resources/js/app/documentsuploadvalidation.js?rnd=${app_release_no}'/>"></script>