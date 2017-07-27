package entities.requests.wcms.documentType.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateDocumentTypeRequest {

    @JsonProperty("DocumentType")
    private DocumentType DocumentType;

    @JsonProperty("RequestInfo")
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
