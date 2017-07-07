package builders.wcms.propertyTypeUsageType.create;

import entities.requests.wcms.propertyTypeUsageType.create.PropertyTypeUsageType;

public class PropertyTypeUsageTypeBuilder {
    PropertyTypeUsageType propertyTypeUsageType = new PropertyTypeUsageType();

    public PropertyTypeUsageTypeBuilder() {
        propertyTypeUsageType.setActive(true);
        propertyTypeUsageType.setTenantId("default");
    }


    public PropertyTypeUsageTypeBuilder withPropertyType(String propertyType) {
        propertyTypeUsageType.setPropertyType(propertyType);
        return this;
    }

    public PropertyTypeUsageTypeBuilder withTenantId(String tenantId) {
        propertyTypeUsageType.setTenantId(tenantId);
        return this;
    }

    public PropertyTypeUsageTypeBuilder withActive(boolean active) {
        propertyTypeUsageType.setActive(active);
        return this;
    }

    public PropertyTypeUsageTypeBuilder withUsageType(String usageType) {
        propertyTypeUsageType.setUsageType(usageType);
        return this;
    }

    public PropertyTypeUsageType build() {
        return propertyTypeUsageType;
    }
}
