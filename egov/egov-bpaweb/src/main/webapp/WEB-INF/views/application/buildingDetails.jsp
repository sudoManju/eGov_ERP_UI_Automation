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

<%@page import="org.python.modules.jarray"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglib/cdn.tld" prefix="cdn"%>
<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">Building Details</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label text-right">Building
		Unit Count</label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation" maxlength="5" data-pattern="number"
			id="unitCount" path="buildingDetail[0].unitCount" />
		<form:errors path="buildingDetail[0].unitCount"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Building
		Unit Classification</label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation"  maxlength="64" data-pattern="alphanumericwithspace"
			id="unitClassification" path="buildingDetail[0].unitClassification" />
		<form:errors path="buildingDetail[0].unitClassification"
			cssClass="add-margin error-msg" />
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Building
		Floor Count</label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation"
			data-pattern="number" maxlength="5" id="floorCount"
			path="buildingDetail[0].floorCount" />
		<form:errors path="buildingDetail[0].floorCount"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Building
		No. Of Basement Unit </label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation" maxlength="5" data-pattern="number"
			id="noofbasementUnit" path="buildingDetail[0].noofbasementUnit" />
		<form:errors path="buildingDetail[0].noofbasementUnit"
			cssClass="add-margin error-msg" />
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Building
		Height Groud </label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation" maxlength="10" data-pattern="decimalvalue"
			id="buildingheightGround"
			path="buildingDetail[0].buildingheightGround" />
		<form:errors path="buildingDetail[0].buildingheightGround"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Building
		Height Floor</label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation"  maxlength="5" data-pattern="decimalvalue"
			id="buildingheightFloor" path="buildingDetail[0].buildingheightFloor" />
		<form:errors path="buildingDetail[0].buildingheightFloor"
			cssClass="add-margin error-msg" />
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Building
		No Of Upper Floor </label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation" id="noofupperFloor" maxlength="5" data-pattern="number"
			path="buildingDetail[0].noofupperFloor" />
		<form:errors path="buildingDetail[0].noofupperFloor"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Building
		No Of Dwelling Unit </label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation" maxlength="5" data-pattern="number"
			id="noofdwellingUnit" path="buildingDetail[0].noofdwellingUnit" />
		<form:errors path="buildingDetail[0].noofdwellingUnit"
			cssClass="add-margin error-msg" />
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Building
		Proposed Sital in Sqmt </label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation" maxlength="10" data-pattern="decimalvalue"
			id="proposedSitalinSqmt" path="buildingDetail[0].proposedSitalinSqmt" />
		<form:errors path="buildingDetail[0].proposedSitalinSqmt"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Building
		Proposed Floor Area</label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation"
			maxlength="10" data-pattern="decimalvalue" id="buildingDetail[0].proposedfloorArea"
			path="buildingDetail[0].proposedfloorArea" />
		<form:errors path="buildingDetail[0].proposedfloorArea"
			cssClass="add-margin error-msg" />
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Building
		Total Plint Area </label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation"
			maxlength="10" data-pattern="decimalvalue" id="district"
			path="buildingDetail[0].totalPlintArea" />
		<form:errors path="buildingDetail[0].totalPlintArea"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Building
		Total Slab</label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation"
			maxlength="10" data-pattern="decimalvalue" id="totalSlab"
			path="buildingDetail[0].totalSlab" />
		<form:errors path="buildingDetail[0].totalSlab"
			cssClass="add-margin error-msg" />
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Existing
		Building Category <span class="mandatory"></span>
	</label>
	<div class="col-sm-3 add-margin">
		<form:select path="buildingDetail[0].existBldgCategory"
			data-first-option="false" id="" cssClass="form-control"
			required="required">
			<form:option value="">
				<spring:message code="lbl.select" />
			</form:option>
			<form:options items="${buildingCategorYlist}" itemValue="id"
				itemLabel="code" />
		</form:select>
		<form:errors path="buildingDetail[0].existBldgCategory"
			cssClass="add-margin error-msg" />
	</div>
	<label class="col-sm-2 control-label text-right">Proposed
		Building Category <span class="mandatory"></span>
	</label>
	<div class="col-sm-3 add-margin">
		<form:select path="buildingDetail[0].proposedBldgCategory"
			data-first-option="false" id="" cssClass="form-control"
			required="required">
			<form:option value="">
				<spring:message code="lbl.select" />
			</form:option>
			<form:options items="${buildingCategorYlist}" itemValue="id"
				itemLabel="code" />
		</form:select>
		<form:errors path="buildingDetail[0].proposedBldgCategory"
			cssClass="add-margin error-msg" />
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Building
		Is Groud Floor </label>
	<div class="col-sm-3 add-margin">
		<form:checkbox id="isGroudFloor" path="buildingDetail[0].isGroudFloor"
			value="isGroudFloor" />
		<form:errors path="buildingDetail[0].isGroudFloor" />
	</div>
	<label class="col-sm-2 control-label text-right">Building
		isStiltFloor</label>
	<div class="col-sm-3 add-margin">
		<form:checkbox id="isStiltFloor" path="buildingDetail[0].isStiltFloor"
			value=".isStiltFloor" />
		<form:errors path="buildingDetail[0].isStiltFloor" />
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Building
		Is Mezzanine Floor </label>
	<div class="col-sm-3 add-margin">
		<form:checkbox id="isGroudFloor"
			path="buildingDetail[0].isMezzanineFloor" value="isMezzanineFloor" />
		<form:errors path="buildingDetail[0].isMezzanineFloor" />
	</div>
</div>