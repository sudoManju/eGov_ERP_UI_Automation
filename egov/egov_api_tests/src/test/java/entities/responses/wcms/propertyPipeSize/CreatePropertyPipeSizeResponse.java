package entities.responses.wcms.propertyPipeSize;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePropertyPipeSizeResponse {
    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;
    @JsonProperty("PropertyTypePipeSizes")
    private PropertyTypePipeSizes[] PropertyTypePipeSize;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public PropertyTypePipeSizes[] getPropertyTypePipeSize() {
        return this.PropertyTypePipeSize;
    }

    public void setPropertyTypePipeSize(PropertyTypePipeSizes[] PropertyTypePipeSize) {
        this.PropertyTypePipeSize = PropertyTypePipeSize;
    }
}
