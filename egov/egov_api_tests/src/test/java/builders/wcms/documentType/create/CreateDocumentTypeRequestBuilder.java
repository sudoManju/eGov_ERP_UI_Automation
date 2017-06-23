package builders.wcms.documentType.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.documentType.create.CreateDocumentTypeRequest;
import entities.requests.wcms.documentType.create.DocumentType;

public class CreateDocumentTypeRequestBuilder {

    CreateDocumentTypeRequest createDocumentTypeRequest = new CreateDocumentTypeRequest();

    public CreateDocumentTypeRequestBuilder withDocumentType(DocumentType DocumentType) {
        createDocumentTypeRequest.setDocumentType(DocumentType);
        return this;
    }

    public CreateDocumentTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createDocumentTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateDocumentTypeRequest build() {
        return createDocumentTypeRequest;
    }
}
