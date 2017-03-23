package entities.responses.commonMaster.category;

import entities.responses.commonMaster.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CategoryResponse {
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
