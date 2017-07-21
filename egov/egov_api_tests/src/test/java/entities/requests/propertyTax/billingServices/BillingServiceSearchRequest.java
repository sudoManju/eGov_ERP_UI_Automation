package entities.requests.propertyTax.billingServices;

import org.codehaus.jackson.annotate.JsonProperty;

public class BillingServiceSearchRequest {

    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
