package steps.sewerageTax;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.works.ApproverDetails;
import pages.DashboardPage;
import pages.SewerageTax.newSewerageConnectionPage;
import pages.works.SpillOverEstimatePage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by karthik on 27/1/17.
 */
public class newSewerageConnectionSteps extends BaseSteps implements En {
    public newSewerageConnectionSteps(){
        And("^he chooses to create new sewage connection$", () -> {
           pageStore.get(DashboardPage.class).chooseForNewSewerageConnection();
        });
        And("^he create new sewerage connection for above assessment number$", () -> {
            pageStore.get(newSewerageConnectionPage.class).createNewConnection(scenarioContext.getAssessmentNumber());

            String approverDetailsDataId = "assis_Engineer_1";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

        });
        And("^he forward to engineer and closes the acknowledgement$", () -> {
            pageStore.get(newSewerageConnectionPage.class).forward();

            scenarioContext.setApplicationNumber(pageStore.get(newSewerageConnectionPage.class).getApplicatioNumber());

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessage());

            pageStore.get(newSewerageConnectionPage.class).close();

        });
        And("^he chooses to collect sewerage tax for above application number$", () -> {
            pageStore.get(DashboardPage.class).chooseToCollectSewerageTax();
        });
        And("^he search for above application number to collect$", () -> {
            pageStore.get(newSewerageConnectionPage.class).searchForApplicationNumberToCollect(scenarioContext.getApplicationNumber());
        });
        And("^he collect the charges and closes the acknowledgement$", () -> {
           pageStore.get(newSewerageConnectionPage.class).collectCharges();

           pageStore.get(newSewerageConnectionPage.class).closeMultipleWindows("http://kurnool-uat.egovernments.org/stms/collectfee/search");
        });
    }
}
