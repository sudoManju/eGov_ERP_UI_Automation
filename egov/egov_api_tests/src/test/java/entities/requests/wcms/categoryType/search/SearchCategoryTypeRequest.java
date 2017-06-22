package entities.requests.wcms.categoryType.search;

import entities.requests.wcms.categoryType.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchCategoryTypeRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
