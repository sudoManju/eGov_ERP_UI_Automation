package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PropertyAcknowledgementPage extends BasePage {


    private WebDriver driver;

    public PropertyAcknowledgementPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getAcknowledgementMessage() {
        String text = driver.findElement(By.xpath("//table/tbody/tr/td")).getText();
        System.out.println(text);
    }
}
