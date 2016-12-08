package steps.ptis;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.ptis.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pages.ptis.PropertyAcknowledgementPage;
import pages.ptis.PropertyDetailsPage;
import steps.BaseSteps;
import utils.ExcelReader;

import java.io.IOException;

public class PropertyDetailsPageSteps extends BaseSteps implements En {
    public PropertyDetailsPageSteps() {
        And("^he enters property header details as (\\w+)$", (String propertyDetailsDataId) -> {
            PropertyHeaderDetails propertyHeaderDetails = new ExcelReader(ptisTestDataFileName).getPropertyHeaderDetails(propertyDetailsDataId);
            pageStore.get(PropertyDetailsPage.class).enterPropertyHeader(propertyHeaderDetails);
        });

        And("^he enters owner details for the first owner as (\\w+)$", (String ownerDetailsDataId) -> {
            OwnerDetails ownerDetails = new ExcelReader(ptisTestDataFileName).getOwnerDetails(ownerDetailsDataId);
            pageStore.get(PropertyDetailsPage.class).enterOwnerDetails(ownerDetails);
        });

        And("^he enters property address details as (\\w+)$", (String addressDetailsDataId) -> {
            PropertyAddressDetails addressDetails = new ExcelReader(ptisTestDataFileName).getPropertyAddressDetails(addressDetailsDataId);
            pageStore.get(PropertyDetailsPage.class).enterPropertyAddressDetails(addressDetails);
        });

        And("^he enters assessment details as (\\w+)$", (String assessmentDetailsDataId) -> {
            AssessmentDetails assessmentDetails = new ExcelReader(ptisTestDataFileName).getAssessmentDetails(assessmentDetailsDataId);
            pageStore.get(PropertyDetailsPage.class).enterAssessmentDetails(assessmentDetails);
        });

        And("^he enters amenities as (\\w+)$", (String amenitiesDataId) -> {
            Amenities amenities = new ExcelReader(ptisTestDataFileName).getAmenties(amenitiesDataId);
            pageStore.get(PropertyDetailsPage.class).selectAmenities(amenities);
        });

        And("^he enters construction type details as (\\w+)$", (String constructionTypeDetailsDataId) -> {
            ConstructionTypeDetails constructionTypeDetails = new ExcelReader(ptisTestDataFileName).getConstructionTypeDetails(constructionTypeDetailsDataId);
            pageStore.get(PropertyDetailsPage.class).enterConstructionTypeDetails(constructionTypeDetails);
        });

        And("^he enters floor details as (\\w+)$", (String floorDetailsDataId) -> {
            FloorDetails floorDetails = new ExcelReader(ptisTestDataFileName).getFloorDetails(floorDetailsDataId);
            pageStore.get(PropertyDetailsPage.class).enterFloorDetails(floorDetails);
        });

        And("^he enters approval details as (\\w+)$", (String approvalDetailsDataId) -> {
            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approvalDetailsDataId);
            pageStore.get(PropertyDetailsPage.class).enterApprovalDetails(approvalDetails);
        });

