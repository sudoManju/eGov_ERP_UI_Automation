package pages.ptis;

import entities.ptis.RegistrationDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class TransferDetailsPage extends BasePage {

    private WebDriver webdriver;

    public TransferDetailsPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    @FindBy(id = "REGISTERED TRANSFER")
    private WebElement registrationAlreadyDoneButton;

    @FindBy(id = "seller")
    private WebElement sellerExecutantNameTextBox;

    @FindBy(id = "buyer")
    private WebElement buyerClaimantNameTextBox;

    @FindBy(id = "doorNo")
    private WebElement doorNoTextBox;

    @FindBy(id = "address")
    private WebElement propertyAddressTextBox;

    @FindBy(id = "mobileNumber")
    private WebElement transferMobileNumber;

    @FindBy(id = "plotArea")
    private WebElement registeredPlotAreaTextBox;

    @FindBy(id = "plinthArea")
    private WebElement registeredPlinthAreaTextBox;

    @FindBy(id = "eastBoundary")
    private WebElement eastBoundaryTextBox;

    @FindBy(id = "westBoundary")
    private WebElement westBoundaryTextBox;

    @FindBy(id = "northBoundary")
    private WebElement northBoundaryTextBox;

    @FindBy(id = "southBoundary")
    private WebElement southBoundaryTextBox;

    @FindBy(id = "sroName")
    private WebElement sRONameTextBox;

    @FindBy(id = "transRsnId")
    private WebElement reasonforTransfersection;

    @FindBy(id = "docNum")
    private WebElement registrationDocumentNumberTextBox;

    @FindBy(id = "deedDate")
    private WebElement registrationDocumentDateTextBox;

    @FindBy(id = "partyValue")
    private WebElement partiesConsiderationValueTextBox;

    @FindBy(id = "departmentValue")
    private WebElement departmentGuidelinesValueTextBox;

    @FindBy(name = "assessmentNo")
    private WebElement searchMutationTextBox;

    @FindBy(className = "buttonsubmit")
    private WebElement payFeeButton;

    @FindBy(id = "Generate Title Transfer Notice")
    private WebElement titleTransferNoticeTextBox;

    @FindBy(id = "assessmentNum")
    private WebElement searchAssessmentNumberTextBox;

    public void chooseRegistrationAlreadyDone() {
        waitForElementToBeClickable(registrationAlreadyDoneButton, webdriver);
        registrationAlreadyDoneButton.click();
    }

    public void enterRegistrationDetails(RegistrationDetails registrationDetails) {

        enterText(transferMobileNumber, "2299087661", webdriver);
        enterText(sellerExecutantNameTextBox, registrationDetails.getSellerExecutantName(), webdriver);
        enterText(buyerClaimantNameTextBox, registrationDetails.getBuyerClaimantName(), webdriver);
        enterText(doorNoTextBox, registrationDetails.getDoorNo(), webdriver);
        enterText(propertyAddressTextBox, registrationDetails.getPropertyAddress(), webdriver);
        enterText(registeredPlotAreaTextBox, registrationDetails.getRegisteredPlotArea(), webdriver);
        enterText(registeredPlinthAreaTextBox, registrationDetails.getRegisteredPlinthArea(), webdriver);
        enterText(eastBoundaryTextBox, registrationDetails.getEastBoundary(), webdriver);
        enterText(westBoundaryTextBox, registrationDetails.getWestBoundary(), webdriver);
        enterText(northBoundaryTextBox, registrationDetails.getNorthBoundary(), webdriver);
        enterText(southBoundaryTextBox, registrationDetails.getSouthBoundary(), webdriver);
        enterText(sRONameTextBox, registrationDetails.getSroName(), webdriver);
        selectFromDropDown(reasonforTransfersection, registrationDetails.getReasonForChange(), webdriver);
        enterText(registrationDocumentNumberTextBox, registrationDetails.getRegistrationDocumentNumber(), webdriver);
        enterText(registrationDocumentDateTextBox, registrationDetails.getRegistrationDocumentDate(), webdriver);
        enterText(partiesConsiderationValueTextBox, registrationDetails.getPartiesConsiderationValue(), webdriver);
        enterText(departmentGuidelinesValueTextBox, registrationDetails.getDepartmentGuidelinesValue(), webdriver);
    }

    public void enterEnclosureDetails() {

        WebElement document1 = webdriver.findElement(By.id("save_documents_0__uploads"));
        uploadFile(document1, System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx", webdriver);
        WebElement document2 = webdriver.findElement(By.id("save_documents_1__uploads"));
        uploadFile(document2, System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx", webdriver);
        WebElement document3 = webdriver.findElement(By.id("save_documents_2__uploads"));
        uploadFile(document3, System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx", webdriver);
        WebElement document4 = webdriver.findElement(By.id("save_documents_3__uploads"));
        uploadFile(document4, System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx", webdriver);
    }

    public void searchAssessmentNumber(String mutationAssessmentNumber) {
        enterText(searchMutationTextBox, mutationAssessmentNumber, webdriver);
        clickOnButton(payFeeButton, webdriver);
    }

    public void generateTitleTransferNotice() {
        clickOnButton(titleTransferNoticeTextBox, webdriver);
        switchToNewlyOpenedWindow(webdriver);
        webdriver.close();
        switchToPreviouslyOpenedWindow(webdriver);
    }
}
