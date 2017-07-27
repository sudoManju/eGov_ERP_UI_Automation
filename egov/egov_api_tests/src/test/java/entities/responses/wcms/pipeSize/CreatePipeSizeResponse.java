package entities.responses.wcms.pipeSize;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePipeSizeResponse {
    private ResponseInfo ResponseInfo;

    @JsonProperty("PipeSizes")
    private PipeSizes[] PipeSizes;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public PipeSizes[] getPipeSizes() {
        return this.PipeSizes;
    }

    public void setPipeSizes(PipeSizes[] PipeSizes) {
        this.PipeSizes = PipeSizes;
    }
}
