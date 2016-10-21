package pages;

import entities.LoginDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private WebDriver webDriver;


    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//input[@name='username']")
    private WebElement emailTextBox;

    @FindBy(xpath = "//input[@name='pass']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement submitButton;

    public void loginAs(LoginDetails loginDetails) throws InterruptedException {


        waitForElementToBeClickable(submitButton, webDriver);
        emailTextBox.clear();
        emailTextBox.sendKeys(loginDetails.getLoginId());
//        waitForElementVisibility(By.id("login-iframeame"), webDriver);
//        webDriver.switchTo().frame(webDriver.findElement(By.id("login-iframe")));
//        JavascriptExecutor executor = (JavascriptExecutor) webDriver;

//        executor.executeScript(String.format("document.getElementById('login_email').value='%s';", loginDetails.getLoginId()));
//        executor.executeScript(String.format("document.getElementById('login_password').value='%s';", loginDetails.getPassword()));

        waitForElementToBeClickable(submitButton, webDriver);
        passwordTextBox.clear();
        passwordTextBox.sendKeys(loginDetails.getPassword());

//        jsClick(submitButton, webDriver);
//        Thread.sleep(5000);
//        submitButton.click();
        submitButton.click();
        try {
//            webDriver.switchTo().defaultContent();
            waitForElementToBeClickable(webDriver.findElement(By.linkText("Logout")), webDriver);
//        refreshIfOnLandingPage();
        } catch (NoSuchElementException to) {
//            webDriver.switchTo().frame(webDriver.findElement(By.id("login-iframe")));
            submitButton.click();
        }
    }
    private void refreshIfOnLandingPage() {
        if (!(webDriver.getCurrentUrl().contains("quotes")||webDriver.getCurrentUrl().contains("payment")))
        webDriver.navigate().refresh();
    }

//        catch (NoSuchElementException nsee){
//            return "login failure";
//        }

//        return null;
//    }

    public void switchToLoginFrame() {
        webDriver.switchTo().frame(webDriver.findElement(By.id("login-iframe")));
    }
}
