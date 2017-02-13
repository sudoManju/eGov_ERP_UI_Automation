package steps;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.ApprovalDetailsEntity;
import pages.ApprovalDetailsPage;
import utils.ExcelReader;

import static com.googlecode.totallylazy.Functions.or;

/**
 * Created by tester1 on 1/26/2017.
 */
public class ApprovalDetailsSteps extends BaseSteps implements En {

    public ApprovalDetailsSteps() {

        And("^he forwards for approver (.*)$", (String approvalDetailsDataId) -> {
            ApprovalDetailsEntity approvalDetails=new ExcelReader(approvalDetailsTestDataFileName).getApprovalDetailsForGrievance(approvalDetailsDataId);
            if (approvalDetailsDataId.equals("sanitaryInspector1")  || approvalDetailsDataId.equals("LightingSuperintendent"))
            {
                pageStore.get(ApprovalDetailsPage.class).enterApprovalDetailsForGrievances(approvalDetails);
                pageStore.get(ApprovalDetailsPage.class).createGrievance();
            }
            else if(approvalDetailsDataId.equals("sanitaryInspector") || approvalDetailsDataId.equals("commissioner") || approvalDetailsDataId.equals("commissioner2"))
            {
                pageStore.get(ApprovalDetailsPage.class).enterApprovalDetails(approvalDetails);
                pageStore.get(ApprovalDetailsPage.class).forward();
            }

            else if(approvalDetailsDataId.equals("commissioner1"))
            {
                pageStore.get(ApprovalDetailsPage.class).enterApproverDetails(approvalDetails);
                pageStore.get(ApprovalDetailsPage.class).forward();
            }

        });
    }
}
