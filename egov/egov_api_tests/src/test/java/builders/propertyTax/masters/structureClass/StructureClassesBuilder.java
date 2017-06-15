package builders.propertyTax.masters.structureClass;

import builders.propertyTax.masters.AuditDetailsBuilder;
import entities.requests.propertyTax.masters.AuditDetails;
import entities.requests.propertyTax.masters.structureClass.StructureClasses;

public class StructureClassesBuilder {

    StructureClasses structureClasses = new StructureClasses();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public StructureClassesBuilder(){
        structureClasses.setActive(true);
        structureClasses.setDescription("Testing the structureClass Master");
        structureClasses.setId(0);
        structureClasses.setTenantId("ap.kurnool");
        structureClasses.setAuditDetails(auditDetails);
    }

    public StructureClassesBuilder withName(String name){
        structureClasses.setName(name);
        return this;
    }

    public StructureClassesBuilder withCode(String code){
        structureClasses.setCode(code);
        return this;
    }

    public StructureClassesBuilder withNameLocal(String nameLocal){
        structureClasses.setNameLocal(nameLocal);
        return this;
    }

    public StructureClassesBuilder withOrderNumber(int number){
        structureClasses.setOrderNumber(number);
        return this;
    }

    public StructureClasses build(){
        return structureClasses;
    }
}
