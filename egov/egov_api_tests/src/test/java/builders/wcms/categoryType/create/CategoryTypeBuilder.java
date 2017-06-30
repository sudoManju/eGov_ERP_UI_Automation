package builders.wcms.categoryType.create;

import entities.requests.wcms.categoryType.create.CategoryType;
import tests.BaseAPITest;


public class CategoryTypeBuilder {

    CategoryType category = new CategoryType();

    public CategoryTypeBuilder() {
        category.setCode("");
        category.setName("Category Type" + new BaseAPITest().get3DigitRandomInt());
        category.setActive(true);
        category.setDescription("Category Type Description");
        category.setTenantId("default");
    }

    public static CategoryTypeBuilder aCategoryType() {
        return new CategoryTypeBuilder();
    }

    public CategoryTypeBuilder withCode(String code) {
        category.setCode(code);
        return this;
    }

    public CategoryTypeBuilder withName(String name) {
        category.setName(name);
        return this;
    }

    public CategoryTypeBuilder withTenantId(String tenantId) {
        category.setTenantId(tenantId);
        return this;
    }

    public CategoryTypeBuilder withDescription(String description) {
        category.setDescription(description);
        return this;
    }

    public CategoryTypeBuilder withActive(boolean active) {
        category.setActive(active);
        return this;
    }

    public CategoryTypeBuilder withId(int id) {
        category.setId(id);
        return this;
    }

    public CategoryType build() {
        return category;
    }
}
