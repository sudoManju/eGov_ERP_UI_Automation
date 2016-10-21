package pages;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Properties;

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


    protected String randomEmail() {
        String random = RandomStringUtils.randomAlphabetic(8);
        return random.toLowerCase() + "@tv.com";
    }

    protected void enterText(String textToBeEntered, WebElement textBox) {
        textBox.clear();
        textBox.sendKeys(textToBeEntered);
    }

    protected String getSelectedOption(WebElement element) {
        String selectedOption = element.getAttribute("value");
//        Select select = new Select(element);
//        selectedOption = select.getFirstSelectedOption().getText();
        return selectedOption;
    }

    protected void getElementIntoView(WebElement elementToBringIntoView, WebDriver driver) {
        if (elementToBringIntoView.isEnabled()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementToBringIntoView);
        }
    }
}
