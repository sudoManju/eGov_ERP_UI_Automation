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

    @FindBy(id = "btnsearch")
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
        stagePercentage.sendKeys(Keys.TAB);

        WebElement stageScheduleDate = requiredRow.findElements(By.tagName("td")).get(3).findElement(By.name("activities[0].scheduleStartDate"));
        clickOnCurrentDate();
        //stageScheduleDate.sendKeys(date);
        stageScheduleDate.sendKeys(Keys.TAB);


        WebElement stageCompletionDate = requiredRow.findElements(By.tagName("td")).get(4).findElement(By.name("activities[0].scheduleEndDate"));
        stageCompletionDate.sendKeys(date);
        stageCompletionDate.sendKeys(Keys.TAB);

        waitForElementToBeVisible(addRowButton,driver);
        addRowButton.click();

        WebElement requiredRow1 = milestoneStageTbl.findElements(By.tagName("tr")).get(1);

        WebElement stageDescription1 = requiredRow1.findElements(By.tagName("td")).get(1).findElement(By.name("activities[1].description"));
        stageDescription1.sendKeys("Stage 2");

        WebElement stagePercentage1 = requiredRow1.findElements(By.tagName("td")).get(2).findElement(By.name("activities[1].percentage"));
        stagePercentage1.sendKeys("50");

        WebElement stageScheduleDate1 = requiredRow1.findElements(By.tagName("td")).get(3).findElement(By.name("activities[1].scheduleStartDate"));
        stageScheduleDate1.sendKeys("28/12/2016");
        stageScheduleDate1.sendKeys(Keys.TAB);

        WebElement stageCompletionDate1 = requiredRow1.findElements(By.tagName("td")).get(4).findElement(By.name("activities[1].scheduleEndDate"));
        stageCompletionDate1.sendKeys("02/01/2017");
        stageCompletionDate1.sendKeys(Keys.TAB);


    }

    public void clickOnCurrentDate()throws StaleElementReferenceException{
        int i,j = 0;
        List<WebElement> rowsPresent = calenderForm.findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        System.out.println("Rows:"+rowsPresent.size());

        for ( i=0;i<rowsPresent.size();i++){
           for ( j=0; j < rowsPresent.get(i).findElements(By.tagName("td")).size(); j++){
               if(rowsPresent.get(i).findElements(By.tagName("td")).get(j).getAttribute("class").equals("active day")) {
                   rowsPresent.get(i).findElements(By.tagName("td")).get(j).click();
                   break;
               }
           }
        }
    }
}
