package steps.wcms;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.ptis.*;
import entities.wcms.EnclosedDocument;
import entities.wcms.FieldInspectionDetails;
import pages.DashboardPage;
import pages.ptis.PropertyDetailsPage;
import pages.wcms.WaterChargeManagementPage;
import steps.BaseSteps;
import utils.ExcelReader;

import java.io.IOException;

/**
 * Created by vinaykumar on 9/12/16.
 */
public class WaterChargeManagementSteps extends BaseSteps implements En {

    public WaterChargeManagementSteps() {

        And("^user will enter the details of the new water connection$", () -> {

            String connectionDetails = "connectionInfo";
            String enclosedDocumentDetails = "enclosedInfo";

            pageStore.get(WaterChargeManagementPage.class).enterWaterConectionAssessmentNumber(scenarioContext.getAssessmentNumber());

            ConnectionInfo connectionInfo = new ExcelReader(ptisTestDataFileName).getConnectionInfo(connectionDetails);
            pageStore.get(WaterChargeManagementPage.class).enterNewWaterConnectionInfo(connectionInfo);

            EnclosedDocument enclosedDocument = new ExcelReader(ptisTestDataFileName).getDocumentInfo(enclosedDocumentDetails);
            pageStore.get(WaterChargeManagementPage.class).enterDocumentInfo(enclosedDocument);
        });

        And("^user enter the water management approval details as (\\w+)$", (String approvalOfficer) -> {
            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approvalOfficer);
            pageStore.get(WaterChargeManagementPage.class).enterWaterApprovalDetails(approvalDetails);
        });

        And("^user will enter the consumer number$", () -> {

            pageStore.get(WaterChargeManagementPage.class).enterConsumerNumber(scenarioContext.getConsumerNumber());
            scenarioContext.setApplicationNumber(scenarioContext.getConsumerNumber());
        });

