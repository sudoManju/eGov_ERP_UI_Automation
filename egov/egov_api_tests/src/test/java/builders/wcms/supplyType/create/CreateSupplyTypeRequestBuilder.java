package builders.wcms.supplyType.create;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.supplyType.create.CreateSupplyTypeRequest;
import entities.requests.wcms.supplyType.create.SupplyType;

public class CreateSupplyTypeRequestBuilder {

    CreateSupplyTypeRequest createSupplyTypeRequest = new CreateSupplyTypeRequest();

    public CreateSupplyTypeRequestBuilder withSupplyType(SupplyType SupplyType) {
        createSupplyTypeRequest.setSupplyType(SupplyType);
        return this;
    }

    public CreateSupplyTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createSupplyTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateSupplyTypeRequest build() {
        return createSupplyTypeRequest;
    }
}
