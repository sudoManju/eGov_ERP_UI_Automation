package builders.collections.businessCategory.search;

import entities.requests.collections.RequestInfo;
import entities.requests.collections.businessCategory.search.SearchBusinessCategoryRequest;

public class SearchBusinessCategoryRequestBuilder {

    SearchBusinessCategoryRequest searchBusinessCategoryRequest = new SearchBusinessCategoryRequest();

    public SearchBusinessCategoryRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        searchBusinessCategoryRequest.setRequestInfo(requestInfo);
        return this;
    }

    public SearchBusinessCategoryRequest build() {
        return searchBusinessCategoryRequest;
    }
}
