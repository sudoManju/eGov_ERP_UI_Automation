package builders.wcms.sourceType.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.sourceType.create.CreateSourceTypeRequest;
import entities.requests.wcms.sourceType.create.SourceType;

public class CreateSourceTypeRequestBuilder {
    CreateSourceTypeRequest createSourceTypeRequest = new CreateSourceTypeRequest();

    public CreateSourceTypeRequestBuilder withSourceType(SourceType sourceType) {
        createSourceTypeRequest.setSourceType(sourceType);
        return this;
    }

    public CreateSourceTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createSourceTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateSourceTypeRequest build() {
        return createSourceTypeRequest;
    }
}
