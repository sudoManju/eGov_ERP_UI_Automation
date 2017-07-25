package builders.propertyTax.masters.woodTypes;

import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.masters.woodType.WoodTypeMasterRequest;
import entities.requests.propertyTax.masters.woodType.WoodTypes;

public class WoodTypeMasterRequestBuilder {

    WoodTypeMasterRequest request = new WoodTypeMasterRequest();

    public WoodTypeMasterRequestBuilder() {
    }

    public WoodTypeMasterRequestBuilder withWoodType(WoodTypes[] woodTypes) {
        request.setWoodTypes(woodTypes);
        return this;
    }

    public WoodTypeMasterRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        request.setRequestInfo(requestInfo);
        return this;
    }

    public WoodTypeMasterRequest build() {
        return request;
    }
}
