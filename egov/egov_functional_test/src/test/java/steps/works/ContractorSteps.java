package steps.works;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.works.ContractorPage;
import steps.BaseSteps;

/**
 * Created by manjunatha-lap on 16/12/2016.
 */
public class ContractorSteps extends BaseSteps implements En {
    public ContractorSteps() {
        And("^he chooses to create contractor$", () -> {
            pageStore.get(ContractorPage.class).chooseToCreateContractor();
        });
        And("^he enters the contractor master data$", () -> {
           String name =  pageStore.get(ContractorPage.class).entersContractorMasterData();
            scenarioContext.setApplicationNumber(name);
            String msg = pageStore.get(ContractorPage.class).successMessage();
            scenarioContext.setActualMessage(msg);
        });
        And("^he chooses for view or modify contractor$", () -> {
            pageStore.get(ContractorPage.class).viewContractor();
        });
        And("^he search for contractor$", () -> {
         pageStore.get(ContractorPage.class).searchContractor(scenarioContext.getApplicationNumber());
        });
        And("^he close the acknowledgement$", () -> {
           pageStore.get(ContractorPage.class).close();
        });
        And("^he select the required contractor$", () -> {
            pageStore.get(ContractorPage.class).select();
        });
        And("^modifies the required contractor$", () -> {
            pageStore.get(ContractorPage.class).modify();
            String msg = pageStore.get(ContractorPage.class).successMessage();
            scenarioContext.setActualMessage(msg);
        });

    }
}
