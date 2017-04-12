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

$(document).ready( function () {
	
	//For time picker
	$(function () {
        $('#appointmentTime').datetimepicker({
            format: 'LT',
            disabledHours: [0,1,2,3,4,5,6,7,8,9,18,19,20,21,22,23],
            minDate: moment({h:10}),
            maxDate: moment({h:17})
        });
    });
	//re-schedule appointment date validation
	$('#appointmentDate').on('changeDate', function() {
		var  currDate = $('#appointmentDate').val();
		var  prevDate = $('#previoueappointmentDate').val();
		var datearray = prevDate.split("-");
		var newPrevdate = datearray[2] + '/' + datearray[1] + '/' + datearray[0];
		if(currDate <= newPrevdate){
			bootbox.alert('Re-schedule date should be greater than the previous scheduled date.');
			$('#appointmentDate').val('').datepicker("refresh");
		}
		});
});

