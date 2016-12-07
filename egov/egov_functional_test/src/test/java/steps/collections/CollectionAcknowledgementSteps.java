package steps.collections;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.collections.CollectionAcknowledgementPage;
import steps.BaseSteps;

/**
 * Created by karthik on 7/12/16.
 */
public class CollectionAcknowledgementSteps extends BaseSteps implements En {
    public CollectionAcknowledgementSteps() {
        And("^he stores the challan number and closes acknowledgement$", () -> {
            String challanNumber = (pageStore.get(CollectionAcknowledgementPage.class).getChallanNumber()) ;
            scenarioContext.setChallanNumber(challanNumber);

            pageStore.get(CollectionAcknowledgementPage.class).close();
        });
    }
}
