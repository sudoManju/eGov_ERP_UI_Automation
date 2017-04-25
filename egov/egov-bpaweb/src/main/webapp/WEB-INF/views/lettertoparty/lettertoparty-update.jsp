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
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/taglib/cdn.tld" prefix="cdn"%>
<form:form role="form" action="/bpa/lettertoparty/update" method="post"
	modelAttribute="lettertoParty" id="lettertoPartyform"
	cssClass="form-horizontal form-groups-bordered"
	enctype="multipart/form-data">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary" data-collapsed="0">
				<div class="panel-title">
					<spring:message code="title.lettertoparty.update" />
				</div>
			</div>
			<div class="panel panel-primary" data-collapsed="0">

				<div class="panel panel-primary" data-collapsed="0">
					<jsp:include page="../application/viewapplication-details.jsp"></jsp:include>
					<div class="panel-heading custom_form_panel_heading">
						<div class="panel-title">
							<spring:message code="lbl.lp.details" />
						</div>
					</div>
					<div class="panel-body">
						<div class="row add-border">
							<div class="col-sm-3 add-margin">
								<spring:message code="lbl.lpNumber" />
							</div>
							<div class="col-sm-3 add-margin view-content">
								<c:out value="${lettertoParty.lpNumber}"></c:out>
							</div>
							<form:hidden path="application" id="applicationId"
								value="${application.id}" />

							<form:hidden path="letterDate" id="letterDate"
								value="${letterDate}" />
							<input type="hidden" id='lettertoParty' name="lettertoParty"
								value="${lettertoParty.id}">
						</div>
						<div class="form-group">
							<div class="col-sm-3 add-margin">
								<spring:message code="lbl.lpreason" />
								<span class="mandatory"></span>
							</div>
							<div class="col-sm-3 add-margin">
								<form:select path="lpReason" data-first-option="false"
									id="lpReason" cssClass="form-control" required="required">
									<form:option value="">
										<spring:message code="lbl.select" />
									</form:option>
									<form:options items="${lpReasonList}" itemValue="id"
										itemLabel="description" />
								</form:select>
								<form:errors path="lpReason" cssClass="add-margin error-msg" />
							</div>


							<div class="row add-border">
								<label class="col-sm-3 control-label text-right"><spring:message
										code="lbl.lpdescription" /></label>
								<div class="col-sm-3 add-margin">
									<form:textarea path="lpDesc"
										class="form-control patternvalidation" data-pattern="string"
										maxlength="1024" id="lpDesc" />
									<form:errors path="lpDesc" cssClass="error-msg" />
								</div>

							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3 add-margin">
								<spring:message code="lbl.lpsentdate" />
							</div>
							<div class="col-sm-3 add-margin">
								<form:input path="sentDate" class="form-control datepicker"
									data-date-end-date="0d" id="sentDate"
									data-inputmask="'mask': 'd/m/y'" />
								<form:errors path="sentDate" cssClass="add-margin error-msg" />
							</div>
						</div>


						<c:choose>
							<c:when test="${!lettertoPartyDocument.isEmpty()}">
								<div class="panel-heading custom_form_panel_heading">
									<div class="panel-title">
										<spring:message code="lbl.encloseddocuments" />
										-
										<spring:message code="lbl.checklist" />
									</div>
								</div>
								<div class="form-group view-content header-color hidden-xs">
									<div class="col-sm-3 text-center">
										<spring:message code="lbl.documentname" />
									</div>
									<div class="col-sm-3 text-center">
										<spring:message code="lbl.issubmitted" />
									</div>
									<div class="col-sm-3 text-center">
										<spring:message code="lbl.remarks" />
									</div>
									<div class="col-sm-3 text-center">
										<spring:message code="lbl.attachdocument" />
									</div>
								</div>
								<c:forEach var="lpdoc" items="${lettertopartydocList}"
									varStatus="status">
									<form:hidden
										path="lettertoPartyDocument[${status.index}].lettertoParty"
										id="lettertopartyId" value="${lettertoParty.id}" />
									<form:hidden
										path="lettertoPartyDocument[${status.index}].checklistDetail"
										id="checklist" value="${doc.id}" />
									<tr>
										<td>
											<div class="form-group">
												<div class="col-sm-3 add-margin check-text text-center">
													<c:forEach var="chk" items="${checkListDetailList}">
														<c:if test="${lpdoc.checklistDetail.id == chk.id}">
															<c:out value="${chk.description}" />
															<form:hidden
																id="lettertoPartyDocument${status.index}checklistDetail"
																path="lettertoPartyDocument[${status.index}].checklistDetail"
																value="${chk.id}" />
															<form:hidden
																id="lettertoPartyDocument${status.index}checklistDetail"
																path="lettertoPartyDocument[${status.index}].checklistDetail.isMandatory"
																value="${chk.isMandatory}" />
														</c:if>
													</c:forEach>
												</div>
												<div class="col-sm-3 add-margin text-center">
													<form:checkbox
														id="lettertoPartyDocument${status.index}issubmitted"
														path="lettertoPartyDocument[${status.index}].issubmitted"
														value="lettertoPartyDocument${status.index}issubmitted" />
												</div>

												<div class="col-sm-3 add-margin text-center">
													<form:textarea class="form-control patternvalidation"
														data-pattern="string" maxlength="256"
														id="lettertoPartyDocument${status.index}remarks"
														path="lettertoPartyDocument[${status.index}].remarks" />
													<td><c:out value="${doc.id}"></c:out></td>
													<form:errors
														path="lettertoPartyDocument[${status.index}].remarks"
														cssClass="add-margin error-msg" />
												</div>

												<div class="col-sm-3 add-margin text-center">
													<c:choose>
														<c:when test="${chk.isMandatory}">
															<input type="file" id="file${status.index}id"
																name="lettertoPartyDocument[${status.index}].files"
																class="file-ellipsis upload-file" required="required">
														</c:when>
														<c:otherwise>
															<input type="file" id="file${status.index}id"
																name="lettertoPartyDocument[${status.index}].files"
																class="file-ellipsis upload-file">
														</c:otherwise>
													</c:choose>
													<form:errors
														path="lettertoPartyDocument[${status.index}].files"
														cssClass="add-margin error-msg" />
													<div class="add-margin error-msg text-left">
														<font size="2"> <spring:message
																code="lbl.mesg.document" />
														</font>
													</div>
												</div>

												<div class="col-sm-2">
													<c:set value="false" var="isDocFound"></c:set>
													<c:forEach items="${lpdoc.getSupportDocs()}" var="file">
														<c:set value="true" var="isDocFound"></c:set>
														<a
															href="/bpa/application/downloadfile/${file.fileStoreId}"
															data-gallery>${file.fileName} </a>
													</c:forEach>

													<c:if test="${!isDocFound}">
										NA
									</c:if>
												</div>
											</div>
								</c:forEach>
								</td>
								</tr>
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<div class="text-center">
	<button type='submit' class='btn btn-primary' id="buttonSubmit">
		<spring:message code='lbl.update' />
	</button>
	<button type="submit" class="btn btn-primary"
		onclick="return getUrlToPring()">
		<spring:message code="lbl.print.lettertoparty" />
	</button>
	<a href='javascript:void(0)' class='btn btn-default'
		onclick='self.close()'><spring:message code='lbl.close' /></a>
</div>

<script
	src="<cdn:url value='/resources/global/js/bootstrap/bootstrap-datepicker.js' context='/egi'/>"></script>
<link rel="stylesheet"
	href="<cdn:url value='/resources/global/css/bootstrap/bootstrap-datepicker.css' context='/egi'/>">
<script
	src="<cdn:url value='/resources/js/app/lettertoparty.js?rnd=${app_release_no}'/> "></script>
