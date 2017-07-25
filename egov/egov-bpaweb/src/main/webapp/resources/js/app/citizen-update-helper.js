/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2017>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */


$(document).ready(function($) {
	
	//toggle between multiple tab
	jQuery('form').validate({
		ignore: ".ignore",
		invalidHandler: function(e, validator){
		if(validator.errorList.length)
		$('#settingstab a[href="#' + jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id') + '"]').tab('show');
		}
		});
	
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
	}
	
	
	var validator=$("#editCitizenApplicationform").validate({
	  highlight: function(element, errorClass) {
	    $(element).fadeOut(function() {
	      $(element).fadeIn();
	    });
	  }
	});
	
	
 $('#buttonSave').click(function() {
	 var button=$('#buttonSave').val();
	 return validateEditForm(button, validator);
				});
 $('#buttonSubmit').click(function() {
	var button = $('#buttonSubmit').val();
	return validateEditForm(button, validator);
 });
 
 $('#buttonCancel').click(function(e) {
	 
	 if ($('#editCitizenApplicationform').valid()) {
			bootbox
			.confirm({
				message : 'Do you really want to Cancel the application ?',
				buttons : {
					'cancel' : {
						label : 'No',
						className : 'btn-danger'
					},
					'confirm' : {
						label : 'Yes',
						className : 'btn-primary'
					}
				},
				callback : function(result) {
					if (result) {
						var button=$('#buttonCancel').val();
						document.getElementById("workFlowAction").value=button;
						$('.loader-class').modal('show', {
							backdrop : 'static'
						});
						document.forms[0].submit();
					} else {
						e.stopPropagation();
						e.preventDefault();
					}
				}
			});
			} else {
			e.stopPropagation();
			e.preventDefault();
			}
	 
	 
	});
	
});

function validateEditForm(button, validator) {

	 if ($('#editCitizenApplicationform').valid() && validateUploadFilesMandatory()) {
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
				
				if(button == 'Submit' && $('#validateCitizenAcceptance').val() == 'true' && $('#citizenDisclaimerAccepted').val() != 'true'){
					bootbox.alert("Citizen Disclaimer Acceptance Pending. Cannot Submit Application.");
					return false;
				}
			 }
		}
		document.getElementById("workFlowAction").value=button;
		$("#applicantdet").prop("disabled",false);
		$("#appDet").prop("disabled",false);
		$("#serviceType").prop("disabled",false);
		$("#admissionfeeAmount").prop("disabled",false);
		return true;
	 } else {
		 $errorInput=undefined;
			
			$.each(validator.invalidElements(), function(index, elem){
				
				if(!$(elem).is(":visible") && !$(elem).val() && index==0 
						&& $(elem).closest('div').find('.bootstrap-tagsinput').length > 0){
					$errorInput=$(elem);
				}
				
				if(!$(elem).is(":visible") && !$(elem).closest('div.panel-body').is(":visible")){
					$(elem).closest('div.panel-body').show();
					console.log("elem", $(elem));
				}
			});
			
			if($errorInput)
				$errorInput.tagsinput('focus');
			
			validator.focusInvalid();
			return false;
	 }
}
	