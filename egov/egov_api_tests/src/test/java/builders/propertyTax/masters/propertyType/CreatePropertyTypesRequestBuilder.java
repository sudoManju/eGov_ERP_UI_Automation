package builders.propertyTax.masters.propertyType;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.propertyType.CreatePropertyTypeRequest;
import entities.requests.propertyTax.masters.propertyType.PropertyTypes;
import org.apache.commons.lang3.RandomUtils;

public class CreatePropertyTypesRequestBuilder {

    PropertyTypes[] propertyTypes = new PropertyTypes[1];

    String num = String.valueOf((RandomUtils.nextInt(100, 999)));

    PropertyTypes propertyTypes1 = new PropertyTypesBuilder().withName("Test_"+num).withCode(num).withNameLocal("Test_"+num)
                                     .withOrderNum(Integer.parseInt(num)).build();

    CreatePropertyTypeRequest request = new CreatePropertyTypeRequest();

    public CreatePropertyTypesRequestBuilder(){
        propertyTypes[0] = propertyTypes1;
        request.setPropertyTypes(propertyTypes);
    }

    public CreatePropertyTypesRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public CreatePropertyTypeRequest build(){
        return request;
    }
}
