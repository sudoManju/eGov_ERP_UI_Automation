package builders;

import entities.ApprovalDetailsEntity;

public class ApprovalDetailsEntityBuilder {
    ApprovalDetailsEntity approvalDetails = new ApprovalDetailsEntity();

    public ApprovalDetailsEntityBuilder() {
    }

    public ApprovalDetailsEntityBuilder withApproverDepartment(String approverDepartment) {
        approvalDetails.setApproverDepartment(approverDepartment);
        return this;
    }

    public ApprovalDetailsEntityBuilder withApproverDesignation(String approverDesignation) {
        approvalDetails.setApproverDesignation(approverDesignation);
        return this;
    }

    public ApprovalDetailsEntityBuilder withApprover(String approver) {
        approvalDetails.setApprover(approver);
        return this;
    }

    public ApprovalDetailsEntityBuilder withApproverRemarks(String approverRemarks) {
        approvalDetails.setApproverRemarks(approverRemarks);
        return this;
    }

    public ApprovalDetailsEntity build() {
        return approvalDetails;
    }
}
