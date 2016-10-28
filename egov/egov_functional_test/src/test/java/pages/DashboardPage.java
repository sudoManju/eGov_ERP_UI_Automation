package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DashboardPage extends BasePage {
    private WebDriver driver;

    @FindBy(id = "searchtree")
    private WebElement searchTreeTextBox;

    @FindBy(linkText = "Create New Property")
    private WebElement createNewPropertyLink;

    @FindBy(className = "profile-name")
    private WebElement profileNameLink;

    @FindBy(linkText = "Sign Out")
    private WebElement signOutLink;

    @FindBy(id = "official_inbox")
    private WebElement officialInboxTable;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseToCreateNewProperty() {
        waitForElementToBeClickable(searchTreeTextBox, driver);
        enterText(searchTreeTextBox, "Create New Property");
        waitForElementToBeVisible(createNewPropertyLink, driver);

        createNewPropertyLink.click();

        switchToNewlyOpenedWindow();
    }

    private void switchToNewlyOpenedWindow() {
        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() > 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void logOut() {
        waitForElementToBeVisible(profileNameLink, driver);
        profileNameLink.click();
        signOutLink.click();
    }

    public void openApplication(String applicationNumber) {
        getApplicationRowFor(applicationNumber).click();
        switchToNewlyOpenedWindow();
    }

    private WebElement getApplicationRowFor(String applicationNumber) {
        waitForElementToBeVisible(driver.findElement(By.id("worklist")), driver);
        waitForElementToBeVisible(officialInboxTable, driver);

        await().atMost(5, SECONDS).until(() -> officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size() > 1);
        List<WebElement> applicationRows = officialInboxTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("total number of rows -- " + applicationRows.size());
        for (WebElement applicationRow : applicationRows) {
            if (applicationRow.findElements(By.tagName("td")).get(4).getText().contains(applicationNumber))
                return applicationRow;
        }
        throw new RuntimeException("No application row found for -- " + applicationNumber);
    }
}
