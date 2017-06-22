package builders.wcms.categoryType.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.categoryType.search.SearchCategoryTypeRequest;

public class SearchCategoryTypeRequestBuilder {

    SearchCategoryTypeRequest searchCategoryTypeRequest = new SearchCategoryTypeRequest();

    public SearchCategoryTypeRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        searchCategoryTypeRequest.setRequestInfo(requestInfo);
        return this;
    }

    public SearchCategoryTypeRequest build() {
        return searchCategoryTypeRequest;
    }
}
