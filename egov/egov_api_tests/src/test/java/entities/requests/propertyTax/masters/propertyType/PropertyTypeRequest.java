package entities.requests.propertyTax.masters.propertyType;

import entities.requests.propertyTax.masters.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class PropertyTypeRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    private PropertyTypes[] propertyTypes;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public PropertyTypes[] getPropertyTypes() {
        return propertyTypes;
    }

    public void setPropertyTypes(PropertyTypes[] propertyTypes) {
        this.propertyTypes = propertyTypes;
    }
}
