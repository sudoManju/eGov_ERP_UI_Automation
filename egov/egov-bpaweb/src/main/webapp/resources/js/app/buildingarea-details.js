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
	
	// on page load to calculate sum of plinth and carpet area
	 $( ".plinthArea" ).trigger( "change" );
	 $( ".carpetArea" ).trigger( "change" );
	  
	var tbody = $('#buildingAreaDetails').children('tbody');
	var table = tbody.length ? tbody : $('#buildingAreaDetails');
	var row = '<tr>'+
	'<td class="text-center"><span class="serialNo" id="slNoInsp">{{sno}}</span></td>'+
	'<td ><input type="text" class="form-control table-input text-right patternvalidation" name="buildingDetail[0].applicationFloorDetails[{{idx}}].floorDescription" id="applicationFloorDetails[{{idx}}]floorDescription" maxlength="128" value="" /></td>'+
	'<td class="text-right"><input type="text" class="form-control table-input patternvalidation plinthArea" data-pattern="number" name="buildingDetail[0].applicationFloorDetails[{{idx}}].plinthArea" id="applicationFloorDetails[{{idx}}]plinthArea" maxlength="15"  /></td>'+
	'<td class="text-right"><input type="text" class="form-control table-input text-right patternvalidation carpetArea" data-pattern="number" name="buildingDetail[0].applicationFloorDetails[{{idx}}].carpetArea" id="applicationFloorDetails[{{idx}}]carpetArea" maxlength="15" value=""  /></td>'+
	'<td class="text-center"><a href="javascript:void(0);" class="btn-sm btn-danger" id="deleteBuildAreaRow" data-record-id="${var1.id}"><i class="fa fa-trash"></i></a></td>'+
	'</tr>';

	jQuery('#addBuildAreaRow').click(function(){
		var idx=$(tbody).find('tr').length;
		var sno=idx+1;
		//Add row
		var row={
		       'sno': sno,
		       'idx': idx
		   };
		addRowFromObject(row);
	});
	
	function addRowFromObject(rowJsonObj)
	{
		table.append(row.compose(rowJsonObj));
	}
	
	String.prototype.compose = (function (){
		   var re = /\{{(.+?)\}}/g;
		   return function (o){
		       return this.replace(re, function (_, k){
		           return typeof o[k] != 'undefined' ? o[k] : '';
		       });
		   }
	}());
});

	var plinthAreaSum = 0;
	var carpetAreaSum = 0;
	var totalSum = 0;
$(document).on('change', '.plinthArea', function() {
     var totalPlinth = 0;
     $("#buildingAreaDetails tbody tr").each(function () {
    	 totalPlinth +=  parseInt($(this).find('td:eq(2) input.plinthArea').val());
     });
     $("#buildingAreaDetails tfoot tr td:eq(2)").html(totalPlinth);
     plinthAreaSum = $("#buildingAreaDetails tfoot tr td:eq(2)").html();
     carpetAreaSum = $("#buildingAreaDetails tfoot tr td:eq(3)").html();
     totalSum = parseInt(plinthAreaSum == '' ? 0 : plinthAreaSum) + parseInt(carpetAreaSum == '' ? 0 : carpetAreaSum);
     $("#sumOfPlinthCarpet").html(totalSum);
});
	 
$(document).on('change', '.carpetArea', function() {
     var totalCarpet = 0;
     $("#buildingAreaDetails tbody tr").each(function () {
    	 totalCarpet +=  parseInt($(this).find('td:eq(3) input.carpetArea').val());
     });
     $("#buildingAreaDetails tfoot tr td:eq(3)").html(totalCarpet);
     plinthAreaSum = $("#buildingAreaDetails tfoot tr td:eq(2)").html();
     carpetAreaSum = $("#buildingAreaDetails tfoot tr td:eq(3)").html();
     totalSum = parseInt(plinthAreaSum == '' ? 0 : plinthAreaSum) + parseInt(carpetAreaSum == '' ? 0 : carpetAreaSum);
     $("#sumOfPlinthCarpet").html(totalSum);
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
	
	var tbl=document.getElementById("buildingAreaDetails");
    var lastRow = tbl.rows.length-3;
    
    var floorName = $('*[name="bpaApplication.buildingDetail[0].applicationFloorDetails['+lastRow+'].floorName"]').val();
    var plinthArea= $('*[plinthArea="bpaApplication.buildingDetail[0].applicationFloorDetails['+lastRow+'].plinthArea"]').val();
    var carpetArea = $('*[pipeLength="bpaApplication.buildingDetail[0].applicationFloorDetails['+lastRow+'].carpetArea"]').val();    
    if(!floorName || !plinthArea || !carpetArea) { 
    	bootbox.alert("Enter all mandatory values for existing rows before adding. Values cannot be 0 or empty.");
    	return false;
    } 
    return true;
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
	// on delete to re-calculate sum of plinth and carpet area
	  $( ".plinthArea" ).trigger( "change" );
	  $( ".carpetArea" ).trigger( "change" );
	
});	