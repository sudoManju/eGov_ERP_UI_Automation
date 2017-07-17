package builders.wcms.categoryType.create;

import entities.requests.wcms.categoryType.create.CategoryType;
import tests.BaseAPITest;

import static data.SearchParameterData.TENANT_DEFAULT;

public class CategoryTypeBuilder {

    CategoryType category = new CategoryType();

    public CategoryTypeBuilder() {
        category.setCode("");
        category.setName("Category Type " + new BaseAPITest().getRandomIntFromRange(1000, 9999));
        category.setActive(true);
        category.setDescription("Category Type Description");
        category.setTenantId(TENANT_DEFAULT);
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