        And("^he forwards for approval to (.*)$", (String approvalDetailsDataId) -> {
            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approvalDetailsDataId);
            pageStore.get(PropertyDetailsPage.class).enterApprovalDetails(approvalDetails);
            pageStore.get(PropertyDetailsPage.class).forward();
        });

        And("^he approved the property with remarks \"([^\"]*)\"$", (String remarks) -> {
            pageStore.get(PropertyDetailsPage.class).enterApproverRemarks(remarks);
            pageStore.get(PropertyDetailsPage.class).approve();
        });
        And("^he does a digital signature$", () -> {
            pageStore.get(PropertyDetailsPage.class).digitallySign();
        });
        And("^he generates a notice$", () -> {
            pageStore.get(PropertyDetailsPage.class).generateNotice();

        });

        And("^the property tax bill be created$", () -> {
            pageStore.get(PropertyDetailsPage.class).create();
        });
        And("^he enter all the credential details$", () -> {

            And("^he search property with assessment number$", () -> {
                String searchId = "searchWithAssessmentNumber";

                SearchDetails searchDetails = new ExcelReader(ptisTestDataFileName).getSearchDetails(searchId);
                pageStore.get(PropertyDetailsPage.class).enterSearchDetailsOfAssessmentNumber(searchDetails);
            });
            And("^he check total number of records found$", () -> {
                pageStore.get(PropertyDetailsPage.class).checkNoOfRecords();
            });
            And("^he search property with door number$", () -> {
                String searchId = "searchWithDoorNumber";

                SearchDetails searchDetails = new ExcelReader(ptisTestDataFileName).getSearchDetails(searchId);
                pageStore.get(PropertyDetailsPage.class).enterSearchDetailsOfDoorNumber(searchDetails);

            });
            And("^he search property with mobile number$", () -> {
                String searchId = "searchWithMobileNumber";

                SearchDetails searchDetails = new ExcelReader(ptisTestDataFileName).getSearchDetails(searchId);
                pageStore.get(PropertyDetailsPage.class).enterSearchDetailsOfMobileNumber(searchDetails);
            });
            And("^he search property with zone and ward number$", () -> {
                String searchId = "searchWithZoneAndWardNumber";

                SearchDetails searchDetails = new ExcelReader(ptisTestDataFileName).getSearchDetails(searchId);
                pageStore.get(PropertyDetailsPage.class).enterSearchDetailsOfZoneAndWardNumber(searchDetails);
            });

            And("^finally user will submit the application$", () -> {
                pageStore.get(PropertyDetailsPage.class).chooseToSubmit();
            });
        });

        And("^user need to enter the date and get the report details$", () -> {
            String vltReportInfo = "report1";

            VLTReport vltReport = new ExcelReader(ptisTestDataFileName).getVLTReportInfo(vltReportInfo);
            pageStore.get(PropertyDetailsPage.class).enterVLTReportDetails(vltReport);
        });

        And("^user will enter the details of the new water connection$", () -> {

            String applicationParticularsDetails = "applicantInfo1" ;
            String connectionDetails = "connectionInfo";
            String enclosedDocumentDetails = "enclosedInfo";
            String approveDetails = "engineer";

            ApplicantInfo applicantInfo = new ExcelReader(ptisTestDataFileName).getApplicantInfo(applicationParticularsDetails);
            pageStore.get(PropertyDetailsPage.class).enterWaterConectionInfo(applicantInfo);

            ConnectionInfo connectionInfo = new ExcelReader(ptisTestDataFileName).getConnectionInfo(connectionDetails);
            pageStore.get(PropertyDetailsPage.class).enterNewWaterConnectionInfo(connectionInfo);

            EnclosedDocument enclosedDocument = new ExcelReader(ptisTestDataFileName).getDocumentInfo(enclosedDocumentDetails);
            pageStore.get(PropertyDetailsPage.class).enterDocumentInfo(enclosedDocument);

            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveDetails);
            pageStore.get(PropertyDetailsPage.class).enterWaterApprovalDetails(approvalDetails);

        });

        And("^user will enter the consumer number as (\\w+)$", (String consumerNumber) -> {
            pageStore.get(PropertyDetailsPage.class).enterConsumerNumber(consumerNumber);
        });

        And("^user will enter the details of the new additional water connection$", () -> {
            String connectionDetails = "connectionInfo1";
            String approveDetails = "engineer";

            ConnectionInfo connectionInfo = new ExcelReader(ptisTestDataFileName).getConnectionInfo(connectionDetails);
            pageStore.get(PropertyDetailsPage.class).enterAdditionalWaterConnectionInfo(connectionInfo);

            ApprovalDetails approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approveDetails);
            pageStore.get(PropertyDetailsPage.class).enterWaterApprovalDetails(approvalDetails);

        });
        Then("^user will get the application number and closes the form$", () -> {
//            String applicationNumber = pageStore.get(PropertyDetailsPage.class).findAdditionalApplicationNumber();
            String applicationNumber = pageStore.get(PropertyDetailsPage.class).findAdditionalApplicationNumber();
            scenarioContext.setApplicationNumber(applicationNumber);
        });
        And("^user will enter the field inspection details as (\\w+)$", (String inspectionInfo) -> {

            FieldInspectionDetails fieldInspectionDetails = new ExcelReader(ptisTestDataFileName).getFieldInspectionInfo(inspectionInfo);
            pageStore.get(PropertyDetailsPage.class).enterFieldInspectionInfo(fieldInspectionDetails);


        });

    }
}
