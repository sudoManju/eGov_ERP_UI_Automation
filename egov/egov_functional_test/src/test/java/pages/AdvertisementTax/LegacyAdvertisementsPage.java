package pages.AdvertisementTax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Created by karthik on 12/1/17.
 */
public class LegacyAdvertisementsPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "pendingTax")
    private WebElement pendingTaxTextBox;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(xpath = ".//*[@id='advertisementSuccessform']/div/div/div")
    private WebElement creationMsg;

    @FindBy(linkText = "Close")
    private WebElement closeLink;

    @FindBy(css = "input[id='permissionNumber'][type='text']")
    private WebElement permissionNumberTextBox;

    @FindBy(id = "search-update")
    private WebElement searchButton;

    @FindBy(xpath = ".//*[@id='search-update-result-table']/tbody/tr/td[6]/span[1]/i")
    private WebElement updateButton;

    @FindBy(css = "input[id='advertisement.taxPaidForCurrentYear1'][type='radio']")
    private WebElement taxForYearYesRadioButton;

    public LegacyAdvertisementsPage (WebDriver driver){
        this.driver = driver;
    }

    public void enterArrearsTaxDetails() {

        waitForElementToBeClickable(pendingTaxTextBox,driver);
        pendingTaxTextBox.sendKeys("1000");
    }

    public String submit(){

        waitForElementToBeClickable(submitButton,driver);
        submitButton.click();

        String number = creationMsg.getText();
        String num = number.substring(number.lastIndexOf(" ")+1);
        System.out.println("\n"+num);

        return num;
    }

    public String successMessage() {
        waitForElementToBeVisible(creationMsg,driver);
        return creationMsg.getText();
    }

    public void close(){
         waitForElementToBeClickable(closeLink,driver);
         closeLink.click();

        switchToPreviouslyOpenedWindow(driver);
    }

    public void searchFile(String applicationNumber) {

        waitForElementToBeClickable(permissionNumberTextBox,driver);
        permissionNumberTextBox.sendKeys(applicationNumber);

        waitForElementToBeClickable(searchButton,driver);
        searchButton.click();
    }

    public void updateLegacyAd(){
        waitForElementToBeClickable(updateButton,driver);
        updateButton.click();

        switchToNewlyOpenedWindow(driver);
    }

    public void update() {
       waitForElementToBeClickable(taxForYearYesRadioButton,driver);
       taxForYearYesRadioButton.click();

       waitForElementToBeClickable(pendingTaxTextBox,driver);
       pendingTaxTextBox.clear();
       pendingTaxTextBox.sendKeys("0");

    }
}
