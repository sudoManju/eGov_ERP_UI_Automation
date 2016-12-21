package steps.wcms;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.ptis.*;
import entities.wcms.EnclosedDocument;
import entities.wcms.FieldInspectionDetails;
import pages.ptis.PropertyDetailsPage;
import pages.wcms.WaterChargeManagementPage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by vinaykumar on 9/12/16.
 */
public class WaterChargeManagementSteps extends BaseSteps implements En {

    public WaterChargeManagementSteps() {

        And("^user will enter the details of the new water connection$", () -> {

            String applicationParticularsDetails = "applicantInfo1";
            String connectionDetails = "connectionInfo";
            String enclosedDocumentDetails = "enclosedInfo";
            String approveDetails = "engineer";

            ApplicantInfo applicantInfo = new ExcelReader(ptisTestDataFileName).getApplicantInfo(applicationParticularsDetails);
            pageStore.get(WaterChargeManagementPage.class).enterWaterConectionAssessmentNumber(applicantInfo);

            ConnectionInfo connectionInfo = new ExcelReader(ptisTestDataFileName).getConnectionInfo(connectionDetails);
            pageStore.get(WaterChargeManagementPage.class).enterNewWaterConnectionInfo(connectionInfo);

            EnclosedDocument enclosedDocument = new ExcelReader(ptisTestDataFileName).getDocumentInfo(enclosedDocumentDetails);
            pageStore.get(WaterChargeManagementPage.class).enterDocumentInfo(enclosedDocument);

            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveDetails);
            pageStore.get(WaterChargeManagementPage.class).enterWaterApprovalDetails(approvalDetails);

        });

        And("^user will enter the consumer number as (\\w+)$", (String consumerNumber) -> {
            pageStore.get(PropertyDetailsPage.class).enterConsumerNumber(consumerNumber);
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
            String applicationNumber = pageStore.get(PropertyDetailsPage.class).findAdditionalApplicationNumber();
            scenarioContext.setApplicationNumber(applicationNumber);
        });

        And("^user will enter the field inspection details as (\\w+)$", (String inspectionInfo) -> {
            FieldInspectionDetails fieldInspectionDetails = new ExcelReader(ptisTestDataFileName).getFieldInspectionInfo(inspectionInfo);
            pageStore.get(WaterChargeManagementPage.class).enterFieldInspectionInfo(fieldInspectionDetails);
        });

        And("^user will click on the generate estimation notice$", () -> {
            pageStore.get(WaterChargeManagementPage.class).clickOnGenerateNotice();
        });

        And("^user will click on collect charges and collect the money form the customer & closes it$", () -> {
            pageStore.get(WaterChargeManagementPage.class).clickOnCollectCharges();
            pageStore.get(WaterChargeManagementPage.class).toReceiveAmount();
            pageStore.get(WaterChargeManagementPage.class).closeReceipt();
        });
        Then("^user will filter the application based upon the connection details as (\\w+)$", (String connectionType) -> {
            pageStore.get(WaterChargeManagementPage.class).searchWaterConnectionApplications(connectionType);
        });
        And("^user will enter the closure connection detials as (\\w+)$", (String closureType) -> {
            String approveDetails = "engineer";
            pageStore.get(WaterChargeManagementPage.class).enterDetailsOfClosureConnection(closureType);

            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveDetails);
            pageStore.get(WaterChargeManagementPage.class).enterWaterApprovalDetails(approvalDetails);
        });
        And("^chooses to act upon the application number as (\\w+)$", (String consumerNumber) -> {
            pageStore.get(WaterChargeManagementPage.class).selectApplication(consumerNumber);
            pageStore.get(WaterChargeManagementPage.class).forward();
        });
    }
}
