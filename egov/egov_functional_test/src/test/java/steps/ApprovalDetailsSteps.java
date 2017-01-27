package steps;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import entities.ApprovalDetailsEntity;
import pages.ApprovalDetailsPage;
import utils.ExcelReader;

/**
 * Created by tester1 on 1/26/2017.
 */
public class ApprovalDetailsSteps extends BaseSteps implements En {
    public ApprovalDetailsSteps() {
        And("^he forwards complaint to (.*)$", (String approvalDetailsDataId) -> {
            ApprovalDetailsEntity approvalDetails=new ExcelReader(approvalDetailsTestDataFileName).getApprovalDetailsForGrievance(approvalDetailsDataId);
            pageStore.get(ApprovalDetailsPage.class).enterApprovalDetailsForGrievances(approvalDetails);
        });
        And("^he forwards for approver (.*)$", (String approvalDetailsDataId) -> {
            ApprovalDetailsEntity approvalDetails=new ExcelReader(approvalDetailsTestDataFileName).getApprovalDetailsForGrievance(approvalDetailsDataId);
            pageStore.get(ApprovalDetailsPage.class).enterApprovalDetails(approvalDetails);
            pageStore.get(ApprovalDetailsPage.class).forward();
        });
        And("^he will enter the approval details as (\\w+)$", (String approvalDetailsDataId) -> {
            ApprovalDetailsEntity approvalDetails=new ExcelReader(approvalDetailsTestDataFileName).getApprovalDetailsForGrievance(approvalDetailsDataId);
            pageStore.get(ApprovalDetailsPage.class).enterApproverDetails(approvalDetails);

        });


    }

}
