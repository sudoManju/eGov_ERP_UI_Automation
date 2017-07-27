package entities.responses.assetManagement.searchCategory;

import org.codehaus.jackson.annotate.JsonProperty;

public class SearchAssetCategoryResponse {

    @JsonProperty("TaxPeriodsMasterResponse")
    private ResponseInfo ResponseInfo;

    @JsonProperty("AssetCategory")
    private AssetCategory[] AssetCategory;

    public ResponseInfo getResponseInfo() {
        return this.ResponseInfo;
    }

    public void setResponseInfo(ResponseInfo ResponseInfo) {
        this.ResponseInfo = ResponseInfo;
    }

    public AssetCategory[] getAssetCategory() {
        return this.AssetCategory;
    }

    public void setAssetCategory(AssetCategory[] AssetCategory) {
        this.AssetCategory = AssetCategory;
    }
}
