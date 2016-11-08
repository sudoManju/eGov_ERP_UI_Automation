package pages.collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class PropertyTaxPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "assessmentNum")
    private WebElement assessmentNumberTextBox;

    @FindBy(id = "CollectTax")
    private WebElement collectTexButton;

    @FindBy(id = "payTax")
    private WebElement payTaxButton;

    public PropertyTaxPage(WebDriver driver) {
        this.driver = driver;
    }

    public void collectTaxFor(String assessmentNumber) {
        assessmentNumberTextBox.sendKeys(assessmentNumber);
        collectTexButton.click();
    }

    public void payTax() {
        payTaxButton.click();
    }
}
