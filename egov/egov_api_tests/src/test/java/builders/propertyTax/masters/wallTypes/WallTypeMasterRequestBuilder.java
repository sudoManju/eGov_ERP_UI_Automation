package builders.propertyTax.masters.wallTypes;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.wallType.WallTypeMasterRequest;
import entities.requests.propertyTax.masters.wallType.WallTypes;

public class WallTypeMasterRequestBuilder {

    WallTypeMasterRequest request = new WallTypeMasterRequest();


    public WallTypeMasterRequestBuilder() {
    }

    public WallTypeMasterRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        request.setRequestInfo(requestInfo);
        return this;
    }

    public WallTypeMasterRequestBuilder withWallTypes(WallTypes[] wallTypes) {
        request.setWallTypes(wallTypes);
        return this;
    }

    public WallTypeMasterRequest build() {
        return request;
    }
}
