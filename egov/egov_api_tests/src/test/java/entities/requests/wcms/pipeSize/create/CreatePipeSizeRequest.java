package entities.requests.wcms.pipeSize.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePipeSizeRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;
    @JsonProperty("PipeSize")
    private PipeSize PipeSize;

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }

    public PipeSize getPipeSize() {
        return this.PipeSize;
    }

    public void setPipeSize(PipeSize PipeSize) {
        this.PipeSize = PipeSize;
    }
}
