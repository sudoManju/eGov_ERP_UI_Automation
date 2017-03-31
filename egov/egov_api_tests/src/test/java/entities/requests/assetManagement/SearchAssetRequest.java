package entities.requests.assetManagement;


import entities.requests.assetManagement.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchAssetRequest {

    @JsonProperty("RequestInfo")
    RequestInfo requestInfo;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
