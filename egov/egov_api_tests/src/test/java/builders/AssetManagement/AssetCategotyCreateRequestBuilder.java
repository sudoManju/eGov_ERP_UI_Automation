package builders.AssetManagement;

import entities.requests.AssetManagement.AssetCategory;
import entities.requests.AssetManagement.AssetCategotyCreateRequest;
import entities.requests.AssetManagement.RequestInfo;

public final class AssetCategotyCreateRequestBuilder {
    AssetCategotyCreateRequest assetCategotyCreateRequest = new AssetCategotyCreateRequest();
    AssetCategory assetCategory = new AssetCategoryBuilder().build();
    RequestInfo requestInfo = new RequestInfoBuilder().build();

    private AssetCategotyCreateRequestBuilder() {
        assetCategotyCreateRequest.setAssetCategory(assetCategory);
        assetCategotyCreateRequest.setRequestInfo(requestInfo);
    }

    public AssetCategotyCreateRequestBuilder withAssetCategory(AssetCategory AssetCategory) {
        assetCategotyCreateRequest.setAssetCategory(AssetCategory);
        return this;
    }

    public AssetCategotyCreateRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        assetCategotyCreateRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public AssetCategotyCreateRequest build() {
        return assetCategotyCreateRequest;
    }
}
