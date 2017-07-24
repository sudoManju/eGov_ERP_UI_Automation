package entities.responses.wcms.searchBoundary;

import entities.responses.wcms.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class SearchBoundaryResponse {
    private ResponseInfo ResponseInfo;

    @JsonProperty("Boundary")
    private Boundary[] Boundary;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public Boundary[] getBoundary() {
        return this.Boundary;
    }

    public void setBoundary(Boundary[] Boundary) {
        this.Boundary = Boundary;
    }
}
