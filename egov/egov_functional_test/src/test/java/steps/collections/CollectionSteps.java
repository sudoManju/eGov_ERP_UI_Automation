package steps.collections;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.collections.ChallanHeaderDetails;
import entities.collections.ChequeDetails;
import entities.ptis.ApprovalDetails;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pages.DashboardPage;
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

        And("^he enters challan details$", () -> {
            String challanheaderid = "challanHeader";
            String approverId = "seniorAssistant";

            ChallanHeaderDetails challanHeaderDetails = new ExcelReader(collectionsTestDataFileName).getChallanHeader(challanheaderid);
            pageStore.get(CollectionsPage.class).enterChallanHeader(challanHeaderDetails);

            ApprovalDetails approverDetails = new ExcelReader(collectionsTestDataFileName).getApprovalDetails(approverId);
            pageStore.get(CollectionsPage.class).enterApprovalDetails(approverDetails);
        });
    }

}
