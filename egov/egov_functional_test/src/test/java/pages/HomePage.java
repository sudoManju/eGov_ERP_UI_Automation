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

    @FindBy(id = "signin-action")
    private WebElement signInButton;

    @FindBy(id = "locationId")
    private WebElement locationSelection;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAs(LoginDetails loginDetails) {
        userNameTextBox.sendKeys(loginDetails.getLoginId());
        passwordTextBox.sendKeys(loginDetails.getPassword());
        if (loginDetails.getHasZone()) {
            waitForElementToBeVisible(locationSelection, driver);
        }
        waitForElementToBeClickable(signInButton, driver);
        signInButton.click();
//        jsClick(signInButton, driver);
    }
}
