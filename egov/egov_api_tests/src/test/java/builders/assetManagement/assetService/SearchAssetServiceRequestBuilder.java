package builders.assetManagement.assetService;

import builders.assetManagement.RequestInfoBuilder;
import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.SearchAssetServiceRequest;

public class SearchAssetServiceRequestBuilder {

    SearchAssetServiceRequest searchAssetServiceRequest = new SearchAssetServiceRequest();
    RequestInfo requestInfo = new RequestInfoBuilder().build();

    public SearchAssetServiceRequestBuilder() {
        searchAssetServiceRequest.setRequestInfo(requestInfo);
    }

    public SearchAssetServiceRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        searchAssetServiceRequest.setRequestInfo(requestInfo);
        return this;
    }

    public SearchAssetServiceRequest build() {
        return searchAssetServiceRequest;
    }
}
