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
            ChequeDetails chequeDetails = new ExcelReader(collectionsTestDataFileName).getChequeDetails(chequeDetailsDataName);
            pageStore.get(CollectionsPage.class).enterChequeDetails(chequeDetails);
        });
    }

}
