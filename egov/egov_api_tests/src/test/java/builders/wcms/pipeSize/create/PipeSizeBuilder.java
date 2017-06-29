package builders.wcms.pipeSize.create;

import entities.requests.wcms.pipeSize.create.PipeSize;
import tests.BaseAPITest;

public class PipeSizeBuilder {
    PipeSize pipeSize = new PipeSize();

    public PipeSizeBuilder() {
        pipeSize.setSizeInMilimeter(new BaseAPITest().get6DigitRandomInt());
        pipeSize.setDescription("PipeSize Description");
        pipeSize.setActive(true);
        pipeSize.setTenantId("default");
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
