package entities.responses.eGovEIS.hrMaster.position.search;

import org.codehaus.jackson.annotate.JsonProperty;

public class HRMasterPositionSearchRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
