package entities.requests.wcms.propertyCategoryType.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePropertyCategoryTypeRequest {

    @JsonProperty("PropertyCategory")
    private PropertyCategory PropertyCategory;
    private RequestInfo RequestInfo;

    public PropertyCategory getPropertyCategory() {
        return this.PropertyCategory;
    }

    public void setPropertyCategory(PropertyCategory PropertyCategory) {
        this.PropertyCategory = PropertyCategory;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
