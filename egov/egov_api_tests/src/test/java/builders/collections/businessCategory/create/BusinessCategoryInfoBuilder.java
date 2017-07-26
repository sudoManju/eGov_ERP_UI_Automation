package builders.collections.businessCategory.create;

import entities.requests.collections.businessCategory.create.BusinessCategoryInfo;
import tests.BaseAPITest;

import java.util.Random;

import static data.SearchParameterData.TENANT_DEFAULT;

public class BusinessCategoryInfoBuilder {

    BusinessCategoryInfo businessCategoryInfo = new BusinessCategoryInfo();

    public BusinessCategoryInfoBuilder() {
        String randomNumber = String.valueOf(new BaseAPITest().getRandomIntFromRange(99, 9999));
        businessCategoryInfo.setTenantId(TENANT_DEFAULT);
        businessCategoryInfo.setName("Business Category " + randomNumber);
        businessCategoryInfo.setCode("BC " + randomNumber);
        businessCategoryInfo.setActive(new Random().nextBoolean());
    }

    public BusinessCategoryInfoBuilder withCode(String code) {
        businessCategoryInfo.setCode(code);
        return this;
    }

    public BusinessCategoryInfoBuilder withName(String name) {
        businessCategoryInfo.setName(name);
        return this;
    }

    public BusinessCategoryInfoBuilder withTenantId(String tenantId) {
        businessCategoryInfo.setTenantId(tenantId);
        return this;
    }

    public BusinessCategoryInfoBuilder withActive(boolean active) {
        businessCategoryInfo.setActive(active);
        return this;
    }

    public BusinessCategoryInfoBuilder withId(int id) {
        businessCategoryInfo.setId(id);
        return this;
    }

    public BusinessCategoryInfo build() {
        return businessCategoryInfo;
    }
}
