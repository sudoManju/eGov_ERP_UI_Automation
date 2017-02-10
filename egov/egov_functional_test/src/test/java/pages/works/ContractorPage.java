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

    @FindBy(css = "input[type='text'][name='contactPerson']")
    private WebElement modifyNameBox;

    @FindBy(xpath = ".//*[@id='msgsDiv']/ul/li/span")
    private WebElement creationMsg;

    @FindBy(id = "correspondenceAddress")
    private WebElement correspondenceAddress;

    @FindBy(id = "panNumber")
    private WebElement panNumber;

    @FindBy(id = "bank")
    private WebElement bankAction;

    @FindBy(id = "ifscCode")
    private WebElement ifscCode;

    @FindBy(xpath = "//*[@id='exemptionForm']")
     private WebElement exemptionFormAction;

    @FindBy(id = "contactPerson")
     private WebElement contactPerson;

    @FindBy(id = "email")
     private WebElement email;

    @FindBy(id = "mobileNumber")
    private WebElement mobileNumber;

    @FindBy(id = "bankAccount")
     private WebElement bankAccount;

    @FindBy(id = "tinNumber")
     private WebElement tinNumber;

    @FindBy(id = "gradeNameyui-rec0")
    private WebElement contractorClass;

    @FindBy(id = "registrationNumberyui-rec0")
     private WebElement registrationNumber;

    @FindBy(xpath = ".//*[@id='yui-rec0']/td[8]/div/select")
     private WebElement categoryDropDownBox;

    public ContractorPage(WebDriver driver) {
        this.driver = driver;
    }

    private void searchFor(String value) {
        enterText(searchTreeTextBox, value);
    }

    public void chooseToCreateContractor() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Contractor");
        waitForElementToBeVisible(createContractorLink, driver);

        createContractorLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    public String entersContractorMasterData() {
        String Name = "KMC"+get6DigitRandomInt();
        waitForElementToBeClickable(contractorName, driver);
        contractorName.sendKeys(Name);
        waitForElementToBeClickable(correspondenceAddress, driver);
        correspondenceAddress.sendKeys("A P State Agro industries Development Corporation Ltd ATP");
        waitForElementToBeClickable(panNumber, driver);
        waitForElementToBeClickable(contactPerson, driver);
        contactPerson.sendKeys("Name");
        waitForElementToBeClickable(email, driver);
        email.sendKeys(Name+"@egov.org");
        waitForElementToBeClickable(mobileNumber, driver);
        mobileNumber.sendKeys("9988"+get6DigitRandomInt());
        panNumber.sendKeys("PANUM2803P");
        waitForElementToBeClickable(tinNumber, driver);
        tinNumber.sendKeys(get6DigitRandomInt());
        waitForElementToBeClickable(bankAction, driver);
        new Select(bankAction).selectByVisibleText("STATE BANK OF MYSORE");
        waitForElementToBeClickable(ifscCode, driver);
        ifscCode.sendKeys("IFSC"+get6DigitRandomInt());
        waitForElementToBeClickable(bankAccount, driver);
        bankAccount.sendKeys(get6DigitRandomInt());
        waitForElementToBeClickable(exemptionFormAction, driver);
        new Select(exemptionFormAction).selectByVisibleText("EARNEST MONEY DEPOSIT");
        waitForElementToBeClickable(department, driver);
        new Select(department).selectByVisibleText("ENGINEERING");
        waitForElementToBeClickable(registrationNumber, driver);
        registrationNumber.sendKeys("0123"+get6DigitRandomInt());
        waitForElementToBeClickable(categoryDropDownBox,driver);
        new Select(categoryDropDownBox).selectByVisibleText("Transport");
        waitForElementToBeClickable(contractorClass, driver);
        new Select(contractorClass).selectByVisibleText("Class-I");
        waitForElementToBeClickable(status, driver);
        new Select(status).selectByVisibleText("Active");
        waitForElementToBeClickable(fromDate, driver);
        fromDate.sendKeys("31/01/2017");
        waitForElementToBeClickable(saveButton,driver);
        saveButton.click();

        return Name;
    }

    public void viewContractor() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.clear();
        searchFor("View/Modify Contractor");
        waitForElementToBeVisible(viewContractorlink, driver);
        viewContractorlink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void searchContractor(String name)
    {
        waitForElementToBeClickable(searchContractorNameBox,driver);
        searchContractorNameBox.sendKeys(name);
        waitForElementToBeClickable(contractorSearchButton, driver);
        contractorSearchButton.click();
    }

    public void close() {
        waitForElementToBeVisible(closeButton,driver);
        closeButton.click();

        switchToPreviouslyOpenedWindow(driver);
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
        modifyNameBox.sendKeys("Testing");

        waitForElementToBeVisible(modifyButton,driver);
        modifyButton.click();
    }

    public String successMessage() {
        waitForElementToBeVisible(creationMsg,driver);
        String msg =creationMsg.getText();
        System.out.println(msg);
        return msg;
    }
}
