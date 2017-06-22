package builders.wcms.categoryType.create;

import entities.requests.wcms.categoryType.RequestInfo;
import entities.requests.wcms.categoryType.create.Category;
import entities.requests.wcms.categoryType.create.CreateCategoryTypeRequest;

public class CreateCategoryTypeRequestBuilder {

    CreateCategoryTypeRequest createCategoryTypeRequest = new CreateCategoryTypeRequest();

    public CreateCategoryTypeRequestBuilder() {
    }

    public CreateCategoryTypeRequestBuilder withCategory(Category Category) {
        createCategoryTypeRequest.setCategory(Category);
        return this;
    }

    public CreateCategoryTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createCategoryTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateCategoryTypeRequest build() {
        return createCategoryTypeRequest;
    }
}
