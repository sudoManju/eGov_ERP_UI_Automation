package builders.wcms.propertyTypeUsageType.create;

import entities.requests.wcms.propertyTypeUsageType.create.PropertyTypeUsageType;

import static data.SearchParameterData.TENANT_DEFAULT;

public class PropertyTypeUsageTypeBuilder {
    PropertyTypeUsageType propertyTypeUsageType = new PropertyTypeUsageType();

    public PropertyTypeUsageTypeBuilder() {
        propertyTypeUsageType.setActive(true);
        propertyTypeUsageType.setTenantId(TENANT_DEFAULT);
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
