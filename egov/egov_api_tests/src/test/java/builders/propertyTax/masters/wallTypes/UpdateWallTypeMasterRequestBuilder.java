package builders.propertyTax.masters.wallTypes;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.wallType.UpdateWallTypeMasterRequest;
import entities.requests.propertyTax.masters.wallType.WallTypes;

public class UpdateWallTypeMasterRequestBuilder {

    UpdateWallTypeMasterRequest request = new UpdateWallTypeMasterRequest();

    public UpdateWallTypeMasterRequestBuilder(){}

    public UpdateWallTypeMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public UpdateWallTypeMasterRequestBuilder withWallTypes(WallTypes[] wallTypes){
        request.setWallTypes(wallTypes);
        return this;
    }

    public UpdateWallTypeMasterRequest build(){
        return request;
    }
}
