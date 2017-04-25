package steps.employeeManagement;


import cucumber.api.java8.En;
import entities.employeeManagement.AssignmentDetails;
import entities.employeeManagement.EmployeeDetails;
import excelDataFiles.EmployeeManagementDetailsDataReader;
import pages.employeeManagement.AssignmentDetailsPage;
import pages.employeeManagement.EmployeeDetailsPage;
import steps.BaseSteps;

public class EmployeeOperationSteps extends BaseSteps implements En {

    public EmployeeOperationSteps(){

        And("^user enters the employee details as (\\w+)$", (String employeeDetailsDataId) -> {
            EmployeeDetails employeeDetails = new EmployeeManagementDetailsDataReader(eisTestDataFileName).getEmployeeDetails(employeeDetailsDataId);
            pageStore.get(EmployeeDetailsPage.class).enterEmployeeDetails(employeeDetails);
        });

        And("^user will enter the assignment details as (\\w+)$", (String dataId) -> {
            AssignmentDetails assignmentDetails = new EmployeeManagementDetailsDataReader(eisTestDataFileName).getAssignmentDetails(dataId);
            pageStore.get(AssignmentDetailsPage.class).enterAssignmentDetails(assignmentDetails);
        });
    }
}
