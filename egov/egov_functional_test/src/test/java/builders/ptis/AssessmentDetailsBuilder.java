package builders.ptis;

import entities.ptis.AssessmentDetails;

public class AssessmentDetailsBuilder {

    AssessmentDetails assessmentDetails = new AssessmentDetails();

    public AssessmentDetailsBuilder() {
    }

    public AssessmentDetailsBuilder withReasonForCreation(String reasonForCreation) {
        assessmentDetails.setReasonForCreation(reasonForCreation);
        return this;
    }

    public AssessmentDetailsBuilder withExtentOfSite(String extentOfSite) {
        assessmentDetails.setExtentOfSite(extentOfSite);
        return this;
    }

    public AssessmentDetailsBuilder withOccupancyCertificateNumber(String occupancyCertificateNumber) {
        assessmentDetails.setOccupancyCertificateNumber(occupancyCertificateNumber);
        return this;
    }

    public AssessmentDetailsBuilder withRegistrationDocDate(String registrationDocDate) {
        assessmentDetails.setRegistrationDocDate(registrationDocDate);
        return this;
    }

    public AssessmentDetailsBuilder withRegistrationDocNumber(String registrationDocNumber) {
        assessmentDetails.setRegistrationDocNumber(registrationDocNumber);
        return this;
    }

    public AssessmentDetails build(){
        return assessmentDetails;
    }

}
