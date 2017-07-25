package builders.propertyTax.masters.wallTypes;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.masters.wallType.WallTypes;

import static data.ConstantData.tenantId;

public class WallTypesBuilder {

    WallTypes wallTypes = new WallTypes();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public WallTypesBuilder() {
        wallTypes.setId(0);
        wallTypes.setTenantId(tenantId);
        wallTypes.setDescription("Testing the wall type");
        wallTypes.setAuditDetails(auditDetails);
    }

    public WallTypesBuilder withName(String name) {
        wallTypes.setName(name);
        return this;
    }

    public WallTypesBuilder withCode(String code) {
        wallTypes.setCode(code);
        return this;
    }

    public WallTypesBuilder withNameLocal(String nameLocal) {
        wallTypes.setNameLocal(nameLocal);
        return this;
    }

    public WallTypesBuilder withId(int id) {
        wallTypes.setId(id);
        return this;
    }

    public WallTypes build() {
        return wallTypes;
    }
}
