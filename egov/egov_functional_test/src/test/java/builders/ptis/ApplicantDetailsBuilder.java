package builders.ptis;

import entities.ptis.ApplicantDetails;

/**
 * Created by karthik on 22/11/16.
 */
public class ApplicantDetailsBuilder {

  ApplicantDetails applicantDetails = new ApplicantDetails();

  public ApplicantDetailsBuilder withAssessmentNumber(String assessmentNumber){
      applicantDetails.setAssessmentNumber(assessmentNumber);
      return this;
  }

  public ApplicantDetailsBuilder withHscNumber(String hscNumber){
      applicantDetails.setHscNumber(hscNumber);
      return this;
  }

  public ApplicantDetailsBuilder withConnectionDate(String connectionDate){
      applicantDetails.setConnectionDate(connectionDate);
      return this;
  }

  public ApplicantDetailsBuilder(){}

  public ApplicantDetails build(){
      return applicantDetails;
  }

}
