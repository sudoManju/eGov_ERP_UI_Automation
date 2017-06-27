package builders.wcms.supplyType.search;

import entities.requests.wcms.RequestInfo;
import entities.requests.wcms.supplyType.search.SearchSupplyTypeRequest;

public class SearchSupplyTypeRequestBuilder {

    SearchSupplyTypeRequest searchSupplyTypeRequest = new SearchSupplyTypeRequest();

    public SearchSupplyTypeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        searchSupplyTypeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public SearchSupplyTypeRequest build() {
        return searchSupplyTypeRequest;
    }
}
