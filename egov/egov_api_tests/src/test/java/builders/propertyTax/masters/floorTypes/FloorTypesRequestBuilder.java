package builders.propertyTax.masters.floorTypes;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.floorType.FloorTypes;
import entities.requests.propertyTax.masters.floorType.FloorTypesRequest;

public class FloorTypesRequestBuilder {

    FloorTypesRequest request = new FloorTypesRequest();

    public FloorTypesRequestBuilder() {
    }

    public FloorTypesRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        request.setRequestInfo(requestInfo);
        return this;
    }

    public FloorTypesRequestBuilder withFloorTypes(FloorTypes[] floorTypes) {
        request.setFloorTypes(floorTypes);
        return this;
    }

    public FloorTypesRequest build() {
        return request;
    }
}
