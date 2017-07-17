package entities.requests.wcms.propertyCategoryType.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePropertyCategoryTypeRequest {

    @JsonProperty("propertyTypeCategoryType")
    private PropertyTypeCategoryType PropertyTypeCategoryType;
    private RequestInfo RequestInfo;

    public PropertyTypeCategoryType getPropertyTypeCategoryType() {
        return this.PropertyTypeCategoryType;
    }

    public void setPropertyTypeCategoryType(PropertyTypeCategoryType PropertyTypeCategoryType) {
        this.PropertyTypeCategoryType = PropertyTypeCategoryType;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
