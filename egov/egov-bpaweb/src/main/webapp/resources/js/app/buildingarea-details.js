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

jQuery(document).ready(function() {
	
	// on page load to calculate sum of floor, plinth and carpet area
	 $( ".plinthArea" ).trigger( "change" );
	 $( ".carpetArea" ).trigger( "change" );
	 $( ".floorArea" ).trigger( "change" );
	 //setFloorCount();
	  
	var tbody = $('#buildingAreaDetails').children('tbody');
	var table = tbody.length ? tbody : $('#buildingAreaDetails');
	var row = '<tr>'+
	'<td class="text-center"><span class="serialNo text-center" id="slNoInsp">{{sno}}</span><input type="hidden" name="buildingDetail[0].applicationFloorDetails[{{idx}}].orderOfFloor" value"{{sno}}"/></td>'+
	'<td ><select name="buildingDetail[0].applicationFloorDetails[{{idx}}].floorDescription" data-first-option="false" id="applicationFloorDetails[{{idx}}]floorDescription" class="form-control floor-details-mandatory floorDescription clear-details" required="required" maxlength="128" > <option value="">Select</option><options items="${buildingFloorList}" /></select></td>'+
	'<td class="text-right"><input type="text" class="form-control table-input text-center patternvalidation floorNumber clear-details" data-pattern="number" name="buildingDetail[0].applicationFloorDetails[{{idx}}].floorNumber" id="applicationFloorDetails[{{idx}}]floorNumber" maxlength="3" /></td>'+
	'<td ><select name="buildingDetail[0].applicationFloorDetails[{{idx}}].occupancy" data-first-option="false" id="applicationFloorDetails[{{idx}}]occupancy" class="form-control floor-details-mandatory occupancy clear-details" required="required" maxlength="128" > <option value="">Select</option><options items="${occupancyList}" /></select></td>'+
	'<td class="text-right"><input type="text" class="form-control table-input text-right patternvalidation plinthArea floor-details-mandatory" data-pattern="decimalvalue" name="buildingDetail[0].applicationFloorDetails[{{idx}}].plinthArea" id="applicationFloorDetails[{{idx}}]plinthArea" required="required" maxlength="10" /></td>'+
	'<td class="text-right"><input type="text" class="form-control table-input text-right patternvalidation floorArea floor-details-mandatory" data-pattern="decimalvalue" name="buildingDetail[0].applicationFloorDetails[{{idx}}].floorArea" id="applicationFloorDetails[{{idx}}]floorArea" maxlength="10" required="required" onblur="validateFloorDetails(this)"  /></td>'+
	'<td class="text-right"><input type="text" class="form-control table-input text-right patternvalidation carpetArea floor-details-mandatory" data-pattern="decimalvalue" name="buildingDetail[0].applicationFloorDetails[{{idx}}].carpetArea" id="applicationFloorDetails[{{idx}}]carpetArea" maxlength="10" required="required" value=""  /></td>'+
	'<td class="text-center"><a href="javascript:void(0);" class="btn-sm btn-danger" id="deleteBuildAreaRow" data-record-id="${var1.id}"><i class="fa fa-trash"></i></a></td>'+
	'</tr>';
	
	$('#addBuildAreaRow').click(function(){
		if(validateBuildAreaOnAdd()){
			var idx=$(tbody).find('tr').length;
			var sno=idx+1;
			//Add row
			var row={
			       'sno': sno,
			       'idx': idx
			   };
			addRowFromObject(row);
			loadFloorlist("buildingDetail[0].applicationFloorDetails["+idx+"].floorDescription");
			loadOccupanctyDetails("buildingDetail[0].applicationFloorDetails["+idx+"].occupancy");
		}
	});
	
	function addRowFromObject(rowJsonObj)
	{
		table.append(row.compose(rowJsonObj));
		setFloorCount();
	}
	
	String.prototype.compose = (function (){
		   var re = /\{{(.+?)\}}/g;
		   return function (o){
		       return this.replace(re, function (_, k){
		           return typeof o[k] != 'undefined' ? o[k] : '';
		       });
		   }
	}());
	
	
	function loadFloorlist(selectBoxName){
		var floorList = $('#buildingFloorList').val();
		var floorListStringArry = floorList.substring(1, floorList.length-1);
		var floorDesc = floorListStringArry.split(',');
					$.each(floorDesc, function(index, floorDesc) {
						$('select[name="'+selectBoxName+'"]').append($('<option>').val(floorDesc).text(floorDesc));
					});
		}
	
	function loadOccupanctyDetails(selectBoxName){
		
		 $.ajax({
				url: "/bpa/application/getoccupancydetails",     
				type: "GET",
				async: false,
				dataType: "json",
				success: function (response) {
					$('select[name="'+selectBoxName+'"]').empty();
					$('select[name="'+selectBoxName+'"]').append($("<option value=''>Select </option>"));
					$.each(response, function(index, occupancy) {
						$('select[name="'+selectBoxName+'"]').append($('<option>').val(occupancy.id).text(occupancy.description));
					});
				}, 
				error: function (response) {
				}
			});
		}
	

	$(document).on('blur','.floorNumber', function() {
		var rowObj = $(this).closest('tr');
		validateUniqueDetails(rowObj.index(),$(rowObj).find('.floorDescription').val(), $(rowObj).find('.floorNumber').val(), $(rowObj).find('.occupancy').val());
	}); 
	
	$(document).on('change','.floorDescription, .occupancy', function() {
		var rowObj = $(this).closest('tr');
		validateUniqueDetails(rowObj.index(),$(rowObj).find('.floorDescription').val(), $(rowObj).find('.floorNumber').val(), $(rowObj).find('.occupancy').val());
	});
	
});


