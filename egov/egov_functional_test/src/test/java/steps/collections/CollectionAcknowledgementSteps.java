package steps.collections;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.collections.CollectionAcknowledgementPage;
import pages.ptis.PropertyAcknowledgementPage;
import steps.BaseSteps;



/**
 * Created by karthik on 7/12/16.
 */
public class CollectionAcknowledgementSteps extends BaseSteps implements En {
    public CollectionAcknowledgementSteps() {
        And("^he stores the challan number and closes acknowledgement$", () -> {
            String challanNumber = (pageStore.get(CollectionAcknowledgementPage.class).getChallanNumber()) ;
            scenarioContext.setChallanNumber(challanNumber);
            pageStore.get(PropertyAcknowledgementPage.class).close();
        });

        And("^he submit all collections$", () -> {
            try {
                pageStore.get(CollectionAcknowledgementPage.class).submitAllCollections();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageStore.get(PropertyAcknowledgementPage.class).close();

        });
        And("^he closes the acknowledgement$", () -> {
            pageStore.get(PropertyAcknowledgementPage.class).close();
        });
        And("^he approves the receipt$", () -> {
            try {
                pageStore.get(CollectionAcknowledgementPage.class).approveCollection();
            } catch (Exception e) {
                e.printStackTrace();
            }

            pageStore.get(PropertyAcknowledgementPage.class).close();
        });
    }
}
