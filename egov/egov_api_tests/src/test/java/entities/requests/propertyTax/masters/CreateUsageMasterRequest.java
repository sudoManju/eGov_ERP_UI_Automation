package entities.requests.propertyTax.masters;

import org.codehaus.jackson.annotate.JsonProperty;

public class CreateUsageMasterRequest {

    private UsageMasters[] usageMasters;

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public UsageMasters[] getUsageMasters() {
        return this.usageMasters;
    }

    public void setUsageMasters(UsageMasters[] usageMasters) {
        this.usageMasters = usageMasters;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
