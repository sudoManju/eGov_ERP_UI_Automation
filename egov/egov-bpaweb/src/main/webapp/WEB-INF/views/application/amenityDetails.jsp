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

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglib/cdn.tld" prefix="cdn"%>

<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">Services and Amenities Measurement Details</div>
</div>
	<div class="form-group">
	<label class="col-sm-3 control-label text-right extentOfLand">Extent Of Land
		<span class="mandatory"></span>
	</label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation" maxlength="10"
			data-pattern="decimalvalue" id="extentOfLand" required="required"
			path="siteDetail[0].extentOfLand" />
		<form:errors path="siteDetail[0].extentOfLand"
			cssClass="add-margin error-msg" />
	

		<form:select path="siteDetail[0].unitOfMeasurement"
			data-first-option="false" id="zone" cssClass="form-control"
			required="required">
			<form:options items="${uomList}" />
		</form:select>
		<form:errors path="siteDetail[0].unitOfMeasurement"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right toggle-madatory Roof"><spring:message code="lbl.roof.conv"/><span ></span></label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation dyn-mandatory Roof" data-pattern="decimalvalue"
			id="roofConversion"
			path="siteDetail[0].roofConversion" /> in Sqmtr.
		<form:errors path="siteDetail[0].roofConversion" 
			cssClass="add-margin error-msg" />
	</div>
	</div>
	<div class="form-group">
	<label class="col-sm-3 control-label text-right toggle-madatory Pole"><spring:message code="lbl.no.of.poles"/><span ></span></label>
	<div class="col-sm-3 add-margin">
		<form:input  class="form-control patternvalidation dyn-mandatory Pole" data-pattern="number"
		  id="dwellingunitnt" 
			path="siteDetail[0].noOfPoles" />In Numbers
		<form:errors path="siteDetail[0].noOfPoles" 
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right toggle-madatory Hut"><spring:message code="lbl.no.of.shuts.huts"/><span ></span></label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation dyn-mandatory Hut" data-pattern="decimalvalue"
			id="noOfHutOrSheds"
			path="siteDetail[0].noOfHutOrSheds" /> In Numbers
		<form:errors path="siteDetail[0].noOfHutOrSheds" 
			cssClass="add-margin error-msg" />
	</div> 
	</div>

	
	<div class="form-group">
	<label class="col-sm-3 control-label text-right toggle-madatory Shut"><spring:message code="lbl.shutter"/><span ></span></label>
	<div class="col-sm-3 add-margin">
		<form:input  class="form-control patternvalidation dyn-mandatory Shut" 
		  id="shutter"  data-pattern="decimalvalue"
			path="siteDetail[0].shutter" />in Sqmtr.
		<form:errors path="siteDetail[0].shutter" 
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right toggle-madatory Towe"><spring:message code="lbl.erection.tower"/>
		<span ></span></label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation dyn-mandatory Towe"
			data-pattern="decimalvalue"  id="erectionoftower"
			path="siteDetail[0].erectionoftower" />in Sqmtr.
		<form:errors path="siteDetail[0].erectionoftower" 
			cssClass="add-margin error-msg" />
	</div>
	</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right toggle-madatory Well"><spring:message code="lbl.number.well"/><span ></span></label>
	<div class="col-sm-3 add-margin">
		<form:input  class="form-control patternvalidation dyn-mandatory Well" data-pattern="number"
		  id="dwellingunitnt" 
			path="siteDetail[0].dwellingunitnt" />In Numbers
		<form:errors path="siteDetail[0].dwellingunitnt" 
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right toggle-madatory Comp"><spring:message code="lbl.len.com.wall"/><span></span></label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation dyn-mandatory Comp"
		data-pattern="decimalvalue"
			  id="siteDetail[0].lengthOfCompoundWall"
			path="siteDetail[0].lengthOfCompoundWall" />in Sqmtr.
		<form:errors path="siteDetail[0].lengthOfCompoundWall" 
			cssClass="add-margin error-msg" />
	</div>
</div>

<div class="form-group">
		<label class="col-sm-3 control-label text-right">Details of Permit/Approved Plan already obtained
		</label>
		<div class="col-sm-3 add-margin">
			<form:checkbox id="isexistingApprovedPlan"
				path="isExistingApprovedPlan"
				value="isExistingApprovedPlan" />
			<form:errors path="isExistingApprovedPlan" cssClass="add-margin error-msg"/>
		</div>
	</div>
	<div class="form-group" id="existingAppPlan">
<div class="form-group">
	<label class="col-sm-3 control-label text-right removemandatory">Fee Amount Paid No <span class="mandatory"></span></label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation" maxlength="128"
			id="feeAmountRecieptNo" path="feeAmountRecieptNo" />
		<form:errors path="feeAmountRecieptNo" cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Date of Receipt<span class="mandatory"></span></label>
	<div class="col-sm-3 add-margin">
		<form:input path="approvedReceiptDate" class="form-control datepicker"
				data-date-end-date="0d" id="approvedReceiptDate"
				data-inputmask="'mask': 'd/m/y'" />
			<form:errors path="approvedReceiptDate" cssClass="add-margin error-msg" />
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label text-right removemandatory">Revised Application Number<span class="mandatory"></span></label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation" maxlength="128"
			id="revisedApplicationNumber" path="revisedApplicationNumber" />
		<form:errors path="revisedApplicationNumber" cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right removemandatory">Already received Permit Number<span class="mandatory"></span></label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation" maxlength="128"
			id="revisedPermitNumber" path="revisedPermitNumber" />
		<form:errors path="revisedPermitNumber" cssClass="add-margin error-msg" />
	</div>
</div>
</div>
	
	<div class="form-group" >
		<label class="col-sm-3 control-label text-right">If the application is for Regularization
		</label>
		<div class="col-sm-3 add-margin">
			<form:checkbox id="isappForRegularization"
				path="siteDetail[0].isappForRegularization"
				value="siteDetail[0].isappForRegularization" />
			<form:errors path="siteDetail[0].isappForRegularization" />
		</div>

</div>
<div class="form-group" id="constDiv">
	<label class="col-sm-3 control-label text-right">Construction Stages</label>
	<div class="col-sm-3 add-margin">
		<form:select path="siteDetail[0].constStages" data-first-option="false" id="constStages"
				cssClass="form-control" >
				<form:option value="">
					<spring:message code="lbl.select" />
				</form:option>
				<form:options items="${constStages}" itemValue="id" itemLabel="code" />
			</form:select>
			<form:errors path="siteDetail[0].constStages"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">If not completed the state of construction</label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation"
				data-pattern="alphanumericwithspace"  maxlength="128"
			 id="stateOfConstruction"
				path="siteDetail[0].stateOfConstruction" />
				<form:errors path="siteDetail[0].stateOfConstruction"
			cssClass="add-margin error-msg" />
	</div>
</div>

