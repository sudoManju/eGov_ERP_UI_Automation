package pages.tradeLicense;

import entities.ptis.ApprovalDetails;
import entities.tradeLicense.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    @FindBy(xpath = ".//*[@id='button'][@class='button']")
    private WebElement closeLicensePage;

    @FindBy(id = "licenseNumber")
    private WebElement licenseNumberBox;

    @FindBy(xpath = ".//*[@id='searchForm']/div[2]/div/button[3]")
    private WebElement closeSearch;

    @FindBy(id = "inactive")
    private WebElement includeInactiveElementCheck;


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
        includeInactiveElementCheck.click();
        searchButton.click();
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

    public void chooseOldTradeLicense() {

        waitForElementToBeClickable(oldTradeLicense, webDriver);
        enterText(oldTradeLicense,get6DigitRandomInt());
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

    public void applicationApproval() {


        WebElement element = webDriver.findElement(By.id("boundary"));
        waitForElementToBeVisible(element , webDriver);
//        System.out.println("=================="+element.getText());
        for (int i = 0 ; i<=2 ; i++) {
            if (element.getText().equals(null)) {
                WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
                webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("boundary")));
            } else {
                waitForElementToBeClickable(approverRemarkTextBox , webDriver);
                enterText(approverRemarkTextBox, "Approved");
                approveButton.click();
                break;
            }
        }
    }


    public void forward() {
        forwardButton.click();
    }

    public void generateLicenseCertificate() {

        WebElement element = webDriver.findElement(By.id("boundary"));
        waitForElementToBeVisible(element , webDriver);
//        System.out.println("=================="+element.getText());
        for (int i = 0 ; i<=2 ; i++) {
            if (element.getText().equals(null)) {
                WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
                webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("boundary")));
            } else {
                waitForElementToBeClickable(generateCertificateButton, webDriver);
                generateCertificateButton.click();
                switchToNewlyOpenedWindow(webDriver);
                break;
            }
        }
        webDriver.close();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void enterApplicationNumberReadingFromExcel(SearchTradeDetails searchId) {
        waitForElementToBeVisible(applicationNumberTextBox, webDriver);
        enterText(applicationNumberTextBox, searchId.getApplicationNumber());
        searchButton.click();
    }

    public String getLegacyLicenseNumber() {
        String licenseNum= webDriver.findElement(By.xpath(".//*[@id='viewTradeLicense']/div[8]/div[1]/div[2]")).getText();
        closeLicensePage.click();
        switchToPreviouslyOpenedWindow(webDriver);
        return licenseNum;
    }

    public void enterLicenseNumber(String licenseNumber) {
        enterText(licenseNumberBox, licenseNumber);
        searchButton.click();
    }

    public void chooseToRenewLicense() {
        waitForElementToBeClickable(collectFeeDropBox , webDriver);
        new Select(collectFeeDropBox).selectByVisibleText("Renew License");
        switchToNewlyOpenedWindow(webDriver);
        saveButton.click();
        closeButton.click();
        switchToNewlyOpenedWindow(webDriver);
        searchButton.click();
    }

    public void checkNoOfRecords() {
        int numOfRecords= webDriver.findElements(By.className("dropchange")).size();
        if(numOfRecords>0)
        {
            System.out.println("--------Number of records = "+numOfRecords);
        }
        else
        {
            System.out.println("--------No records found");
        }
        closeSearch.click();
        switchToPreviouslyOpenedWindow(webDriver);
    }

    public void enterStatus(String status) {
        waitForElementToBeClickable(statusSelect, webDriver);
        new Select(statusSelect).selectByVisibleText(status);
        searchButton.click();
        WebElement show= webDriver.findElement(By.xpath(".//*[@id='tblSearchTrade_length']/label/select"));
        waitForElementToBeClickable(show, webDriver);
        new Select(show).selectByVisibleText("100");
    }

    public void closureApproval() {
        waitForElementToBeClickable(approverRemarkTextBox , webDriver);
        enterText(approverRemarkTextBox, "Approved");
        approveButton.click();
    }
}

