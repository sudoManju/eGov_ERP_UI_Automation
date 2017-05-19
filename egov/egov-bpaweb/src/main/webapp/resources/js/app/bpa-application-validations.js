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
var reportdatatable;
$(document).ready(function() {
	
	// Each Amenity type validations
	$('.applicationAmenity').blur(function(){
		$('.dyn-mandatory').removeAttr('required');
		$('.toggle-madatory').find("span").removeClass( "mandatory" );
		$('.dyn-mandatory').removeClass( "error" );
		
		var amenities = [];
		$.each($(".applicationAmenity option:selected"), function(){            
			amenities.push($(this).text());
        });
		
		var amenitiesArry = amenities.toString().split(',');
		$.each(amenitiesArry, function(index, amenitiesArry) {
			var splitStr = amenitiesArry.substring(0,4);
			if('Roof' == splitStr) {
				$('.Roof').attr('required', true);
				$('.Roof').find("span").addClass( "mandatory" );
			} else if('Well' == splitStr) {
				$('.Well').attr('required', true);
				$('.Well').find("span").addClass( "mandatory" );
			} else if('Pole' == splitStr) {
				$('.Pole').attr('required', true);
				$('.Pole').find("span").addClass( "mandatory" );
			} else if('Shut' == splitStr) {
				$('.Shut').attr('required', true);
				$('.Shut').find("span").addClass( "mandatory" );
			} else if('Towe' == splitStr) {
				$('.Towe').attr('required', true);
				$('.Towe').find("span").addClass( "mandatory" );
			} else if('Comp' == splitStr) {
				$('.Comp').attr('required', true);
				$('.Comp').find("span").addClass( "mandatory" );
			}
		});
	});
	
	
	// For each main service type validations
	$('.serviceType').change(function(){
		var seviceTypeName = $( "#serviceType option:selected" ).text();
		if('Sub-Division of plot/Land Development' == seviceTypeName){
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
			$('.buildingdetails').hide();
			
		} else if('Amenities' == seviceTypeName){
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
			$('.extentOfLand').find("span").removeClass( "mandatory" );
			$('#extentOfLand').removeAttr('required');
		} else if('Permission for Temporary hut or shed' == seviceTypeName){
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
			$('.Hut').find("span").addClass( "mandatory" );
			$('#noOfHutOrSheds').attr('required',true);
		} else {
			$('.extentOfLand').find("span").addClass( "mandatory" );
			$('#extentOfLand').attr('required');
			$('.handle-mandatory').attr('required',true);
			$('.handle-mandatory').find("span").addClass( "mandatory" );
		}
	});
	
	// making atleast one amenity should be mandatory if service type is selected as amenities 
	$('#buttonSubmit').click(function(){
		var seviceTypeName = $( "#serviceType option:selected" ).text();
		if('Amenities' == seviceTypeName && !$( "#applicationAmenity option:selected" ).val()){
				bootbox.alert("Please Select Atleast one amenity.");
				return false; 
		}
	});
	
	$('#existingAppPlan').hide();
	  $('#constDiv').hide();
	$('#isexistingApprovedPlan').on('change', function(){ 
		   if(this.checked) // if changed state is "CHECKED"
		    {
			   $('.removemandatory').find("span").addClass( "mandatory" );
			   $('#existingAppPlan').show();
			   $('#feeAmountRecieptNo').attr('required', true);
			   $('#approvedReceiptDate').attr('required', true);
			   $('#revisedApplicationNumber').attr('required', true);
			   $('#revisedPermitNumber').attr('required', true);
		    }
		   if(!this.checked) // if changed state is "CHECKED"
		    {
			  $('.removemandatory').find("span").removeClass( "mandatory" );
			  $('#feeAmountRecieptNo').attr('required', false);
			  $('#approvedReceiptDate').attr('required', false);
			  $('#revisedApplicationNumber').attr('required', false);
			  $('#revisedPermitNumber').attr('required', false);
			  $('#existingAppPlan').hide();

		    }
		});
	
	$('#isappForRegularization').on('change', function(){ 
		   if(this.checked) // if changed state is "CHECKED"
		    {
			   $('#constDiv').show();
			   $('#constStages').attr('required', true);
			   $('#constStages').change(function(){
					if($('#constStages option:selected').html()=="NotStarted" ||  $(this).val()=="-1"){
						 $('#stateOfConstruction').attr('required', true);
					}
					});
		    }
		   if(!this.checked) // if changed state is "CHECKED"
		    {
			   $('#constStages').attr('required', false);
				  $('#stateOfConstruction').attr('required', false);
				  $('#constDiv').hide();
		    }
		});
	
	
	$('#constStages').change(function(){
		if($('#constStages option:selected').html()=="NotStarted" ||  $(this).val()=="-1"){
			 $('#stateOfConstruction').attr('required', true);
	 		$('#stateOfConstruction').append('<span class="mandatory">*</span>');
					
		}else if($('#constStages option:selected').html()=="Started"){	
			$('#stateOfConstruction').attr('required', false);
		}
	});
	
	$( "#isexistingApprovedPlan" ).trigger( "change" );
	$( "#isappForRegularization" ).trigger( "change" );
	$( "#constStages" ).trigger( "change" );
	$( ".serviceType" ).trigger( "change" );
	$( ".applicationAmenity" ).trigger( "blur" );
	
});

