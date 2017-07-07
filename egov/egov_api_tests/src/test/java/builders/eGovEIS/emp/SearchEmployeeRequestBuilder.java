package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.RequestInfo;
import entities.requests.eGovEIS.emp.SearchEmployeeRequest;

public class SearchEmployeeRequestBuilder {

    RequestInfo requestInfo = new RequestInfoBuilder().build();

    SearchEmployeeRequest request = new SearchEmployeeRequest();

    public SearchEmployeeRequestBuilder() {
        request.setRequestInfo(requestInfo);
    }

    public SearchEmployeeRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        request.setRequestInfo(requestInfo);
        return this;
    }

    public SearchEmployeeRequest build() {
        return request;
    }
}
