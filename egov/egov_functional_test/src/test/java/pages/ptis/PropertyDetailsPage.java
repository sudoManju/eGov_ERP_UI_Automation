package pages.ptis;

import entities.ptis.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PropertyDetailsPage extends BasePage {

    private WebDriver webDriver;


    @FindBy(id = "propTypeCategoryId")
    private WebElement propertyTypeSelection;

    @FindBy(id = "upicNo")
    private WebElement propertyAssessmentNo;

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

    @FindBy(id = "floorTypeId")
    private WebElement floorTypeSelection;

    @FindBy(id = "roofTypeId")
    private WebElement roofTypeSelection;

    @FindBy(id = "wallTypeId")
    private WebElement wallTypeSelection;

    @FindBy(id = "woodTypeId")
    private WebElement woodTypeSelection;

    @FindBy(id = "floorNo")
    private WebElement floorNumberSelection;

    @FindBy(id = "floorConstType")
    private WebElement classificationOfBuildingSelection;

    @FindBy(id = "floorUsage")
    private WebElement natureOfUsageSelection;

    @FindBy(id = "firmName")
    private WebElement firmNameTextBox;

    @FindBy(id = "floorOccupation")
    private WebElement occupancySelection;

    @FindBy(id = "occupantName")
    private WebElement occupantNameTextBox;

    @FindBy(id = "propertyDetail.floorDetailsProxy[0].constructionDate")
    private WebElement constructionDateTextBox;

    @FindBy(id = "propertyDetail.floorDetailsProxy[0].occupancyDate")
    private WebElement effectiveFromDateTextBox;

    @FindBy(id = "unstructuredLand")
    private WebElement unstructuredLandSelection;

    @FindBy(id = "builtUpArealength")
    private WebElement lengthTextBox;

    @FindBy(id = "builtUpAreabreadth")
    private WebElement breadthTextBox;

    @FindBy(id = "builtUpArea")
    private WebElement plinthAreaTextBox;

    @FindBy(id = "propertyDetail.floorDetailsProxy[0].buildingPermissionNo")
    private WebElement buildingPermissionNumberTextBox;

    @FindBy(id = "propertyDetail.floorDetailsProxy[0].buildingPermissionDate")
    private WebElement buildingPermissionDateTextBox;

    @FindBy(id = "propertyDetail.floorDetailsProxy[0].buildingPlanPlinthArea.area")
    private WebElement plinthAreaInBuildingPlanTextBox;


    @FindBy(id = "approverDepartment")
    private WebElement approverDepartmentSelection;

    @FindBy(id = "approverDesignation")
    private WebElement approverDesignationSelection;

    @FindBy(id = "approverPositionId")
    private WebElement approverSelection;

    @FindBy(id = "approverComments")
    private WebElement approverRemarksTextArea;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(id = "Approve")
    private WebElement approveButton;

    @FindBy(id = "Sign")
    private WebElement signButton;

    @FindBy(id = "Generate Notice")
    private WebElement generateNotice;

    @FindBy(id = "Create")
    private WebElement create;

    @FindBy(id = "propertyIdentifier")
    private WebElement assessmentNumberTextBox1;

    @FindBy(id = "consumerCodeData")
    private WebElement hscNumberTextBox;

    @FindBy(id = "executionDate")
    private WebElement connectionDateTextBox;

    @FindBy(id = "waterSource")
    private WebElement waterSourceSelection;

    @FindBy(id = "connectionType")
    private WebElement connectionTypeSelection;

    @FindBy(id = "propertyType")
    private WebElement propertyType1Selection;

    @FindBy(id = "connectionCategorie")
    private WebElement categorySelection;

    @FindBy(id = "usageType")
    private WebElement usageTypeSelection;

    @FindBy(id = "pipeSize")
    private WebElement pipeSizeSelection;

    @FindBy(name = "existingConnection.monthlyFee")
    private WebElement monthlyFeeTextBox;

    @FindBy(id = "existingConnection.donationCharges")
    private WebElement donationChargesTextBox;

    @FindBy(id = "assessmentNum")
    private WebElement assessmentNumTextBox;

    @FindBy(id = "searchByassmentno")
    private WebElement searchButtonByAssmentNo;

    @FindBy(className = "pagelinks")
    private WebElement recordsFound;

    @FindBy(id = "doorNo")
    private WebElement doorNoTextBox;

    @FindBy(id = "mobileNumber")
    private WebElement mobileNoTextBox;

    @FindBy(id = "searchDoorno")
    private WebElement searchButtonByDoorNo;

    @FindBy(id = "searchMobileno")
    private WebElement searchButtonByMobileNo;

    @FindBy(id = "zoneId")
    private WebElement zoneId;

    @FindBy(id = "wardId")
    private WebElement wardId;

    @FindBy(id = "searchByBndry")
    private WebElement searchButtonByZoneAndWard;

    @FindBy(id = "upicNo")
    private WebElement assessmentNumberTextBox;

    @FindBy(id = "Create")
    private WebElement createButton;

    @FindBy(id = "assessmentNum")
    private WebElement assessmentTextbox;

    @FindBy(id = "assessmentform_search")
    private WebElement searchButton;

    @FindBy(name = "assessmentNum")
    private WebElement searchAssessmentTextBox;

    @FindBy(id = "certificationNumber")
    private WebElement editOccupancyTextBox;

    @FindBy(id = "occupantname")
    private WebElement editoccupantNameTextBox;

    @FindBy(id = "propertyDetail.floorDetailsProxy[%#floorsstatus.index].constructionDate")
    private WebElement editconstructionDateTextBox;

    @FindBy(id = "propertyDetail.floorDetailsProxy[%#floorsstatus.index].occupancyDate")
    private WebElement editeffectiveFromDateTextBox;

    @FindBy(id = "applicationDocs0documentNumber")
    private WebElement documentNum1Box;

    @FindBy(id = "applicationDocs0documentDate")
    private WebElement documentDate1Box;

    @FindBy(id = "applicationDocs1documentNumber")
    private  WebElement documentNum2Box;

    @FindBy(id ="applicationDocs1documentDate")
    private WebElement documentDate2Box;

    @FindBy(id ="applicationDocs3documentNumber")
    private WebElement documentNum3Box;

    @FindBy(id = "applicationDocs3documentDate")
    private WebElement documentDate3Box;

    @FindBy(id = "approvalDepartment")
    private WebElement approvalWaterDept;

    @FindBy(id = "approvalDesignation")
    private WebElement approvalWaterDesig;

    @FindBy(id = "approvalPosition")
    private WebElement approvalWaterPos;

    @FindBy(id = "approvalComent")
    private WebElement approvalWaterComment;


    public PropertyDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void enterPropertyHeader(PropertyHeaderDetails propertyHeaderDetails) {


        System.out.println("Before category selection ---" + new Select(propertyTypeSelection).getOptions().size());
        waitForElementToBeClickable(categoryOfOwnershipSelection, webDriver);
        new Select(categoryOfOwnershipSelection).selectByVisibleText(propertyHeaderDetails.getCategoryOfOwnership());

        waitForElementToBeClickable(propertyTypeSelection, webDriver);
        System.out.println("After category selection ---" + new Select(propertyTypeSelection).getOptions().size());
        await().atMost(5, SECONDS).until(() -> new Select(propertyTypeSelection).getOptions().size() > 1);
        new Select(propertyTypeSelection).selectByVisibleText(propertyHeaderDetails.getPropertyType());
    }

    public void enterOwnerDetails(OwnerDetails ownerDetails) {
        waitForElementToBeClickable(mobileNumberTextBox, webDriver);

        enterText(mobileNumberTextBox, ownerDetails.getMobileNumber());
        enterText(ownerNameTextBox, ownerDetails.getOwnerName());
        new Select(genderSelection).selectByVisibleText(ownerDetails.getGender().toUpperCase());
        enterText(emailIdTextBox, ownerDetails.getEmailAddress());
        new Select(guardianRelationSelection).selectByVisibleText(ownerDetails.getGuardianRelation());
        enterText(guardianTextBox, ownerDetails.getGuardianName());
    }

    public void enterPropertyAddressDetails(PropertyAddressDetails addressDetails) {
        waitForElementToBeClickable(localitySelection, webDriver);

        new Select(localitySelection).selectByVisibleText(addressDetails.getLocality());
        new Select(zoneNumberSelection).selectByVisibleText(addressDetails.getZoneNumber());
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

    public void enterConstructionTypeDetails(ConstructionTypeDetails constructionTypeDetails) {
        new Select(floorTypeSelection).selectByVisibleText(constructionTypeDetails.getFloorType());
        new Select(roofTypeSelection).selectByVisibleText(constructionTypeDetails.getRoofType());
        new Select(woodTypeSelection).selectByVisibleText(constructionTypeDetails.getWoodType());
        new Select(wallTypeSelection).selectByVisibleText(constructionTypeDetails.getWallType());
    }

    public void enterFloorDetails(FloorDetails floorDetails) {
        new Select(floorNumberSelection).selectByVisibleText(floorDetails.getFloorNumber());
        new Select(classificationOfBuildingSelection).selectByVisibleText(floorDetails.getClassificationOfBuilding());
        new Select(natureOfUsageSelection).selectByVisibleText(floorDetails.getNatureOfUsage());
        firmNameTextBox.sendKeys(floorDetails.getFirmName());
        new Select(occupancySelection).selectByVisibleText(floorDetails.getOccupancy());
        occupantNameTextBox.sendKeys(floorDetails.getOccupantName());

        constructionDateTextBox.sendKeys(floorDetails.getConstructionDate());
        constructionDateTextBox.sendKeys(Keys.TAB);

        effectiveFromDateTextBox.sendKeys(floorDetails.getEffectiveFromDate());
        effectiveFromDateTextBox.sendKeys(Keys.TAB);

        new Select(unstructuredLandSelection).selectByVisibleText(floorDetails.getUnstructuredLand());

        lengthTextBox.sendKeys(floorDetails.getLength());
        breadthTextBox.sendKeys(floorDetails.getBreadth());
//        plinthAreaTextBox.sendKeys(floorDetails.getPlinthArea());

        buildingPermissionNumberTextBox.sendKeys(floorDetails.getBuildingPermissionNumber());
        buildingPermissionDateTextBox.sendKeys(floorDetails.getBuildingPermissionDate());
        plinthAreaInBuildingPlanTextBox.sendKeys(floorDetails.getPlinthAreaInBuildingPlan());
    }

    public void enterApplicantDetails(ApplicantDetails applicantDetails) {
        waitForElementToBeClickable(assessmentNumberTextBox1, webDriver);
        enterText(assessmentNumberTextBox1, applicantDetails.getAssessmentNumber());

//        waitForElementToBeClickable(hscNumberTextBox, webDriver);
//        enterText(hscNumberTextBox, applicantDetails.getHscNumber());
//
//        waitForElementToBeClickable(connectionDateTextBox, webDriver);
//        enterText(connectionDateTextBox, applicantDetails.getConnectionDate());
    }

    public void enterConnectionDetails(ConnectionDetails connectionDetails) {
        waitForElementToBeClickable(waterSourceSelection, webDriver);

        new Select(waterSourceSelection).selectByVisibleText(connectionDetails.getWaterSourceType());
        new Select(propertyType1Selection).selectByVisibleText(connectionDetails.getPropertyType());
        new Select(categorySelection).selectByVisibleText(connectionDetails.getCategory());
        new Select(connectionTypeSelection).selectByVisibleText(connectionDetails.getConnectionType());
        new Select(usageTypeSelection).selectByVisibleText(connectionDetails.getUsageType());
        new Select(pipeSizeSelection).selectByVisibleText(connectionDetails.getHscPipeSize());

    }

    public void enterFeeDetails(FeeDetails feeDetails) {
        waitForElementToBeClickable(monthlyFeeTextBox, webDriver);
        enterText(monthlyFeeTextBox, feeDetails.getMonthlyFee());

        waitForElementToBeClickable(donationChargesTextBox, webDriver);
        enterText(donationChargesTextBox, feeDetails.getDonationCharges());
    }

    public void enterApprovalDetails(ApprovalDetails approvalDetails) {
        new Select(approverDepartmentSelection).selectByVisibleText(approvalDetails.getApproverDepartment());
        await().atMost(10, SECONDS).until(() -> new Select(approverDesignationSelection).getOptions().size() > 1);
        new Select(approverDesignationSelection).selectByVisibleText(approvalDetails.getApproverDesignation());
        await().atMost(10, SECONDS).until(() -> new Select(approverSelection).getOptions().size() > 1);
        new Select(approverSelection).selectByVisibleText(approvalDetails.getApprover());
        enterApproverRemarks(approvalDetails.getApproverRemarks());
    }

    public void enterApprovalDetailsForWater(ApprovalDetails approvalDetails) {
        new Select(approvalWaterDept).selectByVisibleText(approvalDetails.getApproverDepartment());
        await().atMost(10, SECONDS).until(() -> new Select(approvalWaterDesig).getOptions().size() > 1);
        new Select(approvalWaterDesig).selectByVisibleText(approvalDetails.getApproverDesignation());
        await().atMost(10, SECONDS).until(() -> new Select(approvalWaterPos).getOptions().size() > 1);
        new Select(approvalWaterPos).selectByVisibleText(approvalDetails.getApprover());
        enterApproverRemarks(approvalDetails.getApproverRemarks());
    }

    public void enterApproverRemarks(String approverRemarks) {
        approvalWaterComment.sendKeys(approverRemarks);
    }


    public void forward() {
        forwardButton.click();
    }

    public void approve() {
        approveButton.click();
    }

    public void digitallySign() {
        signButton.click();
    }

    public void generateNotice() {
        generateNotice.click();
    }

    public void enterSearchDetailsOfAssessmentNumber(SearchDetails searchDetails) {
        waitForElementToBeClickable(assessmentNumTextBox, webDriver);
        enterText(assessmentNumTextBox, searchDetails.getSearchValue1());

        searchButtonByAssmentNo.click();
    }

    public void checkNoOfRecords() {
        waitForElementToBeVisible(recordsFound, webDriver);
        int noOfRecords = Integer.parseInt(recordsFound.getText());

        if (noOfRecords > 0) {
            System.out.println("Records Founds:" + noOfRecords);
        } else
            System.out.println("No records founds");
    }

    public void enterSearchDetailsOfDoorNumber(SearchDetails searchDetails) {
        waitForElementToBeClickable(doorNoTextBox, webDriver);
        enterText(doorNoTextBox, searchDetails.getSearchValue1());

        searchButtonByDoorNo.click();
    }

    public void enterSearchDetailsOfMobileNumber(SearchDetails searchDetails) {
        waitForElementToBeClickable(mobileNoTextBox, webDriver);
        enterText(mobileNoTextBox, searchDetails.getSearchValue1());

        searchButtonByMobileNo.click();
    }

    public void enterSearchDetailsOfZoneAndWardNumber(SearchDetails searchDetails) {
        waitForElementToBeClickable(zoneId, webDriver);

        new Select(zoneId).selectByVisibleText(searchDetails.getSearchValue1());
        new Select(wardId).selectByVisibleText(searchDetails.getSearchValue2());

        searchButtonByZoneAndWard.click();
    }

    public void enterAssessmentNumber(String assessmentNumber) {
        assessmentNumberTextBox.sendKeys(assessmentNumber);
    }

    public void create() {
        createButton.click();
    }

    public void searchAssessmentNumber(String assessmentNum) {
        searchAssessmentTextBox.sendKeys(assessmentNum);
    }

    public void search() {
        searchButton.click();
    }

    public void enterEditAssessmentDetails(EditAssessmentDetails assessmentDetails) {
        // waitForElementToBeClickable(extentOfSiteTextBox, webDriver);
        extentOfSiteTextBox.clear();
        extentOfSiteTextBox.sendKeys(assessmentDetails.getExtentOfSite());

        editOccupancyTextBox.sendKeys(assessmentDetails.getOccupancyCertificateNumber());


    }

    public void enterEditFloorDetails(EditFloorDetails floorDetails) {
        new Select(floorNumberSelection).selectByVisibleText(floorDetails.getEditfloorNumber());
        new Select(classificationOfBuildingSelection).selectByVisibleText(floorDetails.getEditclassificationOfBuilding());
        new Select(natureOfUsageSelection).selectByVisibleText(floorDetails.getEditnatureOfUsage());


        new Select(occupancySelection).selectByVisibleText(floorDetails.getEditoccupancy());
        editoccupantNameTextBox.sendKeys(floorDetails.getEditoccupantName());
        editconstructionDateTextBox.sendKeys(floorDetails.getEditconstructionDate());
        editconstructionDateTextBox.sendKeys(Keys.TAB);

        editeffectiveFromDateTextBox.sendKeys(floorDetails.getEditeffectiveFromDate());
        editeffectiveFromDateTextBox.sendKeys(Keys.TAB);
        new Select(unstructuredLandSelection).selectByVisibleText(floorDetails.getEditunstructuredLand());
        lengthTextBox.sendKeys(floorDetails.getEditlength());
        breadthTextBox.sendKeys(floorDetails.getEditbreadth());
        buildingPermissionNumberTextBox.sendKeys(floorDetails.getEditbuildingPermissionNumber());
        buildingPermissionDateTextBox.sendKeys(floorDetails.getEditbuildingPermissionDate());
        buildingPermissionDateTextBox.sendKeys(Keys.TAB);
        plinthAreaInBuildingPlanTextBox.sendKeys(floorDetails.getEditplinthAreaInBuildingPlan());

    }

    public void enterEnclosedDocumentsDetails(EnclosedDocuments enclosedDocuments) {

        waitForElementToBeClickable(documentNum1Box, webDriver);
        documentNum1Box.sendKeys(enclosedDocuments.getDocumentNumber1());
        waitForElementToBeClickable(documentNum2Box, webDriver);
        documentNum2Box.sendKeys(enclosedDocuments.getDocumentNumber2());
        waitForElementToBeClickable(documentNum3Box, webDriver);
        documentNum3Box.sendKeys(enclosedDocuments.getDocumentNumber3());
        waitForElementToBeClickable(documentDate1Box, webDriver);
      enterText(documentDate1Box,enclosedDocuments.getDocumentDate1());
        waitForElementToBeClickable(documentDate2Box, webDriver);
        enterText(documentDate2Box,enclosedDocuments.getDocumentDate2());
        waitForElementToBeClickable(documentDate3Box, webDriver);
        enterText(documentDate3Box,enclosedDocuments.getDocumentDate2());

    }
}