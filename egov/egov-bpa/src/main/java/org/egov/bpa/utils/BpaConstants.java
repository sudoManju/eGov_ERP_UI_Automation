/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
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
package org.egov.bpa.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BpaConstants {
	public static final String ST_CODE_01 = "01";
	public static final String ST_CODE_02 = "02";
	public static final String ST_CODE_03 = "03";
	public static final String ST_CODE_04 = "04";
	public static final String ST_CODE_05 = "05";
	public static final String ST_CODE_06 = "06";
	public static final String ST_CODE_07 = "07";
	public static final String RE_DEVELOPMENT_OF_LAND = "Re-development of land";
    public static final String DEVELOPMENT_OF_LAND = "Development of land";
    public static final String REGULARIZATION = "Regularization";
	public static final String CHANGE_IN_OCCUPANCY = "Change in Occupancy";
	public static final String ADDING_OF_EXTENSION = "Adding of Extension";
	public static final String ALTERATION = "Alteration";
	public static final String DIVISION_OF_PLOT = "Division of Plot";
	public static final String RECONSTRUCTION = "Reconstruction";
	public static final String DEMOLITION = "Demolition";
	public static final String NEW_CONSTRUCTION = "New Construction";
	public static final String ZONE = "Zone";
    public static final String WARD = "Ward";
    public static final String BLOCK = "Block";
    public static final String STREET = "Street";
    public static final String EGMODULE_NAME = "BPA";
    public static final String BPASTATUSMODULETYPE = "BPAAPPLICATION";
    public static final String ROLE_CITIZEN = "Citizen";
    public static final String YEARLY = "Yearly";
    public static final String BPA_APPNO_SEQ = "SEQ_BPA_APPNO_";
    public static final String USERNAME_ANONYMOUS = "anonymous";
    public static final String BPA_BILLNO_SEQ = "SEQ_BILLNO_";
    public static final String BPA_PLANPERMNO_SEQ = "SEQ_BPA_PLANPERMNO_";
    public static final String LOCALITY = "locality";
    public static final String ELECTIONWARD_BNDRY_TYPE = "Election Ward";
    public static final String LOCALITY_BNDRY_TYPE = "Locality";
    public static final String LOCATION_HIERARCHY_TYPE = "LOCATION";
    public static final String REVENUE_HIERARCHY_TYPE = "REVENUE";
    public static final Character DMD_STATUS_CHEQUE_BOUNCED = 'B';
    public static final String DEMANDISHISTORY = "N";
    public static final String STRING_VALIDATION = "Paid Amount is greater than Total Amount to be paid";

    public static final String APPLICATION_MODULE_TYPE = "BPA";
    public static final String BPASTATUS_MODULETYPE = "REGISTRATION";
    public static final String CSC_SOURCE = "CSC";
    public static final String COLON_CONCATE = "::";
    public static final String NATURE_OF_WORK = "Building Plan Approval";
    public static final String WF_NEW_STATE = "NEW";
    public static final String WF_REJECT_STATE = "Rejected";
    public static final String WF_END_STATE = "END";
    public static final String APPLICATION_STATUS_CREATED = "CREATED";
    public static final String APPLICATION_STATUS_APPROVED = "Approved";
    public static final String APPLICATION_STATUS_FIELD_INS = "Field Inspected";
    public static final String APPLICATION_STATUS_ORDER_ISSUED = "Order Issued to Applicant";
    public static final String APPLICATION_STATUS_DIGI_SIGNED ="Digitally signed";
    public static final String APPLICATION_STATUS_RECORD_APPROVED = "Record Approved";
    public static final String WF_APPROVE_BUTTON = "Approve";
    public static final String APPLICATION_STATUS_NOCUPDATED ="NOC Updated";
    public static final String WF_REJECT_BUTTON = "REJECT";
    public static final String WF_CANCELAPPLICATION_BUTTON = "CANCEL APPLICATION";
    public static final String APPLICATION_STATUS_CANCELLED = "Cancelled";
    public static final String CREATE_ADDITIONAL_RULE_CREATE = "CREATEBPAAPPLICATION";

    public static final String STAKE_HOLDER_CHECK_LIST_TYPE = "STAKEHOLDERDOCUMENT";
    public static final String ADMISSIONFEEREASON = "ADMISSIONFEES";
    public static final String BPAFEETYPE = "AdmissionFee";
    public static final String BOUNDARY_TYPE_ZONE = "Zone";
    public static final String BOUNDARY_TYPE_CITY = "City";

    public static final String FILESTORE_MODULECODE = "BPA";
    public static final String CHECKLIST_TYPE = "DOCUMENTATION";
    public static final String BPA_STATUS_SUPERINDENT_APPROVED="Superintendent Approved";
    
    public static final String SENDSMSFORBPA = "SENDSMSFROOMBPAMODULE";
    public static final String SENDEMAILFORBPA = "SENDEMAILFROOMBPAMODULE";
    
 // Sms And Email Code For New Connection
    public static final String SMSEMAILTYPENEWBPAREGISTERED = "newbparegistered";
    public static final String APPLICATION_STATUS_REGISTERED = "Registered";
    public static final String DOCUMENTVERIFIED = "Document Verified";
    public static final String SMSEMAILTYPELETTERTOPARTY = "Letter To Party";
    
    public static final String LETTERTOPARTY_NUMBER_SEQ = "SEQ_BPA_LP_";
    public static final String LETTERTOPARTY_ACK_NUMBER_SEQ = "SEQ_BPA_LP_ACK_";
    public static final String CREATEDLETTERTOPARTY = "Letter To Party Created";
    public static final String LETTERTOPARTYSENT = "LP Sent to Applicant";
    public static final String LETTERTOPARTYINITIATED  = "LP Initiated";
    public static final String LETTERTOPARTYINITIATE = "LP Initiate";
    public static final String LETTERTOPARTYDETAILS = "lettertoparty";
    public static final String CHECKLIST_TYPE_NOC = "NOC";
    public static final String BPA_APPLICATION = "bpaApplication";
    public static final String APPLICATION_HISTORY = "applicationHistory";
    public static final String CREATEINSPECTIONDETAIL_FORM = "createInspectiondetail-form";
    public static final String ADDITIONALRULE = "additionalRule";
    public static final String INSPECTION_NUMBER_SEQ = "SEQ_BPA_INSPECTIONNUMBER";
    public static final String INSPECTIONLOCATION= "INSPECTIONLOCATION";
    public static final String INSPECTIONMEASUREMENT = "INSPECTIONMEASUREMENT";
    public static final String LETTERTOPARTY_REPLY_RECEIVED="Letter To Party Reply Received"; 

    public static final String INSPECTIONACCESS = "INSPECTIONACCESS";

    public static final String INSPECTIONSURROUNDING= "INSPECTIONSURROUNDING";

    public static final String INSPECTIONTYPEOFLAND= "INSPECTIONTYPEOFLAND";

    public static final String INSPECTIONPROPOSEDSTAGEWORK = "INSPECTIONPROPOSEDSTAGEWORK";

    public static final String INSPECTIONWORKCOMPLETEDPERPLAN= "INSPECTIONWORKCOMPLETEDPERPLAN";
    
    public static final String INSPECTIONAREALOC= "INSPECTIONAREALOC";
    public static final String INSPECTIONLENGTHOFCOMPOUNDWALL= "INSPECTIONLENGTHOFCOMPOUNDWALL";
    public static final String INSPECTIONNUMBEROFWELLS= "INSPECTIONNUMBEROFWELLS";
    public static final String INSPECTIONERECTIONOFTOWER= "INSPECTIONERECTIONOFTOWER";
    public static final String INSPECTIONSHUTTER= "INSPECTIONSHUTTER";
    public static final String INSPECTIONROOFCONVERSION= "INSPECTIONROOFCONVERSION";

    public static final String INSPECTIONHGTBUILDABUTROAD= "INSPECTIONHGTBUILDABUTROAD";
    public static final String BPASTATUS_APPLICATIONFEE_MODULE = "APPLICATIONFEE";
    public static final String BPASTATUS_APPLICATIONFEE_APPROVED = "Approved";
    public static final String FEETYPE_SANCTIONFEE = "SanctionFee";
    public static final String AUTOCALCULATEFEEBYINSPECTION = "BPA_AUTOCALCULATE_FEE";
    public static final String BPASTATUS_MODULETYPE_REGISTRATIONFEE = "APPLICATIONFEE";
    public static final String BPASTATUS_REGISTRATIONFEE_APPROVED = "Approved";
    
    public static final String APPLN_STATUS_FIELD_INSPECTION_INITIATED = "Field Inspection initiated";
    public static final String BPA_ADM_FEE = "Admission Fee";
    public static final String BPA_PERMIT_FEE = "Permit Fees";
    public static final String BPA_WELL_FEE = "Charges for Well";
    public static final String BPA_COMPOUND_FEE = "Charges for Compound Wall";
    public static final String IMAGES_BASE_PATH = "/egi/resources/global/images/";
    public static final String IMAGE_CONTEXT_PATH = "/egi";
    public static final String BPADEMANDNOTICETITLE = "Building Plan Approval Demand Notice";
    public static final String DEMANDNOCFILENAME = "bpaDemandNotice";
    public static final String STRING_BPA_FUCNTION_CODE = "5100";
    public static final String ROLE_BILLCOLLECTOR = "Collection Operator";
    public static final String  BPA_DEPARTMENT_CODE="BPA_DEPARTMENT_CODE";
    public static final String BPA_DEFAULT_FUND_CODE="BPA_DEFAULT_FUND_CODE";
    public static final String BPA_DEFAULT_FUNCTIONARY_CODE="BPA_DEFAULT_FUNCTIONARY_CODE";
    public static final String BPA_DEFAULT_FUND_SRC_CODE="BPA_DEFAULT_FUND_SRC_CODE";
    public static final String DESIGNATION_AEE="Assistant executive engineer";
    public static final String DESIGNATION_AE="Assistant engineer";
    public static final String DESIGNATION_EE="Executive engineer";
    public static final String DESIGNATION_COMMISSIONER="Commissioner";
    public static final String DESIGNATION_SECRETARY="Secretary";
    public static final String DESIGNATION_SUPERIAPPROVAL="Superintendent of Approval";
    public static final String BUILDINGPERMITFILENAME = "buildingpermit";
    public static final String BUILDINGDEVELOPPERMITFILENAME = "buildingdeveloppermit";
    public static final String GENERATEPERMITORDER = "Generate Permit Order";
    public static final String TOTAL_PLINT_AREA = "totalPlintArea";
    public static final String BUILDINGHEIGHT_GROUND = "buildingheightGround";
	public static final String FLOOR_COUNT = "floorCount";
	public static final String EXTENTINSQMTS = "extentinsqmts";
	public static final String THATCHED_TILED_HOUSE = "Thatched / Tiled House";
	public static final String MERCANTILE_COMMERCIAL = "Mercantile / Commercial";
	public static final String INDUSTRIAL = "Industrial";
	public static final String RESIDENTIAL = "Residential";
	public static final String BPAFEECOLLECT="BPA Admission fees collected";
	public static final String WF_SURVEYOR_FORWARD_BUTTON = "Submit";
	private static final List<String> BUILDPERMIT = new ArrayList<>();
	static {
		BUILDPERMIT.add(ST_CODE_02); // Demolition
		BUILDPERMIT.add(ST_CODE_01); // New Construction
		BUILDPERMIT.add(ST_CODE_03); // Reconstruction
		BUILDPERMIT.add(ST_CODE_04); // Alteration
		BUILDPERMIT.add(ST_CODE_06); // Adding of Extension
		BUILDPERMIT.add(ST_CODE_07); // Change in Occupancy
	}

	private static final List<String> DEVELOPPERMIT = new ArrayList<>();
	static {
		DEVELOPPERMIT.add(ST_CODE_05);
	}

	private static final List<String> FLOORLIST = new ArrayList<>();
	static {
		FLOORLIST.add("Basement 3");
		FLOORLIST.add("Basement 2");
		FLOORLIST.add("Basement 1");
		FLOORLIST.add("Ground Floor");
		FLOORLIST.add("Mezzanine Floor");
		FLOORLIST.add("First Floor");
		FLOORLIST.add("Second Floor");
		FLOORLIST.add("Third Floor");
		FLOORLIST.add("Fourth Floor");
		FLOORLIST.add("Fifth Floor");
		FLOORLIST.add("Sixth Floor");
		FLOORLIST.add("Seventh Floor");
		FLOORLIST.add("Ninth Floor");
		FLOORLIST.add("Tenth Floor");
		FLOORLIST.add("Eleventh Floor");
		FLOORLIST.add("Tweleth Floor");
		FLOORLIST.add("Thirteenth Floor");
		FLOORLIST.add("Fourteenth Floor");
		FLOORLIST.add("Fifteenth Floor");
	}
	
	private static final Map<String, Map<String, BigDecimal>> STAKEHOLDERRESTRICTIONS = new HashMap<>();
	static {
		Map<String, BigDecimal> architectRestriction = new HashMap<>();
		architectRestriction.put(EXTENTINSQMTS, BigDecimal.valueOf(10000));
		architectRestriction.put(TOTAL_PLINT_AREA, BigDecimal.valueOf(1500));
		architectRestriction.put(FLOOR_COUNT, BigDecimal.valueOf(3));
		architectRestriction.put(BUILDINGHEIGHT_GROUND, BigDecimal.valueOf(11));
		STAKEHOLDERRESTRICTIONS.put("architect", architectRestriction);
		STAKEHOLDERRESTRICTIONS.put("Building Designer-A", architectRestriction);
		STAKEHOLDERRESTRICTIONS.put("Engineer-A", architectRestriction);
	}
	static {
		Map<String, BigDecimal> buildingDesingerBRestriction = new HashMap<>();
		buildingDesingerBRestriction.put(EXTENTINSQMTS, BigDecimal.valueOf(5000));
		buildingDesingerBRestriction.put(TOTAL_PLINT_AREA, BigDecimal.valueOf(4000));
		buildingDesingerBRestriction.put(FLOOR_COUNT, BigDecimal.valueOf(4));
		buildingDesingerBRestriction.put(BUILDINGHEIGHT_GROUND, BigDecimal.valueOf(14.5));
		STAKEHOLDERRESTRICTIONS.put("Building Designer-B", buildingDesingerBRestriction);
		STAKEHOLDERRESTRICTIONS.put("Engineer-B", buildingDesingerBRestriction);
	}
	static {
		Map<String, BigDecimal> engineerARestriction = new HashMap<>();
		engineerARestriction.put(EXTENTINSQMTS, BigDecimal.valueOf(10000));
		engineerARestriction.put(TOTAL_PLINT_AREA, BigDecimal.valueOf(2250));
		engineerARestriction.put(FLOOR_COUNT, BigDecimal.valueOf(3));
		engineerARestriction.put(BUILDINGHEIGHT_GROUND, BigDecimal.valueOf(11));
		STAKEHOLDERRESTRICTIONS.put("Supervisor-A", engineerARestriction);
	}
	static {
		Map<String, BigDecimal> engineerARestriction = new HashMap<>();
		engineerARestriction.put(EXTENTINSQMTS, BigDecimal.valueOf(3000));
		engineerARestriction.put(TOTAL_PLINT_AREA, BigDecimal.valueOf(300));
		engineerARestriction.put(FLOOR_COUNT, BigDecimal.valueOf(2));
		engineerARestriction.put(BUILDINGHEIGHT_GROUND, BigDecimal.valueOf(7.5));
		STAKEHOLDERRESTRICTIONS.put("Supervisor-B", engineerARestriction);
	}

	private static final List<String> BPAFEECATEGORY1 = new ArrayList<>();
	static {
		BPAFEECATEGORY1.add(ST_CODE_01); // New Construction
		BPAFEECATEGORY1.add(ST_CODE_02); // Demolition
		BPAFEECATEGORY1.add(ST_CODE_03); // Reconstruction
		BPAFEECATEGORY1.add(ST_CODE_04); // Alteration
		BPAFEECATEGORY1.add(ST_CODE_06); // Adding of Extension
		BPAFEECATEGORY1.add(ST_CODE_07); // Change in Occupancy
	}
	
	private static final List<String> VALIDATIONPUPOSE = new ArrayList<>();
	static {
		VALIDATIONPUPOSE.add(ST_CODE_02); // Demolition
		VALIDATIONPUPOSE.add(ST_CODE_01); // New Construction
		VALIDATIONPUPOSE.add(ST_CODE_03); // Reconstruction
		VALIDATIONPUPOSE.add(ST_CODE_04); // Alteration
		VALIDATIONPUPOSE.add(ST_CODE_06); // Adding of Extension
		VALIDATIONPUPOSE.add(ST_CODE_07); // Change in Occupancy
	}
	
	private static final List<String> BPAFEECATEGORY2 = new ArrayList<>();
	static {
		BPAFEECATEGORY2.add(ST_CODE_05);     // Sub-Division of Plot/Development of land
	}
	
	public static Map<String, Map<String, BigDecimal>> getStakeholderRestrictions() {
		return Collections.unmodifiableMap(STAKEHOLDERRESTRICTIONS);
	}
	
	public static List<String> getBuildingFloorsList() {
		return Collections.unmodifiableList(FLOORLIST);
	}
	
	public static List<String> getServicesForBuildPermit() {
		return Collections.unmodifiableList(BUILDPERMIT);
	}

	public static List<String> getServicesForDevelopPermit() {
		return Collections.unmodifiableList(DEVELOPPERMIT);
	}
	
	public static List<String> getBpaFeeCateory1() {
		return Collections.unmodifiableList(BPAFEECATEGORY1);
	}
	
	public static List<String> getBpaFeeCateory2() {
		return Collections.unmodifiableList(BPAFEECATEGORY2);
	}
	
	public static List<String> getServicesForValidation() {
		return Collections.unmodifiableList(VALIDATIONPUPOSE);
	}
}