package builders.wcms.propertyCategoryType.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyCategoryType.create.CreatePropertyCategoryTypeRequest;
import entities.requests.wcms.propertyCategoryType.create.PropertyCategory;

public class CreatePropertyCategoryTypeRequestBuilder {

    CreatePropertyCategoryTypeRequest createPropertyCategoryTypeRequest = new CreatePropertyCategoryTypeRequest();

    public CreatePropertyCategoryTypeRequestBuilder withPropertyCategory(PropertyCategory PropertyCategory) {
        createPropertyCategoryTypeRequest.setPropertyCategory(PropertyCategory);
        return this;
    }

    public CreatePropertyCategoryTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createPropertyCategoryTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreatePropertyCategoryTypeRequest build() {
        return createPropertyCategoryTypeRequest;
    }
}
