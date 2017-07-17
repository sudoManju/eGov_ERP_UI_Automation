package entities.requests.wcms.propertyPipeSize.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePropertyPipeSizeRequest {
    @JsonProperty("propertyTypePipeSize")
    private PropertyTypePipeSize PropertyTypePipeSize;
    private RequestInfo RequestInfo;

    public PropertyTypePipeSize getPropertyTypePipeSize() {
        return this.PropertyTypePipeSize;
    }

    public void setPropertyTypePipeSize(PropertyTypePipeSize PropertyTypePipeSize) {
        this.PropertyTypePipeSize = PropertyTypePipeSize;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
