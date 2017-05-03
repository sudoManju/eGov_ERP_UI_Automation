package entities.responses.eGovEIS.employeeMaster.create;

public class LeaveTypeCreateResponse {
    private LeaveType[] LeaveType;
    private entities.responses.eGovEIS.employeeMaster.RequestInfo RequestInfo;

    public LeaveType[] getLeaveType() {
        return this.LeaveType;
    }

    public void setLeaveType(LeaveType[] LeaveType) {
        this.LeaveType = LeaveType;
    }

    public entities.responses.eGovEIS.employeeMaster.RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(entities.responses.eGovEIS.employeeMaster.RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
