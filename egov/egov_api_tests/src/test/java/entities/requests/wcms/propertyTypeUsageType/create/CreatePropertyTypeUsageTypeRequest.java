package entities.requests.wcms.propertyTypeUsageType.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePropertyTypeUsageTypeRequest {

    @JsonProperty("PropertyTypeUsageType")
    private PropertyTypeUsageType propertyTypeUsageType;
    private RequestInfo RequestInfo;

    public PropertyTypeUsageType getPropertyTypeUsageType() {
        return this.propertyTypeUsageType;
    }

    public void setPropertyTypeUsageType(PropertyTypeUsageType propertyTypeUsageType) {
        this.propertyTypeUsageType = propertyTypeUsageType;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
