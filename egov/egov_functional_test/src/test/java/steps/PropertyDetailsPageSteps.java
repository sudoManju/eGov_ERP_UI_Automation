package steps;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pages.PropertyAcknowledgementPage;
import pages.PropertyDetailsPage;
import utils.ExcelReader;

import java.io.IOException;

public class PropertyDetailsPageSteps extends BaseSteps implements En {
    public PropertyDetailsPageSteps() {
        And("^he enters property header details as (\\w+)$", (String propertyDetailsDataId) -> {

            PropertyHeaderDetails propertyHeaderDetails = null;
            try {
                propertyHeaderDetails = new ExcelReader().getPropertyHeaderDetails(propertyDetailsDataId);
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
                ownerDetails = new ExcelReader().getOwnerDetails(ownerDetailsDataId);
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
                addressDetails = new ExcelReader().getPropertyAddressDetails(addressDetailsDataId);
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
                assessmentDetails = new ExcelReader().getAssessmentDetails(assessmentDetailsDataId);
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
                amenities = new ExcelReader().getAmenties(amenitiesDataId);
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
                constructionTypeDetails = new ExcelReader().getConstructionTypeDetails(constructionTypeDetailsDataId);
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
                floorDetails = new ExcelReader().getFloorDetails(floorDetailsDataId);
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
                approvalDetails = new ExcelReader().getApprovalDetails(approvalDetailsDataId);
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
                approvalDetails = new ExcelReader().getApprovalDetails(approvalDetailsDataId);
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

    }
}
