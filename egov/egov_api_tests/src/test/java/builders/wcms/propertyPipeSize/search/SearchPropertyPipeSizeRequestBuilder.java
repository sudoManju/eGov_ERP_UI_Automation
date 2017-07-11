package builders.wcms.propertyPipeSize.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyPipeSize.search.SearchPropertyPipeSizeRequest;

public class SearchPropertyPipeSizeRequestBuilder {
    SearchPropertyPipeSizeRequest searchPropertyPipeSizeRequest = new SearchPropertyPipeSizeRequest();

    public SearchPropertyPipeSizeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        searchPropertyPipeSizeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public SearchPropertyPipeSizeRequest build() {
        return searchPropertyPipeSizeRequest;
    }
}
