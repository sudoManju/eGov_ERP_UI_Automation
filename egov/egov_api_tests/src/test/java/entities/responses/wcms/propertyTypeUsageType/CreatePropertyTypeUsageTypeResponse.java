package entities.responses.wcms.propertyTypeUsageType;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePropertyTypeUsageTypeResponse {
    private ResponseInfo ResponseInfo;
    @JsonProperty("propertyTypeUsageTypes")
    private PropertyTypeUsageTypes[] PropertyTypeUsageTypes;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public PropertyTypeUsageTypes[] getPropertyTypeUsageTypes() {
        return this.PropertyTypeUsageTypes;
    }

    public void setPropertyTypeUsageTypes(PropertyTypeUsageTypes[] PropertyTypeUsageTypes) {
        this.PropertyTypeUsageTypes = PropertyTypeUsageTypes;
    }
}
