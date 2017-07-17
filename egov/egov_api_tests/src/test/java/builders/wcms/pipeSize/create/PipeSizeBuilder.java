package builders.wcms.pipeSize.create;

import entities.requests.wcms.pipeSize.create.PipeSize;
import tests.BaseAPITest;

import static data.SearchParameterData.TENANT_DEFAULT;

public class PipeSizeBuilder {
    PipeSize pipeSize = new PipeSize();

    public PipeSizeBuilder() {
        pipeSize.setSizeInMilimeter(String.valueOf(new BaseAPITest().getRandomIntFromRange(100, 999)));
        pipeSize.setDescription("PipeSizes Description");
        pipeSize.setActive(true);
        pipeSize.setTenantId(TENANT_DEFAULT);
    }


    public PipeSizeBuilder withCode(String code) {
        pipeSize.setCode(code);
        return this;
    }

    public PipeSizeBuilder withSizeInInch(int sizeInInch) {
        pipeSize.setSizeInInch(sizeInInch);
        return this;
    }

    public PipeSizeBuilder withTenantId(String tenantId) {
        pipeSize.setTenantId(tenantId);
        return this;
    }

    public PipeSizeBuilder withSizeInMilimeter(String sizeInMilimeter) {
        pipeSize.setSizeInMilimeter(sizeInMilimeter);
        return this;
    }

    public PipeSizeBuilder withActive(boolean active) {
        pipeSize.setActive(active);
        return this;
    }

    public PipeSizeBuilder withDescription(String description) {
        pipeSize.setDescription(description);
        return this;
    }

    public PipeSizeBuilder withId(int id) {
        pipeSize.setId(id);
        return this;
    }

    public PipeSize build() {
        return pipeSize;
    }
}
