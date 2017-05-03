package entities.responses.eGovEIS.employeeMaster.create;

import entities.responses.eGovEIS.employeeMaster.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class LeaveTypeCreateResponse {

    @JsonProperty("LeaveType")
    private LeaveType[] LeaveType;

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public LeaveType[] getLeaveType() {
        return this.LeaveType;
    }

    public void setLeaveType(LeaveType[] LeaveType) {
        this.LeaveType = LeaveType;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
