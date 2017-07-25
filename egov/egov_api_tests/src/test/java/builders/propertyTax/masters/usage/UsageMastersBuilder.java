package builders.propertyTax.masters.usage;

import builders.propertyTax.AuditDetailsBuilder;
import entities.requests.propertyTax.AuditDetails;
import entities.requests.propertyTax.masters.usage.UsageMasters;

import static data.ConstantData.tenantId;

public class UsageMastersBuilder {

    UsageMasters usageMasters = new UsageMasters();

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public UsageMastersBuilder() {
        usageMasters.setId(0);
        usageMasters.setName("Egovernments");
        usageMasters.setCode("123");
        usageMasters.setNameLocal("Residential");
        usageMasters.setDescription("Testing the Master Api of usage");
        usageMasters.setActive(true);
        usageMasters.setIsResidential(true);
        usageMasters.setOrderNumber(0);
        usageMasters.setTenantId(tenantId);
        usageMasters.setAuditDetails(auditDetails);
    }

    public UsageMastersBuilder withId(int id) {
        usageMasters.setId(id);
        return this;
    }

    public UsageMastersBuilder withName(String name) {
        usageMasters.setName(name);
        return this;
    }

    public UsageMastersBuilder withCode(String code) {
        usageMasters.setCode(code);
        return this;
    }

    public UsageMastersBuilder withNameLocal(String nameLocal) {
        usageMasters.setNameLocal(nameLocal);
        return this;
    }

    public UsageMastersBuilder withOrderNumber(int orderNumber) {
        usageMasters.setOrderNumber(orderNumber);
        return this;
    }

    public UsageMasters build() {
        return usageMasters;
    }
}
