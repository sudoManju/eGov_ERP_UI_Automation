package steps.wcms;

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

        And("^user will enter the field inspection details as (\\w+)$", (String inspectionInfo) -> {
            FieldInspectionDetails fieldInspectionDetails = new ExcelReader(ptisTestDataFileName).getFieldInspectionInfo(inspectionInfo);
            pageStore.get(WaterChargeManagementPage.class).enterFieldInspectionInfo(fieldInspectionDetails);
        });

    }
}
