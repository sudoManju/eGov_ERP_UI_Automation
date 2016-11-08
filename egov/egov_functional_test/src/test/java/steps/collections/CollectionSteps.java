package steps.collections;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.collections.ChequeDetails;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pages.collections.CollectionsPage;
import steps.BaseSteps;
import utils.ExcelReader;

import java.io.IOException;

public class CollectionSteps extends BaseSteps implements En {
    public CollectionSteps() {
        And("^he pays using cheque with details as (\\w+)$", (String chequeDetailsDataName) -> {
            ChequeDetails chequeDetails = null;
            try {
                chequeDetails = new ExcelReader(collectionsTestDataFileName).getChequeDetails(chequeDetailsDataName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }

            pageStore.get(CollectionsPage.class).enterChequeDetails(chequeDetails);
        });
    }

}
