package steps;

import cucumber.api.PendingException;
import cucumber.api.java8.Da;
import cucumber.api.java8.En;
import pages.DashboardPage;
import pages.wcms.WaterChargeManagementPage;

public class DashboardSteps extends BaseSteps implements En {
    public DashboardSteps() {

        And("^current user logs out$", () -> {
            pageStore.get(DashboardPage.class).logOut();
        });

        And("^chooses to act upon the above create application$", () -> {
            pageStore.get(DashboardPage.class).openApplicationNew(scenarioContext.getApplicationNumber());
        });

        And("^chooses to act upon the above assessment$", () -> {
            pageStore.get(DashboardPage.class).openApplicationNew(scenarioContext.getAssessmentNumber());
        });

        And("^chooses to act upon the above challan$", () -> {
            pageStore.get(DashboardPage.class).openApplicationNew(scenarioContext.getApplicationNumber());
        });

        And("^he open application from drafts items$", () -> {
            pageStore.get(DashboardPage.class).openApplicationNew("Property Tax");
        });

        And("^he chooses to act upon on receipt$", () -> {
            pageStore.get(DashboardPage.class).openApplicationNew("Property Tax");
        });

        When("^he chosses to collect water charges$", () -> {
            pageStore.get(DashboardPage.class).chooseTopayWaterCharge();
        });

        And("^he chooses to act upon the above preamble number$", () -> {
            pageStore.get(DashboardPage.class).openApplicationNew(scenarioContext.getApplicationNumber());
        });

        And("^he choose to act upon the above licence number$", () -> {
            pageStore.get(DashboardPage.class).openApplicationNew(scenarioContext.getApplicationNumber());
        });

        And("^he choose to act upon the above application number$", () -> {
           pageStore.get(DashboardPage.class).openApplicationNew(scenarioContext.getApplicationNumber());
            System.out.println("Application Number"+scenarioContext.getApplicationNumber());
        });

        And("^choose to act upon the above CRN$", () -> {
//            pageStore.get(DashboardPage.class).openApplicationNew(scenarioContext.getCRN());
            pageStore.get(DashboardPage.class).openApplicationNew(scenarioContext.getApplicationNumber());

        });

        And("^officer search for the assignment mode as (\\w+)$", (String assignmentMode) -> {
            pageStore.get(DashboardPage.class).chooseForModeOFAssignment(assignmentMode);
        });

        And("^choose to act upon the above CRN in his own dratfs$", () -> {
//            pageStore.get(DashboardPage.class).openApplicationNew(scenarioContext.getCRN());
            pageStore.get(DashboardPage.class).openApplicationNew(scenarioContext.getApplicationNumber());
        });

        And("^user will select the required screen as \"([^\"]*)\"$", (String screenName) -> {
            pageStore.get(DashboardPage.class).chooseScreen(screenName);
        });

        And("^user will select the required screen as \"([^\"]*)\" with condition as \"([^\"]*)\"$", (String screenName, String condition) -> {
            pageStore.get(DashboardPage.class).chooseScreen(screenName , condition);
        });
    }
}