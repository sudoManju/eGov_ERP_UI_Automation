package builders.wcms.sourceType.create;

import entities.requests.wcms.sourceType.create.SourceType;
import tests.BaseAPITest;

public class SourceTypeBuilder {
    SourceType sourceType = new SourceType();

    public SourceTypeBuilder() {
        sourceType.setActive(true);
        sourceType.setCode("");
        sourceType.setId(0);
        sourceType.setName("SourceType" + new BaseAPITest().get6DigitRandomInt());
        sourceType.setDescription("Source Type Description");
        sourceType.setTenantId("default");
    }

    public SourceTypeBuilder withCode(String code) {
        sourceType.setCode(code);
        return this;
    }

    public SourceTypeBuilder withName(String name) {
        sourceType.setName(name);
        return this;
    }

    public SourceTypeBuilder withTenantId(String tenantId) {
        sourceType.setTenantId(tenantId);
        return this;
    }

    public SourceTypeBuilder withDescription(String description) {
        sourceType.setDescription(description);
        return this;
    }

    public SourceTypeBuilder withActive(boolean active) {
        sourceType.setActive(active);
        return this;
    }

    public SourceTypeBuilder withId(int id) {
        sourceType.setId(id);
        return this;
    }

    public SourceType build() {
        return sourceType;
    }
}
