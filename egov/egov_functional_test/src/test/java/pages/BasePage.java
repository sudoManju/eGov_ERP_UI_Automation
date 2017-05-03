package pages;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Properties;

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

    protected void jsClick(WebElement webElement, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }

    protected void jsClickCheckbox(WebElement webElement, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", webElement);
    }

    protected void enterText(WebElement webElement, String value, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        webElement.clear();
        webElement.sendKeys(value);
    }

    protected void selectFromDropDown(WebElement webElement, String value, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        await().atMost(20, SECONDS).until(() -> new Select(webElement).getOptions().size() > 1);
        new Select(webElement).selectByVisibleText(value);
    }

    protected void selectAParticularFromDropDown(WebElement webElement, int i, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        await().atMost(20, SECONDS).until(() -> new Select(webElement).getOptions().size() > 1);
        new Select(webElement).selectByIndex(i);
    }

    protected void clickOnButton(WebElement webElement, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        webElement.click();
    }

    protected String getTextFromWeb(WebElement webElement, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        return webElement.getText();
    }

    protected void uploadFile(WebElement element, String filePath, WebDriver driver) {
        waitForElementToBeVisible(element, driver);
        element.sendKeys(filePath);
    }

    protected void enterDate(WebElement webElement, String date, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        webElement.clear();
        webElement.sendKeys(date, Keys.TAB);
    }

    protected void checkDropdownIsLoadedOrNot(WebElement webElement, String text, WebDriver webDriver , String containsText) {
        if (webElement.getText().contains(containsText)) {
            selectFromDropDown(webElement, text, webDriver);
        }
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

    protected String get6DigitRandomInt() {
        return String.valueOf((100000 + RandomUtils.nextInt(900000)));
    }

    protected String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    protected String getCurrentYear() {
        return getCurrentDate().replaceAll("/", "-").split("-")[2];
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

    protected void maximizeBrowserWindow(WebDriver webDriver) {
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected void refreshBrowserWindow(WebDriver webDriver) {
        webDriver.navigate().refresh();
    }

    public void isSuccesful(String expectedMessage, String actualMessage) {
        Boolean found = Arrays.asList(actualMessage.split("\\ ")).contains(expectedMessage);
        Assert.assertTrue(found);
    }

    protected String getEnvironmentURL() {
        String env = System.getProperty("env");
        String url = null;
        if (env.equals("staging")) {
            url = "http://kurnool-uat.egovernments.org";
        } else if (env.equals("qa")) {
            url = "http://kurnool-qa.egovernments.org";
        }
        return url;
    }
}