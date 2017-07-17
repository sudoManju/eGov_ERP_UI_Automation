package entities.requests.wcms.documentType.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateDocumentTypeRequest {

    @JsonProperty("documentType")
    private DocumentType DocumentType;
    @JsonProperty("requestInfo")
    private RequestInfo RequestInfo;

    public DocumentType getDocumentType() {
        return this.DocumentType;
    }

    public void setDocumentType(DocumentType DocumentType) {
        this.DocumentType = DocumentType;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
