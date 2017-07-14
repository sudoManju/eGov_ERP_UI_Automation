package entities.requests.assetManagement.assetRevaluation.create;

import entities.requests.assetManagement.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateAssetRevaluationRequest {
    @JsonProperty("Revaluation")
    private Revaluation Revaluation;

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public Revaluation getRevaluation() {
        return this.Revaluation;
    }

    public void setRevaluation(Revaluation Revaluation) {
        this.Revaluation = Revaluation;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
