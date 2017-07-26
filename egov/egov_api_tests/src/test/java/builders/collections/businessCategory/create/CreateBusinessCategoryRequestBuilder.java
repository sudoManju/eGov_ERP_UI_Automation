package builders.collections.businessCategory.create;

import entities.requests.collections.RequestInfo;
import entities.requests.collections.businessCategory.create.BusinessCategoryInfo;
import entities.requests.collections.businessCategory.create.CreateBusinessCategoryRequest;

import static data.SearchParameterData.TENANT_DEFAULT;

public class CreateBusinessCategoryRequestBuilder {

    CreateBusinessCategoryRequest createBusinessCategoryRequest = new CreateBusinessCategoryRequest();

    public CreateBusinessCategoryRequestBuilder() {
        createBusinessCategoryRequest.setTenantId(TENANT_DEFAULT);
    }

    public CreateBusinessCategoryRequestBuilder withTenantId(String tenantId) {
        createBusinessCategoryRequest.setTenantId(tenantId);
        return this;
    }

    public CreateBusinessCategoryRequestBuilder withBusinessCategoryInfo(BusinessCategoryInfo BusinessCategoryInfo) {
        createBusinessCategoryRequest.setBusinessCategoryInfo(BusinessCategoryInfo);
        return this;
    }

    public CreateBusinessCategoryRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createBusinessCategoryRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateBusinessCategoryRequest build() {
        return createBusinessCategoryRequest;
    }
}
