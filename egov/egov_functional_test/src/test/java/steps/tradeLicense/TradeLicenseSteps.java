package steps.tradeLicense;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import cucumber.api.java8.Tr;
import entities.ptis.ApprovalDetails;
import entities.tradeLicense.*;
import pages.ptis.PropertyDetailsPage;
import pages.tradeLicense.TradeLicensePage;
import steps.BaseSteps;
import utils.ExcelReader;


/**
 * Created by bimal on 20/12/16.
 */
public class TradeLicenseSteps extends BaseSteps implements En {
    public TradeLicenseSteps() {
        And("^he enters trade owner details of new license (\\w+)$", (String tradeDetailsData) -> {

            TradeOwnerDetails tradeOwnerDetails = new ExcelReader(tradeLicenseTestDataFileName).getTradeOwnerDetails(tradeDetailsData);
           pageStore.get(TradeLicensePage.class).entertradeOwnerDetails(tradeOwnerDetails);
        });
        And("^he enters trade location details of new license (\\w+)$", (String tradeLocationData) -> {
            // Write code here that turns the phrase above into concrete actions
           TradeLocationDetails tradelocationDetails = new ExcelReader(tradeLicenseTestDataFileName).getTradeLocationDetails(tradeLocationData);
            pageStore.get(TradeLicensePage.class).entertradeLocationDetails(tradelocationDetails);

        });
        And("^he enters trade details of new license (\\w+)$", (String tradeDetailsData) -> {
            // Write code here that turns the phrase above into concrete actions

            TradeDetails tradedetails = new ExcelReader(tradeLicenseTestDataFileName).getTradeDetails(tradeDetailsData);
            pageStore.get(TradeLicensePage.class).entertradeDetails(tradedetails);

        });
        And("^he search existing application number$", () -> {
            // Write code here that turns the phrase above into concrete actions
            pageStore.get(TradeLicensePage.class).enterApplicationNumber();
            pageStore.get(TradeLicensePage.class).clickOnSearchButton();
        });
        And("^he choose to collectfees$", () -> {
            // Write code here that turns the phrase above into concrete actions
            pageStore.get(TradeLicensePage.class).chooseCollectFees();
        });
        And("^he choose to payTax of applicationNumber$", () -> {
            // Write code here that turns the phrase above into concrete actions
            pageStore.get(TradeLicensePage.class).chooseToPayTaxOfApplicationNumber();
        });
        And("^he enters old license number$", () -> {
            // Write code here that turns the phrase above into concrete actions
           pageStore.get(TradeLicensePage.class).chooseOldTradeLicnese();
        });
        And("^he copy trade application number$", () -> {
            // Write code here that turns the phrase above into concrete actions
            pageStore.get(TradeLicensePage.class).copyApplicationNumber();
        });
        And("^he enters fee details of legency trade license (\\w+)$", (String legencyDetailsData) -> {
            // Write code here that turns the phrase above into concrete actions
            LegencyDetails legencyDetails = new ExcelReader(tradeLicenseTestDataFileName).getLegencyDetails(legencyDetailsData);
            pageStore.get(TradeLicensePage.class).enterlegencyDetails(legencyDetails);
        });
        And("^he choose a trade license for closure as (\\w+)$", (String ClosureData) -> {
           LicenseClosureDetails closureDetails=new ExcelReader(tradeLicenseTestDataFileName).getDetailsForClosure(ClosureData);
            pageStore.get(TradeLicensePage.class).enterDetailsForClosure(closureDetails);
            String licenseNumber= pageStore.get(TradeLicensePage.class).getLicenseNumber();
            scenarioContext.setLicenseNumber(licenseNumber);
        });
        And("^he closes the acknowledgement page$", () -> {
            pageStore.get(TradeLicensePage.class).closeAcknowledgement();
        });
        And("^he approves the closure$", () -> {
            pageStore.get(TradeLicensePage.class).closureApproval();
        });
        And("^he forwards for approver (.*)$", (String approvalDetailsDataId) -> {
            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approvalDetailsDataId);
            pageStore.get(TradeLicensePage.class).enterApprovalDetails(approvalDetails);
            pageStore.get(TradeLicensePage.class).forward();
        });


    }
}
