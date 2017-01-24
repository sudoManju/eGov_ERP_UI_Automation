package steps.marriageRegistration;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.marriageRegistration.MarriageRegistrationInformation;
import pages.marriageRegistration.MarriageRegistrationPage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by manjunatha-lap on 24/01/2017.
 */
public class MarriageRegistrationSteps extends BaseSteps implements En {
    public MarriageRegistrationSteps() {
        And("^he enters the applicants details as (\\w+)$", (String applicantsInformationDataId) -> {
            MarriageRegistrationInformation marriageRegistrationInformation = new ExcelReader(marriageRegistrationTestDataFileName).getApplicantsInformation(applicantsInformationDataId);
            pageStore.get(MarriageRegistrationPage.class).enterApplicantsInformation(marriageRegistrationInformation);
        });
    }
}
