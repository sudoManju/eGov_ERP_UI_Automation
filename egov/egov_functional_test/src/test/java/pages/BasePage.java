package pages;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Properties;
import utils.ScenarioContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage {

    protected void waitForElementVisibility(By locator, WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, Properties.waitTime);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementToBeVisible(WebElement element, WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, Properties.waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBePresent(By by, WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, Properties.waitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected boolean isElementPresent(By by, WebDriver driver) {
        return driver.findElements(by).size() > 0;
    }

    protected boolean isElementPresentAndDisplayed(By by, WebDriver driver) {
        if (driver.findElements(by).size() > 0)
            return driver.findElement(by).isDisplayed();
        return false;
    }


    protected void waitForElementToDisappear(By locator, WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, Properties.waitTime);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitForElementToBeClickable(WebElement element, WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, Properties.waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void jsClick(WebElement webElement, WebDriver webDriver) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", webElement);
    }

    protected void jsClickCheckbox(WebElement webElement, WebDriver webDriver) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].checked = true;", webElement);
    }


    protected void enterText(WebElement webElement, String value) {
        webElement.clear();
        webElement.sendKeys(value);
    }

    protected void switchToNewlyOpenedWindow(WebDriver driver) {
        await().atMost(20, SECONDS).until(() -> driver.getWindowHandles().size() > 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    protected void switchToPreviouslyOpenedWindow(WebDriver driver) {
        await().atMost(5, SECONDS).until(() -> driver.getWindowHandles().size() == 1);
        for (String winHandle : driver.getWindowHandles()) {
        driver.switchTo().window(winHandle);
    }
}

    protected String get6DigitRandomInt() {return String.valueOf((100000 + RandomUtils.nextInt(900000)));

    }

    protected String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    protected String getPreviousDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }

    protected String getFutureDate(int i) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, i);
        return dateFormat.format(cal.getTime());
    }

    protected String getPastDate(int i) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);
        return dateFormat.format(cal.getTime());
    }

    protected void maximizeBrowserWindow(WebDriver webDriver){
        webDriver.manage().window().maximize();
    }

    protected void refreshBrowserWindow(WebDriver webDriver){
        webDriver.navigate().refresh();
    }

    public void isSuccesful(String expectedMessage,String actualMessage){
        Boolean found = Arrays.asList(actualMessage.split("\\ ")).contains(expectedMessage);
        Assert.assertTrue(found);
//        Assert.assertEquals(expectedMessage,actualMessage);
    }

    public String getEnvironmentURL(){
        String env = System.getProperty("env");
        String url = null;
        if(env.equals("staging")) {
            url = "http://kurnool-uat.egovernments.org";
        }
        else if(env.equals("qa")){
            url = "http://kurnool-qa.egovernments.org";
        }
        return url;
    }
}
