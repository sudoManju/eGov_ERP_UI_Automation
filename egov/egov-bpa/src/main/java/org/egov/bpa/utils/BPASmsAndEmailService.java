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
package org.egov.bpa.utils;

import static org.egov.bpa.utils.BpaConstants.EGMODULE_NAME;
import static org.egov.bpa.utils.BpaConstants.SENDEMAILFORBPA;
import static org.egov.bpa.utils.BpaConstants.SENDSMSFORBPA;
import static org.egov.bpa.utils.BpaConstants.SMSEMAILTYPENEWBPAREGISTERED;

import java.util.List;
import java.util.Locale;

import org.egov.bpa.application.entity.ApplicationStakeHolder;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.BpaAppointmentSchedule;
import org.egov.bpa.application.entity.StakeHolder;
import org.egov.bpa.application.entity.enums.AppointmentSchedulePurpose;
import org.egov.infra.admin.master.entity.AppConfigValues;
import org.egov.infra.admin.master.service.AppConfigValueService;
import org.egov.infra.config.core.ApplicationThreadLocals;
import org.egov.infra.messaging.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BPASmsAndEmailService {
    private static final String MSG_KEY_SMS_STAKEHOLDER_NEW = "msg.newstakeholder.sms";
    private static final String SUBJECT_KEY_EMAIL_STAKEHOLDER_NEW = "msg.newstakeholder.email.subject";
    private static final String BODY_KEY_EMAIL_STAKEHOLDER_NEW = "msg.newstakeholder.email.body";
    private static final String MSG_KEY_SMS_BPA_APPLN_NEW = "msg.bpa.newappln.sms";
    private static final String MSG_KEY_SMS_BPA_APPLN_NEW_PWD = "msg.bpa.newappln.sms.pwd";
    private static final String SUBJECT_KEY_EMAIL_BPA_APPLN_NEW = "msg.bpa.newappln.email.subject";
    private static final String BODY_KEY_EMAIL_BPA_APPLN_NEW = "msg.bpa.newappln.email.body";
    private static final String BODY_KEY_EMAIL_BPA_APPLN_NEW_PWD = "msg.bpa.newappln.email.body.pwd";
    
    private static final String MSG_KEY_SMS_BPA_DOC_SCRUTINY = "msg.bpa.doc.scruty.schedule.sms";
    private static final String SUBJECT_KEY_EMAIL_BPA_DOC_SCRUTINY = "msg.bpa.doc.scruty.schedule.email.subject";
    private static final String BODY_KEY_EMAIL_BPA_DOC_SCRUTINY = "msg.bpa.doc.scruty.schedule.email.body";
    private static final String MSG_KEY_SMS_BPA_DOC_SCRUTINY_RESCHE = "msg.bpa.doc.scruty.reschedule.sms";
    private static final String SUBJECT_KEY_EMAIL_BPA_DOC_SCRUTINY_RESCHE = "msg.bpa.doc.scruty.schedule.email.subject";
    private static final String BODY_KEY_EMAIL_BPA_DOC_SCRUTINY_RESCHE = "msg.bpa.doc.scruty.reschedule.email.subject";
    private static final String MSG_KEY_SMS_BPA_FIELD_INS = "msg.bpa.field.ins.schedule.sms";
    private static final String SUBJECT_KEY_EMAIL_BPA_FIELD_INS = "msg.bpa.field.ins.schedule.email.subject";
    private static final String BODY_KEY_EMAIL_BPA_FIELD_INS = "msg.bpa.field.ins.schedule.email.body";
    private static final String MSG_KEY_SMS_BPA_FIELD_INS_RESCHE = "msg.bpa.field.ins.reschedule.sms";
    private static final String SUBJECT_KEY_EMAIL_BPA_FIELD_INS_RESCHE = "msg.bpa.field.ins.reschedule.email.body";
    private static final String BODY_KEY_EMAIL_BPA_FIELD_INS_RESCHE = "msg.bpa.field.ins.reschedule.email.subject";

    private static final String MSG_KEY_SMS_LETTERTOPARTY = "msg.bpa.lettertoparty.sms";
    private static final String SUBJECT_KEY_EMAIL_LETTERTOPARTY = "msg.bpa.lettertoparty.email.body";
    private static final String BODY_KEY_EMAIL_LETTERTOPARTY = "msg.bpa.lettertoparty.email.subject";
    @Autowired
    private MessagingService messagingService;
    @Autowired
    @Qualifier("parentMessageSource")
    private MessageSource bpaMessageSource;
    @Autowired
    private AppConfigValueService appConfigValuesService;
    
    public String getMunicipalityName() {
        return ApplicationThreadLocals.getMunicipalityName();
    }

    public void sendSMSForStakeHolder(final StakeHolder stakeHolder) {
        String msgKey = MSG_KEY_SMS_STAKEHOLDER_NEW;
        if (isSmsEnabled() && stakeHolder.getMobileNumber() != null) {
            String message = buildMessageDetails(stakeHolder, msgKey);
            messagingService.sendSMS(stakeHolder.getMobileNumber(), message);
        }
    }

    public void sendEmailForStakeHolder(final StakeHolder stakeHolder) {
        String msgKeyMail = BODY_KEY_EMAIL_STAKEHOLDER_NEW;
        String msgKeyMailSubject = SUBJECT_KEY_EMAIL_STAKEHOLDER_NEW;
        if (isEmailEnabled() && stakeHolder.getEmailId() != null) {
            final String message = buildMessageDetails(stakeHolder, msgKeyMail);
            final String subject = bpaMessageSource.getMessage(msgKeyMailSubject, null, null);
            messagingService.sendEmail(stakeHolder.getEmailId(), subject, message);
        }
    }

    public void sendSMSAndEmail(final BpaApplication bpaApplication) {
        String mobileNo;
        String email;
        String applicantName;
        String loginUserName;
        String password;
        if (isSmsEnabled() || isEmailEnabled()) {  
            for (ApplicationStakeHolder applnStakeHolder : bpaApplication.getStakeHolder()) {
                if (applnStakeHolder.getApplication() != null && applnStakeHolder.getApplication().getOwner() != null) {
                    applicantName = applnStakeHolder.getApplication().getOwner().getUser().getName();
                    email = applnStakeHolder.getApplication().getOwner().getUser().getEmailId();
                    mobileNo = applnStakeHolder.getApplication().getOwner().getUser().getMobileNumber();
                    loginUserName = applnStakeHolder.getApplication().getOwner().getUser().getUsername();
                    if(bpaApplication.isMailPwdRequired())
                    	password = mobileNo;
                    else
                    	password = "";
                    buildSmsAndEmailForBPANewAppln(bpaApplication, applicantName, mobileNo, email,loginUserName,password);
                }
                if (applnStakeHolder.getStakeHolder() != null && applnStakeHolder.getStakeHolder().getIsActive()) {
                    applicantName = applnStakeHolder.getStakeHolder().getName();
                    email = applnStakeHolder.getStakeHolder().getEmailId();
                    mobileNo = applnStakeHolder.getStakeHolder().getMobileNumber();
                    loginUserName = applnStakeHolder.getStakeHolder().getUsername();
                   /* if(bpaApplication.isMailPwdRequired()) //Will send password only to citizen.
                    	password = mobileNo;
                    else*/
                    	password = "";
                    buildSmsAndEmailForBPANewAppln(bpaApplication, applicantName, mobileNo, email,loginUserName,password);
                }
            }
        }
    }

    public void sendSMSAndEmailToscheduleAppointment(final BpaAppointmentSchedule scheduleDetails,
            final BpaApplication bpaApplication) {
        if (isSmsEnabled() || isEmailEnabled()) {
            buildSmsAndEmailForScheduleAppointment(scheduleDetails, bpaApplication, bpaApplication.getOwner().getUser().getName(),
                    bpaApplication.getOwner().getUser().getMobileNumber(), bpaApplication.getOwner().getUser().getEmailId());
        }
    }

    public void sendSMSAndEmailToApplicantForLettertoparty(final BpaApplication bpaApplication) {
        if (isSmsEnabled() || isEmailEnabled()) {
            buildSmsAndEmailForBPANewAppln(bpaApplication, bpaApplication.getOwner().getUser().getName(),
                    bpaApplication.getOwner().getUser().getEmailId(),
                    bpaApplication.getOwner().getUser().getMobileNumber(),"","");
        }
    }

    private void buildSmsAndEmailForBPANewAppln(final BpaApplication bpaApplication, final String applicantName,
            final String mobileNo, final String email, final String loginUserName, final String password) {
        String smsMsg = null;
        String body = "";
        String subject = "";
        String smsCode = "";
        String mailCode = "";
        if ((BpaConstants.APPLICATION_STATUS_CREATED).equalsIgnoreCase(bpaApplication.getStatus().getCode()) || 
        		"Registered".equalsIgnoreCase(bpaApplication.getStatus().getCode())) {
        	if(password!=""){
        		smsCode = MSG_KEY_SMS_BPA_APPLN_NEW_PWD;
        		mailCode = BODY_KEY_EMAIL_BPA_APPLN_NEW_PWD;
        	} else{
        		smsCode = MSG_KEY_SMS_BPA_APPLN_NEW;
        		mailCode = BODY_KEY_EMAIL_BPA_APPLN_NEW;
        	}
        	smsMsg = smsBodyByCodeAndArgsWithType(smsCode, applicantName, bpaApplication,
                    SMSEMAILTYPENEWBPAREGISTERED, loginUserName, password);
            body = emailBodyByCodeAndArgsWithType(mailCode, applicantName,
                    bpaApplication, SMSEMAILTYPENEWBPAREGISTERED, loginUserName, password);
            subject = emailSubjectforEmailByCodeAndArgs(SUBJECT_KEY_EMAIL_BPA_APPLN_NEW, bpaApplication.getApplicationNumber());
        } else if (BpaConstants.CREATEDLETTERTOPARTY.equalsIgnoreCase(bpaApplication.getStatus().getCode())) {
            smsMsg = smsBodyByCodeAndArgsWithType(MSG_KEY_SMS_LETTERTOPARTY, applicantName,
                    bpaApplication, BpaConstants.SMSEMAILTYPELETTERTOPARTY,"","");
            body = emailBodyByCodeAndArgsWithType(BODY_KEY_EMAIL_LETTERTOPARTY, applicantName,
                    bpaApplication, BpaConstants.SMSEMAILTYPELETTERTOPARTY, "","");
            subject = emailSubjectforEmailByCodeAndArgs(SUBJECT_KEY_EMAIL_LETTERTOPARTY, bpaApplication.getApplicationNumber());
        }

        if (mobileNo != null && smsMsg != null)
            messagingService.sendSMS(mobileNo, smsMsg);
        if (email != null && body != null)
            messagingService.sendEmail(email, subject, body);
    }

    private void buildSmsAndEmailForScheduleAppointment(final BpaAppointmentSchedule scheduleDetails,
            final BpaApplication bpaApplication, final String applicantName, final String mobileNo, final String email) {
        String smsMsg = null;
        String body = null;
        String subject = null;
        if (AppointmentSchedulePurpose.DOCUMENTSCRUTINY.equals(scheduleDetails.getPurpose())) {
            if (!scheduleDetails.isPostponed()) {
                smsMsg = buildMessageDetailsForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        MSG_KEY_SMS_BPA_DOC_SCRUTINY);
                body = buildMessageDetailsForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        BODY_KEY_EMAIL_BPA_DOC_SCRUTINY);
                subject = emailSubjectforEmailForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        SUBJECT_KEY_EMAIL_BPA_DOC_SCRUTINY);
            } else {
                smsMsg = buildMessageDetailsForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        MSG_KEY_SMS_BPA_DOC_SCRUTINY_RESCHE);
                body = buildMessageDetailsForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        BODY_KEY_EMAIL_BPA_DOC_SCRUTINY_RESCHE);
                subject = emailSubjectforEmailForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        SUBJECT_KEY_EMAIL_BPA_DOC_SCRUTINY_RESCHE);
            }
        } else if (AppointmentSchedulePurpose.INSPECTION.equals(scheduleDetails.getPurpose())) {
            if (!scheduleDetails.isPostponed()) {
                smsMsg = buildMessageDetailsForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        MSG_KEY_SMS_BPA_FIELD_INS);
                body = buildMessageDetailsForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        BODY_KEY_EMAIL_BPA_FIELD_INS);
                subject = emailSubjectforEmailForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        SUBJECT_KEY_EMAIL_BPA_FIELD_INS);
            } else {
                smsMsg = buildMessageDetailsForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        MSG_KEY_SMS_BPA_FIELD_INS_RESCHE);
                body = buildMessageDetailsForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        BODY_KEY_EMAIL_BPA_FIELD_INS_RESCHE);
                subject = emailSubjectforEmailForScheduleAppointment(scheduleDetails, bpaApplication, applicantName,
                        SUBJECT_KEY_EMAIL_BPA_FIELD_INS_RESCHE);
            }
        }
        if (mobileNo != null && smsMsg != null)
            messagingService.sendSMS(mobileNo, smsMsg);
        if (email != null && body != null)
            messagingService.sendEmail(email, subject, body);
    }

    private String buildMessageDetailsForScheduleAppointment(final BpaAppointmentSchedule scheduleDetails,
            final BpaApplication bpaApplication, String applicantName, String msgKey) {
        String mesg = null;
        if (AppointmentSchedulePurpose.DOCUMENTSCRUTINY.equals(scheduleDetails.getPurpose())) {
            if (!scheduleDetails.isPostponed()) {
                mesg = bpaMessageSource.getMessage(msgKey,
                        new String[] { applicantName, scheduleDetails.getAppointmentDate().toString(),
                                scheduleDetails.getAppointmentTime(), scheduleDetails.getAppointmentLocation(),
                                bpaApplication.getApplicationNumber(), getMunicipalityName() },
                        null);
            } else {
                mesg = bpaMessageSource.getMessage(msgKey,
                        new String[] { applicantName, scheduleDetails.getPostponementReason(),
                                scheduleDetails.getAppointmentDate().toString(),
                                scheduleDetails.getAppointmentTime(), scheduleDetails.getAppointmentLocation(),
                                bpaApplication.getApplicationNumber(), getMunicipalityName() },
                        null);
            }
        } else if (AppointmentSchedulePurpose.INSPECTION.equals(scheduleDetails.getPurpose())) {

            if (!scheduleDetails.isPostponed()) {
                mesg = bpaMessageSource.getMessage(msgKey,
                        new String[] { applicantName, scheduleDetails.getAppointmentDate().toString(),
                                scheduleDetails.getAppointmentTime(),
                                bpaApplication.getApplicationNumber(), getMunicipalityName() },
                        null);
            } else {
                mesg = bpaMessageSource.getMessage(msgKey,
                        new String[] { applicantName,
                                scheduleDetails.getAppointmentDate().toString(),
                                scheduleDetails.getAppointmentTime(),
                                bpaApplication.getApplicationNumber(),
                                scheduleDetails.getPostponementReason(), getMunicipalityName() },
                        null);
            }
        }
        return mesg;
    }

    private String emailSubjectforEmailForScheduleAppointment(final BpaAppointmentSchedule scheduleDetails,
            final BpaApplication bpaApplication, String applicantName, String msgKey) {
        final Locale locale = LocaleContextHolder.getLocale();
        return bpaMessageSource.getMessage(msgKey, new String[] { applicantName, scheduleDetails.getAppointmentDate().toString(),
                scheduleDetails.getAppointmentTime(), scheduleDetails.getAppointmentLocation(),
                bpaApplication.getApplicationNumber() }, locale);
    }

    private String emailSubjectforEmailByCodeAndArgs(String code, String applicationNumber) {
        final Locale locale = LocaleContextHolder.getLocale();
        return bpaMessageSource.getMessage(code, new String[] { applicationNumber }, locale);
    }

	private String emailBodyByCodeAndArgsWithType(String code, String applicantName, BpaApplication bpaApplication,
			String type, String loginUserName, String password) {
		String body = "";
		if (SMSEMAILTYPENEWBPAREGISTERED.equalsIgnoreCase(type)){			
			if(password!="")
				body = bpaMessageSource.getMessage(code,
					new String[] { applicantName, bpaApplication.getApplicationNumber(),loginUserName, password, getMunicipalityName() }, null);
			else
				body = bpaMessageSource.getMessage(code,
						new String[] { applicantName, bpaApplication.getApplicationNumber(),loginUserName,getMunicipalityName() }, null);
		}
		else if (BpaConstants.SMSEMAILTYPELETTERTOPARTY.equalsIgnoreCase(type)) 
				body = bpaMessageSource.getMessage(code,
						new String[] { applicantName, bpaApplication.getApplicationNumber(), getMunicipalityName() }, null);
		return body; 
	}

	private String smsBodyByCodeAndArgsWithType(String code, String applicantName, BpaApplication bpaApplication,
			String type, String loginUserName, String password) {
		String smsMsg = "";
		if (SMSEMAILTYPENEWBPAREGISTERED.equalsIgnoreCase(type)){
			if(password!="")
				smsMsg = bpaMessageSource.getMessage(code,
						new String[] { applicantName, bpaApplication.getApplicationNumber(),loginUserName, password, getMunicipalityName() }, null);
			else
				smsMsg = bpaMessageSource.getMessage(code,
						new String[] { applicantName, bpaApplication.getApplicationNumber(),loginUserName,getMunicipalityName() }, null);
		}
		else if (BpaConstants.SMSEMAILTYPELETTERTOPARTY.equalsIgnoreCase(type))
			smsMsg = bpaMessageSource.getMessage(code,
					new String[] { applicantName, bpaApplication.getApplicationNumber(),getMunicipalityName() }, null);
		return smsMsg;
	}

    private String buildMessageDetails(final StakeHolder stakeHolder, String msgKeyMail) {
        return bpaMessageSource.getMessage(msgKeyMail, new String[] { stakeHolder.getName(), stakeHolder.getCode(),
                stakeHolder.getUsername(), stakeHolder.getMobileNumber(), getMunicipalityName() }, null);
    }

    public Boolean isSmsEnabled() {
        return getAppConfigValueByPassingModuleAndType(EGMODULE_NAME, SENDSMSFORBPA);
    }

    public Boolean getAppConfigValueByPassingModuleAndType(String moduleName, String sendsmsoremail) {
        final List<AppConfigValues> appConfigValue = appConfigValuesService.getConfigValuesByModuleAndKey(moduleName,
                sendsmsoremail);
        return "YES".equalsIgnoreCase(
                appConfigValue != null && !appConfigValue.isEmpty() ? appConfigValue.get(0).getValue() : "NO");
    }

    public Boolean isEmailEnabled() {
        return getAppConfigValueByPassingModuleAndType(EGMODULE_NAME, SENDEMAILFORBPA);
    }
}