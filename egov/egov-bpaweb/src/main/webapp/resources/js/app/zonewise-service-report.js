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

$(document)
		.ready(
				function() {
					
					$('#btnSearch').click(function() {
						callAjaxSearch();
					});

					function callAjaxSearch() {
						var from = $('#fromDate').val();
						var to = $('#toDate').val();
						var applicantName = $('#applicantName').val();
						var applicationNumber = $('#applicationNumber').val();
						var wardId = $('#wardId').val();
						var electionWardId = $('#electionWardId').val();
						var zoneId = $('#zone').val();
						var statusId = $('#statusId').val();
						
						$('.report-section').removeClass('display-hide');
						$("#searchZoneWiseServiceCount")
								.dataTable(
										{
											ajax : {
												url : "/bpa/reports/zonewisedetails",
												type : "POST",
												beforeSend : function() {
													$('.loader-class')
															.modal(
																	'show',
																	{
																		backdrop : 'static'
																	});
												},
												"data" : getFormData($('form')),
												"dataSrc": function ( json ) {
													json.data.forEach(function(item){
														item["rowTotal"] = item.zone1 + item.zone2 + item.zone3 + item.zone4;
													});
									                return json.data;
									            },   
												complete : function() {
													$('.loader-class').modal(
															'hide');
												}
											},
											"bDestroy" : true,
											"sDom" : "<'row'<'col-xs-12 hidden col-right'f>r>t<'row'<'col-xs-3'i><'col-xs-3 col-right'l><'col-xs-3 col-right'<'export-data'T>><'col-xs-3 text-right'p>>",
											"oTableTools" : {
												"sSwfPath" : "../../../../../../egi/resources/global/swf/copy_csv_xls_pdf.swf",
												"aButtons" : [ "xls", "pdf",
																"print" ]
											},
											aaSorting : [],
											columns : [
												{
													"data" : null,
													render : function(data, type, row, meta) {
														return meta.row
																+ meta.settings._iDisplayStart
																+ 1;
													},
													"sClass" : "text-center"
												},
													{
														"data" : "serviceType",
														"sClass" : "text-left"
													},
													{
														"data" : "zone1",
														 render : function(data, type, row, meta) {
															return parseInt(row.zone1)!==0? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType='+row.serviceType
																	+ '&'
																	+ 'applicantName='+applicantName
																	+ '&'
																	+ 'applicationNumber='+applicationNumber
																	+ '&'
																	+ 'fromDate='+from
																	+ '&'
																	+ 'toDate='+to
																	+ '&'
																	+ 'ward='+wardId
																	+ '&'
																	+ 'electionWard='+electionWardId
																	+ '&'
																	+ 'zone=ZONE-1'
																	+ '&'
																	+ 'zoneId='+zoneId
																	+ '&'
																	+ 'status='+statusId
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.zone1 + '</a>':row.zone1;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "zone2",
														render : function(data, type, row, meta) {
															return parseInt(row.zone2)!==0? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType='+row.serviceType
																	+ '&'
																	+ 'applicantName='+applicantName
																	+ '&'
																	+ 'applicationNumber='+applicationNumber
																	+ '&'
																	+ 'fromDate='+from
																	+ '&'
																	+ 'toDate='+to
																	+ '&'
																	+ 'ward='+wardId
																	+ '&'
																	+ 'electionWard='+electionWardId
																	+ '&'
																	+ 'zone=ZONE-2'
																	+ '&'
																	+ 'zoneId='+zoneId
																	+ '&'
																	+ 'status='+statusId
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.zone2 + '</a>':row.zone2;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "zone3",
														render : function(data, type, row, meta) {
															return parseInt(row.zone3)!==0 ? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType='+row.serviceType
																	+ '&'
																	+ 'applicantName='+applicantName
																	+ '&'
																	+ 'applicationNumber='+applicationNumber
																	+ '&'
																	+ 'fromDate='+from
																	+ '&'
																	+ 'toDate='+to
																	+ '&'
																	+ 'ward='+wardId
																	+ '&'
																	+ 'electionWard='+electionWardId
																	+ '&'
																	+ 'zone=ZONE-3'
																	+ '&'
																	+ 'zoneId='+zoneId
																	+ '&'
																	+ 'status='+statusId
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.zone3 + '</a>':row.zone3;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "zone4",
														render : function(data, type, row, meta) {
															return parseInt(row.zone4)!== 0 ? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType='+row.serviceType
																	+ '&'
																	+ 'applicantName='+applicantName
																	+ '&'
																	+ 'applicationNumber='+applicationNumber
																	+ '&'
																	+ 'fromDate='+from
																	+ '&'
																	+ 'toDate='+to
																	+ '&'
																	+ 'ward='+wardId
																	+ '&'
																	+ 'electionWard='+electionWardId
																	+ '&'
																	+ 'zone=ZONE-4'
																	+ '&'
																	+ 'zoneId='+zoneId
																	+ '&'
																	+ 'status='+statusId
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.zone4 + '</a>':row.zone4;
														},
														"sClass" : "text-center"
													},
													{
														"data":"rowTotal",
														"sClass" : "text-center"
													}],
													
													"footerCallback" : function(row, data, start, end, display) {
														var api = this.api(), data;
														if (data.length == 0) {
															jQuery('#report-footer').hide();
														} else {
															jQuery('#report-footer').show(); 
														}
														if (data.length > 0) {
															updateTotalFooter(2, api);
															updateTotalFooter(3, api);
															updateTotalFooter(4, api);
															updateTotalFooter(5, api);
															updateTotalFooter(6, api);
															
															}
													},
													"aoColumnDefs" : [ {
														"aTargets" : [2,3,4,5,6],
														"mRender" : function(data, type, full) {
															return formatNumberInr(data);    
														}
													} ]	
										});
					}
					
					
					$(document).on('click','.dropchange',function(){
					    var url = $(this).val();
					    if(url){
					    	openPopup(url);
					    }
					    
					});

					function openPopup(url)
					{
						window.open(url,'window','scrollbars=yes,resizable=yes,height=700,width=800,status=yes');
					}

				});

function openPopup(url) {
	window.open(url, 'window',
			'scrollbars=1,resizable=yes,height=600,width=800,status=yes');

}

function getFormData($form) {
	var unindexed_array = $form.serializeArray();
	var indexed_array = {};

	$.map(unindexed_array, function(n, i) {
		indexed_array[n['name']] = n['value'];
	});

	return indexed_array;
}

function updateTotalFooter(colidx, api) {
	// Remove the formatting to get integer data for summation
	var intVal = function(i) {
		return typeof i === 'string' ? i.replace(/[\$,]/g, '') * 1
				: typeof i === 'number' ? i : 0;
	};

	// Total over all pages
	
	if (api.column(colidx).data().length){
        var total = api
        .column(colidx )
        .data()
        .reduce( function (a, b) {
        return intVal(a) + intVal(b);
        } ) }
        else{ total = 0};

	// Total over this page
	
	if (api.column(colidx).data().length){
        var pageTotal = api
            .column( colidx, { page: 'current'} )
            .data()
            .reduce( function (a, b) {
                return intVal(a) + intVal(b);
            } ) }
            else{ pageTotal = 0};

	// Update footer
	jQuery(api.column(colidx).footer()).html(
			formatNumberInr(pageTotal) + ' (' + formatNumberInr(total)
					+ ')');
}


//inr formatting number
function formatNumberInr(x) {
	if (x) {
		x = x.toString();
		var afterPoint = '';
		if (x.indexOf('.') > 0)
			afterPoint = x.substring(x.indexOf('.'), x.length);
		x = Math.floor(x);
		x = x.toString();
		var lastThree = x.substring(x.length - 3);
		var otherNumbers = x.substring(0, x.length - 3);
		if (otherNumbers != '')
			lastThree = ',' + lastThree;
		var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",")
				+ lastThree + afterPoint;
		return res;
	}
	return x;
}
