package builders.propertyTax.masters.usage;

import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.masters.usage.SearchMasterRequest;

public class SearchUsageMasterRequestBuilder {

    SearchMasterRequest request = new SearchMasterRequest();

    public SearchUsageMasterRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        request.setRequestInfo(requestInfo);
        return this;
    }

    public SearchMasterRequest build() {
        return request;
    }
}
