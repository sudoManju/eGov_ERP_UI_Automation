package builders.wcms.supplyType.create;

import entities.requests.wcms.supplyType.create.SupplyType;
import tests.BaseAPITest;

public class SupplyTypeBuilder {

    SupplyType supplyType = new SupplyType();

    public SupplyTypeBuilder() {
        supplyType.setCode("");
        supplyType.setId(0);
        supplyType.setName("SupplyType " + new BaseAPITest().get6DigitRandomInt());
        supplyType.setDescription("SupplyType Description");
        supplyType.setTenantId("default");
        supplyType.setActive(true);
    }

    public SupplyTypeBuilder withCode(String code) {
        supplyType.setCode(code);
        return this;
    }

    public SupplyTypeBuilder withName(String name) {
        supplyType.setName(name);
        return this;
    }

    public SupplyTypeBuilder withTenantId(String tenantId) {
        supplyType.setTenantId(tenantId);
        return this;
    }

    public SupplyTypeBuilder withDescription(String description) {
        supplyType.setDescription(description);
        return this;
    }

    public SupplyTypeBuilder withActive(boolean active) {
        supplyType.setActive(active);
        return this;
    }

    public SupplyTypeBuilder withId(int id) {
        supplyType.setId(id);
        return this;
    }

    public SupplyType build() {
        return supplyType;
    }
}
