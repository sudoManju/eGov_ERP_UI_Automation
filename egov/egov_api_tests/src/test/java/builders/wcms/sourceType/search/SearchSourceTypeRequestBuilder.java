package builders.wcms.sourceType.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.sourceType.search.SearchSourceTypeRequest;

public class SearchSourceTypeRequestBuilder {
    SearchSourceTypeRequest searchSourceTypeRequest = new SearchSourceTypeRequest();

    public SearchSourceTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        searchSourceTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public SearchSourceTypeRequest build() {
        return searchSourceTypeRequest;
    }
}
