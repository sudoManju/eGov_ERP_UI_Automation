package builders.propertyTax.masters.propertyType;

import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.masters.propertyType.PropertyTypeRequest;
import entities.requests.propertyTax.masters.propertyType.PropertyTypes;

public class PropertyTypesRequestBuilder {

    PropertyTypeRequest request = new PropertyTypeRequest();

    public PropertyTypesRequestBuilder() {
    }

    public PropertyTypesRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        request.setRequestInfo(requestInfo);
        return this;
    }

    public PropertyTypesRequestBuilder withPropertyTypes(PropertyTypes[] propertyTypes) {
        request.setPropertyTypes(propertyTypes);
        return this;
    }

    public PropertyTypeRequest build() {
        return request;
    }
}
