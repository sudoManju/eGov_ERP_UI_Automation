package builders.propertyTax.masters.wallTypes;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.wallType.CreateWallTypeMasterRequest;
import entities.requests.propertyTax.masters.wallType.WallTypes;
import org.apache.commons.lang3.RandomUtils;

public class CreateWallTypeMasterRequestBuilder {

    CreateWallTypeMasterRequest request = new CreateWallTypeMasterRequest();

    WallTypes[] wallTypes = new WallTypes[1];

    String num = String.valueOf((RandomUtils.nextInt(100, 999)));

    WallTypes wallTypes1 = new WallTypesBuilder().withCode(num).withName("Test_"+num).withNameLocal("Test_"+num).build();

    public CreateWallTypeMasterRequestBuilder(){
        wallTypes[0] = wallTypes1;
        request.setWallTypes(wallTypes);
    }

    public CreateWallTypeMasterRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public CreateWallTypeMasterRequest build(){
        return request;
    }
}
