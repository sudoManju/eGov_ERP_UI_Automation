package entities.responses.wcms.propertyCategoryType;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePropertyCategoryTypeResponse {
    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;

    @JsonProperty("PropertyCategories")
    private PropertyCategories[] propertyCategories;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public PropertyCategories[] getPropertyCategories() {
        return this.propertyCategories;
    }

    public void setPropertyCategories(PropertyCategories[] propertyCategories) {
        this.propertyCategories = propertyCategories;
    }
}