function validateUniqueDetails(idx,floorDesc,level,occupancy){
	if(floorDesc && occupancy) {
		$('#buildingAreaDetails tbody tr').each(function(index){
			
			if(idx===index)
				return;
			
			var floorName  = $(this).find('*[name$="floorDescription"]').val();
		    var floorNumber = $(this).find('*[name$="floorNumber"]').val();
		    var occupancy1 = $(this).find('*[name$="occupancy"]').val();
		    if(floorDesc === floorName && level === floorNumber && occupancy ===occupancy1) {
		    	$('#buildingAreaDetails tbody tr:eq('+idx+')').find('.clear-details').val('');
		    	bootbox.alert('With combination of Floor Description : '+floorDesc+', Level : '+level+' and Occupancy TYpe : '+$(this).find('*[name$="occupancy"] option:selected').text()+' are already present with existing entered details, please check and enter valid values.');
			    return false;
		    }
		});
	}
}

function setFloorCount() {
	$("#floorCount").val($('#buildingAreaDetails tbody tr').length);
}

var plinthAreaSum = 0;
var carpetAreaSum = 0;


$(document).on('change', '.plinthArea', function() {
    var totalPlinth = 0;
   /* var rowPlinthArea = 0;
    var rowFloorArea = 0;
    var rowObj = $(this).closest('tr');*/
    $("#buildingAreaDetails tbody tr").each(function () {
    	/*rowFloorArea = parseFloat($(this).find('td:eq(3) input.floorArea').val());
    	rowPlinthArea = parseFloat($(this).find('td:eq(4) input.plinthArea').val());
    	 if(rowPlinthArea > rowFloorArea) {
    		 $(rowObj).find('.plinthArea').val('');
    		 $( ".floorArea" ).trigger( "change" );
    		 $( ".plinthArea" ).trigger( "change" );
    		 bootbox.alert("Please enter valid values, Builtup Area should be less than or equal to the Floor Area.");
    		 return false;
    	 }*/
    	 if($(this).find('td:eq(4) input.plinthArea').val())
    		 totalPlinth +=  parseFloat($(this).find('td:eq(4) input.plinthArea').val());
    });
    var seviceTypeName = $( "#serviceType option:selected" ).text();
	if(totalPlinth && seviceTypeName && 'Alteration' != seviceTypeName && 'Adding of Extension' != seviceTypeName 
			&& 'Huts and Sheds' != seviceTypeName) {
		$("#totalPlintArea").val(totalPlinth.toFixed(2));
	}
    $("#buildingAreaDetails tfoot tr td:eq(4)").html(totalPlinth.toFixed(2));
});

