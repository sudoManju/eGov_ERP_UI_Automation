package builders.wcms.propertyCategoryType;

import entities.requests.wcms.propertyCategoryType.create.PropertyCategory;

public class PropertyCategoryBuilder {
    PropertyCategory propertyCategory = new PropertyCategory();

    public PropertyCategoryBuilder() {
        propertyCategory.setActive("true");
        propertyCategory.setTenantId("default");
    }

    public PropertyCategoryBuilder withPropertyTypeName(String propertyTypeName) {
        propertyCategory.setPropertyTypeName(propertyTypeName);
        return this;
    }

    public PropertyCategoryBuilder withCategoryTypeName(String categoryTypeName) {
        propertyCategory.setCategoryTypeName(categoryTypeName);
        return this;
    }

    public PropertyCategoryBuilder withTenantId(String tenantId) {
        propertyCategory.setTenantId(tenantId);
        return this;
    }

    public PropertyCategoryBuilder withActive(String active) {
        propertyCategory.setActive(active);
        return this;
    }

    public PropertyCategory build() {
        return propertyCategory;
    }
}
