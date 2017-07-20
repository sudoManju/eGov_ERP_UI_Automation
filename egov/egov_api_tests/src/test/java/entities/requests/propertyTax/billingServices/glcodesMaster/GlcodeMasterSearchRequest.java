package entities.requests.propertyTax.billingServices.glcodesMaster;

import entities.requests.propertyTax.billingServices.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class GlcodeMasterSearchRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
