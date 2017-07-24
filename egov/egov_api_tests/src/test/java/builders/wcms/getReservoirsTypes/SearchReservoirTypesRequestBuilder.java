package builders.wcms.getReservoirsTypes;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.getReservoirsTypes.SearchReservoirTypesRequest;

public class SearchReservoirTypesRequestBuilder {
    SearchReservoirTypesRequest searchReservoirTypesRequest = new SearchReservoirTypesRequest();

    public SearchReservoirTypesRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        searchReservoirTypesRequest.setRequestInfo(requestInfo);
        return this;
    }

    public SearchReservoirTypesRequest build() {
        return searchReservoirTypesRequest;
    }
}
