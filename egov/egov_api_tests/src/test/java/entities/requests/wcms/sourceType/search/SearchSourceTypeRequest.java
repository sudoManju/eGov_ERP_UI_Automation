package entities.requests.wcms.sourceType.search;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchSourceTypeRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
