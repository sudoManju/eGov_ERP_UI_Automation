package builders.wcms.propertyPipeSize.create;

import entities.requests.wcms.propertyPipeSize.create.PropertyTypePipeSize;

public class PropertyPipeSizeBuilder {
    PropertyTypePipeSize propertyTypePipeSize = new PropertyTypePipeSize();

    public PropertyPipeSizeBuilder() {
        propertyTypePipeSize.setTenantId("default");
        propertyTypePipeSize.setActive("true");
    }

    public PropertyPipeSizeBuilder withPropertyTypeName(String propertyTypeName) {
        propertyTypePipeSize.setPropertyTypeName(propertyTypeName);
        return this;
    }

    public PropertyPipeSizeBuilder withPipeSize(String pipeSize) {
        propertyTypePipeSize.setPipeSize(pipeSize);
        return this;
    }

    public PropertyPipeSizeBuilder withTenantId(String tenantId) {
        propertyTypePipeSize.setTenantId(tenantId);
        return this;
    }

    public PropertyPipeSizeBuilder withActive(String active) {
        propertyTypePipeSize.setActive(active);
        return this;
    }

    public PropertyTypePipeSize build() {
        return propertyTypePipeSize;
    }
}
