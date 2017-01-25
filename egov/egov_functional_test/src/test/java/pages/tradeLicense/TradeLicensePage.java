package pages.tradeLicense;

import entities.ptis.ApprovalDetails;
import entities.tradeLicense.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by bimal on 20/12/16.
 */
public class TradeLicensePage extends BasePage {
    private WebDriver webDriver;

    @FindBy(id = "adhaarId")
    private WebElement aadhaarNumberTextBox;

    @FindBy(id = "mobilePhoneNumber")
    private WebElement mobileNumberTextBox;

    @FindBy(id = "applicantName")
    private WebElement tradeOwnerNameTextBox;

    @FindBy(id = "fatherOrSpouseName")
    private WebElement fatherSpouseNameTextBox;

    @FindBy(id = "emailId")
    private WebElement emailIDTextBox;

    @FindBy(id = "licenseeAddress")
    private WebElement tradeOwnerAddressTextBox;

    @FindBy(id = "propertyNo")
    private WebElement propertyAssessmentNumberTextBox;

    @FindBy(id = "ownershipType")
    private WebElement OwnershipTypeDropBox;

    @FindBy(id = "nameOfEstablishment")
    private WebElement tradeTitleTextBox;

    @FindBy(id = "buildingType")
    private WebElement TradeTypeDropBox;

    @FindBy(id = "category")
    private WebElement TradeCategoryDropBox;

    @FindBy(id = "select2-subCategory-container")
    private WebElement tradeSubCategoryDropBox;

    @FindBy(id = "tradeArea_weight")
    private WebElement TradeAreaWeightOfPremises;

    @FindBy(id = "remarks")
    private WebElement remarksTextBox;

    @FindBy(id = "startDate")
    private WebElement tradeCommencementDateTextBox;

    @FindBy(id = "btnsave")
    private WebElement saveButton;

    @FindBy(className = "select2-search__field")
    private WebElement searchBox;

    @FindBy(id = "close")
    private WebElement closeButton;

    @FindBy(id = "applicationNumber")
    private WebElement applicationNumberTextBox;

    @FindBy(id = "searchtree")
    private WebElement searchTreeBox;

    @FindBy(id = "btnsearch")
    private WebElement searchButton;

    @FindBy(id = "recordActions")
    private WebElement collectFeeDropBox;

    @FindBy(id = "instrHeaderCash.instrumentAmount")
    private WebElement amountTextBox;

    @FindBy(id = "totalamounttobepaid")
    private WebElement totalAmountReceived;

//    @FindBy (id = "button2']")
//    private WebElement tradePayButton;

    @FindBy(id = "oldLicenseNumber")
    private WebElement oldTradeLicense;

    @FindBy(id = "buttonClose")
    private WebElement printClose;

    @FindBy(name = "legacyInstallmentwiseFees[201104]")
    private WebElement amount1;

    @FindBy(name = "legacyInstallmentwiseFees[201204]")
    private WebElement amount2;

    @FindBy(name = "legacyInstallmentwiseFees[201304]")
    private WebElement amount3;

    @FindBy(name = "legacyInstallmentwiseFees[201404]")
    private WebElement amount4;

    @FindBy(name = "legacyInstallmentwiseFees[201504]")
    private WebElement amount5;

    @FindBy(name = "legacyInstallmentwiseFees[201604]")
    private WebElement amount6;

    @FindBy(id = "enterTradeLicense-enterExisting_legacyFeePayStatus_201104_")
    private WebElement checkBox1;

    @FindBy(id = "enterTradeLicense-enterExisting_legacyFeePayStatus_201204_")
    private WebElement checkBox2;

    @FindBy(id = "enterTradeLicense-enterExisting_legacyFeePayStatus_201304_")
    private WebElement checkBox3;

    @FindBy(id = "enterTradeLicense-enterExisting_legacyFeePayStatus_201404_")
    private WebElement checkBox4;

    @FindBy(id = "enterTradeLicense-enterExisting_legacyFeePayStatus_201504_")
    private WebElement checkBox5;

