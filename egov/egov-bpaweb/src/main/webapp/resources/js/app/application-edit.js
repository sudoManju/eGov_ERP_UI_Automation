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
jQuery(document)
		.ready(
				function() {

					// toggle between multiple tab
					jQuery('form')
							.validate(
									{
										ignore : ".ignore",
										invalidHandler : function(e, validator) {
											if (validator.errorList.length)
												$(
														'#settingstab a[href="#'
																+ jQuery(
																		validator.errorList[0].element)
																		.closest(
																				".tab-pane")
																		.attr(
																				'id')
																+ '"]').tab(
														'show');
										}
									});

					var validator = $("#editBpaApplicationForm").validate({
						highlight : function(element, errorClass) {
							$(element).fadeOut(function() {
								$(element).fadeIn();
							});
						}
					});

					$('.upload-file').removeAttr('required');
					$("#mobileNumber").prop("readOnly", true);
					$("#name").prop("readOnly", true);
					$("#emailId").prop("readOnly", true);
					$('#gender').attr("style", "pointer-events: none;");
					$("#address").prop("readOnly", true);
					$("#admissionfeeAmount").prop("disabled", true);
					$("#applicationDate").prop("disabled", true);
					$("#stakeHolderTypeHead").prop("disabled", true);
					$("#stakeHolderType").prop("disabled", true);
					$("#applicantdet").prop("disabled", true);
					$("#appDet").prop("disabled", true);
					$("#serviceType").prop("disabled", true);
					
					$(".workAction")
							.click(
									function(e) {
										var action = document
												.getElementById("workFlowAction").value;
										if (action == 'Reject') {
											$('#Reject').attr('formnovalidate',
													'true');
											bootbox
													.confirm(
															"Do you really want to Reject the application?",
															function(result) {
																if (result) {
																	var approvalComent = $(
																			'#approvalComent')
																			.val();
																	if (approvalComent == "") {
																		bootbox.alert("Please enter rejection comments!");
																		$('#approvalComent').focus();
																		return true;
																	} else {
																		if (validateForm(validator))
																			validateWorkFlowApprover(action);
																		
																		$('.loader-class').modal('show', {
																			backdrop : 'static'
																		});
																		document.forms[0].submit();
																	}
																}
															});
											return false;
										}

										if (action == 'CANCEL APPLICATION') {
											$('#Cancel').attr('formnovalidate',
													'true');
											bootbox
													.confirm(
															"Do you really want to Cancel the application?",
															function(result) {
																if (result) {
																	var approvalComent = $('#approvalComent').val();
																	if (approvalComent == "") {
																		bootbox.alert("Please enter cancellation comments!");
																		return true;
																	} else {
																		if (validateForm(validator))
																			validateWorkFlowApprover(action);
																		
																		$('.loader-class').modal('show', {
																			backdrop : 'static'
																		});
																		document.forms[0].submit();
																	}
																}
															});
											return false;
										}
										validateWorkFlowApprover(action);
										if ($('#wfstateDesc').val() == 'NEW') {
											$('#approvalDepartment')
													.removeAttr('required');
											$('#approvalDesignation')
													.removeAttr('required');
											$('#approvalPosition').removeAttr(
													'required');
											document.forms[0].submit;
											return true;

										}
										if ($('#wfstateDesc').val() != 'NEW') {
											return validateForm(validator);
										}
									});

					// mobile number validation
					$('#mobileNumber')
							.blur(
									function() {
										var mobileno = $(this).val();
										if (mobileno.length < 10) {
											bootbox
													.alert("Please enter 10 digit mobile number");
											$(this).val('');
										}
									});

					$(document).on(
							'blur',
							'.textarea-content',
							function(evt) {
								$(this).tooltip('hide').attr(
										'data-original-title', $(this).val());
								evt.stopImmediatePropagation();
							});

					$(document).on(
							'click',
							'.showModal',
							function(evt) {
								var tableheaderid = $(this).data('header');
								$('#textarea-header').html(tableheaderid);
								$('#textarea-updatedcontent').attr(
										'data-assign-to',
										$(this).data('assign-to'));
								$('#textarea-updatedcontent').val(
										$('#' + $(this).data('assign-to'))
												.val());
								$("#textarea-modal").modal('show');
								evt.stopImmediatePropagation();
							});

					// update textarea content in table wrt index
					$(document)
							.on(
									'click',
									'#textarea-btnupdate',
									function(evt) {
										$('#'+ $('#textarea-updatedcontent').attr(
												'data-assign-to')).val($('#textarea-updatedcontent').val());
										evt.stopImmediatePropagation();
									});

					jQuery(".dateval")
							.datepicker(
									{
										format : 'dd/mm/yyyy',
										autoclose : true,
										onRender : function(date) {
											return date.valueOf() < now
													.valueOf() ? 'disabled'
													: '';
										}
									}).on(
									'changeDate',
									function(ev) {
										var electiondate = jQuery(
												'#letterSentOn').val();
										var oathdate = jQuery(
												'#replyReceivedOn').val();
										if (electiondate && oathdate) {
											DateValidation1(electiondate,
													oathdate);
										}

									}).data('datepicker');

					function DateValidation1(start, end) {
						if (start != "" && end != "") {
							var stsplit = start.split("/");
							var ensplit = end.split("/");

							start = stsplit[1] + "/" + stsplit[0] + "/"
									+ stsplit[2];
							end = ensplit[1] + "/" + ensplit[0] + "/"
									+ ensplit[2];

							return ValidRange(start, end);
						} else {
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
							bootbox
									.alert("ReplyReceivedOn should be greater than Letter Sent On");
							$('#replyReceivedOn').val('').datepicker("refresh");

						} else {
							retvalue = true;
						}
						return retvalue;
					}
					$('#zone').trigger('change');
					//$('#ward').trigger('change');   
					$('#schemes').trigger('change');

				});

function validateForm(validator) {
	if ($('#viewBpaApplicationForm').valid()) {
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