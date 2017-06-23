package builders.wcms.categoryType.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.categoryType.create.CategoryType;
import entities.requests.wcms.categoryType.create.CreateCategoryTypeRequest;

public class CreateCategoryTypeRequestBuilder {

    CreateCategoryTypeRequest createCategoryTypeRequest = new CreateCategoryTypeRequest();

    public CreateCategoryTypeRequestBuilder() {
    }

    public CreateCategoryTypeRequestBuilder withCategory(CategoryType Category) {
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
