package steps;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import utils.ScenarioContext;

import java.io.File;
import java.io.IOException;

public class BaseSteps {

    public static PageStore pageStore;

    public static ScenarioContext scenarioContext;
    protected String ptisTestDataFileName = "PTISTestData";
    protected String collectionsTestDataFileName = "CollectionsTestData";
    protected String lineEstimateTestDataFileName = "lineEstimateTestData";
    protected String loginTestDataFileName = "loginTestData";

    protected void takeScreenShot(String screenshotName) throws IOException {
        WebDriver augment = new Augmenter().augment(pageStore.getDriver());
        File file = ((TakesScreenshot) augment).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(screenshotName + ".jpg"));
    }

}
