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
var occupancyResponse;
$(document).ready(function() {
	
	$('.show-hide').hide();
	$('.totalPlintArea').show();
	
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
		$('.buildingdetails').show();
		$('.noofhutorshed').removeAttr('required');
		$('.noofhutorshed').find("span").removeClass( "mandatory" );
		$('.noofhutorshed').removeClass( "error" );
		if('Sub-Division of plot/Land Development' == seviceTypeName){
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
			$('.buildingdetails').hide();
		} else if('Amenities' == seviceTypeName){
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
		} else if('Permission for Temporary hut or shed' == seviceTypeName){
			$('.show-hide').hide();
			$('.noofhutorshed').show();
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
			$('.Hut').find("span").addClass( "mandatory" );
			$('.noofhutorshed').attr('required',true);
			$('.noofhutorshed').find("span").addClass( "mandatory" );
			$('#totalPlintArea').attr('required',true);
		} else {
			$('.show-hide').hide();
			if('Change in occupancy' == seviceTypeName){
				$('.changeInOccupancyArea').show();
			} else if('Alteration' == seviceTypeName){
				$('.alterationInArea').show();
			} else if('Adding of Extension' == seviceTypeName){
				$('.additionInArea').show();
			} else {
				$('.totalPlintArea').show();
			}
			
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
	
	// on form load get occupancy details List
	$.ajax({
		url: "/bpa/application/getoccupancydetails",     
		type: "GET",
		dataType: "json",
		success: function (response) {
			occupancyResponse = arrayGroupByKey(response, 'id');
		}, 
		error: function (response) {
		}
	});
	
});

$('#occupancy').change(function(){
	$('.clear-values').val('');
	$('#buildingAreaDetails').find('input').val('');
	getOccupancyObject();
});

function arrayGroupByKey(arry, groupByKey){
	var resultData={};
	var result=arry.reduce(function(result, current) {
	   result[current[groupByKey]] = result[current[groupByKey]] || [];
	   result[current[groupByKey]].push(current);
	   return result;
	}, {});

	Object.keys(result).sort().forEach(function(key) {
	 resultData[key] = result[key];
	});

	return resultData;
}
var extentOfLand;
var totalPlintArea;
var extentInSqmts;
$('#totalPlintArea').blur(function(e) {
	var occpancyObj = getOccupancyObject();
	var numOfTimesAreaPermissible = occpancyObj[0].numOfTimesAreaPermissible;
	var numOfTimesAreaPermWitAddnlFee = occpancyObj[0].numOfTimesAreaPermWitAddnlFee;
	var areaPermissibleWOAddnlFee = parseInt(extentInSqmts) * parseInt(numOfTimesAreaPermissible);
	var areaPermissibleWithAddnlFee = parseInt(extentInSqmts) * parseInt(numOfTimesAreaPermWitAddnlFee);
		if(parseInt(totalPlintArea) > areaPermissibleWithAddnlFee) {
			bootbox.alert("For the occupancy type of " +occpancyObj[0].description+", maximum permissible area allowed with addtional fee is "+areaPermissibleWithAddnlFee+", beyond of permissible area you can't construct construction.");
			$('#totalPlintArea').val('');
			return false;
		} else if(parseInt(totalPlintArea) > areaPermissibleWOAddnlFee) {
			bootbox
			.confirm({
				message : 'For the occupancy type of ' +occpancyObj[0].description+', maximum permissible area allowed with out addtional fee is '+areaPermissibleWOAddnlFee+', Do you want continue construction in additional area with addtional cost of Rs.1000 per Sq.mt.If you want continue further please select Yes / No ?',
				buttons : {
					'cancel' : {
						label : 'No',
						className : 'btn-danger pull-right'
					},
					'confirm' : {
						label : 'Yes',
						className : 'btn-danger pull-right'
					}
				},
				callback : function(result) {
					if (result) {
						//
					} else {
						$('#totalPlintArea').val('');
						e.stopPropagation();
						e.preventDefault();
					}
				}
			});
		}
});

function getOccupancyObject() {
	extentOfLand = $('#extentOfLand').val();
	extentInSqmts = $('#extentinsqmts').val();
	totalPlintArea = $('#totalPlintArea').val();
	var occpancyId = $('#occupancy').val();
		if(!occpancyId){
			bootbox.alert("Please select occpancy type");
			return false;
		}
	return occupancyResponse[occpancyId];
}

// Floor wise plinth area validations
function validateFloorDetails(plinthArea){
	var occpancyObj = getOccupancyObject();
	if(!extentOfLand){
		bootbox.alert("Please enter extend of land area value");
		return false;
	}
	if(!totalPlintArea){
		bootbox.alert("Please enter total plinth area value");
		return false;
	}
	var inputPlinthArea = $(plinthArea).val();
	var permissibleAreaInPercentage = occpancyObj[0].permissibleAreaInPercentage;
	var permissibleAreaForFloor = extentInSqmts * permissibleAreaInPercentage / 100;
	if(parseInt(inputPlinthArea) > parseInt(permissibleAreaForFloor)){
		$(plinthArea).val('');
		bootbox.alert("For type of " +occpancyObj[0].description+", each floor wise maximum permissable plinth area is " +permissibleAreaForFloor+", so beyond of maximum floor wise area you can't construct your building.");
		return false;
	}
	if(parseInt($("#sumOfPlinthArea").val()) > parseInt(totalPlintArea)){
		$(plinthArea).val('');
		bootbox.alert("Sum of floor wise plinth area is exceeding the total plinth area of you entered, please check and enter valid data.");
		return false;
	}
	$( ".plinthArea" ).trigger( "change" );
	$( ".carpetArea" ).trigger( "change" );
}

$('.for-calculation').change(function(){
	getOccupancyObject();
	$("#extentinsqmts").val(convertExtendOfLandToSqmts(extentOfLand, $("#unitOfMeasurement").val()));
	$("#extentinsqmtshdn").val(convertExtendOfLandToSqmts(extentOfLand, $("#unitOfMeasurement").val()));
});

function convertExtendOfLandToSqmts(extentOfLand,uom){
	var extentinsqmts;
	if(uom == 'ARE') {
		extentinsqmts = extentOfLand * 100;
	} else if(uom == 'HECTARE') {
		extentinsqmts = extentOfLand * 10000;
	} else {
		extentinsqmts = extentOfLand;
	}
	return extentinsqmts;
}

