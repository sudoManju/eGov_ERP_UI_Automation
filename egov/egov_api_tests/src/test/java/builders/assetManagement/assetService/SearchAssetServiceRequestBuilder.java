package builders.assetManagement.assetService;

import entities.requests.assetManagement.RequestInfo;
import entities.requests.assetManagement.assetServices.search.SearchAssetServiceRequest;

public final class SearchAssetServiceRequestBuilder {
    RequestInfo requestInfo;

    public SearchAssetServiceRequestBuilder() {
    }

    public static SearchAssetServiceRequestBuilder searchAssetServiceRequest() {
        return new SearchAssetServiceRequestBuilder();
    }

    public SearchAssetServiceRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
        return this;
    }

    public SearchAssetServiceRequest build() {
        SearchAssetServiceRequest searchAssetServiceRequest = new SearchAssetServiceRequest();
        searchAssetServiceRequest.setRequestInfo(requestInfo);
        return searchAssetServiceRequest;
    }
}