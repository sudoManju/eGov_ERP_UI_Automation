package builders.propertyTax.masters.roofTypes;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.masters.roofType.RoofTypes;

import static data.ConstantData.tenantId;

public class RoofTypesBuilder {

    RoofTypes roofTypes = new RoofTypes();
    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public RoofTypesBuilder() {
        roofTypes.setId(0);
        roofTypes.setTenantId(tenantId);
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
