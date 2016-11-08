package pages.collections;

import entities.collections.ChequeDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class CollectionsPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "chequeradiobutton")
    private WebElement chequeModeofPaymentRadio;


    @FindBy(id = "instrumentChequeNumber")
    private WebElement chequeNumberTextBox;

    @FindBy(id = "instrumentDate")
    private WebElement chequeDateTextBox;

    @FindBy(id = "bankName")
    private WebElement bankNameInput;

    @FindBy(id = "instrumentChequeAmount")
    private WebElement amountTextBox;

    @FindBy(id = "paidBy")
    private WebElement paidByTextBox;

    @FindBy(id = "totalamounttobepaid")
    private WebElement amountToBePaidLabel;


    public CollectionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterChequeDetails(ChequeDetails chequeDetails) {
        waitForElementToBeClickable(chequeModeofPaymentRadio, driver);
        jsClick(chequeModeofPaymentRadio, driver);
//        chequeModeofPaymentRadio.click();
        chequeNumberTextBox.sendKeys(chequeDetails.getChequeNumber());
        chequeDateTextBox.sendKeys(chequeDetails.getChequeDate());
        chequeDateTextBox.sendKeys(Keys.TAB);
        bankNameInput.sendKeys(chequeDetails.getBankName());

        driver.findElement(By.id("bankcodescontainer"))
                .findElements(By.cssSelector("ul li"))
                .get(0).click();

//        amountTextBox.sendKeys(amountToBePaidLabel.getText());
        paidByTextBox.sendKeys(chequeDetails.getPaidBy());
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript(String.format("document.getElementById('instrumentChequeAmount').value = '%s';", amountToBePaidLabel.getText()));


        enterText(amountTextBox, amountToBePaidLabel.getAttribute("value").split("\\.")[0]);
    }
}
