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
var extentOfLand;
var totalFloorArea;
var extentInSqmts;
$(document).ready(function() {
	
	$('.buildingdetails').hide();
	$('.show-hide').hide();
	$('.totalPlintArea').show();
	
	$('.decimalfixed').each(function(){
		if($(this).val()){
			$(this).val(parseFloat($(this).val()).toFixed(2));
		}
	});
	
	
	// Validate input value must be greater than zero using class name
	$.validator.addMethod("nonzero", function(value, element) {
		return this.optional(element) || (parseFloat(value) > 0);
		}, '* Value must be greater than zero');
	
	// pincode validation
	$.validator.addMethod("searchpincode", function(value, element) {
		return this.optional(element) || value !== '';
		}, 'required');
	
	// To prevent multiple decimal places in single input
	$(document).on('keypress', '.decimalfixed', function(evt) {
		
		  evt = (evt) ? evt : window.event;
		  var charCode = (evt.which) ? evt.which : evt.keyCode;
		  if (charCode == 8 || charCode == 37) {
		    return true;
		  } else if (charCode == 46 && $(this).val().indexOf('.') != -1) {
		    return false;
		  } else if (charCode > 31 && charCode != 46 && (charCode < 48 || charCode > 57)) {
		    return false;
		  }
		  return true;
	});
	
	// government or quasi validation
	$('#isEconomicallyWeakerSec').hide();
	$('.governmentType').on('change', function() {
		 var govType = $("input[name='governmentType']:checked").val();
	        if(govType == 'NOT_APPLICABLE'){
	        	$('#isEconomicallyWeakerSec').hide();
	        } else {
	        	$('#isEconomicallyWeakerSec').show();
	        }
		});
	
	$('.governmentType').trigger("change");
	// TOWN PLANNING SCHEME VALIDATION
	$('#schemes').change(function(){
		var scheme = $( "#schemes option:selected" ).val();
		if(scheme) {
			$('#landUsage').attr('required',true);
			$('.landUsage').find("span").addClass( "mandatory" );
		} else {
			$('#landUsage').removeAttr('required');
			$('.landUsage').find("span").removeClass( "mandatory" );
		}
	});
	
	// For each main service type validations
	$('.serviceType').change(function(){
		
		loadAmenities();
		
		var seviceTypeName = $( "#serviceType option:selected" ).text();
		$('.doorNo').show();
		$('.show-hide').hide();
		$('.floor-toggle-mandatory').find("span").removeClass( "mandatory" );
		$('.floor-details-mandatory').removeAttr('required');
		$('.areaOfBase').hide();
		$('.extentOfLand').show();
		if('Sub-Division of plot/Land Development'.localeCompare(seviceTypeName) == 0 ){
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
			$('.buildingdetails').hide();
		} else if('Tower Construction'.localeCompare(seviceTypeName) == 0 || 'Pole Structures'.localeCompare(seviceTypeName) == 0){
			$('.extentOfLand').hide();
			$('.areaOfBase').show();
			$('.extentOfLand').find("span").removeClass( "mandatory" );
			$('#extentOfLand').removeAttr('required');
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
			$('.buildingdetails').hide();
		} else if('Amenities' == seviceTypeName){
			$('.buildingdetails').hide();
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
		} else if('Huts and Sheds' == seviceTypeName){
			$('.buildingdetails').hide();
			$('.noofhutorshed').show();
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
			$('.Hut').find("span").addClass( "mandatory" );
			$('.noofhutorshed').find("span").addClass( "mandatory" );
		} else if('Alteration' == seviceTypeName){
			$('.buildingdetails').show();
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
			$('.alterationInArea').find("span").addClass( "mandatory" );
			$('#totalPlintArea').attr('required',true);
			$('#totalPlintArea').attr('readOnly',false);
			$('.alterationInArea').show();
		} else if('Adding of Extension' == seviceTypeName){
			$('.buildingdetails').show();
			$('.handle-mandatory').removeAttr('required');
			$('.handle-mandatory').find("span").removeClass( "mandatory" );
			$('#totalPlintArea').attr('required',true);
			$('#totalPlintArea').attr('readOnly',false);
			$('.additionInArea').find("span").addClass( "mandatory" );
			$('.additionInArea').show();
		} else {
			if('New Construction'.localeCompare(seviceTypeName) == 0 ){
				$('.buildingdetails').show();
				$('.totalPlintArea').show();
				$('.doorNo').hide();
			} else if('Reconstruction'.localeCompare(seviceTypeName) == 0 ){
				$('.buildingdetails').show();
				$('.totalPlintArea').show();
			}  else if('Change in occupancy' == seviceTypeName){
				$('.buildingdetails').show();
				$('.changeInOccupancyArea').show();
			} else if ('Demolition' == seviceTypeName){
				$('.buildingdetails').show();
				$('.demolition').show();
			} else {
				$('.totalPlintArea').show();
			}
			$('.floor-toggle-mandatory').find("span").addClass( "mandatory" );
			$('.floor-details-mandatory').attr('required',true);
			$('.extentOfLand').find("span").addClass( "mandatory" );
			$('#extentOfLand').attr('required');
			$('.handle-mandatory').attr('required',true);
			$('.handle-mandatory').find("span").addClass( "mandatory" );
			$('#totalPlintArea').attr('readOnly',true);
		}
	});
	
	// Each Amenity type validations
	
	$(document).on('change',"#applicationAmenity",function (){ 
		loadAmenities();
	});
	
	function loadAmenities(){
		
		var amenities = [];
		
		if($( "#serviceType option:selected" ).text() == 'Huts and Sheds'){
			amenities.push('Huts and Sheds');
		} else if($( "#serviceType option:selected" ).text() == 'Tower Construction'){
			amenities.push('Tower Construction');
		} else if($( "#serviceType option:selected" ).text() == 'Pole Structures'){
			amenities.push('Pole Structures');
		}
		
		$.each($("#applicationAmenity option:selected"), function(idx){     
			amenities.push($(this).text());
		});
		
		
		var result="";
		$.each(amenities, function(idx, value){            
			//console.log('is even?', $(this).text(), idx, );
			var isEven=(parseInt(idx)%2 === 0);
			if(isEven){
				result= result+ (result?"</div><div class='form-group'>":"<div class='form-group'>");
			}
			result=result+getTemplateComplie(value, isEven);
        });
		result=result+"</div>";
		$('#amenitiesInputs').html(result);
		patternvalidation();
	}
	
	function getTemplateComplie(value, isFirstPosition){
		var templateStr="";
		
		switch(value) {
		    case "Well":
		        templateStr=$('#well-template').html();
		        break;
		    case "Compound Wall":
		    	templateStr=$('#compound-template').html();
		        break;
		    case "Shutter or Door Conversion/Erection under rule 100 or 101":
		    	templateStr=$('#shutter-template').html();
		        break;
		    case "Roof Conversion under rule 100 or 101":
		    	templateStr=$('#roof-template').html();
		        break;
		    case "Tower Construction":
		    	templateStr=$('#tower-template').html();
		        break;
		    case "Pole Structures":
		    	templateStr=$('#poles-template').html();
		        break;
		    case "Huts and Sheds":
		    	templateStr=$('#sheds-template').html();
		        break;
		}
		
		return templateStr.replace(/{className}/g, isFirstPosition?'col-sm-3':'col-sm-2');
	}
	
	// making atleast one amenity should be mandatory if service type is selected as amenities 
	$('#buttonSubmit, #bpaCreate').click(function(){
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
			   $('#approvedFeeAmount').attr('required', true);
			   $('#revisedPermitNumber').attr('required', true);
		    } else if(!this.checked) { // if changed state is "CHECKED" 
			  $('.removemandatory').find("span").removeClass( "mandatory" );
			  $('#feeAmountRecieptNo').attr('required', false);
			  $('#approvedReceiptDate').attr('required', false);
			  $('#approvedFeeAmount').attr('required', false);
			  $('#revisedPermitNumber').attr('required', false);
			  $('#existingAppPlan').hide();
		    }
		});
	
	$('#isappForRegularization').on('change', function(){ 
		   if(this.checked) // if changed state is "CHECKED"
		    {
			   $('.constStages').find("span").addClass( "mandatory" );
			   $('#constStages').attr('required', true);
			   $('#constDiv').show();
			   $('#inprogress').hide();
			   $('#constStages').attr('required', true);
			   $('#constStages').change(function(){
					if($('#constStages option:selected').html()=="In Progress" ||  $(this).val()=="-1"){
						$('#inprogress').show();
						$('.stateOfConstruction').find("span").addClass( "mandatory" );
						 $('#stateOfConstruction').attr('required', true);
					} else {
						 $('#inprogress').hide();
					}
				});
		    } else if(!this.checked) // if changed state is "CHECKED"
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
	
	
	$('#extentOfLand,#unitOfMeasurement').change(function(){
		var extentOfLand = $('#extentOfLand').val();
		var uom = $('#unitOfMeasurement').val();
		if(extentOfLand && uom && convertExtendOfLandToSqmts(extentOfLand, uom) > 1000000) {
			$('#extentOfLand').val('');
			$('#extentinsqmts').val('');
			bootbox.alert("Maximum allowed area of extend land upto 10 lakhs Sq.Mtrs only.");
		}
	});
	
	$('#occupancy').change(function(){
		$( ".plinthArea" ).trigger( "change" );
		$( ".carpetArea" ).trigger( "change" );
		$( ".floorArea" ).trigger( "change" );
		//$('.clear-values').val('');
		$('#buildingAreaDetails').find('input').val('');
		$('#buildingAreaDetails').find('select').val('');
		getOccupancyObject();
	});
	
	$(document).on('blur', '.floorArea', function(e) {
		var seviceTypeName = $( "#serviceType option:selected" ).text();
		if(seviceTypeName && 'Alteration' != seviceTypeName && 'Adding of Extension' != seviceTypeName 
				&& 'Huts and Sheds' != seviceTypeName) {
			var rowObj = $(this).closest('tr');
			var occpancyObj = getOccupancyObject();
			if(occpancyObj) {
				var numOfTimesAreaPermissible = occpancyObj[0].numOfTimesAreaPermissible;
				var numOfTimesAreaPermWitAddnlFee = occpancyObj[0].numOfTimesAreaPermWitAddnlFee;
				var areaPermissibleWOAddnlFee = parseFloat(extentInSqmts) * parseFloat(numOfTimesAreaPermissible);
				var areaPermissibleWithAddnlFee = parseFloat(extentInSqmts) * parseFloat(numOfTimesAreaPermWitAddnlFee);
				totalFloorArea = $("#sumOfFloorArea").val();
					if(areaPermissibleWithAddnlFee == 0) {
						if(parseFloat(totalFloorArea) > areaPermissibleWOAddnlFee){
							 bootbox.alert("For the occupancy type of " +occpancyObj[0].description+", maximum permissible area is "+areaPermissibleWOAddnlFee+" Sq.Mtrs, beyond of permissible area you can't construct construction.");
							 $(rowObj).find('.floorArea').val('');
							 $( ".floorArea" ).trigger( "change" );
							 $( ".plinthArea" ).trigger( "change" );
				    		 $( ".carpetArea" ).trigger( "change" );
							return false;
						} else {
							return true;
						}
					} else if(parseFloat(totalFloorArea) > areaPermissibleWithAddnlFee) {
						 bootbox.alert("For the occupancy type of " +occpancyObj[0].description+", maximum permissible area allowed with addtional fee is "+areaPermissibleWithAddnlFee+" Sq.Mtrs, beyond of permissible area you can't construct construction.");
						 $(rowObj).find('.floorArea').val('');
						 $( ".floorArea" ).trigger( "change" );
						 $( ".plinthArea" ).trigger( "change" );
			    		 $( ".carpetArea" ).trigger( "change" );
						return false;
					} else if(parseFloat(totalFloorArea) > areaPermissibleWOAddnlFee) {
						bootbox
						.confirm({
							message : 'For the occupancy type of ' +occpancyObj[0].description+', maximum permissible area allowed with out addtional fee is '+areaPermissibleWOAddnlFee+' Sq.Mtrs, Do you want continue construction in additional area with addtional cost of Rs.1000 per Sq.Mtr.If you want continue further please select Yes / No ?',
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
									//
								} else {
									$(rowObj).find('.floorArea').val('');
									 $( ".floorArea" ).trigger( "change" );
									 $( ".plinthArea" ).trigger( "change" );
						    		 $( ".carpetArea" ).trigger( "change" );
									e.stopPropagation();
									e.preventDefault();
								}
							}
						});
					}
			}
		}
	});

	$('.for-calculation').change(function(){
		getOccupancyObject();
		$("#extentinsqmts").val(convertExtendOfLandToSqmts(extentOfLand, $("#unitOfMeasurement").val()));
		$("#extentinsqmtshdn").val(convertExtendOfLandToSqmts(extentOfLand, $("#unitOfMeasurement").val()));
	});
	
	// multi-select without pressing ctrl key
	$("select.tick-indicator").mousedown(function(e){
	    e.preventDefault();
	    
		var select = this;
	    var scroll = select.scrollTop;
	    
	    e.target.selected = !e.target.selected;
	    
	    $(this).trigger('change');
	    
	    setTimeout(function(){select.scrollTop = scroll;}, 0);
	    
	    $(select).focus();

	}).mousemove(function(e){e.preventDefault()});
	
	// trigger events on pageload
	$( "#isexistingApprovedPlan" ).trigger( "change" );
	$( "#isappForRegularization" ).trigger( "change" );
	$( "#constStages" ).trigger( "change" );
	$( ".serviceType" ).trigger( "change" );
	$( ".applicationAmenity" ).trigger( "change" );
	
});

