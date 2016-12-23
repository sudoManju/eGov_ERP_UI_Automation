package steps.tradeLicense;

import cucumber.api.java8.En;
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
    }
}
