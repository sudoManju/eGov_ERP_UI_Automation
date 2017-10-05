package steps.employeeManagement;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.employeeManagement.position.PositionPage;
import steps.BaseSteps;

public class PositionSteps extends BaseSteps implements En {

    public PositionSteps() {
        And("^user will enter the position details for creation$", () -> {
            pageStore.get(PositionPage.class).enterPositionDetails();
        });
        And("^user selects the position (.*) position (.*) and the position name as (.*)$", (String departmentID, String designationID, String positionID) -> {
            pageStore.get(PositionPage.class).selectPositionDetails(departmentID, designationID, positionID);
        });
        Then("^user clicks on create position button, and closes the page$", () -> {
            pageStore.get(PositionPage.class).selectPositionDetails();
        });
    }
}
