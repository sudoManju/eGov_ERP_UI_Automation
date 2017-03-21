package builders.assetManagement;

import entities.requests.assetManagement.assetCategory.AssetCategory;
import entities.requests.assetManagement.assetCategory.AssetCategoryCreateRequest;
import entities.requests.assetManagement.assetCategory.RequestInfo;

public class AssetCategoryCreateRequestBuilder {

    AssetCategoryCreateRequest request = new AssetCategoryCreateRequest();
    AssetCategory AssetCategory = new AssetCategoryBuilder().build();
    RequestInfo RequestInfo = new RequestInfoBuilder().build();

    public AssetCategoryCreateRequestBuilder(){
        request.setRequestInfo(RequestInfo);
        request.setAssetCategory(AssetCategory);
    }

    public AssetCategoryCreateRequestBuilder withAssetCategory(AssetCategory assetCategory){
        request.setAssetCategory(assetCategory);
        return this;
    }

    public AssetCategoryCreateRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public AssetCategoryCreateRequest build(){
        return request;
    }
}
