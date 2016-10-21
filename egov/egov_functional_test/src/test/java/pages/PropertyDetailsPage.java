package pages;

import entities.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PropertyDetailsPage extends BasePage {

    private WebDriver webDriver;


    @FindBy(id = "propTypeCategoryId")
    private WebElement propertyTypeSelection;

    @FindBy(id = "propTypeId")
    private WebElement categoryOfOwnershipSelection;

    @FindBy(id = "aadharNo")
    private WebElement aadharNoTextBox;

    @FindBy(id = "mobileNumber")
    private WebElement mobileNumberTextBox;

    @FindBy(id = "ownerName")
    private WebElement ownerNameTextBox;

    @FindBy(id = "gender")
    private WebElement genderSelection;

    @FindBy(id = "emailId")
    private WebElement emailIdTextBox;

    @FindBy(id = "basicProperty.propertyOwnerInfoProxy[0].owner.guardianRelation")
    private WebElement guardianRelationSelection;

    @FindBy(id = "guardian")
    private WebElement guardianTextBox;

    @FindBy(id = "locality")
    private WebElement localitySelection;

    @FindBy(id = "zoneId")
    private WebElement zoneNumberSelection;

    @FindBy(id = "wardId")
    private WebElement wardNumberSelection;

    @FindBy(id = "blockId")
    private WebElement blockNumberSelection;

    @FindBy(id = "electionWardId")
    private WebElement electionWardSeletion;

    @FindBy(id = "createProperty-create_pinCode")
    private WebElement pincodeTextBox;

    @FindBy(id = "createProperty-create_houseNumber")
    private WebElement doorNumberTextBox;

    @FindBy(id = "mutationId")
    private WebElement reasonForCreationSelection;

    @FindBy(id = "areaOfPlot")
    private WebElement extentOfSiteTextBox;

    @FindBy(id = "propertyDetail.occupancyCertificationNo")
    private WebElement occupancyCertificateNumberTextBox;

    @FindBy(id = "regdDocNo")
    private WebElement registrationDocNumber;

    @FindBy(id = "basicProperty.regdDocDate")
    private WebElement registrationDocDate;

    @FindBy(id = "propertyDetail.lift")
    private WebElement liftCheckbox;

    @FindBy(id = "propertyDetail.toilets")
    private WebElement toiletsCheckbox;

    @FindBy(id = "propertyDetail.waterTap")
    private WebElement waterTapCheckbox;

    @FindBy(id = "propertyDetail.electricity")
    private WebElement electricityCheckbox;

    @FindBy(id = "propertyDetail.attachedBathRoom")
    private WebElement attachedBathroomCheckbox;

    @FindBy(id = "propertyDetail.waterHarvesting")
    private WebElement waterHarvestingCheckbox;

    @FindBy(id = "propertyDetail.cable")
    private WebElement cableConnectionCheckbox;


    public PropertyDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void enterPropertyHeader(PropertyHeaderDetails propertyHeaderDetails) {

        System.out.println("Before category selection ---" + new Select(propertyTypeSelection).getOptions().size());
        waitForElementToBeClickable(categoryOfOwnershipSelection, webDriver);
        new Select(categoryOfOwnershipSelection).selectByVisibleText(propertyHeaderDetails.getCategoryOfOwnership());

        waitForElementToBeClickable(propertyTypeSelection, webDriver);
        System.out.println("After category selection ---" + new Select(propertyTypeSelection).getOptions().size());
        new Select(propertyTypeSelection).selectByVisibleText(propertyHeaderDetails.getPropertyType());
    }

    public void enterOwnerDetails(OwnerDetails ownerDetails) {
        waitForElementToBeClickable(mobileNumberTextBox, webDriver);

        mobileNumberTextBox.sendKeys(ownerDetails.getMobileNumber());
        ownerNameTextBox.sendKeys(ownerDetails.getOwnerName());
        new Select(genderSelection).selectByVisibleText(ownerDetails.getGender().toUpperCase());
        emailIdTextBox.sendKeys(ownerDetails.getEmailAddress());
        new Select(guardianRelationSelection).selectByVisibleText(ownerDetails.getGuardianRelation());
        guardianTextBox.sendKeys(ownerDetails.getGuardianName());
    }

    public void enterPropertyAddressDetails(PropertyAddressDetails addressDetails) {
        waitForElementToBeClickable(localitySelection, webDriver);

        new Select(localitySelection).selectByVisibleText(addressDetails.getLocality());
        new Select(zoneNumberSelection).selectByVisibleText(addressDetails.getZoneNumber());
//        new Select(wardNumberSelection).selectByVisibleText(addressDetails.getWardNumber());
//        new Select(blockNumberSelection).selectByVisibleText(addressDetails.getBlockNumber());
        new Select(electionWardSeletion).selectByVisibleText(addressDetails.getElectionWard());

        doorNumberTextBox.sendKeys(addressDetails.getDoorNumber());
        pincodeTextBox.sendKeys(addressDetails.getPincode());
    }

    public void enterAssessmentDetails(AssessmentDetails assessmentDetails) {
        new Select(reasonForCreationSelection).selectByVisibleText(assessmentDetails.getReasonForCreation());
        extentOfSiteTextBox.sendKeys(assessmentDetails.getExtentOfSite());
        occupancyCertificateNumberTextBox.sendKeys(assessmentDetails.getOccupancyCertificateNumber());
        registrationDocNumber.sendKeys(assessmentDetails.getRegistrationDocNumber());
        registrationDocDate.sendKeys(assessmentDetails.getRegistrationDocDate());
    }

    public void selectAmenities(Amenities amenities) {
        selectAmenityIfRequired(liftCheckbox, amenities.getLift());
        selectAmenityIfRequired(toiletsCheckbox, amenities.getToilets());
        selectAmenityIfRequired(attachedBathroomCheckbox, amenities.getAttachedBathroom());
        selectAmenityIfRequired(electricityCheckbox, amenities.getElectricity());
        selectAmenityIfRequired(waterTapCheckbox, amenities.getWaterTap());
        selectAmenityIfRequired(waterHarvestingCheckbox, amenities.getWaterHarvesting());
        selectAmenityIfRequired(cableConnectionCheckbox, amenities.getCableConnection());
    }

    private void selectAmenityIfRequired(WebElement element, Boolean hasAmenity) {
        if (hasAmenity && !element.isSelected())
            element.click();
    }
}
