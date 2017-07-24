package builders.wcms.storageReservoir.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.storageReservoir.search.SearchStorageReservoirRequest;

public class SearchStorageReservoirRequestBuilder {
    SearchStorageReservoirRequest searchStorageReservoirRequest = new SearchStorageReservoirRequest();

    public SearchStorageReservoirRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        searchStorageReservoirRequest.setRequestInfo(requestInfo);
        return this;
    }

    public SearchStorageReservoirRequest build() {
        return searchStorageReservoirRequest;
    }
}
