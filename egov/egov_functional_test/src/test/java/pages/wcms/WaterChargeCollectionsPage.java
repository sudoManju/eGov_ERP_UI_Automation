package pages.wcms;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WaterChargeCollectionsPage extends BasePage{

    private WebDriver webDriver;

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

    @FindBy(name = "consumerCode")
    private WebElement onlineConsumerCode;

    @FindBy(id = "searchapprvedapplication")
    private WebElement onlineSearchApplication;

    @FindBy(css = ".btn.btn-xs.btn-secondary.collect-hoardingWiseFee")
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

    @FindBy(id = "paymentInfo")
    private WebElement onlinePaymentSuccessMessage;

    @FindBy(id = "btnGenerateReceipt")
    private WebElement onlineGenerateReceipt;

    @FindBy(id = "buttonClose")
    private WebElement closeReceiptButton;

    private String message;

    public WaterChargeCollectionsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void paymentWithMode(String mode){

        waitForElementToBeVisible(totalAmount, webDriver);
        String amount = totalAmount.getAttribute("value");
        String actualAmount = amount.split("\\.")[0];

        switch (mode){

            case "cash" :

                waitForElementToBeClickable(cashRadio , webDriver);
                jsClick(cashRadio ,webDriver);

                waitForElementToBeClickable(amountToBePaidTextBox,webDriver);
                amountToBePaidTextBox.sendKeys(actualAmount);

                break;

            case "cheque" :

                waitForElementToBeClickable(chequeModeOfPaymentRadio,webDriver);
                jsClick(chequeModeOfPaymentRadio,webDriver);

                waitForElementToBeVisible(chequeNumberTextBox,webDriver);
                chequeNumberTextBox.sendKeys("123456");
                waitForElementToBeClickable(chequeDateTextBox,webDriver);
                chequeDateTextBox.sendKeys("02/01/2017");
                waitForElementToBeClickable(bankNameInput,webDriver);
                bankNameInput.sendKeys("102");

                webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                await().atMost(10, SECONDS).until(() -> webDriver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                bankNameInput.sendKeys(Keys.TAB);

                waitForElementToBeClickable(payAmountBoxForCheque,webDriver);
                payAmountBoxForCheque.sendKeys(actualAmount);

                break;

            case "dd" :

                waitForElementToBeClickable(ddModeOfPaymentRadio,webDriver);
                jsClick(ddModeOfPaymentRadio,webDriver);

                waitForElementToBeVisible(chequeNumberTextBox,webDriver);
                chequeNumberTextBox.sendKeys("123456");
                waitForElementToBeClickable(chequeDateTextBox,webDriver);
                chequeDateTextBox.sendKeys("02/01/2017");
                waitForElementToBeClickable(bankNameInput,webDriver);
                bankNameInput.sendKeys("102");

                webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                await().atMost(10, SECONDS).until(() -> webDriver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                waitForElementToBeClickable(payAmountBoxForCheque,webDriver);
                payAmountBoxForCheque.sendKeys(actualAmount);

                break;

        }
        jsClick(button2, webDriver);
        switchToNewlyOpenedWindow(webDriver);
    }

    public void onlinePaymentLink(){
        webDriver.navigate().to("http://kurnool-uat.egovernments.org/wtms/search/waterSearch/");
    }

    public void enterOnlineConsumerNumber(String consumerNumber){
        waitForElementToBeClickable(onlineConsumerCode , webDriver);
        onlineConsumerCode.sendKeys(consumerNumber);

        waitForElementToBeClickable(onlineSearchApplication , webDriver);
        onlineSearchApplication.click();
    }

    public void clickOnOnlinePayButton(){

        waitForElementToBeClickable(onlinePayButton , webDriver);
        onlinePayButton.click();
    }

    public void selectBankDetails(){

        waitForElementToBeVisible(totalOnlineAmount.get(1) , webDriver);
        String amount = totalOnlineAmount.get(1).getText();

        waitForElementToBeClickable(totalOnlineAmountToBePaid , webDriver);
        totalOnlineAmountToBePaid.sendKeys(amount.split("\\.")[0]);

        waitForElementToBeClickable(axisBankRadio , webDriver);
        jsClick(axisBankRadio ,webDriver);

        waitForElementToBeClickable(termsAndConditionsCheckBox , webDriver);
        termsAndConditionsCheckBox.click();

        waitForElementToBeClickable(button2, webDriver);
        button2.click();

        waitForElementToBeClickable(masterCardImage , webDriver);
        masterCardImage.click();
    }

    public void enterCardDetails(){

        waitForElementToBeClickable(cardNumber , webDriver);
        cardNumber.sendKeys("5123456789012346");

        waitForElementToBeClickable(cardMonth , webDriver);
        cardMonth.sendKeys("05");

        waitForElementToBeClickable(cardYear ,webDriver);
        cardYear.sendKeys("17");

        waitForElementToBeClickable(cvvNumber , webDriver);
        cvvNumber.sendKeys("123");

        waitForElementToBeClickable(onlineCardPaymentButton , webDriver);
        onlineCardPaymentButton.click();
    }

    public String onlinePaymentSuccess(){

        waitForElementToBeVisible(onlinePaymentSuccessMessage , webDriver);
        message = onlinePaymentSuccessMessage.getText();
        return message;
    }

    public void onlineGenerateReceipt(){
        waitForElementToBeClickable(onlineGenerateReceipt , webDriver);
        onlineGenerateReceipt.click();

        waitForElementToBeClickable(closeReceiptButton , webDriver);
        if(closeReceiptButton.isDisplayed()){
            webDriver.close();
        }
    }
}
