package entities.responses.wcms.categoryType.create;

import entities.responses.wcms.categoryType.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateCategoryTypeResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;

    @JsonProperty("Category")
    private Category[] Category;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public Category[] getCategory() {
        return this.Category;
    }

    public void setCategory(Category[] Category) {
        this.Category = Category;
    }
}
