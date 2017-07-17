package builders.wcms.propertyCategoryType.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyCategoryType.create.CreatePropertyCategoryTypeRequest;
import entities.requests.wcms.propertyCategoryType.create.PropertyTypeCategoryType;

public class CreatePropertyCategoryTypeRequestBuilder {

    CreatePropertyCategoryTypeRequest createPropertyCategoryTypeRequest = new CreatePropertyCategoryTypeRequest();

    public CreatePropertyCategoryTypeRequestBuilder withPropertyCategory(PropertyTypeCategoryType PropertyTypeCategoryType) {
        createPropertyCategoryTypeRequest.setPropertyTypeCategoryType(PropertyTypeCategoryType);
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
