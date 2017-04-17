package entities.requests.eGovEIS.hrLeave.openingBalance;

import org.codehaus.jackson.annotate.JsonProperty;

public class OpeningBalanceCreateRequest {

    @JsonProperty("LeaveOpeningBalance")
    private LeaveOpeningBalance[] LeaveOpeningBalance;

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

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
