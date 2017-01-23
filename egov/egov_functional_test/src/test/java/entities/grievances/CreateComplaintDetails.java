package entities.grievances;

/**
 * Created by tester1 on 1/23/2017.
 */
public class CreateComplaintDetails {

    private String citizenName;
    private String citizenMobNo;
    private String emailId;
    private String citizenAddress;


    public void setCitizenname(String citizenname) {
        this.citizenName = citizenname;
    }

    public void setCitizenMobNo(String citizenMobNo) {
        this.citizenMobNo = citizenMobNo;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setCitizenAddress(String citizenAddress) {
        this.citizenAddress = citizenAddress;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public String getcitizenMobNo() {
        return citizenMobNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getcitizenAddress() {
        return citizenAddress;
    }
}
