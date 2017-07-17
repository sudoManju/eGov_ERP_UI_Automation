package entities.requests.wcms.categoryType.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateCategoryTypeRequest {

    @JsonProperty("categoryType")
    private CategoryType category;
    private RequestInfo requestInfo;

    public entities.requests.wcms.categoryType.create.CategoryType getCategory() {
        return this.category;
    }

    public void setCategory(entities.requests.wcms.categoryType.create.CategoryType Category) {
        this.category = Category;
    }

    public RequestInfo getRequestInfo() {
        return this.requestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.requestInfo = RequestInfo;
    }
}
