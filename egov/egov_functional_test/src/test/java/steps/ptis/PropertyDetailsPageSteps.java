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
//            pageStore.get(PropertyDetailsPage.class).enterApproverRemarks(remarks);
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
    }
}