package entities.ptis;

import java.util.Date;
import java.util.stream.Stream;

/**
 * Created by vinaykumar on 22/11/16.
 */
public class ApplicantInfo {

    private String ptAssessmentNumber;
    private String hscNumber;
    private String connectionDate;

    public String getPtAssessmentNumber() {
        return ptAssessmentNumber;
    }

    public void setPtAssessmentNumber(String ptAssessmentNumber) {
        this.ptAssessmentNumber = ptAssessmentNumber;
    }

    public String getHscNumber() {
        return hscNumber;
    }

    public void setHscNumber(String hscNumber) {
        this.hscNumber = hscNumber;
    }

    public String getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(String connectionDate) {
        this.connectionDate = connectionDate;
    }
}
