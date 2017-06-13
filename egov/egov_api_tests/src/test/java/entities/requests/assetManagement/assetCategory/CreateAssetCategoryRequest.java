package entities.requests.assetManagement.assetCategory;

import org.codehaus.jackson.annotate.JsonProperty;

public class CreateAssetCategoryRequest {

    @JsonProperty("AssetCategory")
    private AssetCategory AssetCategory;

    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;

    public AssetCategory getAssetCategory() {
        return this.AssetCategory;
    }

    public void setAssetCategory(AssetCategory AssetCategory) {
        this.AssetCategory = AssetCategory;
    }

    public RequestInfo getRequestInfo() {
        return this.RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}
