package builders.propertyTax.masters.department;

import entities.requests.propertyTax.masters.RequestInfo;
import entities.requests.propertyTax.masters.department.CreateDepartmentRequest;
import entities.requests.propertyTax.masters.department.Departments;
import org.apache.commons.lang3.RandomUtils;

public class CreateDepartmentRequestBuilder {

    CreateDepartmentRequest request = new CreateDepartmentRequest();

    String num = String.valueOf((RandomUtils.nextInt(100, 999)));

    Departments[] departments = new Departments[1];

    Departments departments1 = new DepartmentsBuilder().withName("Test"+num).withCode(num).withNameLocal("Test"+num).build();

    public CreateDepartmentRequestBuilder(){
        departments[0] = departments1;
        request.setDepartments(departments);
    }

    public CreateDepartmentRequestBuilder withRequestInfo(RequestInfo requestInfo){
        request.setRequestInfo(requestInfo);
        return this;
    }

    public CreateDepartmentRequest build(){
        return request;
    }
}
