package builders.wcms.searchBoundary;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.searchBoundary.SearchBoundaryRequest;

public class SearchBoundaryRequestBuilder {
    SearchBoundaryRequest searchBoundaryRequest = new SearchBoundaryRequest();

    public SearchBoundaryRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        searchBoundaryRequest.setRequestInfo(requestInfo);
        return this;
    }

    public SearchBoundaryRequest build() {
        return searchBoundaryRequest;
    }
}
