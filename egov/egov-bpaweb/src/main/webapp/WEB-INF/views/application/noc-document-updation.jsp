<%--
  ~ eGov suite of products aim to improve the internal efficiency,transparency,
  ~    accountability and the service delivery of the government  organizations.
  ~
  ~     Copyright (C) <2017>  eGovernments Foundation
  ~
  ~     The updated version of eGov suite of products as by eGovernments Foundation
  ~     is available at http://www.egovernments.org
  ~
  ~     This program is free software: you can redistribute it and/or modify
  ~     it under the terms of the GNU General Public License as published by
  ~     the Free Software Foundation, either version 3 of the License, or
  ~     any later version.
  ~
  ~     This program is distributed in the hope that it will be useful,
  ~     but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~     GNU General Public License for more details.
  ~
  ~     You should have received a copy of the GNU General Public License
  ~     along with this program. If not, see http://www.gnu.org/licenses/ or
  ~     http://www.gnu.org/licenses/gpl.html .
  ~
  ~     In addition to the terms of the GPL license to be adhered to in using this
  ~     program, the following additional terms are to be complied with:
  ~
  ~         1) All versions of this program, verbatim or modified must carry this
  ~            Legal Notice.
  ~
  ~         2) Any misrepresentation of the origin of the material is prohibited. It
  ~            is required that all modified versions of this material be marked in
  ~            reasonable ways as different from the original version.
  ~
  ~         3) This license does not grant any rights to any user of the program
  ~            with regards to rights under trademark law for use of the trade names
  ~            or trademarks of eGovernments Foundation.
  ~
  ~   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
  --%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="panel-heading">
	<div class="panel-title">Status of NOC from the Following
		Departments.</div>
</div>

<div class="panel-body custom">
	<table class="table table-bordered  multiheadertbl" id=bpaupdatenocdetails>
		<thead>
			<tr>
				<th><spring:message code="lbl.srl.no" /></th>
				<th><spring:message code="lbl.department" /></th>
				<th><spring:message code="lbl.nature.noc.req" /></th>
				<th><spring:message code="lbl.letr.sent.on" /></th>
				<th><spring:message code="lbl.reply.recv.on" /></th>
				<th><spring:message code="lbl.noc.reject" /></th>
				<th><spring:message code="lbl.noc.not.aplicable" /></th>
				<th width="8%"><spring:message code="lbl.obtained" /></th>
				<th><spring:message code="lbl.remarks" /></th>
				<th><spring:message code="lbl.attachdocument" /><br>(<spring:message
						code="lbl.mesg.document" />)</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="doc" items="${nocCheckListDetails}"
				varStatus="status">
				<form:hidden
					path="applicationNOCDocument[${status.index}].application"
					id="applicationId" value="${bpaApplication.id}" />
				<form:hidden
					path="applicationNOCDocument[${status.index}].checklist"
					id="checklist" value="${doc.id}" />
				<form:hidden id="checkListDocumentsForNOC[${status.index}].id"
					path="checkListDocumentsForNOC[${status.index}].id"
					value="${doc.id}" />
				<tr>
					<td><c:out value="${status.index+1}"></c:out></td>
					<td><c:out value="${doc.description}"></c:out> <c:if
							test="${doc.isMandatory}">
							<span class="mandatory"></span>
						</c:if></td>
					<td>

						<div class="input-group">
							<form:textarea
								class="form-control patternvalidation textarea-content"
								data-pattern="string" maxlength="512"
								id="applicationNOCDocument${status.index}natureOfRequest"
								path="applicationNOCDocument[${status.index}].natureOfRequest" />
							<form:errors
								path="applicationNOCDocument[${status.index}].natureOfRequest"
								cssClass="add-margin error-msg" />
							<span class="input-group-addon showModal"
								data-assign-to="applicationNOCDocument${status.index}natureOfRequest"
								data-header="Nature Of NOC Request"><span
								class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></span>
						</div>
					</td>
					<td><form:input class="form-control dateval" maxlength="50"
							id="letterSentOn"
							path="applicationNOCDocument[${status.index}].letterSentOn" /> <form:errors
							path="applicationNOCDocument[${status.index}].letterSentOn"
							cssClass="add-margin error-msg" /></td>
					<td><form:input class="form-control dateval" maxlength="50"
							id="replyReceivedOn"
							path="applicationNOCDocument[${status.index}].replyReceivedOn" />
						<form:errors
							path="applicationNOCDocument[${status.index}].replyReceivedOn"
							cssClass="add-margin error-msg" /></td>
					<td><form:radiobutton
							path="applicationNOCDocument[${status.index}].rejection"
							value="true" /> <spring:message code="lbl.yes" /> <form:radiobutton
							path="applicationNOCDocument[${status.index}].rejection"
							value="false" /> <spring:message code="lbl.no" /></td>
					<td><form:radiobutton
							path="applicationNOCDocument[${status.index}].notApplicable"
							value="true" /> <spring:message code="lbl.yes" /> <form:radiobutton
							path="applicationNOCDocument[${status.index}].notApplicable"
							value="false" /> <spring:message code="lbl.no" /></td>
					<td><form:radiobutton
							path="applicationNOCDocument[${status.index}].issubmitted"
							value="true" /> <spring:message code="lbl.yes" /> <form:radiobutton
							path="applicationNOCDocument[${status.index}].issubmitted"
							value="false" /> <spring:message code="lbl.no" /></td>
					<td>
						<div class="input-group">
							<form:textarea
								class="form-control patternvalidation textarea-content"
								data-pattern="string" maxlength="512"
								id="applicationNOCDocument${status.index}remarks"
								path="applicationNOCDocument[${status.index}].remarks" />
							<form:errors
								path="applicationNOCDocument[${status.index}].remarks"
								cssClass="add-margin error-msg" />
							<span class="input-group-addon showModal"
								data-assign-to="applicationNOCDocument${status.index}remarks"
								data-header="Remarks"><span
								class="glyphicon glyphicon-pencil" style="cursor: pointer"></span></span>
						</div>
					</td>
					<td><c:choose>
							<c:when test="${doc.isMandatory}">
								<input type="file" id="file${status.index}id"
									name="checkListDocumentsForNOC[${status.index}].file"
									class="file-ellipsis upload-file" required="required">
							</c:when>
							<c:otherwise>
								<input type="file" id="file${status.index}id"
									name="checkListDocumentsForNOC[${status.index}].file"
									class="file-ellipsis upload-file">
							</c:otherwise>
						</c:choose> <form:errors
							path="checkListDocumentsForNOC[${status.index}].file"
							cssClass="add-margin error-msg" /> <c:set value="false"
							var="isDocFound"></c:set> <c:forEach
							items="${bpaApplication.applicationNOCDocument}" var="nocdoc"
							varStatus="loopStatus">
							<c:if test="${nocdoc.checklist.id == doc.id}">
								<c:set value="true" var="isDocFound"></c:set>
								<a
									href="/bpa/application/downloadfile/${nocdoc.nocFileStore.fileStoreId}"
									data-gallery>${nocdoc.nocFileStore.fileName} </a>
							</c:if>
						</c:forEach> <c:if test="${!isDocFound}">
				NA
			</c:if></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>

<!-- Modal -->
<div class="modal fade" id="textarea-modal" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" id="textarea-header"></h4>
			</div>
			<div class="modal-body">
				<textarea class="form-control textarea-content-of-modal"
					id="textarea-updatedcontent" rows="10"></textarea>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					id="textarea-btnupdate" data-dismiss="modal">Update</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

			</div>
		</div>

	</div>
</div>