package steps.ptis;


import cucumber.api.PendingException;
import cucumber.api.java8.En;

import entities.ptis.HearingDetails;
import entities.ptis.RevisionPetitionDetails;
import pages.ptis.RevisionPetitionPage;
import steps.BaseSteps;
import utils.ExcelReader;

/**
 * Created by bimal on 25/1/17.
 */
public class RevisionPetitionSteps extends BaseSteps implements En{
    public RevisionPetitionSteps() {

        And("^he search for assessment from commissioner screen$", () -> {

          pageStore.get(RevisionPetitionPage.class).revisionPetitionSearchScreen(scenarioContext.getCommAssessmentNumber());

        });
        And("^he enters revision petition details(\\w+)$", (String revisionPetitionDataId ) -> {

          RevisionPetitionDetails revisionPetitionDetails = new ExcelReader(ptisTestDataFileName).getRevisionPetitionDetails(revisionPetitionDataId);
          pageStore.get(RevisionPetitionPage.class).revisionPetitionBlock(revisionPetitionDetails);

        });
        And("^he choose revision petition header$", () -> {
           pageStore.get(RevisionPetitionPage.class).chooseRevisionPetitionHeader();
        });
        And("^he enters hearing details(\\w+)$", (String hearingDataId) -> {
            HearingDetails hearingDetails = new ExcelReader(ptisTestDataFileName).getHearingDetails(hearingDataId);
            pageStore.get(RevisionPetitionPage.class).enterHearingDetails(hearingDetails);

        });

    }


}
