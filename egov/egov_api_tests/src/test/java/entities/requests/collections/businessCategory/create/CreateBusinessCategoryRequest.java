package entities.requests.collections.businessCategory.create;

import entities.requests.collections.RequestInfo;

public class CreateBusinessCategoryRequest {
    private String tenantId;
    private BusinessCategoryInfo BusinessCategoryInfo;
    private RequestInfo RequestInfo;

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public BusinessCategoryInfo getBusinessCategoryInfo() {
        return this.BusinessCategoryInfo;
    }

    public void setBusinessCategoryInfo(BusinessCategoryInfo BusinessCategoryInfo) {
        this.BusinessCategoryInfo = BusinessCategoryInfo;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
