package pages.works;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

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

    @FindBy(xpath = "//*[@id='estimatePhotographs']/div[3]/div/div/div[2]/div/div/div/a")
    private WebElement browseFiles;

    public void searchEstimate() {
        waitForElementToBeClickable(searchEstimateButton, driver);
        searchEstimateButton.click();
        waitForElementToBeClickable(uploadPhotoLink, driver);
        uploadPhotoLink.click();
        switchToNewlyOpenedWindow(driver);
    }

//    public void uploadEstimatePhotos() {
//        browseFiles.sendKeys(System.getProperty("user.dir") + "E:/backupAutomation/eGov/egov/egov_functional_test/src/test/resources/During.PNG");
//    }
}
