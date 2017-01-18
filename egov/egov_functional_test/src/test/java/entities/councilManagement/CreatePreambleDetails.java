package entities.councilManagement;

/**
 * Created by tester1 on 1/4/2017.
 */
public class CreatePreambleDetails {


    private String preambleDepartment;
    private String amount;
    private String gistOfPreamble;
    private String createPreambleDetails;
    private String preambleNumber;
    private String committeeType;
    private String councilMeetingDate;
    private String councilMeetingTime;
    private String councilMeetingPlace;

    public void setPreambleDepartment(String preambleDepartment) {
        this.preambleDepartment = preambleDepartment;
    }
    public String getPreambleDepartment() {
        return preambleDepartment;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAmount() {
        return amount;
    }


    public String getGistOfPreamble() {
        return gistOfPreamble;
    }
    public void setGistOfPreamble(String gistOfPreamble) {
        this.gistOfPreamble = gistOfPreamble;
    }


    public String getPreambleNumber() { return preambleNumber; }
    public void setPreambleNumber(String preambleNumber) {
        this.preambleNumber = preambleNumber;
    }

    public String build() {
        return createPreambleDetails;
    }


    public String getCommitteeType() {
        return committeeType;
    }

    public void setCommitteeType(String committeeType) {
        this.committeeType = committeeType;
    }

    public void setCouncilMeetingDate(String councilMeetingDate) {
        this.councilMeetingDate = councilMeetingDate;
    }

    public void setCouncilMeetingTime(String councilMeetingTime) {
        this.councilMeetingTime = councilMeetingTime;
    }

    public void setCouncilMeetingPlace(String councilMeetingPlace) {
        this.councilMeetingPlace = councilMeetingPlace;
    }


    public String getcouncilMeetingDate() { return councilMeetingDate; }


    public String getCouncilMeetingTime() {
        return councilMeetingTime;
    }

    public String getCouncilMeetingPlace() {
        return councilMeetingPlace;
    }
}
