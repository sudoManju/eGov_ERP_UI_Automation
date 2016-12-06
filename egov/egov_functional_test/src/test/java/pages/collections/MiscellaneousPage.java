package pages.collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

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

    @FindBy(id = "billCreditDetailslist[0].creditAmountDetail")
    private  WebElement receiptHeadsAmount;

    @FindBy(id = "button2")
    private WebElement payButton;





    public void enterMiscellaneousDetails() {

        paidByTextBox.sendKeys("Bimal Kumar");
        narrationTextBox.sendKeys("Narration");
        payeeAddressTextBox.sendKeys("Bangalore");
        new Select(serviceCategoryDropDown).selectByVisibleText("Entry Fees");
//        waitForElementToBeClickable(serviceTypeIDropDown,driver);
        if(serviceTypeIDropDown.isDisplayed())
        new Select(serviceTypeIDropDown).selectByIndex(1);
        if(receiptHeadsAmount.isDisplayed())
        receiptHeadsAmount.click();
        receiptHeadsAmount.clear();
        receiptHeadsAmount.sendKeys("655");
        payButton.click();







    }
}
