package builders.ptis;

import entities.ptis.ApplicantInfo;
import entities.ptis.PropertyAddressDetails;

/**
 * Created by vinaykumar on 22/11/16.
 */
public class ApplicantInfoBuilder {

    ApplicantInfo applicantInfo = new ApplicantInfo();

    public ApplicantInfoBuilder withPTAssessmentNumber(String ptNumber) {
        applicantInfo.setPtAssessmentNumber(ptNumber);
        return this;
    }

    public ApplicantInfoBuilder withHSCNumber(String hscNumber) {
        applicantInfo.setHscNumber(hscNumber);
        return this;
    }

    public ApplicantInfoBuilder withConnectionDate(String conDate) {
        applicantInfo.setConnectionDate(conDate);
        return this;
    }

    public ApplicantInfo build(){
        return applicantInfo;
    }


}
