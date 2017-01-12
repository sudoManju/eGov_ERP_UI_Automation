package pages.AdvertisementTax;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by karthik on 11/1/17.
 */
public class AdvertisementsPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "category")
    private WebElement categoryBox;

    @FindBy(id = "subCategory")
    private WebElement subCategoryBox;

    @FindBy(id = "rateClass")
    private WebElement classBox;

    @FindBy(id = "revenueInspector")
    private WebElement revenueInspectorBox;

    @FindBy(css = "input[id='advertisement.type2'][type='radio']")
    private WebElement structureTypeRadioButton;

    @FindBy(id = "propertyType")
    private WebElement propertyTypeBox;

    @FindBy(css = "input[id='applicationDate'][type='text']")
    private WebElement applicationDateBox;

    @FindBy(css = "input[id='permissionstartdate'][type='text']")
    private WebElement permissionStartDateBox;

    @FindBy(css = "input[id='permissionenddate'][type='text']")
    private WebElement permissionEndDateBox;

    @FindBy(id = "advertisementParticular")
    private WebElement adParticularTextBox;

    @FindBy(id = "advertisementDuration")
    private WebElement advertismentDurationBox;

    @FindBy(id = "locality")
    private WebElement localityBox;

    @FindBy(id = "address")
    private WebElement addressTextBox;

    @FindBy(css = "input[id='measurement'][type='text']")
    private WebElement measurementTextBox;

    @FindBy(id = "unitOfMeasure")
    private WebElement measurementTypeBox;

    @FindBy(css = "input[id='taxAmount'][type='text']")
    private WebElement taxAmountTextBox;

    @FindBy(id = "ownerDetail")
    private WebElement ownerDetailsTextBox;

    @FindBy(id = "Forward")
    private WebElement forwardButton;

    @FindBy(xpath = ".//*[@id='advertisementSuccessform']/div/div/div")
    private WebElement creationMsg;

    @FindBy(linkText = "Close")
     private WebElement closeLink;

    @FindBy(css = "li[role='presentation'] a[data-now^='Advertisement']")
    private WebElement advertisementLink;

    @FindBy(id = "official_inbox_wrapper")
    private WebElement workListTable;

    @FindBy(id = "approvalComent")
    private WebElement commentBox;

    @FindBy(id = "Approve")
    private WebElement approveButton;

    @FindBy(css = "input[id='hoardingnumber'][type='text']")
    private WebElement hoardingNumberTextBox;

    @FindBy(id = "search")
    private WebElement submitButton;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String date = sdf.format(new Date());

    public AdvertisementsPage(WebDriver driver){
       this.driver = driver;
    }


    public void enterAdvertisementDetails() {
        waitForElementToBeVisible(categoryBox,driver);
        new Select(categoryBox).selectByVisibleText("Hoardings");

        waitForElementToBeClickable(subCategoryBox,driver);
        new Select(subCategoryBox).selectByVisibleText("Foot over Bridges");

        waitForElementToBeClickable(classBox,driver);
        new Select(classBox).selectByVisibleText("A");

        waitForElementToBeClickable(revenueInspectorBox,driver);
        new Select(revenueInspectorBox).selectByVisibleText("N Rajesh");

        waitForElementToBeClickable(structureTypeRadioButton,driver);
        structureTypeRadioButton.click();

        waitForElementToBeClickable(propertyTypeBox,driver);
        new Select(propertyTypeBox).selectByVisibleText("GOVERNMENT");
    }

    public void enterPermissionDetails() {
         waitForElementToBeClickable(applicationDateBox,driver);
         applicationDateBox.sendKeys(date, Keys.TAB);

         waitForElementToBeClickable(adParticularTextBox,driver);
         adParticularTextBox.sendKeys("For elections");

        waitForElementToBeClickable(ownerDetailsTextBox,driver);
        ownerDetailsTextBox.sendKeys("Bhartiya janatha party");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 7);
        String date1 = sdf.format(c.getTime());
        waitForElementToBeClickable(permissionStartDateBox,driver);
        permissionStartDateBox.sendKeys(date1, Keys.TAB);

        c.add(Calendar.DATE, 30);
        String date2 = sdf.format(c.getTime());
        waitForElementToBeClickable(permissionEndDateBox,driver);
        permissionEndDateBox.sendKeys(date2, Keys.TAB);

        waitForElementToBeClickable(advertismentDurationBox,driver);
        new Select(advertismentDurationBox).selectByVisibleText("MONTH");
    }

    public void enterLocalityDetails() {
        waitForElementToBeClickable(localityBox,driver);
        new Select(localityBox).selectByVisibleText("Avanthi Nagar");

        waitForElementToBeClickable(addressTextBox,driver);
        addressTextBox.sendKeys("footover Bridge, mainroad, Avanthi Nagar");
    }


    public void enterStructureDetails() {
        waitForElementToBeClickable(measurementTextBox, driver);
        measurementTextBox.sendKeys("20");

        waitForElementToBeClickable(measurementTypeBox, driver);
        new Select(measurementTypeBox).selectByVisibleText("SQ.FT");

        waitForElementToBeClickable(taxAmountTextBox, driver);
        taxAmountTextBox.sendKeys("10");
    }


    public String forward() {
        waitForElementToBeClickable(forwardButton,driver);
        forwardButton.click();

        String Msg = creationMsg.getText();
        String applicationNumber = Msg.substring(Msg.lastIndexOf(" ")+1);
        System.out.println(applicationNumber);

        return applicationNumber;
    }


    public String successMessage() {

        String msg = creationMsg.getText();
        return msg;
    }

    public void close() {
     waitForElementToBeVisible(closeLink,driver);
     closeLink.click();

     switchToPreviouslyOpenedWindow(driver);
    }


    public void selectAdvertisementTag(String applicationNumber) {
        waitForElementToBeVisible(advertisementLink,driver);
        advertisementLink.click();

        List<WebElement> totalRows = workListTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("\n"+totalRows.size());
        for (WebElement applicationRow : totalRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains(applicationNumber))
                applicationRow.click();
            break;
        }

        switchToNewlyOpenedWindow(driver);
    }


    public void approverComment() {
        waitForElementToBeClickable(commentBox,driver);
        commentBox.sendKeys("Approved");
    }

    public void approve() {
        waitForElementToBeClickable(approveButton,driver);
        approveButton.click();
    }

    public String getAdvertisementNumber() {
        waitForElementToBeVisible(creationMsg,driver);
        String number = creationMsg.getText();

        String applicationNumber = number.substring(number.lastIndexOf(" ")+1);
        System.out.println(applicationNumber);

        return applicationNumber;
    }

    public void searchAndSelect(String number) {
      waitForElementToBeVisible(hoardingNumberTextBox,driver);
      hoardingNumberTextBox.sendKeys(number);

      waitForElementToBeClickable(submitButton,driver);
      submitButton.click();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       WebElement dropDownBox = driver.findElement(By.id("adtaxdropdown"));
       new Select(dropDownBox).selectByVisibleText("View");

       switchToNewlyOpenedWindow(driver);
    }

    public void closeMultipleWindows(String url) {

        waitForElementToBeVisible(closeLink,driver);
        closeLink.click();

        for (String winHandle : driver.getWindowHandles()) {
            if(driver.switchTo().window(winHandle).getCurrentUrl().equals(url)){
                break;
            }
        }

        close();
    }
}
