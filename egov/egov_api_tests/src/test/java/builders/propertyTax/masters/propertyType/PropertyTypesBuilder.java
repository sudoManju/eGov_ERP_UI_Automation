package builders.propertyTax.masters.propertyType;

import builders.propertyTax.masters.AuditDetailsBuilder;
import entities.requests.propertyTax.masters.AuditDetails;
import entities.requests.propertyTax.masters.propertyType.PropertyTypes;

public class PropertyTypesBuilder {

    PropertyTypes propertyTypes = new PropertyTypes();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public PropertyTypesBuilder(){
        propertyTypes.setTenantId("default");
        propertyTypes.setId(0);
        propertyTypes.setDescription("Testing the propertyType master");
        propertyTypes.setActive(true);
        propertyTypes.setAuditDetails(auditDetails);
    }

    public PropertyTypesBuilder withName(String name){
        propertyTypes.setName(name);
        return this;
    }

    public PropertyTypesBuilder withCode(String code){
        propertyTypes.setCode(code);
        return this;
    }

    public PropertyTypesBuilder withNameLocal(String nameLocal){
        propertyTypes.setNameLocal(nameLocal);
        return this;
    }

    public PropertyTypesBuilder withOrderNum(int num){
        propertyTypes.setOrderNumber(num);
        return this;
    }

    public PropertyTypes build(){
        return propertyTypes;
    }

}
