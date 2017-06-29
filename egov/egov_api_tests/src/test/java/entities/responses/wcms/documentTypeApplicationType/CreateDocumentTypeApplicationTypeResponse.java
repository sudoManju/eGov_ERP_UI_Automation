package entities.responses.wcms.documentTypeApplicationType;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateDocumentTypeApplicationTypeResponse {
    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;
    @JsonProperty("DocumentTypeApplicationType")
    private DocumentTypeApplicationType[] DocumentTypeApplicationType;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public DocumentTypeApplicationType[] getDocumentTypeApplicationType() {
        return this.DocumentTypeApplicationType;
    }

    public void setDocumentTypeApplicationType(DocumentTypeApplicationType[] DocumentTypeApplicationType) {
        this.DocumentTypeApplicationType = DocumentTypeApplicationType;
    }
}
