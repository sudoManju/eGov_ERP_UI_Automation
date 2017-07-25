package entities.requests.propertyTax.masters.usage;

import entities.requests.propertyTax.RequestInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class UsageMasterRequest {

    private UsageMasters[] usageMasters;

    @JsonProperty("RequestInfo")
    private entities.requests.propertyTax.RequestInfo RequestInfo;

    public UsageMasters[] getUsageMasters() {
        return this.usageMasters;
    }

    public void setUsageMasters(UsageMasters[] usageMasters) {
        this.usageMasters = usageMasters;
    }

    public entities.requests.propertyTax.RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
