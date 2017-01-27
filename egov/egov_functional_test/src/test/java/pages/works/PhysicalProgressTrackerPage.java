package pages.works;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

/**
 * Created by manjunatha-lap on 16/01/2017.
 */
public class PhysicalProgressTrackerPage extends BasePage {

    private WebDriver driver;
    public PhysicalProgressTrackerPage(WebDriver driver){this.driver = driver;}

    @FindBy(id = "btnsearch")
    private WebElement searchEstimateButton;

    @FindBy(xpath = "//*[@id='resultTable']/tbody/tr[1]/td[6]/a")
    private WebElement uploadPhotoLink;

    @FindBy(css = "#before")
    private WebElement browseFile1;

    @FindBy(css = "#during")
    private WebElement browseFile2;

    @FindBy(css = "#after")
    private WebElement browseFile3;

    @FindBy(linkText = "Close")
    private WebElement closeLink;

    public void searchEstimate() {
        waitForElementToBeClickable(searchEstimateButton, driver);
        searchEstimateButton.click();
        waitForElementToBeClickable(uploadPhotoLink, driver);
        uploadPhotoLink.click();
        switchToNewlyOpenedWindow(driver);
    }

    public void uploadEstimatePhotos(){
        browseFile1.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
        browseFile2.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
        browseFile3.sendKeys(System.getProperty("user.dir") + "/src/test/resources/logo.jpg");
    }

    public void close() {
        waitForElementToBeClickable(closeLink,driver);
        closeLink.click();

        for (String winHandle : driver.getWindowHandles()) {
            if(driver.switchTo().window(winHandle).getCurrentUrl().equals("http://kurnool-uat.egovernments.org/egworks/lineestimate/searchlineestimateform")){
                break;
            }
        }

        closeLink.click();

        switchToPreviouslyOpenedWindow(driver);
    }
}
