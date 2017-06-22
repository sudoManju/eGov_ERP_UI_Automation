package entities.requests.wcms.categoryType.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateCategoryTypeRequest {

    @JsonProperty("Category")
    private Category Category;

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public entities.requests.wcms.categoryType.create.Category getCategory() {
        return this.Category;
    }

    public void setCategory(entities.requests.wcms.categoryType.create.Category Category) {
        this.Category = Category;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
