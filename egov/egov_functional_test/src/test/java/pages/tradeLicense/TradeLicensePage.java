package pages.tradeLicense;

import entities.tradeLicense.LegencyDetails;
import entities.tradeLicense.TradeDetails;
import entities.tradeLicense.TradeLocationDetails;
import entities.tradeLicense.TradeOwnerDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import static com.jayway.awaitility.Awaitility.await;
import static java.lang.Enum.valueOf;
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

    @FindBy (id = "applicantName")
    private WebElement tradeOwnerNameTextBox;

    @FindBy (id = "fatherOrSpouseName")
    private WebElement fatherSpouseNameTextBox;

    @FindBy (id = "emailId")
    private WebElement emailIDTextBox;

    @FindBy (id = "licenseeAddress")
    private WebElement tradeOwnerAddressTextBox;

    @FindBy (id = "propertyNo")
    private WebElement propertyAssessmentNumberTextBox;

    @FindBy (id = "ownershipType")
    private WebElement OwnershipTypeDropBox;

    @FindBy (id = "nameOfEstablishment")
    private WebElement tradeTitleTextBox;

    @FindBy (id = "buildingType")
    private WebElement TradeTypeDropBox;

    @FindBy (id = "category")
    private WebElement TradeCategoryDropBox;

    @FindBy (id = "select2-subCategory-container")
    private WebElement tradeSubCategoryDropBox;

    @FindBy (id = "tradeArea_weight")
    private WebElement TradeAreaWeightOfPremises;

    @FindBy (id = "remarks")
    private WebElement remarksTextBox;

    @FindBy (id = "startDate")
    private WebElement tradeCommencementDateTextBox;

    @FindBy (id = "btnsave")
    private WebElement saveButton;

    @FindBy (className = "select2-search__field")
    private WebElement searchBox;

    @FindBy (id = "close")
    private WebElement closeButton;

    @FindBy (id = "applicationNumber")
    private WebElement applicationNumberTextBox;

    @FindBy (id = "searchtree")
    private WebElement searchTreeBox;

    @FindBy (id = "btnsearch")
    private WebElement searchButton;

    @FindBy (id = "recordActions")
    private WebElement collectFeeDropBox;

    @FindBy (id = "instrHeaderCash.instrumentAmount")
    private WebElement amountTextBox;

    @FindBy (id = "totalamounttobepaid")
    private WebElement totalAmountReceived;

//    @FindBy (id = "button2']")
//    private WebElement tradePayButton;

    @FindBy (id = "oldLicenseNumber")
    private WebElement oldTradeLicense;

    @FindBy (id = "buttonClose")
    private WebElement printClose;

    @FindBy (name = "legacyInstallmentwiseFees[201104]")
    private WebElement amount1;




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

//      To Copy Application number


        public void copyApplicationNumber() {

            WebElement element= webDriver.findElement(By.cssSelector(".col-sm-3.col-xs-6.add-margin.view-content"));
            tradeApplicationNumber= element.getText() ;

            waitForElementToBeVisible(closeButton, webDriver);
            closeButton.click();
            await().atMost(5, SECONDS).until(() -> webDriver.getWindowHandles().size() == 1);
            for (String winHandle : webDriver.getWindowHandles()) {
                webDriver.switchTo().window(winHandle);
            }

            waitForElementToBeClickable(searchTreeBox, webDriver);
            searchTreeBox.clear();

    }

//   paste the coppied code
    public void enterApplicationNumber() {
        waitForElementToBeVisible(applicationNumberTextBox, webDriver);
        String number = tradeApplicationNumber;
        enterText(applicationNumberTextBox , number);
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

        waitForElementToBeClickable(printClose, webDriver);
        printClose.click();

        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();

        switchToNewlyOpenedWindow(webDriver);
        webDriver.close();

    }

    public void chooseOldTradeLicnese() {

        waitForElementToBeClickable(oldTradeLicense, webDriver);
        enterText(oldTradeLicense,"TL/08373/2016");
    }
   // enterText(aadhaarNumberTextBox, tradeOwnerDetails.getAadhaarNumber());

    public void enterlegencyDetails(LegencyDetails legencyDetails) {
        waitForElementToBeClickable(amount1 , webDriver);
        amount1.clear();
        enterText(amount1, legencyDetails.getAmount1());
    }
}

