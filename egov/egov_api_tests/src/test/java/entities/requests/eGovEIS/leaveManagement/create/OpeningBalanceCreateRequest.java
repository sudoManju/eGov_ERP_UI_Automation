package entities.requests.eGovEIS.leaveManagement.create;

import entities.requests.eGovEIS.leaveManagement.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class OpeningBalanceCreateRequest {

    @JsonProperty("LeaveOpeningBalance")
    private LeaveOpeningBalance[] LeaveOpeningBalance;

    @JsonProperty("RequestInfo1")
    private entities.requests.eGovEIS.leaveManagement.RequestInfo RequestInfo;

    public LeaveOpeningBalance[] getLeaveOpeningBalance() {
        return this.LeaveOpeningBalance;
    }

    public void setLeaveOpeningBalance(LeaveOpeningBalance[] LeaveOpeningBalance) {
        this.LeaveOpeningBalance = LeaveOpeningBalance;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
