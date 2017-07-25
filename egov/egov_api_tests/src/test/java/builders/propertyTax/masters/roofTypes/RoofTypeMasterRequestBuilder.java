package builders.propertyTax.masters.roofTypes;

import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.masters.roofType.RoofTypeMasterRequest;
import entities.requests.propertyTax.masters.roofType.RoofTypes;

public class RoofTypeMasterRequestBuilder {

    RoofTypeMasterRequest request = new RoofTypeMasterRequest();

    public RoofTypeMasterRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        request.setRequestInfo(requestInfo);
        return this;
    }

    public RoofTypeMasterRequestBuilder withRoofTypes(RoofTypes[] roofTypes) {
        request.setRoofTypes(roofTypes);
        return this;
    }

    public RoofTypeMasterRequest build() {
        return request;
    }
}
