package entities.requests.wcms.propertyPipeSize.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePropertyPipeSizeRequest {
    @JsonProperty("PropertyPipeSize")
    private PropertyPipeSize PropertyPipeSize;
    private RequestInfo RequestInfo;

    public PropertyPipeSize getPropertyPipeSize() {
        return this.PropertyPipeSize;
    }

    public void setPropertyPipeSize(PropertyPipeSize PropertyPipeSize) {
        this.PropertyPipeSize = PropertyPipeSize;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
