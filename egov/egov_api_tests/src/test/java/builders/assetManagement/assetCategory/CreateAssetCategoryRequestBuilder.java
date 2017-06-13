package builders.assetManagement.assetCategory;

import entities.requests.assetManagement.assetCategory.AssetCategory;
import entities.requests.assetManagement.assetCategory.CreateAssetCategoryRequest;
import entities.requests.assetManagement.assetCategory.RequestInfo;

public class CreateAssetCategoryRequestBuilder {

    CreateAssetCategoryRequest request = new CreateAssetCategoryRequest();

    RequestInfo requestInfo = new RequestInfoBuilder().build();

    public CreateAssetCategoryRequestBuilder() {
        request.setRequestInfo(requestInfo);
    }

    public CreateAssetCategoryRequestBuilder withAssetCategory(AssetCategory category) {
        request.setAssetCategory(category);
        return this;
    }

    public CreateAssetCategoryRequest build() {
        return request;
    }
}
