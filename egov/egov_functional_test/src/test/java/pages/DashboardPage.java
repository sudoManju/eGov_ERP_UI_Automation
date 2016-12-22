package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @FindBy(id = "aplicationSearchResults")
    private WebElement applicationSearchTable;

    @FindBy(id = "official_inbox_wrapper")
    private WebElement inboxTable;

    @FindBy(id = "official_drafts")
    private WebElement officialDraftsTable;

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

    @FindBy(linkText = "Search Application")
    private WebElement searchApplication;

    private WebElement appRow1;

    @FindBy(css = "li[class='dropdown'] a[data-original-title ='Drafts']")
    private WebElement draftsLink;

    @FindBy(linkText = "Collect Tax")
    private WebElement collectPropertyTax;

    @FindBy(linkText = "Apply for Closure of Connection")
    private WebElement closureConnection;

    @FindBy(linkText = "Create Journal Voucher")
    private WebElement createJournalVoucher;

    @FindBy(linkText = "Create Milestone Template")
    private WebElement createMilestoneTemplateLink;

    @FindBy(linkText = "View Milestone Template")
    private WebElement viewMilestoneTemplateLink;


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
        waitForElementToBeVisible(signOutLink , driver);
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

    public void chooseForSearchApplication(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Search Application");
        waitForElementToBeVisible(searchApplication, driver);
        searchApplication.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void openSearchApplication(String applicationNumber) {
        appRow1 = getSearchApplicationRowFor(applicationNumber);
        waitForElementToBeClickable(appRow1 , driver);
        System.out.println(appRow1.getText());
        appRow1.click();
        switchToNewlyOpenedWindow(driver);
    }

    private WebElement getSearchApplicationRowFor(String applicationNumber) {
        waitForElementToBeVisible(driver.findElement(By.id("searchResultDiv")), driver);
        waitForElementToBeVisible(applicationSearchTable, driver);

        await().atMost(10, SECONDS).until(() -> applicationSearchTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = applicationSearchTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());

        for (WebElement applicationRow1 : applicationRows) {
            if (applicationRow1.findElements(By.tagName("td")).get(1).getText().contains(applicationNumber))
                return applicationRow1;
        }

        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }

    public void openDrafts(){
        waitForElementToBeVisible(draftsLink, driver);
        draftsLink.click();

    }

    public void openCollection() {
        getCollectionRow().click();
        switchToNewlyOpenedWindow(driver);
    }

    private WebElement getCollectionRow() {
//        waitForElementToBeVisible(driver.findElement(By.id("drafts")), driver);
        waitForElementToBeVisible(officialDraftsTable, driver);

        await().atMost(10, SECONDS).until(() -> officialDraftsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialDraftsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        List<WebElement> propertyTaxList = new ArrayList<>();

       for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains("Property Tax-944177-Zone-1"))
                propertyTaxList.add(applicationRow);
       }
       return propertyTaxList.get(0);

//       throw new RuntimeException("No application row found for -- ");

    }

    public WebElement getReceiptRow() {
        waitForElementToBeVisible(driver.findElement(By.id("worklist")), driver);
        waitForElementToBeVisible(officialInboxTable, driver);
        await().atMost(10, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        List<WebElement> propertyTaxList = new ArrayList<>();

        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains("Property Tax-944177-Zone-1"))
                propertyTaxList.add(applicationRow);
        }
        return propertyTaxList.get(0);
    }

    public void openReceipt(){
        appRow1 = getReceiptRow();
        waitForElementToBeClickable(appRow1 , driver);
        appRow1.click();
        switchToNewlyOpenedWindow(driver);
    }
    public void choosePropertyTaxCollection() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Collect Tax");
        waitForElementToBeVisible(collectPropertyTax, driver);
        collectPropertyTax.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToApplyForClosureConnection(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Apply for Closure of Connection");
        waitForElementToBeVisible(closureConnection , driver);
        closureConnection.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToApplyForJournalVoucher(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create Journal Voucher");
        waitForElementToBeClickable(createJournalVoucher ,driver);
        createJournalVoucher.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToCreateMilestoneTemplate() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Create milestone template");
        waitForElementToBeClickable(createMilestoneTemplateLink,driver);
        createMilestoneTemplateLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseToViewMilestoneTemplate() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("View milestone template");
        waitForElementToBeClickable(viewMilestoneTemplateLink,driver);
        viewMilestoneTemplateLink.click();
        switchToNewlyOpenedWindow(driver);
    }
}

