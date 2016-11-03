package steps.ptis;

import cucumber.api.java8.En;
import entities.ptis.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pages.ptis.PropertyDetailsPage;
import steps.BaseSteps;
import utils.ExcelReader;

import java.io.IOException;

public class PropertyDetailsPageSteps extends BaseSteps implements En {
    public PropertyDetailsPageSteps() {
        And("^he enters property header details as (\\w+)$", (String propertyDetailsDataId) -> {

            PropertyHeaderDetails propertyHeaderDetails = null;
            try {
                propertyHeaderDetails = new ExcelReader(ptisTestDataFileName).getPropertyHeaderDetails(propertyDetailsDataId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            pageStore.get(PropertyDetailsPage.class).enterPropertyHeader(propertyHeaderDetails);
        });

        And("^he enters owner details for the first owner as (\\w+)$", (String ownerDetailsDataId) -> {
            OwnerDetails ownerDetails = null;

            try {
                ownerDetails = new ExcelReader(ptisTestDataFileName).getOwnerDetails(ownerDetailsDataId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }

            pageStore.get(PropertyDetailsPage.class).enterOwnerDetails(ownerDetails);
        });
        And("^he enters property address details as (\\w+)$", (String addressDetailsDataId) -> {
            PropertyAddressDetails addressDetails = null;

            try {
                addressDetails = new ExcelReader(ptisTestDataFileName).getPropertyAddressDetails(addressDetailsDataId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }

            pageStore.get(PropertyDetailsPage.class).enterPropertyAddressDetails(addressDetails);
        });
        And("^he enters assessment details as (\\w+)$", (String assessmentDetailsDataId) -> {
            // Write code here that turns the phrase above into concrete actions
            AssessmentDetails assessmentDetails = null;
            try {
                assessmentDetails = new ExcelReader(ptisTestDataFileName).getAssessmentDetails(assessmentDetailsDataId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }

            pageStore.get(PropertyDetailsPage.class).enterAssessmentDetails(assessmentDetails);
        });
        And("^he enters amenities as (\\w+)$", (String amenitiesDataId) -> {
            Amenities amenities = null;

            try {
                amenities = new ExcelReader(ptisTestDataFileName).getAmenties(amenitiesDataId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            pageStore.get(PropertyDetailsPage.class).selectAmenities(amenities);
        });
        And("^he enters construction type details as (\\w+)$", (String constructionTypeDetailsDataId) -> {
            ConstructionTypeDetails constructionTypeDetails = null;
            try {
                constructionTypeDetails = new ExcelReader(ptisTestDataFileName).getConstructionTypeDetails(constructionTypeDetailsDataId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            pageStore.get(PropertyDetailsPage.class).enterConstructionTypeDetails(constructionTypeDetails);
        });
        And("^he enters floor details as (\\w+)$", (String floorDetailsDataId) -> {
            FloorDetails floorDetails = null;

            try {
                floorDetails = new ExcelReader(ptisTestDataFileName).getFloorDetails(floorDetailsDataId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }

            pageStore.get(PropertyDetailsPage.class).enterFloorDetails(floorDetails);
        });
        And("^he enters approval details as (\\w+)$", (String approvalDetailsDataId) -> {
            ApprovalDetails approvalDetails = null;
            try {
                approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approvalDetailsDataId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            pageStore.get(PropertyDetailsPage.class).enterApprovalDetails(approvalDetails);
        });
        And("^he forwards the details$", () -> {
            pageStore.get(PropertyDetailsPage.class).forward();
        });
        And("^he forwards for approval to (.*)$", (String approvalDetailsDataId) -> {
            ApprovalDetails approvalDetails = null;
            try {
                approvalDetails = new ExcelReader(ptisTestDataFileName).getApprovalDetails(approvalDetailsDataId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            pageStore.get(PropertyDetailsPage.class).enterApprovalDetails(approvalDetails);
            pageStore.get(PropertyDetailsPage.class).forward();
        });
        And("^he approved the property with remarks \"([^\"]*)\"$", (String remarks) -> {
            // Write code here that turns the phrase above into concrete actions
            pageStore.get(PropertyDetailsPage.class).enterApproverRemarks(remarks);
            pageStore.get(PropertyDetailsPage.class).approve();
        });
        And("^he does a digital signature$", () -> {
            pageStore.get(PropertyDetailsPage.class).digitallySign();
        });
        And("^he generates a notice$", () -> {
          pageStore.get(PropertyDetailsPage.class).generateNotice();
        });

    }
}
