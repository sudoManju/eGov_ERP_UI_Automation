package entities.requests.assetManagement.assetCategory;

import entities.requests.assetManagement.RequestInfo;

public class AssetCategoryCreateRequest {
    private AssetCategory AssetCategory;

    private RequestInfo RequestInfo;

    public AssetCategory getAssetCategory() {
        return AssetCategory;
    }

    public void setAssetCategory(AssetCategory AssetCategory) {
        this.AssetCategory = AssetCategory;
    }

    public RequestInfo getRequestInfo() {
        return RequestInfo;
    }

    public void setRequestInfo(RequestInfo RequestInfo) {
        this.RequestInfo = RequestInfo;
    }
}