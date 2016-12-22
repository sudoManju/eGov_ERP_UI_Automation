package pages.works;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.Calendar;
import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by karthik on 21/12/16.
 */
public class MilestoneTemplatePage extends BasePage {

    private WebDriver driver;

    @FindBy(css = "input[name ='code'][type = 'text']")
    private WebElement templateCodeBox;

    @FindBy(xpath = ".//*[@id='name']")
    private WebElement templateNameTextBox;

    @FindBy(id = "templateDescription")
    private WebElement templateDescriptionTextBox;

    @FindBy(xpath = ".//*[@id='typeOfWork']")
    private WebElement typeOfWorkBox;

    @FindBy(xpath = ".//*[@id='subType']")
    private WebElement subTypeOfWorkBox;

    @FindBy(id = "temptActvRow")
    private WebElement addTemplateActivityButton;

    @FindBy(className = "yui-dt-data")
    private WebElement stageTable;

    @FindBy(css = "input[value='Save'][type='submit']")
    private WebElement saveButton;

    @FindBy(css = "input[id='closeButton'][value='Close']")
    private WebElement closeButton;

    public MilestoneTemplatePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterMilestoneTemplateDetails() {

     waitForElementToBeVisible(templateCodeBox,driver);
     String templateCode = String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND));
     templateCodeBox.sendKeys("TC"+templateCode);

     waitForElementToBeClickable(templateNameTextBox,driver);
     templateNameTextBox.sendKeys("testing");

     waitForElementToBeClickable(templateDescriptionTextBox,driver);
     templateDescriptionTextBox.sendKeys("Test-automation for the project");

     waitForElementToBeClickable(typeOfWorkBox,driver);
     new Select(typeOfWorkBox).selectByVisibleText("Roads, Drains, Bridges and Flyovers");

     waitForElementToBeClickable(subTypeOfWorkBox,driver);
     subTypeOfWorkBox.click();
     subTypeOfWorkBox.click();
     new Select(subTypeOfWorkBox).selectByVisibleText("Roads");

     waitForElementToBeVisible(addTemplateActivityButton,driver);
     addTemplateActivityButton.click();

     waitForElementToBeVisible(stageTable,driver);
     List<WebElement>totalRows = stageTable.findElement(By.tagName("tr")).findElements(By.tagName("td"));
        WebElement stageOrderTextBox = totalRows.get(0).findElement(By.className("slnowk"));
        stageOrderTextBox.sendKeys("1");
        WebElement stageDescriptionTextBox = totalRows.get(1).findElement(By.className("selectmultilinewk"));
        stageDescriptionTextBox.sendKeys("Testing for Roads");
        WebElement stagePercentageTextBox = totalRows.get(2).findElement(By.className("selectamountwk"));
        stagePercentageTextBox.sendKeys("100");

    }

    public void saveAndClose() {
        waitForElementToBeClickable(saveButton,driver);
        saveButton.click();

        waitForElementToBeVisible(closeButton,driver);
        closeButton.click();

        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle :driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

    }
}
