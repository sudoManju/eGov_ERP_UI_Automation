package pages;

import entities.LoginDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private WebDriver driver;

    @FindBy(id = "j_username")
    private WebElement userNameTextBox;

    @FindBy(id = "j_password")
    private WebElement passwordTextBox;

    //@FindBy(id = "signin-action")
    @FindBy(css = ".btn.btn-custom.btn-block.btn-login.signin-submit")
    private WebElement signInButton;

    @FindBy(id = "locationId")
    private WebElement locationSelection;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAs(LoginDetails loginDetails) {

        driver.navigate().refresh();
        userNameTextBox.sendKeys(loginDetails.getLoginId());
        passwordTextBox.sendKeys(loginDetails.getPassword());
        passwordTextBox.sendKeys(Keys.CONTROL + "t");
        passwordTextBox.sendKeys(Keys.ENTER);
//        waitForElementToBeClickable(signInButton, driver);
//        signInButton.click();
    }
}
