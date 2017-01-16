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

    @FindBy(id = "agencyTypeAhead")
    private WebElement agency;

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

    @FindBy(css = "input[value= 'Advertisement'][type= 'radio']")
    private WebElement searchType;

    @FindBy(id = "hoardingnumber")
    private WebElement advertisementNumber;

    @FindBy(id = "search")
    private WebElement searchAdvertisementButton;

    @FindBy(xpath = "//*[@id='adtax_search']/tbody/tr[1]/td[7]/button")
    private WebElement collectButton;

    @FindBy(xpath = "//*[@id='adtax_search']/tbody/tr[4]/td[5]/button")
    private WebElement agencyWisecollectButton;

    @FindBy(css = "input[id='totalamounttobepaid'][type='text']")
     private WebElement totalamounttobepaid;

    @FindBy(id = "instrHeaderCash.instrumentAmount")
     private WebElement amount;

    @FindBy(css = "input[type='submit'][value ='Pay']")
     private WebElement payButton;

    @FindBy(id = "agencyWiseCollectionListSelected[0]")
     private WebElement selectAdvertisement;

    @FindBy(id = "agencysearch")
     private WebElement collectFeeButton;

    @FindBy(css = "input[type ='button'][value='Close']")
     private WebElement closeButton;

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

//         advertisement creating by agency wise

//         waitForElementToBeClickable(agency, driver);
//         new Select(agency).selectByVisibleText("ANAND CINI COMPLEX");

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

        String applicationNumber = (number.split("\\ "))[8];
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

    public void closeMultiple(String url){

        waitForElementToBeClickable(closeButton, driver);
        closeButton.click();

        for (String winHandle : driver.getWindowHandles()) {
            if(driver.switchTo().window(winHandle).getCurrentUrl().equals(url)){
                break;
            }
        }

        close();
    }

    public void searchByAdvertisementNumber(String adervtisementNumber) {
        waitForElementToBeClickable(searchType, driver);
        searchType.click();
        waitForElementToBeClickable(advertisementNumber, driver);
        advertisementNumber.sendKeys(adervtisementNumber);
        waitForElementToBeClickable(searchAdvertisementButton, driver);
        searchAdvertisementButton.click();

    }

    public void collectAdvertisementTax() {
        waitForElementToBeClickable(collectButton, driver);
        collectButton.click();
        switchToNewlyOpenedWindow(driver);
        waitForElementToBeVisible(totalamounttobepaid, driver);
        String AmountNum = totalamounttobepaid.getAttribute("value");
        String Amount = AmountNum.split("\\.")[0];
        System.out.println(Amount);
        waitForElementToBeClickable(amount, driver);
        amount.sendKeys(Amount);
        waitForElementToBeClickable(payButton, driver);
//        payButton.submit();
        jsClick(payButton, driver);
    }

    public void enterAgencyDetails() {
        
    }

    public void searchByAgency() {
        WebElement selectElement = driver.findElement(By.id("categories"));
        Select categories = new Select(selectElement);
        List<WebElement> allOptions = categories.getOptions();
        int count = allOptions.size();
        System.out.println(count);
        categories.selectByIndex(3);
        searchAdvertisementButton.click();
        agencyWisecollectButton.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void selectsAgency() {

        if(selectAdvertisement.isSelected())
        {
            System.out.println("Checkbox is already selected");
        }
        else {
            waitForElementToBeClickable(selectAdvertisement, driver);
            selectAdvertisement.click();
            waitForElementToBeClickable(collectFeeButton, driver);
            collectFeeButton.click();
        }
    }

    public void collectAdvertisementTaxByAgency() {
        waitForElementToBeVisible(totalamounttobepaid, driver);
        String AmountNum = totalamounttobepaid.getAttribute("value");
        String Amount = AmountNum.split("\\.")[0];
        System.out.println(Amount);
        waitForElementToBeClickable(amount, driver);
        amount.sendKeys(Amount);
        waitForElementToBeClickable(payButton, driver);
        jsClick(payButton, driver);
    }
}