$(document).on('change', '.floorArea', function() {
    var totalFloorArea = 0;
    $("#buildingAreaDetails tbody tr").each(function () {
    	if($(this).find('td:eq(5) input.floorArea').val())
    	 totalFloorArea +=  parseFloat($(this).find('td:eq(5) input.floorArea').val());
    });
    if(totalFloorArea) {
    	$("#sumOfFloorArea").val(totalFloorArea.toFixed(2));
    	$("#buildingAreaDetails tfoot tr td:eq(5)").html(totalFloorArea.toFixed(2));
    }
    
});

$(document).on('change', '.carpetArea', function() {
     var totalCarpet = 0;
    /* var rowPlinthArea = 0;
     var rowCarpetArea = 0;
     var rowObj = $(this).closest('tr');*/
     $("#buildingAreaDetails tbody tr").each(function () {
    	 /*rowPlinthArea = parseFloat($(this).find('td:eq(4) input.plinthArea').val());
    	 rowCarpetArea = parseFloat($(this).find('td:eq(5) input.carpetArea').val());
    	 if(rowCarpetArea > rowPlinthArea) {
    		 $(rowObj).find('.carpetArea').val('');
    		 $( ".plinthArea" ).trigger( "change" );
    		 $( ".carpetArea" ).trigger( "change" );
    		 bootbox.alert("Please enter valid values, Carpet Area should be less than the Builtup Area.");
    		 return false;
    	 }*/
    	 if($(this).find('td:eq(6) input.carpetArea').val())
    	 totalCarpet += parseFloat($(this).find('td:eq(6) input.carpetArea').val());
     });
     if(totalCarpet)
     $("#buildingAreaDetails tfoot tr td:eq(6)").html(totalCarpet.toFixed(2));
});
	
function generateSno()
{
	var idx=1;
	$(".serialNo").each(function(){
		$(this).text(idx);
		idx++;
	});
}

function validateBuildAreaOnAdd(){
	
	var isValid=true;
    $('#buildingAreaDetails tbody tr').each(function(index){
    	var floorName  = $(this).find('*[name$="floorDescription"]').val();
	    var plinthArea = $(this).find('*[name$="plinthArea"]').val();
	    var floorArea = $(this).find('*[name$="floorArea"]').val();
	    var carpetArea = $(this).find('*[name$="carpetArea"]').val();
	    var occupancy  = $(this).find('*[name$="occupancy"]').val();
	    if(!floorName || !plinthArea || !carpetArea || !floorArea || !occupancy) { 
	    	bootbox.alert("Please select floor description and occupancy type and enter values of builtup area, floor area and carpet area for existing rows before adding. Values cannot be 0 or empty.");
	    	isValid=false;
	    	return false;
	    } 
    });
   
    return isValid;
}


$(document).on('click',"#deleteBuildAreaRow",function (){
	
    var rowIndex = $(this).closest('td').parent()[0].sectionRowIndex;
	$(this).closest('tr').remove();	
	
	generateSno();
	
	  $("#buildingAreaDetails tbody tr").each(function() {
			$(this).find("input, select, hidden,textarea").each(function() {
				var index = $(this).closest('td').parent()[0].sectionRowIndex;
				if(index>=rowIndex){
					var increment = index++;
					$(this).attr({
						'name': function(_, name){
							return name.replace(/\d+/g,+increment);
						},
						'id': function(_, id){
							if(id==undefined){
								return "";
							}
							return id.replace(/\d+/g, +increment);
						}
					});
				}
				
			});
	 });
	  setFloorCount();
	// on delete to re-calculate sum of plinth and carpet area
	  $( ".floorArea" ).trigger( "change" );
	  $( ".plinthArea" ).trigger( "change" );
	  $( ".carpetArea" ).trigger( "change" );
	
});	
