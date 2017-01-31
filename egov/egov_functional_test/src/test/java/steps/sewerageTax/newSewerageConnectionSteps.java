package steps.sewerageTax;

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
        And("^he forward to assistant engineer and closes the acknowledgement$", () -> {
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
        And("^he chooses to act upon above sewerage connection$", () -> {
           pageStore.get(newSewerageConnectionPage.class).SelectSewerageTax();

           pageStore.get(newSewerageConnectionPage.class).selectAboveApplication(scenarioContext.getApplicationNumber());
        });
        And("^he approve the above sewerage application$", () -> {
           pageStore.get(newSewerageConnectionPage.class).approveTheApplication();
        });
        And("^he forward to DEE and close the acknowledgement$", () -> {
            String approverDetailsDataId = "DeputyExecutiveEngineer_1";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

            pageStore.get(newSewerageConnectionPage.class).forward();

            scenarioContext.setApplicationNumber(pageStore.get(newSewerageConnectionPage.class).getApplicatioNumber());

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessage());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he closes the sewerage acknowledgement$", () -> {
            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessage());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he generates estimation notice for above sewerage application$", () -> {
            pageStore.get(newSewerageConnectionPage.class).generateEstimationNotice();
        });
        And("^he forward to executive engineer and closes the acknowledgement$", () -> {
            String approverDetailsDataId = "Executive_engineer";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

            pageStore.get(newSewerageConnectionPage.class).forward();

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessage());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he chooses to act upon above sewerage application$", () -> {
            pageStore.get(newSewerageConnectionPage.class).searchForApplicationInbox(scenarioContext.getApplicationNumber());

            pageStore.get(newSewerageConnectionPage.class).selectAboveApplication(scenarioContext.getApplicationNumber());
        });
        And("^he generate workOrder for above sewerage connection$", () -> {
           pageStore.get(newSewerageConnectionPage.class).generateWorkOrder(scenarioContext.getApplicationNumber());

            String approverDetailsDataId = "assis_Engineer_1";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);
        });
        And("^he execute connection and closes the acknowledgement$", () -> {
            pageStore.get(newSewerageConnectionPage.class).executeConnection();

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessage1());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he chooses to search for above sewerage connection$", () -> {
           pageStore.get(DashboardPage.class).chooseForSearchSewerageConnection();
        });
        And("^he search for above sewerage connection$", () -> {
            pageStore.get(newSewerageConnectionPage.class).searchForAboveSewerageConnection(scenarioContext.getApplicationNumber());
        });
        And("^he increses the number of closets$", () -> {
            pageStore.get(newSewerageConnectionPage.class).increseTheNumberOfClosets();

            String approverDetailsDataId = "assis_Engineer_1";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

        });
        And("^he forward to assistant engineer for change in closets and closes the acknowledgement$", () -> {
            pageStore.get(newSewerageConnectionPage.class).forward();

            scenarioContext.setApplicationNumber(pageStore.get(newSewerageConnectionPage.class).getApplicatioNumberForChangeSewerage());

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessageForChangeSewerage());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he chooses to act upon above sewerage connection for change$", () -> {
            pageStore.get(newSewerageConnectionPage.class).selectChangeInClosets();

            pageStore.get(newSewerageConnectionPage.class).selectAboveApplication(scenarioContext.getApplicationNumber());
        });
        And("^he forward to DEE for change and close the acknowledgement$", () -> {
            String approverDetailsDataId = "DeputyExecutiveEngineer_1";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

            pageStore.get(newSewerageConnectionPage.class).forward();

            scenarioContext.setApplicationNumber(pageStore.get(newSewerageConnectionPage.class).getApplicatioNumberForChangeSewerage());

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessageForChangeSewerage());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he closes the sewerageChange acknowledgement$", () -> {
            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessageForChangeSewerage());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he forward to executive engineer for change in closets and closes the acknowledgement$", () -> {
            String approverDetailsDataId = "Executive_engineer";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

            pageStore.get(newSewerageConnectionPage.class).forward();

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessageForChangeSewerage());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he execute connection for change and closes the acknowledgement$", () -> {
            pageStore.get(newSewerageConnectionPage.class).executeConnection();

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessage1ForChangeSewerage());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he search for above sewerage application for closure$", () -> {
           pageStore.get(newSewerageConnectionPage.class).searchForSewerageConnectionForClosure(scenarioContext.getApplicationNumber());
        });
        And("^he put remarks and forward the application$", () -> {
            pageStore.get(newSewerageConnectionPage.class).remarks();

            String approverDetailsDataId = "assis_Engineer_1";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

        });
        And("^he chooses to act upon above sewerage connection for closure$", () -> {
           pageStore.get(newSewerageConnectionPage.class).selectClosureConnection();

           pageStore.get(newSewerageConnectionPage.class).selectAboveApplication(scenarioContext.getApplicationNumber());

        });
        And("^he forwards to DEE for closure and close the acknowledgement$", () -> {
            String approverDetailsDataId = "DeputyExecutiveEngineer_1";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

            pageStore.get(newSewerageConnectionPage.class).forward();

            scenarioContext.setApplicationNumber(pageStore.get(newSewerageConnectionPage.class).getApplicatioNumberForClosure());

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessageForClosure());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he forwards for closure and closes the acknowledgement$", () -> {
            pageStore.get(newSewerageConnectionPage.class).forward();

            scenarioContext.setApplicationNumber(pageStore.get(newSewerageConnectionPage.class).getApplicatioNumberForClosure());

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessageForClosure());

            pageStore.get(newSewerageConnectionPage.class).close();
        });
        And("^he forwards to executive engineer for closure and close the acknowledgement$", () -> {
            String approverDetailsDataId = "Executive_engineer";

            ApproverDetails approverDetails = new ExcelReader(lineEstimateTestDataFileName).getApprovalDetailsForEstimate(approverDetailsDataId);

            pageStore.get(SpillOverEstimatePage.class).enterApproverDetails(approverDetails);

            pageStore.get(newSewerageConnectionPage.class).forward();

            scenarioContext.setApplicationNumber(pageStore.get(newSewerageConnectionPage.class).getApplicatioNumberForClosure());

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessageForClosure());

            pageStore.get(newSewerageConnectionPage.class).close();


        });
        And("^he closes the seweargeClosure acknowledgement$", () -> {
            scenarioContext.setApplicationNumber(pageStore.get(newSewerageConnectionPage.class).getApplicatioNumberForClosure());

            scenarioContext.setActualMessage(pageStore.get(newSewerageConnectionPage.class).getSuccessMessageForClosure());

            pageStore.get(newSewerageConnectionPage.class).close();

        });
        And("^he generates closure notice$", () -> {
            pageStore.get(newSewerageConnectionPage.class).generateClosureNotice();
        });
    }
}
