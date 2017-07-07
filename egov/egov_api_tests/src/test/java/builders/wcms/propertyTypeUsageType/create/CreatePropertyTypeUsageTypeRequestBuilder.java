package builders.wcms.propertyTypeUsageType.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyTypeUsageType.create.CreatePropertyTypeUsageTypeRequest;
import entities.requests.wcms.propertyTypeUsageType.create.PropertyTypeUsageType;

public class CreatePropertyTypeUsageTypeRequestBuilder {
    CreatePropertyTypeUsageTypeRequest createPropertyTypeUsageTypeRequest = new CreatePropertyTypeUsageTypeRequest();

    public CreatePropertyTypeUsageTypeRequestBuilder withPropertyTypeUsageType(PropertyTypeUsageType propertyTypeUsageType) {
        createPropertyTypeUsageTypeRequest.setPropertyTypeUsageType(propertyTypeUsageType);
        return this;
    }

    public CreatePropertyTypeUsageTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createPropertyTypeUsageTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreatePropertyTypeUsageTypeRequest build() {
        return createPropertyTypeUsageTypeRequest;
    }
}
