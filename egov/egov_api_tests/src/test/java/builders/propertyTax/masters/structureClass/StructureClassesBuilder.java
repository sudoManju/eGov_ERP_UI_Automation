package builders.propertyTax.masters.structureClass;

import builders.propertyTax.masters.AuditDetailsBuilder;
import entities.requests.propertyTax.masters.AuditDetails;
import entities.requests.propertyTax.masters.structureClass.StructureClasses;

import static data.ConstantData.tenantId;

public class StructureClassesBuilder {

    StructureClasses structureClasses = new StructureClasses();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public StructureClassesBuilder() {
        structureClasses.setActive(true);
        structureClasses.setDescription("Testing the structureClass Master");
        structureClasses.setId(0);
        structureClasses.setTenantId(tenantId);
        structureClasses.setAuditDetails(auditDetails);
    }

    public StructureClassesBuilder withName(String name) {
        structureClasses.setName(name);
        return this;
    }

    public StructureClassesBuilder withCode(String code) {
        structureClasses.setCode(code);
        return this;
    }

    public StructureClassesBuilder withNameLocal(String nameLocal) {
        structureClasses.setNameLocal(nameLocal);
        return this;
    }

    public StructureClassesBuilder withOrderNumber(int number) {
        structureClasses.setOrderNumber(number);
        return this;
    }

    public StructureClassesBuilder withId(int id) {
        structureClasses.setId(id);
        return this;
    }

    public StructureClasses build() {
        return structureClasses;
    }
}
