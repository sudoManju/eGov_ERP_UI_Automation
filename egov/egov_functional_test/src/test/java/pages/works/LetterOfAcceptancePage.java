package pages.works;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import javax.swing.text.html.CSS;
import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by manjunatha-lap on 22/12/2016.
 */
public class LetterOfAcceptancePage extends BasePage
{
    private WebDriver driver;
    public LetterOfAcceptancePage(WebDriver driver){this.driver=driver;}

    @FindBy(id = "fileNumber")
    private WebElement fileNumber;

    @FindBy(id = "fileDate")
    private WebElement fileDate;

    @FindBy(className = "caret")
    private WebElement tenderPer;

    @FindBy(id = "tenderFinalizedPercentage")
    private WebElement tenderFinalizedPercentage;

    @FindBy(id = "contractorSearch")
    private WebElement firmName;

    @FindBy(css = "div[class='alert text-center'][style='color:green;']")
    private WebElement loaNumber;

//    @FindBy(xpath = "//*[@id='createLetterOfAcceptanceForm']/div[1]/div/div[1]/div[3]/div[6]/div[1]/div/span[2]/i")
//    private WebElement addFirm;
//
//    @FindBy(xpath = "//*[@id='contractorCode']")
//    private WebElement contractorCode;
//
//    @FindBy(id = "department")
//    private WebElement departmentSearch;
//
//    @FindBy(id = "btnsearch")
//    private WebElement contractorSearch;
//
//    @FindBy(xpath = "//*[@id='resultTable']/tbody/tr[1]/td[2]")
//    private WebElement contractorSearchResult;

    @FindBy(id = "contractPeriod")
    private WebElement contractPeriod;

    @FindBy(id = "defectLiabilityPeriod")
    private WebElement defectLiabilityPeriod;

    @FindBy(id = "engineerIncharge")
    private WebElement engineerIncharge;

    @FindBy(id = "save")
    private WebElement saveButton;

    @FindBy(linkText = "View LOA PDF")
    private WebElement viewLOAPDF;

    @FindBy(id = "closeButton")
    private WebElement closeButton;

    @FindBy(linkText = "Close")
    private WebElement closeLink;

    @FindBy(css = "button[id='btnsearch'][type='button']")
    private WebElement searchLOAButton;

    @FindBy(xpath = ".//*[@id='workOrderNumber']")
    private WebElement workOrderNumber;

    @FindBy(xpath = "(//*[@id='resultTable']/tbody/tr/td/input)[1]")
     private WebElement selectLOA;

    @FindBy(id = "btnmodifyloa")
     private WebElement modifyLOAbutton;

    @FindBy(id = "revisedValue")
    private WebElement revisedValue;

    @FindBy(id = "modify")
     private WebElement modifyButton;

    @FindBy(id = "actionDropdown")
     private WebElement actionDropdown;

    @FindBy(xpath = "//*[@id='workOrder']/div[2]/div/a[1]")
     private WebElement closeViewLOA;

    String RandomString = RandomStringUtils.randomAlphanumeric(5).toUpperCase();

    public void enterLOAdetails()
    {
        waitForElementToBeClickable(fileNumber, driver);
        fileNumber.sendKeys(RandomString);
        waitForElementToBeClickable(fileDate, driver);
        fileDate.sendKeys("22/12/2016"+ Keys.TAB);
//        waitForElementToBeClickable(tenderPer, driver);
//        tenderPer.click();
        waitForElementToBeClickable(tenderFinalizedPercentage, driver);
        tenderFinalizedPercentage.sendKeys("12"+Keys.TAB);
        waitForElementToBeClickable(firmName, driver);
        firmName.sendKeys("KMC055");
//        waitForElementToBeClickable(addFirm, driver);
//        addFirm.click();
//        switchToNewlyOpenedWindow(driver);
//        waitForElementToBeClickable(contractorCode, driver);
//        contractorCode.sendKeys("KMC055");
        waitForElementToBeVisible( driver.findElement(By.className("tt-dropdown-menu")),driver);
        WebElement dropdown = driver.findElement(By.className("tt-dropdown-menu"));
        dropdown.click();
//        waitForElementToBeClickable(departmentSearch, driver);
//        new Select(departmentSearch).selectByVisibleText("ENGINEERING");
//        waitForElementToBeClickable(contractorSearch, driver);
//        contractorSearch.click();
//        waitForElementToBeClickable(contractorSearchResult, driver);
//        contractorSearchResult.click();
        waitForElementToBeClickable(contractPeriod, driver);
        contractPeriod.sendKeys("200");
        waitForElementToBeClickable(defectLiabilityPeriod, driver);
        defectLiabilityPeriod.sendKeys("0.5"+Keys.TAB);
        waitForElementToBeClickable(engineerIncharge, driver);
        new Select(engineerIncharge).selectByVisibleText("A.P.Sreenivasulu - Assistant Engineer");
    }

    public String saveAndClose() {
            waitForElementToBeVisible(saveButton, driver);
            saveButton.click();

//            waitForElementToBeClickable(viewLOAPDF, driver);
//            viewLOAPDF.click();

        waitForElementToBeVisible(loaNumber,driver);
        String loaText = loaNumber.getText();
        String workNumber = (loaText.split("\\ ")[5]);

        waitForElementToBeVisible(closeButton, driver);
        closeButton.click();

        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        return workNumber;
    }

    public void searchForLOA(String number) {
        waitForElementToBeClickable(workOrderNumber, driver);
        workOrderNumber.sendKeys(number);
        waitForElementToBeClickable(searchLOAButton, driver);
        searchLOAButton.click();
        waitForElementToBeClickable(actionDropdown, driver);
        new Select(actionDropdown).selectByVisibleText("View LOA");
        switchToNewlyOpenedWindow(driver);
        waitForElementToBeClickable(closeViewLOA, driver);
        closeViewLOA.click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
            waitForElementToBeVisible(closeLink, driver);
            closeLink.click();

            for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void close() {
        waitForElementToBeVisible(closeButton,driver);
        closeButton.click();

        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void searchForLOAModify(String number) {
        waitForElementToBeClickable(workOrderNumber, driver);
        workOrderNumber.sendKeys(number);
        waitForElementToBeClickable(searchLOAButton, driver);
        searchLOAButton.click();
        waitForElementToBeClickable(selectLOA, driver);
        selectLOA.click();
        waitForElementToBeClickable(modifyLOAbutton, driver);
        modifyLOAbutton.click();
        waitForElementToBeClickable(revisedValue, driver);
        revisedValue.sendKeys("8");
        waitForElementToBeClickable(modifyButton, driver);
        modifyButton.click();
        close();
    }
}
