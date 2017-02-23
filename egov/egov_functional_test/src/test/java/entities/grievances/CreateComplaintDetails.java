package entities.grievances;

public class CreateComplaintDetails {

    private String citizenName;
    private String citizenMobNo;
    private String emailId;
    private String grievanceCategory;
    private String grievanceType;
    private String grievanceDetails;
    private String grievanceLocation;
    private String locationLandmark;
    private String processingStatus;

    public void setCitizenname(String citizenname) {
        this.citizenName = citizenname;
    }

    public void setCitizenMobNo(String citizenMobNo) {
        this.citizenMobNo = citizenMobNo;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public void setGrievanceCategory(String grievanceCategory) {
        this.grievanceCategory = grievanceCategory;
    }

    public void setGrievanceType(String grievanceType) {
        this.grievanceType = grievanceType;
    }

    public void setGrievanceDetails(String grievanceDetails) {
        this.grievanceDetails = grievanceDetails;
    }

    public void setGrievanceLocation(String grievanceLocation) {
        this.grievanceLocation = grievanceLocation;
    }

    public void setLocationLandmark(String locationLandmark) {
        this.locationLandmark = locationLandmark;
    }

    public String getGrievanceCategory() {
        return grievanceCategory;
    }

    public String getGrievanceType() {
        return grievanceType;
    }

    public String getGrievanceDetails() {
        return grievanceDetails;
    }

    public String getGrievanceLocation() {
        return grievanceLocation;
    }

    public String getLocationLandmark() {
        return locationLandmark;
    }

}
