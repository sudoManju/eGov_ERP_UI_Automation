package entities.requests.wcms.documentTypeApplicationType.create;

import entities.requests.wcms.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateDocumentTypeApplicationTypeRequest {

    @JsonProperty("documentTypeApplicationType")
    private DocumentTypeApplicationType DocumentTypeApplicationType;
    @JsonProperty("requestInfo")
    private RequestInfo RequestInfo;

    public DocumentTypeApplicationType getDocumentTypeApplicationType() {
        return this.DocumentTypeApplicationType;
    }

    public void setDocumentTypeApplicationType(DocumentTypeApplicationType DocumentTypeApplicationType) {
        this.DocumentTypeApplicationType = DocumentTypeApplicationType;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
