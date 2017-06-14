package builders.propertyTax.masters;

import entities.requests.propertyTax.masters.CreateUsageMasterRequest;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.UsageMasters;

public class CreateUsageMasterRequestBuilder {

    CreateUsageMasterRequest request = new CreateUsageMasterRequest();

    UsageMasters[] usageMasterses = new UsageMasters[1];
    UsageMasters usageMasters = new UsageMastersBuilder().build();

    public CreateUsageMasterRequestBuilder(){
        usageMasterses[0] = usageMasters;
       request.setUsageMasters(usageMasterses);
    }

    public CreateUsageMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public CreateUsageMasterRequest build(){
        return request;
    }
}
