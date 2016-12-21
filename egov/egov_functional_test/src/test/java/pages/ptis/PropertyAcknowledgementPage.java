package pages.ptis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PropertyAcknowledgementPage extends BasePage {


    private WebDriver driver;

    @FindBy(css = "input[value='Close'][type='button']")
    private WebElement closeButton;

    @FindBy(linkText = "Close")
    private WebElement closeLink;

    @FindBy(id = "buttonClose")
    private WebElement propertyCloseButton;

    @FindBy(id = "view")
    private WebElement assessmentViewButton;

    @FindBy(className = "btn btn-default")
    private WebElement assessmentCloseButton;


    public PropertyAcknowledgementPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAcknowledgementMessage() {
        return driver.findElement(By.xpath("//table/tbody/tr/td")).getText();
    }

    public String getApplicationNumber() {
        List<WebElement> elements = driver.findElement(By.tagName("table")).findElement(By.tagName("tbody"))
                .findElement(By.tagName("tr")).findElement(By.tagName("td")).findElements(By.tagName("span"));
        return elements.get(0).getText();
    }

    public void close() {
        waitForElementToBeClickable(closeButton,driver);
        closeButton.click();
        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void close1() {
        waitForElementToBeClickable(propertyCloseButton,driver);
        propertyCloseButton.click();
        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public String getSignatureNotification() {
        return driver.findElement(By.cssSelector("div.panel-title")).getText();
    }

    public void closeFromCommisionersLogin() {
        closeLink.click();
        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void cancelPrint() {
        driver.findElement(By.className("cancel")).click();
    }

    public void toViewSubmissionPage(){
        waitForElementToBeClickable(assessmentViewButton , driver);
        assessmentViewButton.click();
    }

    public void toCloseDataEntryPage(){
        waitForElementToBeClickable(assessmentCloseButton , driver);
        assessmentCloseButton.click();
    }

    public void toCloseAdditionalConnectionPage(){

        closeLink.click();
        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }
}
