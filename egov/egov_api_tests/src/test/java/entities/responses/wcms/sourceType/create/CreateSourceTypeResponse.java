package entities.responses.wcms.sourceType.create;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateSourceTypeResponse {
    private ResponseInfo ResponseInfo;

    @JsonProperty("sourceTypes")
    private SourceTypes[] sourceTypes;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public SourceTypes[] getSourceTypes() {
        return this.sourceTypes;
    }

    public void setSourceTypes(SourceTypes[] sourceTypes) {
        this.sourceTypes = sourceTypes;
    }
}
