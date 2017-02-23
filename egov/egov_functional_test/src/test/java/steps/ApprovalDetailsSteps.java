package steps;

import cucumber.api.java8.En;
import entities.ApprovalDetailsNew;
import pages.ApprovalDetailsPage;
import excelDataFiles.ExcelReader;

public class ApprovalDetailsSteps extends BaseSteps implements En {

    public ApprovalDetailsSteps() {

        And("^he forwards for approver (.*)$", (String approvalDetailsDataId) -> {
            ApprovalDetailsNew approvalDetailsNew = new ExcelReader(approvalDetailsTestDataFileName).getApprovalDetailsNew(approvalDetailsDataId);
            if (approvalDetailsDataId.equals("sanitaryInspector1") || approvalDetailsDataId.equals("LightingSuperintendent")) {
                pageStore.get(ApprovalDetailsPage.class).enterApprovalDetailsForGrievances(approvalDetailsNew);
                pageStore.get(ApprovalDetailsPage.class).createGrievance();
            } else if (approvalDetailsDataId.equals("sanitaryInspector") || approvalDetailsDataId.equals("commissioner") || approvalDetailsDataId.equals("commissioner2")) {
                pageStore.get(ApprovalDetailsPage.class).enterApprovalDetails(approvalDetailsNew);
                pageStore.get(ApprovalDetailsPage.class).forward();
            } else if (approvalDetailsDataId.equals("commissioner1")) {
                pageStore.get(ApprovalDetailsPage.class).enterApproverDetails(approvalDetailsNew);
                pageStore.get(ApprovalDetailsPage.class).forward();
            }
        });
    }
}
