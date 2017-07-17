package entities.responses.wcms.propertyCategoryType;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreatePropertyCategoryTypeResponse {
    @JsonProperty("responseInfo")
    private ResponseInfo ResponseInfo;

    @JsonProperty("propertyTypeCategoryTypes")
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
