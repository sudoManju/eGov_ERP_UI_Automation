package pages;

import entities.LoginDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @FindBy(css = ".form-control.style-form.valid")
    private WebElement zoneSelect;

    @FindBy(xpath = "html/body/div[1]/header/nav/div/div[1]/a/img")
    private WebElement logo;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAs(LoginDetails loginDetails) {

        isImageLoaded();
        userNameTextBox.sendKeys(loginDetails.getLoginId());
        passwordTextBox.sendKeys(loginDetails.getPassword());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement signForm = driver.findElement(By.id("signform"));
        waitForElementToBeClickable(signForm, driver);
        signForm.submit();
    }

    private void isImageLoaded(){
        Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", logo);
        for(int i = 0 ; i<= 10 ; i++){
            if (!ImagePresent){
                driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
                driver.navigate().refresh();
            }
            else {
                break;
            }
        }
    }

    public void visitWebsite() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
