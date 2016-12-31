package steps.tradeLicense;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.tradeLicense.TradeDetails;
import entities.tradeLicense.TradeLocationDetails;
import entities.tradeLicense.TradeOwnerDetails;
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


    }
}
