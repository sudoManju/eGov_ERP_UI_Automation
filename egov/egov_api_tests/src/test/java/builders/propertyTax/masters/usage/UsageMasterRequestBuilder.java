package builders.propertyTax.masters.usage;

import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.masters.usage.UsageMasterRequest;
import entities.requests.propertyTax.masters.usage.UsageMasters;

public class UsageMasterRequestBuilder {

    UsageMasterRequest request = new UsageMasterRequest();

    public UsageMasterRequestBuilder() {
    }

    public UsageMasterRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        request.setRequestInfo(requestInfo);
        return this;
    }

    public UsageMasterRequestBuilder withUsageMasters(UsageMasters[] usageMasters) {
        request.setUsageMasters(usageMasters);
        return this;
    }

    public UsageMasterRequest build() {
        return request;
    }
}
