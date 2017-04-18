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
jQuery(document).ready(function($) {

	$("#applicantdet").prop("disabled",true);
	$("#appDet").prop("disabled",true);
	$("#serviceType").prop("disabled",true);
	/*$(".btn-primary")
	.click(
			function() {
	document.forms[0].submit();
			});*/
	
	// By default to point update noc details tab
	var tabfocus;
	if($('#showUpdateNoc').val()){
		tabfocus='#checklist-info';
	}
	else{
		tabfocus='#applicant-info';
	}
	
	var prefix = "tab_";
	if (tabfocus) {
	    $('.nav-tabs a[href="'+tabfocus.replace(prefix,"")+'"]').tab('show');
	}

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

//validate form while toggle between multiple tab
jQuery('form').validate({
	ignore: ".ignore",
	invalidHandler: function(e, validator){
	if(validator.errorList.length)
	$('#settingstab a[href="#' + jQuery(validator.errorList[0].element).closest(".tab-pane").attr('id') + '"]').tab('show');
	}
	});


$(document).on('blur','.textarea-content', function(evt) {
	$(this).tooltip('hide')
    .attr('data-original-title', $(this).val());
	evt.stopImmediatePropagation();
});

$(document).on('click','.showModal',function(evt){
	var tableheaderid = $(this).data('header');
	$('#textarea-header').html(tableheaderid);
	$('#textarea-updatedcontent').attr('data-assign-to', $(this).data('assign-to'));
	$('#textarea-updatedcontent').val($('#'+$(this).data('assign-to')).val());
	$("#textarea-modal").modal('show');
	evt.stopImmediatePropagation();
});

//update textarea content in table wrt index
$(document).on('click','#textarea-btnupdate',function(evt){
	$('#'+$('#textarea-updatedcontent').attr('data-assign-to')).val($('#textarea-updatedcontent').val());
	evt.stopImmediatePropagation();
});



jQuery( ".dateval" ).datepicker({ 
  	 format: 'dd/mm/yyyy',
  	 autoclose:true,
       onRender: function(date) {
    	    return date.valueOf() < now.valueOf() ? 'disabled' : '';
    	  }
	  }).on('changeDate', function(ev) {
		  var electiondate = jQuery('#letterSentOn').val();
		  var oathdate = jQuery('#replyReceivedOn').val();
		  if(electiondate && oathdate){
			  DateValidation1(electiondate , oathdate);
		  }
		 
	  }).data('datepicker');
	
	function DateValidation1(start , end){
	    if (start != "" && end != "") {
			var stsplit = start.split("/");
			var ensplit = end.split("/");
			
			start = stsplit[1] + "/" + stsplit[0] + "/" + stsplit[2];
			end = ensplit[1] + "/" + ensplit[0] + "/" + ensplit[2];
			
			return ValidRange(start, end);
		}else{
			return true;
		}
	}

	function ValidRange(start, end) {
		var retvalue = false;
	    var startDate = Date.parse(start);
	    var endDate = Date.parse(end);
		
	    // Check the date range, 86400000 is the number of milliseconds in one day
	    var difference = (endDate - startDate) / (86400000 * 7);
	    if (difference < 0) {
			bootbox.alert("ReplyReceivedOn should be greater than Letter Sent On");
			$('#replyReceivedOn').val('').datepicker("refresh");
			
			} else {
			retvalue = true;
		}
	    return retvalue;
	}

});


