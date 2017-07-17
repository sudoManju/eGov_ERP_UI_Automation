package entities.requests.wcms.supplyType.search;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchSupplyTypeRequest {

    @JsonProperty("requestInfo")
    private RequestInfo RequestInfo;

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
