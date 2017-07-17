package entities.requests.wcms.pipeSize.search;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchPipeSizeRequest {

    @JsonProperty("requestInfo")
    private RequestInfo RequestInfo;

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
