package builders.propertyTax.masters.structureClass;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.structureClass.StructureClassRequest;
import entities.requests.propertyTax.masters.structureClass.StructureClasses;

public class CreateStructureClassRequestBuilder {

    StructureClassRequest request = new StructureClassRequest();

    public CreateStructureClassRequestBuilder(){}

    public CreateStructureClassRequestBuilder withStructureClasses(StructureClasses[] structureClasses){
        request.setStructureClasses(structureClasses);
        return this;
    }

    public CreateStructureClassRequestBuilder withRequestinfo(RequestInfo requestinfo){
        request.setRequestInfo(requestinfo);
        return this;
    }

    public StructureClassRequest build(){
        return request;
    }
}
