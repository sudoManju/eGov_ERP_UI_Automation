package builders.propertyTax.masters.woodTypes;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.masters.woodType.WoodTypes;

import static data.ConstantData.tenantId;

public class WoodTypesBuilder {

    WoodTypes woodTypes = new WoodTypes();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public WoodTypesBuilder() {
        woodTypes.setAuditDetails(auditDetails);
        woodTypes.setTenantId(tenantId);
        woodTypes.setId(0);
        woodTypes.setDescription("Testing the woodTypes Master");
    }

    public WoodTypesBuilder withName(String name) {
        woodTypes.setName(name);
        return this;
    }

    public WoodTypesBuilder withCode(String code) {
        woodTypes.setCode(code);
        return this;
    }

    public WoodTypesBuilder withNameLocal(String nameLocal) {
        woodTypes.setNameLocal(nameLocal);
        return this;
    }

    public WoodTypesBuilder withId(int id) {
        woodTypes.setId(id);
        return this;
    }

    public WoodTypes build() {
        return woodTypes;
    }
}
