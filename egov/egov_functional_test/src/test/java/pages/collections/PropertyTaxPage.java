package pages.collections;

import entities.collections.PaymentMethod;
import org.apache.bcel.generic.SWITCH;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PropertyTaxPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "assessmentNum")
    private WebElement assessmentNumberTextBox;

    @FindBy(id = "CollectTax")
    private WebElement collectTexButton;

    @FindBy(id = "payTax")
    private WebElement payTaxButton;

    @FindBy(css = "input[id='totalamounttobepaid'][type='text']")
    private WebElement totalAmountToBePaidText;

    @FindBy(css = "input[id='instrHeaderCash.instrumentAmount'][type='text']")
    private WebElement amountPaidByCashTextBox;

    @FindBy(css = "input[value='Pay'][type='submit']")
    private WebElement payButton;

    @FindBy(css = "input[type='radio'][id='chequeradiobutton']")
    private WebElement chequeModeRadioButton;

    @FindBy(css = "input[type='text'][id='instrumentDate']")
    private WebElement chequeDateTextBox;

    @FindBy(css = "input[type='text'][name='instrumentProxyList[0].bankId.name']")
    private WebElement bankNameTextBox;

    @FindBy(css = "input[type='text'][name='instrumentProxyList[0].instrumentNumber']")
    private WebElement chequeNumberTextBox;

    @FindBy(id = "instrumentChequeAmount")
    private WebElement amountPaidByChequeTextBox;

    @FindBy(css = "input[id='ddradiobutton'][type='radio']")
    private WebElement ddRadioButton;

    @FindBy(css = "input[id='bankradiobutton'][type='radio']")
    private WebElement directBankRadioButton;

    @FindBy(id = "bankBranchMaster")
    private WebElement bankNameDropBox;

    @FindBy(id = "accountNumberMaster")
    private WebElement accountNumberDropBox;

    @FindBy(css = "input[id='bankChallanDate'][type='text']")
    private WebElement challanDateTextBox;

    @FindBy(css = "input[id='instrHeaderBank.transactionNumber'][type='text']")
    private WebElement referenceNumberTextBox;

    @FindBy(css = "input[id='instrHeaderBank.instrumentAmount'][type='text']")
    private WebElement directBankAmountTextBox;

    public PropertyTaxPage(WebDriver driver) {
        this.driver = driver;
    }

    public void collectTaxFor(String assessmentNumber) {
        assessmentNumberTextBox.sendKeys(assessmentNumber);
        collectTexButton.click();
    }

    public void payTax() {
        payTaxButton.click();
    }

    public void collectTax(PaymentMethod paymentmethod, String paymentMode) {
        String amount = totalAmountToBePaidText.getAttribute("value");
        String actualAmount = amount.split("\\.")[0];

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());

        switch (paymentMode){

            case "cash":

                amountPaidByCashTextBox.sendKeys(actualAmount);

                break;

            case "cheque":

                waitForElementToBeClickable(chequeModeRadioButton,driver);
                jsClick(chequeModeRadioButton,driver);

                waitForElementToBeVisible(chequeNumberTextBox,driver);
                chequeNumberTextBox.sendKeys(paymentmethod.getChequeNumber());

                waitForElementToBeClickable(chequeDateTextBox,driver);
                chequeDateTextBox.sendKeys(date);

                waitForElementToBeClickable(bankNameTextBox,driver);
                bankNameTextBox.sendKeys(paymentmethod.getBankName());
                await().atMost(10, SECONDS).until(() -> driver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                amountPaidByChequeTextBox.sendKeys(actualAmount);

                break;

            case "dd":

                waitForElementToBeClickable(ddRadioButton,driver);
                jsClick(ddRadioButton,driver);

                waitForElementToBeVisible(chequeNumberTextBox,driver);
                chequeNumberTextBox.sendKeys(paymentmethod.getChequeNumber());

                waitForElementToBeClickable(chequeDateTextBox,driver);
                chequeDateTextBox.sendKeys(date);

                waitForElementToBeClickable(bankNameTextBox,driver);
                bankNameTextBox.sendKeys(paymentmethod.getBankName());
                await().atMost(10, SECONDS).until(() -> driver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                amountPaidByChequeTextBox.sendKeys(actualAmount);

                break;

            case "directBank1":

                waitForElementToBeClickable(directBankRadioButton,driver);
                jsClick(directBankRadioButton,driver);

                waitForElementToBeVisible(referenceNumberTextBox,driver);
                referenceNumberTextBox.sendKeys(paymentmethod.getChequeNumber());

                waitForElementToBeClickable(challanDateTextBox,driver);
                challanDateTextBox.sendKeys(date);

                waitForElementToBeClickable(bankNameDropBox,driver);
                new Select(bankNameDropBox).selectByVisibleText(paymentmethod.getBankName());

                waitForElementToBeClickable(accountNumberDropBox,driver);
                new Select(accountNumberDropBox).selectByVisibleText(paymentmethod.getAccountNumber());

                waitForElementToBeClickable(directBankAmountTextBox,driver);
                directBankAmountTextBox.sendKeys(actualAmount);

                break;

        }

      waitForElementToBeClickable(payButton,driver);
      jsClick(payButton,driver);
    }
}
