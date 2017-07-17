package builders.wcms.propertyPipeSize.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyPipeSize.create.CreatePropertyPipeSizeRequest;
import entities.requests.wcms.propertyPipeSize.create.PropertyTypePipeSize;

public class CreatePropertyPipeSizeRequestBuilder {
    CreatePropertyPipeSizeRequest createPropertyPipeSizeRequest = new CreatePropertyPipeSizeRequest();

    public CreatePropertyPipeSizeRequestBuilder withPropertyPipeSize(PropertyTypePipeSize PropertyTypePipeSize) {
        createPropertyPipeSizeRequest.setPropertyTypePipeSize(PropertyTypePipeSize);
        return this;
    }

    public CreatePropertyPipeSizeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createPropertyPipeSizeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreatePropertyPipeSizeRequest build() {
        return createPropertyPipeSizeRequest;
    }
}
