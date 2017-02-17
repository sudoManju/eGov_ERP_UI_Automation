package pages;

import cucumber.runtime.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DashboardPage extends BasePage {
    private WebDriver driver;

    @FindBy(id = "searchtree")
    private WebElement searchTreeTextBox;

    @FindBy(css = ".list a")
    private List<WebElement> searchResults;

    @FindBy(linkText = "Collect Charges")
    private WebElement collectChargesLink;

    @FindBy(className = "profile-name")
    private WebElement profileNameLink;

    @FindBy(linkText = "Sign Out")
    private WebElement signOutLink;

    @FindBy(linkText = "Search Application")
    private WebElement searchApplication;

    private WebElement appRow1;

    @FindBy(xpath = "html/body/div[1]/header/nav/div/div[3]/ul/li[2]/a")
    private WebElement profileLink;

    @FindBy(linkText = "Cheque Assignment")
    private List<WebElement> chequeAssignment;

    @FindBy(linkText = "RTGS Assignment")
    private WebElement rtgsAssignment;

    @FindBy(css = "li[class='dropdown'] a[data-work='worklist']")
    private WebElement officialInboxTable;

    @FindBy(css = "li[class='dropdown'] a[data-work='drafts']")
    private WebElement officialDraftsTable;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
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

    public void chooseForSearchApplication(){
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Search Application");
        waitForElementToBeVisible(searchApplication, driver);
        searchApplication.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseTopayWaterCharge() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor("Collect Charges");
        waitForElementToBeVisible(collectChargesLink, driver);
        collectChargesLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseForModeOFAssignment(String mode){
        waitForElementToBeClickable(searchTreeTextBox, driver);

        if(mode.equalsIgnoreCase("cheque")){
            searchFor("Cheque Assignment");
            waitForElementToBeClickable(chequeAssignment.get(0), driver);
            chequeAssignment.get(0).click();
            switchToNewlyOpenedWindow(driver);
        }
        else {
            searchFor("RTGS Assignment");
            waitForElementToBeClickable(rtgsAssignment, driver);
            rtgsAssignment.click();
            switchToNewlyOpenedWindow(driver);
        }
    }

    public void chooseScreen(String screenName) {

        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor(screenName);
        waitForElementToBePresent(By.cssSelector(".list a"), driver);
        searchResults.stream().filter(searchResult -> searchResult.getText().equalsIgnoreCase(screenName)).findFirst().get().click();
        switchToNewlyOpenedWindow(driver);
    }

    public void chooseScreen(String screenName, String condition) {

        waitForElementToBeClickable(searchTreeTextBox, driver);
        searchFor(screenName);
        waitForElementToBePresent(By.cssSelector(".list a"), driver);
        Optional<WebElement> href = searchResults.stream().filter(searchResult -> {
            return searchResult.getText().equalsIgnoreCase(screenName) && searchResult.getAttribute("href").contains(condition);
        }).findFirst();
        if(href.isPresent()) {
            href.get().click();
        }
        switchToNewlyOpenedWindow(driver);
    }
    public void openApplicationNew(String number)
    {
        getApplicationRow(number).click();
        switchToNewlyOpenedWindow(driver);
    }

    private WebElement getApplicationRow(String number){
        List<WebElement> totalRows;
        try{
            waitForElementToBeVisible(officialInboxTable, driver);
            officialInboxTable.click();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            await().atMost(20, SECONDS).until(() -> driver.findElement(By.id("official_inbox")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
            totalRows = driver.findElement(By.id("official_inbox")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            for (WebElement voucherRow : totalRows) {
                if (voucherRow.findElements(By.tagName("td")).get(4).getText().contains(number))
                    return voucherRow;
            }
            throw new RuntimeException("No voucher row found in Inbox -- " + number);
        }
        catch (Exception e){
            waitForElementToBeClickable(officialDraftsTable , driver);
            officialDraftsTable.click();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            await().atMost(20, SECONDS).until(() -> driver.findElement(By.id("official_drafts")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
            totalRows = driver.findElement(By.id("official_drafts")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            for (WebElement voucherRow : totalRows) {
                if (voucherRow.findElements(By.tagName("td")).get(4).getText().contains(number))
                    return voucherRow;
            }
            throw new RuntimeException("No voucher row found in Inbox and Drafts -- " + number);
        }
    }
}


