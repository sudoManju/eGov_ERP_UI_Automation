package entities.responses.wcms.documentTypeApplicationType;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateDocumentTypeApplicationTypeResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;

    @JsonProperty("DocumentTypeApplicationTypes")
    private DocumentTypeApplicationTypes[] DocumentTypeApplicationTypes;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public DocumentTypeApplicationTypes[] getDocumentTypeApplicationTypes() {
        return this.DocumentTypeApplicationTypes;
    }

    public void setDocumentTypeApplicationTypes(DocumentTypeApplicationTypes[] DocumentTypeApplicationTypes) {
        this.DocumentTypeApplicationTypes = DocumentTypeApplicationTypes;
    }
}
