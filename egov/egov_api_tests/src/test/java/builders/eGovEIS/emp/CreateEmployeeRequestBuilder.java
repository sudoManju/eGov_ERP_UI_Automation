package builders.eGovEIS.emp;

import entities.requests.eGovEIS.emp.CreateEmployeeRequest;
import entities.requests.eGovEIS.emp.Employee;
import entities.requests.eGovEIS.emp.RequestInfo;

public class CreateEmployeeRequestBuilder {

    CreateEmployeeRequest request = new CreateEmployeeRequest();

    RequestInfo requestInfo = new RequestInfoBuilder().build();

    public CreateEmployeeRequestBuilder(){
        request.setRequestInfo(requestInfo);
    }

    public CreateEmployeeRequestBuilder withEmployee(Employee employee){
        request.setEmployee(employee);
        return this;
    }

    public CreateEmployeeRequest build(){
        return request;
    }
}
