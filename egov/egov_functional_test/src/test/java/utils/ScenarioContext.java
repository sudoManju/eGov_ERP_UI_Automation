package utils;

import pages.ptis.EditPropertyAcknowledgementPage;

public class ScenarioContext {

    private String applicationNumber;

    private String assessmentNumber;

    private String   dataScreenAssessmentNumber;

    private String challanNumber;

    private String voucherNumber;

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

}

