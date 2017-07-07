package builders.propertyTax.masters.roofTypes;

import builders.propertyTax.masters.AuditDetailsBuilder;
import entities.requests.propertyTax.masters.AuditDetails;
import entities.requests.propertyTax.masters.roofType.RoofTypes;

public class RoofTypesBuilder {

    RoofTypes roofTypes = new RoofTypes();
    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public RoofTypesBuilder() {
        roofTypes.setId(0);
        roofTypes.setTenantId("default");
        roofTypes.setDescription("Testing the Roof Type Master");
        roofTypes.setAuditDetails(auditDetails);
    }

    public RoofTypesBuilder withName(String name) {
        roofTypes.setName(name);
        return this;
    }

    public RoofTypesBuilder withCode(String code) {
        roofTypes.setCode(code);
        return this;
    }

    public RoofTypesBuilder withNameLocal(String nameLocal) {
        roofTypes.setNameLocal(nameLocal);
        return this;
    }

    public RoofTypesBuilder withId(int id) {
        roofTypes.setId(id);
        return this;
    }

    public RoofTypes build() {
        return roofTypes;
    }
}