function getOccupancyObject() {
	extentOfLand = $('#extentOfLand').val();
	extentInSqmts = $('#extentinsqmts').val();
	/*totalFloorArea = $('#totalFloorArea').val();*/
	var occpancyId = $('#occupancy').val();
		/*if(!occpancyId){
			bootbox.alert("Please select occpancy type");
			return false;
		}*/
	return occupancyResponse[occpancyId];
}


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

//Floor wise floor area validations
function validateFloorDetails(plinthArea) {
	var seviceTypeName = $( "#serviceType option:selected" ).text();
	if(seviceTypeName && 'Alteration' != seviceTypeName && 'Adding of Extension' != seviceTypeName 
			&& 'Huts and Sheds' != seviceTypeName) {	
		var occpancyObj = getOccupancyObject();
		if(!extentOfLand){
			bootbox.alert("Please enter extend of land area value");
			return false;
		}
		/*if(!totalFloorArea){
			bootbox.alert("Please enter total builtup area value");
			return false;
		}*/
		var inputPlinthArea = $(plinthArea).val();
		var permissibleAreaInPercentage = occpancyObj[0].permissibleAreaInPercentage;
		var permissibleAreaForFloor = extentInSqmts * permissibleAreaInPercentage / 100;
		if(parseFloat(inputPlinthArea) > parseFloat(permissibleAreaForFloor)){
			$(plinthArea).val('');
			bootbox.alert("For type of " +occpancyObj[0].description+", each floor wise maximum permissable floor area is " +permissibleAreaForFloor+" Sq.Mtrs, so beyond of maximum permissable floor wise area you can't construct your building.");
			return false;
		}
		/*if(parseFloat($("#sumOfFloorArea").val()) > parseFloat(totalFloorArea)){
			$(plinthArea).val('');
			bootbox.alert("Sum of floor wise floor area "+parseFloat($("#sumOfFloorArea").val())+" Sq.Mtrs is exceeding the total builtup area "+parseFloat(totalFloorArea)+" Sq.Mtrs of you entered, please check and enter valid data.");
			return false;
		}*/
		$( ".plinthArea" ).trigger( "change" );
		$( ".carpetArea" ).trigger( "change" );
	}
}

