package builders.propertyTax.masters.floorTypes;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.floorType.CreateFloorTypesRequest;
import entities.requests.propertyTax.masters.floorType.FloorTypes;
import org.apache.commons.lang3.RandomUtils;

public class CreateFloorTypesRequestBuilder {

    CreateFloorTypesRequest request = new CreateFloorTypesRequest();

    String num = String.valueOf((RandomUtils.nextInt(100, 999)));

    FloorTypes[] floorTypes = new FloorTypes[1];

    FloorTypes floorTypes1 = new FloorTypesBuilder().withName("Test_"+num).withCode(num)
            .withNameLocal("Test_"+num).build();

    public CreateFloorTypesRequestBuilder(){
        floorTypes[0] = floorTypes1;
        request.setFloorTypes(floorTypes);
    }

    public CreateFloorTypesRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public CreateFloorTypesRequest build(){
        return request;
    }
}
