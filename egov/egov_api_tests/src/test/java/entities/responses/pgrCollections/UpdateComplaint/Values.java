package entities.responses.pgrCollections.UpdateComplaint;

public class Values {
    private Object complaintStatus;
    private String locationId;
    private String receivingMode;

    public Object getComplaintStatus() {
        return this.complaintStatus;
    }

    public void setComplaintStatus(Object complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public String getLocationId() {
        return this.locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getReceivingMode() {
        return this.receivingMode;
    }

    public void setReceivingMode(String receivingMode) {
        this.receivingMode = receivingMode;
    }
}
