package entities.ptis;

/**
 * Created by karthik on 22/11/16.
 */
public class ApplicantDetails {

   private String assessmentNumber;
   private String hscNumber;
   private String connectionDate;

    public String getAssessmentNumber() {
        return assessmentNumber;
    }

    public void setAssessmentNumber(String assessmentNumber) {
        this.assessmentNumber = assessmentNumber;
    }

    public String getHscNumber() {
        return hscNumber;
    }

    public void setHscNumber(String hsrNumber) {
        this.hscNumber = hsrNumber;
    }

    public String getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(String connectionDate) {
        this.connectionDate = connectionDate;
    }
}
