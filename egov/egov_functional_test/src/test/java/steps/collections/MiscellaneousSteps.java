package steps.collections;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.collections.MiscellaneousPage;
import pages.collections.PropertyTaxPage;
import steps.BaseSteps;


/**
 * Created by soumyaghosh on 01/12/16.
 */
public class MiscellaneousSteps extends BaseSteps implements En {
    public MiscellaneousSteps() {
        And("^he enters Miscellaneous header$", () -> {
            pageStore.get((MiscellaneousPage.class)).enterMiscellaneousDetails();
        });
    }
}
