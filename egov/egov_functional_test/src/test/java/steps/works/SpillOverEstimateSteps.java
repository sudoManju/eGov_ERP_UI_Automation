package steps.works;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.works.*;

import org.junit.Assert;
import pages.BasePage;
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
           String reqMsg = pageStore.get(SpillOverEstimatePage.class).saveAndClose();
            scenarioContext.setActualMessage(reqMsg);
        });
        And("^he enters approver details as (\\w+)$", (String approverDetailsDataId) -> {
         ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

        });
        And("^he enters work details as for (\\w+)$", (String workDetailsDataId) -> {
            WorkDetails workDetails = new ExcelReader(lineEstimateTestDataFileName).getWorkDetails(workDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterWorkDetailsforestimate(workDetails);
        });
        And("^he forwards to DEE and closes the acknowledgement$", () -> {
            String number = pageStore.get(SpillOverEstimatePage.class).forwardToDEE();
            scenarioContext.setEstimateNumber(number);

            String Message = pageStore.get(SpillOverEstimatePage.class).successMessage(2);
            scenarioContext.setActualMessage(Message);

            pageStore.get(SpillOverEstimatePage.class).close();
        });
        And("^he clicks on estimate and opens the application$", () -> {
            pageStore.get(SpillOverEstimatePage.class).openApplication(scenarioContext.getEstimateNumber());
        });
        And("^he enters the AdminSanctionNumber$", () -> {
           pageStore.get(SpillOverEstimatePage.class).adminSanctionNumber();
        });
        And("^he submit the application to superIntendent$", () -> {
            pageStore.get(SpillOverEstimatePage.class).submit();

            String actualMsg = pageStore.get(SpillOverEstimatePage.class).successMessage(7);
            scenarioContext.setActualMessage(actualMsg);

            pageStore.get(SpillOverEstimatePage.class).close();
        });
        And("^he submit the application to commissioner$", () -> {
            pageStore.get(SpillOverEstimatePage.class).submit();

            String actualMsg = pageStore.get(SpillOverEstimatePage.class).successMessage(8);
            scenarioContext.setActualMessage(actualMsg);

            pageStore.get(SpillOverEstimatePage.class).close();
        });
        And("^he submit the application to assis_Engineer$", () -> {
            pageStore.get(SpillOverEstimatePage.class).submit();

            String actualMsg = pageStore.get(SpillOverEstimatePage.class).successMessage(14);
            scenarioContext.setActualMessage(actualMsg);

            pageStore.get(SpillOverEstimatePage.class).close();
        });
        And("^he enters the details for approve$", () -> {
            pageStore.get(SpillOverEstimatePage.class).detailsForApprove();
        });
        And("^he approves the application$", () -> {
            pageStore.get(SpillOverEstimatePage.class).approve();

            String actualMsg = pageStore.get(SpillOverEstimatePage.class).successMessage(2);
            scenarioContext.setActualMessage(actualMsg);

            pageStore.get(SpillOverEstimatePage.class).close();
        });

    }



}


