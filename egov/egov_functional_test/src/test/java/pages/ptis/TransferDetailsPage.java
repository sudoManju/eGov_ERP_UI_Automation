package pages.ptis;

import entities.ptis.RegistrationDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

/**
 * Created by bimal on 18/1/17.
 */
public class TransferDetailsPage extends BasePage {

    private WebDriver webdriver;

    public TransferDetailsPage (WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    @FindBy (id = "REGISTERED TRANSFER")
    private WebElement registrationAlreadyDoneButton;

    @FindBy(id = "seller")
    private WebElement sellerExecutantNameTextBox;

    @FindBy (id = "buyer")
    private WebElement buyerClaimantNameTextBox;

    @FindBy (id = "doorNo")
    private WebElement doorNoTextBox;

    @FindBy (id = "address")
    private WebElement propertyAddressTextBox;

    @FindBy (id = "mobileNumber")
    private WebElement transferMobileNumber;

    @FindBy (id = "plotArea")
    private WebElement registeredPlotAreaTextBox;

    @FindBy (id = "plinthArea")
    private WebElement registeredPlinthAreaTextBox;

    @FindBy (id = "eastBoundary")
    private WebElement eastBoundaryTextBox;

    @FindBy (id = "westBoundary")
    private WebElement westBoundaryTextBox;

    @FindBy (id = "northBoundary")
    private WebElement northBoundaryTextBox;

    @FindBy (id = "southBoundary")
    private WebElement southBoundaryTextBox;

    @FindBy (id = "sroName")
    private WebElement sRONameTextBox;

    @FindBy (id = "transRsnId")
    private WebElement reasonforTransfersection;

    @FindBy (id = "docNum")
    private WebElement registrationDocumentNumberTextBox;

    @FindBy (id = "deedDate")
    private WebElement registrationDocumentDateTextBox;

    @FindBy (id = "partyValue")
    private WebElement partiesConsiderationValueTextBox;

    @FindBy (id = "departmentValue")
    private WebElement departmentGuidelinesValueTextBox;

    @FindBy (name = "assessmentNo")
    private WebElement searchMutationTextBox;

    @FindBy (className = "buttonsubmit")
    private WebElement payFeeButton;

    public void chooseRegistrationAlreadyDone() {
        waitForElementToBeClickable(registrationAlreadyDoneButton, webdriver);
        registrationAlreadyDoneButton.click();
    }


    public void enterRegistrationDetails(RegistrationDetails registrationDetails) {
        waitForElementToBeClickable(transferMobileNumber, webdriver);
        transferMobileNumber.sendKeys("2299087661");
        transferMobileNumber.sendKeys(Keys.TAB);
        waitForElementToBeClickable(sellerExecutantNameTextBox, webdriver);
        enterText(sellerExecutantNameTextBox, registrationDetails.getSellerExecutantName());
        enterText(buyerClaimantNameTextBox, registrationDetails.getBuyerClaimantName());
        enterText(doorNoTextBox, registrationDetails.getDoorNo());
        enterText(propertyAddressTextBox, registrationDetails.getPropertyAddress());
        enterText(registeredPlotAreaTextBox, registrationDetails.getRegisteredPlotArea());
        enterText(registeredPlinthAreaTextBox, registrationDetails.getRegisteredPlinthArea());
        enterText(eastBoundaryTextBox, registrationDetails.getEastBoundary());
        enterText(westBoundaryTextBox, registrationDetails.getWestBoundary());
        enterText(northBoundaryTextBox, registrationDetails.getNorthBoundary());
        enterText(southBoundaryTextBox, registrationDetails.getSouthBoundary());
        enterText(sRONameTextBox, registrationDetails.getSroName());
        new Select(reasonforTransfersection).selectByVisibleText(registrationDetails.getReasonForChange());
        enterText(registrationDocumentNumberTextBox, registrationDetails.getRegistrationDocumentNumber());
        enterText(registrationDocumentDateTextBox, registrationDetails.getRegistrationDocumentDate());
        enterText(partiesConsiderationValueTextBox, registrationDetails.getPartiesConsiderationValue());
        enterText(departmentGuidelinesValueTextBox, registrationDetails.getDepartmentGuidelinesValue());
        departmentGuidelinesValueTextBox.sendKeys(Keys.TAB);

    }


    public void enterEnclosureDetails() {

        WebElement document1 = webdriver.findElement(By.id("save_documents_0__uploads"));
        document1.sendKeys(System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx");

        WebElement document2 = webdriver.findElement(By.id("save_documents_1__uploads"));
        document2.sendKeys(System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx");

        WebElement document3 = webdriver.findElement(By.id("save_documents_2__uploads"));
        document3.sendKeys(System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx");

        WebElement document4 = webdriver.findElement(By.id("save_documents_3__uploads"));
        document4.sendKeys(System.getProperty("user.dir") + "/src/test/resources/PTISTestData.xlsx");
    }

    public void searchAssessmentNumber(String mutationAssessmentNumber) {
        searchMutationTextBox.sendKeys(mutationAssessmentNumber);

        waitForElementToBeClickable(payFeeButton, webdriver);


    }
}
