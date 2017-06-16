package builders.propertyTax.masters.woodTypes;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.woodType.CreateWoodTypeRequest;
import entities.requests.propertyTax.masters.woodType.WoodTypes;
import org.apache.commons.lang3.RandomUtils;

public class CreateWoodTypesRequestBuilder {

    CreateWoodTypeRequest request = new CreateWoodTypeRequest();

    WoodTypes[] woodTypes = new WoodTypes[1];

    String num = String.valueOf((RandomUtils.nextInt(100, 999)));

    WoodTypes woodTypes1 = new WoodTypesBuilder().withName("Test"+num).withCode(num).withNameLocal("Test_"+num).build();

    public CreateWoodTypesRequestBuilder(){
        woodTypes[0] = woodTypes1;
        request.setWoodTypes(woodTypes);
    }

    public CreateWoodTypesRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public CreateWoodTypeRequest build(){
        return request;
    }
}
