package builders.eGovEIS.Employee;

import entities.requests.eGovEIS.Employee.RequestInfo;
import entities.requests.eGovEIS.Employee.SearchEmployeeRequest;

public class SearchEmployeeRequestBuilder {

    SearchEmployeeRequest request = new SearchEmployeeRequest();
    RequestInfo requestInfo = new RequestInfoBuilder().build();

    public SearchEmployeeRequestBuilder(){
        request.setRequestInfo(requestInfo);
    }

    public SearchEmployeeRequestBuilder withRequestInfo(RequestInfo requestInfo1){
        request.setRequestInfo(requestInfo1);
        return this;
    }

    public SearchEmployeeRequest build(){
        return request;
    }
}
