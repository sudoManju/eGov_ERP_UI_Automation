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

$(document).ready(
		function($) {
			
			var citizenOrBusiness = $('#citizenOrBusinessUser').val();
			if (citizenOrBusiness == 'true') {
				$('#serviceType').prop("disabled", true);
				$("#applicationDate").prop("readOnly",true); 
				$("#applicationDate").removeClass( "form-control datepicker" ).addClass( "form-control" ); 
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

			$('#schemes').change(function(){
				jQuery.ajax({
					url: "/bpa/ajax/getlandusagebyscheme",
					type: "GET",
					data: {
						schemeId : jQuery('#schemes').val()
					},
					cache: false,
					dataType: "json",
					success: function (response) {
						jQuery('#landUsage').html("");
						jQuery('#landUsage').append("<option value=''>Select</option>");
						jQuery.each(response, function(index, value) {
							jQuery('#landUsage').append($('<option>').text(value.usageDesc).attr('value', value.usageId));
						});
						jQuery('#landUsage').val(jQuery('#landUsageObjectId').val());
					}, 
					error: function (response) {
						jQuery('#landUsage').html("");
						jQuery('#landUsage').append("<option value=''>Select</option>");
					}
					
				});
			});
			$('#landUsage').change(function() {
				jQuery('#landUsageId').val(jQuery('#landUsage').val());
			});
			$('#schemes').trigger("change");
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

			/*$('#mobileNumber').change(function(){ 
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
			});*/

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
			
			

			//Instantiate the postaladdress name Bloodhound suggestion engine
			var postaladdressengine = new Bloodhound({
				datumTokenizer : function(datum) {
					return Bloodhound.tokenizers.whitespace(datum.value);
				},
				queryTokenizer : Bloodhound.tokenizers.whitespace,
				remote : {
					url : '/bpa/ajax/postaladdress',
					replace : function(url, query) {
						return url + '?pincode=' + query ;
					},
					filter : function(data) {
						return $.map(data, function(postal) {
							return {
								name : postal.pincode,
								taluk : postal.taluk,
								pincode : postal.pincode,
								value : postal.id,
								postOffice : postal.postOffice,
								district : postal.district,
								state : postal.state
							}
						});
					}
				}
			});

			//Initialize the Bloodhound suggestion engine
			postaladdressengine.initialize();

			var postal_typeahead = $('#postalAddressTypeHead').typeahead({
				hint: false,
				highlight: false,
				minLength: 2
			}, {
				displayKey : 'name',
				source : postaladdressengine.ttAdapter(),
				 templates: {
				    empty: [
				      '<div class="empty-message">',
				        'No Pincode Available',
				      '</div>'
				    ].join('\n'),
				    suggestion: Handlebars.compile('<div class="custom-list-item"><h4 class="padding-10">{{name}} <small>{{postOffice}}</small></h4></div>')
				  }
			});

			function postalCodeSelectedEvent(event, data) {
					$('#taluk').val(data.taluk);
					$('#postalAddress').val(data.value);
					$('#postOffices').val(data.postOffice);
					$('#district').val(data.district);
					$('#state').val(data.state);
			}

			typeaheadWithEventsHandling(postal_typeahead, '#postalAddress', undefined, postalCodeSelectedEvent);
			
	});
