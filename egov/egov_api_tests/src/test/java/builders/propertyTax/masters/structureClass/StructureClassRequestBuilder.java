package builders.propertyTax.masters.structureClass;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.structureClass.StructureClassRequest;
import entities.requests.propertyTax.masters.structureClass.StructureClasses;

public class StructureClassRequestBuilder {

    StructureClassRequest request = new StructureClassRequest();

    public StructureClassRequestBuilder() {
    }

    public StructureClassRequestBuilder withStructureClasses(StructureClasses[] structureClasses) {
        request.setStructureClasses(structureClasses);
        return this;
    }

    public StructureClassRequestBuilder withRequestinfo(RequestInfo requestinfo) {
        request.setRequestInfo(requestinfo);
        return this;
    }

    public StructureClassRequest build() {
        return request;
    }
}
