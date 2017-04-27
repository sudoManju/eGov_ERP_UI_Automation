package pages.leaseAndAgreement;

import entities.leaseAndAgreement.LandAgreementDetails;
import entities.leaseAndAgreement.LandAllotteeDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class LeaseAndAgreementPage extends BasePage {

    @FindBy(id = "asset_category")
    private WebElement assetCategorySearchDropdown;

    @FindBy(css = "button[class='btn btn-submit']")
    private WebElement searchAsseetButton;

    @FindBy(css = "input[type='search']")
    private WebElement searchAssetCodeTextBox;

    @FindBy(id = "myOptions")
    private WebElement actionDropdown;

    // Land Allottee Details
    @FindBy(css = "input[id='allottee.aadhaarNumber']")
    private WebElement aadharNumberTextBox;

    @FindBy(css = "input[id='allottee.mobileNumber']")
    private WebElement mobileNumberTextBox;

    @FindBy(css = "input[id='allottee.name']")
    private WebElement nameTextBox;

    @FindBy(css = "input[id='allottee.emailId']")
    private WebElement emailIdTextBox;

    @FindBy(css = "input[id='allottee.pan']")
    private WebElement panTextBox;

    // Land Agreement Details
    @FindBy(css = "input[id='tenderNumber']")
    private WebElement tenderNumberTextBox;

    @FindBy(css = "input[id='tenderDate']")
    private WebElement tenderDate;

    @FindBy(css = "[id='natureOfAllotment']")
    private WebElement natureOfAllotmentDropdown;

    @FindBy(css = "[id='councilNumber']")
    private WebElement councilNumberTextBox;

    @FindBy(css = "[id='councilDate']")
    private WebElement councilDate;

    @FindBy(css = "[id='rent']")
    private WebElement landRentTextBox;

    @FindBy(css = "[id='paymentCycle']")
    private WebElement paymentCycleDropdown;

    @FindBy(css = "[id='bankGuaranteeAmount']")
    private WebElement bankGuaranteeAmountTextBox;

    @FindBy(css = "[id='bankGuaranteeDate']")
    private WebElement bankGuaranteeDate;

    @FindBy(css = "[id='solvencyCertificateNo']")
    private WebElement solvencyCertificateNumberTextBox;

    @FindBy(css = "[id='solvencyCertificateDate']")
    private WebElement solvencyCertificateDate;

    @FindBy(css = "[id='securityDeposit']")
    private WebElement securityDepositTextBox;

    @FindBy(css = "[id='securityDepositDate']")
    private WebElement securityDepositDate;

    @FindBy(css = "[id='commencementDate']")
    private WebElement commencementDate;

    @FindBy(css = "[id='rentIncrementMethod']")
    private WebElement rentIncrementMethodDropdown;

    @FindBy(css = "[id='timePeriod']")
    private WebElement timePeriodDropdown;

    // Approval Details
    @FindBy(css = "[id='approverDepartment']")
    private WebElement approverDepartmentDropdown;

    @FindBy(css = "[id='approverDesignation']")
    private WebElement approverDesignationDropdown;

    @FindBy(css = "[id='approverName']")
    private WebElement approverNameDropdown;

    private WebDriver webDriver;

    public LeaseAndAgreementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void searchAssetApplication() {
        selectFromDropDown(assetCategorySearchDropdown, "land", webDriver);
        clickOnButton(searchAsseetButton, webDriver);
        enterText(searchAssetCodeTextBox, "00003", webDriver);
        selectFromDropDown(actionDropdown, "Create", webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void enterAgreementDetails(LandAllotteeDetails landAllotteeDetails, LandAgreementDetails landAgreementDetails) {
        enterLandAllotteeDetails(landAllotteeDetails);
        enterLandAgreementDetails(landAgreementDetails);
    }

    private void enterLandAllotteeDetails(LandAllotteeDetails landAllotteeDetails) {
        enterText(aadharNumberTextBox, get6DigitRandomInt() + get6DigitRandomInt(), webDriver);
        enterText(mobileNumberTextBox, "9" + get6DigitRandomInt() + get6DigitRandomInt().substring(0, 3), webDriver);
        enterText(nameTextBox, landAllotteeDetails.getName(), webDriver);
        enterText(emailIdTextBox, landAllotteeDetails.getEmail(), webDriver);
        enterText(panTextBox, "ABCDE" + get6DigitRandomInt().substring(0, 4) + "F", webDriver);

    }

    private void enterLandAgreementDetails(LandAgreementDetails landAgreementDetails) {
        enterText(tenderNumberTextBox, "T" + get6DigitRandomInt().substring(0, 3), webDriver);
        enterDate(tenderDate, landAgreementDetails.getTenderDate(), webDriver);
        selectFromDropDown(natureOfAllotmentDropdown, landAgreementDetails.getNatureOfAllotment(), webDriver);
        enterText(councilNumberTextBox, "C" + get6DigitRandomInt().substring(0, 3), webDriver);
        enterDate(councilDate, landAgreementDetails.getCouncilDate(), webDriver);
        enterText(landRentTextBox, landAgreementDetails.getLandRent(), webDriver);
        selectFromDropDown(paymentCycleDropdown, landAgreementDetails.getPaymentCycle(), webDriver);
        enterText(bankGuaranteeAmountTextBox, landAgreementDetails.getBankGuaranteeAmount(), webDriver);
        enterDate(bankGuaranteeDate, landAgreementDetails.getBankGuaranteeDate(), webDriver);
        enterText(solvencyCertificateNumberTextBox, "S" + get6DigitRandomInt().substring(0, 3), webDriver);
        enterDate(solvencyCertificateDate, landAgreementDetails.getSolvencyCertificateDate(), webDriver);
        enterDate(commencementDate, landAgreementDetails.getCommencementDate(), webDriver);
        selectFromDropDown(rentIncrementMethodDropdown, landAgreementDetails.getRentIncrementMethod(), webDriver);
        selectFromDropDown(timePeriodDropdown, landAgreementDetails.getTimePeriod(), webDriver);
    }
}
