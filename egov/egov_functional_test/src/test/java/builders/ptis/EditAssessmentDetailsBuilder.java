package builders.ptis;


import entities.ptis.EditAssessmentDetails;

/**
 * Created by bimal on 23/11/16.
 */
public class EditAssessmentDetailsBuilder {

    EditAssessmentDetails editAssesmentDetails = new EditAssessmentDetails();


    public EditAssessmentDetailsBuilder() {
        editAssesmentDetails.setExtentOfSite("100");
    }

    public EditAssessmentDetails build() {
        return editAssesmentDetails;
    }

    public EditAssessmentDetailsBuilder withExtentOfSite(String extentOfSite) {
        editAssesmentDetails.setExtentOfSite(extentOfSite);
        return this;
    }

    public EditAssessmentDetailsBuilder withOccupancyCertificateNumber(String certificateNumber) {
        editAssesmentDetails.setOccupancyCertificateNumber(certificateNumber);
        return this;
    }
}
