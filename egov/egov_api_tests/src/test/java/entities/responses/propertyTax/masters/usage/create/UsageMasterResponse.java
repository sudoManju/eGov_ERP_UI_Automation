package entities.responses.propertyTax.masters.usage.create;

public class UsageMasterResponse {
    private UsageMasters[] usageMasters;
    private ResponseInfo responseInfo;

    public UsageMasters[] getUsageMasters() {
        return this.usageMasters;
    }

    public void setUsageMasters(UsageMasters[] usageMasters) {
        this.usageMasters = usageMasters;
    }

    public ResponseInfo getResponseInfo() {
        return this.responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
