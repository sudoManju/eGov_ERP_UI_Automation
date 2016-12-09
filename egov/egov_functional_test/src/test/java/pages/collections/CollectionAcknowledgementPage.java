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

    @FindBy(css = "input[value='Close'][type='button']")
    private WebElement closeButton;

    @FindBy(linkText = "Close")
    private WebElement closeLink;

    @FindBy(id = "buttonprint")
    private WebElement printButton;

    @FindBy(xpath = ".//*[@id='actionMessages']/ul/li")
    private WebElement number;

    public String getChallanNumber() {

        String num = number.getText();
        int i = num.lastIndexOf(' ');
        String number = num.substring(i+1);

        System.out.println("Challan Number"+ number);

        return number;
    }

    public void close() {

        waitForElementToBeClickable(closeButton,driver);
        closeButton.click();
        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        switchToNewlyOpenedWindow(driver);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }
}
