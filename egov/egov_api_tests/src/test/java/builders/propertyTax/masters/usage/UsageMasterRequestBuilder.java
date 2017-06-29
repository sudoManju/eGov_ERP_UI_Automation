package builders.propertyTax.masters.usage;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.usage.UsageMasterRequest;
import entities.requests.propertyTax.masters.usage.UsageMasters;
import org.apache.commons.lang3.RandomUtils;

public class UsageMasterRequestBuilder {

    UsageMasterRequest request = new UsageMasterRequest();

    public UsageMasterRequestBuilder(){}

    public UsageMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public UsageMasterRequestBuilder withUsageMasters(UsageMasters[] usageMasters){
        request.setUsageMasters(usageMasters);
        return this;
    }

    public UsageMasterRequest build(){
        return request;
    }
}
