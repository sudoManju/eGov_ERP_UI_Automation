package entities.requests.wcms.sourceType.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateSourceTypeRequest {
    @JsonProperty("sourceType")
    private SourceType sourceType;

    @JsonProperty("requestInfo")
    private RequestInfo RequestInfo;

    public SourceType getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
