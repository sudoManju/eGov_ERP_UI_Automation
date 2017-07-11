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
			id="editCitizenApplicationform"
			cssClass="form-horizontal form-groups-bordered"
			enctype="multipart/form-data">
			<form:hidden path="" id="workFlowAction" name="workFlowAction"/>	
			<form:hidden path="" id="onlinePaymentEnable" name="onlinePaymentEnable" value="${onlinePaymentEnable}" />
			<form:hidden path="" id="wfstateDesc" value="${bpaApplication.state.value}" />
			<input type="hidden" name="citizenOrBusinessUser" id="citizenOrBusinessUser" value="${citizenOrBusinessUser}">
			<input type="hidden"  id="validateCitizenAcceptance" name="validateCitizenAcceptance" value="${validateCitizenAcceptance}"/>
			<input type="hidden"  id="citizenDisclaimerAccepted" name="citizenDisclaimerAccepted" value="${citizenDisclaimerAccepted}"/>
			<input type="hidden"  id="isCitizen" name="isCitizen" value="${isCitizen}"/>
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
					<div class="panel panel-primary" data-collapsed="0" id="applicantDiv">
						<jsp:include page="applicantDetailForm.jsp"></jsp:include>
					</div>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="applicationDetails.jsp"></jsp:include>
					</div>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="amenityDetails.jsp"></jsp:include>
					</div>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="siteDetail.jsp"></jsp:include>
					</div>
					<c:if test="${bpaApplication.serviceType.description ne 'Sub-Division of plot/Land Development'}">
						<div class="panel panel-primary" data-collapsed="0">
							<jsp:include page="buildingDetails.jsp" />
						</div>
					</c:if>
					<c:if test="${(isCitizen && validateCitizenAcceptance) || (!isCitizen)}">
						<div class="panel panel-primary" data-collapsed="0">
							<jsp:include page="disclaimer.jsp" />
						</div>
					</c:if>
					<c:if test="${not empty  bpaApplication.receipts}">
						<div class="panel panel-primary" data-collapsed="0">
							<jsp:include page="view-bpa-receipt-details.jsp"></jsp:include>
						</div>
					</c:if>
					<div class="panel panel-primary" data-collapsed="0">
						<jsp:include page="applicationhistory-view.jsp"></jsp:include>
					</div>
				</div>
			</div>
			<div class="buttonbottom" align="center" id="buttonDiv">
				<table>
					<tr>
					<td>
					<form:button type="submit" id="buttonSave" class="btn btn-primary"
							value="Save"> Save </form:button>
					<c:if test="${citizenOrBusinessUser && bpaApplication.id !=null && bpaApplication.state==null && !isCitizen}">
						<form:button type="submit" id="buttonSubmit" class="btn btn-primary"
							value="Submit">Submit</form:button>
						<form:button type="submit" id="buttonCancel" class="btn btn-primary"
						value="CANCEL APPLICATION"> CANCEL APPLICATION </form:button>
					</c:if>
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
	
	$('#applicantDiv :input').attr('readOnly', true);
	$('#gender').attr("style", "pointer-events: none;"); 
	 
	if($('#isCitizen').val() == 'true'){
		$(':input').attr('readOnly', true);  
		$('#buttonSubmit,#button2').prop("readOnly",false);
		$('option:not(:selected)').attr('disabled', true);
		if($('#validateCitizenAcceptance').val() == 'true'){
			$('#citizenAccepted').prop("readOnly",false);
			$("input[type=checkbox]:not('#citizenAccepted')") .on("click", function(e) {
	            e.preventDefault();
	        });
		}else{
			$("input[type=checkbox]") .on("click", function(e) {
	            e.preventDefault();
	        });
		}
	} else {
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
	}

 $('#buttonSave').click(function() {
				 if($('#citizenOrBusinessUser').val())
					{// for citizen login
					 if($('#validateCitizenAcceptance').val() == 'true' && $('#isCitizen').val() == 'true'){
						 if(!$('#citizenAccepted').prop('checked')){
								bootbox.alert("Please accept disclaimer to continue...");
								return false;
							}
					 } else if($('#isCitizen').val() != 'true'){  //for business user login
						if(!$('#architectAccepted').prop('checked')){
							bootbox.alert("Please accept disclaimer to continue...");
							return false;
						}
					 }
					}
					var button=$('#buttonSave').val();
					document.getElementById("workFlowAction").value=button;
					$("#applicantdet").prop("disabled",false);
					$("#appDet").prop("disabled",false);
					$("#serviceType").prop("disabled",false);
					$("#admissionfeeAmount").prop("disabled",false);
						document.forms[0].submit();
					
				});
 $('#buttonSubmit').click(function() {
	 	if($('#citizenOrBusinessUser').val())
		{
			// for citizen login
	 		 if($('#validateCitizenAcceptance').val() == 'true' && $('#isCitizen').val() == 'true'){
				 if(!$('#citizenAccepted').prop('checked')){
						bootbox.alert("Please accept disclaimer to continue...");
						return false;
					}
			 } else if($('#isCitizen').val() != 'true'){  //for business user login
				if(!$('#architectAccepted').prop('checked')){
					bootbox.alert("Please accept disclaimer to continue...");
					return false;
				}
				if($('#validateCitizenAcceptance').val() == 'true' && $('#citizenDisclaimerAccepted').val() != 'true'){
					bootbox.alert("Citizen Disclaimer Acceptance Pending. Cannot Submit Application.");
					return false;
				}
			 }
		}
		var button=$('#buttonSubmit').val();
		document.getElementById("workFlowAction").value=button;
		$("#applicantdet").prop("disabled",false);
		$("#appDet").prop("disabled",false);
		$("#serviceType").prop("disabled",false);
		$("#admissionfeeAmount").prop("disabled",false);
			document.forms[0].submit();
		
	});
 $('#buttonCancel').click(function() {
		var button=$('#buttonCancel').val();
		document.getElementById("workFlowAction").value=button;
		document.forms[0].submit();
		
	});
});
 </script>

<script
	src="<c:url value='/resources/global/js/handlebars/handlebars.js?rnd=${app_release_no}' context='/egi'/>"></script>
<script
	src="<cdn:url value='/resources/global/js/egov/inbox.js?rnd=${app_release_no}' context='/egi'/>"></script>
<script
	src="<cdn:url value='/resources/js/app/buildingarea-details.js?rnd=${app_release_no}'/>"></script>
<script
	src="<cdn:url value='/resources/js/app/bpa-application-validations.js?rnd=${app_release_no}'/>"></script>