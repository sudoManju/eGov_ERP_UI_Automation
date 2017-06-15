package builders.propertyTax.masters.usage;

import entities.requests.propertyTax.masters.usage.CreateUsageMasterRequest;
import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.usage.UsageMasters;
import org.apache.commons.lang3.RandomUtils;

public class CreateUsageMasterRequestBuilder {

    CreateUsageMasterRequest request = new CreateUsageMasterRequest();

    UsageMasters[] usageMasterses = new UsageMasters[1];
    String num = String.valueOf((RandomUtils.nextInt(100, 999)));
    UsageMasters usageMasters = new UsageMastersBuilder().withName("Test"+num).withCode(num).withNameLocal("Local"+num)
                                   .withOrderNumber(Integer.parseInt(num)).build();

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