    @FindBy(id = "enterTradeLicense-enterExisting_legacyFeePayStatus_201604_")
    private WebElement checkBox6;

    @FindBy(id = "status")
    private WebElement statusSelect;

    @FindBy(id = "category")
    private WebElement tradeCategorySelect;

    @FindBy(id = "approverComments")
    private WebElement approverRemarkTextBox;

    @FindBy(id = "Approve")
    private WebElement approveButton;

    @FindBy(id = "approverComments")
    private WebElement approverCommentsTextBox;

    @FindBy(id = "approverDepartment")
    private WebElement approverDepartmentSelection;

    @FindBy(id = "approverDesignation")
    private WebElement approverDesignationSelection;

    @FindBy(id = "approverPositionId")
    private WebElement approverSelection;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(id = "Generate Certificate")
    private WebElement generateCertificateButton;


    String tradeApplicationNumber;

    public TradeLicensePage(WebDriver webDriver) {this.webDriver = webDriver;
    }
    public void entertradeOwnerDetails(TradeOwnerDetails tradeOwnerDetails) {
        waitForElementToBeClickable(aadhaarNumberTextBox , webDriver);

        enterText(aadhaarNumberTextBox, tradeOwnerDetails.getAadhaarNumber());
        enterText(mobileNumberTextBox, tradeOwnerDetails.getMobileNumber());
        enterText(tradeOwnerNameTextBox, tradeOwnerDetails.getTradeOwnerName());
        enterText(fatherSpouseNameTextBox,tradeOwnerDetails.getFatherSpouseName());
        enterText(emailIDTextBox, tradeOwnerDetails.getEmailId());
        enterText(tradeOwnerAddressTextBox, tradeOwnerDetails.getTradeOwnerAddress());
    }

    public void entertradeLocationDetails(TradeLocationDetails tradelocationDetails) {
        waitForElementToBeClickable(propertyAssessmentNumberTextBox, webDriver);
        enterText(propertyAssessmentNumberTextBox, tradelocationDetails.getpropertyAssessmentNumber());
        propertyAssessmentNumberTextBox.sendKeys(Keys.TAB);
        waitForElementToBeClickable(OwnershipTypeDropBox, webDriver);
        new Select(OwnershipTypeDropBox).selectByVisibleText(tradelocationDetails.getownershipType());
    }

    public void entertradeDetails(TradeDetails tradedetails) {
        waitForElementToBeClickable(tradeTitleTextBox, webDriver);
        enterText(tradeTitleTextBox, tradedetails.getTradeTitle());
        new Select(TradeTypeDropBox).selectByVisibleText(tradedetails.gettradeType());
        new Select(TradeCategoryDropBox).selectByVisibleText(tradedetails.getTradeCategory());

//       Search value from DropDown by searching value using Search field.

        try {
            waitForElementToBeClickable(tradeSubCategoryDropBox, webDriver);
            tradeSubCategoryDropBox.click();
        } catch (StaleElementReferenceException e) {
            WebElement element = webDriver.findElement(By.id("select2-subCategory-container"));
            element.click();
        }
        waitForElementToBeVisible(searchBox, webDriver);
        searchBox.sendKeys(tradedetails.gettradeSubCategory());
        WebElement element = webDriver.findElement(By.cssSelector(".select2-results__option.select2-results__option--highlighted"));
        element.click();

        enterText(TradeAreaWeightOfPremises, tradedetails.gettradeAreaWeightOfPremises());
        enterText(remarksTextBox, tradedetails.getremarks());
        enterText(tradeCommencementDateTextBox, tradedetails.gettradeCommencementDate());
        waitForElementToBeClickable(saveButton, webDriver);
        saveButton.click();
    }


    public String getApplicationNumber() {
        List<WebElement> elements=webDriver.findElements(By.cssSelector(".col-sm-3.col-xs-6.add-margin.view-content"));
        String appNum = elements.get(0).getText();
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
        return appNum;
        }

