package steps;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.DashboardPage;

public class DashboardSteps extends BaseSteps implements En {
    public DashboardSteps() {
        When("^he chooses to create new property$", () -> {
            pageStore.get(DashboardPage.class).chooseToCreateNewProperty();
        });
        And("^current user logs out$", () -> {
            pageStore.get(DashboardPage.class).logOut();
        });
        And("^chooses to act upon the above application$", () -> {
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getApplicationNumber());
        });
        And("^chooses to act upon the above assessment$", () -> {
            // Write code here that turns the phrase above into concrete actions
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getAssessmentNumber());
        });
        When("^he chooses to collect taxes$", () -> {
            pageStore.get(DashboardPage.class).chooseToCollectTaxes();
        });

<<<<<<< HEAD
        When("^he chooses to Data Entry Screen$", () -> {
            pageStore.get(DashboardPage.class).chooseToDataEntryScreen();
        });
        When("^he chooses to search property$", () -> {
            pageStore.get(DashboardPage.class).chooseToSearchProperty();
        });

        When("^he chooses to create Miscellaneous receipt$", () -> {
                // Write code here that turns the phrase above into concrete actions
//            pageStore.get(DashboardPage.class).createMiscellenous();

            });
        And("^he chooses to apply for new connection$", () -> {
            pageStore.get(DashboardPage.class).chooseToApplyForConnection();
        });
        When("^he chooses to create Challan$", () -> {
            pageStore.get(DashboardPage.class).createChallan();
=======
        When("^user chooses to data entry screen$", () -> {
            pageStore.get(DashboardPage.class).chooseToCreateNewDataEntryScreen();
        });
        When("^he chooses to create Miscellaneous receipt$", () -> {
                // Write code here that turns the phrase above into concrete actions
                pageStore.get(DashboardPage.class).createMiscellenous();

        });
        And("^he chooses to apply for new water connection$", () -> {
            pageStore.get(DashboardPage.class).chooseToCreateNewWaterConnection();
        });
        And("^user chooses to find the daily collection vlt reports$", () -> {
            pageStore.get(DashboardPage.class).chooseToFindDailyVLTReports();
        });

        When("^he chooses to addition alteration$", () -> {
            // Write code here that turns the phrase above into concrete actions
            pageStore.get(DashboardPage.class).chooseToAdditionAlteration();
        });
        When("^he chooses to create data entry$", () -> {
            // Write code here that turns the phrase above into concrete actions
           pageStore.get(DashboardPage.class).chooseToCreateDataEntry();
        });

        When("^he chooses to create new spillover estimate$", () -> {
        pageStore.get(DashboardPage.class).createSpilloverEstimate();
>>>>>>> 2256981a4bb263d19c81a9a32b7c999df22ea498
        });
    }
}