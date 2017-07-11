package builders.wcms.propertyCategoryType.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyCategoryType.search.SearchPropertyCategoryTypeRequest;

public class SearchPropertyCategoryTypeRequestBuilder {
    SearchPropertyCategoryTypeRequest seachPropertyCategoryTypeRequest = new SearchPropertyCategoryTypeRequest();

    public SearchPropertyCategoryTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        seachPropertyCategoryTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public SearchPropertyCategoryTypeRequest build() {
        return seachPropertyCategoryTypeRequest;
    }
}
