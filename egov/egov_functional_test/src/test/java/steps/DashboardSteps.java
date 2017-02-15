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
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getApplicationNumber());
        });

        And("^chooses to act upon the above assessment$", () -> {
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getAssessmentNumber());
        });

        And("^chooses to act upon the above challan$", () -> {
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getChallanNumber());
        });

        And("^user chooses to find the daily collection pt reports$", () -> {
            pageStore.get(DashboardPage.class).chooseForDailyCollectionPTReports();
        });

        And("^he clicks on drafts$", () -> {
            pageStore.get(DashboardPage.class).openDrafts();

        });
        And("^he open application from drafts items$", () -> {
            pageStore.get(DashboardPage.class).openCollection("official_drafts");
        });
        And("^he chooses to act upon on receipt$", () -> {
            pageStore.get(DashboardPage.class).openReceipt("official_inbox");
        });

        When("^he chosses to collect water charges$", () -> {
            pageStore.get(DashboardPage.class).chooseTopayWaterCharge();
        });

        And("^user search for the modify detailed code$", () -> {
            pageStore.get(DashboardPage.class).chooseToModifyDetailedCode();
        });

        And("^he chooses to act upon the above preamble number$", () -> {
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getPreambleNumber());
        });

        And("^he choose to do transfer ownership$", () -> {
            pageStore.get(DashboardPage.class).createTransferOwnership();
        });

        When("^he chooses mutation fee$", () -> {
            pageStore.get(DashboardPage.class).chooseToPayMutationFee();
        });

        And("^he choose to act upon the above licence number$", () -> {
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getLicenseNumber());
            System.out.println("Closure License Number"+scenarioContext.getLicenseNumber());
        });

        And("^he choose to act upon the above application number$", () -> {
           pageStore.get(DashboardPage.class).openApplication(scenarioContext.getApplicationNumber());
            System.out.println("Application Number"+scenarioContext.getApplicationNumber());
        });

        And("^choose to act upon the above CRN$", () -> {
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getCRN());
        });

        When("^he choose to create revision petition$", () -> {
           pageStore.get(DashboardPage.class).chooseRevisionPetition();
        });

        And("^officer search for the assignment mode as (\\w+)$", (String assignmentMode) -> {
            pageStore.get(DashboardPage.class).chooseForModeOFAssignment(assignmentMode);
        });

        And("^choose to act upon the above CRN in his own dratfs$", () -> {
            pageStore.get(DashboardPage.class).openApplicationInDrafts(scenarioContext.getCRN());
        });

        And("^user will select the required screen as \"([^\"]*)\"$", (String screenName) -> {
            pageStore.get(DashboardPage.class).chooseScreen(screenName);
        });

        And("^user will select the required screen as \"([^\"]*)\" with condition as \"([^\"]*)\"$", (String screenName, String condition) -> {
            pageStore.get(DashboardPage.class).chooseScreen(screenName , condition);
        });
    }
}



