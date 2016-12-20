package pages.works;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

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

    @FindBy(id = "statusDescyui-rec0")
    private WebElement fromDate;

    @FindBy(id = "saveButton")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id='department']")
    private WebElement contractorDepartment;

    @FindBy(id = "status")
    private WebElement contractorStatus;

    @FindBy(id = "searchButton")
    private WebElement contractorSearchButton;


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
        contractorCode.sendKeys("1100");
        waitForElementToBeClickable(contractorName, driver);
        contractorName.sendKeys("A");
        waitForElementToBeClickable(department, driver);
        new Select(department).selectByVisibleText("ENGINEERING");
        waitForElementToBeClickable(status, driver);
        new Select(status).selectByVisibleText("Active");
        waitForElementToBeClickable(fromDate, driver);
        fromDate.sendKeys("20/12/2016");

        saveButton.click();
    }

    public void viewContractor()
    {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("View/Modify Contractor");
        waitForElementToBeVisible(viewContractorlink, driver);
        viewContractorlink.click();
    }

    public void searchContractor()
    {
        waitForElementToBeClickable(contractorDepartment, driver);
        new Select(contractorDepartment).selectByVisibleText("ENGINEERING");
        waitForElementToBeClickable(contractorStatus, driver);
        new Select(contractorStatus).selectByVisibleText("Active");
        waitForElementToBeClickable(contractorSearchButton, driver);
        contractorSearchButton.click();
    }


}
