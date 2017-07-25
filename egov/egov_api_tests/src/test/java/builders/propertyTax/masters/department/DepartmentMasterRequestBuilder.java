package builders.propertyTax.masters.department;

import entities.requests.propertyTax.RequestInfo;
import entities.requests.propertyTax.masters.department.DepartmentMasterRequest;
import entities.requests.propertyTax.masters.department.Departments;

public class DepartmentMasterRequestBuilder {

    DepartmentMasterRequest request = new DepartmentMasterRequest();

    public DepartmentMasterRequestBuilder() {
    }

    public DepartmentMasterRequestBuilder withRequestInfo(RequestInfo requestInfo) {
        request.setRequestInfo(requestInfo);
        return this;
    }

    public DepartmentMasterRequestBuilder withDepartments(Departments[] departments) {
        request.setDepartments(departments);
        return this;
    }

    public DepartmentMasterRequest build() {
        return request;
    }
}
