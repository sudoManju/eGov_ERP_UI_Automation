package builders.assetManagement;

import entities.requests.assetManagement.assetCategory.AssetCategory;
import entities.requests.assetManagement.assetCategory.AssetCategotyCreateRequest;
import entities.requests.assetManagement.assetCategory.RequestInfo;

public final class AssetCategotyCreateRequestBuilder {
    AssetCategotyCreateRequest assetCategotyCreateRequest = new AssetCategotyCreateRequest();
    AssetCategory assetCategory = new AssetCategoryBuilder().build();
    RequestInfo requestInfo = new RequestInfoBuilder().build();

    public AssetCategotyCreateRequestBuilder() {
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
