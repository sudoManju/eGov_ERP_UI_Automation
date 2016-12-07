package pages.collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by karthik on 7/12/16.
 */
public class CollectionAcknowledgementPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "challan.challanNumber")
    private WebElement challanNumber;

    @FindBy(id = "buttonclose2")
    private WebElement closeButton;

    public String getChallanNumber() {

        String num = challanNumber.getCssValue("challan.challanNumber");

        System.out.println("Challan Number"+ num);

        return num;
    }

    public void close() {
        waitForElementToBeClickable(closeButton,driver);
        closeButton.click();
        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }
}
