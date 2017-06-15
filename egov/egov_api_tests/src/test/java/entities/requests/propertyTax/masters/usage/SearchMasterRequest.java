package entities.requests.propertyTax.masters.usage;

import entities.requests.propertyTax.masters.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchMasterRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
