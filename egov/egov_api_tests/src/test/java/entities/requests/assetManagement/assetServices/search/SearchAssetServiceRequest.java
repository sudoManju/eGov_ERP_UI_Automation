package entities.requests.assetManagement.assetServices.search;

import entities.requests.assetManagement.RequestInfo;
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