package pages.wcms;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WaterChargeCollectionsPage extends WaterChargeManagementPage {

    @FindBy(id = "totalamounttobepaid")
    private WebElement totalAmount;

    @FindBy(id = "cashradiobutton")
    private WebElement cashRadio;

    @FindBy(id = "instrHeaderCash.instrumentAmount")
    private WebElement amountToBePaidTextBox;

    @FindBy(id = "chequeradiobutton")
    private WebElement chequeModeOfPaymentRadio;

    @FindBy(id = "instrumentChequeNumber")
    private WebElement chequeNumberTextBox;

    @FindBy(id = "instrumentDate")
    private WebElement chequeDateTextBox;

    @FindBy(id = "bankName")
    private WebElement bankNameInput;

    @FindBy(css = "input[id='instrumentChequeAmount'][type='text']")
    private WebElement payAmountBoxForCheque;

    @FindBy(id = "ddradiobutton")
    private WebElement ddModeOfPaymentRadio;

    @FindBy(css = "input[type='submit'][id='button2']")
    private WebElement button2;

    @FindBy(name = "assessmentNum")
    private WebElement onlineConsumerCode;

    @FindBy(id = "assessmentform_search")
    private WebElement onlineSearchApplication;

    @FindBy(css = "[name='action']")
    private WebElement onlinePayButton;

    @FindBy(className = "justbold")
    private List<WebElement> totalOnlineAmount;

    @FindBy(id = "paymentAmount")
    private WebElement totalOnlineAmountToBePaid;

    @FindBy(name = "radioButton1")
    private WebElement axisBankRadio;

    @FindBy(id = "checkbox")
    private WebElement termsAndConditionsCheckBox;

    @FindBy(xpath = "html/body/center/table[6]/tbody/tr[3]/td/table/tbody/tr/td[3]/a/img")
    private WebElement masterCardImage;

    @FindBy(id = "CardNumber")
    private WebElement cardNumber;

    @FindBy(id = "CardMonth")
    private WebElement cardMonth;

    @FindBy(id = "CardYear")
    private WebElement cardYear;

    @FindBy(id = "Securecode")
    private WebElement cvvNumber;

    @FindBy(id = "Paybutton")
    private WebElement onlineCardPaymentButton;

    @FindBy(css = "div[id='paymentInfo']")
    private WebElement onlinePaymentSuccessMessage;

    @FindBy(id = "btnGenerateReceipt")
    private WebElement onlineGenerateReceipt;

    @FindBy(id = "buttonClose")
    private WebElement closeReceiptButton;

    private String message;

    public WaterChargeCollectionsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void paymentWithMode(String mode) {

        waitForElementToBeVisible(totalAmount, webDriver);
        String amount = totalAmount.getAttribute("value");
        String actualAmount = amount.split("\\.")[0];

        switch (mode) {

            case "cash":

                jsClick(cashRadio, webDriver);
                waitForElementToBeClickable(amountToBePaidTextBox, webDriver);
                amountToBePaidTextBox.sendKeys(actualAmount);

                break;

            case "cheque":

                jsClick(chequeModeOfPaymentRadio, webDriver);

                enterText(chequeNumberTextBox, "123456", webDriver);
                enterDate(chequeDateTextBox, getPastDate(1), webDriver);
                enterText(bankNameInput, "102", webDriver);

                webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                await().atMost(10, SECONDS).until(() -> webDriver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                bankNameInput.sendKeys(Keys.TAB);

                enterText(payAmountBoxForCheque, actualAmount, webDriver);
                break;

            case "dd":

                jsClick(ddModeOfPaymentRadio, webDriver);

                enterText(chequeNumberTextBox, "123456", webDriver);
                enterDate(chequeDateTextBox, getPastDate(1), webDriver);
                enterText(bankNameInput, "102", webDriver);

                webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                await().atMost(10, SECONDS).until(() -> webDriver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                enterText(payAmountBoxForCheque, actualAmount, webDriver);
                break;

        }
        jsClick(button2, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void onlinePaymentLink() {
        webDriver.navigate().to(getEnvironmentURL() + "/ptis/citizen/search/search-searchForm.action#no-back-button");
    }

    public void enterOnlineConsumerNumber(String consumerNumber) {
        enterText(onlineConsumerCode, consumerNumber, webDriver);
        clickOnButton(onlineSearchApplication, webDriver);
    }

    public void clickOnOnlinePayButton() {
        clickOnButton(onlinePayButton, webDriver);
        await().atMost(20, TimeUnit.SECONDS).until(() -> webDriver.findElements(By.id("updatePaytax")).size() == 1);
        if (webDriver.findElements(By.id("updatePaytax")).size() == 1) {
            enterText(webDriver.findElement(By.cssSelector("[name='mobileNumber']")), 1000 + get6DigitRandomInt(), webDriver);
            waitForElementToBeClickable(webDriver.findElement(By.id("updatePaytax")), webDriver);
            webDriver.findElement(By.id("updatePaytax")).click();
        }
    }

    public void selectBankDetails() {

        String amount = getTextFromWeb(totalOnlineAmount.get(1), webDriver);

        waitForElementToBeClickable(totalOnlineAmountToBePaid, webDriver);
        totalOnlineAmountToBePaid.sendKeys(amount.split("\\.")[0]);
        jsClick(axisBankRadio, webDriver);

        clickOnButton(termsAndConditionsCheckBox, webDriver);
        clickOnButton(button2, webDriver);

        clickOnButton(masterCardImage, webDriver);
    }

    public void enterCardDetails() {

        waitForElementToBeClickable(cardNumber, webDriver);
        cardNumber.sendKeys("5457210001000019");

        waitForElementToBeClickable(cardMonth, webDriver);
        cardMonth.sendKeys("12");

        waitForElementToBeClickable(cardYear, webDriver);
        cardYear.sendKeys("25");

        waitForElementToBeClickable(cvvNumber, webDriver);
        cvvNumber.sendKeys("123");

        clickOnButton(onlineCardPaymentButton, webDriver);
    }

    public String onlinePaymentSuccess() {
        await().atMost(30, SECONDS).until(() -> webDriver.findElements(By.cssSelector("div[id='paymentInfo']")).size() == 1);
        message = getTextFromWeb(onlinePaymentSuccessMessage, webDriver);
        return message;
    }

    public void onlineGenerateReceipt() {

        clickOnButton(onlineGenerateReceipt, webDriver);
        waitForElementToBeClickable(closeReceiptButton, webDriver);
        if (closeReceiptButton.isDisplayed()) {
            webDriver.close();
        }
    }
}
