package entities.requests.wcms.propertyCategoryType.search;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchPropertyCategoryTypeRequest {
    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
