package pages.works;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.Calendar;
import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by manjunatha-lap on 16/12/2016.
 */
public class ContractorPage extends BasePage
{
    private WebDriver driver;

    @FindBy(id = "searchtree")
    private WebElement searchTreeTextBox;

    @FindBy(linkText = "Create Contractor")
    private WebElement createContractorLink;

    @FindBy(linkText = "View/Modify Contractor")
    private WebElement viewContractorlink;

    @FindBy(id = "code")
    private WebElement contractorCode;

    @FindBy(id = "name")
    private WebElement contractorName;

    @FindBy(className = "yui-dt-dropdown")
    private WebElement department;

    @FindBy(id = "statusDescyui-rec0")
    private WebElement status;

    @FindBy(name = "actionContractorDetails[0].validity.startDate")
    private WebElement fromDate;

    @FindBy(id = "saveButton")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id='department']")
    private WebElement contractorDepartment;

    @FindBy(id = "status")
    private WebElement contractorStatus;

    @FindBy(id = "searchButton")
    private WebElement contractorSearchButton;

    @FindBy(css = "input[id='closeButton'][value='Close']")
    private WebElement closeButton;

    @FindBy(id = "contractorName")
     private WebElement searchContractorNameBox;

    @FindBy(id = "currentRow")
    private WebElement contractorsListTable;

    @FindBy(css = "input[value='Modify'][type='submit']")
    private WebElement modifyButton;

    @FindBy(id = "name")
    private WebElement modifyNameBox;

    String min = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
    String hour = String.valueOf(Calendar.getInstance().get(Calendar.HOUR));

    public ContractorPage(WebDriver driver) {
        this.driver = driver;
    }

    private void searchFor(String value) {
        enterText(searchTreeTextBox, value);
    }

    public void chooseToCreateContractor()
    {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Contractor");
        waitForElementToBeVisible(createContractorLink, driver);

        createContractorLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    public void entersContractorMasterData()
    {
        waitForElementToBeClickable(contractorCode, driver);

        String code = (min+hour);
        contractorCode.sendKeys(code);
        waitForElementToBeClickable(contractorName, driver);
        contractorName.sendKeys("testers");
        waitForElementToBeClickable(department, driver);
        new Select(department).selectByVisibleText("ENGINEERING");
        waitForElementToBeClickable(status, driver);
        new Select(status).selectByVisibleText("Active");
        waitForElementToBeClickable(fromDate, driver);
        fromDate.sendKeys("20/12/2016");

        waitForElementToBeClickable(saveButton,driver);
        saveButton.click();
    }



    public void viewContractor()
    {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("View/Modify Contractor");
        waitForElementToBeVisible(viewContractorlink, driver);
        viewContractorlink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void searchContractor()
    {
        waitForElementToBeClickable(searchContractorNameBox,driver);
        searchContractorNameBox.sendKeys("testers");
        waitForElementToBeClickable(contractorSearchButton, driver);
        contractorSearchButton.click();
    }


    public void close() {
        waitForElementToBeVisible(closeButton,driver);
        closeButton.click();

        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void select() {
        waitForElementToBeVisible(contractorsListTable,driver);
        List<WebElement> totalRows = contractorsListTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("Rows:"+totalRows.size());
        WebElement requiredRow = totalRows.get(totalRows.size()-1);
        WebElement element = requiredRow.findElements(By.tagName("td")).get(0).findElement(By.id("radio"));
        jsClick(element,driver);

        waitForElementToBeVisible(modifyButton,driver);
        modifyButton.click();
    }

    public void modify() {

        waitForElementToBeVisible(modifyNameBox,driver);
        modifyNameBox.click();
        modifyNameBox.clear();
        modifyNameBox.sendKeys("auto-testers");

        waitForElementToBeVisible(modifyButton,driver);
        modifyButton.click();
    }
}
