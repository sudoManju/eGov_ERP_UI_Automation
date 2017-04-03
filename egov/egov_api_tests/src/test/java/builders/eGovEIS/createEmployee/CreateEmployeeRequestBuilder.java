package builders.eGovEIS.createEmployee;

import entities.requests.eGovEIS.createEmployee.CreateEmployeeRequest;
import entities.requests.eGovEIS.createEmployee.Employee;
import entities.requests.eGovEIS.createEmployee.RequestInfo;

public final class CreateEmployeeRequestBuilder {
    CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
    Employee employee = new EmployeeBuilder().build();

    public CreateEmployeeRequestBuilder() {
        createEmployeeRequest.setEmployee(employee);
    }

    public CreateEmployeeRequestBuilder withRequestInfo(RequestInfo RequestInfo) {
        createEmployeeRequest.setRequestInfo(RequestInfo);
        return this;
    }

    public CreateEmployeeRequestBuilder withEmployee(Employee Employee) {
        createEmployeeRequest.setEmployee(Employee);
        return this;
    }

    public CreateEmployeeRequest build() { return createEmployeeRequest; }
}
