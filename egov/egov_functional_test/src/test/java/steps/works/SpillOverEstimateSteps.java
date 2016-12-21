package steps.works;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.works.*;

import pages.works.SpillOverEstimatePage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by karthik on 16/12/16.
 */
public class SpillOverEstimateSteps extends BaseSteps implements En {

    public SpillOverEstimateSteps() {

        And("^he enters estimate header details as (\\w+)$", (String estimateHeaderDetailsDataId) -> {

           EstimateHeaderDetails estimateHeaderDetails = new ExcelReader(lineEstimateTestDataFileName).getEstimateHeaderDetails(estimateHeaderDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterEstimateHeaderDetails(estimateHeaderDetails);
        });
        And("^he enters financial details as (\\w+)$", (String financialDetailsDataId) -> {
           FinancialDetails financialDetails = new ExcelReader(lineEstimateTestDataFileName).getFinancialDetails(financialDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterFinancialDetails(financialDetails);
        });
        And("^he enters work details as (\\w+)$", (String workDetailsDataId) -> {
            WorkDetails workDetails = new ExcelReader(lineEstimateTestDataFileName).getWorkDetails(workDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterWorkDetails(workDetails);
        });
        And("^he enters administration sanction details as (\\w+)$", (String adminSanctionDetailsDataId) -> {
            AdminSanctionDetails adminSanctionDetails = new ExcelReader(lineEstimateTestDataFileName).getAdminSanctionDetails(adminSanctionDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterAdminSanctionDetails(adminSanctionDetails);
        });
        And("^he enters technical sanction details as (\\w+)$", (String technicalSanctionDetailsDataId) -> {
           TechnicalSanctionDetails technicalSanctionDetails = new ExcelReader(lineEstimateTestDataFileName).getTechnicalSanctionDetails(technicalSanctionDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterTechnicalSanctionDetails(technicalSanctionDetails);
        });
        And("^he saves the file and closes the acknowledgement$", () -> {
           pageStore.get(SpillOverEstimatePage.class).saveAndClose();
        });

    }



}


