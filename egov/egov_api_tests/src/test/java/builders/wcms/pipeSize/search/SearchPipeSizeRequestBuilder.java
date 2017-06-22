package builders.wcms.pipeSize.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.pipeSize.search.SearchPipeSizeRequest;

public class SearchPipeSizeRequestBuilder {
    SearchPipeSizeRequest searchPipeSizeRequest = new SearchPipeSizeRequest();

    public SearchPipeSizeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        searchPipeSizeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public SearchPipeSizeRequest build() {
        return searchPipeSizeRequest;
    }
}
