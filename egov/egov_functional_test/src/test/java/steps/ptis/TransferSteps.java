package steps.ptis;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.collections.CollectionsPage;
import pages.ptis.PropertyAcknowledgementPage;
import pages.ptis.PropertyDetailsPage;
import steps.PageStore;

import static steps.BaseSteps.pageStore;

/**
 * Created by bimal on 13/12/16.
 */
public class TransferSteps implements En {
    public TransferSteps() {
        And("^he pay tax using Cash$", () -> {
            // Write code here that turns the phrase above into concrete actions
            pageStore.get(PropertyDetailsPage.class).payCash();
            pageStore.get(PropertyAcknowledgementPage.class).close1();
        });
    }
}
