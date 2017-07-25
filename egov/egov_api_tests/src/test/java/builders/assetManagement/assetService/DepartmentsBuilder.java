package builders.assetManagement.assetService;

import entities.requests.assetManagement.assetServices.create.Department;

public class DepartmentsBuilder {

    Department department = new Department();

    public DepartmentsBuilder(){
        department.setId(913);
        department.setName("department_department6");
        department.setCode("code_code6");
    }

    public Department build(){
        return department;
    }
}
