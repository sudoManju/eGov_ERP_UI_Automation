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

public class BpaConstants {

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
    public static final String WATER_CONN_BILLNO_SEQ = "SEQ_BILLNO_";
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
    
    public static final String LETTERTOPARTY_NUMBER_SEQ = "SEQ_BPA_LP_";
    public static final String CREATEDLETTERTOPARTY = "Letter To Party Created";
    public static final String LETTERTOPARTYSENT = "LP Sent to Applicant";
    public static final String LETTERTOPARTYINITIATED  = "LP Initiated";
    public static final String LETTERTOPARTYDETAILS = "lettertoparty";
    public static final String CHECKLIST_TYPE_NOC = "NOC";
    public static final String BPA_APPLICATION = "bpaApplication";
    public static final String APPLICATION_HISTORY = "applicationHistory";
    public static final String CREATEINSPECTIONDETAIL_FORM = "createInspectiondetail-form";
    public static final String ADDITIONALRULE = "additionalRule";
    public static final String INSPECTION_NUMBER_SEQ = "SEQ_BPA_INSPECTIONNUMBER";
    public static final String INSPECTIONLOCATION= "INSPECTIONLOCATION";
    public static final String INSPECTIONMEASUREMENT = "INSPECTIONMEASUREMENT";

    public static final String INSPECTIONACCESS = "INSPECTIONACCESS";

    public static final String INSPECTIONSURROUNDING= "INSPECTIONSURROUNDING";

    public static final String INSPECTIONTYPEOFLAND= "INSPECTIONTYPEOFLAND";

    public static final String INSPECTIONPROPOSEDSTAGEWORK = "INSPECTIONPROPOSEDSTAGEWORK";

    public static final String INSPECTIONWORKCOMPLETEDPERPLAN= "INSPECTIONWORKCOMPLETEDPERPLAN";

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
}
