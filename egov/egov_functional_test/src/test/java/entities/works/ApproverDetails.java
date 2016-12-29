package entities.works;

/**
 * Created by karthik on 28/12/16.
 */
public class ApproverDetails {

    String approverDepartment;
    String approverDesignation;
    String approver;
    String approverComment;

    public String getApproverComment() {
        return approverComment;
    }

    public void setApproverComment(String approverComment) {
        this.approverComment = approverComment;
    }

    public String getApproverDepartment() {
        return approverDepartment;
    }

    public void setApproverDepartment(String approverDepartment) {
        this.approverDepartment = approverDepartment;
    }

    public String getApproverDesignation() {
        return approverDesignation;
    }

    public void setApproverDesignation(String approverDesignation) {
        this.approverDesignation = approverDesignation;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }
}