    public void enterApplicationNumber(String applicationNumber) {
        waitForElementToBeVisible(applicationNumberTextBox, webDriver);
        enterText(applicationNumberTextBox , applicationNumber);
    }


    public void clickOnSearchButton() {

        waitForElementToBeClickable(searchButton, webDriver);
        searchButton.click();
    }

    public void chooseCollectFees() {
        waitForElementToBeClickable(collectFeeDropBox , webDriver);
        new Select(collectFeeDropBox).selectByVisibleText("Collect Fees");
        switchToNewlyOpenedWindow(webDriver);
    }


    public void chooseToPayTaxOfApplicationNumber() {
        switchToNewlyOpenedWindow(webDriver);

        waitForElementToBeClickable(amountTextBox , webDriver);
        enterText(amountTextBox , totalAmountReceived.getAttribute("value").split("\\.")[0]);

        WebElement element = webDriver.findElement(By.id("button2"));
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        executor.executeScript("arguments[0].click();", element);

        webDriver.close();
        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
        webDriver.navigate().refresh();

    }

    public void chooseOldTradeLicnese(String assessmentNumber) {

        waitForElementToBeClickable(oldTradeLicense, webDriver);
        enterText(oldTradeLicense,assessmentNumber);
    }

    public void enterlegencyDetails() {

        List<WebElement> elements=webDriver.findElements(By.cssSelector(".form-control.patternvalidation.feeamount"));

            enterText(elements.get(5),"200");
            webDriver.switchTo().activeElement();
            jsClick(webDriver.findElement(By.cssSelector(".btn.btn-primary")), webDriver);
    }

    public void enterDetailsForClosure(LicenseClosureDetails closureDetails) {
        waitForElementToBeClickable(statusSelect, webDriver);
        new Select(statusSelect).selectByVisibleText(closureDetails.getStatusDetails());
        new Select(tradeCategorySelect).selectByVisibleText(closureDetails.getTradeCategory());
        searchButton.click();
        waitForElementToBeClickable(collectFeeDropBox , webDriver);
        new Select(collectFeeDropBox).selectByVisibleText("Closure");
        switchToNewlyOpenedWindow(webDriver);
    }

    public String getLicenseNumber() {
        List<WebElement> elements= webDriver.findElements(By.cssSelector(".col-xs-3.add-margin.view-content"));
        return elements.get(11).getText();
    }

    public void closeAcknowledgement() {
        webDriver.close();
        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void closureApproval() {
        enterText(approverRemarkTextBox, "Approved");
        approveButton.click();
    }

    public void enterApprovalDetails(ApprovalDetails approvalDetails) {
        new Select(approverDepartmentSelection).selectByVisibleText(approvalDetails.getApproverDepartment());
        await().atMost(10, SECONDS).until(() -> new Select(approverDesignationSelection).getOptions().size() > 1);
        new Select(approverDesignationSelection).selectByVisibleText(approvalDetails.getApproverDesignation());
        await().atMost(10, SECONDS).until(() -> new Select(approverSelection).getOptions().size() > 1);
        new Select(approverSelection).selectByVisibleText(approvalDetails.getApprover());
        enterText(approverCommentsTextBox, approvalDetails.getApproverRemarks());
    }

    public void forward() {
        forwardButton.click();
    }

    public void generateLicenseCertificate() {
        waitForElementToBeClickable(generateCertificateButton, webDriver);
        generateCertificateButton.click();

    }

    public void enterApplicationNumberReadingFromExcel(SearchTradeDetails searchId) {
        waitForElementToBeVisible(applicationNumberTextBox, webDriver);
        enterText(applicationNumberTextBox, searchId.getApplicationNumber());
    }
//    public void checkNoOfRecords() {
//        waitForElementToBeVisible(recordsFound, webDriver);
//        int noOfRecords = Integer.parseInt(recordsFound.getText());
//
//        if (noOfRecords > 0) {
//            System.out.println("Records Founds:" + noOfRecords);
//        } else
//            System.out.println("No records founds");
//    }
}

