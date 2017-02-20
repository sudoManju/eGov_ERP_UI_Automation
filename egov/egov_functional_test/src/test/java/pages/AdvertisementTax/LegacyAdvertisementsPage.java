package pages.AdvertisementTax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
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

    @FindBy(xpath = ".//*[@id='search-update-result-table']/tbody/tr/td[7]/span[1]/i")
    private WebElement updateButton;

    @FindBy(css = "input[id='advertisement.taxPaidForCurrentYear1'][type='radio']")
    private WebElement taxForYearYesRadioButton;

    @FindBy(css = "input[id='advertisementNumber'][type='text']")
    private WebElement advertisementNumberBox;

    @FindBy(css = "input[id='hoardingnumber'][type='text']")
    private WebElement hoardingNumberTextBox;

    @FindBy(id = "renewalsearch")
    private WebElement renewalSearchButton;

    @FindBy(id = "renewdropdown")
    private WebElement renewalDropDownBox;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(css = "input[id='taxAmount'][type='text']")
    private WebElement taxAmountTextBox;

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

        boolean isPresent = driver.findElements(By.xpath(".//*[@id='advertisementform']/div/div[2]/div/div/div[22]/div/label")).size() > 0;

        if(isPresent){
            waitForElementToBeClickable(taxAmountTextBox, driver);
            taxAmountTextBox.clear();
            taxAmountTextBox.sendKeys("10");
            System.out.println("success");

            waitForElementToBeClickable(submitButton,driver);
            submitButton.click();
        }

        String number = creationMsg.getText();
        String num = number.split("\\ ")[6];
        String num1 = num.substring(0, num.length()-1);
        System.out.println("\n"+num1);

        return num1;
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

       waitForElementToBeClickable(advertisementNumberBox,driver);
       advertisementNumberBox.sendKeys(applicationNumber);

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

    public void searchFileForRenewal(String applicationNumber) {

        waitForElementToBeVisible(hoardingNumberTextBox,driver);
        hoardingNumberTextBox.sendKeys(applicationNumber);

        waitForElementToBeClickable(renewalSearchButton,driver);
        renewalSearchButton.click();
    }

    public void requestForRenewal() {
        waitForElementToBeVisible(renewalDropDownBox,driver);
        new Select(renewalDropDownBox).selectByVisibleText("Adtax Renewal");

        switchToNewlyOpenedWindow(driver);
    }


}
