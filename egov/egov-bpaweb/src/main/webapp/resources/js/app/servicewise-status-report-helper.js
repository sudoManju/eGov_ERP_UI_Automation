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
						
						var commonUrl;
						
						$('.report-section').removeClass('display-hide');
						$("#searchStatusByServiceTypeReportTable")
								.dataTable(
										{
											ajax : {
												url : "/bpa/reports/servicewise-statusreport",
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
														item["rowTotal"] = item.serviceType01 + item.serviceType02 +item.serviceType03 +item.serviceType04 +item.serviceType05 +item.serviceType06 +item.serviceType07;
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
														"data" : "status",
														"sClass" : "text-left"
													},
													{
														"data" : "serviceType01",
														 render : function(data, type, row, meta) {
															return parseInt(row.serviceType01)!==0? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType=01'
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
																	+ 'zone='+zoneId
																	+ '&'
																	+ 'status='+row.status
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.serviceType01 + '</a>':row.serviceType01;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "serviceType02",
														render : function(data, type, row, meta) {
															return parseInt(row.serviceType02)!==0? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType=02'
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
																	+ 'zone='+zoneId
																	+ '&'
																	+ 'status='+row.status
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.serviceType02 + '</a>':row.serviceType02;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "serviceType03",
														render : function(data, type, row, meta) {
															return parseInt(row.serviceType03)!==0 ? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType=03'
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
																	+ 'zone='+zoneId
																	+ '&'
																	+ 'status='+row.status
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.serviceType03 + '</a>':row.serviceType03;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "serviceType04",
														render : function(data, type, row, meta) {
															return parseInt(row.serviceType04)!== 0 ? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType=04'
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
																	+ 'zone='+zoneId
																	+ '&'
																	+ 'status='+row.status
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.serviceType04 + '</a>':row.serviceType04;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "serviceType05",
														render : function(data, type, row, meta) {
						    									return parseInt(row.serviceType05)!== 0 ? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType=05'
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
																	+ 'zone='+zoneId
																	+ '&'
																	+ 'status='+row.status
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.serviceType05 + '</a>':row.serviceType05;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "serviceType06",
														render : function(data, type, row, meta) {
															return parseInt(row.serviceType06)!== 0 ? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType=06'
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
																	+ 'zone='+zoneId
																	+ '&'
																	+ 'status='+row.status
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.serviceType06 + '</a>':row.serviceType06;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "serviceType07",
														render : function(data, type, row, meta) {
															return parseInt(row.serviceType07)!== 0 ? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType=07'
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
																	+ 'zone='+zoneId
																	+ '&'
																	+ 'status='+row.status
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.serviceType07 + '</a>':row.serviceType07;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "serviceType08",
														render : function(data, type, row, meta) {
															return parseInt(row.serviceType08)!== 0 ? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType=08'
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
																	+ 'zone='+zoneId
																	+ '&'
																	+ 'status='+row.status
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.serviceType08 + '</a>':row.serviceType08;
														},
														"sClass" : "text-center"
													},
													{
														"data" : "serviceType09",
														render : function(data, type, row, meta) {
															return parseInt(row.serviceType09)!==0 ? '<a onclick="openPopup(\'/bpa/reports/servicewise-statusreport/view?'
																	+ 'serviceType=09'
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
																	+ 'zone='+zoneId
																	+ '&'
																	+ 'status='+row.status
																	+ '&'
																	+ 'revenueWard='+wardId
																	+ '\')" href="javascript:void(0);">'
																	+ row.serviceType09 + '</a>':row.serviceType09;
														},
														"sClass" : "text-center"
													},{
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
															updateTotalFooter(7, api);
															updateTotalFooter(8, api);
															updateTotalFooter(9, api);
															updateTotalFooter(10, api);
															updateTotalFooter(11, api);
															}
													},
													"aoColumnDefs" : [ {
														"aTargets" : [2,3,4,5,6,7,8,9,10,11],
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
