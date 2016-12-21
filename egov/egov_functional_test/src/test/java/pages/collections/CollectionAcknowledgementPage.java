package pages.collections;

import org.openqa.selenium.*;
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

    @FindBy(css = "input[value='Submit All Collections'][type='submit']")
    private WebElement submitAllCollectionsButton;

    @FindBy(id ="input[value='Approve All Collections'][type='submit']")
    private WebElement approveCollectionButton;

    public String getChallanNumber() {

        String num = challanNumber.getAttribute("value");
        System.out.println("Challan Number"+ num);

        return num;
    }

    public void submitAllCollections() throws Exception {

        //test_Scroll_Page_To_Bottom();
        WebElement element = driver.findElement(By.id("input[value='Submit All Collections'][type='submit']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

        //waitForElementToBeClickable(submitAllCollectionsButton,driver);
        submitAllCollectionsButton.click();
    }


    public void test_Scroll_Page_To_Bottom() throws Exception {
//        init();

//        driver.navigate().to("http://www.alexa.com/topsites/countries;15/LU");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void approveCollection() throws Exception {
        //test_Scroll_Page_To_Bottom();

        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.id("input[value='Approve All Collections'][type='submit']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

//        waitForElementToBeClickable(approveCollectionButton,driver);
//        approveCollectionButton.click();
    }
}
