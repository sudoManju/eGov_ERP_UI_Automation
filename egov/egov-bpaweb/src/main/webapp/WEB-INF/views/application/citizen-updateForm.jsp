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
						<form:hidden path="" id="onlinePaymentEnable"
				value="${onlinePaymentEnable}" />
							
			<form:hidden path="" id="wfstateDesc"
				value="${bpaApplication.state.value}" />
					<input type="hidden" name="citizenOrBusinessUser"
				value="${citizenOrBusinessUser}">
	<ul class="nav nav-tabs" id="settingstab">
				<li class="active"><a data-toggle="tab"
					href="#appliccation-info" data-tabidx=0><spring:message
							code='lbl.appln.details' /></a></li>
				<li><a data-toggle="tab" href="#document-info" data-tabidx=1><spring:message
							code='title.documentdetail' /></a></li>
			</ul>
		<div class="tab-content">
				<div id="document-info" class="tab-pane fade">
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="bpaDocumentDetails.jsp"></jsp:include>
					</div>
				</div>
				<div id="appliccation-info" class="tab-pane fade in active">
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="applicationDetails.jsp"></jsp:include>
					</div>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="applicantDetailForm.jsp"></jsp:include>
					</div>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="siteDetail.jsp"></jsp:include>
					</div>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="amenityDetails.jsp"></jsp:include>
					</div>
					<c:if test="${bpaApplication.serviceType.description ne 'Sub-Division of plot/Land Development'}">
						<div class="panel panel-primary" data-collapsed="0">
							<jsp:include page="buildingDetails.jsp" />
						</div>
					</c:if>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="applicationhistory-view.jsp"></jsp:include>
					</div>
				</div>
			</div>
			<div class="buttonbottom" align="center">
				<table>
					<tr>
					<td><c:choose>
					<c:when test="${citizenOrBusinessUser && bpaApplication.id !=null && bpaApplication.state==null}">
				<form:button type="submit" id="buttonSave" class="btn btn-primary"
					value="Save"> Save </form:button>
				<form:button type="submit" id="buttonSubmit" class="btn btn-primary"
					value="Submit">Submit</form:button>
					<form:button type="submit" id="buttonCancel" class="btn btn-primary"
					value="CANCEL APPLICATION"> CANCEL APPLICATION </form:button>
					<c:if test="${onlinePaymentEnable}">
					<form:button type="submit" id="buttonPay" class="btn btn-primary"
					value="Pay Online"> Pay Online </form:button></c:if>
					</c:when>
					<c:otherwise>
					</c:otherwise>
					</c:choose>
						<input type="button" name="button2" id="button2" value="Close"
							class="btn btn-primary" onclick="window.close();" />
						</td>
					</tr>
				</table>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">

jQuery(document).ready(function() {
	$("#applicantdet").prop("disabled",true);
	$("#appDet").prop("disabled",true);
	$("#serviceType").prop("disabled",true);
	$("#admissionfeeAmount").prop("disabled",true);
	if($('#isexistingApprovedPlan').val() == 'true') {
		$('#existingAppPlan').show();
	} else {
		$('#existingAppPlan').hide();
	}

	if($('#isappForRegularization').val() == 'true') {
		$('#constDiv').show();
	}
	else {
		$('#constDiv').hide();
	}
 $('#buttonSave').click(function() {
					var button=$('#buttonSave').val();
					document.getElementById("workFlowAction").value=button;
					$("#applicantdet").prop("disabled",false);
					$("#appDet").prop("disabled",false);
					$("#serviceType").prop("disabled",false);
					$("#admissionfeeAmount").prop("disabled",false);
						document.forms[0].submit();
					
				});
 $('#buttonSubmit').click(function() {
		var button=$('#buttonSubmit').val();
		document.getElementById("workFlowAction").value=button;
		$("#applicantdet").prop("disabled",false);
		$("#appDet").prop("disabled",false);
		$("#serviceType").prop("disabled",false);
		$("#admissionfeeAmount").prop("disabled",false);
			document.forms[0].submit();
		
	});
 $('#buttonPay').click(function() {
		var button=$('#buttonPay').val();
		document.getElementById("workFlowAction").value=button;
		$("#applicantdet").prop("disabled",false);
		$("#appDet").prop("disabled",false);
		$("#serviceType").prop("disabled",false);
		$("#admissionfeeAmount").prop("disabled",false);
			document.forms[0].submit();
		
	});
});
 </script>
<script
	src="<cdn:url value='/resources/global/js/egov/inbox.js?rnd=${app_release_no}' context='/egi'/>"></script>
<script
	src="<cdn:url value='/resources/js/app/buildingarea-details.js?rnd=${app_release_no}'/>"></script>
<script
	src="<cdn:url value='/resources/js/app/bpa-application-validations.js?rnd=${app_release_no}'/>"></script>