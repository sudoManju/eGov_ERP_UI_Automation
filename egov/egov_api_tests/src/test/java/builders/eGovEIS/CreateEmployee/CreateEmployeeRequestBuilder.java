package builders.eGovEIS.CreateEmployee;


import entities.requests.eGovEIS.Employee.CreateEmployeeRequest;
import entities.requests.eGovEIS.Employee.Employee;
import entities.requests.eGovEIS.Employee.RequestInfo;

public class CreateEmployeeRequestBuilder {

    CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();

    public CreateEmployeeRequestBuilder(){}

    public CreateEmployeeRequestBuilder withRequestInfo(RequestInfo requestInfo){
        createEmployeeRequest.setRequestInfo(requestInfo);
        return this;
    }

    public CreateEmployeeRequestBuilder withEmployee(Employee employee){
        createEmployeeRequest.setEmployee(employee);
        return this;
    }

    public CreateEmployeeRequest build(){
        return  createEmployeeRequest;
    }
}
