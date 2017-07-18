/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
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
var reportdatatable;

jQuery(document).ready(
		function($) {

			var validator = $("#newApplicationform").validate({
				highlight : function(element, errorClass) {
					$(element).fadeOut(function() {
						$(element).fadeIn();
					});
				}
			});

			if ($('#noJAORSAMessage') && $('#noJAORSAMessage').val())
				bootbox.alert($('#noJAORSAMessage').val());

			if ($('#invalidStakeholder').val())
				bootbox.alert($('#invalidStakeholder').val());

			$('#buttonSubmit').click(function(e) {
				return validateForm(validator);
			});

			function validateForm(validator) {
				if($("#postOffices option:selected" ).val() == "") {
					bootbox.alert('Please select postoffice')
				}
				if ($('#newApplicationform').valid() && validateUploadFilesMandatory()) {
					document.getElementById("workFlowAction").value = $(
							'#buttonSubmit').val();
					return true;
				} else {
					$.each(validator.invalidElements(), function(index, elem){
						if(!$(elem).is(":visible") && !$(elem).closest('div.panel-body').is(":visible")){
							$(elem).closest('div.panel-body').show();
						}
					});
					
					validator.focusInvalid();
					return false;
				}
			}
			
			$('.applicantname').hide();
			$('#name').change(function() {
				$('.applicantname').show();
				$( "span#applicantName" ).html( $(this).val() );
			});
			
			$("select.tick-indicator").mousedown(function(e){
			    e.preventDefault();
			    
				var select = this;
			    var scroll = select.scrollTop;
			    
			    e.target.selected = !e.target.selected;
			    
			    $(this).trigger('change');
			    
			    setTimeout(function(){select.scrollTop = scroll;}, 0);
			    
			    $(select).focus();

			}).mousemove(function(e){e.preventDefault()});
			

		});

