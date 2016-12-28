package pages.tradeLicense;

import entities.tradeLicense.TradeDetails;
import entities.tradeLicense.TradeLocationDetails;
import entities.tradeLicense.TradeOwnerDetails;
import org.jsoup.select.Evaluator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

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

    @FindBy (css = "select2-subCategory-container")
    private WebElement tradeSubCategoryDropBox;

    @FindBy (id = "tradeArea_weight")
    private WebElement TradeAreaWeightOfPremises;

    @FindBy (id = "remarks")
    private WebElement remarksTextBox;

    @FindBy (id = "startDate")
    private WebElement tradeCommencementDateTextBox;

    @FindBy (id = "btnsave")
    private WebElement saveButton;

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

        waitForElementToBeClickable(tradeSubCategoryDropBox, webDriver);

        new Select(tradeSubCategoryDropBox).selectByVisibleText(tradedetails.gettradeSubCategory());
        new Select(TradeAreaWeightOfPremises).selectByVisibleText(tradedetails.gettradeAreaWeightOfPremises());
        enterText(remarksTextBox, tradedetails.getremarks());
        enterText(tradeCommencementDateTextBox, tradedetails.gettradeCommencementDate());

        waitForElementToBeClickable(saveButton, webDriver);
        saveButton.click();

    }
}

