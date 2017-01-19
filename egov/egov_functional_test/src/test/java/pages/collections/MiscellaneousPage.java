package pages.collections;

import entities.collections.PaymentMethod;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by soumyaghosh on 01/12/16.
 */
public class MiscellaneousPage extends BasePage{
    private WebDriver driver;
    @FindBy(id ="paidBy")
    private WebElement paidByTextBox;

    @FindBy(id = "referenceDesc")
    private WebElement narrationTextBox;

    @FindBy(id = "payeeAddress")
    private WebElement payeeAddressTextBox;

    @FindBy(id = "serviceCategoryid")
    private WebElement serviceCategoryDropDown;

    @FindBy(id = "serviceId")
    private WebElement serviceTypeIDropDown;

    @FindBy(css = "input[type='text'][id='billCreditDetailslist[0].creditAmountDetail']")
    private  WebElement receiptHeadsAmount;

    @FindBy(css = "input[type='text'][name='instrumentProxyList[0].instrumentNumber']")
    private WebElement chequeNumberTextBox;

    @FindBy(css = "input[type='radio'][id='chequeradiobutton']")
    private WebElement chequeModeRadioButton;

    @FindBy(css = "input[type='text'][id='instrumentDate']")
    private WebElement chequeDateTextBox;

    @FindBy(css = "input[type='text'][name='instrumentProxyList[0].bankId.name']")
    private WebElement bankNameTextBox;

    @FindBy(xpath = ".//*[@id='instrumentChequeAmount']")
    private WebElement amountTextBox;

    @FindBy(css = "input[type='submit'][value='Pay']")
    private WebElement payButton;

    public MiscellaneousPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterMiscellaneousDetails() {

        paidByTextBox.sendKeys("Bimal Kumar");
        narrationTextBox.sendKeys("Narration");
        payeeAddressTextBox.sendKeys("Bangalore");
        new Select(serviceCategoryDropDown).selectByVisibleText("Entry Fees");

        waitForElementToBeVisible(serviceTypeIDropDown , driver);
        new Select(serviceTypeIDropDown).getOptions().get(1);
//        new Select(serviceTypeIDropDown).selectByIndex(1);

        for (int i = 0; i < 4; i++) {
            if (receiptHeadsAmount.isDisplayed())
                try {
                    receiptHeadsAmount.click();
                    receiptHeadsAmount.clear();
                    receiptHeadsAmount.sendKeys("655");
                } catch (StaleElementReferenceException e) {
                   WebElement element1 = driver.findElement(By.cssSelector("input[type='text'][id='billCreditDetailslist[0].creditAmountDetail']"));
                    element1.click();
                    element1.clear();
                    element1.sendKeys("655");
                }
          }
    }

    public void enterPaymentDetails(PaymentMethod paymentmethod, String mode) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());

        switch (mode){

            case "cash":

                break;

            case "cheque":

                waitForElementToBeClickable(chequeModeRadioButton,driver);
                chequeModeRadioButton.click();

                waitForElementToBeVisible(chequeNumberTextBox,driver);
                chequeNumberTextBox.sendKeys(paymentmethod.getChequeNumber());

                waitForElementToBeClickable(chequeDateTextBox,driver);
                chequeDateTextBox.sendKeys(date);

                waitForElementToBeClickable(bankNameTextBox,driver);
                bankNameTextBox.sendKeys(paymentmethod.getBankName());
                await().atMost(10, SECONDS).until(() -> driver.findElement(By.id("bankcodescontainer"))
                        .findElements(By.cssSelector("ul li"))
                        .get(0).click());

                waitForElementToBeClickable(amountTextBox,driver);
                amountTextBox.sendKeys("655");
        }

        waitForElementToBeClickable(payButton,driver);
        payButton.click();
    }
}
