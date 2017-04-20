package steps.employeeManagement;


import cucumber.api.java8.En;
import steps.BaseSteps;

public class EmployeeOperationSteps extends BaseSteps implements En {

    public EmployeeOperationSteps(){

        And("^user enters the employee details as (\\w+)$", (String employeeDetailsDataId) -> {

        });

        And("^user will enter the assignment details as (\\w+)$", () -> {

        });
    }
}
