package entities.responses.wcms.documentType;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateDocumentTypeResponse {

    @JsonProperty("ResponseInfo")
    private ResponseInfo ResponseInfo;
    @JsonProperty("DocumentTypes")
    private DocumentTypes[] documentTypes;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public DocumentTypes[] getDocumentTypes() {
        return this.documentTypes;
    }

    public void setDocumentTypes(DocumentTypes[] documentTypes) {
        this.documentTypes = documentTypes;
    }
}
