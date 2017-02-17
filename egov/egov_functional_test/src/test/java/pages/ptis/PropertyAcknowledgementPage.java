package pages.ptis;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
        return elements.get(1).getText();
    }
    public void close() {

        try {
            waitForElementToBeClickable(closeButton,driver);
            closeButton.click();
        }
        catch (StaleElementReferenceException e){
            WebElement element = driver.findElement(By.cssSelector("input[value='Close'][type='button']"));
            waitForElementToBeClickable(element , driver);
            element.click();
        }
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
        waitForElementToBeVisible(closeLink , driver);
        waitForElementToBeClickable(closeLink , driver);
        closeLink.click();
        switchToPreviouslyOpenedWindow(driver);
    }

    public String getActualMsg() {
        WebElement ele=driver.findElement(By.xpath(".//*[@id='createProperty-create']/div/table/tbody/tr[1]/td"));
        return ele.getText();
    }


    public String getActualMsgAssessment() {
        WebElement ele=driver.findElement(By.xpath(".//*[@id='createProperty-forward']/div/table/tbody/tr[1]/td"));
        return ele.getText();
    }

    public String getAssessmentNumber() {
        List<WebElement> elements = driver.findElement(By.tagName("table")).findElement(By.tagName("tbody"))
                .findElement(By.tagName("tr")).findElement(By.tagName("td")).findElements(By.tagName("span"));
        return elements.get(1).getText();
    }
}
