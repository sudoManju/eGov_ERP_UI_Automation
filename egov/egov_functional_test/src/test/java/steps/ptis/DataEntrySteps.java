package steps.ptis;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.ptis.OwnerDetails;
import entities.ptis.PropertyHeaderDetails;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pages.ptis.PropertyDetailsPage;
import steps.BaseSteps;
import utils.ExcelReader;

import java.io.IOException;

/**
 * Created by bimal on 16/11/16.
 */
public class DataEntrySteps extends BaseSteps implements En {
    public DataEntrySteps() {
        And("^he creates a new assessment for a private residential property$", () -> {
            String assessmentNumber = "1016"+ get6DigitRandomInt();
            scenarioContext.setAssessmentNumber(assessmentNumber);

            pageStore.get(PropertyDetailsPage.class).enterAssessmentNumber(assessmentNumber);

            pageStore.get(PropertyDetailsPage.class)
                    .enterPropertyHeader(new ExcelReader(ptisTestDataFileName).getPropertyHeaderDetails("residentialPrivate"));

            pageStore.get(PropertyDetailsPage.class)
                    .enterOwnerDetails(new ExcelReader(ptisTestDataFileName).getOwnerDetails("bimal"));

            pageStore.get(PropertyDetailsPage.class)
                    .enterPropertyAddressDetails(new ExcelReader(ptisTestDataFileName).getPropertyAddressDetails("addressOne"));
            pageStore.get(PropertyDetailsPage.class)
                    .enterAssessmentDetails(new ExcelReader(ptisTestDataFileName).getAssessmentDetails("assessmentNewProperty"));
            pageStore.get(PropertyDetailsPage.class)
                    .selectAmenities(new ExcelReader(ptisTestDataFileName).getAmenties("all"));
            pageStore.get(PropertyDetailsPage.class)
                    .enterConstructionTypeDetails(new ExcelReader(ptisTestDataFileName).getConstructionTypeDetails("defaultConstructionType"));
            pageStore.get(PropertyDetailsPage.class)
                    .enterFloorDetails(new ExcelReader(ptisTestDataFileName).getFloorDetails("firstFloor"));

            pageStore.get(PropertyDetailsPage.class).create();

        });
    }

    private String get6DigitRandomInt() {return String.valueOf((100000 + RandomUtils.nextInt(900000)));
    }
}
