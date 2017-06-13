package entities.requests.assetManagement;

import org.codehaus.jackson.annotate.JsonProperty;

public class SearchAssetServiceRequest {

    @JsonProperty("RequestInfo")
    RequestInfo requestInfo;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
