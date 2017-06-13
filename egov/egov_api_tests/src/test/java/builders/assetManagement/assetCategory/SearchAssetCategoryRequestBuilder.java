package builders.assetManagement.assetCategory;

import entities.requests.assetManagement.assetCategory.RequestInfo;
import entities.requests.assetManagement.assetCategory.SearchAssetCategoryRequest;

public class SearchAssetCategoryRequestBuilder {

    SearchAssetCategoryRequest request = new SearchAssetCategoryRequest();

    RequestInfo requestInfo = new RequestInfoBuilder().build();

    public SearchAssetCategoryRequestBuilder(){
        request.setRequestInfo(requestInfo);
    }

    public SearchAssetCategoryRequest build(){
        return request;
    }
}
