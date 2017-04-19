package steps.employeeManagement;


import cucumber.api.java8.En;
import steps.BaseSteps;

public class employeeOperationSteps extends BaseSteps implements En {

    public employeeOperationSteps(){

        And("^user enters the employee details as (\\w+)$", (String employeeDetailsDataId) -> {

        });
    }
}
