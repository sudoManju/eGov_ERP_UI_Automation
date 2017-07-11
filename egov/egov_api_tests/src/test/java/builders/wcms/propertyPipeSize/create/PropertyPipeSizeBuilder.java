package builders.wcms.propertyPipeSize.create;

import entities.requests.wcms.propertyPipeSize.create.PropertyPipeSize;

public class PropertyPipeSizeBuilder {
    PropertyPipeSize propertyPipeSize = new PropertyPipeSize();

    public PropertyPipeSizeBuilder() {
        propertyPipeSize.setTenantId("default");
        propertyPipeSize.setActive("true");
    }

    public PropertyPipeSizeBuilder withPropertyTypeName(String propertyTypeName) {
        propertyPipeSize.setPropertyTypeName(propertyTypeName);
        return this;
    }

    public PropertyPipeSizeBuilder withPipeSize(String pipeSize) {
        propertyPipeSize.setPipeSize(pipeSize);
        return this;
    }

    public PropertyPipeSizeBuilder withTenantId(String tenantId) {
        propertyPipeSize.setTenantId(tenantId);
        return this;
    }

    public PropertyPipeSizeBuilder withActive(String active) {
        propertyPipeSize.setActive(active);
        return this;
    }

    public PropertyPipeSize build() {
        return propertyPipeSize;
    }
}
