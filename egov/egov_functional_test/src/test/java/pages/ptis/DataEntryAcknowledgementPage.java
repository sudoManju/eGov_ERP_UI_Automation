package pages.ptis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by bimal on 5/12/16.
 */


    public class DataEntryAcknowledgementPage extends BasePage {


        private WebDriver driver;

        @FindBy(css = "input[value='Close'][type='button']")
        private WebElement closeButton;

        @FindBy(linkText = "Close")
        private WebElement closeLink;


        public DataEntryAcknowledgementPage(WebDriver driver) {
            this.driver = driver;
        }

    public String getdataentryAcknowledgementMessage() {
        return driver.findElement(By.xpath("//table/tbody/tr/td")).getText();

    }

        public String getAssessmentNumber() {
            List<WebElement> elements = driver.findElement(By.tagName("table")).findElement(By.tagName("tbody"))
                    .findElement(By.tagName("tr")).findElement(By.tagName("td")).findElements(By.tagName("span"));
            return elements.get(1).getText();

        }

        public void close() {
            closeButton.click();
            await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
        }



//    public String getAssessmentNumber() {
//    }
}
