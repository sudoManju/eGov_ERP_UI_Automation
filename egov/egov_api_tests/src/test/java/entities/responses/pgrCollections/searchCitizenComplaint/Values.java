package entities.responses.pgrCollections.searchCitizenComplaint;

public class Values {
    private String complaintStatus;
    private String locationId;
    private String stateId;
    private String departmentId;
    private String receivingMode;
    private String childLocationId;
    private String assigneeId;

    public String getComplaintStatus() {
        return this.complaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public String getLocationId() {
        return this.locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getStateId() {
        return this.stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getReceivingMode() {
        return this.receivingMode;
    }

    public void setReceivingMode(String receivingMode) {
        this.receivingMode = receivingMode;
    }

    public String getChildLocationId() {
        return this.childLocationId;
    }

    public void setChildLocationId(String childLocationId) {
        this.childLocationId = childLocationId;
    }

    public String getAssigneeId() {
        return this.assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }
}
