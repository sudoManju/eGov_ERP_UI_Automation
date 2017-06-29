package builders.wcms.documentTypeApplicationType.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.documentTypeApplicationType.create.CreateDocumentTypeApplicationTypeRequest;
import entities.requests.wcms.documentTypeApplicationType.create.DocumentTypeApplicationType;

public class CreateDocumentTypeApplicationTypeRequestBuilder {
    CreateDocumentTypeApplicationTypeRequest createDocumentTypeApplicationTypeRequest = new CreateDocumentTypeApplicationTypeRequest();

    public CreateDocumentTypeApplicationTypeRequestBuilder withDocumentTypeApplicationType(DocumentTypeApplicationType DocumentTypeApplicationType) {
        createDocumentTypeApplicationTypeRequest.setDocumentTypeApplicationType(DocumentTypeApplicationType);
        return this;
    }

    public CreateDocumentTypeApplicationTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createDocumentTypeApplicationTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateDocumentTypeApplicationTypeRequest build() {
        return createDocumentTypeApplicationTypeRequest;
    }
}
