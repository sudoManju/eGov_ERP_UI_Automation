package pages.works;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by karthik on 26/12/16.
 */
public class MilestoneTrackPage extends BasePage {

    private WebDriver driver;

    @FindBy(xpath = ".//*[@id='btnsearch']")
    private WebElement searchButton;

    @FindBy(id = "resultTable_wrapper")
    private WebElement searchTableForCreate;

    @FindBy(id = "createMilestone")
    private WebElement createMilestoneButton;

    @FindBy(xpath = ".//*[@id='milestone']/div[1]/div/div[1]/div/div/div[2]/div[5]/div[2]/a")
    private WebElement loaNumberBox;

    @FindBy(id = "milestoneDetailsTbl")
    private WebElement milestoneStageTbl;

    @FindBy(id = "addRowBtn")
    private WebElement addRowButton;

    @FindBy(className = "datepicker-days")
    private WebElement calenderForm;

    @FindBy(xpath = ".//*[@id='resultTable']/tbody/tr[1]/td[1]/input")
    private WebElement radioButton;

    public MilestoneTrackPage(WebDriver driver) {this.driver = driver;}

    public void search() {
        waitForElementToBeVisible(searchButton,driver);
        searchButton.click();
    }

    public void select() {
        waitForElementToBeVisible(searchTableForCreate,driver);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitForElementToBeVisible(radioButton,driver);
        jsClick(radioButton,driver);

        waitForElementToBeVisible(createMilestoneButton,driver);
        createMilestoneButton.click();
    }

    public String getLoaNumber() {
        waitForElementToBeVisible(loaNumberBox,driver);
        String loaNumber = loaNumberBox.getText();
        System.out.println("\n Number: "+loaNumber);
        return loaNumber;
    }

    public void enterMilestoneDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());
        System.out.println(date);

        waitForElementToBeVisible(milestoneStageTbl,driver);
        WebElement requiredRow = milestoneStageTbl.findElements(By.tagName("tr")).get(0);

        WebElement stageDescription = requiredRow.findElements(By.tagName("td")).get(1).findElement(By.name("activities[0].description"));
        stageDescription.sendKeys("Stage 1");

        WebElement stagePercentage = requiredRow.findElements(By.tagName("td")).get(2).findElement(By.name("activities[0].percentage"));
        stagePercentage.sendKeys("50");

        WebElement stageScheduleStartDate = requiredRow.findElements(By.tagName("td")).get(3).findElement(By.name("activities[0].scheduleStartDate"));
        stageScheduleStartDate.sendKeys(date);
        stageScheduleStartDate.sendKeys(Keys.TAB);

        WebElement stageScheduleEndDate = requiredRow.findElements(By.tagName("td")).get(4).findElement(By.name("activities0.scheduleEndDate"));
        stageScheduleEndDate.sendKeys(date);

    }

}
