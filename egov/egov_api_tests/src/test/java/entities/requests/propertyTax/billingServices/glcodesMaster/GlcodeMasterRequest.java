package entities.requests.propertyTax.billingServices.glcodesMaster;

import entities.requests.propertyTax.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class GlcodeMasterRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    private GlcodeMasters[] glcodeMasters;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public GlcodeMasters[] getGlcodeMasters() {
        return glcodeMasters;
    }

    public void setGlcodeMasters(GlcodeMasters[] glcodeMasters) {
        this.glcodeMasters = glcodeMasters;
    }
}