var citizenOrBusiness = $('#citizenOrBusinessUser').val();
if (citizenOrBusiness == 'true') {
	$('#serviceType').prop("disabled", true);
}
function chkNumeric(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		if (charCode == 46) {
			return true;
		} else {
			return false;
		}
	}
	return true;
}
function validateMobileNumber(obj) {
	var text = obj.value;
	if (text != '') {
		if (text.length != 10) {
			obj.value = "";
			bootbox.alert("Invalid Mobile length");
			return false;
		}
		validatePhoneNumber(obj, 'mobile');
	}
	return true;
}
// email validation
$('input[id$="emailId"]')
		.blur(
				function() {
					var pattern = new RegExp(
							"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
					var email = $(this).val();
					if (!pattern.test(email) && $(this).val().length > 0) {
						var span = $(this).siblings('span');
						$(span).addClass('error-msg');
						$(span).text('Please enter valid email..!');
						$(this).show();
						$(this).val("");
					} else {
						var span1 = $(this).siblings('span');
						$(span1).removeClass('error-msg');
						$(span1).text('');
					}
				});
// mobile number validation
$('#mobileNumber').blur(function() {
	var mobileno = $(this).val();
	if (mobileno && mobileno.length < 10) {
		bootbox.alert("Please enter 10 digit mobile number");
		$(this).val('');
	}
});
$('#zone').change(
		function() {
			jQuery.ajax({
				url : "/egi/public/boundary/ajaxBoundary-blockByWard.action",
				type : "GET",
				data : {
					wardId : jQuery('#zone').val()
				},
				cache : false,
				dataType : "json",
				success : function(response) {
					jQuery('#ward').html("");
					jQuery('#ward').append("<option value=''>Select</option>");
					jQuery.each(response, function(index, value) {
						jQuery('#ward').append(
								$('<option>').text(value.blockName).attr(
										'value', value.blockId));
					});
				},
				error : function(response) {
					jQuery('#ward').html("");
					jQuery('#ward').append("<option value=''>Select</option>");
				}
			});
		});
$('#ward').change(function(){
	jQuery.ajax({
		url: "/bpa/boundary/ajaxBoundary-localityByWard",
		type: "GET",
		data: {
			wardId : jQuery('#ward').val()
		},
		cache: false,
		dataType: "json",
		success: function (response) {
			jQuery('#localitys').html("");
			jQuery('#localitys').append("<option value=''>Select</option>");
			jQuery.each(response, function(index, value) {
				jQuery('#localitys').append($('<option>').text(value.localityName).attr('value', value.localityId));
			});
		}, 
		error: function (response) {
			jQuery('#localitys').html("");
			jQuery('#localitys').append("<option value=''>Select</option>");
		}
	});
});

$('#serviceType,.applicationAmenity').change(function() {
	var servicesAndAmenities =[];
	servicesAndAmenities.push($('#serviceType').val());
	$.each($("#applicationAmenity option:selected"), function(idx){     
		servicesAndAmenities.push($(this).val());
	});
	
	$.ajax({
		url : "/bpa/ajax/getAdmissionFees",
		type : "GET",
		contentType: 'application/json; charset=UTF-8',
		data : {
			"serviceTypeIds" : servicesAndAmenities.join(",")
		},
		cache : false,
		dataType: "json",
		success : function(response) {
			jQuery('#admissionfee').val(response);
		},
		error : function(response) {
		}
	});
	
});
$('#serviceType')
		.change(
				function() {
					jQuery
							.ajax({
								url : "/bpa/application/getdocumentlistbyservicetype",
								type : "GET",
								data : {
									serviceType : $('#serviceType').val()
								},
								dataType : "json",
								success : function(response) {
									$('#bpaDocumentsBody').empty();
									$
											.each(
													response,
													function(index, checklist) {
														$('#bpaDocumentsBody')
																.append(
																		'<div class="form-group">'
																				+ '<div class="col-sm-3 add-margin check-text"> <input type="hidden" id="applicationDocument'
																				+ index
																				+ 'checklistDetail" name="applicationDocument['
																				+ index
																				+ '].checklistDetail" value="'
																				+ checklist.id
																				+ '">'
																				+ '<input type="hidden" id="applicationDocument'
																				+ index
																				+ 'checklistDetail" name="applicationDocument['
																				+ index
																				+ '].checklistDetail.isMandatory" value="'
																				+ checklist.isMandatory
																				+ '">'
																				+ '<input type="hidden" id="applicationDocument'
																				+ index
																				+ 'checklistDetail.description" name="applicationDocument['
																				+ index
																				+ '].checklistDetail.description" value="'
																				+ checklist.description
																				+ '">'
																				+ checklist.description
																				+ (checklist.isMandatory ? '<span class="mandatory"></span>'
																						: '')
																				+ '</div>'
																				+ '<div class="col-sm-2 add-margin "><input type="checkbox" id="applicationDocument'
																				+ index
																				+ 'issubmitted" name="applicationDocument['
																				+ index
																				+ '].issubmitted" /></div>'
																				+ '<div class="col-sm-3 add-margin "><div class="input-group"><textarea class="form-control patternvalidation" data-pattern="string" maxlength="256" name="applicationDocument['
																				+ index
																				+ '].remarks" /></div></div>'
																				+ '<div class="col-sm-4 add-margin "><div class="files-upload-container" data-allowed-extenstion="doc,docx,xls,xlsx,rtf,pdf,txt,zip,jpeg,jpg,png,gif,tiff" '
																				+ (checklist.isMandatory ? "required"
																						: '')
																				+ '> <div class="files-viewer"> <a href="javascript:void(0);" class="file-add" data-unlimited-files="true" data-toggle="tooltip" data-placement="top" tittle="Test Tooltip" data-file-input-name="applicationDocument['
																				+ index
																				+ '].files"> <i class="fa fa-plus" aria-hidden="true"></i></a></div></div>'
																				+ '</div>'
																				+ '</div>');
													})
								},
								error : function(response) {

								}
							});
				});

// toggle between multiple tab
jQuery('form').validate({
	ignore : ".ignore",
	invalidHandler : function(e, validator) {
		if (validator.errorList.length)
			focusToTabElement(validator.errorList[0].element);
	}
});

function focusToTabElement(element) {
	$('#settingstab a[href="#'
					+ jQuery(element).closest(".tab-pane").attr('id') + '"]')
			.tab('show');
}

$('#mobileNumber').change(function(){ 
	jQuery.ajax({
		url: "/bpa/getApplicantDetails",
		type: "GET",
		data: {
			mobileNumber : $('#mobileNumber').val()
		},
		cache : false,
		dataType: "json",
		success: function (response) {
				if(response.id!=undefined){
					jQuery('#name').val(response.name);
					jQuery('#emailId').val(response.emailId);
					jQuery('#gender').val(response.gender);
					jQuery('#address').val(response.address);
					jQuery('#userId').val(response.id); 
					$('.applicantname').show();
					$( "span#applicantName" ).html(response.name);
					$("#name").prop("readOnly", true);
					$("#emailId").prop("readOnly", true);
					$('#gender').attr("style", "pointer-events: none;");
					$("#address").prop("readOnly", true);
				}else{
					$("#name").prop("readOnly", false);
					$("#emailId").prop("readOnly", false);
					$('#gender').attr("style", "pointer-events:");
					$("#address").prop("readOnly", false);
					jQuery('#userId').val("");
				}
		}, 
		error: function (response) {
		}
	});
});

// Instantiate the stakeholder name Bloodhound suggestion engine
var stakeholderengine = new Bloodhound({
	datumTokenizer : function(datum) {
		return Bloodhound.tokenizers.whitespace(datum.value);
	},
	queryTokenizer : Bloodhound.tokenizers.whitespace,
	remote : {
		url : '/bpa/ajax/stakeholdersbytype',
		replace : function(url, query) {
			return url + '?name=' + query + '&stakeHolderType='
					+ $('#stakeHolderType').val();
		},
		filter : function(data) {
			// Map the remote source JSON array to a JavaScript object array
			return $.map(data, function(stakeHolder) {
				return {
					name : stakeHolder.name,
					value : stakeHolder.id
				}
			});
		}
	}
});
// Initialize the Bloodhound suggestion engine
stakeholderengine.initialize();

var sh_typeahead = $('#stakeHolderTypeHead').typeahead({
	hint : false,
	highlight : false,
	minLength : 1
}, {
	displayKey : 'name',
	source : stakeholderengine.ttAdapter(),
	templates: {
	    empty: [
	      '<div class="empty-message">',
	        'No Name Available',
	      '</div>'
	    ].join('\n')
	  }
});
typeaheadWithEventsHandling(sh_typeahead, '#stakeHolderName');
