package builders.propertyTax.masters.structureClass;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.structureClass.CreateStructureClassRequest;
import entities.requests.propertyTax.masters.structureClass.StructureClasses;
import org.apache.commons.lang3.RandomUtils;

public class CreateStructureClassRequestBuilder {

    CreateStructureClassRequest request = new CreateStructureClassRequest();

    String num = String.valueOf((RandomUtils.nextInt(100, 999)));

    StructureClasses[] structureClasses = new StructureClasses[1];

    StructureClasses structureClass = new StructureClassesBuilder().withName("Test_"+num).withCode(num).withNameLocal("Testing"+num)
                                        .withOrderNumber(Integer.parseInt(num)).build();

    public CreateStructureClassRequestBuilder(){
          structureClasses[0] = structureClass;
          request.setStructureClasses(structureClasses);
    }

    public CreateStructureClassRequestBuilder withRequestinfo(RequestInfo requestinfo){
        request.setRequestInfo(requestinfo);
        return this;
    }

    public CreateStructureClassRequest build(){
        return request;
    }
}
