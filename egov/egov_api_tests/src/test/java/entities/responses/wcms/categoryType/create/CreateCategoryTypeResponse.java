package entities.responses.wcms.categoryType.create;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateCategoryTypeResponse {

    private ResponseInfo ResponseInfo;
    @JsonProperty("categoryTypes")
    private CategoryTypes[] Category;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public CategoryTypes[] getCategory() {
        return this.Category;
    }

    public void setCategory(CategoryTypes[] Category) {
        this.Category = Category;
    }
}
