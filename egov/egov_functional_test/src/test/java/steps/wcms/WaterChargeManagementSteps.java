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

            String applicationParticularsDetails = scenarioContext.getAssessmentNumber();
            String connectionDetails = "connectionInfo";
            String enclosedDocumentDetails = "enclosedInfo";

//            ApplicantInfo applicantInfo = new ExcelReader(ptisTestDataFileName).getApplicantInfo(applicationParticularsDetails);
            pageStore.get(WaterChargeManagementPage.class).enterWaterConectionAssessmentNumber(applicationParticularsDetails);

            ConnectionInfo connectionInfo = new ExcelReader(ptisTestDataFileName).getConnectionInfo(connectionDetails);
            pageStore.get(WaterChargeManagementPage.class).enterNewWaterConnectionInfo(connectionInfo);

            EnclosedDocument enclosedDocument = new ExcelReader(ptisTestDataFileName).getDocumentInfo(enclosedDocumentDetails);
            pageStore.get(WaterChargeManagementPage.class).enterDocumentInfo(enclosedDocument);
        });

        And("^user enter the water management approval details as (\\w+)$", (String approvalOfficer) -> {
            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approvalOfficer);
            pageStore.get(WaterChargeManagementPage.class).enterWaterApprovalDetails(approvalDetails);
        });

        And("^user will enter the consumer number as (\\w+)$", (String consumerNumber) -> {
            scenarioContext.setApplicationNumber(consumerNumber);
            pageStore.get(PropertyDetailsPage.class).enterConsumerNumber(scenarioContext.getApplicationNumber());
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

        And("^user closes the search application page$", () -> {
            pageStore.get(WaterChargeManagementPage.class).closeSearchApplicationPage();
        });

        And("^user will approve the application with sanction number", () -> {
            pageStore.get(WaterChargeManagementPage.class).commissionerApprove();
        });

        And("^user will provide the digital signature$", () -> {
            pageStore.get(WaterChargeManagementPage.class).commissionerSignature();
        });

        And("^the user will generate the work order$", () -> {
            pageStore.get(WaterChargeManagementPage.class).generateWorkOrder();
        });

        And("^user will perform the execution of tap$", () -> {
            pageStore.get(WaterChargeManagementPage.class).executeTap();
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

        And("^user will closes the acknowledgement form$", () -> {
            pageStore.get(WaterChargeManagementPage.class).closePage();
        });
    }
}
