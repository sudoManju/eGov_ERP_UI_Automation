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
jQuery(document).ready(function($) {
	
	if($('#noJAORSAMessage') && $('#noJAORSAMessage').val())
		bootbox.alert($('#noJAORSAMessage').val());
});

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
function validateMobileNumber(obj)
{
	var text = obj.value;
	if(text!=''){
		if(text.length!=10)
		{		
			obj.value="";
			bootbox.alert("Invalid Mobile length");
			return false;
		}
	validatePhoneNumber(obj,'mobile');
	}
	return true;
}
//email validation
$('input[id$="emailId"]').blur(function() {
		var pattern = new RegExp("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
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
//mobile number validation
$('#mobileNumber').blur( function () {
	 var mobileno = $(this).val();
		if (mobileno.length < 10) {
			bootbox.alert("Please enter 10 digit mobile number");
			$(this).val('');
		}
	});
$('#ward').change(function(){
	jQuery.ajax({
		url: "/egi/public/boundary/ajaxBoundary-blockByWard.action",
		type: "GET",
		data: {
			wardId : jQuery('#ward').val()
		},
		cache: false,
		dataType: "json",
		success: function (response) {
			jQuery('#block').html("");
			jQuery('#block').append("<option value=''>Select</option>");
			jQuery.each(response, function(index, value) {
				jQuery('#block').append($('<option>').text(value.blockName).attr('value', value.blockId));
			});
		}, 
		error: function (response) {
			jQuery('#block').html("");
			jQuery('#block').append("<option value=''>Select</option>");
		}
	});
});



$('#serviceType').change(function(){
	jQuery.ajax({
		url: "/bpa/ajax/getAdmissionFees",
		type: "GET",
		data: {
			serviceType : jQuery('#serviceType').val()
		},
		cache: false,
		dataType: "json",
		success: function (response) {
			
				$("#admissionfeeAmount").prop("disabled", true);
				jQuery('#admissionfeeAmount').val(response);

		}, 
		error: function (response) {
			
		}
	});
});
$('#serviceType').change(function(){
	jQuery.ajax({
		url: "/bpa/application/getdocumentlistbyservicetype",
		type: "GET",
		data: {
			serviceType : $('#serviceType').val()
		},
		dataType: "json",
		success: function (response) {
			$('#bpaDocumentsBody').empty();
			$.each(response, function (index, checklist) {
                $('#bpaDocumentsBody').append(
                		'<div class="form-group">'+
                			'<div class="col-sm-3 add-margin check-text text-right"> <input type="hidden" id="applicationDocument'+index+'checklistDetail" name="applicationDocument['+index+'].checklistDetail" value="'+checklist.id+'">'+
                			'<input type="hidden" id="applicationDocument'+index+'checklistDetail" name="applicationDocument['+index+'].checklistDetail.isMandatory" value="'+checklist.isMandatory+'">'+
                			'<input type="hidden" id="applicationDocument'+index+'checklistDetail.description" name="applicationDocument['+index+'].checklistDetail.description" value="'+checklist.description+'">'+
	                		checklist.description+ (checklist.isMandatory?'<span class="mandatory"></span>':'') +'</div>'
	                		+'<div class="col-sm-3 add-margin text-center"><input type="checkbox" id="applicationDocument'+index+'issubmitted" name="applicationDocument['+index+'].issubmitted" value="applicationDocument${status.index}issubmitted" /></div>'
	                		+'<div class="col-sm-3 add-margin text-center"><div class="input-group"><textarea class="form-control patternvalidation" data-pattern="string" maxlength="256" name="applicationDocument['+index+'].remarks" /></div></div>' +
	                		'<div class="col-sm-3 add-margin text-center"><input type="file" id="file'+index+'id" name="applicationDocument['+index+'].files"class="file-ellipsis upload-file"'+ (checklist.isMandatory? "required" :'')+'>'+
	                		'</div>'+
                		'</div>');
            })

		}, 
		error: function (response) {
			
		}
	});
});


//toggle between multiple tab
jQuery('form').validate({
	ignore: ".ignore",
	invalidHandler: function(e, validator){
	if(validator.errorList.length)
	$('#settingstab a[href="#' + jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id') + '"]').tab('show');
	}
	});

$('#buttonSubmit').click(function(e) {
	if ($('form').valid()) {
		//
	} else {
		e.preventDefault();
	}
});

$('#stakeHolderType').change(function(){
	$.ajax({
		url: "/bpa/ajax/stakeholdersbytype",     
		type: "GET",
		data: {
			stakeHolderType : $('#stakeHolderType').val()    
		},
		dataType: "json",
		success: function (response) {
			$('#stakeHolder').empty();
			$('#stakeHolder').append($("<option value=''>Select from below</option>"));
			$.each(response, function(index, value) {
				$('#stakeHolder').append($('<option>').text(value.name).attr('value', value.id));  
			});
		}, 
		error: function (response) {
		}
	});
});