package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Properties;

import java.io.IOException;

public class StartingSteps extends BaseSteps {

    @Before
    public void beforeScenario() throws IOException {
        pageStore = new PageStore();
        pageStore.getDriver().get(Properties.url);
        pageStore.getDriver().manage().window().maximize();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) pageStore.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        pageStore.pages.clear();
//        pageStore.destroy();
    }
}
