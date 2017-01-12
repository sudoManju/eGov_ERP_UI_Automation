package steps;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.DashboardPage;
import pages.wcms.WaterChargeManagementPage;

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
//            pageStore.get(DashboardPage.class).openApplication("00531-2017-UY");
        });

        And("^chooses to act upon the above assessment$", () -> {
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getAssessmentNumber());
        });

        When("^he chooses to collect taxes$", () -> {
            pageStore.get(DashboardPage.class).chooseToCollectTaxes();
        });

        When("^he chooses to Data Entry Screen$", () -> {
            pageStore.get(DashboardPage.class).chooseToDataEntryScreen();
        });
        When("^he chooses to search property$", () -> {
            pageStore.get(DashboardPage.class).chooseToSearchProperty();
        });

        When("^he chooses to create Miscellaneous receipt$", () -> {
           pageStore.get(DashboardPage.class).createMiscellenous();
        });

        When("^he chooses to create Challan$", () -> {
            pageStore.get(DashboardPage.class).createChallan();
        });

        When("^user chooses to data entry screen$", () -> {
            pageStore.get(DashboardPage.class).chooseToCreateNewDataEntryScreen();
        });

        And("^user chooses to apply for new water connection$", () -> {
            pageStore.get(DashboardPage.class).chooseToCreateNewWaterConnection();
        });

        And("^user chooses to find the daily collection vlt reports$", () -> {
            pageStore.get(DashboardPage.class).chooseToFindDailyVLTReports();
        });

        When("^he chooses to addition alteration$", () -> {
            pageStore.get(DashboardPage.class).chooseToAdditionAlteration();
        });

        When("^he chooses to create data entry$", () -> {
            pageStore.get(DashboardPage.class).chooseToCreateDataEntry();
        });

        When("^he chooses to create new spillover estimate$", () -> {
            pageStore.get(DashboardPage.class).createSpilloverEstimate();
        });
        And("^user chooses to apply for new additional water connection$", () -> {
            pageStore.get(DashboardPage.class).chooseToAdditionalWaterConnection();
        });
        And("^chooses to act upon the above challan$", () -> {
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getChallanNumber());
        });

        And("^he search for challan receipt$", () -> {
            pageStore.get(DashboardPage.class).chooseToSearchForChallanReceipt();
        });

        And("^user chooses to find the daily collection pt reports$", () -> {
            pageStore.get(DashboardPage.class).chooseForDailyCollectionPTReports();
        });

        Then("^user will search for the recent application$", () -> {
            pageStore.get(DashboardPage.class).chooseForSearchApplication();
        });

        And("^user chooses to act upon the above application in search applications$", () -> {
            pageStore.get(DashboardPage.class).openSearchApplication(scenarioContext.getApplicationNumber());
        });

        And("^he clicks on drafts$", () -> {
            pageStore.get(DashboardPage.class).openDrafts();

        });
        And("^he open application from drafts items$", () -> {
            pageStore.get(DashboardPage.class).openCollection();
        });
        And("^he chooses to act upon on receipt$", () -> {
            pageStore.get(DashboardPage.class).openReceipt();
        });

        When("^he chooses to collect tax of property$", () -> {
            pageStore.get(DashboardPage.class).choosePropertyTaxCollection();
        });

        And("^user search to apply for closure of connection$", () -> {
            pageStore.get(DashboardPage.class).chooseToApplyForClosureConnection();
        });

        When("^he chooses to create new License$", () -> {
           pageStore.get(DashboardPage.class).chooseToCreateNewLicense();
        });

        And("^officer search for the create journal voucher$", () -> {
            pageStore.get(DashboardPage.class).chooseToApplyForJournalVoucher();
        });

        When("^he chosses to collect water charges$", () -> {
            pageStore.get(DashboardPage.class).chooseTopayWaterCharge();
        });

        And("^officer search for the bill payment$", () -> {
            pageStore.get(DashboardPage.class).selectForVoucherBill();
        });

        And("^he chooses to create milestone$", () -> {
           pageStore.get(DashboardPage.class).chooseForCreateMilestone();
        });

        When("^he chooses to create new estimate$", () -> {
           pageStore.get(DashboardPage.class).chooseForCreateEstimate();
        });

        And("^officer will search for the new expense bill$", () -> {
            pageStore.get(DashboardPage.class).toCreateNewExpenseBill();
        });

        And("^he choose to search trade license$", () -> {
           pageStore.get(DashboardPage.class).searchTradeLicense();
        });

        When("^he choose to create legency trade license$", () -> {
            // Write code here that turns the phrase above into concrete actions
            pageStore.get(DashboardPage.class).searchLegencyTradeLicense();
        });

        And("^user search for the modify detailed code$", () -> {
            pageStore.get(DashboardPage.class).chooseToModifyDetailedCode();
        });

        And("^officer will search for the Create Remittance Recovery$", () -> {
            pageStore.get(DashboardPage.class).createRemittanceRecovery();
        });

        And("^user search to apply for change of use for existing connection$", () -> {
            pageStore.get(DashboardPage.class).chooseToChangeOfUse();
        });

        When("^he choose to create preamble$", () -> {
            pageStore.get(DashboardPage.class).choosesToCreatePreamble();
        });

        And("^user search to data entry screen for water$", () -> {
            pageStore.get(DashboardPage.class).createDataEntryScreenForWater();
        });

        And("^he chooses to act upon the above preamble number$", () -> {
            pageStore.get(DashboardPage.class).openApplication(scenarioContext.getPreambleNumber());
        });

        And("^officer will search for the Create Voucher for expense bill$", () -> {
            pageStore.get(DashboardPage.class).createExpenseBillVoucher();
        });

    }
}



