package utils;

import java.io.*;

public class ScenarioContext implements Serializable {

    private String applicationNumber;

    private String assessmentNumber;

    private String dataScreenAssessmentNumber;

    private String challanNumber;

    private String voucherNumber;

    private String workNumber;

    private String loaNumber;

    private String actualMessage;

    private String estimateNumber;

    private String preambleNumber;

    private int isRemittance;

    private String contractorBillNumber;

    private String consumerNumber;

    private String agendaNumber;

    private String meetingNumber;
    
    private String licenseNumber;

    private String user;

    private String previousUser;

    private String CRN;

    public String getPreviousUser() {
        return previousUser;
    }

    public void setPreviousUser(String previousUser) {
        this.previousUser = previousUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(String consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public String getContractorBillNumber() {
        return contractorBillNumber;
    }

    public void setContractorBillNumber(String contractorBillNumber) {
        this.contractorBillNumber = contractorBillNumber;
    }

    public int getIsRemittance() {
        return isRemittance;
    }

    public void setIsRemittance(int isRemittance) {
        this.isRemittance = isRemittance;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getLoaNumber() {
        return loaNumber;
    }

    public String getActualMessage() {
        return actualMessage;
    }

    public void setActualMessage(String actualMessage) {
        this.actualMessage = actualMessage;
    }

    public void setLoaNumber(String loaNumber) {
        this.loaNumber = loaNumber;

    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getChallanNumber() {
        return challanNumber;
    }

    public void setChallanNumber(String challanNumber) {
        this.challanNumber = challanNumber;
    }

    public String getAssessmentNumber() {
        return assessmentNumber;
    }

    public void setAssessmentNumber(String assessmentNumber) {
        this.assessmentNumber = assessmentNumber;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getDataScreenAssessmentNumber() {
        return dataScreenAssessmentNumber;
    }

    public void setDataScreenAssessmentNumber(String dataScreenAssessmentNumber) {
        this.dataScreenAssessmentNumber = dataScreenAssessmentNumber;
    }

    public String getEstimateNumber() {
        return estimateNumber;
    }

    public void setEstimateNumber(String estimateNumber) {
        this.estimateNumber = estimateNumber;
    }

    public void setPreambleNumber(String preambleNumber) {
        this.preambleNumber = preambleNumber;
    }

    public String getPreambleNumber() {
        return preambleNumber;
    }

    public void setAgendaNumber(String agendaNumber) {
        this.agendaNumber = agendaNumber;
    }

    public String getAgendaNumber() { return agendaNumber; }


    public void setMeetingNumber(String meetingNumber) { this.meetingNumber = meetingNumber; }

    public String getMeetingNumber() {
        return meetingNumber;
    }


    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }


    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setCRN(String CRN) {
        this.CRN = CRN;
    }

    public String getCRN() {
        return CRN;
    }
}

