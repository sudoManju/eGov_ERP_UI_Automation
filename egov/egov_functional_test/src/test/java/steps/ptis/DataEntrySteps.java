package steps.ptis;

import cucumber.api.java8.En;
import excelDataFiles.PtisDataReader;
import org.apache.commons.lang.math.RandomUtils;
import pages.ptis.PropertyDetailsPage;
import steps.BaseSteps;
import excelDataFiles.ExcelReader;

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
                    .enterPropertyHeader(new PtisDataReader(ptisTestDataFileName).getPropertyHeaderDetails("residentialPrivate"));
            pageStore.get(PropertyDetailsPage.class)
                    .enterOwnerDetails(new PtisDataReader(ptisTestDataFileName).getOwnerDetails("bimal"));
            pageStore.get(PropertyDetailsPage.class)
                    .enterPropertyAddressDetails(new PtisDataReader(ptisTestDataFileName).getPropertyAddressDetails("addressOne"));
            pageStore.get(PropertyDetailsPage.class)
                    .enterAssessmentDetails(new PtisDataReader(ptisTestDataFileName).getAssessmentDetails("assessmentNewProperty"));
            pageStore.get(PropertyDetailsPage.class)
                    .selectAmenities(new PtisDataReader(ptisTestDataFileName).getAmenties("all"));
            pageStore.get(PropertyDetailsPage.class)
                    .enterConstructionTypeDetails(new PtisDataReader(ptisTestDataFileName).getConstructionTypeDetails("defaultConstructionType"));
            pageStore.get(PropertyDetailsPage.class)
                    .enterFloorDetails(new PtisDataReader(ptisTestDataFileName).getFloorDetails("firstFloor"));
            pageStore.get(PropertyDetailsPage.class).create();
        });
    }
    private String get6DigitRandomInt() {return String.valueOf((100000 + RandomUtils.nextInt(900000)));
    }
}