        And("^user will enter the details of the new additional water connection$", () -> {
            String connectionDetails = "connectionInfo1";
            String approveDetails = "engineer";

            ConnectionInfo connectionInfo = new ExcelReader(ptisTestDataFileName).getConnectionInfo(connectionDetails);
            pageStore.get(WaterChargeManagementPage.class).enterAdditionalWaterConnectionInfo(connectionInfo);

            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveDetails);
            pageStore.get(WaterChargeManagementPage.class).enterWaterApprovalDetails(approvalDetails);

        });

        Then("^user will get the application number and closes the form$", () -> {
            String applicationNumber = pageStore.get(WaterChargeManagementPage.class).findAdditionalApplicationNumber();
            scenarioContext.setApplicationNumber(applicationNumber);
        });

        And("^user will enter the closure connection details as (\\w+)$", (String closureType) -> {
            String approveDetails = "engineer";
            pageStore.get(WaterChargeManagementPage.class).enterDetailsOfClosureConnection(closureType);

            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveDetails);
            pageStore.get(WaterChargeManagementPage.class).enterWaterApprovalDetails(approvalDetails);
        });

        And("^chooses to act upon the above closure application$", () -> {
            pageStore.get(WaterChargeManagementPage.class).selectApplication(scenarioContext.getApplicationNumber());
            pageStore.get(WaterChargeManagementPage.class).forward();
        });

        And("^user will approve the closure application$", () -> {
            pageStore.get(WaterChargeManagementPage.class).commissionerClosureApprove();
        });

        And("^user will click on the generate acknowledge ment$", () -> {
            pageStore.get(WaterChargeManagementPage.class).toGenerateAcknowledgement();
        });

        And("^user will enter the details of the change of use water connection$", () -> {
            String connectionDetails = "changeOfUse";
            String approveDetails = "engineer";

            ConnectionInfo connectionInfo = new ExcelReader(ptisTestDataFileName).getConnectionInfo(connectionDetails);
            pageStore.get(WaterChargeManagementPage.class).changeOfUseWaterConnectionInfo(connectionInfo);

            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveDetails);
            pageStore.get(WaterChargeManagementPage.class).enterWaterApprovalDetails(approvalDetails);

        });

        And("^user will enter the details of data entry screen for water charges$", () -> {
            String applicationParticularsDetails = "dataEntryInfo";
            String connectionDetails = "dataEntryInfo";

            ApplicantInfo applicantInfo = new ExcelReader(ptisTestDataFileName).getApplicantInfo(applicationParticularsDetails);
            pageStore.get(WaterChargeManagementPage.class).enterWaterDataEntryDetails(applicantInfo);

            ConnectionInfo connectionInfo = new ExcelReader(ptisTestDataFileName).getConnectionInfo(connectionDetails);
            pageStore.get(WaterChargeManagementPage.class).enterNewWaterConnectionInfo(connectionInfo);

            pageStore.get(WaterChargeManagementPage.class).estimationFeeDetails();

        });

        And("^user will notify the successfull creation of data entry screen as \"([^\"]*)\"$", (String arg0) -> {
            String message = pageStore.get(WaterChargeManagementPage.class).closesTheDataEntryPage();
            scenarioContext.setActualMessage(message);

        });

        And("^user will enter the closure consumer number$", () -> {
            String applicationParticularsDetails = "additionalConnection";

            ApplicantInfo applicantInfo = new ExcelReader(ptisTestDataFileName).getApplicantInfo(applicationParticularsDetails);
            pageStore.get(WaterChargeManagementPage.class).enterConsumerNumber(applicantInfo.getPtAssessmentNumber());
        });

        And("^user will choose the above application and enter the field inspection details as (\\w+) and closes the acknowledgement form$", (String inspectionDetails) -> {

            // Choose To Act on above application
                pageStore.get(DashboardPage.class).openApplication(scenarioContext.getApplicationNumber());

            // User will enter the field inspection details
                FieldInspectionDetails fieldInspectionDetails = new ExcelReader(ptisTestDataFileName).getFieldInspectionInfo(inspectionDetails);
                pageStore.get(WaterChargeManagementPage.class).enterFieldInspectionInfo(fieldInspectionDetails);

            // User will closes the acknowledgement form
                pageStore.get(WaterChargeManagementPage.class).closePage();
        });

        And("^user will choose the above application and click on the generate estimation notice$", () -> {

            // Choose To Act on above application
                pageStore.get(DashboardPage.class).openApplication(scenarioContext.getApplicationNumber());

            // User will click on the generate estimation notice
                pageStore.get(WaterChargeManagementPage.class).clickOnGenerateNotice();
        });

        And("^user will search the recent application based on connection details as (\\w+) and collects money$", (String connectionDetails) -> {

            // User will search for the recent application
                pageStore.get(DashboardPage.class).chooseForSearchApplication();

            // User will filter the application based upon the connection details
                pageStore.get(WaterChargeManagementPage.class).searchWaterConnectionApplications(connectionDetails);

            // User chooses to act upon the above application in search applications
                pageStore.get(DashboardPage.class).openSearchApplication(scenarioContext.getApplicationNumber());

            // User will click on collect charges and collect the money form the customer & closes it
                String consumerNumber = pageStore.get(WaterChargeManagementPage.class).clickOnCollectCharges();
                pageStore.get(WaterChargeManagementPage.class).toReceiveAmount();
                pageStore.get(WaterChargeManagementPage.class).closeReceipt();
                scenarioContext.setConsumerNumber(consumerNumber);

            // User closes the search application page
                pageStore.get(WaterChargeManagementPage.class).closeSearchApplicationPage();
        });

        And("^user will choose the above application and enter the approval details as (\\w+)$", (String approvalOfficer) -> {

            // Choose To Act on above application
                pageStore.get(DashboardPage.class).openApplication(scenarioContext.getApplicationNumber());

            // User enter the water management approval details
                ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approvalOfficer);
                pageStore.get(WaterChargeManagementPage.class).enterWaterApprovalDetails(approvalDetails);

            // User will closes the acknowledgement form
                pageStore.get(WaterChargeManagementPage.class).closePage();
        });

        And("^user will choose the above application to approve and provides the digital signature$", () -> {

            // Choose To Act on above application
                pageStore.get(DashboardPage.class).openApplication(scenarioContext.getApplicationNumber());

            // User will approve the application with sanction number
                pageStore.get(WaterChargeManagementPage.class).commissionerApprove();

            // Choose To Act on above application
                pageStore.get(DashboardPage.class).openApplication(scenarioContext.getApplicationNumber());

            // User will provide the digital signature
                pageStore.get(WaterChargeManagementPage.class).commissionerSignature();
        });

        And("^user will choose the above application and click on generate the work order$", () -> {

            // Choose To Act on above application
                pageStore.get(DashboardPage.class).openApplication(scenarioContext.getApplicationNumber());

            // User will generate the work order
                pageStore.get(WaterChargeManagementPage.class).generateWorkOrder();
        });

        And("^user will choose the above application and click on to perform the execution of tap$", () -> {

             // Choose To Act on above application
                pageStore.get(DashboardPage.class).openApplication(scenarioContext.getApplicationNumber());

            // User will perform the execution of tap
                pageStore.get(WaterChargeManagementPage.class).executeTap();

        });
    }
}
