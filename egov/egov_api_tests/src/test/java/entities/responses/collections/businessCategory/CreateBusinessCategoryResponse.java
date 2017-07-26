package entities.responses.collections.businessCategory;

import entities.responses.collections.ResponseInfo;
import org.codehaus.jackson.annotate.JsonProperty;

public class CreateBusinessCategoryResponse {
    private ResponseInfo ResponseInfo;
    @JsonProperty("BusinessCategoryInfo")
    private BusinessCategoryInfo[] BusinessCategoryInfo;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public BusinessCategoryInfo[] getBusinessCategoryInfo() {
        return this.BusinessCategoryInfo;
    }

    public void setBusinessCategoryInfo(BusinessCategoryInfo[] BusinessCategoryInfo) {
        this.BusinessCategoryInfo = BusinessCategoryInfo;
    }
}
