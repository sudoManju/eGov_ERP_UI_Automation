package entities.responses.wcms.propertyCategoryType;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePropertyCategoryTypeResponse {
    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;

    @JsonProperty("PropertyTypeCategoryTypes")
    private PropertyTypeCategoryTypes[] propertyCategories;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public PropertyTypeCategoryTypes[] getPropertyCategories() {
        return this.propertyCategories;
    }

    public void setPropertyCategories(PropertyTypeCategoryTypes[] propertyCategories) {
        this.propertyCategories = propertyCategories;
    }
}
