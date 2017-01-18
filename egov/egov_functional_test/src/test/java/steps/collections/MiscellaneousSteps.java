package steps.collections;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.collections.PaymentMethod;
import pages.collections.MiscellaneousPage;
import pages.collections.PropertyTaxPage;
import steps.BaseSteps;
import utils.ExcelReader;


/**
 * Created by soumyaghosh on 01/12/16.
 */
public class MiscellaneousSteps extends BaseSteps implements En {
    public MiscellaneousSteps() {
        And("^he enters Miscellaneous header$", () -> {
            pageStore.get((MiscellaneousPage.class)).enterMiscellaneousDetails();
        });
        And("^he pays using (\\w+)$", (String paymentMode) -> {
            PaymentMethod paymentmethod = new ExcelReader(collectionsTestDataFileName).getPaymentMethodDetails(paymentMode);
            pageStore.get(MiscellaneousPage.class).enterPaymentDetails(paymentmethod, paymentMode);
        });
    }
}
