package builders.wcms.propertyTypeUsageType;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.propertyTypeUsageType.search.SearchPropertyTypeUsageTypeRequest;

public class SearchPropertyTypeUsageTypeRequestBuilder {
    SearchPropertyTypeUsageTypeRequest searchPropertyTypeUsageTypeRequest = new SearchPropertyTypeUsageTypeRequest();

    public SearchPropertyTypeUsageTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        searchPropertyTypeUsageTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public SearchPropertyTypeUsageTypeRequest build() {
        return searchPropertyTypeUsageTypeRequest;
    }
}
