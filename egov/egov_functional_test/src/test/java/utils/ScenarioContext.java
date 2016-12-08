package utils;

import pages.ptis.EditPropertyAcknowledgementPage;

public class ScenarioContext {

    private String applicationNumber;

    private String assessmentNumber;

    private String challanNumber = "12/2016-17/43";

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

//    public void getAssessmentNumber(EditPropertyAcknowledgementPage assessmentNumber) {
//        {return assessmentNumber;}
//
//            public void setAssessmentNumber (String assessmentNumber)
//        {this.assessmentNumber = assessmentNumber;   }

    }

