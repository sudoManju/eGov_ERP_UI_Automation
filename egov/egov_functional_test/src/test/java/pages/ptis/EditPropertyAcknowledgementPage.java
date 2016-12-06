package pages.ptis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by bimal on 30/11/16.
 */
public class EditPropertyAcknowledgementPage {


    //  public void close() {

    private WebDriver driver;

    @FindBy(css = "input[value='Close'][type='button']")
    private WebElement closeButton;

    public void close() {
        closeButton.click();
        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public String getApplicationNumber() {

        return "1016041190";
    }
}


