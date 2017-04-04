package builders.eGovEIS;

import entities.requests.eGovEIS.RequestInfo;
import entities.requests.eGovEIS.SearchEmployeeRequest;

public final class SearchEmployeeRequestBuilder {

    SearchEmployeeRequest searchEmployeeRequest = new SearchEmployeeRequest();

    RequestInfo requestInfo = new RequestInfoBuilder("search").build1();

    public SearchEmployeeRequestBuilder() {
        searchEmployeeRequest.setRequestInfo(requestInfo);
    }

    public SearchEmployeeRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        searchEmployeeRequest.setRequestInfo(requestInfo);
        return this;
    }

    public SearchEmployeeRequest build() {
        return searchEmployeeRequest;
    }
}
