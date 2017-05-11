package steps.employeeManagement;


import cucumber.api.java8.En;
import entities.employeeManagement.AssignmentDetails;
import entities.employeeManagement.EmployeeDetails;
import entities.employeeManagement.JurisdictionDetails;
import excelDataFiles.EmployeeManagementDetailsDataReader;
import pages.employeeManagement.AssignmentDetailsPage;
import pages.employeeManagement.EmployeeDetailsPage;
import pages.employeeManagement.EmployeeOtherDetailsPage;
import steps.BaseSteps;

public class EmployeeOperationSteps extends BaseSteps implements En {

    public EmployeeOperationSteps() {

        And("^user enters the employee details as (\\w+)$", (String employeeDetailsDataId) -> {
            EmployeeDetails employeeDetails = new EmployeeManagementDetailsDataReader(eisTestDataFileName).getEmployeeDetails(employeeDetailsDataId);
            scenarioContext.setApplicationNumber(pageStore.get(EmployeeDetailsPage.class).enterEmployeeDetails(employeeDetails));
        });

        And("^user will enter the assignment details as (\\w+)$", (String dataId) -> {
            AssignmentDetails assignmentDetails = new EmployeeManagementDetailsDataReader(eisTestDataFileName).getAssignmentDetails(dataId);
            pageStore.get(AssignmentDetailsPage.class).enterAssignmentDetails(assignmentDetails);
        });

        And("^user will enter the jurisdiction details as (\\w+)$", (String dataId) -> {
            JurisdictionDetails jurisdictionDetails = new EmployeeManagementDetailsDataReader(eisTestDataFileName).getJurisdictionDetails(dataId);
            pageStore.get(EmployeeOtherDetailsPage.class).enterJurisdictionDetails(jurisdictionDetails);
        });
        Then("^user clicks on submit button$", () -> {
            pageStore.get(EmployeeOtherDetailsPage.class).submitCreateEmployee();

        });
        Then("^user close the employee search$", () -> {
            pageStore.get(EmployeeOtherDetailsPage.class).closeEmployeeSearch();
        });
        And("^user will enter the service section and other details$", () -> {
            pageStore.get(EmployeeOtherDetailsPage.class).enterServiceSectionDetails();
            pageStore.get(EmployeeOtherDetailsPage.class).enterProbationDetails();
            pageStore.get(EmployeeOtherDetailsPage.class).enterRegularisationDetails();
            pageStore.get(EmployeeOtherDetailsPage.class).enterEducationDetails();
            pageStore.get(EmployeeOtherDetailsPage.class).enterTechnicalQualificationDetails();
            pageStore.get(EmployeeOtherDetailsPage.class).enterDepartmentalTestDetails();
        });

    }
}
