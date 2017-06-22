package entities.responses.wcms.pipeSize;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePipeSizeResponse {
    private ResponseInfo ResponseInfo;

    @JsonProperty("PipeSize")
    private PipeSize[] PipeSize;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public PipeSize[] getPipeSize() {
        return this.PipeSize;
    }

    public void setPipeSize(PipeSize[] PipeSize) {
        this.PipeSize = PipeSize;
    }
}
