package entities.responses.assetManagement.createCategory;

import org.codehaus.jackson.annotate.JsonProperty;

public class CreateAssetCategoryResponse {

    private Object ResponseInfo;

    @JsonProperty("AssetCategory")
    private AssetCategory[] AssetCategory;

    public Object getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(Object ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public AssetCategory[] getAssetCategory() {
        return this.AssetCategory;
    }

    public void setAssetCategory(AssetCategory[] AssetCategory) {
        this.AssetCategory = AssetCategory;
    }
}
