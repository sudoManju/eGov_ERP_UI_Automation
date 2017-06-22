package builders.wcms.categoryType.create;

import entities.requests.wcms.categoryType.create.Category;
import tests.BaseAPITest;

public class CategoryBuilder {
    Category category = new Category();

    public CategoryBuilder() {
        category.setCode("");
        category.setName("Category Type" + new BaseAPITest().get3DigitRandomInt());
        category.setActive(true);
        category.setDescription("Category Type Description");
        category.setTenantId("default");
    }

    public CategoryBuilder withCode(String code) {
        category.setCode(code);
        return this;
    }

    public CategoryBuilder withName(String name) {
        category.setName(name);
        return this;
    }

    public CategoryBuilder withTenantId(String tenantId) {
        category.setTenantId(tenantId);
        return this;
    }

    public CategoryBuilder withDescription(String description) {
        category.setDescription(description);
        return this;
    }

    public CategoryBuilder withActive(boolean active) {
        category.setActive(active);
        return this;
    }

    public CategoryBuilder withId(int id) {
        category.setId(id);
        return this;
    }

    public Category build() {
        return category;
    }
}
