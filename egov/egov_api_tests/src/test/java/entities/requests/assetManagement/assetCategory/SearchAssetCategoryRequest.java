package entities.requests.assetManagement.assetCategory;

import org.codehaus.jackson.annotate.JsonProperty;

public class SearchAssetCategoryRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
