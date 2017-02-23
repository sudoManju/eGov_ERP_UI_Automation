package builders;

import entities.ApprovalDetailsNew;

public class ApprovalDetailsEntityBuilder {
    ApprovalDetailsNew approvalDetailsNew = new ApprovalDetailsNew();

    public ApprovalDetailsEntityBuilder() {
    }

    public ApprovalDetailsEntityBuilder withApproverDepartment(String approverDepartment) {
        approvalDetailsNew.setApproverDepartment(approverDepartment);
        return this;
    }

    public ApprovalDetailsEntityBuilder withApproverDesignation(String approverDesignation) {
        approvalDetailsNew.setApproverDesignation(approverDesignation);
        return this;
    }

    public ApprovalDetailsEntityBuilder withApprover(String approver) {
        approvalDetailsNew.setApprover(approver);
        return this;
    }

    public ApprovalDetailsEntityBuilder withApproverRemarks(String approverRemarks) {
        approvalDetailsNew.setApproverRemarks(approverRemarks);
        return this;
    }

    public ApprovalDetailsNew build() {
        return approvalDetailsNew;
    }
}
