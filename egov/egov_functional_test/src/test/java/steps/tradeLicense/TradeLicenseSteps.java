package steps.tradeLicense;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import cucumber.api.java8.Tr;
import entities.ptis.ApprovalDetails;
import entities.tradeLicense.*;
import org.apache.commons.lang.math.RandomUtils;
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
            TradeLocationDetails tradelocationDetails = new ExcelReader(tradeLicenseTestDataFileName).getTradeLocationDetails(tradeLocationData);
            pageStore.get(TradeLicensePage.class).entertradeLocationDetails(tradelocationDetails);

        });
        And("^he enters trade details of new license (\\w+)$", (String tradeDetailsData) -> {
            TradeDetails tradedetails = new ExcelReader(tradeLicenseTestDataFileName).getTradeDetails(tradeDetailsData);
            pageStore.get(TradeLicensePage.class).entertradeDetails(tradedetails);

        });
        And("^he search existing application number$", () -> {
            pageStore.get(TradeLicensePage.class).enterApplicationNumber(scenarioContext.getApplicationNumber());
        });
        And("^he choose to collectfees$", () -> {
            pageStore.get(TradeLicensePage.class).chooseCollectFees();
        });
        And("^he choose to payTax of applicationNumber$", () -> {
            pageStore.get(TradeLicensePage.class).chooseToPayTaxOfApplicationNumber();
        });
        And("^he enters old license number$", () -> {
           pageStore.get(TradeLicensePage.class).chooseOldTradeLicense();
        });
        And("^he copy trade application number$", () -> {
            String applicationNumber = pageStore.get(TradeLicensePage.class).getApplicationNumber();
            scenarioContext.setApplicationNumber(applicationNumber);
        });
        And("^he enters fee details of legency trade license$", () -> {
            pageStore.get(TradeLicensePage.class).enterlegencyDetails();
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
        And("^he approves application$", () -> {
            pageStore.get(TradeLicensePage.class).applicationApproval();
        });

        And("^he generates the license certificate$", () -> {
            pageStore.get(TradeLicensePage.class).generateLicenseCertificate();
        });
        And("^he search trade license with application number$", () -> {
            String searchId = "searchWithApplicationNumber";
            SearchTradeDetails searchTradeDetails = new ExcelReader(tradeLicenseTestDataFileName).getTradeSearchDetails(searchId);
            pageStore.get(TradeLicensePage.class).enterApplicationNumberReadingFromExcel(searchTradeDetails);

        });
        And("^he copies the license number and closes the acknowledgement$", () -> {
            scenarioContext.setApplicationNumber(pageStore.get(TradeLicensePage.class).getLegacyLicenseNumber());
            System.out.println("Application Number "+scenarioContext.getApplicationNumber());
        });
        And("^he choose to search with license number$", () -> {
           pageStore.get(TradeLicensePage.class).enterLicenseNumber(scenarioContext.getApplicationNumber());
        });
        And("^he choose to renew trade license$", () -> {
            pageStore.get(TradeLicensePage.class).chooseToRenewLicense();
        });
        And("^he checks total number of records$", () -> {
           pageStore.get(TradeLicensePage.class).checkNoOfRecords();
        });
        And("^he search trade license with license number$", () -> {
            String searchId = "searchWithLicenseNumber";
            SearchTradeDetails searchTradeDetails = new ExcelReader(tradeLicenseTestDataFileName).getTradeSearchDetails(searchId);
            pageStore.get(TradeLicensePage.class).enterLicenseNumber(searchTradeDetails.getLicenseNumber());

        });
        And("^he search trade license with status \"([^\"]*)\"$", (String status) -> {
            pageStore.get(TradeLicensePage.class).enterStatus(status);
        });
        And("^he approves the closure$", () -> {
            pageStore.get(TradeLicensePage.class).closureApproval();
        });


    }
}
