package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DashboardPage extends BasePage {
    private WebDriver driver;

    @FindBy(id = "searchtree")
    private WebElement searchTreeTextBox;

    @FindBy(linkText = "Create New Property")
    private WebElement createNewPropertyLink;

    @FindBy(linkText = "Data Entry Screen")
    private WebElement dataEntryScreenLink;

    @FindBy(linkText = "Search Property")
    private WebElement searchPropertyLink;

    @FindBy(linkText = "Property Tax")
    private WebElement propertyTaxLink;

    @FindBy(className = "profile-name")
    private WebElement profileNameLink;

    @FindBy(linkText = "Create Spillover Estimate")
    private WebElement createSpilloverEstimate;

    @FindBy(linkText = "Sign Out")
    private WebElement signOutLink;

    @FindBy(id = "official_inbox")
    private WebElement officialInboxTable;

    @FindBy(xpath = "//html/body/div[3]/div[2]/div/ul/li[2]/a")
    private WebElement dataEntryScreenLinkText;

    @FindBy(linkText = "Miscellaneous Receipt")
    private WebElement miscellaneousReceipt;

    @FindBy(linkText = "Apply for New Connection")
    private WebElement applyForNewWaterConnection;

    @FindBy(linkText = "Create Challan")
    private WebElement createChallanLink;

    @FindBy(linkText = "Daily collection report(VLT)")
    private WebElement vltReport;

    @FindBy(linkText = "Data Entry Screen")
    private WebElement dataEntryScreen;

    @FindBy(linkText = "Addition/Alteration of Assessment")
    private WebElement additionAlterationLink;

    @FindBy(linkText = "Apply for Additional Connection")
    private WebElement additionalNewWaterConnection;

    @FindBy(linkText = "Challan Receipt")
    private WebElement challanReceiptLink;

    @FindBy(linkText = "Daily collection report(PT)")
    private WebElement ptReport;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseToSearchProperty(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("search property");
        waitForElementToBeVisible(searchPropertyLink, driver);

        searchPropertyLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateNewProperty() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create New Property");
        waitForElementToBeVisible(createNewPropertyLink, driver);

        createNewPropertyLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToDataEntryScreen(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Data Entry Screen");
        waitForElementToBeVisible(dataEntryScreenLink, driver);

        dataEntryScreenLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    private void searchFor(String value) {
        enterText(searchTreeTextBox, value);
    }

    public void logOut() {
        waitForElementToBeVisible(profileNameLink, driver);
        profileNameLink.click();
        signOutLink.click();
    }

    public void openApplication(String applicationNumber) {
        getApplicationRowFor(applicationNumber).click();
        switchToNewlyOpenedWindow(driver);
    }

    private WebElement getApplicationRowFor(String applicationNumber) {
        waitForElementToBeVisible(driver.findElement(By.id("worklist")), driver);
        waitForElementToBeVisible(officialInboxTable, driver);

        await().atMost(10, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains(applicationNumber))
                return applicationRow;
        }
        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }

    public void chooseToCollectTaxes() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Property Tax");
        waitForElementToBeVisible(propertyTaxLink, driver);
        propertyTaxLink.click();
        switchToNewlyOpenedWindow(driver);
    }


    //It choose the data entry screen from dashboard
    public void chooseToCreateNewDataEntryScreen() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Data Entry Screen");
        waitForElementToBeVisible(dataEntryScreenLinkText, driver);

        dataEntryScreenLinkText.click();

        switchToNewlyOpenedWindow(driver);}

    public void createMiscellenous() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchTreeTextBox.click();
        searchTreeTextBox.sendKeys("Miscellaneous Receipt");
        miscellaneousReceipt.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateDataEntry() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Data Entry Screen");
        waitForElementToBeVisible(dataEntryScreen, driver);
        dataEntryScreen.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToAdditionAlteration() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Addition/Alteration of Assessment");
        waitForElementToBeVisible(additionAlterationLink, driver);
        additionAlterationLink.click();
        switchToNewlyOpenedWindow(driver);
    }

        public void createSpilloverEstimate()
    {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Spillover Estimate");
        waitForElementToBeVisible(createSpilloverEstimate, driver);
        createSpilloverEstimate.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateNewWaterConnection(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Apply for New Connection");
        waitForElementToBeVisible(applyForNewWaterConnection, driver);

        applyForNewWaterConnection.click();
        switchToNewlyOpenedWindow(driver);

    }

    public void chooseToFindDailyVLTReports(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Daily Collection Report(VLT)");
        waitForElementToBeVisible(vltReport, driver);

        vltReport.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void createChallan() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Challan");

        waitForElementToBeVisible(createChallanLink, driver);
        createChallanLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToAdditionalWaterConnection(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Apply for Additional Connection");
        waitForElementToBeVisible(additionalNewWaterConnection, driver);

        additionalNewWaterConnection.click();
        switchToNewlyOpenedWindow(driver);

    }

    public void chooseToSearchForChallanReceipt() {

        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("challan receipt");

        waitForElementToBeVisible(challanReceiptLink,driver);
        challanReceiptLink.click();

        switchToNewlyOpenedWindow(driver);
    }

    public void chooseForDailyCollectionPTReports(){

        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Daily collection report(PT)");
        waitForElementToBeVisible(ptReport, driver);
        ptReport.click();
        switchToNewlyOpenedWindow(driver);
    }
}

